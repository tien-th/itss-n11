package dangnhap;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;
import java.nio.file.Paths;
import java.util.Objects;


public class TestLogin extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            URL url = Paths.get("./src/main/java/dangnhap/dangnhap.fxml").toUri().toURL();
            Parent root = FXMLLoader.load(url);

            Scene scene = new Scene(root);
           // theem file sytle.css
//            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("l.css")).toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.show();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
