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
         String sql = "SELECT * FROM Medical";
            preparedStatement = DbConnection.openConnection().prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("Medical_id");
                String ten_Medical = resultSet.getString("ten_Medical");
                String nhom_Medical = resultSet.getString("nhom_Medical");
                int soluong = resultSet.getInt("soluong");
                String nhasx = resultSet.getString("nhasx");
                Date hsd = resultSet.getDate("hsd");
                Medical a = new Medical(id, ten_Medical, nhom_Medical, soluong, nhasx, hsd);
                medicalList.add(a);
            }
     }



    public boolean add(Medical Medical) throws SQLException {
        String query = "INSERT INTO Medical (Medical_id, ten_Medical, nhom_Medical, soluong, nhasx, hsd) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, Medical.getThuocId());
            statement.setString(2, Medical.getTenThuoc());
            statement.setString(3, Medical.getNhomThuoc());
            statement.setInt(4, Medical.getSoLuong());
            statement.setString(5, Medical.getNhaSx());
            statement.setDate(6, Medical.getHsd());
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        }
    }

    public boolean update(Medical Medical) throws SQLException {
        String query = "UPDATE Medical SET ten_Medical=?, nhom_Medical=?, soluong=?, nhasx=?, hsd=? WHERE Medical_id=?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, Medical.getTenThuoc());
            statement.setString(2, Medical.getNhomThuoc());
            statement.setInt(3, Medical.getSoLuong());
            statement.setString(4, Medical.getNhaSx());
            statement.setDate(5, Medical.getHsd());
            statement.setInt(6, Medical.getThuocId());
            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        }
    }

    public boolean delete(int MedicalId) throws SQLException {
        String query = "DELETE FROM Medical WHERE Medical_id=?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, MedicalId);
            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;
        }
    }
}