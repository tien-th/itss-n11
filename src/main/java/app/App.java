package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;
import java.nio.file.Paths;


public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            URL url = Paths.get("src/main/resources/com/screen/dangnhap.fxml").toUri().toURL();
            Parent root = FXMLLoader.load(url);

            Scene scene = new Scene(root);
//            String css = this.getClass().getResource("/temp.css").toExternalForm();
//            scene.getStylesheets().add(css);
            primaryStage.setScene(scene);
            primaryStage.show();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
