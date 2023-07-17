package controller;

import model.repository.RegisterServiceSaver;

import java.sql.SQLException;

public class RegisterController {
    public static String dangKyKham(int id, String ngayKham, int startHour) throws SQLException, ClassNotFoundException {
        RegisterServiceSaver dkyKham = new RegisterServiceSaver();
        if (dkyKham.dangKyKham(id, ngayKham, startHour)) {
            return "Successful registration";
        }
        else {
            return "Registration failed - Time has been booked";
        }
    }

    public static String dangKyTrongGiu(int id, String ngayKham, int startHour) throws SQLException, ClassNotFoundException {
        RegisterServiceSaver dkyDV = new RegisterServiceSaver();
        int lod_id = dkyDV.dangKyTrongGiu(id, ngayKham, startHour);
        if ( lod_id != 0) {
            return "Registration for cage service number " +  lod_id + " was successful at " + startHour + ":00 on "  + ngayKham;
        }
        else {
            return "Service registration failed - This time period is full";
        }
    }


    public static String dangKyVs(int id, String ngayKham, int startHour, String dichVu) throws SQLException, ClassNotFoundException {
        RegisterServiceSaver dkyDV = new RegisterServiceSaver();
        boolean check = dkyDV.dangKyVs(id, ngayKham, startHour, dichVu);
        if (check) {
            return "Register for " + dichVu + " service successfully at " + startHour + ":00 on "  + ngayKham;
        }
        else {
            return "Service registration failed - This time period is full";
        }
    }
}
