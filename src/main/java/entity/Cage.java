package entity;
import entity.Pet;

import java.util.List;

public class Cage {
    private int id_cage;
    private boolean status;   // true if cage is empty, false if cage is full
    private Pet pet;

    // constructor
    public Cage(int id_cage, boolean status) {
        this.id_cage = id_cage;
        this.status = status;
        this.pet = null;
    }
    //getter and setter
    public int getId_cage() {
        return id_cage;
    }

    public void setId_cage(int id_cage) {
        this.id_cage = id_cage;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public  Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    // toString
    @Override
    public String toString() {
        return "Cage{" + "id_cage=" + id_cage + ", status=" + status + ", pet=" + pet + '}';
    }
}

