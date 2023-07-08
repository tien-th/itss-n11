package quanly;
import dangkydichvu.UserFuncBase;
import entity.Appoint;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
public class AppointUIController extends UserFuncBase implements Initializable {
    @FXML
    private TableView<Appoint> appointTableView ;
    @FXML
    private TableColumn<Appoint, Integer> petIdColumn;
    @FXML
    private TableColumn<Appoint, String> datetimeColumn;
    @FXML
    private TableColumn<Appoint, String> time_slotColumn;
    @FXML
    private TableColumn<Appoint, String> statusColumn;
    private ObservableList<Appoint> appointList;
    AppointController appointController = new AppointController();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            appointController.getAppointList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        appointList = FXCollections.observableArrayList(appointController.appointList);
        petIdColumn.setCellValueFactory(new PropertyValueFactory<Appoint, Integer>("pet_id"));
        datetimeColumn.setCellValueFactory(new PropertyValueFactory<Appoint, String>("day"));
        time_slotColumn.setCellValueFactory(new PropertyValueFactory<Appoint, String>("time"));

        appointTableView.setItems(appointList);
    }

    public void deleteAppoint( ActionEvent event) throws SQLException, ClassNotFoundException {
        if (user.getRole() != 1 ){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText("You are not allowed to delete pet");
            alert.showAndWait();
            return ;
        }
        Appoint selectedAppoint = appointTableView.getSelectionModel().getSelectedItem();
        if (selectedAppoint == null) {
            return;
        }
        int selectedAppointId = selectedAppoint.getPet_id();
        String day = selectedAppoint.getDay();
        int time = selectedAppoint.getTime_slot();
        AppointController.deleteAppoint(selectedAppointId, day, time);
        appointList.remove(selectedAppoint);
    }

    public void updateAppoint(ActionEvent event) throws SQLException, ClassNotFoundException {
        if (user.getRole() != 1) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText("You are not allowed to update appointments");
            alert.showAndWait();
            return;
        }

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
            int notification = appointController.updateAppoint(updatedAppoint, selectedAppoint); // Update the appointment using the controller
            if (notification == 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Error");
                alert.setContentText("The appointment is not changed");
                alert.showAndWait();
                return;
            }
            else if (notification == -1) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Error");
                alert.setContentText("The appointment is not available at this time");
                alert.showAndWait();
                return;
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Update Appointment");
            alert.setHeaderText("Success");
            System.out.println(notification);
            alert.setContentText("The appointment is updated successfully");
            alert.showAndWait();
            appointList.set(appointList.indexOf(selectedAppoint), updatedAppoint); // Update the list
            appointTableView.refresh(); // Refresh the table view
        });
    }
    @FXML
    private TextField searchTextField; // Add this field to your UI

}