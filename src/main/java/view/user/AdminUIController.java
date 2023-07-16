package view.user;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import utils.connection.DbConnection;
import view.services.CageMUIController;
import view.services.CareServicesMUIController;
import view.services.MedicalMUIController;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AdminUIController extends ActorUi implements Initializable {

    public void viewPetUI(ActionEvent actionEvent) throws IOException {
        UserUIController userUIController = new UserUIController();
        userUIController.setUser(user);
        userUIController.viewPet(actionEvent);
    }

    public void viewUserUI(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        URL url = Paths.get("./src/main/resources/com/screen/userMUI.fxml").toUri().toURL();
        FXMLLoader loader = new FXMLLoader();
        Parent userViewParent = loader.load(url.openStream());
        Scene scene = new Scene(userViewParent);
        UserMUIController userMUIController = loader.getController();
        userMUIController.setUser(user);
//        Scene scene = Utils.showScreen("./src/main/java/quanly/userMUI.fxml");
        stage.setScene(scene);
    }

    public void viewManageAppointmentsUI(ActionEvent actionEvent) throws IOException {
        // TODO refactor
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        URL url = Paths.get("./src/main/resources/com/screen/appointUI.fxml").toUri().toURL();
        FXMLLoader loader = new FXMLLoader();
        Parent userViewParent = loader.load(url.openStream());

        Scene scene = new Scene(userViewParent);
        AppointUIController servicesMUIController = loader.getController();
        servicesMUIController.setUser(user);

        stage.setScene(scene);

    }

    public void viewManageCareUI(ActionEvent actionEvent) throws IOException {
// TODO
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        URL url = Paths.get("./src/main/resources/com/screen/careServicesMUI.fxml").toUri().toURL();
        FXMLLoader loader = new FXMLLoader();
        Parent userViewParent = loader.load(url.openStream());

        Scene scene = new Scene(userViewParent);
        CareServicesMUIController servicesMUIController = loader.getController();
        servicesMUIController.setUser(user);

        stage.setScene(scene);

    }

    public void viewManageMedicineUI(ActionEvent actionEvent) throws IOException {
        // TODO
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        URL url = Paths.get("./src/main/resources/com/screen/medicalMUI.fxml").toUri().toURL();
        FXMLLoader loader = new FXMLLoader();
        Parent userViewParent = loader.load(url.openStream());

        Scene scene = new Scene(userViewParent);
        MedicalMUIController medicalMUIController = loader.getController();
        medicalMUIController.setUser(user);
        stage.setScene(scene);
    }

    public void viewManageCustodialUI(ActionEvent actionEvent) {
        // TODO
    }

    public void viewStatisticalUI(ActionEvent actionEvent) {
        // TODO
    }

    public void viewManageCage(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        URL url = Paths.get("./src/main/resources/com/screen/cageMUI.fxml").toUri().toURL();
        FXMLLoader loader = new FXMLLoader();
        Parent userViewParent = loader.load(url.openStream());
        Scene scene = new Scene(userViewParent);
        CageMUIController cageMUIController = loader.getController();
        cageMUIController.setUser(user);

        stage.setScene(scene);
    }

    @FXML
    Label totalUser = new Label();
    @FXML
    Label totalPet = new Label();
    @FXML
    Label totalCage = new Label();
    @FXML
    Label totalCare = new Label();
    @FXML
    Label totalMedicine = new Label();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        displayTotal();
    }

    public int userTotatUsed() throws SQLException, ClassNotFoundException {
        int total = 0;
        PreparedStatement ps = null;
        String Sql = "SELECT COUNT(*) FROM public.user";
        ps = DbConnection.openConnection().prepareStatement(Sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            total = rs.getInt("COUNT");

        }
        return total;

    }

    public int petTotatUsed() throws SQLException, ClassNotFoundException {
        int total = 0;
        PreparedStatement ps = null;
        String Sql = "SELECT COUNT(*) FROM pet";
        ps = DbConnection.openConnection().prepareStatement(Sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            total = rs.getInt("COUNT");
        }
        return total;
    }

    public int cageTotatUsed() throws SQLException, ClassNotFoundException {
        int total = 0;
        PreparedStatement ps = null;
        String Sql = "SELECT COUNT(*) FROM lodging";
        ps = DbConnection.openConnection().prepareStatement(Sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            total = rs.getInt("COUNT");
        }
        return total;
    }
    public int thuocTotatUsed() throws SQLException, ClassNotFoundException {
        int total = 0;
        PreparedStatement ps = null;
        String Sql = "SELECT COUNT(*) From thuoc";
        ps = DbConnection.openConnection().prepareStatement(Sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            total = rs.getInt("COUNT");
        }
        return total;
    }

    public void displayTotal(){
        try {
            totalUser.setText(String.valueOf(userTotatUsed()));
            totalPet.setText(String.valueOf(petTotatUsed()));
            totalCage.setText(String.valueOf(cageTotatUsed()));
//            totalCare.setText(String.valueOf(adminController.careTotatUsed()));
            totalMedicine.setText(String.valueOf(thuocTotatUsed()));

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();}
    }
}

