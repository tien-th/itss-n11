package view.user;


import model.entity.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;


public class ActorUi {

    @FXML
    public Label nameLabel = new Label();
    public User user = null;

    public void setUser(User user) {
        this.user = user;
        nameLabel.setText(user.getName()); // TODO : refactor
    }

    public void signOut(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        URL url = Paths.get("./src/main/resources/com/screen/dangnhap.fxml").toUri().toURL();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(url);
        Parent userViewParent = loader.load(url.openStream());
        DangnhapUiController dangnhapUiController = loader.getController();
        Scene scene = new Scene(userViewParent);
        stage.setScene(scene);
    }


}
