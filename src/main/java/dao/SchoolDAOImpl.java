package dao;

import enums.Table;
import managers.SQLProcessManager;
import tools.DataTool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;


public class SchoolDAOImpl implements SchoolDAO {

    private Connection connection;

    SchoolDAOImpl(Connection connection) {
        this.connection = connection;
    }

    public List<String> getGroupNames() {

        return getStudentsSetsNames(Table.GROUPS);
    }

    public List<String> getTeamNames() {

        return getStudentsSetsNames(Table.TEAMS);
    }

    private List<String> getStudentsSetsNames(Table studentSetTable) {
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        List<String> names = new ArrayList<>();
        String query = String.format("SELECT name FROM %s", studentSetTable.getName());

        try {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            List<String[]> dataCollection = SQLProcessManager.getObjectsDataCollection(resultSet);
            names = DataTool.flattenNestedCollectionList(dataCollection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return names;
    }
}