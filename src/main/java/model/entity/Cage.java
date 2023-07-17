package model.entity;


public class Cage {

    private int id_cage;
    private int status;
    private int pet_id;


    public Cage(int id, int status, int pet_id) {
        this.id_cage = id;
        this.status = status;
        this.pet_id =pet_id;
    }

    public Cage(int id, int status) {
        this.id_cage = id;
        this.status = status;
    }


//    public Cage(int id, int status) {
//        this.id_cage = id;
//        this.status = status;
//    }


// getter and setter
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

    public int getPet_id() {
        return pet_id;
    }

    public void setPet_id(int pet_id) {
        this.pet_id = pet_id;
    }

    public int getStatus() {
        return status;
    }
}

