package enums;

public enum QuestsStatus {

    COMPLETED("Completed"), TO_IMPROVE("To improve"), WAITING_FOR_APPROVAL("Waiting for approval"), AVAILABLE ("Available");

    private String name;

    QuestsStatus(String name) {this.name = name; }

    public String getName(){ return name;}
}
