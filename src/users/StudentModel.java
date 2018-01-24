package users;

import java.util.ArrayList;
import java.util.List;

import application.Role;
import item.ArtifactModel;

public class StudentModel extends UserModel
{
    private Character groupName;
    private String team;
    private int wallet;
    private int experience;
    private List<ArtifactModel> inventory;
    private float attendance;

    public StudentModel(String firstName, String lastName, String password)
    {
        super(firstName, lastName, password);
        role = Role.STUDENT.getName();
        wallet = 0;
        experience = 0;
        inventory = new ArrayList<ArtifactModel>();
        attendance = 100;
        groupName = '0';
        team = "undefined";

    }

    public StudentModel(int id, String firstName, String lastName, String email, String password, char group) {
        super(id, firstName, lastName, email, password);
        role = Role.STUDENT.getName();
        wallet = 0;
        experience = 0;
        attendance = 100;
        groupName = group;
    }

    public Character getGroup()
    {
        return groupName;
    }

    public void setGroup(Character groupName)
    {
        this.groupName = groupName;
    }

    public String getTeam()
    {
        return team;
    }

    public void setTeam(String team)
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

    public void setExperience(int value)
    {
        this.experience = value;
    }

    public float getAttendance()
    {
        return attendance;
    }

    public void setAttendance(float attendance)
    {
        this.attendance = attendance;
    }
    public String toString()
    {
        return super.toString()+String.format(" Group : %s, Team: %s, Wallet: %dcc, Experience: %d, Attendance: %.2f",getGroup(), getTeam(), getWallet(), getExperience(), getAttendance());
    }
}
