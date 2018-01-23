package users;

import java.util.ArrayList;

public class TeamModel {

    private String name;
    private ArrayList<StudentModel> studentsInTeam;

    private  TeamModel(){
        studentsInTeam = new ArrayList<StudentModel>();
    }

    public String getName(){
        return  name;
    }

    public void setName(String name){
        this.name = name;
    }

    public ArrayList<StudentModel> getStudentsFromTeam(){
        return  studentsInTeam;
    }

    public void addStudentToTeam(StudentModel student){
        studentsInTeam.add(student);
    }
}
