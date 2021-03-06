package meetingroomapplication.reservation.web;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import meetingroomapplication.reservation.ReservableRoom;
import meetingroomapplication.reservation.ReservableRoomId;
import meetingroomapplication.reservation.Reservation;
import meetingroomapplication.reservation.ReservationException;
import meetingroomapplication.reservation.ReservationService;
import meetingroomapplication.room.RoomService;
import meetingroomapplication.user.ReservationUserDetails;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("reservations/{date}/{roomId}")
public class ReservationsController {
    
    private final ReservationService reservationService;

    private final RoomService roomService;

    public ReservationsController(RoomService roomService, ReservationService reservationService) {
        this.roomService = roomService;
        this.reservationService = reservationService;
    }

    @PostMapping(params = "cancel")
    public String cancel(@RequestParam("reservationId") Integer reservationId,
                         @PathVariable("roomId") Integer roomId,
                         @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @PathVariable("date") LocalDate date,
                         Model model) {

        try {
        	
            Reservation reservation = this.reservationService.findOne(reservationId);
            this.reservationService.cancel(reservation);
        } catch (AccessDeniedException e) {
            model.addAttribute("error", e.getMessage());
            return reserveForm(date, roomId, model);
        }
        catch (Exception e) {
        	System.out.println("inside catch-->>>");
            e.printStackTrace();
        }
        return "redirect:/reservations/{date}/{roomId}";
    }

    @PostMapping
    public String reserve(@Validated ReservationForm form, BindingResult bindingResult,
                          @AuthenticationPrincipal ReservationUserDetails userDetails,
                          @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @PathVariable("date") LocalDate date,
                          @PathVariable("roomId") Integer roomId, Model model) {
        if (bindingResult.hasErrors()) {
            return reserveForm(date, roomId, model);
        }

        ReservableRoom reservableRoom = new ReservableRoom(
            new ReservableRoomId(roomId, date));
        Reservation reservation = new Reservation();
        reservation.setStartTime(form.getStartTime());
        reservation.setEndTime(form.getEndTime());
        reservation.setReservableRoom(reservableRoom);
        reservation.setUser(userDetails.getUser());

        try {
            this.reservationService.reserve(reservation);
        } catch (ReservationException e) {
            model.addAttribute("error", e.getMessage());
            return reserveForm(date, roomId, model);
        }
        return "redirect:/reservations/{date}/{roomId}";
    }

    @GetMapping
    public String reserveForm(
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @PathVariable("date") LocalDate date,
        @PathVariable("roomId") Integer roomId, Model model) {
        ReservableRoomId reservableRoomId = new ReservableRoomId(roomId, date);
        List<Reservation> reservations = this.reservationService
            .findReservations(reservableRoomId);

        LocalTime baseTime = LocalTime.of(0, 0);
        List<LocalTime> timeList = IntStream.range(0, 24 * 2)
            .mapToObj(i -> baseTime.plusMinutes(30 * i)).collect(Collectors.toList());
        model.addAttribute("room", roomService.findMeetingRoom(roomId));
        model.addAttribute("reservations", reservations);
        model.addAttribute("timeList", timeList);
        return "reservations/reserveForm";
    }

    @ModelAttribute
    public ReservationForm setUpForm() {
        ReservationForm form = new ReservationForm();
        // デフォルト値
        form.setStartTime(LocalTime.of(9, 0));
        form.setEndTime(LocalTime.of(10, 0));
        return form;
    }
}
