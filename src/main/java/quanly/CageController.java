package quanly;

import connection.DbConnection;
import entity.*;


import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class CageController {

   public ArrayList<Cage> cageList = new ArrayList<Cage>();

   public void getCageList() throws SQLException, ClassNotFoundException{
       PreparedStatement ps = null;
       String sql1 = "SELECT * FROM lodging";
       String sql = "SELECT l.lodging_id, s.pet_id, l.status " +
               "FROM lodging l " +
               "JOIN set_lodging s ON l.lodging_id = s.lodging_id";
         ps = DbConnection.openConnection().prepareStatement(sql);

         java.sql.ResultSet rs = ps.executeQuery();
         while (rs.next()){
              int id = rs.getInt("lodging_id");
              int status = rs.getInt("status");
              int pet_id = rs.getInt("pet_id");
              Cage c = new Cage(id, status, pet_id);
              cageList.add(c);
         }

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



}
