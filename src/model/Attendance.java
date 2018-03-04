package model;

import dao.DaoFactory;
import tools.DataTool;
import dao.AttendanceDAO;

import java.time.LocalDate;
import java.util.*;

public class Attendance extends PassiveModel {

    private Map<LocalDate, Boolean> attendance;

    Attendance(Integer ownerId) {
        this.ownerId = ownerId;
        this.attendance = new HashMap<>();
    }

    public void setAttendance(Map<LocalDate, Boolean> attendance) {
        this.attendance = attendance;
        saveModel();
    }

    public void setAttendance() {
        this.attendance = DaoFactory.getByType(AttendanceDAO.class).load(ownerId);
    }

    public void clearAttendance() {
        this.attendance.clear();
        saveModel();
    }

    public void addAttendance(Boolean attendance) {
        setAttendance();
        LocalDate date = LocalDate.now();
        this.attendance.put(date, attendance);
        saveModel();
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

    public String getPercentageAttendance() {
        setAttendance();
        String attendancePercentage = "Attendance (in percent): 100%";
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
            attendancePercentage = "Attendance (in percent): " + String.valueOf(percentageValue) + "%";
        }
        return attendancePercentage;
    }

    public String toString() {
        return getPercentageAttendance();
    }

    public String getFullDataToString() {
        setAttendance();
        Map<LocalDate,Boolean> sorted = DataTool.sortDateMap(attendance); // sort map - result LinkedHashMap
        String attendancePercentage = getPercentageAttendance();
        StringBuilder sb = new StringBuilder();
        sb.append("\t");
        sb.append(attendancePercentage);
        sb.append("\n\n\tdate: attendance\n\t");
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
            sb.append(String.format("\t\t- %s: %s\n", date, attendance));
        }
        return sb.toString();
    }
}
