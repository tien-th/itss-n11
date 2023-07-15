package repository.quanly;

import utils.connection.DbConnection;
import entity.*;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class CageController {
   public ArrayList<Cage> cageList ;
   public ArrayList<Cage> getCageList() throws SQLException, ClassNotFoundException{
       if (cageList == null){
           cageList = CageDbManager.getCageList();
       }
       return cageList;
   }

  public void deleteCage(int id) throws SQLException, ClassNotFoundException{
       CageDbManager.deleteCage(id);
       for (Cage c : cageList){
           if (c.getId_cage() == id){
               cageList.remove(c);
               return;
           }
       }
   }

    public static void updateCage(Cage cage) throws SQLException, ClassNotFoundException {
        CageDbManager.updateCage(cage);
    }

    public Cage addCage() throws SQLException, ClassNotFoundException {
        int newId =maxId() + 1 ;
        Cage cage = new Cage(newId, 0);
        CageDbManager.addCage(cage);
        cageList.add(cage);
        return cage;
    }

    private int maxId() {
        int maxId = 1;
        for (Cage c : cageList){
            if (c.getId_cage() > maxId){
                maxId = c.getId_cage();
            }
        }
        return maxId;
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

    public void detailInfo(int id_pet_selected) throws SQLException, ClassNotFoundException {

        String message = CageDbManager.detailPetinFor(id_pet_selected);
        JOptionPane JOptionPane = null;
        JOptionPane.showMessageDialog(null, message);
//            System.out.println(rs.getString("p.pet_id") + " " + rs.getString("p.name") + " " + rs.getString("p.category") + " " + rs.getString("u.hoten"));
        }

}
