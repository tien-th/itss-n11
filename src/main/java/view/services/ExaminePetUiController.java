package view.services;

import model.entity.Medicine;
import model.entity.Pet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import controller.KhamController;
import view.ScreenHandler;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ExaminePetUiController extends ScreenHandler implements Initializable {

    @FXML
    private TextField petNameTextField;
    @FXML
    private TextField petCategoryTextField;
    @FXML
    private TextField petAgeTextField;
    @FXML
    private TextArea symptomTextArea;
    @FXML
    private TextArea diagnosticTextArea;
    @FXML
    private TextArea adviceTextArea;
    @FXML
    private ComboBox<String> medicineComboBox;
    @FXML
    private TextField quantityTextField;
    @FXML
    private Button prescribeButton;
    @FXML
    private ListView<String> prescribedMedicineListView;
    @FXML
    private Button deleteMedicineButton = new Button() ;

    private ObservableList<String> medicineList;
    private ObservableList<String> prescribedMedicineList;

    private int recordId = 0;

    Pet pet;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Access the pet object from KhamController
        pet = KhamController.pet;
        recordId = KhamController.getRecordId();
        try {
            KhamController.saveRecord(recordId, pet.getId() ,"", "", "");
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        // Set the pet information in the text fields
        petNameTextField.setText(pet.getName());
        petCategoryTextField.setText(pet.getCategory());
        petAgeTextField.setText(String.valueOf(pet.getAge()));


        // Initialize the medicine list and prescribed medicine list
        getListMedicineName();
        medicineList = FXCollections.observableArrayList(listMedicineName);
        prescribedMedicineList = FXCollections.observableArrayList();

        // Populate the medicine combo box
        medicineComboBox.setItems(medicineList);

        // Set up the prescribed medicine list view
        prescribedMedicineListView.setItems(prescribedMedicineList);

        // Disable the delete button initially
        deleteMedicineButton.setDisable(true);
    }

    @FXML
    public void saveExamination() throws SQLException, ClassNotFoundException {
        // Get the input from the text areas
        String symptom = symptomTextArea.getText();
        String diagnostic = diagnosticTextArea.getText();
        String advice = adviceTextArea.getText();

        KhamController.saveRecord(recordId, pet.getId() ,symptom, diagnostic, advice);
        // Perform the necessary actions with the examination data

        // Clear the input fields
        symptomTextArea.clear();
        diagnosticTextArea.clear();
        adviceTextArea.clear();
    }

    @FXML
    public void prescribeMedicine() {
        String selectedMedicine = medicineComboBox.getSelectionModel().getSelectedItem();
        String quantity = quantityTextField.getText();

        // Add the prescribed medicine to the list view
        String medicineName = selectedMedicine.split(" - ")[0];
        int medicineId = Integer.valueOf(selectedMedicine.split(" - ")[1]);
        int quantityInt = Integer.valueOf(quantity);
//        query db
        boolean check = KhamController.prescript(medicineId, quantityInt);
        if (!check) {
        	return;
        }
        KhamController.savePrescription(recordId, medicineId, quantityInt);
        String prescription = selectedMedicine + " : " + quantity;

        prescribedMedicineList.add(prescription);

        // Clear the medicine and quantity fields
        medicineComboBox.getSelectionModel().clearSelection();
        quantityTextField.clear();
    }

    @FXML
    public void deleteMedicine() {
        // get selected textMedicine
        String selectedMedicine = prescribedMedicineListView.getSelectionModel().getSelectedItem();
        String medicineName = selectedMedicine.split(" - ")[0];
        String tmp = selectedMedicine.split(" - ")[1];
        int medicineId = Integer.valueOf(tmp.split(" : ")[0]);
        int quantity = Integer.valueOf(tmp.split(" : ")[1]);
        KhamController.deletePrescription(recordId, medicineId, quantity);

        int selectedIndex = prescribedMedicineListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            prescribedMedicineList.remove(selectedIndex);
        }

        deleteMedicineButton.setDisable(true);
    }

    ArrayList <String> listMedicineName = new ArrayList<String>();
    void getListMedicineName() {
        KhamController.getMedicines();
    	for (Medicine medicine : KhamController.medicineList) {
    		listMedicineName.add(medicine.getMedicineName() + " - " + medicine.getId());
    	}
    }
}
