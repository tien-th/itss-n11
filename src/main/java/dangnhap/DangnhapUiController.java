package dangnhap;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import menu.ActorUi;
import entity.User;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.sql.SQLException;

public class DangnhapUiController {


    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordField;

    // catch event for login button
    public void loginButtonClicked(ActionEvent e) throws SQLException, ClassNotFoundException, IOException {

        String username = usernameTextField.getText();
        String password = passwordField.getText();

        LoginController login = new LoginController();
        User u = login.getUser(username, password);

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

        String urlStr = LoginController.getUrlScreenActor(u);
        URL url = Paths.get(urlStr).toUri().toURL();
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        Parent userViewParent = loader.load(url.openStream());
        ActorUi actorUiController =  loader.getController();
        actorUiController.setUser(u);
        Scene scene = new Scene(userViewParent);
        stage.setScene(scene);
    }

    public void registerButtonClicked(ActionEvent e) throws IOException {
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        URL url = Paths.get("./src/main/java/dangnhap/dangky.fxml").toUri().toURL();
        Parent registerViewParent = FXMLLoader.load(url);
        Scene scene = new Scene(registerViewParent);
        stage.setScene(scene);
    }

}
