package controller.user;

import controller.login.DangnhapUiController;
import entity.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import controller.pet.ViewPetUIController;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
public class UserUIController {
    // Xử lý sự kiện
    @FXML
    Label nameLabel = new Label();
    User user = null;

    public void setUser(User user) {
        this.user = user;
        nameLabel.setText(user.getName());
    }


    public void viewPet(ActionEvent e) throws IOException {
        //  : move to view pet scene
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        URL url = Paths.get("./src/main/resources/com/screen/viewPetScreen.fxml").toUri().toURL();
        FXMLLoader loader = new FXMLLoader();

        Parent adminViewParent = loader.load(url.openStream());
        ViewPetUIController viewPetUIController = loader.getController();
        System.out.println(user.getName() + " " + user.getRole());
        Scene scene = new Scene(adminViewParent);
        viewPetUIController.setUser(user);
        viewPetUIController.initialize(url, null);
        stage.setScene(scene);
    }

    public void dangKyTrongGiu(ActionEvent e) throws IOException {
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        System.out.println("asdasdas");
        URL url = Paths.get("./src/main/resources/com/screen/dangKyTrongGiuScreen.fxml").toUri().toURL();
        FXMLLoader loader = new FXMLLoader();
        Parent adminViewParent = loader.load(url.openStream());
        System.out.println("asdasdas");
        DangKyTrongGiuScreenController dangKyTrongGiuScreenController = loader.getController();
        System.out.println(user.getName() + "abcd " + user.getRole());
        Scene scene = new Scene(adminViewParent);
        dangKyTrongGiuScreenController.setUser(user);
        dangKyTrongGiuScreenController.initialize(url, null);
        stage.setScene(scene);
    }

    public void dangKyKham(ActionEvent e) throws IOException {
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        System.out.println("asdasdas");
        URL url = Paths.get("./src/main/resources/com/screen/dangKyKhamScreen.fxml").toUri().toURL();
        FXMLLoader loader = new FXMLLoader();
        Parent adminViewParent = loader.load(url.openStream());
        System.out.println("asdasdas");
        DangKyKhamScreenController dangKyKhamScreenController = loader.getController();
        System.out.println(user.getName() + "abcd " + user.getRole());
        Scene scene = new Scene(adminViewParent);
        dangKyKhamScreenController.setUser(user);
        dangKyKhamScreenController.initialize(url, null);
        stage.setScene(scene);
    }

    public void dangKyVs(ActionEvent e) throws IOException {
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        System.out.println("asdasdas");
        URL url = Paths.get("./src/main/resources/com/screen/dangKyVSScreen.fxml").toUri().toURL();
        FXMLLoader loader = new FXMLLoader();
        Parent adminViewParent = loader.load(url.openStream());
        System.out.println("asdasdas");
        DangKyVSScreenController dangKyVSScreenController = loader.getController();
        System.out.println(user.getName() + "abcd " + user.getRole());
        Scene scene = new Scene(adminViewParent);
        dangKyVSScreenController.setUser(user);
        dangKyVSScreenController.initialize(url, null);
        stage.setScene(scene);
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
//    public <UpdateInfoController> void updateInfo(ActionEvent event) throws IOException {
//
//        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        URL url = Paths.get("./src/main/java/dangnhap/updateInfo.fxml").toUri().toURL();
//        FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(url);
//        Parent userViewParent = loader.load(url.openStream());
//        UpdateInfoController updateInfoController = loader.getController();
//        updateInfoController.setUser(user);
//        Scene scene = new Scene(userViewParent);
//        stage.setScene(scene);
//    }
//}

}
