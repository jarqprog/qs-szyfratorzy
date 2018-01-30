package school;

import users.StudentDAO;
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
        this.id = -1;
        this.name = name;
        this.students = new ArrayList<>();
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
        setStudents();
        return students;
    }

    public void setStudents(List<StudentModel> students) {
        this.students = students;
    }

    public abstract void setStudents();

    public void addStudent(StudentModel student){
        students.add(student);
    }

    public void removeStudent(StudentModel student){
        students.remove(student);
    }

    public String toString(){
        return String.format("Id: %s, name: %s, number of students: %s",
                this.id, this.name, students.size());
    }

}
