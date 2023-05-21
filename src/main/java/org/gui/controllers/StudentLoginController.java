package org.gui.controllers;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.gui.main.StudentMain;

import java.io.IOException;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class StudentLoginController {

    @FXML
    public TextField logInStudentId;
    @FXML
    public TextField logInPassword;
    @FXML
    private PasswordField logInPasswordHide;
    @FXML
    private CheckBox showPassword;
    @FXML
    public Button logInButton;

    @FXML
    public ImageView samcisLogo;
    @FXML
    private Button loadRegisterGUIbtn;
    @FXML
    private AnchorPane loginAnchorPane;
    @FXML
    private StackPane parentContainer;



    @FXML
    public void LoadRegisterInformationGUI() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/org/registerInterface.fxml")));
        Scene scene = loadRegisterGUIbtn.getScene();

        root.translateXProperty().set(scene.getWidth());
        parentContainer.getChildren().add(root);

        Timeline timeline = new Timeline();
        KeyValue keyValue = new KeyValue(root.translateXProperty(), 0, Interpolator.EASE_IN);
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(1), keyValue);

        timeline.getKeyFrames().add(keyFrame);
        timeline.setOnFinished(event1 -> parentContainer.getChildren().remove(loginAnchorPane));
        timeline.play();
    }

    @FXML
    public void showPassword() {
        if(showPassword.isSelected()) {
            logInPassword.setText(logInPasswordHide.getText());
            logInPassword.toFront();
        } else {
            logInPasswordHide.getText();
            logInPasswordHide.toFront();
        }
    }


    public void logInNa(ActionEvent actionEvent) throws Exception {
        Alert message = new Alert(Alert.AlertType.INFORMATION);

        String studentIdText = logInStudentId.getText();

        if (studentIdText.isEmpty()) {
            message.setContentText("Invalid Student Id or Password");
            message.setTitle("Unsuccessful Login");
            message.show();

            System.out.println("Login Unsuccessful");
            logInPasswordHide.setText("");
            logInPassword.setText("");
            logInStudentId.setText("");
            return;
        }

        studId = Integer.parseInt(studentIdText);
        String password = getPassword();

        boolean isLoggedIn = DataPB.loginStudent(studId, password);

        if (isLoggedIn) {
            System.out.println("Welcome Student");

            Stage loginStage = (Stage) logInButton.getScene().getWindow();
            loginStage.close();

            Stage primaryStage = new Stage();
            Image image = new Image("SLU_LOGO.jpg");

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/studentMainInterface.fxml"));
            Parent root = fxmlLoader.load();

            Scene scene = new Scene(root);
            primaryStage.setTitle("Student");
            primaryStage.getIcons().add(image);
            primaryStage.setResizable(false);
            primaryStage.setScene(scene);
            primaryStage.show();

            StudentMainInterfaceController mainInterfaceController = fxmlLoader.getController();
            mainInterfaceController.setStudId(studId);
            System.out.println(studId + " FROM LOGIN");
        } else {
            message.setContentText("Invalid Student Id or Password");
            message.setTitle("Unsuccessful Login");
            message.show();
        }

        System.out.println("Login Unsuccessful");
        logInPasswordHide.setText("");
        logInPassword.setText("");
        logInStudentId.setText("");
    }

    private String getPassword() {
        if (showPassword.isSelected()) {
            return logInPassword.getText();
        } else {
            return logInPasswordHide.getText();
        }
    }

    private int studId;


}


