package repository.quanly;

import entity.Cage;
import entity.Pet;
import utils.connection.DbConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CageDbManager {
    public static ArrayList<Cage> getCageList() throws SQLException, ClassNotFoundException {
        ArrayList<Cage> cageList = new ArrayList<>();
        PreparedStatement ps = null;
        String sql1 = "SELECT * FROM lodging";
        String sql = "SELECT l.lodging_id,  COALESCE(s.pet_id, 0) AS pet_id, l.status " +
                "FROM lodging l " +
                "LEFT JOIN set_lodging s ON l.lodging_id = s.lodging_id";
        ps = DbConnection.openConnection().prepareStatement(sql);

        java.sql.ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("lodging_id");
            int status = rs.getInt("status");
            int pet_id = rs.getInt("pet_id");
            Cage c = new Cage(id, status, pet_id);
            cageList.add(c);
        }
        return cageList;
    }

    public static void deleteCage(int id) throws SQLException, ClassNotFoundException {
        String sql = "delete from lodging where lodging_id = ?";
        PreparedStatement ps = DbConnection.openConnection().prepareStatement(sql);
        ps.setInt(1, id);
        ps.executeUpdate();
    }

    public static void updateCage(Cage cage) throws SQLException, ClassNotFoundException {
        String sql = "update lodging set status = ? where lodging_id = ?";
        PreparedStatement ps = DbConnection.openConnection().prepareStatement(sql);
        ps.setInt(1, cage.isStatus());
        ps.setInt(2, cage.getId_cage());
        ps.executeUpdate();
    }

    public static void addCage(Cage cage) throws SQLException, ClassNotFoundException {
        String sql = "select max(lodging_id) as max_id from lodging";
        try {
            PreparedStatement ps = DbConnection.openConnection().prepareStatement(sql);
            java.sql.ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int id = rs.getInt(1) + 1;
                sql = "insert into lodging values (" + id + ", 0)";
                ps = DbConnection.openConnection().prepareStatement(sql);
                ps.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String detailPetinFor(int id_pet_selected) throws SQLException, ClassNotFoundException {
        PreparedStatement ps = null;
        String sql = "SELECT p.pet_id, p.name,p.category, p.username " +
                "FROM pet p " +
                "WHERE p.pet_id =" + id_pet_selected;
        System.out.printf(sql);
        ps = DbConnection.openConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
//
            int pet_id = rs.getInt("pet_id");
            String name = rs.getString("name");
            String category = rs.getString("category");
            String username = rs.getString("username");
            Pet p = new Pet(pet_id, username, name, category);
            String message = "Thông tin thú cưng:\n"
                    + " ID: " + p.getId() + "\n"
                    + " Tên: " + p.getName() + "\n"
                    + " Loại: " + p.getCategory() + "\n"
                    + " Chủ nhân: " + p.getUsername();

            return message;
        }

        return "Không tìm thấy pet";
    }
}