package model.repository.dbmanager;

import model.entity.Appoint;
import model.entity.Pet;
import utils.connection.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class ExamineInforDbManager {


    public static ArrayList<Appoint> getLichKhamToday() throws SQLException, ClassNotFoundException {
        ArrayList<Appoint> appointList = new ArrayList<>();
        String sql = "select * from lichkham where day = current_date";
        Connection con = DbConnection.openConnection();
        PreparedStatement stmt = con.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("pet_id");
            Date day = rs.getDate("day");
            String daystr = String.valueOf(day);
            int time_slot = rs.getInt("time_slot");
            String state = rs.getString("state");
            Appoint a = new Appoint(id, daystr, time_slot, state);
            appointList.add(a);
        }
        return appointList;
    }


    public static Pet getPet(int id) throws SQLException, ClassNotFoundException {
        String sql = "select * from pet where pet_id = " + id;
        Connection con = DbConnection.openConnection();
        PreparedStatement stmt = con.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            String username = rs.getString("username");
            String name = rs.getString("name");
            String color = rs.getString("color");
            String category = rs.getString("category");
            int age = rs.getInt("age");
            String gender = rs.getString("gender");
            Pet pet = new Pet(id,username, name, color, category, age, gender);
            System.out.println(pet);
            return pet;
        }
        return null ;
    }
}
