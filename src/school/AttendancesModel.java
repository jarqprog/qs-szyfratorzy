package school;

import application.DataTool;

import java.time.LocalDate;
import java.util.*;

public class AttendancesModel {

    private int studentId;
    private Map<LocalDate, Boolean> attendaces;

    public AttendancesModel(Integer studentId) {
        this.studentId = studentId;
        this.attendaces = new HashMap<>();
        if(studentId != -1) {
            setAttendances(); // new created student has id=-1 (while creating) and it's no point to load nothing from database
        }
    }

    public void setAttendances(Map<LocalDate, Boolean> attendaces) {
        this.attendaces = attendaces;
    }

    public void setAttendances() {
        SchoolDAO dao = new SchoolDAO();
        this.attendaces = dao.loadAttendances(studentId);
    }

    public void clearAttendances() {
        this.attendaces.clear();
    }

    public void addAttendance(Boolean attendance) {
        LocalDate date = LocalDate.now();
        this.attendaces.put(date, attendance);
    }

    public void addAttendance(LocalDate date, Boolean attendance) {
        this.attendaces.put(date, attendance);
    }

    public Map<LocalDate, Boolean> getAttendances() {
        return this.attendaces;
    }

    public Map<LocalDate, Boolean> getUpdatedAttendances() {
        setAttendances();
        return this.attendaces;
    }

    public int getStudentId() {
        return studentId;
    }

    public String getPercentageAttendances() {
        setAttendances();
        String attendacesPercentage = "Attendance (in percent): 100%";
        int size = attendaces.size();
        if(size > 0) {
            int counter = 0;
            Collection<Boolean> attendancesCollection = attendaces.values();
            for (Boolean attendance : attendancesCollection) {
                if (attendance) {
                    counter++;
                }
            }
            float attendanceFactor = (float) counter / size;
            attendacesPercentage = "Attendance (in percent): " + String.valueOf(attendanceFactor * 100) + "%";
        }
        return attendacesPercentage;
    }

    public String toString() {
        return getPercentageAttendances();
    }

    public String getFullDataToString() {
        setAttendances();
        Map<LocalDate,Boolean> sorted = DataTool.sortMapByKey(attendaces); // sort map - result LinkedHashMap
        String attendancesPercentage = getPercentageAttendances();
        StringBuilder sb = new StringBuilder();
        sb.append("\n  Attendances:\n\n\t");
        sb.append(attendancesPercentage);
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
        dao.saveAttendances(this);
    }
}
