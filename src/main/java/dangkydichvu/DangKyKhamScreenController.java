package dangkydichvu;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.SQLException;

import entity.*;
import view_pet.*;

import java.util.ArrayList;
import java.util.ResourceBundle;

public class DangKyKhamScreenController extends UserFuncBase implements Initializable {
    @FXML
    private Label petNameLabel = new Label();
    @FXML
    private ChoiceBox<String> choiceBoxTenThuCung;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)  {
        if (user == null) {
            System.out.println("user is null in initializeChoiceBox");
            return ; // fix bug
        }
        else {
            System.out.println("user is not null in initializeDangKyKhamScreen");
        }
        ViewPetController viewPetController = new ViewPetController();
        try {
            viewPetController.getPetList(user);
            ArrayList<Pet> petList = viewPetController.petList;
            ArrayList<String> petNames = extractPetNames(petList);
            choiceBoxTenThuCung.getItems().addAll(petNames);
            choiceBoxTenThuCung.setOnAction(this::getPetNameLabel);

        } catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    private void getPetNameLabel(ActionEvent actionEvent) {
        String petName = choiceBoxTenThuCung.getValue();
        petNameLabel.setText(petName);
    }
    private ArrayList<String> extractPetNames(ArrayList<Pet> petList){
        ArrayList<String> petNames = new ArrayList<>();
        for(Pet pet: petList){
            petNames.add(pet.getName());
        }
        return petNames;
    }


    @FXML
    private CheckBox checkBoxKhamBenh;
    @FXML
    private CheckBox checkBoxSpa;
    @FXML
    private CheckBox checkBoxTrongGiu;
    @FXML
    private DatePicker datePickerNgayKham;
    @FXML
    private ComboBox<String> comboBoxThoiGian;

    private void initializeComboBox() {
        comboBoxThoiGian.getItems().addAll("9:00 AM", "10:00 AM", "11:00 AM", "1:00 PM", "2:00 PM");
    }
    private void handleLuuButton() {
        // Xử lý lưu đăng ký khám tại đây
        String tenThuCung = choiceBoxTenThuCung.getValue();
        boolean khamBenh = checkBoxKhamBenh.isSelected();
        boolean spa = checkBoxSpa.isSelected();
        boolean trongGiữ = checkBoxTrongGiu.isSelected();
        String ngayKham = datePickerNgayKham.getValue().toString();
        String thoiGian = comboBoxThoiGian.getValue();

        // Thực hiện các thao tác lưu đăng ký khám với các thông tin đã lấy được
        System.out.println("Đăng ký khám");
        System.out.println("Tên thú cưng: " + tenThuCung);
        System.out.println("Dịch vụ:");
        if (khamBenh) {
            System.out.println("- Khám bệnh");
        }
        if (spa) {
            System.out.println("- Spa");
        }
        if (trongGiữ) {
            System.out.println("- Trông giữ");
        }
        System.out.println("Ngày khám: " + ngayKham);
        System.out.println("Thời gian: " + thoiGian);
    }
}
