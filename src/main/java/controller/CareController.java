package controller;

import  model.entity.Care;
import model.repository.dbmanager.CareDbManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CareController {
    private ArrayList<Care> careList ;

    public ArrayList<Care>  getListCareServices() throws SQLException, ClassNotFoundException {
        if (careList == null) {
            careList = CareDbManager.getListCareServices();
        }
        return careList;
    }

    public void deleteCareServices(Care selectedCare) throws SQLException, ClassNotFoundException {
        int selectedCareId = selectedCare.getPet_id();
        String day = selectedCare.getDay();
        int time = selectedCare.getTime_slot();
        CareDbManager.deleteCareServices(selectedCareId, day, time);
        careList.remove(selectedCare);
    }
    public static int updateCareServices(Care updatedCare, Care oldCare) {
//        if day and time_slot are not changed return (do nothing)
        if (updatedCare.getDay().equals(oldCare.getDay()) && updatedCare.getTime_slot() == oldCare.getTime_slot()) {
            return 0 ;
        }
//        if day and time_slot of updatedAppoint is already in database alert user and return
        try {
            String sql = "select * from dichvuvs where day = ? and time_slot = ?";
            PreparedStatement ps = utils.connection.DbConnection.openConnection().prepareStatement(sql);
            ps.setDate(1, java.sql.Date.valueOf(updatedCare.getDay()));
            ps.setInt(2, updatedCare.getTime_slot());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return -1 ;
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

      return 1;
    }


}

