package menu;

import dangnhap.DangnhapUiController;
import entity.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import quanly.UserMUIController;
import utils.Utils;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;

public class AdminUIController {
    @FXML
    Label nameLabel = new Label() ;
    User user = null ;
    public void setUser(User user) {
        this.user = user ;
        nameLabel.setText(user.getName()); // TODO : refactor
    }

    public void signOut(ActionEvent event) throws IOException {
        // TODO --- passed
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        URL url = Paths.get("./src/main/java/dangnhap/dangnhap.fxml").toUri().toURL();
        FXMLLoader loader = new FXMLLoader();
        Parent userViewParent = loader.load(url.openStream());
        DangnhapUiController dangnhapUiController = loader.getController();
        Scene scene = new Scene(userViewParent);
        stage.setScene(scene);
    }


    public void viewPetUI(ActionEvent actionEvent) throws IOException {
        UserUIController userUIController = new UserUIController();
        userUIController.setUser(user);
        userUIController.viewPet(actionEvent);
    }

    public void viewUserUI(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        URL url = Paths.get("./src/main/java/quanly/userMUI.fxml").toUri().toURL();
        FXMLLoader loader = new FXMLLoader();
        Parent userViewParent = loader.load(url.openStream());
        Scene scene = new Scene(userViewParent);
        UserMUIController  userMUIController = loader.getController();
        userMUIController.setUser(user);
//        Scene scene = Utils.showScreen("./src/main/java/quanly/userMUI.fxml");
        stage.setScene(scene);
    }
    public void viewManageAppointmentsUI(ActionEvent actionEvent) {
        // TODO
    }
    public void viewManageCleaningUI(ActionEvent actionEvent) {
// TODO
    }
    public void viewManageMedicineUI(ActionEvent actionEvent) {
        // TODO
    }
    public void viewManageCustodialUI(ActionEvent actionEvent) {
        // TODO
    }
    public void viewStatisticalUI(ActionEvent actionEvent) {
        // TODO
    }
}
