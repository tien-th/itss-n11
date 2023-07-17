package view.user;

import model.entity.User;
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
import controller.UserManageController;
import view.ScreenHandler;

import java.io.IOException;
import java.sql.SQLException;

public class UserMUIController extends ScreenHandler implements Initializable {
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


    UserManageController userManageController = new UserManageController();

    @Override
    public void initialize(java.net.URL location, java.util.ResourceBundle resources) {
        try {
            showdata();
            searchUser();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void deleteUser(ActionEvent event) throws SQLException, ClassNotFoundException {
        User selectedUser = userTableView.getSelectionModel().getSelectedItem();
        if (selectedUser == null) {

        }
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

        // Gender ComboBox
        ComboBox<String> genderComboBox = new ComboBox<>();
        genderComboBox.getItems().addAll("male", "female");

        // Role ComboBox
        ComboBox<String> roleComboBox = new ComboBox<>();
        roleComboBox.getItems().addAll("Customer", "Admin", "Doctor");
        roleComboBox.setPromptText("Role");

        GridPane gridPane = new GridPane();
        gridPane.add(new Label("Username"), 0, 0);
        gridPane.add(usernameTextField, 1, 0);
        gridPane.add(new Label("Password"), 0, 1);
        gridPane.add(passwordTextField, 1, 1);
        gridPane.add(new Label("Name"), 0, 2);
        gridPane.add(nameTextField, 1, 2);
        gridPane.add(new Label("Email"), 0, 3);
        gridPane.add(emailTextField, 1, 3);
        gridPane.add(new Label("Birthday"), 0, 4);
        gridPane.add(birthdayPicker, 1, 4);
        gridPane.add(new Label("Gender"), 0, 5);
        gridPane.add(genderComboBox, 1, 5);
        gridPane.add(new Label("Role"), 0, 6);
        gridPane.add(roleComboBox, 1, 6);
        dialog.getDialogPane().setContent(gridPane);
        dialog.showAndWait();

        if(dialog.getResult() == ButtonType.OK){
            String role = roleComboBox.getValue();
            int roleValue = 0; // Default to customer
            switch (role) {
                case "Admin":
                    roleValue = 1;
                    break;
                case "Doctor":
                    roleValue = 2;
                    break;
            }

            User user = new User(usernameTextField.getText(), passwordTextField.getText(), emailTextField.getText(), nameTextField.getText(), birthdayPicker.getValue().toString(), genderComboBox.getValue(), roleValue);
            userManageController.addUser(user);
            userList.add(user);
            userTableView.setItems(userList);
            userTableView.refresh();
        }
    }
    @FXML
    public void showdata() throws SQLException, ClassNotFoundException {

        userList = FXCollections.observableArrayList(userManageController.getUserList());
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
    public void searchUser() throws SQLException, ClassNotFoundException{
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
