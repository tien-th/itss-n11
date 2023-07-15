module com.example.itssn11 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;


    opens com.example.itssn11 to javafx.fxml;
    exports com.example.itssn11;


    opens app to javafx.praphics, javafx.fxml;
    exports app;

    opens entity to javafx.praphics, javafx.fxml, javafx.base;
    exports entity;

    exports controller.quanly;
    opens controller.quanly to javafx.fxml, javafx.praphics;

    exports repository.quanly;
    opens repository.quanly to javafx.fxml, javafx.praphics;
    exports controller.user;
    opens controller.user to javafx.fxml, javafx.praphics;
    exports repository.user;
    opens repository.user to javafx.fxml, javafx.praphics;
    exports repository;
    opens repository to javafx.fxml, javafx.praphics;

    exports controller.doctor;
    opens controller.doctor to javafx.fxml, javafx.praphics;
    exports repository.doctor;
    opens repository.doctor to javafx.fxml, javafx.praphics;
    exports controller.login;
    opens controller.login to javafx.fxml, javafx.praphics;
    exports controller.pet;
    opens controller.pet to javafx.fxml, javafx.praphics;

}