package controller.user;
import javafx.event.ActionEvent;

import java.sql.SQLException;

import entity.*;
import utils.Utils;

public class DangKyKhamScreenController extends UserFuncBase {

    public void handleLuuButton(ActionEvent event) throws SQLException, ClassNotFoundException {
        // Xử lý lưu đăng ký khám tại đây
        String tenThuCung = choiceBoxTenThuCung.getValue();
        Pet pet = getPet(tenThuCung);
        String ngayKham = datePickerNgayKham.getValue().toString();
        String thoiGian = comboBoxThoiGian.getValue();
        String[] parts = thoiGian.split(":");
        int startHour = Integer.parseInt(parts[0]);

        System.out.println("day :" + ngayKham + "time: " + startHour);
        String dkyKhamInfor = RegisterController.dangKyKham(pet.getId(), ngayKham, startHour);
        Utils.showAlert(dkyKhamInfor);
    }
}
