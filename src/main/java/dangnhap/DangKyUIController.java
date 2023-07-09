package dangnhap;

import dangkydichvu.UserFuncBase;
import entity.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import menu.AdminUIController;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.sql.SQLException;


public class DangKyUIController extends UserFuncBase {
    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField passwordTextField;
    @FXML
    private TextField rePasswordTextField;

    @FXML
    private Label errorLabel = new Label();

    // TODO --Long : check if email is valid (action event)
    // để sau
    @FXML
    private TextField emailTextField;

//    public void checkEmail(ActionEvent Cage) {
//        String email = emailTextField.getText();
//        if (email.contains("@")) {
//            System.out.println("Email hợp lệ");
//        }
//        else {
//            System.out.println("Email không hợp lệ");
//        }
//    }

    @FXML
    private TextField nameTextField;

    @FXML
    private DatePicker birthdayPicker ;
    @FXML
    private Label birthdayLabel = new Label();


    public void getBirthday(ActionEvent e) {
        birthdayLabel.setText(birthdayPicker.getValue().toString());
        System.out.println(birthdayPicker.getValue().toString());
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



    public void registerButtonClicked(ActionEvent e) throws SQLException, ClassNotFoundException {
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();
        String rePassword = rePasswordTextField.getText();
        if (!password.equals(rePassword)) {
            // TODO
            errorLabel.setText("not match");

//            System.out.println("Mật khẩu không trùng khớp");
            return ;
        }

        String email = emailTextField.getText();
        String name = nameTextField.getText();
        String birthday = birthdayLabel.getText();
        String genderStr = gender.getText();

        DangnhapController register = new DangnhapController();
        User u = new User(username, password, email, name, birthday, genderStr);
        register.saveUserToDb(u);

    }
    @FXML
    private Button back;
    public void back(ActionEvent e) throws IOException {
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        URL url = Paths.get("src/main/java/dangnhap/dangnhap.fxml").toUri().toURL();
        Parent signInViewParent = FXMLLoader.load(url);
        Scene scene = new Scene(signInViewParent);
        stage.setScene(scene);
    }


}
