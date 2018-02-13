package model;

import tools.DataTool;
import dao.AttendanceDAO;

import java.time.LocalDate;
import java.util.*;

public class Attendance {

    private int studentId;
    private Map<LocalDate, Boolean> attendance;

    public Attendance(Integer studentId) {
        this.studentId = studentId;
        this.attendance = new HashMap<>();
    }

    public void setAttendance(Map<LocalDate, Boolean> attendance) {
        this.attendance = attendance;
        saveObject();
    }

    public void setAttendance() {
        AttendanceDAO dao = new AttendanceDAO();
        this.attendance = dao.load(studentId);
    }

    public void clearAttendance() {
        this.attendance.clear();
        saveObject();
    }

    public void addAttendance(Boolean attendance) {
        setAttendance();
        LocalDate date = LocalDate.now();
        this.attendance.put(date, attendance);
        saveObject();
    }

    public void addAttendance(LocalDate date, Boolean attendance) {
        this.attendance.put(date, attendance);
    }

    public Map<LocalDate, Boolean> getAttendance() {
        return this.attendance;
    }

    public Map<LocalDate, Boolean> getUpdatedAttendance() {
        setAttendance();
        return this.attendance;
    }

    public int getStudentId() {
        return studentId;
    }

    public String getPercentageAttendance() {
        setAttendance();
        String attendacePercentage = "Attendance (in percent): 100%";
        int size = attendance.size();
        if(size > 0) {
            int counter = 0;
            Collection<Boolean> attendanceCollection = attendance.values();
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
        Map<LocalDate,Boolean> sorted = DataTool.sortDateMap(attendance); // sort map - result LinkedHashMap
        String attendancePercentage = getPercentageAttendance();
        StringBuilder sb = new StringBuilder();
        sb.append("Attendance:\n\n\t");
        sb.append(attendancePercentage);
        sb.append("\n\n\t\t\tdate: attendace\n");
        int lineMultiplier = 45;
        String sign = "-";
        String line = DataTool.getMultipliedString(sign, lineMultiplier) + "\n\t\t";
        sb.append(line);
        String attendance;
        for (Map.Entry<LocalDate, Boolean> entry : sorted.entrySet()) {
            String date = String.valueOf(entry.getKey());
            if(entry.getValue()) {
                attendance = "present";
            } else {
                attendance = "absent";
            }
            sb.append(String.format("\t\t\t- %s: %s\n", date, attendance));
        }
        return sb.toString();
    }

    public void saveObject() {
        AttendanceDAO dao = new AttendanceDAO();
        dao.save(this);
    }
}
