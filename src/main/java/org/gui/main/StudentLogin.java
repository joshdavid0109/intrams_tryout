package org.gui.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class StudentLogin extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        Image image = new Image("SLU_LOGO.jpg");

        FXMLLoader fxmlLoader = new FXMLLoader(AdminMain.class.getResource("/org/studentMainInterface.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        primaryStage.setTitle("Student");
        primaryStage.getIcons().add(image);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
