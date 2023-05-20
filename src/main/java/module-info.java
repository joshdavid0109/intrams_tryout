module com.example.im_final_project {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires javafx.graphics;
    requires java.sql;

    opens org;

    opens org.gui.main to javafx.fxml;
    exports org.gui.main;

    opens org.gui.controllers to javafx.fxml;
    exports org.gui.controllers;
}