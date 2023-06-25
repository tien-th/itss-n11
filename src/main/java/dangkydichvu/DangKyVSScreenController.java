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
    private CheckBox checkBoxTiaLong;
    @FXML
    private CheckBox checkBoxLamMong;
    @FXML
    private CheckBox checkBoxTam;

    private void initializeComboBox() {
        comboBoxThoiGian.getItems().addAll("9:00 AM", "10:00 AM", "11:00 AM", "1:00 PM", "2:00 PM");
    }

    private void handleLuuButton() {
        String tenThuCung = choiceBoxTenThuCung.getValue();
        boolean khamBenh = checkBoxTiaLong.isSelected();
        boolean spa = checkBoxLamMong.isSelected();
        boolean trongGiữ = checkBoxTam.isSelected();
        String ngayKham = datePickerNgayKham.getValue().toString();
        String thoiGian = comboBoxThoiGian.getValue();

        // Thực hiện các thao tác lưu đăng ký khám với các thông tin đã lấy được
        System.out.println("Đăng ký khám");
        System.out.println("Tên thú cưng: " + tenThuCung);
        System.out.println("Dịch vụ:");
        if (khamBenh) {
            System.out.println("- Tia Long");
        }
        if (spa) {
            System.out.println("- Lam Mong");
        }
        if (trongGiữ) {
            System.out.println("- Tắm");
        }
        System.out.println("Ngày khám: " + ngayKham);
        System.out.println("Thời gian: " + thoiGian);
    }

    private void handleThoatButton() {
        // Thực hiện các thao tác khi người dùng thoát khỏi giao diện đăng ký khám
        System.out.println("Thoát");
    }
}
