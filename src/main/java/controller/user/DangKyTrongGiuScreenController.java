package controller.user;
import repository.user.RegisterServiceSaver;
import entity.Pet;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import utils.Utils;

import java.sql.SQLException;
public class DangKyTrongGiuScreenController extends UserFuncBase {
    @FXML
    private Label petNameLabel = new Label();

    public void handleLuuButton(ActionEvent event) throws SQLException, ClassNotFoundException {
        // Xử lý lưu đăng ký khám tại đây
        String tenThuCung = choiceBoxTenThuCung.getValue();
        Pet pet = getPet(tenThuCung);
        String ngayKham = datePickerNgayKham.getValue().toString();
        String thoiGian = comboBoxThoiGian.getValue();
        String[] parts = thoiGian.split(":");
        int startHour = Integer.parseInt(parts[0]);

        String dkGiuPetInfor =  Controller.dangKyTrongGiu(pet.getId(), ngayKham, startHour);
        Utils.showAlert(dkGiuPetInfor);
    }

}
