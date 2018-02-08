
package users;

import application.Role;
import school.ExperienceLevelsController;
import school.GroupModel;
import school.TeamModel;
import school.AttendanceModel;
import shop.InventoryModel;


public class StudentModel extends UserModel {

    private GroupModel group;
    private TeamModel team;
    private int wallet;
    private int experience;
    private InventoryModel inventory;
    private AttendanceModel attendance;
    private String experienceLevel;

    public StudentModel(String firstName, String lastName, String password) {
        super(firstName, lastName, password);
        wallet = 0;
        experience = 0;
        team = new TeamModel(1, "undefined");
        group = new GroupModel(1,"undefined");
        role = Role.STUDENT.getName();
        id = saveNewObjectGetId();
        attendance = new AttendanceModel(id);
        inventory = new InventoryModel(id);

    }

    public StudentModel(int id, String firstName, String lastName, String email,
                        String password, int wallet, int experience,
                        TeamModel team, GroupModel group, InventoryModel inventory) {

        super(id, firstName, lastName, email, password);
        this.wallet = wallet;
        this.experience = experience;
        this.attendance = new AttendanceModel(id);
        this.team = team;
        this.group = group;
        this.inventory = inventory;
        role = Role.STUDENT.getName();
    }

    public GroupModel getGroup() {
        setGroup();
        return group;
    }

    public void setGroup(GroupModel group) {
        this.group = group;
        saveObject();
    }

    public void setGroup() {
        this.group.setStudents();
    }

    public TeamModel getTeam() {
        setTeam();
        return team;
    }

    public void setTeam(TeamModel team) {
        this.team = team;
        saveObject();
    }

    public void setTeam(){
        this.team.setStudents();
    }

    public int getWallet(){
        return wallet;
    }

    public void setWallet(int value) {
        this.wallet = value;
        saveObject();
    }

    public void modifyWallet(int value){
        this.wallet += value;
        saveObject();
    }

    public int getExperience(){
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
        saveObject();
    }

    public void incrementExperience(int pointsToAdd) {
        this.experience += pointsToAdd;
        saveObject();
    }

    public void setExperienceLevel(String level, int experienceToNextLevel){
        experienceLevel = String.format("%s (%s/%s)", level, experience, experienceToNextLevel);
    }

    public String getExperienceLevel(){
        new ExperienceLevelsController().setStudentExperienceLevel(this);
        return experienceLevel;
    }

    public AttendanceModel getAttendance(){
        return attendance;
    }

    public void checkAttendance(Boolean isPresent){
        attendance.addAttendance(isPresent);
    }

    public InventoryModel getInventory() { return inventory; }

    public void setInventory(InventoryModel inventory) { this.inventory = inventory; }

    public String getFullDataToString() {
        return super.getFullDataToString() + String.format(
                " \n\n\t -group: %s\n\t -team: %s\n\t -wallet: %dcc\n\t" +
                " -level: %s\n\t -%s\n", getGroup(), getTeam(),
                wallet, getExperienceLevel(), attendance);
    }

    public void saveObject(){
        StudentDAO dao = new StudentDAO();
        dao.saveObject(this);
    }

    public int saveNewObjectGetId(){
        StudentDAO dao = new StudentDAO();
        return dao.saveObjectAndGetId(this);
    }
}

