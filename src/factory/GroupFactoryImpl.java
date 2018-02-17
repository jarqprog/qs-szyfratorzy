package factory;

import dao.GroupDAO;
import model.Group;

public class GroupFactoryImpl implements StudentSetFactory {

    public Group create(String name) {
        Group group = new Group(name);
        int id = new GroupDAO().saveObjectAndGetId(group);
        group.setId(id);
        return group;
    }
}
