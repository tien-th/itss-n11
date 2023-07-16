module com.example.itssn11 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;

    opens app to javafx.praphics, javafx.fxml;
    exports app;

    opens model.entity to javafx.praphics, javafx.fxml, javafx.base;
    exports model.entity;

//    exports view.ui.quanly;
//    opens view.ui.quanly to javafx.fxml, javafx.praphics;

//    exports model.repository.quanly;
//    opens model.repository.quanly to javafx.fxml, javafx.praphics;
//    exports view.ui.user;
//    opens view.ui.user to javafx.fxml, javafx.praphics;
//    exports model.repository.user;
//    opens model.repository.user to javafx.fxml, javafx.praphics;


//    exports view.ui.doctor;
//    opens view.ui.doctor to javafx.fxml, javafx.praphics;
//    exports model.repository.doctor;
//    opens model.repository.doctor to javafx.fxml, javafx.praphics;
//    exports view.ui.login;
//    opens view.ui.login to javafx.fxml, javafx.praphics;
//    exports view.ui.pet;
//    opens view.ui.pet to javafx.fxml, javafx.praphics;
    exports model.repository;
    opens model.repository to javafx.fxml, javafx.praphics;

    exports utils.connection ;

//    opens service.appointment to javafx.fxml, javafx.praphics;
    exports controller;
    opens controller to javafx.fxml, javafx.praphics;
    exports view.user;
    opens view.user to javafx.fxml, javafx.praphics;
    exports view.services;
    opens view.services to javafx.fxml, javafx.praphics;
    exports view.pet;
    opens view.pet to javafx.fxml, javafx.praphics;
    exports view;
    opens view to javafx.fxml, javafx.praphics;
    exports utils;
    opens utils to javafx.fxml, javafx.praphics;

}