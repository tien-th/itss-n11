package controller;
import model.entity.Appoint;
import model.repository.dbmanager.AppointDbManager;

import java.sql.SQLException;
import java.util.ArrayList;

public class AppointController {
    private ArrayList<Appoint> appointList;

    public ArrayList<Appoint>  getAppointList() throws SQLException, ClassNotFoundException {
        if (appointList == null) {
            appointList = AppointDbManager.getAppointList();
        }
        return appointList;
    }

    public void deleteAppoint(Appoint selectedAppoint) throws SQLException, ClassNotFoundException {
        int selectedAppointId = selectedAppoint.getPet_id();
        String day = selectedAppoint.getDay();
        int time_slot = selectedAppoint.getTime_slot();
        AppointDbManager.deleteAppoint(selectedAppointId, day, time_slot);
    }

    public String updateAppoint(Appoint updatedAppoint, Appoint oldAppoint) {
//        if day and time_slot are not changed return (do nothing)
        int check = AppointDbManager.updateAppoint(updatedAppoint, oldAppoint);
        if (check == 0) {
            return "Không có gì thay đổi";
        }
        if (check == -1) {
            return "Thời gian đã có người đặt";
        }
        appointList.remove(oldAppoint);
        appointList.add(updatedAppoint);
        return "Cập nhật thành công";

    }
}
