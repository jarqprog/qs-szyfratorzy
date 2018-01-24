package users;

import java.util.ArrayList;
import java.util.List;
import application.Role;
import item.ArtifactModel;
import school.GroupModel;
import school.TeamModel;

public class StudentModel extends UserModel {

    private GroupModel group;
    private TeamModel team;
    private int wallet;
    private int experience;
    private List<ArtifactModel> inventory;
    private float attendance; // tu będzie obiekt

    public StudentModel(String firstName, String lastName, String password) {
        super(firstName, lastName, password);
        role = Role.STUDENT.getName();
        wallet = 0;
        experience = 0;
        attendance = 100;
        group = new GroupModel("undefined");
        team = new TeamModel("undefined");
        inventory = new ArrayList<ArtifactModel>();
    }

    public StudentModel(int id, String firstName, String lastName, String email,
                        String password, int wallet, int experience, float attendance,
                        GroupModel group, TeamModel team, ArrayList<ArtifactModel> inventory) {
        super(id, firstName, lastName, email, password);
        role = Role.STUDENT.getName();
        this.wallet = wallet;
        this.experience = experience;
        this.attendance = attendance;
        this.group = group;
        this.team = team;
        this.inventory = inventory;

    }

    public GroupModel getGroup() {
        return group;
    }

    public void setGroup(GroupModel group)
    {
        this.group = group;
    }

    public TeamModel getTeam()
    {
        return team;
    }

    public void setTeam(TeamModel team)
    {
        this.team = team;
    }

    public int getWallet()
    {
        return wallet;
    }

    public void setWallet(int value)
    {
        this.wallet = value;
    }

    public int getExperience()
    {
        return experience;
    }

    public void setExperience(int experience)
    {
        this.experience = experience;
    }

    public float getAttendance()
    {
        return attendance;
    }

    public void setAttendance(float attendance)
    {
        this.attendance = attendance;
    }

//    public String toString() {
//        return super.toString(); + String.format(" Group : %s, Team: %s, Wallet: %dcc, Experience: %d, Attendance: %.2f",getGroup(), getTeam(), getWallet(), getExperience(), getAttendance());
//    }
}
