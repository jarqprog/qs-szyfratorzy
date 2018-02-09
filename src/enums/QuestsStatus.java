package enums;

public enum QuestsStatus {

    COMPLITED("Complited"), WAITING_FOR_APPROVAL("Waiting for approval"), AVAILABLE ("Available");

    private String name;

    QuestsStatus(String name) {this.name = name; }

    public String getName(){ return name;}
}
