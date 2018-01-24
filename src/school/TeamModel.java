package school;


import java.util.ArrayList;
import java.util.List;
import users.StudentModel;


public class TeamModel {

    private String name;
    private List<StudentModel> students;
    private int id;

    public TeamModel(String name) {

        this.name = name;
        this.students = new ArrayList<StudentModel>();
    }

    public TeamModel(int id, String name, List<StudentModel> students) {

        this.id = id;
        this.name = name;
        this.students = students;
    }

    public int getId() {
        return id;
    }

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

    public void removeStudent(StudentModel student){
        students.remove(student);
    }
}
