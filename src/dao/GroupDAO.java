package dao;

import model.Group;
import enums.Table;
import view.UsersView;

public class GroupDAO extends ActiveObjDAOImpl<Group> {

    public GroupDAO(){
        this.DEFAULT_TABLE = Table.GROUPS.getName();
    }

    public Group getOneObject(String [] record){

        final Integer ID_INDEX = 0;
        final Integer NAME_INDEX = 1;

        int id = Integer.parseInt(record[ID_INDEX]);
        String name = record[NAME_INDEX];
        return new Group(id, name);
    }

    public void save(Group group){
        String group_id = String.valueOf(group.getId());
        String name = group.getName();
        String query;

        if(group_id.equals("-1")){
            query = String.format("INSERT INTO %s VALUES(null, '%s');", DEFAULT_TABLE, name);
        } else{
            query = String.format("UPDATE %s SET name='%s' " +
                    "WHERE id=%s;", DEFAULT_TABLE, name, group_id);
        }


        UsersView view = new UsersView();
        view.displayMessage("Jestem w group dao, próbuję zapisać: " + getClass().getSimpleName());
        view.handlePause();



        dao = new DbManagerDAO();
        dao.inputData(query);
    }
}
