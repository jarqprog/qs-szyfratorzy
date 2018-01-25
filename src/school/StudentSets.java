package school;

import users.StudentModel;

import java.util.ArrayList;
import java.util.List;

public abstract class StudentSets {

    protected String name;
    protected List<StudentModel> students;
    protected int id;

    public StudentSets(int id, String name, List<StudentModel> students) {
        this(name);
        this.id = id;
        this.students = students;
    }

    public StudentSets(String name) {

        this.name = name;
        this.students = new ArrayList<StudentModel>();
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
