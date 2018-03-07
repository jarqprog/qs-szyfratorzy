package enums;

public enum Role {

    STUDENT("student"), MENTOR("mentor"), ADMIN("admin");

    private String name;

    Role(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }
}
