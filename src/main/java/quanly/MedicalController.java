package quanly;

import connection.DbConnection;
import entity.Medical;

import java.sql.*;
import java.util.ArrayList;

public class MedicalController {
    private Connection connection;
    public MedicalController() {
        this.connection = connection;
    }

    public ArrayList<Medical> medicalList = new ArrayList<Medical>();
     public void getMedicalList() throws SQLException, ClassNotFoundException {
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
                Medical a = new Medical(id, ten_thuoc, nhom_thuoc, soluong, nhasx, hsd);
                medicalList.add(a);
            }
     }



    public int add(Medical Medical) throws SQLException {
        String sql  = "select max(thuoc_id) from thuoc";
        try{
            PreparedStatement ps = DbConnection.openConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                int id = rs.getInt(1);
                sql = "insert into thuoc values ("+ (id+1) + ",'" + Medical.getTenThuoc() + "','" + Medical.getNhomThuoc() + "'," + Medical.getSoLuong() + ",'" + Medical.getNhaSx() + "','" + Medical.getHsd() +"')";
                ps = DbConnection.openConnection().prepareStatement(sql);
                ps.executeUpdate();
                return id+1;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return  -1;
    }

    public boolean update(Medical Medical) throws SQLException, ClassNotFoundException {
        String sql = "select * from thuoc where thuoc_id = " + Medical.getThuocId();
        PreparedStatement ps = DbConnection.openConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        if (rs.next() ) {
            // update pet in database
            sql = "update thuoc set ten_thuoc = ?, nhom_thuoc = ?, soluong = ?, nhasx = ?, hsd = ? where thuoc_id = ?" ;
            ps = DbConnection.openConnection().prepareStatement(sql);
            ps.setString(1, Medical.getTenThuoc());
            ps.setString(2, Medical.getNhomThuoc());
            ps.setInt(3, Medical.getSoLuong());
            ps.setString(4, Medical.getNhaSx());
            ps.setDate(5, Medical.getHsd());
            ps.setInt(6, Medical.getThuocId());
            ps.executeUpdate();
            return true;
        }
        return false;
    }

    public void delete(Medical medical) throws SQLException {
        String query = "DELETE FROM thuoc WHERE thuoc_id=" + medical.getThuocId();
        PreparedStatement ps = null;
        try {
            ps = DbConnection.openConnection().prepareStatement(query);
            ps.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

     }
    public Medical getMedical(int MedicalId) throws SQLException {
        Medical Medical = null;
        String query = "SELECT * FROM thuoc WHERE thuoc_id=?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, MedicalId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String ten_thuoc = resultSet.getString("ten_thuoc");
                String nhom_thuoc = resultSet.getString("nhom_thuoc");
                int soluong = resultSet.getInt("soluong");
                String nhasx = resultSet.getString("nhasx");
                Date hsd = resultSet.getDate("hsd");
                Medical = new Medical(MedicalId, ten_thuoc, nhom_thuoc, soluong, nhasx, hsd);
            }
        }
        return Medical;
    }
}