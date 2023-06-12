package dangnhap;

import entity.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class DangKyUIController {

    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField passwordTextField;
    @FXML
    private TextField rePasswordTextField;

    // TODO: check if email is valid (action event)
    @FXML
    private TextField emailTextField;

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
            // TODO: show error message
            System.out.println("Mật khẩu không trùng khớp");
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
}
