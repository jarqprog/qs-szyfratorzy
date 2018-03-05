package dao;

import enums.Table;
import model.StudentInventory;

import java.sql.Connection;

public class StudentInventoryDAO extends InventoryDAO<StudentInventory> {

    StudentInventoryDAO(Connection connection) {
        super(connection);
    }

    protected void setDefaultTable(){
        this.DEFAULT_TABLE = Table.STUDENTS_ARTIFACTS.getName();
    }
}
