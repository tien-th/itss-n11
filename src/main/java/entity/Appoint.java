package entity;

public class Appoint {
       // tạo cho tôi các thuộc tính sau: state(String), pet_id(int), day(date), time_slot(int)
    private String state;
    private int pet_id;
    private String day;
    private int time_slot;
    // contructor
    public Appoint(String state, int pet_id, String day, int time_slot) {
        this.state = state;
        this.pet_id = pet_id;
        this.day = day;
        this.time_slot = time_slot;
    }
    public Appoint(String state, int pet_id, String day){
        this.state = state;
        this.pet_id = pet_id;
        this.day = day;
    }
    public Appoint(int id, String day, int time, String state) {
        this.state = state;
        this.pet_id = pet_id;
        this.day = day;
        this.time_slot = time_slot;
    }
    public String getState() {
        return state;
    }
    //continue
    public void setState(String state) {
        this.state = state;
    }
    public int getPet_id() {
        return pet_id;
    }
    public void setPet_id(int pet_id) {
        this.pet_id = pet_id;
    }
    public String getDay() {
        return day;
    }
    public void setDay(String day) {
        this.day = day;
    }
    public int getTime_slot() {
        return time_slot;
    }
    public void setTime_slot(int time_slot) {
        this.time_slot = time_slot;
    }
    // toString
    @Override
    public String toString() {
        return "Appoint [day=" + day + ", pet_id=" + pet_id + ", state=" + state + ", time_slot=" + time_slot + "]";
    }


}
