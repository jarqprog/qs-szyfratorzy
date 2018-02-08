package users;


import java.util.List;
import java.util.ArrayList;
import application.DatabaseDAO;
import school.GroupModel;


public class UsersDAO extends DatabaseDAO implements LogableDAO{

    protected final static int ROLE_INDEX = 0;
    protected final static int ID_INDEX = 1;
    protected final static int FNAME_INDEX = 2;
    protected final static int LNAME_INDEX = 3;
    protected final static int EMAIL_INDEX = 4;
    protected final static int PASSWORD_INDEX = 5;


    protected String maxUserIdFile;

    public UsersDAO(){
        defaultFileName = "users.csv";
        defaultFilePath = "data_files/users.csv";
        maxUserIdFile = "data_files/maxUserId.csv";
        prepareFile();
        prepareFile(maxUserIdFile);
    }


    public void prepareAdmin(){
        if(! checkIfAdminInData()){
            saveModelToFile(createFirstAdmin());
        }
    }

    protected Boolean checkIfAdminInData(){
        updateLoadedTables();
        for(String[] table : loadedTables){
            if(checkIfDataMatches("admin", ROLE_INDEX, table)){
                return true;
            }
        }
        return false;
    }

    protected AdminModel createFirstAdmin(){
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
        String email = table[EMAIL_INDEX];
        String password = table[PASSWORD_INDEX];
        GroupModel group = new GroupModel("undefined");

        return new MentorModel(id, fname, lname, email, password, group);
    }

    public AdminModel createAdminModel(String[] table){
        int id = Integer.parseInt(table[ID_INDEX]);
        String fname = table[FNAME_INDEX];
        String lname = table[LNAME_INDEX];
        String email = table[EMAIL_INDEX];
        String password = table[PASSWORD_INDEX];

        return new AdminModel(id, fname, lname, email, password);
    }

    public StudentModel createStudentModel(String[] table){
//        int groupIndex = 5;
//        int id = Integer.parseInt(table[ID_INDEX]);
//        String fname = table[FNAME_INDEX];
//        String lname = table[LNAME_INDEX];
//        String email = table[EMAIL_INDEX];
//        String password = table[PASSWORD_INDEX];
//        int wallet = 100;
//        int experience = 1000;
//        GroupModel group = new GroupModel("undefined");
//        TeamModel team = new TeamModel("undefined");
//        InventoryModel inventory = new InventoryModel(id);
//
//        return new StudentModel(id, fname, lname, email, password, wallet,
//                                experience, team, group, inventory);

        return null;
    }

    public List<AdminModel> getAdminsFromFile(){
        List<AdminModel> loadedAdmins = new ArrayList<AdminModel>();
//        List<String[]> collection = getCollectionTablesByChosenParameters("admin", ROLE_INDEX);
//        for(String[] table : collection){
//            AdminModel admin = createAdminModel(table);
//            loadedAdmins.add(admin);
//        }
        return loadedAdmins;
    }

    public List<MentorModel> getMentorsFromFile(){
        List<MentorModel> loadedMentors = new ArrayList<MentorModel>();
//        List<String[]> collection = getCollectionTablesByChosenParameters("mentor", ROLE_INDEX);
//        for(String[] table : collection){
//            MentorModel mentor = createMentorModel(table);
//            loadedMentors.add(mentor);
//        }
        return loadedMentors;
    }

    public List<StudentModel> getStudentsFromFile(){
        List<StudentModel> loadedStudents = new ArrayList<StudentModel>();
//        List<String[]> collection = getCollectionTablesByChosenParameters("student", ROLE_INDEX);
//        for(String[] table : collection){
//            StudentModel student = createStudentModel(table);
//            loadedStudents.add(student);
//        }
        return loadedStudents;
    }

    public void saveModelToFile(UserModel model){
//        if(model.getRole().equals("admin")){
//            saveAdmin( (AdminModel) model);
//        } else if(model.getRole().equals("mentor")) {
//            saveMentor( (MentorModel) model);
//        } else {
//            saveStudent( (StudentModel) model);
//        }
    }

    protected void saveAdmin(AdminModel admin){
//        String role = "admin";
//        String id = String.valueOf(admin.getId());
//        String fName = admin.getFirstName();
//        String lName = admin.getLastName();
//        String email = admin.getEmail();
//        String password = admin.getPassword();
//        removeDataIfIdAlreadyExists(id, ID_INDEX);
//        String[] table = {role, id, fName, lName, email, password};
//        loadedTables.add(table);
//        saveData();
    }

    protected void saveMentor(MentorModel mentor){
//        String role = "mentor";
//        String id = String.valueOf(mentor.getId());
//        String fName = mentor.getFirstName();
//        String lName = mentor.getLastName();
//        String email = mentor.getEmail();
//        String password = mentor.getPassword();
////        String group = String.valueOf(mentor.getGroupName());
//        removeDataIfIdAlreadyExists(id, ID_INDEX);
////        String[] table = {role, id, fName, lName, email, password, group};
////        loadedTables.add(table);
//        saveData();
    }

    protected void saveStudent(StudentModel student){
//        String role = "student";
//        String id = String.valueOf(student.getId());
//        String fName = student.getFirstName();
//        String lName = student.getLastName();
//        String email = student.getEmail();
//        String password = student.getPassword();
//        String group = String.valueOf(student.getGroup());
////        String team = student.getTeam();
//        String experience = String.valueOf(student.getExperience());
//        String attendance = String.valueOf(student.getAttendance());
//        String wallet = String.valueOf(student.getWallet());
//        removeDataIfIdAlreadyExists(id, ID_INDEX);
////        String[] table = {role, id, fName, lName, email, password, group, team, experience, attendance, wallet};
////        loadedTables.add(table);
////        saveData();
    }

}
