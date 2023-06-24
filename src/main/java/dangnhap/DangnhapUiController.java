package dangnhap;


// DangnhapController.java is a controller for Dangnhap.fxml (see src\main\resources\dangnhap\Dangnhap.fxml)
// It is used by DangnhapApplication.java (see src\main\java\dangnhap\DangnhapApplication.java)

import entity.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import menu.UserUIController;

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
        User u = login.checkLogin(username, password);

        if (u == null) {
            System.out.println("Invalid username or password");
            // show error message "Invalid username or password" in a alert box on interface
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid username or password");
            alert.setContentText("Please try again");
            alert.showAndWait();
            return ;
        }

        // TODO -- Tien : move to another scene if login success base on role (admin or user)

        // copilot write this
        if (u.getRole() == 1 ) {
            System.out.println("Move to admin screen");
            // TODO -- Tien   : move to another scene if login success base on role (admin or user)
            // open a new screen in admin.fxml
             Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            URL url = Paths.get("./src/main/java/menu/admin.fxml").toUri().toURL();
            Parent adminViewParent = FXMLLoader.load(url);
            Scene scene = new Scene(adminViewParent);
            UserUIController userUIController = (UserUIController) adminViewParent.getOnScroll();
            userUIController.setUser(u);
            stage.setScene(scene);
        }
        else if (u.getRole() == 0) {
            System.out.println("Move to user screen");
            // TODO -- done : move to another scene if login success base on role: user
            // open a new screen in user.fxml
            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            URL url = Paths.get("./src/main/java/menu/user.fxml").toUri().toURL();
            FXMLLoader loader = new FXMLLoader();
            Parent userViewParent = loader.load(url.openStream());

            Scene scene = new Scene(userViewParent);
            UserUIController userUIController =  loader.getController();
            userUIController.setUser(u);
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
