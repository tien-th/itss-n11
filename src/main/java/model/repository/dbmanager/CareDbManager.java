package model.repository.dbmanager;

import model.entity.Care;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CareDbManager {
    public static ArrayList<Care> getListCareServices() throws SQLException, ClassNotFoundException {
        ArrayList<Care> careList = new ArrayList<>();
        PreparedStatement ps = null;
        String sql = "SELECT * FROM dichvuvs";
        ps = utils.connection.DbConnection.openConnection().prepareStatement(sql);

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int pet_id = rs.getInt("pet_id");
            String name_services = rs.getString("loai_dv");
            String day = String.valueOf(rs.getDate("day")); // name
            int time_slot = rs.getInt("time_slot");
            int price = rs.getInt("giatien");

//            String state = rs.getString("state");
            Care c = new Care(pet_id, name_services, day, time_slot, price);
            System.out.println(c);
            careList.add(c);
        }
        return careList;
    }


    public static void deleteCareServices(int petId, String day, int timeSlot) throws SQLException, ClassNotFoundException {
        String sql = "delete from dichvuvs where pet_id = ? and day = ? and time_slot = ?";
        PreparedStatement ps = utils.connection.DbConnection.openConnection().prepareStatement(sql);
        ps.setInt(1, petId);
        ps.setDate(2, java.sql.Date.valueOf(day));
        ps.setInt(3, timeSlot);
        ps.executeUpdate();
    }
}
