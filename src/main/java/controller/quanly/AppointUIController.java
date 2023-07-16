package controller.quanly;
import controller.user.ScreenHandler;
import utils.Utils;

import entity.Appoint;
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
import repository.quanly.AppointController;

import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
public class AppointUIController extends ScreenHandler implements Initializable {
    @FXML
    private TableView<Appoint> appointTableView ;
    @FXML
    private TableColumn<Appoint, Integer> petIdColumn;
    @FXML
    private TableColumn<Appoint, String> datetimeColumn;
    @FXML
    private TableColumn<Appoint, Integer> time_slotColumn;
    @FXML
    private TableColumn<Appoint, String> statusColumn;
    private ObservableList<Appoint> appointList;
    AppointController appointController = new AppointController();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        // data in all column align center
        petIdColumn.setStyle("-fx-alignment: CENTER;");
        datetimeColumn.setStyle("-fx-alignment: CENTER;");
        time_slotColumn.setStyle("-fx-alignment: CENTER;");
//        statusColumn.setStyle("-fx-alignment: CENTER;");
        try {
            appointList = FXCollections.observableArrayList(appointController.getAppointList());
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        petIdColumn.setCellValueFactory(new PropertyValueFactory<Appoint, Integer>("pet_id"));
        datetimeColumn.setCellValueFactory(new PropertyValueFactory<Appoint, String>("day"));
        time_slotColumn.setCellValueFactory(new PropertyValueFactory<Appoint, Integer>("time"));
        appointTableView.setItems(appointList);
    }

    public void deleteAppoint( ActionEvent event) throws SQLException, ClassNotFoundException {

        Appoint selectedAppoint = appointTableView.getSelectionModel().getSelectedItem();
        if (selectedAppoint == null) {
            return;
        }
        appointController.deleteAppoint(selectedAppoint);
        appointList.remove(selectedAppoint);
    }

    public void updateAppoint(ActionEvent event) throws SQLException, ClassNotFoundException {

        Appoint selectedAppoint = appointTableView.getSelectionModel().getSelectedItem();
        if (selectedAppoint == null) {
            return;
        }

        Dialog<Appoint> dialog = new Dialog<>();
        dialog.setTitle("Update Appointment");

        ButtonType updateButtonType = new ButtonType("Update", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(updateButtonType, ButtonType.CANCEL);

        // Create text fields for the updated information
        TextField dayTextField = new TextField(selectedAppoint.getDay());
        TextField timeTextField = new TextField(Integer.toString(selectedAppoint.getTime_slot()));

        // Create a grid pane and add the text fields
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        gridPane.add(new Label("Day:"), 0, 0);
        gridPane.add(dayTextField, 1, 0);
        gridPane.add(new Label("Time Slot:"), 0, 1);
        gridPane.add(timeTextField, 1, 1);

        dialog.getDialogPane().setContent(gridPane);

        // Set the result converter to retrieve the updated information
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == updateButtonType) {
                return new Appoint(
                        selectedAppoint.getPet_id(),
                        dayTextField.getText(),
                        Integer.parseInt(timeTextField.getText()),
                        selectedAppoint.getState()
                );
            }
            return null;
        });

        // Show the dialog and wait for the user's input
        Optional<Appoint> result = dialog.showAndWait();
        result.ifPresent(updatedAppoint -> {
            String notification = appointController.updateAppoint(updatedAppoint, selectedAppoint); // Update the appointment using the controller
            Utils.showAlert(notification);
            appointList.set(appointList.indexOf(selectedAppoint), updatedAppoint); // Update the list
            appointTableView.refresh(); // Refresh the table view
        });
    }
    @FXML
    private TextField searchTextField; // Add this field to your UI
    @FXML
    public void search(ActionEvent event) throws  SQLException, ClassNotFoundException{
        FilteredList<Appoint> filteredList = new FilteredList<>(appointList , p-> true);
        searchTextField.textProperty().addListener((observable, oldValue, newValue) ->{
            filteredList.setPredicate(appoint -> {
                if(newValue == null || newValue.isEmpty()){
                    return true;
                }
                if(Integer.valueOf(String.valueOf(searchTextField)) == appoint.getPet_id()){
                    return true;
                }
                return false;
            });
        });
        SortedList<Appoint> sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(appointTableView.comparatorProperty());
        appointTableView.setItems(sortedList);
    }

}