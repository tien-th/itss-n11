package menu;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;

public class UserUIController {

    // Xử lý sự kiện

    public void viewPet(ActionEvent e) throws IOException {
        //  : move to view pet scene
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        URL url = Paths.get("./src/main/java/menu/viewPetScreen.fxml").toUri().toURL();
        Parent adminViewParent = FXMLLoader.load(url);
        Scene scene = new Scene(adminViewParent);
        stage.setScene(scene);
        // TODO: thiet ke man hinh xem thong tin pet trong file viewPetScreen.fxml
    }

    public void dangKyTrongGiu(ActionEvent e) throws IOException {
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        URL url = Paths.get("./src/main/java/menu/dangKyTrongGiuScreen.fxml").toUri().toURL();
        Parent adminViewParent = FXMLLoader.load(url);
        Scene scene = new Scene(adminViewParent);
        stage.setScene(scene);
        // TODO -- Long : thiet ke man hinh dang ky trong giu trong file dangKyTrongGiuScreen.fxml
    }
    public void dangKyKham(ActionEvent e) throws IOException {
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        URL url = Paths.get("./src/main/java/menu/dangKyKhamScreen.fxml").toUri().toURL();
        Parent adminViewParent = FXMLLoader.load(url);
        Scene scene = new Scene(adminViewParent);
        stage.setScene(scene);
        // TODO -- Trung : thiet ke man hinh dang ky kham trong file dangKyKhamScreen.fxml
    }

    public void dangKyVs(ActionEvent e) throws IOException {
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        URL url = Paths.get("./src/main/java/menu/dangKyVsScreen.fxml").toUri().toURL();
        Parent adminViewParent = FXMLLoader.load(url);
        Scene scene = new Scene(adminViewParent);
        stage.setScene(scene);
        // TODO -- Tuan : thiet ke man hinh dang ky vs trong file dangKyVsScreen.fxml
    }

}
