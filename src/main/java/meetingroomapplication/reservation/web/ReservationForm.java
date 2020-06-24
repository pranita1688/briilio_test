package meetingroomapplication.reservation.web;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalTime;

@EndTimeMustBeAfterStartTime(message = "End time should be later than start time")
public class ReservationForm implements Serializable {

    @NotNull(message = "Required")
    @ThirtyMinutesUnit(message = "30Please enter in minutes")
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime endTime;

    @NotNull(message = "Required")
    @ThirtyMinutesUnit(message = "30 Please enter in minutes")
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime startTime;

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }
}
