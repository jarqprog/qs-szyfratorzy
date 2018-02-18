package model;

import dao.StudentDAO;

public class StudentFactoryImpl implements UserFactory {

    public Student create(String firstName, String lastName, String password) {
        Student user = new Student(firstName, lastName, password);
        int id =  new StudentDAO().saveObjectAndGetId(user);
        user.setId(id);
        user.setAttendance(new Attendance(id));
        user.setInventory(new StudentInventory(id));
        user.setStudentsQuests(new StudentsQuests(id));
        return user;
    }
}
