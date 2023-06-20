package menu;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.sql.SQLException;

import entity.*;
import view_pet.*;

import java.util.ArrayList;


public class DangKyKhamScreenController {
    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Label labelTenThuCung;

    @FXML
    private Label labelDangKyKham;

    @FXML
    private ChoiceBox<String> choiceBoxTenThuCung;

    @FXML
    private Label labelDichVu;

    @FXML
    private CheckBox checkBoxKhamBenh;

    @FXML
    private CheckBox checkBoxSpa;

    @FXML
    private CheckBox checkBoxTrongGiữ;

    @FXML
    private Label labelDatLich;

    @FXML
    private DatePicker datePickerNgayKham;

    @FXML
    private ComboBox<String> comboBoxThoiGian;

    @FXML
    private Label labelNgayKham;

    @FXML
    private Label labelThoiGian;

    @FXML
    private Button buttonLuu;

    @FXML
    private Button buttonThoat;

    User user = null;

    public void setUser(User user) {
        this.user = user;
    }

    @FXML
    private void initialize() {
        initializeChoiceBox(user);
        initializeComboBox();
        initializeButtons();
    }



    private ArrayList<String> extractPetNames(ArrayList<Pet> petList){
        ArrayList<String> petNames = new ArrayList<>();
        for(Pet pet: petList){
            petNames.add(pet.getName());
        }
        return petNames;

    }




    private void initializeChoiceBox(User user) {
        ViewPetController viewPetController = new ViewPetController();
        try {
            viewPetController.getPetList(user);
            ArrayList<Pet> petList = viewPetController.petList;
            ArrayList<String> petNames = extractPetNames(petList);
            choiceBoxTenThuCung.getItems().addAll(petNames);

            choiceBoxTenThuCung.setOnAction(event -> {
                String selectedName = choiceBoxTenThuCung.getValue();
            });


        } catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }



    }

    private void initializeComboBox() {
        comboBoxThoiGian.getItems().addAll("9:00 AM", "10:00 AM", "11:00 AM", "1:00 PM", "2:00 PM");
    }

    private void initializeButtons() {
        buttonLuu.setOnAction(event -> handleLuuButton());
        buttonThoat.setOnAction(event -> handleThoatButton());
    }

    private void handleLuuButton() {
        // Xử lý lưu đăng ký khám tại đây
        String tenThuCung = choiceBoxTenThuCung.getValue();
        boolean khamBenh = checkBoxKhamBenh.isSelected();
        boolean spa = checkBoxSpa.isSelected();
        boolean trongGiữ = checkBoxTrongGiữ.isSelected();
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

    private void handleThoatButton() {
        // Thực hiện các thao tác khi người dùng thoát khỏi giao diện đăng ký khám
        System.out.println("Thoát");
    }
}
