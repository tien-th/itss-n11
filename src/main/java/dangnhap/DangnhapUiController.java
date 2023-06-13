package dangnhap;


// DangnhapController.java is a controller for Dangnhap.fxml (see src\main\resources\dangnhap\Dangnhap.fxml)
// It is used by DangnhapApplication.java (see src\main\java\dangnhap\DangnhapApplication.java)

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.sql.SQLException;

public class DangnhapUiController {

    // Get data that user input and check if it is valid in dataset json file (src/main/java/data/user.json)
    // If valid, return true, else return false
    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField passwordTextField;

    // catch event for login button
    public void loginButtonClicked(ActionEvent e) throws SQLException, ClassNotFoundException, IOException {

        // get data from user input

//        String[] loginInfor = new String[2];
//        loginInfor[0] = usernameTextField.getText();
//        loginInfor[1] = passwordTextField.getText();
//        return loginInfor ;

        String username = usernameTextField.getText();
        String password = passwordTextField.getText();
        DangnhapController login = new DangnhapController();
        int role = login.checkLogin(username, password);
        // TODO --Trung : if role == -1 then show error message "Invalid username or password"
        if (role == -1) {
//            System.out.println("Invalid username or password");
            return ;
        }
        // TODO : end

        // TODO -- Tien : move to another scene if login success base on role (admin or user)

        // copilot write this
        if (role == 1) {
            System.out.println("Login success");
            // TODO : move to another scene if login success base on role (admin or user)
            // open a new screen in admin.fxml
            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            URL url = Paths.get("./src/main/java/dangnhap/admin.fxml").toUri().toURL();
            Parent adminViewParent = FXMLLoader.load(url);
            Scene scene = new Scene(adminViewParent);
            stage.setScene(scene);
        }
        else if (role == 2) {
            System.out.println("Login success");
            // TODO : move to another scene if login success base on role (admin or user)
            // open a new screen in user.fxml
            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            URL url = Paths.get("./src/main/java/dangnhap/user.fxml").toUri().toURL();
            Parent userViewParent = FXMLLoader.load(url);
            Scene scene = new Scene(userViewParent);
            stage.setScene(scene);
        }
        else {
            System.out.println("Login failed");
        }
        // end copilot write this
    }

    // catch event for register button
    // if user click register button, it will open a new window for user to register
    // open a new screen in dangky.fxml
    public void registerButtonClicked(ActionEvent e) throws IOException {
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        URL url = Paths.get("./src/main/java/dangnhap/dangky.fxml").toUri().toURL();
        Parent registerViewParent = FXMLLoader.load(url);
//        FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(getClass().getResource("/dangnhap/dangky.fxml"));
//        Parent registerViewParent = loader.load();
        Scene scene = new Scene(registerViewParent);
        stage.setScene(scene);
    }

}
