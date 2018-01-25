package users;

import java.util.List;
import java.util.ArrayList;
import application.Table;
import application.DbManagerDAO;

public class AdminDAO extends UsersDAO {

    private DbManagerDAO dao;

    private final String DEFAULT_TABLE = Table.ADMINS.getName();

    private final Integer ID_INDEX = 0;
    private final Integer FIRST_NAME_INDEX = 1;
    private final Integer LAST_NAME_INDEX = 2;
    private final Integer EMAIL_INDEX = 3;
    private final Integer PASSWORD_INDEX = 4;

    private int adminId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    public List<AdminModel> getManyObjects(List<String[]> dataCollection) {

        List<AdminModel> admins = new ArrayList<AdminModel>();
        for (String[] record : dataCollection) {
            AdminModel admin = getOneObject(record);
            admins.add(student);
        }
        return admins;
    }

    public AdminModel getOneObject(String[] adminData) {

        adminId = Integer.parseInt(adminData[ID_INDEX]);
        firstName = adminData[FIRST_NAME_INDEX];
        lastName = adminData[LAST_NAME_INDEX];
        email = adminData[EMAIL_INDEX];
        password = adminData[PASSWORD_INDEX];

        return new AdminModel(studentId, firstName, lastName, email, password);
    }

    public AdminModel getOneObject(String query) {
        dao = new DbManagerDAO();
        String[] adminData = dao.getData(query).get(0);
        adminId = Integer.parseInt(adminData[ID_INDEX]);
        firstName = adminData[FIRST_NAME_INDEX];
        lastName = adminData[LAST_NAME_INDEX];
        email = adminData[EMAIL_INDEX];
        password = adminData[PASSWORD_INDEX];

        return new AdminModel(studentId, firstName, lastName, email, password);
    }

    public void saveObject(AdminModel admin) {
        String adminId = String.valueOf(admin.getId());
        firstName = admin.getFirstName();
        lastName = admin.getLastName();
        email = admin.getEmail();
        password = admin.getPassword();

        String query;
        if(adminId.equals("-1")){

            query = String.format(
                            "INSERT INTO %s " +
                            "VALUES(null, '%s', '%s', '%s', '%s', %s);",
                    DEFAULT_TABLE, firstName, lastName, email, password);

        } else{

            query = String.format(
                            "UPDATE %s SET first_name='%s' , last_name='%s', email='%s', password='%s', " +
                            "WHERE id=%s;", DEFAULT_TABLE, firstName, lastName, email, password);
        }

        dao = new DbManagerDAO();
        dao.inputData(query);
    }

    public void saveObjects(List<AdminModel> admins){

        for(AdminModel admin : admins) {
            saveObject(admin);
        }
    }
}
