package school;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class AttendanceModel {

    // temporary implementation!

    private int studentId = 0;
    private Map<LocalDate, Integer> attendace;

    public void setAttendance(Map<LocalDate, Integer> attendace){
        this.attendace = attendace;
    }

    public Map<LocalDate, Integer> getAttendace(){

        // temporary
        return new HashMap<>();
    }
}
