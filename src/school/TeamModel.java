package school;

import users.StudentModel;

import java.util.ArrayList;
import java.util.List;
import users.StudentModel;


public class TeamModel {

    private String name;
    private List<StudentModel> students;

    private  TeamModel(){
        students = new ArrayList<StudentModel>();
    }

    public String getName(){
        return  name;
    }

    public void setName(String name){
        this.name = name;
    }

    public List<StudentModel> getStudents(){
        return students;
    }

    public void addStudent(StudentModel student){
        students.add(student);
    }
}
