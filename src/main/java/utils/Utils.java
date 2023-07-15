package utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;

public class Utils {
    static public Scene showScreen( String fxmlPath) throws IOException {
        URL url = Paths.get(fxmlPath).toUri().toURL();
        FXMLLoader loader = new FXMLLoader();
        Parent userViewParent = loader.load(url.openStream());
        Scene scene = new Scene(userViewParent);
        return scene;
    }
    public static void showAlert(String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(null);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }




}
