package quanly;

import  dangkydichvu.UserFuncBase;
import entity.Care;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CareServicesMUIController extends  UserFuncBase implements Initializable {
    @FXML
    private TableView<Care> careTableView;
    @FXML
    private TableColumn<Care, Integer> petIdColumn;
    @FXML
    private TableColumn<Care, String> dichvuColumn;
    @FXML
    private TableColumn<Care, String> datetimeColumn;
    @FXML
    private TableColumn<Care, String> timeSlotColumn;

    private ObservableList<Care> careList;
    CareController careController = new CareController();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            careController.getListCareServices();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        careList = FXCollections.observableArrayList(careController.careList);
        petIdColumn.setCellValueFactory(new PropertyValueFactory<Care, Integer>("pet_id"));
        dichvuColumn.setCellValueFactory(new PropertyValueFactory<Care, String>("services"));
        datetimeColumn.setCellValueFactory(new PropertyValueFactory<Care, String>("day"));
        timeSlotColumn.setCellValueFactory(new PropertyValueFactory<Care, String>("timeslot"));
        timeSlotColumn.setCellFactory(column -> {
            return new TableCell<Care, String>() {
                @Override
                protected void updateItem(String timeslot, boolean empty) {
                    super.updateItem(timeslot, empty);
                    if (empty) {
                        setText(null);
                    } else {
                        setText(timeslot + ":00");
                    }
                }
            };
        });

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
}
