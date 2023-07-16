package model.repository;


import model.entity.Pet;
import model.entity.User;
import utils.connection.DbConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PetDbQuerier {
//    public ArrayList<Pet>  petList = new ArrayList<Pet>();

    public static ArrayList<Pet> getPetList(User user) throws SQLException, ClassNotFoundException {
        ArrayList<Pet> petList = new ArrayList<>();
        PreparedStatement ps = null;
        if (user.getRole() == 1) { // admin
            // get all pets
            String sql = "SELECT * FROM pet";
            ps = DbConnection.openConnection().prepareStatement(sql);

        } else if (user.getRole() == 0) { // customer
            // get pets of this customer
            String sql = "SELECT * FROM pet WHERE username = ?";
            ps = DbConnection.openConnection().prepareStatement(sql);
            ps.setString(1, user.getUsername());
        }

        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            int id = rs.getInt("pet_id"); // id
            String name = rs.getString("name"); // name
            String username = rs.getString("username"); // username
            String category = rs.getString("category"); // category
            int age = rs.getInt("age") ;
            String color = rs.getString("color"); // color
            String gender = rs.getString("gender") ;
            Pet p = new Pet(id, username, name, color, category, age, gender );
            petList.add(p);
        }
        return petList;
    }

    public static boolean updatePet(Pet pet) throws SQLException, ClassNotFoundException {
        String sql = "select * from pet where pet_id = " + pet.getId();
        PreparedStatement ps = DbConnection.openConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        if (rs.next() ) {
            // update pet in database
            sql = "update pet set category = ?, age = ?, color = ?, name= ? where pet_id = ?" ;
            ps = DbConnection.openConnection().prepareStatement(sql);
            ps.setString(1, pet.getCategory());
            ps.setInt(2, pet.getAge());
            ps.setString(3, pet.getColor());
            ps.setString(4, pet.getName());
            ps.setInt(5, pet.getId());
            ps.executeUpdate();
            return true;
        }
        return false ;
    }

    public static void deletePet(Pet selectedPet) {
        String sql = "delete from pet where pet_id = " + selectedPet.getId();
        try {
            PreparedStatement ps = DbConnection.openConnection().prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    public static int addPet(Pet newPet) {
        // query database to get the last pet_id
        String sql = "select max(pet_id) from pet";
        try {
            PreparedStatement ps = DbConnection.openConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int id = rs.getInt(1) + 1;
                sql = "INSERT INTO pet VALUES (" + id + ", '" + newPet.getUsername() + "', '" + newPet.getCategory() + "', " + newPet.getAge() + ", '" + newPet.getGender() + "', '" + newPet.getColor() + "', '" + newPet.getName() + "')";
                ps = DbConnection.openConnection().prepareStatement(sql);
                System.out.println(sql);
                ps.executeUpdate();
                return id;
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return -1 ;
    }
}
