package model;

public class StudentFactoryImpl implements UserFactory {

    public Student create(String firstName, String lastName, String password) {
        Student user = new Student(firstName, lastName, password);
        int id = ModelDaoFactory.getByType(StudentDAO.class).saveObjectAndGetId(user);
        user.setId(id);
        user.setAttendance(new Attendance(id));
        user.setInventory(new StudentInventory(id));
        user.setStudentsQuests(new StudentsQuests(id));
        return user;
    }
}
