package controller.user;
import repository.user.DangKyDvController;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

import java.sql.SQLException;

import entity.*;

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
        DangKyDvController dangKyKhamController = new DangKyDvController();
        if (dangKyKhamController.dangKyKham(pet, ngayKham, startHour) ) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("");
            alert.setHeaderText("Đăng ký khám thành công");
            alert.setContentText("Đăng ký cho " + pet.getName() + " thành công vào ngày " + ngayKham + " lúc " + startHour + " giờ");
            alert.showAndWait();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("");
            alert.setHeaderText("Đăng ký khám thất bại");
            alert.setContentText("Khoảng thời gian đã hết chỗ");
            alert.showAndWait();
        }

    }
}
