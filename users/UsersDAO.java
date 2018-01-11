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

    protected final static int STUDENT_GROUP_INDEX = 5;
    protected final static int STUDENT_TEAM_INDEX = 6;
    protected final static int STUDENT_EXPERIENCE_INDEX = 7;
    protected final static int STUDENT_ATTENDANCE_INDEX = 8;
    protected final static int STUDENT_WALLET_INDEX = 9;


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
            String loginToCompare = element[FNAME_INDEX] + element[ID_INDEX];
            if((login.equals(loginToCompare)) && (password.equals(element[PASSWORD_INDEX]))) {
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

    public void saveModelToFile(UserModel model){
        if(model.getUserRole().equals("admin")){
            saveAdmin( (AdminModel) model);
        } else if(model.getUserRole().equals("mentor")) {
            saveMentor( (MentorModel) model);
        } else {
            saveStudent( (StudentModel) model);
        }
    }

    protected void saveAdmin(AdminModel admin){
        String role = "admin";
        String id = String.valueOf(admin.getUserID());
        String fName = admin.getUserFirstName();
        String lName = admin.getUserLastName();
        String password = admin.getUserPassword();
        removeDataIfIdAlreadyExists(id, ID_INDEX);
        String[] table = {role, id, fName, lName, password};
        loadedTables.add(table);
        saveData();
    }

    protected void saveMentor(MentorModel mentor){
        String role = "mentor";
        String id = String.valueOf(mentor.getUserID());
        String fName = mentor.getUserFirstName();
        String lName = mentor.getUserLastName();
        String password = mentor.getUserPassword();
        String group = String.valueOf(mentor.getMentorGroupName());
        removeDataIfIdAlreadyExists(id, ID_INDEX);
        String[] table = {role, id, fName, lName, password, group};
        loadedTables.add(table);
        saveData();
    }

    protected void saveStudent(StudentModel student){
        String role = "student";
        String id = String.valueOf(student.getUserID());
        String fName = student.getUserFirstName();
        String lName = student.getUserLastName();
        String password = student.getUserPassword();
        String group = String.valueOf(student.getGroup());
        String team = student.getTeam();
        String experience = String.valueOf(student.getExperience());
        String attendance = String.valueOf(student.getAttendance());
        String wallet = String.valueOf(student.getWallet());
        removeDataIfIdAlreadyExists(id, ID_INDEX);
        String[] table = {role, id, fName, lName, password, group, team, experience, attendance, wallet};
        loadedTables.add(table);
        saveData();
    }




}
