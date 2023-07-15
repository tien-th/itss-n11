package repository.quanly;

import entity.Medical;
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


    public static int addMedical(String tenThuoc, String nhomThuoc, int soLuong, String nhaSx, Date hsd, int gia) {
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
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return -1 ;
    }
}
