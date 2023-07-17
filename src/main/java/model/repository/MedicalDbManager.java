package model.repository;

import model.entity.Medical;
import utils.connection.DbConnection;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MedicalDbManager {
    public static ArrayList<Medical> getMedicalList() throws SQLException, ClassNotFoundException {
        ArrayList<Medical> medicalList = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        String sql = "SELECT * FROM thuoc" ;
        preparedStatement = DbConnection.openConnection().prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int id = resultSet.getInt("thuoc_id");
            String ten_thuoc = resultSet.getString("ten_thuoc");
            String nhom_thuoc = resultSet.getString("nhom_thuoc");
            int soluong = resultSet.getInt("soluong");
            String nhasx = resultSet.getString("nhasx");
            Date hsd = resultSet.getDate("hsd");
            int price = resultSet.getInt("price");

            Medical a = new Medical(id, ten_thuoc, nhom_thuoc, soluong, nhasx, hsd, price);
            medicalList.add(a);
        }
        return medicalList;
    }

    public static boolean updateMedical(Medical medical) throws SQLException, ClassNotFoundException {
        String sql = "select * from thuoc where thuoc_id = " + medical.getThuocId();
        System.out.println(sql);
        PreparedStatement ps = DbConnection.openConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();


        if (rs.next() ) {
            // update pet in database
            sql = "update thuoc set ten_thuoc = ?, nhom_thuoc = ?, soluong = ?, nhasx = ?, hsd = ?, price = ? where thuoc_id = ?" ;

            System.out.println("---------------- " + medical.getSoLuong());
            ps = DbConnection.openConnection().prepareStatement(sql);
            ps.setString(1, medical.getTenThuoc());
            ps.setString(2, medical.getNhomThuoc());
            ps.setInt(3, medical.getSoLuong());
            ps.setString(4, medical.getNhaSx());
            ps.setDate(5, medical.getHsd());
            ps.setInt(7, medical.getThuocId());
            ps.setInt(6, medical.getPrice());
            ps.executeUpdate();
            return true;
        }
        return false;
    }

    public static int addMedical(String tenThuoc, String nhomThuoc, int soLuong, String nhaSx, String hsd, int gia) {
        String sql  = "select max(thuoc_id) from thuoc";
        try{
            PreparedStatement ps = DbConnection.openConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                int id = rs.getInt(1);
                sql = "insert into thuoc values ("+ (id+1) + ",'" + tenThuoc + "','" + nhomThuoc + "'," + soLuong + ",'" + nhaSx + "','" + hsd +"'," + gia + ")";
                ps = DbConnection.openConnection().prepareStatement(sql);
                ps.executeUpdate();
                return id+1;
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        return -1 ;
    }
}