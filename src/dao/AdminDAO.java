package dao;


import model.Admin;
import enums.Table;

public class AdminDAO extends ActiveObjDAOImpl<Admin> {

    private String firstName;
    private String lastName;
    private String email;
    private String password;


    public AdminDAO(){
        this.DEFAULT_TABLE = Table.ADMINS.getName();
    }

    public Admin getOneObject(String[] adminData) {

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

    public void saveObject(Admin admin){
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