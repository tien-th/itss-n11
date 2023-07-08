package entity;

public class Appoint {
    private String state;
    private int pet_id;
    private String day;
    private int time_slot;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
        this.time_slot = Integer.parseInt(time.split(":")[0]);
    }

    private String time; // for display

    public Appoint(int pet_id, String day, int time_slot, String state) {
        this.state = state;
        this.pet_id = pet_id;
        this.day = day;
        this.time_slot = time_slot;
        this.time =  String.valueOf(time_slot) + ":00";
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
        this.time =  String.valueOf(time_slot) + ":00";
    }
    // toString
    @Override
    public String toString() {
        return "Appoint [day=" + day + ", pet_id=" + pet_id + ", state=" + state + ", time_slot=" + time_slot + "]";
    }


}
