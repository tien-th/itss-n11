package utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

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



}
