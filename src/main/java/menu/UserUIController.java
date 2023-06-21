package menu;

import dangkydichvu.DangKyKhamScreenController;
import entity.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import view_pet.ViewPetUIController;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
public class UserUIController {

    // Xử lý sự kiện
    @FXML
    Label nameLabel ;
    User user = null ;
    public void setUser(User user) {
        this.user = user;
        nameLabel.setText(user.getName());
    }


    public void viewPet(ActionEvent e) throws IOException {
        //  : move to view pet scene
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        URL url = Paths.get("./src/main/java/view_pet/viewPetScreen.fxml").toUri().toURL();
        FXMLLoader loader = new FXMLLoader();

        Parent adminViewParent = loader.load(url.openStream());
        ViewPetUIController viewPetUIController = loader.getController();
        System.out.println(user.getName() + " " +  user.getRole());
        Scene scene = new Scene(adminViewParent);
        viewPetUIController.setUser(user);
        viewPetUIController.initialize(url, null);
        stage.setScene(scene);
        // TODO: thiet ke man hinh xem thong tin pet trong file viewPetScreen.fxml
    }

    public void dangKyTrongGiu(ActionEvent e) throws IOException {
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        System.out.println("asdasdas");
        URL url = Paths.get("./src/main/java/menu/dangKyTrongGiuScreen.fxml").toUri().toURL();

        Parent adminViewParent = FXMLLoader.load(url);
        Scene scene = new Scene(adminViewParent);
        stage.setScene(scene);
        // TODO -- Long : thiet ke man hinh dang ky trong giu trong file dangKyTrongGiuScreen.fxml
    }
    public void dangKyKham(ActionEvent e) throws IOException {
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        System.out.println("asdasdas");
        URL url = Paths.get("./src/main/java/dangkydichvu/dangKyKhamScreen.fxml").toUri().toURL();
        FXMLLoader loader = new FXMLLoader();
        Parent adminViewParent = loader.load(url.openStream());
        System.out.println("asdasdas");
        DangKyKhamScreenController dangKyKhamScreenController = loader.getController();
        System.out.println(user.getName() + "abcd " +  user.getRole());
        Scene scene = new Scene(adminViewParent);
        dangKyKhamScreenController.setUser(user);
        dangKyKhamScreenController.initialize(url, null);
        stage.setScene(scene);






//        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
//        URL url = Paths.get("./src/main/java/menu/dangKyKhamScreen.fxml").toUri().toURL();
//        Parent adminViewParent = FXMLLoader.load(url);
//        Scene scene = new Scene(adminViewParent);
//        stage.setScene(scene);
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
