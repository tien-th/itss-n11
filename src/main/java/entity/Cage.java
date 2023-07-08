package entity;


public class Cage {
    private int id_cage;
    private int status;   // true if cage is empty, false if cage is full
    private Pet pet;

    // constructor
    public Cage(int id_cage, int status, Pet pet) {
        this.id_cage = id_cage;
        this.status = status;
        this.pet = null;
    }

//    public Cage(int id, int petId, int status) {
//        this.id_cage = id;
//        this.status = status;
//        this.pet = null;    // pet is null when cage is empty
//    }

    public Cage(int id, int status) {
        this.id_cage = id;
        this.status = status;
    }

    public Cage() {

    }

    //getter and setter
    public int getId_cage() {
        return id_cage;
    }

    public void setId_cage(int id_cage) {
        this.id_cage = id_cage;
    }

    public int isStatus() {
        return status;
    }

    public void setStatus(int status) {
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

