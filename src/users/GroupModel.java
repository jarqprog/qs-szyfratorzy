package users;

        import java.util.ArrayList;


public class GroupModel{

    private char name;
    private ArrayList<StudentModel> studentsInGroup;

    private  GroupModel(){
        studentsInGroup = new ArrayList<StudentModel>();
    }

    public char getName(){
        return  name;
    }

    public void setName(char name){
        this.name = name;
    }

    public ArrayList<StudentModel> getStudentsFromGroup(){
        return  studentsInGroup;
    }

    public void addToGroup(StudentModel student){
        studentsInGroup.add(student);
    }
}