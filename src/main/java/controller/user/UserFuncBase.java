package controller.user;
import controller.pet.ViewPetController;
import entity.Pet;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import utils.Utils;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.ResourceBundle;

public class UserFuncBase extends ScreenHandler implements Initializable {
    @FXML
    public ChoiceBox<String> choiceBoxTenThuCung;
    @FXML
    public DatePicker datePickerNgayKham;
    @FXML
    public ChoiceBox<String> comboBoxThoiGian;
    ArrayList<Pet> petList ;

    public Pet getPet(String petName){
        for(Pet pet: petList){
            if(pet.getName().equals(petName)){
                return pet;
            }
        }
        return null;
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
        try {
            ViewPetController controller = new ViewPetController();
            controller.setUser(user);
            petList = controller.getPetList();
            ArrayList<String> petNames = Utils.extractPetNames(petList);
            choiceBoxTenThuCung.getItems().addAll(petNames);
//            choiceBoxTenThuCung.setOnAction(this::getPetNameLabel);

        } catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }


}
