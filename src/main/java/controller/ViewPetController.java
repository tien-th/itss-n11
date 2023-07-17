package controller;

import model.entity.Pet;
import model.entity.User;
import model.repository.PetDbQuerier;

import java.sql.SQLException;
import java.util.ArrayList;

public class ViewPetController {
    private ArrayList<Pet> petArrayList;
    private User user;

    public ArrayList<Pet> getPetList() throws SQLException, ClassNotFoundException {
        if (user == null) {
            return null ;
        }
        if (petArrayList == null) {
           this.petArrayList = PetDbQuerier.getPetList(user) ;
           return petArrayList;
        }
        return petArrayList;
    }

    public String deletePet(Pet pet) throws SQLException, ClassNotFoundException {
        if (user.getRole() == 1 ) {
            return "You do not have permission to delete this pet" ;
        }
        PetDbQuerier.deletePet(pet);
        petArrayList.remove(pet);
        return "Delete pet successfully" ;
    }

    public void setPetArrayList(ArrayList<Pet> petArrayList) {
        this.petArrayList = petArrayList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ArrayList<Pet> searchPet(String searchQuery) {
        ArrayList<Pet> petList = new ArrayList<>();
        for (Pet pet : petArrayList) {
            if (pet.getName().contains(searchQuery)) {
                petList.add(pet);
            }
        }
        return petList;
    }

    private Pet SearchPetById(int id) {
        for (Pet pet : petArrayList) {
            if (pet.getId() == id) {
                return pet;
            }
        }
        return null;
    }

    public String addPet(Pet newPet) {
        if (user.getRole() == 1) {
            return "You do not have permission to add pet" ;
        }

        int id = PetDbQuerier.addPet(newPet);

        if (id == -1) {
            return "Pet registration failed" ;
        } else {
            newPet.setId(id);
            petArrayList.add(newPet);
            return "Successfully registered pet " + newPet.getName() + " with id: " +  id;
        }
    }

    public String update(Pet selectedPet) {
        if(user.getRole() == 1) {
            return "You do not have permission to update this pet" ;
        }

        try {
            boolean check = PetDbQuerier.updatePet(selectedPet);
            if (check){
                Pet pet = SearchPetById(selectedPet.getId());
                pet.setAge(selectedPet.getAge());
                pet.setCategory(selectedPet.getCategory());
                pet.setColor(selectedPet.getColor());
                pet.setName(selectedPet.getName());
                return "Update was successful" ;
            }
            else {
                return "Update failed. Please try again" ;
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
