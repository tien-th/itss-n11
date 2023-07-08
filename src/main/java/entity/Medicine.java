package entity;

public class Medicine {
    // int id , String medicineName, String medicineType, String provider, String date
    private int id;
    private String medicineName;
    private String medicineType;
    private String provider;
    private String date;

    public Medicine(int id, String medicineName, String medicineType, String provider, String date) {
        this.id = id;
        this.medicineName = medicineName;
        this.medicineType = medicineType;
        this.provider = provider;
        this.date = date;
    }
    public Medicine() {
    }

    public int getId() {
        return id;
    }
    public String getMedicineName() {
        return medicineName;
    }
    public String getMedicineType() {
        return medicineType;
    }
    public String getProvider() {
        return provider;
    }
    public String getDate() {
        return date;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }
    public void setMedicineType(String medicineType) {
        this.medicineType = medicineType;
    }
    public void setProvider(String provider) {
        this.provider = provider;
    }
    public void setDate(String date) {
        this.date = date;
    }
    @Override
    public String toString() {
        return "Medicine [date=" + date + ", id=" + id + ", medicineName=" + medicineName + ", medicineType="
                + medicineType + ", provider=" + provider + "]";
    }
}
