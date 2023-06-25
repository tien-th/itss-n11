package dangkydichvu;

import connection.DbConnection;
import entity.Pet;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DangKyDvController {
    public boolean dangKyVs(Pet pet, String day, int startHour, String dichVu) throws SQLException, ClassNotFoundException {
        // Connect to the database
        try (Connection connection = DbConnection.openConnection()) {
            // Check if there is an available lodging for the pet
            String checkAvailabilityQuery = "select * from dichvuvs where loai_dv = '" + dichVu + "' and day = '" + day + "' and time_slot = " + startHour;
            PreparedStatement checkAvailabilityStatement = connection.prepareStatement(checkAvailabilityQuery);
            ResultSet checkAvailabilityResult = checkAvailabilityStatement.executeQuery();
            if (checkAvailabilityResult.next()) {
                System.out.println("Time slot is not available");
                return false;
            } else {
                String state = "ok" ;
                String sql = "insert into dichvuvs (loai_dv ,state ,pet_id, day, time_slot) values ('"+ dichVu +"', '" + state + "', " + pet.getId() + ", '" + day + "', " + startHour + ")";
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.executeUpdate();
                System.out.println("Time slot is available");
                return true;
            }
        }
    }

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

    public int dangKyTrongGiu(Pet pet, String day, int startHour) throws SQLException, ClassNotFoundException {
        // Connect to the database
        try (Connection connection = DbConnection.openConnection()) {
            // Check if there is an available lodging for the pet
            String checkAvailabilityQuery = "SELECT lodging_id FROM lodging WHERE status = 0";
            try (PreparedStatement checkAvailabilityStatement = connection.prepareStatement(checkAvailabilityQuery)) {
                ResultSet resultSet = checkAvailabilityStatement.executeQuery();
                if (resultSet.next()) {
                    int lodgingId = resultSet.getInt("lodging_id");
                    // Update the status of the lodging to 1
                    String updateStatusQuery = "UPDATE lodging SET status = 1 WHERE lodging_id = ?";
                    try (PreparedStatement updateStatusStatement = connection.prepareStatement(updateStatusQuery)) {
                        updateStatusStatement.setInt(1, lodgingId);
                        updateStatusStatement.executeUpdate();
                    }

                    // Insert into the database
                    // Convert date and start hour to timestamp
                    String combinedDateTimeString = day + " " + startHour + ":00:00";
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    LocalDateTime dateTime = LocalDateTime.parse(combinedDateTimeString, formatter);
                    Timestamp timestamp = Timestamp.valueOf(dateTime);

                    String insertLodgingPetQuery = "INSERT INTO set_lodging (lodging_id, pet_id, start_time) VALUES (?, ?, ?)";
                    try (PreparedStatement insertLodgingPetStatement = connection.prepareStatement(insertLodgingPetQuery)) {
                        insertLodgingPetStatement.setInt(1, lodgingId);
                        insertLodgingPetStatement.setInt(2, pet.getId());
                        insertLodgingPetStatement.setTimestamp(3, timestamp);
                        insertLodgingPetStatement.executeUpdate();
                    }

                    System.out.println("Lodging is available");
                    return lodgingId;
                } else {
                    System.out.println("Lodging is not available");
                    return 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }
}
