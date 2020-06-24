package meetingroomapplication.reservation;

import org.springframework.cloud.sleuth.annotation.NewSpan;
import org.springframework.cloud.sleuth.annotation.SpanTag;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReservationService {

    private final ReservableRoomRepository reservableRoomRepository;

    private final ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository, ReservableRoomRepository reservableRoomRepository) {
        this.reservationRepository = reservationRepository;
        this.reservableRoomRepository = reservableRoomRepository;
    }

    // USERIf it is a role, it can be canceled if the reservation user is a logged-in user
    // ADMINIn case of roll all reservations can be canceled
    @Transactional
    @PreAuthorize("hasRole('ADMIN') or #reservation.user.userId == principal.user.userId")
	@NewSpan
    public void cancel(@P("reservation") Reservation reservation) {
        this.reservationRepository.delete(reservation);
    }

	@NewSpan
    public Reservation findOne(@SpanTag(key = "reservationId")Integer reservationId) {
        return this.reservationRepository.findById(reservationId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "\n" + "The reservation does not exist."));
    }

	@NewSpan
    public List<ReservableRoom> findReservableRooms(@SpanTag(key = "date") LocalDate date) {
        return this.reservableRoomRepository
            .findByReservableRoomId_reservedDateOrderByReservableRoomId_roomIdAsc(
                date);
    }

    @NewSpan
    public List<Reservation> findReservations(@SpanTag(key = "reservableRoomId") ReservableRoomId reservableRoomId) {
        return this.reservationRepository
            .findByReservableRoom_ReservableRoomIdOrderByStartTimeAsc(
                reservableRoomId);
    }

    @Transactional
	@NewSpan
    public Reservation reserve(Reservation reservation) {
        ReservableRoomId reservableRoomId = reservation.getReservableRoom()
            .getReservableRoomId();
        
        this.reservableRoomRepository
            .findOneForUpdateByReservableRoomId(reservableRoomId)
            .orElseThrow(() -> new ReservationException.Unavailable("You cannot make reservations for the entered date/room combination."));

        // Get all reservation information for the date/room from the ReservableRoom table and check for duplicates
        boolean overlap = this.reservationRepository
            .findByReservableRoom_ReservableRoomIdOrderByStartTimeAsc(
                reservableRoomId)
            .stream().anyMatch(x -> x.overlap(reservation));
        if (overlap) {
            throw new ReservationException.AlreadyReserved("The time slot for input is already reserved");
        }
        //Registration of reservation information
        this.reservationRepository.save(reservation);
        return reservation;
    }
}
