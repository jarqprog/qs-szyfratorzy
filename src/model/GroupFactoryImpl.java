package model;

import dao.DaoFactory;
import dao.GroupDAO;

public class GroupFactoryImpl implements StudentSetFactory {

    public Group create(String name) {
        Group group = new Group(name);
        int id = DaoFactory.getByType(GroupDAO.class).saveObjectAndGetId(group);
        group.setId(id);
        return group;
    }
}
