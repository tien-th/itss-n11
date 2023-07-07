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
    private TableView<Appoint> appointTableView = new TableView<Appoint>();
    @FXML
    private TableColumn<Appoint, Integer> petIdColumn;
    @FXML
    private TableColumn<Appoint, String> datetimeColumn;
    @FXML
    private TableColumn<Appoint, String> statusColumn;

    private ObservableList<Appoint> appointList;



//     hiển thị dữ liệu có trong database về lịchkham ra màn hình
    AppointController appointController = new AppointController();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            appointList = FXCollections.observableArrayList(appointController.appointList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        petIdColumn.setCellValueFactory(new PropertyValueFactory<Appoint, Integer>("id"));
        datetimeColumn.setCellValueFactory(new PropertyValueFactory<Appoint, String>("datetime"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<Appoint, String>("status"));
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

    








}