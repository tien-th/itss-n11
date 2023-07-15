package repository.quanly;

import utils.connection.DbConnection;


import entity.Appoint;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AppointController {
    public ArrayList<Appoint> appointList = new ArrayList<Appoint>();

    public void getAppointList() throws SQLException, ClassNotFoundException {
        PreparedStatement ps = null;
        String sql = "SELECT * FROM lichkham";
        ps = DbConnection.openConnection().prepareStatement(sql);

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("pet_id");
            String day = String.valueOf(rs.getDate("day"));
            int time = rs.getInt("time_slot");
            String state = rs.getString("state");
            Appoint a = new Appoint(id, day, time, state);
            appointList.add(a);
        }
    }

    public static void deleteAppoint(int pet_id, String day, int time_slot) throws SQLException, ClassNotFoundException {
        String sql = "delete from lichkham where pet_id = ? and day = ? and time_slot = ?";
        PreparedStatement ps = DbConnection.openConnection().prepareStatement(sql);
        ps.setInt(1, pet_id);
        ps.setDate(2, java.sql.Date.valueOf(day));
        ps.setInt(3, time_slot);
        ps.executeUpdate();
    }

    public static int updateAppoint(Appoint updatedAppoint, Appoint oldAppoint) {
//        if day and time_slot are not changed return (do nothing)
        if (updatedAppoint.getDay().equals(oldAppoint.getDay()) && updatedAppoint.getTime_slot() == oldAppoint.getTime_slot()) {
            return 0 ;
        }
//        if day and time_slot of updatedAppoint is already in database alert user and return
        try {
            String sql = "select * from lichkham where day = ? and time_slot = ?";
            PreparedStatement ps = DbConnection.openConnection().prepareStatement(sql);
            ps.setDate(1, java.sql.Date.valueOf(updatedAppoint.getDay()));
            ps.setInt(2, updatedAppoint.getTime_slot());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return -1 ;
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        try {
            String sql = "update lichkham set pet_id = ?, day = ?, time_slot = ?, state = ? where pet_id = ? and day = ? and time_slot = ?";
            PreparedStatement ps = DbConnection.openConnection().prepareStatement(sql);
            ps.setInt(1, updatedAppoint.getPet_id());
            ps.setDate(2, java.sql.Date.valueOf(updatedAppoint.getDay()));
            ps.setInt(3, updatedAppoint.getTime_slot());
            ps.setString(4, updatedAppoint.getState());
            ps.setInt(5, oldAppoint.getPet_id());
            ps.setDate(6, java.sql.Date.valueOf(oldAppoint.getDay()));
            ps.setInt(7, oldAppoint.getTime_slot());
            ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        return 1 ;
    }
    // refactor code above


}
