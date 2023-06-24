package dangkydichvu;

import entity.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import menu.UserUIController;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;

public class UserFuncBase {
    public User user = null ;
    public void setUser(User user) {
        System.out.println("set user " + user.getName() + " " + user.getRole());// fix bug
        this.user = user;
    }
    public void goBack(ActionEvent e ) throws IOException {
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        URL url = Paths.get("./src/main/java/menu/user.fxml").toUri().toURL();
        FXMLLoader loader = new FXMLLoader();
        Parent userViewParent = loader.load(url.openStream());
        Scene scene = new Scene(userViewParent);
        UserUIController userUIController =  loader.getController();
        userUIController.setUser(user);
        stage.setScene(scene);
    }
}
