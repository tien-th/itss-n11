package dangkydichvu;

import connection.DbConnection;
import entity.Pet;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DangKyDvController {

    public boolean dangKyKham(Pet pet, String ngayKham, int startHour) throws SQLException, ClassNotFoundException {
        // connect to database, check if there is a time slot available for the pet
        // if yes, insert into database
        // if no, show alert
        String sql = "select * from lichkham where day = '" + ngayKham + "' and time_slot = " + startHour;
        PreparedStatement ps = DbConnection.openConnection().prepareStatement(sql);
        if (ps.executeQuery().next()) {
            System.out.println("Time slot is not available");
            return false;
        } else {
            String state = "ok" ;
//            sql = "insert into lichkham (state ,idpet, ngaykham, starthour) values ('" + state + "' ," + pet.getId() + ", '" + ngayKham + "', " + startHour + ")";
            sql = "insert into lichkham (state ,pet_id, day, time_slot) values ('" + state + "' ," + pet.getId() + ", '" + ngayKham + "', " + startHour + ")";
            ps = DbConnection.openConnection().prepareStatement(sql);
            ps.executeUpdate();
            System.out.println("Time slot is available");
            return true;
        }
    }
}
