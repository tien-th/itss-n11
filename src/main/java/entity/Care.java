package entity;

import java.util.Date;

public class Care {
    private int pet_id;
    private String services;
    private String day;
    private int time_slot;
//    private boolean state;

    public Care(int pet_id, String services, String day, int time_slot) {
        this.pet_id = pet_id;
        this.services = services;
        this.day = day;
        this.time_slot = time_slot;
//        this.state = state;
    }

    public int getPet_id() {
        return pet_id;
    }

    public void setPet_id(int pet_id) {
        this.pet_id = pet_id;
    }

    public String getServices() {
        return services;
    }

    public void setServices(String services) {
        this.services = services;
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

}
