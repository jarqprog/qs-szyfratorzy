package users;

import application.AbstractDAO;

public class UsersDAO extends AbstractDAO implements LogableDAO{

    protected final static int ROLE_INDEX = 0;
    protected final static int ID_INDEX = 1;
    protected final static int FNAME_INDEX = 2;
    protected final static int LNAME_INDEX = 3;
    protected final static int PASSWORD_INDEX = 4;

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

    public String [] importUserData(String login,String password) {
        for(String [] element : getLoadedTables()) {
            if((login.equals(element[2])) && (password.equals(element[4]))) {
                return element;
            }
        }
        String [] empty = {};
        return empty;
    }

    public MentorModel createMentorModel(String[] table){
        int groupIndex = 5;
        int id = Integer.parseInt(table[ID_INDEX]);
        String fname = table[FNAME_INDEX];
        String lname = table[LNAME_INDEX];
        String password = table[PASSWORD_INDEX];
        char group = table[groupIndex].charAt(0);

        return new MentorModel(id, fname, lname, password, group);
    }

    public AdminModel createAdminModel(String[] table){
        int id = Integer.parseInt(table[ID_INDEX]);
        String fname = table[FNAME_INDEX];
        String lname = table[LNAME_INDEX];
        String password = table[PASSWORD_INDEX];

        return new AdminModel(id, fname, lname, password);
    }

    public StudentModel createStudentModel(String[] table){
        int groupIndex = 5;
        int id = Integer.parseInt(table[ID_INDEX]);
        String fname = table[FNAME_INDEX];
        String lname = table[LNAME_INDEX];
        String password = table[PASSWORD_INDEX];
        char group = table[groupIndex].charAt(0);

        return new StudentModel(id, fname, lname, password, group);

    }
}
