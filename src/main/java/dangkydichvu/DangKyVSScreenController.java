package dangkydichvu;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.SQLException;

import entity.*;
import view_pet.*;

import java.util.ArrayList;
import java.util.ResourceBundle;

public class DangKyVSScreenController extends UserFuncBase  {

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
        DangKyDvController dangKyVsController = new DangKyDvController();
        dangKyVsController.dangKyVs(pet, ngayKham, startHour, dichVu.getText());
    }


}
