package quanly;
import dangkydichvu.UserFuncBase;
import entity.Medical;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class MedicalMUIController extends UserFuncBase  implements Initializable {
    @FXML
    private TableView<Medical> medicalTableView;
    @FXML
    private TableColumn<Medical, Integer> thuocID;
    @FXML
    private TableColumn<Medical, String> tenThuoc;
    @FXML
    private TableColumn<Medical, String> nhomThuoc;
    @FXML
    private TableColumn<Medical, Integer> soLuong;
    @FXML
    private TableColumn<Medical, String> nhaSx;
    @FXML
    private TableColumn<Medical, String> hsd;

    private ObservableList<Medical> medicalList;
    MedicalController medicalController = new MedicalController();


    @Override
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {

        try {
            medicalController.getMedicalList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        medicalList = FXCollections.observableArrayList(medicalController.medicalList);
        thuocID.setCellValueFactory(new PropertyValueFactory<Medical, Integer>("thuocId"));
        tenThuoc.setCellValueFactory(new PropertyValueFactory<Medical, String>("tenThuoc"));
        nhomThuoc.setCellValueFactory(new PropertyValueFactory<Medical, String>("nhomThuoc"));
        soLuong.setCellValueFactory(new PropertyValueFactory<Medical, Integer>("soLuong"));
        nhaSx.setCellValueFactory(new PropertyValueFactory<Medical, String>("nhaSx"));
        hsd.setCellValueFactory(new PropertyValueFactory<Medical, String>("hsd"));
        medicalTableView.setItems(medicalList);


    }

}
