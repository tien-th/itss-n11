package view.services;

import model.entity.Appoint;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import controller.KhamController;
import view.user.ActorUi;
import view.user.DangnhapUiController;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ListPetUiToExamineController extends ActorUi implements Initializable {

    @FXML
    private TableView<Appoint> appointTableView ;
    @FXML
    private TableColumn<Appoint, Integer> petIdColumn;
    @FXML
    private TableColumn<Appoint, String> datetimeColumn;
    @FXML
    private TableColumn<Appoint, String> time_slotColumn;
    private ObservableList<Appoint> appointList;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            KhamController.getLichKham();
        } catch (Exception e) {
            e.printStackTrace();
        }
        appointList = FXCollections.observableArrayList(KhamController.appointList);
        petIdColumn.setCellValueFactory(new PropertyValueFactory<Appoint, Integer>("pet_id"));
        datetimeColumn.setCellValueFactory(new PropertyValueFactory<Appoint, String>("day"));
        time_slotColumn.setCellValueFactory(new PropertyValueFactory<Appoint, String>("time"));
        appointTableView.setItems(appointList);
    }


    public void examine(ActionEvent event) {
        Appoint selectedAppoint = appointTableView.getSelectionModel().getSelectedItem();
        if (selectedAppoint == null) {
            return;
        }
        int selectedAppointId = selectedAppoint.getPet_id();
        String day = selectedAppoint.getDay();
        int time = selectedAppoint.getTime_slot();
        // move to examine page with selected appoint info
        try {
            KhamController.getPet(selectedAppointId);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            URL url = Paths.get("./src/main/resources/com/screen/khamPet.fxml").toUri().toURL();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(url);
            Parent userViewParent = loader.load(url.openStream());

            Scene scene = new Scene(userViewParent);
            stage.setScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
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
