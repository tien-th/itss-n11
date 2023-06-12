module com.example.itssn11 {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.example.itssn11 to javafx.fxml;
    exports com.example.itssn11;
}