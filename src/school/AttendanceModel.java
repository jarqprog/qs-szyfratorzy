package school;

import application.DataTool;

import java.time.LocalDate;
import java.util.*;

public class AttendanceModel {

    private int studentId;
    private Map<LocalDate, Boolean> attendace;

    public AttendanceModel(Integer studentId) {
        this.studentId = studentId;
        this.attendace = new HashMap<>();
        if(studentId != -1) {
            setAttendance(); // new created student has id=-1 (while creating) and it's no point to load nothing from database
        }
    }

    public void setAttendance(Map<LocalDate, Boolean> attendace) {
        this.attendace = attendace;
    }

    public void setAttendance() {
        SchoolDAO dao = new SchoolDAO();
        this.attendace = dao.loadAttendance(studentId);
    }

    public void clearAttendance() {
        this.attendace.clear();
    }

    public void addAttendance(Boolean attendance) {
        LocalDate date = LocalDate.now();
        this.attendace.put(date, attendance);
    }

    public void addAttendance(LocalDate date, Boolean attendance) {
        this.attendace.put(date, attendance);
    }

    public Map<LocalDate, Boolean> getAttendance() {
        return this.attendace;
    }

    public Map<LocalDate, Boolean> getUpdatedAttendance() {
        setAttendance();
        return this.attendace;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId){
        /*
        -- important:
        Only use in StudentModel constructor while creating brand new student (short constructor)
        */
        this.studentId = studentId;
        saveObject();
    }

    public String getPercentageAttendance() {
        setAttendance();
        String attendacePercentage = "Attendance (in percent): 100%";
        int size = attendace.size();
        if(size > 0) {
            int counter = 0;
            Collection<Boolean> attendanceCollection = attendace.values();
            for (Boolean attendance : attendanceCollection) {
                if (attendance) {
                    counter++;
                }
            }
            float attendanceFactor = (float) counter / size;
            int percentageValue = (int) (attendanceFactor * 100);
            attendacePercentage = "Attendance (in percent): " + String.valueOf(percentageValue) + "%";
        }
        return attendacePercentage;
    }

    public String toString() {
        return getPercentageAttendance();
    }

    public String getFullDataToString() {
        setAttendance();
        Map<LocalDate,Boolean> sorted = DataTool.sortDateMap(attendace); // sort map - result LinkedHashMap
        String attendancePercentage = getPercentageAttendance();
        StringBuilder sb = new StringBuilder();
        sb.append("\n  Attendance:\n\n\t");
        sb.append(attendancePercentage);
        sb.append("\n\n\tdate: attendace\n");
        int lineMultiplier = 45;
        String sign = "-";
        String line = DataTool.getMultipliedString(sign, lineMultiplier) + "\n";
        sb.append(line);
        String attendance;
        for (Map.Entry<LocalDate, Boolean> entry : sorted.entrySet()) {
            String date = String.valueOf(entry.getKey());
            if(entry.getValue()) {
                attendance = "present";
            } else {
                attendance = "absent";
            }
            sb.append(String.format("\t- %s: %s\n", date, attendance));
        }
        return sb.toString();
    }

    public void saveObject() {
        SchoolDAO dao = new SchoolDAO();
        dao.saveAttendance(this);
    }
}
