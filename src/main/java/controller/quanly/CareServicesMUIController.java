package controller.quanly;

import controller.user.ScreenHandler;

import entity.Care;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import repository.quanly.CareController;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CareServicesMUIController extends ScreenHandler implements Initializable {
    @FXML
    private TableView<Care> careTableView;
    @FXML
    private TableColumn<Care, Integer> petIdColumn;
    @FXML
    private TableColumn<Care, String> dichvuColumn;
    @FXML
    private TableColumn<Care, String> datetimeColumn;
    @FXML
    private TableColumn<Care, Integer> timeSlotColumn;
    @FXML
    private TableColumn<Care, Integer> giatienColumn;
    private ObservableList<Care> careList;
    CareController careController = new CareController();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            showListCareServices();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            filter();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    public void showListCareServices()throws SQLException, ClassNotFoundException{
        try {
            careController.getListCareServices();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        careList = FXCollections.observableArrayList(careController.careList);
        petIdColumn.setCellValueFactory(new PropertyValueFactory<Care, Integer>("pet_id"));
        dichvuColumn.setCellValueFactory(new PropertyValueFactory<Care, String>("services"));
        datetimeColumn.setCellValueFactory(new PropertyValueFactory<Care, String>("day"));
        timeSlotColumn.setCellValueFactory(new PropertyValueFactory<Care, Integer>("time_slot"));
        timeSlotColumn.setCellFactory(column -> new TableCell<Care, Integer>() {
            @Override
            protected void updateItem(Integer item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? "" : String.format("%02d:00", item));
            }
        });
        giatienColumn.setCellValueFactory(new PropertyValueFactory<Care, Integer>("price"));

        careTableView.setItems(careList);
    }
    public void deleteCareServices(ActionEvent event) throws SQLException, ClassNotFoundException{
        if (user.getRole() != 1 ){
            javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText("You are not allowed to delete pet");
            alert.showAndWait();
            return ;
        }
        Care selectedCare = careTableView.getSelectionModel().getSelectedItem();
        if (selectedCare == null) {
            return;
        }
        int selectedCareId = selectedCare.getPet_id();
        String day = selectedCare.getDay();
        int time = selectedCare.getTime_slot();
        CareController.deleteCareServices(selectedCareId, day, time);
        careList.remove(selectedCare);
        careTableView.refresh();

    }
    @FXML
    private TextField search;

    @FXML
    public void filter() throws SQLException, ClassNotFoundException{
        FilteredList<Care> filteredList = new FilteredList<>(careList, b -> true);
        search.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(care -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (care.getServices().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (care.getDay().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                }else if (Integer.toString(care.getPet_id()).indexOf(lowerCaseFilter)!= -1){
                    return true;
                }
                else
                      return false;
            });
        });
        SortedList<Care> sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(careTableView.comparatorProperty());
        careTableView.setItems(sortedList);

        
    }
    public  void update(ActionEvent event) throws SQLException, ClassNotFoundException{

    }

}
