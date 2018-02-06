package school;

import application.DbManagerDAO;
import application.Table;

import java.time.LocalDate;
import java.util.*;

public class SchoolDAO {

    final private String EXPERIENCE_LEVELS_TABLE = Table.EXPERIENCE_LEVELS.getName();
    final private String ATTENDANCE_TABLE = Table.ATTENDANCE.getName();

    private DbManagerDAO dao;

    public SchoolDAO() {
         dao = new DbManagerDAO();
    }

    public HashMap<String, Integer> loadExperienceLevels(){
        String levelName;
        Integer levelValue;
        int LEVEL_NAME_INDEX = 0;
        int LEVEL_VALUE_INDEX = 1;
        HashMap<String, Integer> experienceLevels = new HashMap<>();
        final String query = String.format("SELECT level_name, level_value FROM %s;", EXPERIENCE_LEVELS_TABLE);

        List<String[]> dataCollection = dao.getData(query);
        for(String[] data : dataCollection){
            levelName = data[LEVEL_NAME_INDEX];
            levelValue = Integer.parseInt(data[LEVEL_VALUE_INDEX]);
            experienceLevels.put(levelName, levelValue);
        }
        return experienceLevels;
    }

    public void saveExperienceLevels(ExperienceLevelsModel experienceLevels) {
        String clearTableQuery = String.format("DELETE FROM %s;", EXPERIENCE_LEVELS_TABLE);
        dao.inputData(clearTableQuery);
        Map<String,Integer> levelsAndValues = experienceLevels.getLevels();
        if(levelsAndValues.size() > 0) {
            Set<String> levels = levelsAndValues.keySet();
            Integer[] values = levelsAndValues.values().toArray(new Integer[0]);
            int index = 0;
            for(String level : levels) {
                int value = values[index];
                String query = String.format("INSERT INTO %s VALUES(null, '%s', %s);",
                        EXPERIENCE_LEVELS_TABLE, level, value);
                dao.inputData(query);
                index++;
            }
        }
    }

    Map<LocalDate,Boolean> loadAttendance(int studentId) {
        LocalDate date;
        Boolean wasPresent;
        int DATE_INDEX = 0;
        int ATTENDANCE_INDEX = 1;
        Map<LocalDate,Boolean> attendance = new HashMap<>();
        final String query = String.format("SELECT date, attendance FROM %s WHERE student_id=%s;",
                                            ATTENDANCE_TABLE, studentId);
        List<String[]> dataCollection = dao.getData(query);
        for(String[] data : dataCollection){
            date = LocalDate.parse(data[DATE_INDEX]);
            wasPresent = data[ATTENDANCE_INDEX].equals("1");
            attendance.put(date, wasPresent);
        }
        return attendance;
    }

    public void saveAttendance(AttendanceModel attendance) {
        int studentId = attendance.getStudentId();
        String clearQuery = String.format("DELETE FROM %s WHERE student_id=%s;", ATTENDANCE_TABLE, studentId);
        dao.inputData(clearQuery);
        Map<LocalDate,Boolean> datesWithAttendance = attendance.getAttendance();
        if(datesWithAttendance.size() > 0) {
            Set<LocalDate> dates = datesWithAttendance.keySet();
            Boolean[] presences = datesWithAttendance.values().toArray(new Boolean[0]);
            String date;
            int presence;
            int index = 0;
            for(LocalDate localDate : dates) {
                date = localDate.toString();
                if(presences[index]) {
                    presence = 1;
                } else {
                    presence = 0;
                }
                String query = String.format("INSERT INTO %s VALUES(null, '%s', %s, %s);",
                        ATTENDANCE_TABLE, date, presence, studentId);
                dao.inputData(query);
                index++;
            }
        }
    }

    List<String> getStudentsSetsNames(String studentSetTable) {
        List<String> names = new ArrayList<>();
        String query = "SELECT name FROM " + studentSetTable + ";";
        List<String[]> data = dao.getData(query);
        int NAME_INDEX = 0;
        for(String[] table : data){
            names.add(table[NAME_INDEX]);
        }
        return names;
    }
}
