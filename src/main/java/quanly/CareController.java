package quanly;

import  entity.Care;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CareController {
    public ArrayList<Care> careList = new ArrayList<Care>();
    public void getListCareServices() throws SQLException, ClassNotFoundException {
        PreparedStatement ps = null;
        String sql = "SELECT * FROM dichvuvs";
        ps = connection.DbConnection.openConnection().prepareStatement(sql);

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int pet_id = rs.getInt("pet_id");
            String name_services = rs.getString("loai_dv");
            String day = String.valueOf(rs.getDate("day")); // name
            int time_slot = rs.getInt("time_slot");
//            String state = rs.getString("state");
            Care c = new Care(pet_id, name_services, day, time_slot);
            careList.add(c);
        }
    }

    public static void deleteCareServices(int pet_id, String day, int time_slot) throws SQLException, ClassNotFoundException {
        String sql = "delete from dichvuvs where pet_id = ? and day = ? and time_slot = ?";
        PreparedStatement ps = connection.DbConnection.openConnection().prepareStatement(sql);
        ps.setInt(1, pet_id);
        ps.setDate(2, java.sql.Date.valueOf(day));
        ps.setInt(3, time_slot);
        ps.executeUpdate();
    }
}

