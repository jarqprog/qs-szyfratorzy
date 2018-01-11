package users;

import java.util.ArrayList;
import java.util.List;
import item.ArtefactModel;

public class StudentModel extends UserModel
{
    private Character groupName;
    private String team;
    private int wallet;
    private int expirence;
    private List<ArtefactModel> inventory;
    private float attendance;

    public StudentModel(String firstName, String lastName, String password)
    {
        super(firstName, lastName, password);
        setUserRole("student");
        wallet = 0;
        expirence = 0;
        inventory = new ArrayList<ArtefactModel>();
        attendance = 100;

    }

    public StudentModel(int id, String firstName, String lastName, String password, char group)
    {
        super(id, firstName, lastName, password);
        setUserRole("student");
        wallet = 0;
        expirence = 0;
        attendance = 100;
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

    public int getExpirence()
    {
        return expirence;
    }

    public void setExpirence(int value)
    {
        this.expirence = value;
    }

    public float getAttendance()
    {
        return attendance;
    }

    public void setAttendance(float attendance)
    {
        this.attendance = attendance;
    }
}
