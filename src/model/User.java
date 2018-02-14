package model;

import tools.DataTool;

public abstract class User {
    protected int id;
    protected String firstName;
    protected String lastName;
    protected String email;
    protected String password;
    protected String role;

    public User(int id, String firstName, String lastName, String email, String password) {
        this(firstName, lastName, password);
        this.id = id;
        this.email = email;
    }

    public User(String firstName, String lastName, String password) {
        this.id = -1;
        this.firstName = firstName;
        this.lastName = lastName;
        email = firstName.toLowerCase() + "." + lastName.toLowerCase() + "@cc.com";
        this.password = password;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
        saveObject();
    }

    public String getLastName()
    {
        return lastName;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
        saveObject();
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        saveObject();
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        saveObject();
    }

    public String getRole()
    {
        return role;
    }

    public void setRole(String role)
    {
        this.role = role;
    }

    public String toString() {
        return String.format("Role: %s, Id: %s, First name: %s, Last name: %s, email: %s",
                            this.role, this.id, this.firstName, this.lastName, this.email);
    }

    public String getFullDataToString() {
        String sign = "-";
        int signMultiplier = 100;
        return String.format(" %s, role: %s, id: %s, email: %s\n %s",
                getFullName(), role, id, getEmail(), DataTool.getMultipliedString(sign, signMultiplier));
    }

    public abstract void saveObject();
}
