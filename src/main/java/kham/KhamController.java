package kham;

import connection.DbConnection;
import entity.Appoint;
import entity.Medicine;
import entity.Pet;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class KhamController {

    public static ArrayList<Appoint> appointList = new ArrayList<Appoint>();
    public static void getLichKham() throws SQLException, ClassNotFoundException {
        // connect to database and get lich kham that day is today in postgresql
        // and add to appointList
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
        return ;
    }

    public static Pet pet ;

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
            pet = new Pet(id,username, name, color, category, age, gender);
            System.out.println(pet);
            return pet;
        }
        System.out.println("query fail");
        return null ;
    }

    public static ArrayList <Medicine> medicineList = new ArrayList<>();
    public static void getMedicines() {
        // get all medicines from database and add to medicineList
        String sql = "select * from thuoc";
        try {
            Connection con = DbConnection.openConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("thuoc_id");
                String name = rs.getString("ten_thuoc");
                String type = rs.getString("nhom_thuoc");
                int num = rs.getInt("soluong");
                String producer = rs.getString("nhasx");
                String date = String.valueOf(rs.getDate("hsd"));
                Medicine m = new Medicine(id, name, type, producer, date);
                medicineList.add(m);
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    public static boolean prescript(int medicineId, int num) {
        // query database check num of medicineId is enough
        // if enough, update num of medicineId in database
        // else return error
        String sql = "select * from thuoc where thuoc_id = " + medicineId;
        try {
            Connection con = DbConnection.openConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int numInDb = rs.getInt("soluong");
                if (numInDb >= num) {
                    sql = "update thuoc set soluong = " + (numInDb - num) + " where thuoc_id = " + medicineId;
                    stmt = con.prepareStatement(sql);
                    stmt.executeUpdate();
                    return  true;
                } else {
                    System.out.println("Not enough medicine");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Only have " + numInDb + ".Not enough medicine");
                    alert.setContentText("Please choose another medicine");
                    alert.showAndWait();
                    return false;
                }
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public static int getRecordId() {
        String sql = "select max(record_id) from benhan";
        try {
            Connection con = DbConnection.openConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("max") + 1 ;
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return -1;
    }

    public static void savePrescription(int recordId, int medicineId, int quantityInt) {
        String sql = "insert into donthuoc values (" + recordId + ", " + medicineId + ", " + quantityInt + ")";
        try {
            Connection con = DbConnection.openConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.executeUpdate();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void deletePrescription(int recordId, int medicineId, int quantity) {
        String sql = "delete from donthuoc where record_id = " + recordId + " and thuoc_id = " + medicineId + " and soluong = " + quantity;
        try {
            Connection con = DbConnection.openConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.executeUpdate();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        sql = "update thuoc set soluong = soluong + " + quantity + " where thuoc_id = " + medicineId;
        try {
            Connection con = DbConnection.openConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.executeUpdate();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void saveRecord(int recordId, int id, String s, String s1, String s2) throws SQLException, ClassNotFoundException {
        String sql ;
        sql = "select * from benhan where record_id = " + recordId;
        Connection con = DbConnection.openConnection();
        PreparedStatement stmt = con.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        if ( !rs.next()) {
            sql = "insert into benhan values (" + recordId + ", " + id + ", '" + s1 + "', '" + s2 + "')";
            Connection connection = DbConnection.openConnection();
            try {
                stmt = connection.prepareStatement(sql);
                stmt.executeUpdate();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        else {
            sql = "update benhan set chuandoan = '" + s1 + "', trieuchung = '" + s2 + "' where record_id = " + recordId;
            Connection connection = DbConnection.openConnection();
            try {
                stmt = connection.prepareStatement(sql);
                stmt.executeUpdate();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public void setAppoint(int selectedAppointId, String day, int time) {
    }
}
