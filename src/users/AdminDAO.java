package users;

import java.util.List;
import java.util.ArrayList;
import application.Table;
import application.DbManagerDAO;

public class AdminDAO extends UsersDAO {

    private final String DEFAULT_TABLE = Table.ADMINS.getName();

    public List<AdminModel> getObjects(String query){
        List<AdminModel> admins = new ArrayList<AdminModel>();
        ///
        return admins;
    }

    public AdminModel getObject(String query){

        // temporary
        return new AdminModel(200, "Michal", "Lejek", "michal@lejek", "12321");
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
                            "VALUES(null, '%s', '%s', '%s', '%s');",
                    DEFAULT_TABLE, firstName, lastName, email, password);

        } else{

            query = String.format(
                            "UPDATE %s SET first_name='%s' , last_name='%s', email='%s', password='%s', " +
                            "WHERE id=%s;", DEFAULT_TABLE, firstName, lastName, email, password);
        }

    }

    public void saveObjects(List<AdminModel> admins){

    
    }
}
