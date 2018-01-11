package users;


import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
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

    public List<AdminModel> getAdminsFromFile(){
        List<AdminModel> loadedAdmins = new ArrayList<AdminModel>();
        List<String[]> collection = getCollectionTablesByChosenParameters("admin", ROLE_INDEX);
        for(String[] table : collection){
            AdminModel admin = createAdminModel(table);
            loadedAdmins.add(admin);
        }
        return loadedAdmins;
    }

    public List<MentorModel> getMentorsFromFile(){
        List<MentorModel> loadedMentors = new ArrayList<MentorModel>();
        List<String[]> collection = getCollectionTablesByChosenParameters("mentor", ROLE_INDEX);
        for(String[] table : collection){
            MentorModel mentor = createMentorModel(table);
            loadedMentors.add(mentor);
        }
        return loadedMentors;
    }

    public List<StudentModel> getStudentsFromFile(){
        List<StudentModel> loadedStudents = new ArrayList<StudentModel>();
        List<String[]> collection = getCollectionTablesByChosenParameters("student", ROLE_INDEX);
        for(String[] table : collection){
            StudentModel student = createStudentModel(table);
            loadedStudents.add(student);
        }
        return loadedStudents;
    }



}
