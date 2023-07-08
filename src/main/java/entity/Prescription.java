package entity;

public class Prescription {
    private String medicineName ;
    private int id ;
    private int num ;

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Prescription(String medicineName, int id, int num) {
        this.medicineName = medicineName;
        this.id = id;
        this.num = num;
    }




}
