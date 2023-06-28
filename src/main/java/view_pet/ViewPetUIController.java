package view_pet;

import dangkydichvu.UserFuncBase;
import entity.Pet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.scene.layout.GridPane;

public class ViewPetUIController extends UserFuncBase implements Initializable {

    // TODO : add update pet function, delete pet function, add pet function

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
        if (user == null) {
            System.out.println("user is null");
            return ; // fix bug
        }
        System.out.println("init user " + user.getName() + " " + user.getRole());// fix bug
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

    @FXML
    private void deletePet( ActionEvent event) {
        if (user.getRole() == 1 ){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText("You are not allowed to delete pet");
            alert.showAndWait();
            return ;
        }

        Pet selectedPet = petTableView.getSelectionModel().getSelectedItem();
        if (selectedPet != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Pet");
            alert.setHeaderText("Delete Pet");
            alert.setContentText("Are you sure you want to delete this pet?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                ViewPetController viewPetController = new ViewPetController();
                viewPetController.deletePet(selectedPet);
                petList.remove(selectedPet);
            }
        }
    }

    @FXML
    private void updatePet( ActionEvent event ) {
        Pet selectedPet = petTableView.getSelectionModel().getSelectedItem();
        if (selectedPet != null) {
            // Create a dialog for updating pet information
            Dialog<UpdatedPetInfo> dialog = new Dialog<>();
            dialog.setTitle("Update Pet Information");

            // Set the button types (Apply and Cancel)
            dialog.getDialogPane().getButtonTypes().addAll(ButtonType.APPLY, ButtonType.CANCEL);

            // Create text fields for the updated information
            TextField nameTextField = new TextField(selectedPet.getName());
            TextField categoryTextField = new TextField(selectedPet.getCategory());
            TextField ageTextField = new TextField(String.valueOf(selectedPet.getAge()));
            TextField colorTextField = new TextField(selectedPet.getColor());

            // Create a grid pane and add the text fields
            GridPane gridPane = new GridPane();
            gridPane.add(new Label("Name:"), 0, 0);
            gridPane.add(nameTextField, 1, 0);
            gridPane.add(new Label("Category:"), 0, 1);
            gridPane.add(categoryTextField, 1, 1);
            gridPane.add(new Label("Age:"), 0, 2);
            gridPane.add(ageTextField, 1, 2);
            gridPane.add(new Label("Color:"), 0, 3);
            gridPane.add(colorTextField, 1, 3);

            dialog.getDialogPane().setContent(gridPane);

            // Set the result converter to retrieve the updated information
            dialog.setResultConverter(dialogButton -> {
                if (dialogButton == ButtonType.APPLY) {
                    return new UpdatedPetInfo(
                            nameTextField.getText(),
                            categoryTextField.getText(),
                            Integer.parseInt(ageTextField.getText()),
                            colorTextField.getText()
                    );
//                    System.out.println("update pet " + updatedPetInfo.getName() + " " + updatedPetInfo.getCategory() + " " + updatedPetInfo.getAge() + " " + updatedPetInfo.getColor());
                }
                return null;
            });

            // Show the dialog and wait for the user's response
            Optional<UpdatedPetInfo> result = dialog.showAndWait();
            System.out.println(result);
            result.ifPresent(updatedInfo -> {
                String updatedName = updatedInfo.getName();
                String updatedCategory = updatedInfo.getCategory();
                int updatedAge = updatedInfo.getAge();
                String updatedColor = updatedInfo.getColor();

                // Perform the update operation using the updated information
                selectedPet.setName(updatedName);
                selectedPet.setCategory(updatedCategory);
                selectedPet.setAge(updatedAge);
                selectedPet.setColor(updatedColor);
                System.out.println("update pet " + selectedPet.getName() + " " + selectedPet.getCategory() + " " + selectedPet.getAge() + " " + selectedPet.getColor());

                // Update database
                ViewPetController viewPetController = new ViewPetController();

                boolean check = false;
                try {
                    check = viewPetController.updatePet(selectedPet);
                    Alert alert;
                    if (check){
                        alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Update Successful");
                        alert.setHeaderText(null);
                        alert.setContentText("Pet information has been updated successfully!");
                    }
                    else {
                        alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Update Failed");
                        alert.setHeaderText(null);
                        alert.setContentText("Pet information has not been updated!");
                    }
                    alert.showAndWait();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
                // Update the table view with the modified pet list
                petTableView.refresh();

                // Display a confirmation message

            });
        }
    }
}
