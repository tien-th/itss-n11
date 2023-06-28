package dangkydichvu;

import entity.Pet;
import entity.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import menu.UserUIController;
import view_pet.ViewPetController;


import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class UserFuncBase implements Initializable {
    @FXML
    public ChoiceBox<String> choiceBoxTenThuCung;
    @FXML
    public DatePicker datePickerNgayKham;
    @FXML
    public ChoiceBox<String> comboBoxThoiGian;

    public User user = null ;
    public void setUser(User user) {
        System.out.println("set user " + user.getName() + " " + user.getRole());// fix bug
        this.user = user;
    }

    ArrayList<Pet> petList ;
    public ArrayList<String> extractPetNames(ArrayList<Pet> petList){
        ArrayList<String> petNames = new ArrayList<>();
        for(Pet pet: petList){
            petNames.add(pet.getName());
        }
        return petNames;
    }

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
        ViewPetController viewPetController = new ViewPetController();
        try {
            viewPetController.getPetList(user);
            petList = viewPetController.petList;
            ArrayList<String> petNames = extractPetNames(petList);
            choiceBoxTenThuCung.getItems().addAll(petNames);
//            choiceBoxTenThuCung.setOnAction(this::getPetNameLabel);

        } catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public void goBack(ActionEvent e ) throws IOException {
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        URL url = Paths.get("./src/main/java/menu/user.fxml").toUri().toURL();
        FXMLLoader loader = new FXMLLoader();
        Parent userViewParent = loader.load(url.openStream());
        Scene scene = new Scene(userViewParent);
        UserUIController userUIController =  loader.getController();
        userUIController.setUser(user);
        stage.setScene(scene);
    }

}
