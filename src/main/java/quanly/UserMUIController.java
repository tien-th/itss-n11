package quanly;

import dangkydichvu.UserFuncBase;
import entity.User;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;

import java.io.FileFilter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UserMUIController extends UserFuncBase {
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
    private TableColumn<User, String> roleColumn;
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
          showdata();
        try {
            searchPet();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


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

    @FXML
    public void addUser(ActionEvent event) throws SQLException, ClassNotFoundException, IOException {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("");
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        TextField usernameTextField = new TextField();
        TextField passwordTextField = new TextField();
        TextField nameTextField = new TextField();
        TextField emailTextField = new TextField();
        DatePicker birthdayPicker  = new DatePicker();
        TextField genderTextField = new TextField();


        TextField roleTextField = new TextField();
        roleTextField.setPromptText("0 for customer, 2 for doctor");

        GridPane gridPane = new GridPane();
        gridPane.add(new Label("Tên đăng nhập"), 0, 0);
        gridPane.add(usernameTextField, 1, 0);
        gridPane.add(new Label("Mật khẩu"), 0, 1);
        gridPane.add(passwordTextField, 1, 1);
        gridPane.add(new Label("Họ và tên"), 0, 2);
        gridPane.add(nameTextField, 1, 2);
        gridPane.add(new Label("Email"), 0, 3);
        gridPane.add(emailTextField, 1, 3);
        gridPane.add(new Label("Ngày sinh"), 0, 4);
        gridPane.add(birthdayPicker, 1, 4);
        gridPane.add(new Label("Giới tính"), 0, 5);
        gridPane.add(genderTextField, 1, 5);
        gridPane.add(new Label("Vai trò"), 0, 6);
        gridPane.add(roleTextField, 1, 6);
        dialog.getDialogPane().setContent(gridPane);
        dialog.showAndWait();
        if(dialog.getResult() == ButtonType.OK){
            User user = new User(usernameTextField.getText(), passwordTextField.getText(), emailTextField.getText(), nameTextField.getText(), birthdayPicker.getValue().toString(), genderTextField.getText(), Integer.parseInt(roleTextField.getText()));
            userManageController.addUser(user);
            userList.add(user);
            userTableView.setItems(userList);
            userTableView.refresh();

        }

    }
    @FXML
    public void showdata(){
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
        // in role column , i waant print customer with role is 0 and doctor with role is 2
        roleColumn.setCellValueFactory(cellData -> {
            String roleString = "";
            if (cellData.getValue().getRole() == 0) {
                roleString = "customer";
            } else if (cellData.getValue().getRole() == 2) {
                roleString = "doctor";
            }
            return new SimpleStringProperty(roleString);
        });



        userTableView.setItems(userList);
        System.out.println(userTableView.getItems().size());
    }
@FXML
    public void searchPet() throws SQLException, ClassNotFoundException{
    FilteredList<User> filteredData = new FilteredList<>(userList, b -> true);
    searchTextField.textProperty().addListener((observable, oldValue, newValue) -> {
        filteredData.setPredicate(user -> {
            // If filter text is empty, display all persons.
            if (newValue == null || newValue.isEmpty()) {
                return true;
            }

            // Compare first name and last name of every person with filter text.
            String lowerCaseFilter = newValue.toLowerCase();

            if (user.getUsername().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                return true; // Filter matches first name.
            } else if (user.getName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                return true; // Filter matches last name.
            } else if (user.getEmail().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                return true; // Filter matches last name.
            } else if (user.getBirthday().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                return true; // Filter matches last name.
            } else if (user.getGender().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                return true; // Filter matches last name.
            } else if (user.getRole() == 0 && "customer".indexOf(lowerCaseFilter) != -1) {
                return true; // Filter matches last name.
            } else if (user.getRole() == 2 && "doctor".indexOf(lowerCaseFilter) != -1) {
                return true; // Filter matches last name.
            }
            else
                return false; // Does not match.
        });
    });
    SortedList<User> sortedData = new SortedList<>(filteredData);
    sortedData.comparatorProperty().bind(userTableView.comparatorProperty());
    userTableView.setItems(sortedData);

}

}
