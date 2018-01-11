package users;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

public class StudentDAO extends UsersDAO
{

    public List<StudentModel> getTestStudents(){

        List<StudentModel> studentsCollection = new ArrayList<StudentModel>();
        studentsCollection.add(new StudentModel("Jan", "Nowak", "123"));
        studentsCollection.add(new StudentModel("Mirek", "Banan", "123"));
        studentsCollection.add(new StudentModel("XXX", "Jablko", "123"));
        studentsCollection.add(new StudentModel("Bio", "Rolka", "123"));
        studentsCollection.add(new StudentModel("Laska", "Laska", "123"));

        return studentsCollection;
    }
}
