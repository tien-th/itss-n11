package dangkydichvu;
import entity.Pet;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

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
        DangKyDvController dangKyDvController = new DangKyDvController();
        int lod = dangKyDvController.dangKyTrongGiu(pet, ngayKham, startHour);
        if (lod != 0 ) {
            System.out.println("lod: " + lod);
        }
        else {
            System.out.println("full slot");
        }
    }

}
