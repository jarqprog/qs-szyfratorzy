package users;

import java.util.List;
import java.util.ArrayList;
import application.Table;

public class AdminDAO extends UsersDAO {

    private final String DEFAULT_TABLE = Table.ADMINS.getName();

    public List<AdminModel> getAllAdmins(){
        List<AdminModel> admins = new ArrayList<AdminModel>();
        ///
        return admins;
    }

    public AdminModel getAdminById(int id){

        // temporary
        return new AdminModel(200, "Michal", "Lejek", "michal@lejek", "12321");
    }
}
