package quanly;

import dangkydichvu.UserFuncBase;
import entity.Appoint;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;


import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;



public class AppointUIController extends UserFuncBase implements Initializable {

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
    // in ra lichkham có từ appointController
//    public void printAppointList() {
//        for (Appoint appoint : appointController.appointList) {
//            System.out.println(appoint);
//        }
//    }
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
        time_slotColumn.setCellValueFactory(new PropertyValueFactory<Appoint, Integer>("time_slot"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<Appoint, String>("state"));

        appointTableView.setItems(appointList);
    }

    @FXML
    private TextField searchTextField; // Add this field to your UI

    @FXML
    private void searchPet(ActionEvent event) {
        String searchQuery = searchTextField.getText();
        if (searchQuery.isEmpty()) {
            // No search query provided
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Lỗi");
            alert.setHeaderText("Không có từ khóa tìm kiếm");
            alert.setContentText("Vui lòng nhập từ khóa tìm kiếm");
            alert.showAndWait();
            return;
        }

        // Clear previous search results
        appointList.clear();

        for (Appoint appoint : appointController.appointList) {
            String petIdStr = String.valueOf(appoint.getPet_id());
            int index = petIdStr.indexOf(searchQuery);
            if (index != -1) {
                appointList.add(appoint);
            }
        }

        if (appointList.isEmpty()) {
            // No users found
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Không tìm thấy người dùng");
            alert.setContentText("Không có người dùng phù hợp với từ khóa tìm kiếm");
            alert.showAndWait();
        }
    }

    // update information for lichkham

    private void updateAppoint(ActionEvent event) {
        Appoint selectedAppoint = appointTableView.getSelectionModel().getSelectedItem();
        if (selectedAppoint == null) {
            // No user selected
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Lỗi");
            alert.setHeaderText("Không có lịch khám được chọn");
            alert.setContentText("Vui lòng chọn một lịch khám để cập nhật");
            alert.showAndWait();
            return;
        }

        // Get the selected user's ID
        int selectedAppointId = selectedAppoint.getPet_id();

        // Find the user with this ID in the ArrayList
        Appoint selectedAppointToUpdate = null;
        for (Appoint appoint : appointController.appointList) {
            if (appoint.getPet_id() == selectedAppointId) {
                selectedAppointToUpdate = appoint;
                break;
            }
        }

        if (selectedAppointToUpdate == null) {
            // This should never happen
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Lỗi");
            alert.setHeaderText("Không tìm thấy lịch khám");
            alert.setContentText("Không tìm thấy lịch khám để cập nhật");
            alert.showAndWait();
            return;
        }

        // Update the user's information
        selectedAppointToUpdate.setDay(selectedAppoint.getDay());
        selectedAppointToUpdate.setTime_slot(selectedAppoint.getTime_slot());
        selectedAppointToUpdate.setState(selectedAppoint.getState());

        // Refresh the TableView to show the changes
        appointTableView.refresh();
    }

    








}