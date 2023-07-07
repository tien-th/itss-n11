package quanly;

import connection.DbConnection;
import entity.User;


import entity.Appoint;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReferenceArray;

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


}
