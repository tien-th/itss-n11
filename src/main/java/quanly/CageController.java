package quanly;

import connection.DbConnection;
import entity.*;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


import javax.swing.*;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class CageController {

   public ArrayList<Cage> cageList = new ArrayList<Cage>();

   public void getCageList() throws SQLException, ClassNotFoundException{
       PreparedStatement ps = null;
       String sql1 = "SELECT * FROM lodging";
       String sql = "SELECT l.lodging_id,  COALESCE(s.pet_id, 0) AS pet_id, l.status " +
               "FROM lodging l " +
               "LEFT JOIN set_lodging s ON l.lodging_id = s.lodging_id";
         ps = DbConnection.openConnection().prepareStatement(sql);

         java.sql.ResultSet rs = ps.executeQuery();
         while (rs.next()){
              int id = rs.getInt("lodging_id");
              int status = rs.getInt("status");
              int pet_id = rs.getInt("pet_id");
              Cage c = new Cage(id, status, pet_id);
              cageList.add(c);
         }
       System.out.println(cageList);
   }
  public static void deleteCage(int id) throws SQLException, ClassNotFoundException{
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
            if(rs.next()) {
                int id = rs.getInt(1) + 1;
                sql = "insert into lodging values ("+ id +", 0)";
                ps = DbConnection.openConnection().prepareStatement(sql);
                ps.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Cage> getCageListByStatus(int status) throws SQLException, ClassNotFoundException {
        List<Cage> cageList = new ArrayList<>();
        String sql = "select * from lodging where status = ?";
        PreparedStatement ps = DbConnection.openConnection().prepareStatement(sql);
        ps.setInt(1, status);
        java.sql.ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("lodging_id");
            int pet_id = rs.getInt("pet_id");
            Cage c = new Cage(id, status, pet_id);
            cageList.add(c);
        }
        return cageList;
    }

    public Pet detailInfo(int id_pet_selected) throws IOException, SQLException, ClassNotFoundException {

        PreparedStatement ps = null;
        String sql = "SELECT p.pet_id, p.name,p.category, p.username " +
                "FROM pet p "+
                "WHERE p.pet_id ="+ id_pet_selected;
        System.out.printf(sql);
        ps = DbConnection.openConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
//
            int pet_id = rs.getInt("pet_id");
            String name = rs.getString("name");
            String category = rs.getString("category");
            String username = rs.getString("username");
            Pet p = new Pet(pet_id, username, name , category);
            String message = "Thông tin thú cưng:\n"
                    + "ID: " + p.getId() + "\n"
                    + "Tên: " + p.getName() + "\n"
                    + "Loại: " + p.getCategory() + "\n"
                    + "Chủ nhân: " + p.getUsername();

            JOptionPane JOptionPane = null;
            JOptionPane.showMessageDialog(null, message);
//            System.out.println(rs.getString("p.pet_id") + " " + rs.getString("p.name") + " " + rs.getString("p.category") + " " + rs.getString("u.hoten"));
            return p;
            // write dialog simple


            }else
                return null;
   }




}
