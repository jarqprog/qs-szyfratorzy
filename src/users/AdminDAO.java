package users;


import application.FactoryDAO;
import application.Table;
import application.DbManagerDAO;

public class AdminDAO extends FactoryDAO {

    private int adminId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;


    public AdminDAO(){
        this.DEFAULT_TABLE = Table.ADMINS.getName();
    }

    public AdminModel getOneObject(String[] adminData) {

        final Integer ID_INDEX = 0;
        final Integer FIRST_NAME_INDEX = 1;
        final Integer LAST_NAME_INDEX = 2;
        final Integer EMAIL_INDEX = 3;
        final Integer PASSWORD_INDEX = 4;

        adminId = Integer.parseInt(adminData[ID_INDEX]);
        firstName = adminData[FIRST_NAME_INDEX];
        lastName = adminData[LAST_NAME_INDEX];
        email = adminData[EMAIL_INDEX];
        password = adminData[PASSWORD_INDEX];
        return new AdminModel(adminId, firstName, lastName, email, password);
    }

    public AdminModel getOneObject(String query) {
        dao = new DbManagerDAO();
        String[] record = dao.getData(query).get(0);
        return getOneObject(record);
    }

    public <T> void saveObject(T t){
        AdminModel admin = (AdminModel) t;
        String adminId = String.valueOf(admin.getId());
        firstName = admin.getFirstName();
        lastName = admin.getLastName();
        email = admin.getEmail();
        password = admin.getPassword();
        String query;
        if(adminId.equals("-1")){

            query = String.format(
                            "INSERT INTO %s " +
                            "VALUES(null, '%s', '%s', '%s', '%s');",
                    DEFAULT_TABLE, firstName, lastName, email, password);
        } else{

            query = String.format(
                            "UPDATE %s SET first_name='%s' , last_name='%s', email='%s', password='%s', " +
                            "WHERE id=%s;", DEFAULT_TABLE, firstName, lastName, email, password, adminId);
        }
        dao = new DbManagerDAO();
        dao.inputData(query);
    }
}