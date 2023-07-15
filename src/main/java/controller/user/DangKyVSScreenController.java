package controller.user;
import repository.user.RegisterServiceSaver;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.SQLException;

import entity.*;

public class DangKyVSScreenController extends UserFuncBase {

    @FXML
    private RadioButton buttonTiaLong, buttonLamMong, buttonTam;
    private Label dichVu = new Label();
    public void getDichVu(ActionEvent event) {
        if (buttonTiaLong.isSelected()) {
//            dichVu.setText("Hair Trimming");
            dichVu.setText("Tia lông");
        }
        else if (buttonLamMong.isSelected()) {
//            dichVu.setText("Nail");
            dichVu.setText("Làm móng");
        }
        else if (buttonTam.isSelected()) {
//            dichVu.setText("Hygiene");
            dichVu.setText("Tắm");
        }
    }

    public void handleLuuButton(ActionEvent event) throws SQLException, ClassNotFoundException {
        String tenThuCung = choiceBoxTenThuCung.getValue();
        Pet pet = getPet(tenThuCung);
        String ngayKham = datePickerNgayKham.getValue().toString();
        String thoiGian = comboBoxThoiGian.getValue();
        String[] parts = thoiGian.split(":");
        int startHour = Integer.parseInt(parts[0]);
        RegisterServiceSaver dangKyVsController = new RegisterServiceSaver();
        dangKyVsController.dangKyVs(pet.getId(), ngayKham, startHour, dichVu.getText());
    }


}
