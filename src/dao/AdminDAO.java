package dao;

import managers.DbProcessManager;
import model.Admin;
import enums.Table;

import java.sql.Connection;
import java.sql.SQLException;

public class AdminDAO extends ActiveModelDAOImpl<Admin> {

    private String firstName;
    private String lastName;
    private String email;
    private String password;


    AdminDAO(Connection connection) {
        super(connection);
    }

    public Admin extractModel(String[] adminData) {

        final Integer ID_INDEX = 0;
        final Integer FIRST_NAME_INDEX = 1;
        final Integer LAST_NAME_INDEX = 2;
        final Integer EMAIL_INDEX = 3;
        final Integer PASSWORD_INDEX = 4;

        int adminId = Integer.parseInt(adminData[ID_INDEX]);
        firstName = adminData[FIRST_NAME_INDEX];
        lastName = adminData[LAST_NAME_INDEX];
        email = adminData[EMAIL_INDEX];
        password = adminData[PASSWORD_INDEX];
        return new Admin(adminId, firstName, lastName, email, password);
    }

    public boolean saveModel(Admin admin){
        String adminId = String.valueOf(admin.getId());
        firstName = admin.getFirstName();
        lastName = admin.getLastName();
        email = admin.getEmail();
        password = admin.getPassword();
        String query;
        if(adminId.equals("-1")){

            query = String.format(
                            "INSERT INTO %s " +
                            "VALUES(null, ?, ?, ?, ?)", DEFAULT_TABLE);
        } else{
            query = String.format(
                            "UPDATE %s SET first_name=?, last_name=?, email=?, password=?, " +
                            "WHERE id=?", DEFAULT_TABLE);
        }
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, password);

            if(!adminId.equals("-1")) {
                preparedStatement.setInt(5, Integer.valueOf(adminId));
            }
            DbProcessManager.executeUpdate(preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    protected void setDefaultTable(){
        this.DEFAULT_TABLE = Table.ADMINS.getName();
    }
}