package users;

public interface LogableDAO{

    public void prepareAdmin();
    public void updateLoadedTables();
    public String [] importUserData(String login,String password);
    public AdminModel createAdminModel(String[] table);
    public StudentModel createStudentModel(String[] table);
    public MentorModel createMentorModel(String[] table);

}
