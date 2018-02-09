package Interface;

import Model.Admin;
import Model.Mentor;
import Model.Student;

public interface LogableDAO{

    public void prepareAdmin();
    public void updateLoadedTables();
    public String [] importUserData(String login,String password);
    public Admin createAdminModel(String[] table);
    public Student createStudentModel(String[] table);
    public Mentor createMentorModel(String[] table);
}
