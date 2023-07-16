package view.user;

import javafx.event.ActionEvent;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;
import view.pet.ViewPetUIController;
import view.services.DangKyKhamScreenController;
import view.services.DangKyTrongGiuScreenController;
import view.services.DangKyVSScreenController;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
public class UserUIController extends ActorUi {

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

}

