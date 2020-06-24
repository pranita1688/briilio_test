package meetingroomapplication.reservation;

import java.time.LocalTime;
import java.util.Objects;

import meetingroomapplication.user.User;

public class Reservation {

    private LocalTime endTime;

    private ReservableRoom reservableRoom;

    private Integer reservationId;

    private LocalTime startTime;

    private User user;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Reservation that = (Reservation) o;

        if (endTime != null ? !endTime.equals(that.endTime) : that.endTime != null) {
            return false;
        }
        if (reservableRoom != null ? !reservableRoom.equals(that.reservableRoom)
            : that.reservableRoom != null) {
            return false;
        }
        if (reservationId != null ? !reservationId.equals(that.reservationId)
            : that.reservationId != null) {
            return false;
        }
        if (startTime != null ? !startTime.equals(that.startTime)
            : that.startTime != null) {
            return false;
        }
        if (user != null ? !user.equals(that.user) : that.user != null) {
            return false;
        }

        return true;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public ReservableRoom getReservableRoom() {
        return reservableRoom;
    }

    public void setReservableRoom(ReservableRoom reservableRoom) {
        this.reservableRoom = reservableRoom;
    }

    public Integer getReservationId() {
        return reservationId;
    }

    public void setReservationId(Integer reservationId) {
        this.reservationId = reservationId;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public int hashCode() {
        int result = reservationId != null ? reservationId.hashCode() : 0;
        result = 31 * result + (startTime != null ? startTime.hashCode() : 0);
        result = 31 * result + (endTime != null ? endTime.hashCode() : 0);
        result = 31 * result + (reservableRoom != null ? reservableRoom.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }

    public boolean overlap(Reservation target) {
        // If the dates and rooms of the two reservations are different, they do not overlap
        if (!Objects.equals(reservableRoom, target.reservableRoom)) {
            return false;
        }
        // 
       // If the start time and end time of two reservations match, it is considered as a duplicate 
        if (startTime.equals(target.startTime) && endTime.equals(target.endTime)) {
            return true;
        }
        // 
       // If the start time and end time of two reservations intersect, it is considered as overlapping if they have an inclusion relationship
        return target.endTime.isAfter(startTime) && endTime.isAfter(target.startTime);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Reservation{");
        sb.append("reservationId=").append(reservationId);
        sb.append(", startTime=").append(startTime);
        sb.append(", endTime=").append(endTime);
        sb.append(", reservableRoom=").append(reservableRoom);
        sb.append(", user=").append(user);
        sb.append('}');
        return sb.toString();
    }
}
