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
       String sql = "SELECT * FROM lodging";
//       String sql = "SELECT lodging.id_lodging, set_lodging.pet_id, lodging.status\n" +
//               "FROM lodging\n" +
//               "JOIN set_lodging ON lodging.id_lodging = set_lodging.lodging_id;";
         ps = DbConnection.openConnection().prepareStatement(sql);

         java.sql.ResultSet rs = ps.executeQuery();
         while (rs.next()){
              int id = rs.getInt("lodging_id");
              int status = rs.getInt("status");
              Cage c = new Cage(id, status);
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
        String sql = "insert into lodging (status) values (?)";
        PreparedStatement ps = DbConnection.openConnection().prepareStatement(sql);
        ps.setInt(1, cage.isStatus());
        ps.executeUpdate();
    }

    public static List<Cage> getCageListByStatus(int status) throws SQLException, ClassNotFoundException {
        List<Cage> cageList = new ArrayList<>();
        String sql = "select * from lodging where status = ?";
        PreparedStatement ps = DbConnection.openConnection().prepareStatement(sql);
        ps.setInt(1, status);
        java.sql.ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("lodging_id");
            Cage c = new Cage(id, status);
            cageList.add(c);
        }
        return cageList;
    }



}
