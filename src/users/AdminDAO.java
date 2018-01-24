package users;

import java.util.List;
import java.util.ArrayList;
import application.Table;
import application.DbManagerDAO;

public class AdminDAO {

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

    public void saveObject(AdminModel admin){

        //
    }

    public void saveObjects(List<AdminModel> admins){

        //
    }
}
