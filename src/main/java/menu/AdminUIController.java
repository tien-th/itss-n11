package menu;

import entity.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

public class AdminUIController {
    @FXML
    Label nameLabel = new Label() ;
    User user = null ;
    public void setUser(User user) {
        this.user = user ;
        nameLabel.setText(user.getName()); // TODO : refactor
    }

    public void signOut(ActionEvent event) {
        // TODO
    }
    public void viewUserUI(ActionEvent actionEvent) {
        // TODO
    }

    public void viewPetUI(ActionEvent actionEvent) throws IOException {
        UserUIController userUIController = new UserUIController();
        // TODO : refactor, inherritance or interface
        userUIController.setUser(user);
        userUIController.viewPet(actionEvent);
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
