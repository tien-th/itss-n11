package dangnhap;


import entity.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import utils.Utils;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.sql.SQLException;


public class DangKyUIController {
    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField passwordTextField;
    @FXML
    private TextField rePasswordTextField;
    @FXML
    private Tooltip errorLabel;
    @FXML
    public void initialize() {
        Tooltip passwordTooltip = new Tooltip("Mật khẩu và xác nhận mật khẩu không khớp");
        Tooltip.install(rePasswordTextField, passwordTooltip);
    }

    // TODO --Long : check if email is valid (action event)
    // để sau
    @FXML
    private TextField emailTextField;

    @FXML
    private TextField nameTextField;

    @FXML
    private DatePicker birthdayPicker ;
    @FXML
    private Label birthdayLabel = new Label();


    public void getBirthday(ActionEvent e) {
        // nếu ngày lớn hơn ngày hiện tại thì báo lỗi
        if (birthdayPicker.getValue().isAfter(java.time.LocalDate.now())) {
            // alert to show error
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid birthday");
            alert.setContentText("Please try again");
            alert.showAndWait();
            return ;
        }
        birthdayLabel.setText(birthdayPicker.getValue().toString());
    }


    @FXML
    private Label gender = new Label();
    @FXML
    private RadioButton male, female;
    public void getGender(ActionEvent e) {
        if (male.isSelected()) {
            gender.setText("male");
        }
        else if (female.isSelected()){
            gender.setText("female");
        }
        System.out.println(gender.getText());
    }


    public void registerButtonClicked(ActionEvent e) throws SQLException, ClassNotFoundException, IOException {
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();
        String rePassword = rePasswordTextField.getText();
        if (!password.equals(rePassword)) {
            rePasswordTextField.getStyleClass().add("error");
            Tooltip passwordTooltip = new Tooltip("Mật khẩu và xác nhận mật khẩu không khớp");
            rePasswordTextField.setTooltip(passwordTooltip);
            passwordTooltip.setAutoHide(false);
            return;
        }

        String email = emailTextField.getText();
        String name = nameTextField.getText();
        String birthday = birthdayLabel.getText();
        String genderStr = gender.getText();

        LoginController register = new LoginController();
        User u = new User(username, password, email, name, birthday, genderStr);

        String noti = register.signUp(u);

        Utils.showAlert(noti);
    }

    public void back(ActionEvent e) throws IOException {
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        URL url = Paths.get("src/main/java/dangnhap/dangnhap.fxml").toUri().toURL();
        Parent signInViewParent = FXMLLoader.load(url);
        Scene scene = new Scene(signInViewParent);
        stage.setScene(scene);
    }
}
