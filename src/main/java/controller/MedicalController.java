package controller;

import model.repository.dbmanager.MedicalDbManager;
import utils.connection.DbConnection;
import model.entity.Medical;

import java.sql.*;
import java.util.ArrayList;

public class MedicalController {
    private Connection connection;
    public MedicalController() {
    }

    public ArrayList<Medical> medicalList ;
    public ArrayList<Medical> getMedicalList() throws SQLException, ClassNotFoundException {
        if (medicalList == null) {
            medicalList = MedicalDbManager.getMedicalList();
        }
        return medicalList;
    }

    public Medical add(String tenThuoc, String loaiThuoc, int soLuong, String nhaSx, Date hsd, int gia) {
        int medicalId = MedicalDbManager.addMedical(tenThuoc, loaiThuoc, soLuong, nhaSx, String.valueOf(hsd), gia);

        if (medicalId == -1) {
            return null;
        }
        Medical m = new Medical(medicalId, tenThuoc, loaiThuoc, soLuong, nhaSx, hsd, gia);
        medicalList.add(m);
        return m;
    }

    public boolean update(Medical medical) throws SQLException, ClassNotFoundException {
        boolean result = MedicalDbManager.updateMedical(medical);
        if (result) {
            for (Medical m : medicalList) {
                if (m.getThuocId() == medical.getThuocId()) {
                    m.setTenThuoc(medical.getTenThuoc());
                    m.setNhomThuoc(medical.getNhomThuoc());
                    m.setSoLuong(medical.getSoLuong());
                    m.setNhaSx(medical.getNhaSx());
                    m.setHsd(medical.getHsd());
                    m.setPrice(medical.getPrice());
                    break;
                }
            }
        }
        return result;
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

}