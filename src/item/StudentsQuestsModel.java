package item;

import java.time.LocalDate;

import application.StudentStockModel;




public class StudentsQuestsModel extends StudentStockModel {


    public StudentsQuestsModel(int id) {
        super(id);
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId){
        /*
        -- important:
        Only use in StudentModel constructor while creating brand new student (short constructor)
        */
        this.studentId = studentId;
//        saveObject();
    }




    public void setStock() {

    }


    public void addItem(QuestModel quest) {
      LocalDate date = LocalDate.now();
        super.getMap().put(quest, date);

    }
}

