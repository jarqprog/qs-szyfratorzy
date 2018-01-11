package users;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

public class AdminDAO extends UsersDAO
{
    public List<AdminModel> getTestAdmins(){

        List<AdminModel> adminsCollection = new ArrayList<AdminModel>();
        adminsCollection.add(new AdminModel("Ania", "Bigos", "123"));
        adminsCollection.add(new AdminModel("Lukasz", "Kot", "123"));
        adminsCollection.add(new AdminModel("Artek", "Szafraniec", "123"));
        adminsCollection.add(new AdminModel("Jarek", "Kowalski", "123"));
        adminsCollection.add(new AdminModel("Aneta", "Fajna", "123"));

        return adminsCollection;
    }

}
