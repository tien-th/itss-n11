package controller.quanly;

import controller.user.UserFuncBase;
import entity.Cage;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import repository.quanly.CageController;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.*;


public class CageMUIController extends UserFuncBase implements Initializable {
    @FXML
    private TableView<Cage> cageTableView;
    @FXML
    private TableColumn<Cage, Integer> cageIdColumn;
    @FXML
    private TableColumn<Cage, String> cageStatusColumn;
    @FXML
    private TableColumn<Cage, String> petIdcolumn;

    private ObservableList<Cage> cageList;
    CageController cageController = new CageController();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       showCage();
       filterCage();

    }

    public void deleteCage(ActionEvent event) throws SQLException, ClassNotFoundException {
        if (user.getRole() != 1) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText("You are not allowed to delete cage");
            alert.showAndWait();
            return;
        }
        Cage cage = cageTableView.getSelectionModel().getSelectedItem();
        if (cage == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText("Please choose a cage");
            alert.showAndWait();
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete cage");
        alert.setHeaderText("Delete cage");
        alert.setContentText("Are you sure to delete this cage?");
        Optional<ButtonType> option = alert.showAndWait();
        if (option.get() == ButtonType.OK) {
            cageController.deleteCage(cage.getId_cage());
            cageList.remove(cage);
        }
    }

    public void updateCage(ActionEvent event) throws SQLException, ClassNotFoundException {
        Cage selectedCage = cageTableView.getSelectionModel().getSelectedItem();
        if (selectedCage == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText("Please choose a cage");
            alert.showAndWait();
            return;
        }
        Dialog<Cage> dialog = new Dialog<>();
        dialog.setTitle("Update cage");

        ButtonType updateButtonType = new ButtonType("Update", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(updateButtonType, ButtonType.CANCEL);

        // create textfield for the updated information
        TextField cageIdTextField = new TextField(Integer.toString(selectedCage.getId_cage()));
        TextField cageStatusTextField = new TextField(Integer.toString(selectedCage.isStatus()));

       // hiện ra màn hình để nhập thông tin
        GridPane grid = new GridPane();
        grid.add(new Label("Cage ID:"), 1, 1);
        grid.add(cageIdTextField, 2, 1);
        grid.add(new Label("Cage Status:"), 1, 2);
        grid.add(cageStatusTextField, 2, 2);

        dialog.getDialogPane().setContent(grid);

        // convert the result to a cage when the update button is clicked.
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == updateButtonType) {
                try {
                    return new Cage(Integer.parseInt(cageIdTextField.getText()), Integer.parseInt(cageStatusTextField.getText()));
                } catch (NumberFormatException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Error");
                    alert.setContentText("Please enter a number");
                    alert.showAndWait();
                    return null;
                }
            }
            return null;
        });

        Optional<Cage> result = dialog.showAndWait();

        result.ifPresent(cage -> {
            try {
                CageController.updateCage(cage);
                cageList.set(cageList.indexOf(selectedCage), cage);
                cageTableView.refresh();
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

    }
    public void addCage(ActionEvent event) throws SQLException, ClassNotFoundException{
        //newid = cageList.get(cageList.size() - 1).getId_cage() + 1;  if (cageList.size() == 0) newid = 1;
        int newid = 0;
        if(cageList.size() == 0) newid = 1;
        else newid = cageList.get(cageList.size() - 1).getId_cage() + 1;

        int newStatus = 0;
        Cage cage = new Cage(newid, newStatus);

        cageController.addCage(cage);
        cageList.add(cage);
        cageTableView.setItems(cageList);
        cageTableView.refresh();
    }

 public void showCageListByStatus (ActionEvent event) throws SQLException, ClassNotFoundException{
      List<Cage> cageList = CageController.getCageListByStatus(1);
     cageIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
     cageStatusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
     cageTableView.setItems(FXCollections.observableList(cageList));
 }



 public void detailInfo(ActionEvent event) throws SQLException, ClassNotFoundException, IOException {
     Cage selectedCage = cageTableView.getSelectionModel().getSelectedItem();

     if (selectedCage == null) {
         Alert alert = new Alert(Alert.AlertType.ERROR);
         alert.setTitle("Error");
         alert.setHeaderText("Error");
         alert.setContentText("Please choose a cage");
         alert.showAndWait();
         return;
     }else {
         int id_pet_selected = selectedCage.getPet_id();
         if (id_pet_selected == 0) {
             Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.setTitle("Error");
             alert.setHeaderText("Error");
             alert.setContentText("Not pet in this cage");
             alert.showAndWait();
             return;
         } else {
             cageController.detailInfo(id_pet_selected);
         }
     }



 }

 public void showCage(){
     try {
         cageController.getCageList();
     } catch (Exception e) {
         e.printStackTrace();
     }
     cageList = FXCollections.observableArrayList(cageController.cageList);

     cageIdColumn.setCellValueFactory(new PropertyValueFactory<Cage, Integer>("id_cage"));

     cageStatusColumn.setCellValueFactory(cellData -> {
         String status = cellData.getValue().isStatus() == 1 ? "đã sử dụng" : "trống";
         return new SimpleStringProperty(status);
     });
     petIdcolumn.setCellValueFactory(cellData -> {
         String petId = cellData.getValue().getPet_id() ==0 ? "null" : Integer.toString(cellData.getValue().getPet_id());
         return new SimpleStringProperty(petId);
     });


     cageTableView.setItems(cageList);

 }

 @FXML
private TextField filterField;

 public void filterCage(){
     FilteredList<Cage> filteredData = new FilteredList<>(cageList, p -> true);
     filterField.textProperty().addListener((observable, oldValue, newValue) -> {
         filteredData.setPredicate(cage -> {
             // If filter text is empty, display all cages.
             if (newValue == null || newValue.isEmpty()) {
                 return true;
             }

             // Compare cage id of every cage with filter text.
             String lowerCaseFilter = newValue.toLowerCase();
             if(String.valueOf(cage.getId_cage()).indexOf(lowerCaseFilter) != -1){
                 return true;
             }
             else if(String.valueOf(cage.isStatus()).indexOf(lowerCaseFilter) != -1){
                 return true;
             }
             else if(String.valueOf(cage.getPet_id()).indexOf(lowerCaseFilter) != -1){
                 return true;
             }
             else
                 return false; // Does not match.
         });
     });
        SortedList<Cage> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(cageTableView.comparatorProperty());
        cageTableView.setItems(sortedData);

 }

}






