package application;

public enum Role {

    STUDENT("student"), MENTOR("mentor"), ADMIN("admin");

    private String name;

    private Role(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }
}
