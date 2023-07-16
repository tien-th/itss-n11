package controller.quanly;
import controller.user.ScreenHandler;

import entity.Medical;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import repository.quanly.MedicalController;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class MedicalMUIController extends ScreenHandler implements Initializable {
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
@FXML
    private TableColumn<Medical, Integer> price;


    private ObservableList<Medical> medicalList;
    MedicalController medicalController = new MedicalController();


    @Override
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        try {
            showdata();
            searchMedical();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private TextField searchTextField;

    public void searchMedical() throws SQLException, ClassNotFoundException {
        FilteredList<Medical> filteredData = new FilteredList<>(medicalList, p -> true);
        searchTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(medical -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (medical.getTenThuoc().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                }else if (medical.getNhomThuoc().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                }else if(medical.getNhaSx().toLowerCase().indexOf(lowerCaseFilter) != -1){
                    return true;
                }//id thuoc match search textfields
                // )
                else if (String.valueOf(medical.getThuocId()).indexOf(lowerCaseFilter)!=-1)
                    return true;
                else
                return false;
            });
        });
        SortedList<Medical> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(medicalTableView.comparatorProperty());
        medicalTableView.setItems(sortedData);
    }

    public void addMedical(ActionEvent event) throws SQLException {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Thêm thuốc");
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.APPLY, ButtonType.CANCEL);
        TextField tenThuoc = new TextField();
        TextField nhomThuoc = new TextField();
        TextField soLuong = new TextField();
        TextField nhaSx = new TextField();
        DatePicker hsd = new DatePicker();
        TextField price = new TextField();

        GridPane gridPane = new GridPane();
        gridPane.add(new Label("Tên thuốc"), 0, 0);
        gridPane.add(tenThuoc, 1, 0);
        gridPane.add(new Label("Nhóm thuốc"), 0, 1);
        gridPane.add(nhomThuoc, 1, 1);
        gridPane.add(new Label("Số lượng"), 0, 2);
        gridPane.add(soLuong, 1, 2);
        gridPane.add(new Label("Nhà sản xuất"), 0, 3);
        gridPane.add(nhaSx, 1, 3);
        gridPane.add(new Label("Hạn sử dụng"), 0, 4);
        gridPane.add(hsd, 1, 4);
        gridPane.add(new Label("Giá"), 0, 5);
        gridPane.add(price, 1, 5);
        dialog.getDialogPane().setContent(gridPane);
        dialog.showAndWait();
        if (dialog.getResult() == ButtonType.APPLY) {
//            int thuocId = medicalController.medicalList.get(medicalController.medicalList.size() - 1).getThuocId() + 1;
//            Medical medical = new Medical(thuocId,tenThuoc.getText(), nhomThuoc.getText(), Integer.parseInt(soLuong.getText()), nhaSx.getText(), Date.valueOf(hsd.getValue()), Integer.parseInt(price.getText()));
            Medical m = medicalController.add(tenThuoc.getText(), nhomThuoc.getText(), Integer.parseInt(soLuong.getText()), nhaSx.getText(), Date.valueOf(hsd.getValue()), Integer.parseInt(price.getText()));
            medicalList.add(m);
            medicalTableView.setItems(medicalList);
            medicalTableView.refresh();
        }
    }

    public void deleteMedical(ActionEvent event) throws SQLException, ClassNotFoundException {
        Medical selectedMedical = medicalTableView.getSelectionModel().getSelectedItem();
        if (selectedMedical == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Lỗi");
            alert.setHeaderText("Không có thuốc được chọn");
            alert.setContentText("Vui lòng chọn thuốc");
            alert.showAndWait();
            return;
        }else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Xác nhận");
            alert.setHeaderText("Xác nhận xóa thuốc");
            alert.setContentText("Bạn có chắc chắn muốn xóa thuốc này?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                medicalController.delete(selectedMedical);
                medicalList.remove(selectedMedical);
                medicalTableView.setItems(medicalList);
                medicalTableView.refresh();
            }
        }
    }

    public void updateMedical(ActionEvent event) throws SQLException, ClassNotFoundException {
        Medical selectedMedical = medicalTableView.getSelectionModel().getSelectedItem();
        if (selectedMedical == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Lỗi");
            alert.setHeaderText("Không có thuốc được chọn");
            alert.setContentText("Vui lòng chọn thuốc");
            alert.showAndWait();
            return;
        }
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Cập nhật thông tin thuốc");
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.APPLY, ButtonType.CANCEL);
        TextField tenThuoc = new TextField(selectedMedical.getTenThuoc());
        TextField nhomThuoc = new TextField(selectedMedical.getNhomThuoc());
        TextField soLuong = new TextField(String.valueOf(selectedMedical.getSoLuong()));
        TextField nhaSx = new TextField(selectedMedical.getNhaSx());
        // chon ngay han su dung javafx
        DatePicker hsd = new DatePicker();
        TextField price = new TextField(String.valueOf(selectedMedical.getPrice()));

        hsd.setValue(selectedMedical.getHsd().toLocalDate());

        GridPane grid = new GridPane();
        grid.add(new Label("Tên thuốc:"), 1, 1);
        grid.add(tenThuoc, 2, 1);
        grid.add(new Label("Nhóm thuốc:"), 1, 2);
        grid.add(nhomThuoc, 2, 2);
        grid.add(new Label("Số lượng:"), 1, 3);
        grid.add(soLuong, 2, 3);
        grid.add(new Label("Nhà sản xuất:"), 1, 4);
        grid.add(nhaSx, 2, 4);
        grid.add(new Label("Hạn sử dụng:"), 1, 5);
        grid.add(hsd, 2, 5);

        grid.add(new Label("Giá:"), 1, 6);
        grid.add(price, 2, 6);

        dialog.getDialogPane().setContent(grid);
        dialog.showAndWait();
        if (dialog.getResult() == ButtonType.APPLY) {
            selectedMedical.setTenThuoc(tenThuoc.getText());
            selectedMedical.setNhomThuoc(nhomThuoc.getText());
            selectedMedical.setSoLuong(Integer.parseInt(soLuong.getText()));
            selectedMedical.setNhaSx(nhaSx.getText());
            selectedMedical.setHsd(java.sql.Date.valueOf(hsd.getValue()));
            selectedMedical.setPrice(Integer.parseInt(price.getText()));

            medicalController.update(selectedMedical);

            boolean result = medicalController.update(selectedMedical);
            if (result) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Thông báo");
                alert.setHeaderText("Cập nhật thông tin thuốc thành công");
                alert.setContentText("Vui lòng chọn thuốc");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Lỗi");
                alert.setHeaderText("Cập nhật thông tin thuốc thất bại");
                alert.setContentText("Vui lòng chọn thuốc");
                alert.showAndWait();
            }
            medicalTableView.refresh();
        }

    }
   @FXML
    public void showdata() throws SQLException, ClassNotFoundException {

       medicalList = FXCollections.observableArrayList(medicalController.getMedicalList());
       thuocID.setCellValueFactory(new PropertyValueFactory<Medical, Integer>("thuocId"));
       tenThuoc.setCellValueFactory(new PropertyValueFactory<Medical, String>("tenThuoc"));
       nhomThuoc.setCellValueFactory(new PropertyValueFactory<Medical, String>("nhomThuoc"));
       soLuong.setCellValueFactory(new PropertyValueFactory<Medical, Integer>("soLuong"));
       nhaSx.setCellValueFactory(new PropertyValueFactory<Medical, String>("nhaSx"));
       hsd.setCellValueFactory(new PropertyValueFactory<Medical, String>("hsd"));
       price.setCellValueFactory(new PropertyValueFactory<Medical, Integer>("price"));
       medicalTableView.setItems(medicalList);
       System.out.println("size" +  medicalList.size());
   }

}
