package enums;

public enum Table {

    STUDENTS("students"), MENTORS("mentors"), ADMINS("admins"),
    TRANSACTIONS("transactions"), GROUPS("groups"),
    TEAMS("teams"), ARTIFACTS("artifacts"), QUESTS("quests"), STUDENTS_ARTIFACTS("students_artifacts"),

    EXPERIENCE_LEVELS("experience_levels"), ATTENDANCE("attendance"), STUDENTS_QUESTS("students_quests"), TEAMS_ARTIFACTS("teams_artifacts");

    private String name;

    Table(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }
}
