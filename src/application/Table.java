package application;

public enum Table {

    STUDENTS("students"), MENTORS("mentors"), ADMINS("admins"), TRANSACTIONS("transactions"), GROUPS("groups"), TEAMS("teams");

    private String name;

    private Table(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }
}
