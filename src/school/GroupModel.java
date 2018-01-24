package school;

import java.util.ArrayList;
import java.util.List;
import users.StudentModel;


public class GroupModel {

    private String name;
    private List<StudentModel> students;
    private int id;

    public GroupModel(String name) {

        this.name = name;
        this.students = new ArrayList<StudentModel>();
    }

    public GroupModel(int id, String name, List<StudentModel> students) {

        this.id = id;
        this.name = name;
        this.students = students;
    }

    public int getId() {
        return id;
    }

    public String getName(){
        return  name;
    }

    public void setName(String name){
        this.name = name;
    }

    public List<StudentModel> getStudents(){
        return  students;
    }

    public void addStudent(StudentModel student){
        students.add(student);
    }

    public void removeStudent(StudentModel student){
        students.remove(student);
    }
}