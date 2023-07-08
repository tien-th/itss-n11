package quanly;

import dangkydichvu.UserFuncBase;
import entity.Cage;
import entity.Pet;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.sql.SQLException;
import java.util.IllegalFormatConversionException;
import java.util.Optional;
import java.util.ResourceBundle;


public class CageMUIController extends UserFuncBase implements Initializable {
    @FXML
    private TableView<Cage> cageTableView;
    @FXML
    private TableColumn<Cage, Integer> cageIdColumn;
    @FXML
    private TableColumn<Cage, String> cageStatusColumn;
//    @FXML
//    private TableColumn<Cage, Integer> petIdcolumn;

    private ObservableList<Cage> cageList;
    CageController cageController = new CageController();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            cageController.getCageList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        cageList = FXCollections.observableArrayList(cageController.cageList);

        cageIdColumn.setCellValueFactory(new PropertyValueFactory<Cage, Integer>("id_cage"));

        cageStatusColumn.setCellValueFactory(cellData -> {
            String status = cellData.getValue().isStatus() == 1 ? "đã sử dụng" : "trống";
            return new SimpleStringProperty(status);
        });
//        petIdcolumn.setCellValueFactory(new PropertyValueFactory<Cage, Integer>("Pet.getId()"));


        cageTableView.setItems(cageList);
    }

    public void deleteCage(ActionEvent event) throws SQLException, ClassNotFoundException {
        if (user.getRole() != 1) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText("You are not allowed to delete cage");
            alert.showAndWait();
            return;
        }
        Cage cage = cageTableView.getSelectionModel().getSelectedItem();
        if (cage == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText("Please choose a cage");
            alert.showAndWait();
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete cage");
        alert.setHeaderText("Delete cage");
        alert.setContentText("Are you sure to delete this cage?");
        Optional<ButtonType> option = alert.showAndWait();
        if (option.get() == ButtonType.OK) {
            cageController.deleteCage(cage.getId_cage());
            cageList.remove(cage);
        }
    }

    public void updateCage(ActionEvent event) throws SQLException, ClassNotFoundException {
        Cage selectedCage = cageTableView.getSelectionModel().getSelectedItem();
        if (selectedCage == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText("Please choose a cage");
            alert.showAndWait();
            return;
        }
        Dialog<Cage> dialog = new Dialog<>();
        dialog.setTitle("Update cage");

        ButtonType updateButtonType = new ButtonType("Update", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(updateButtonType, ButtonType.CANCEL);

        // create textfield for the updated information
        TextField cageIdTextField = new TextField(Integer.toString(selectedCage.getId_cage()));
        TextField cageStatusTextField = new TextField(Integer.toString(selectedCage.isStatus()));

       // hiện ra màn hình để nhập thông tin
        GridPane grid = new GridPane();
        grid.add(new Label("Cage ID:"), 1, 1);
        grid.add(cageIdTextField, 2, 1);
        grid.add(new Label("Cage Status:"), 1, 2);
        grid.add(cageStatusTextField, 2, 2);

        dialog.getDialogPane().setContent(grid);

        // convert the result to a cage when the update button is clicked.
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == updateButtonType) {
                try {
                    return new Cage(Integer.parseInt(cageIdTextField.getText()), Integer.parseInt(cageStatusTextField.getText()));
                } catch (NumberFormatException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Error");
                    alert.setContentText("Please enter a number");
                    alert.showAndWait();
                    return null;
                }
            }
            return null;
        });

        Optional<Cage> result = dialog.showAndWait();

        result.ifPresent(cage -> {
            try {
                CageController.updateCage(cage);
                cageList.set(cageList.indexOf(selectedCage), cage);
                cageTableView.refresh();
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

    }
}






