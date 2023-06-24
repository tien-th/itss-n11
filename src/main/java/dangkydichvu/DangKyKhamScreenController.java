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
            petList = viewPetController.petList;
            ArrayList<String> petNames = extractPetNames(petList);
            choiceBoxTenThuCung.getItems().addAll(petNames);
            choiceBoxTenThuCung.setOnAction(this::getPetNameLabel);

        } catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    @FXML
    private DatePicker datePickerNgayKham;
    @FXML
    private ChoiceBox<String> comboBoxThoiGian;

    ArrayList<Pet> petList ;

    public Pet getPet(String petName){
        for(Pet pet: petList){
            if(pet.getName().equals(petName)){
                return pet;
            }
        }
        return null;
    }

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
            alert.setTitle("Đăng ký khám thất bại");
            alert.setHeaderText("Đăng ký khám thất bại");
            alert.setContentText("Đăng ký khám thất bại");
            alert.showAndWait();
        }

    }


//    @FXML
//    private CheckBox checkBoxKhamBenh;
//    @FXML
//    private CheckBox checkBoxSpa;
//    @FXML
//    private CheckBox checkBoxTrongGiu;
}
