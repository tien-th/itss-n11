package controller.user;

import controller.quanly.AdminUIController;
import entity.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;

public class ScreenHandler {
    public User user = null ;
    public void setUser(User user) {
        System.out.println("set user " + user.getName() + " " + user.getRole());// fix bug
        this.user = user;
    }
    public void goBack(ActionEvent e ) throws IOException {

        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        URL url = null ;
        FXMLLoader loader = new FXMLLoader();
//        Objects userUIController = null;
        if (user.getRole() == 1) {
            url = Paths.get("./src/main/resources/com/screen/adminUI.fxml").toUri().toURL();
            Parent userViewParent = loader.load(url.openStream());
            Scene scene = new Scene(userViewParent);
            AdminUIController userUIController =  loader.getController();
            userUIController.setUser(user);
            stage.setScene(scene);
        }
        else {
            url = Paths.get("./src/main/resources/com/screen/user.fxml").toUri().toURL();
            Parent userViewParent = loader.load(url.openStream());
            Scene scene = new Scene(userViewParent);
            UserUIController userUIController =  loader.getController();
            userUIController.setUser(user);
            stage.setScene(scene);
        }
    }
}
