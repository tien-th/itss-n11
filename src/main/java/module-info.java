module com.example.itssn11 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.itssn11 to javafx.fxml;
    exports com.example.itssn11;

    opens dangnhap to javafx.praphics, javafx.fxml;
    exports dangnhap;

    opens menu to javafx.praphics, javafx.fxml;
    exports menu;

    opens view_pet to javafx.praphics, javafx.fxml;
    exports view_pet;

}