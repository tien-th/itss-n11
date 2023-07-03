package quanly;

import dangkydichvu.UserFuncBase;
import entity.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import java.sql.SQLException;
public class UserMUIController extends UserFuncBase  {
    @FXML
    private TableView<User> userTableView = new TableView<>();
    @FXML
    private TableColumn<User, String> usernameColumn;
    @FXML
    private TableColumn<User, String> nameColumn;
    @FXML
    private TableColumn<User, String> emailColumn;
    @FXML
    private TableColumn<User, String> birthdayColumn;
    @FXML
    private TableColumn<User, String> genderColumn;
    private ObservableList<User> userList;

    @FXML
    private TextField searchTextField; // Add this field to your UI


    @FXML
    private void searchUser(ActionEvent event) {
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
        userList.clear();

        for (User user : userManageController.userList) {
            if (user.getUsername().contains(searchQuery)) {
                userList.add(user);
            }
        }

        if (userList.isEmpty()) {
            // No users found
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thông báo");
            alert.setHeaderText("Không tìm thấy người dùng");
            alert.setContentText("Không có người dùng phù hợp với từ khóa tìm kiếm");
            alert.showAndWait();
        }
    }
    UserManageController userManageController = new UserManageController();
    @Override
    public void initialize(java.net.URL location, java.util.ResourceBundle resources) {
//        super.initialize(location, resources);
//        if (user == null) {
//            System.out.println("user is null");
//            return ; // fix bug
//        }
//        System.out.println("init user " + user.getName() + " " + user.getRole());// fix bug
//        UserManageController userManageController = new UserManageController();
        try {
            userManageController.getUserList();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        System.out.println("user list size " + userManageController.userList.size());
        userList = FXCollections.observableArrayList(userManageController.userList);
        usernameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("username"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("name"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<User, String>("email"));
        birthdayColumn.setCellValueFactory(new PropertyValueFactory<User, String>("birthday"));
        genderColumn.setCellValueFactory(new PropertyValueFactory<User, String>("gender"));
        userTableView.setItems(userList);
        System.out.println(userTableView.getItems().size());
    }

    @FXML
    private void deleteUser(ActionEvent event) throws SQLException, ClassNotFoundException {
        User selectedUser = userTableView.getSelectionModel().getSelectedItem();
        if (selectedUser == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Lỗi");
            alert.setHeaderText("Chưa chọn người dùng");
            alert.setContentText("Vui lòng chọn người dùng");
            alert.showAndWait();
            return;
        }

//        UserManageController userManageController = new UserManageController();
        userManageController.deleteUser(selectedUser);
        userList.remove(selectedUser);
    }

}
