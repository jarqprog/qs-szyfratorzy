package Interface;

import model.Admin;
import model.Mentor;
import model.Student;

public interface LogableDAO{

    public void prepareAdmin();
    public void updateLoadedTables();
    public String [] importUserData(String login,String password);
    public Admin createAdminModel(String[] table);
    public Student createStudentModel(String[] table);
    public Mentor createMentorModel(String[] table);
}
