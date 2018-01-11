package users;

import application.AbstractDAO;

public class UsersDAO extends AbstractDAO implements LogableDAO{

    // protected final static ROLE_INDEX = 0;
    // protected final static ID_INDEX = 1;
    // protected final static ROLE_INDEX = 0;
    // protected final static ID_INDEX = 1;

    protected String maxUserIdFile;

    public UsersDAO(){
        defaultFileName = "users.csv";
        defaultFilePath = "DataFiles/users.csv";
        maxUserIdFile = "DataFiles/maxUserId.csv";
        prepareFile();
        prepareFile(maxUserIdFile);
    }

    public AdminModel createFirstAdmin(){
        return new AdminModel("admin", "admin", "admin");
    }


    // public MentorModel createMentorModel(String[] table){
    //
    // }
    //
    // public AdminModel createAdminModel(String[] table){
    //     // AdminModel model = new
    // }
}
