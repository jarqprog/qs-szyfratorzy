package model;

public class GroupFactoryImpl implements StudentSetFactory {

    public Group create(String name) {
        Group group = new Group(name);
        int id = ModelDaoFactory.getByType(GroupDAO.class).saveObjectAndGetId(group);
        group.setId(id);
        return group;
    }

    public Group getDefault() {
        return new Group(1, "undefined");
    }
}
