package model;

import dao.PassiveModelDAOImpl;
import enums.Table;
import managers.DbProcessManager;
import model.Attendance;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

public class AttendanceDAO extends PassiveModelDAOImpl<Attendance> {

    AttendanceDAO(Connection connection) {
        super(connection);
    }

    public Map<LocalDate,Boolean> load(int ownerId) {
        LocalDate date;
        Boolean wasPresent;
        int DATE_INDEX = 0;
        int ATTENDANCE_INDEX = 1;
        Map<LocalDate,Boolean> attendance = new HashMap<>();
        List<String[]> dataCollection = getAttendanceData(ownerId);
        if(dataCollection.size() > 0) {
            for (String[] data : dataCollection) {
                date = LocalDate.parse(data[DATE_INDEX]);
                wasPresent = data[ATTENDANCE_INDEX].equals("1");
                attendance.put(date, wasPresent);
            }
        }
        return attendance;
    }

    public boolean saveModel(Attendance attendance) {

        Map<LocalDate,Boolean> datesWithAttendance = attendance.getAttendance();

        if(datesWithAttendance.size() > 0) {
            int ownerId = attendance.getOwnerId();
            try {
                clearAttendance(ownerId);
                String query = String.format("INSERT INTO %s VALUES(null, ?, ?, ?) ", DEFAULT_TABLE);
                preparedStatement = connection.prepareStatement(query);
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
                    preparedStatement.setString(1, date);
                    preparedStatement.setInt(2, presence);
                    preparedStatement.setInt(3, ownerId);
                    preparedStatement.addBatch();
                    index++;
                }
                DbProcessManager.executeBatch(preparedStatement);
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
            return true;
        }
        return false;
    }

    protected void setDefaultTable(){
        this.DEFAULT_TABLE = Table.ATTENDANCE.getName();
    }

    private List<String[]> getAttendanceData(int ownerId) {
        String query = String.format("SELECT date, attendance FROM %s WHERE owner_id=?",
                DEFAULT_TABLE);
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, ownerId);
            resultSet = preparedStatement.executeQuery();
            return DbProcessManager.getObjectsDataCollection(resultSet);
        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private void clearAttendance(int ownerId) throws SQLException {
        String clearQuery = String.format("DELETE FROM %s WHERE owner_id=?", DEFAULT_TABLE);
        preparedStatement = connection.prepareStatement(clearQuery);
        preparedStatement.setInt(1, ownerId);
        DbProcessManager.executeUpdate(preparedStatement);
    }
}
