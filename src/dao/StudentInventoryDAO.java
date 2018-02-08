package dao;

import application.Table;

public class StudentInventoryDAO extends InventoryDAO {

    public StudentInventoryDAO(){
        this.DEFAULT_TABLE = Table.STUDENTS_ARTIFACTS.getName();
    }
}
