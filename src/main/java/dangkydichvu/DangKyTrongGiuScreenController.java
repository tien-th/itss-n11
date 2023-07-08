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
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("");
            alert.setHeaderText("Đăng ký dịch vụ thành công");
            alert.setContentText("Đăng ký cho " + pet.getName() + " thành công vào ngày " + ngayKham + " lúc " + startHour + " giờ");
            alert.showAndWait();
        }
        else {
//            System.out.println("full slot");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("");
            alert.setHeaderText("Đăng ký dịch vụ thất bại");
            alert.setContentText("Hiện tại chuồng đã hết chỗ. Vui lòng chọn ngày khác");
            alert.showAndWait();
        }
    }

}
