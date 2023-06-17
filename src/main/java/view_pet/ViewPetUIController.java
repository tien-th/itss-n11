package view_pet;

import entity.Pet;
import entity.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class ViewPetUIController implements Initializable {
    User user = null ;
    public void setUser(User user) {
        this.user = user;
    }

    @FXML
    private TableView<Pet> petTableView;
    @FXML
    private TableColumn<Pet, Integer> idColumn;
    @FXML
    private TableColumn<Pet, String> usernameColumn;
    @FXML
    private TableColumn<Pet, String> nameColumn;
    @FXML
    private TableColumn<Pet, String> colorColumn;
    @FXML
    private TableColumn<Pet, String> categoryColumn;
    @FXML
    private TableColumn<Pet, Integer> ageColumn;
    @FXML
    private TableColumn<Pet, String> genderColumn;

    private ObservableList<Pet> petList; // list of pets


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ViewPetController viewPetController = new ViewPetController();
        try {
            viewPetController.getPetList(user);
        } catch (Exception e) {
            e.printStackTrace();
        }

        petList = FXCollections.observableArrayList(viewPetController.petList);
        idColumn.setCellValueFactory(new PropertyValueFactory<Pet, Integer>("id"));
        usernameColumn.setCellValueFactory(new PropertyValueFactory<Pet, String>("username"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Pet, String>("name"));
        colorColumn.setCellValueFactory(new PropertyValueFactory<Pet, String>("color"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<Pet, String>("category"));
        ageColumn.setCellValueFactory(new PropertyValueFactory<Pet, Integer>("age"));
        genderColumn.setCellValueFactory(new PropertyValueFactory<Pet, String>("gender"));
        petTableView.setItems(petList);

    }

}
