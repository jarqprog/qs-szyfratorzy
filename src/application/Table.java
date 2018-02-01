package application;

public enum Table {

    STUDENTS("students"), MENTORS("mentors"), ADMINS("admins"),
    TRANSACTIONS("transactions"), GROUPS("groups"),
    TEAMS("teams"), ARTIFACTS("artifacts"), QUESTS("quests"), STUDENTS_ARTIFACTS("students_artifacts");

    private String name;

    private Table(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }
}
