package org.gui.controllers;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class CoachRegistrationController {

    @FXML
    private Button loadLoginGUIbtn, returnRegisterInformation;
    @FXML
    private AnchorPane registerAnchorPane;
    @FXML
    private StackPane parentContainer;
    @FXML
    private Button regButton;

    @FXML
    private PasswordField regPasswordHide;
    @FXML
    private PasswordField regVerifyPasswordHide;
    @FXML
    private CheckBox regShowPassword;
    @FXML
    private CheckBox regVerifyShowPassword;
    @FXML
    private TextField regPassword;
    @FXML
    private TextField regVerifyPassword;
    @FXML
    private TextField regUsername;


    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void loadLoginGUI() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/org/coachLoginInterface.fxml")));
        Scene scene = loadLoginGUIbtn.getScene();

        root.translateYProperty().set(scene.getHeight());
        parentContainer.getChildren().add(root);

        Timeline timeline = new Timeline();
        KeyValue keyValue = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_IN);
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(1), keyValue);

        timeline.getKeyFrames().add(keyFrame);
        timeline.setOnFinished(event1 -> parentContainer.getChildren().remove(registerAnchorPane));
        timeline.play();
    }

    @FXML
    public void loadRegisterInformation() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/org/coachRegisterInterfacePersonalDetails.fxml")));
        Scene scene = returnRegisterInformation.getScene();

        root.translateYProperty().set(scene.getHeight());
        parentContainer.getChildren().add(root);

        Timeline timeline = new Timeline();
        KeyValue keyValue = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_IN);
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(1), keyValue);

        timeline.getKeyFrames().add(keyFrame);
        timeline.setOnFinished(event1 -> parentContainer.getChildren().remove(registerAnchorPane));
        timeline.play();
    }

    public void registerNa() throws IOException {

    }

    @FXML
    public void regShowPassword() {
        if(regShowPassword.isSelected()) {
            regPassword.setText(regPasswordHide.getText());
            regPassword.setVisible(true);
            regPasswordHide.setVisible(false);
        } else {
            regPasswordHide.setText(regPassword.getText());
            regPasswordHide.setVisible(true);
            regPassword.setVisible(false);
        }
    }

    @FXML
    public void regVerifyShowPassword() {
        if(regVerifyShowPassword.isSelected()) {
            regVerifyPassword.setText(regVerifyPasswordHide.getText());
            regVerifyPassword.setVisible(true);
            regVerifyPasswordHide.setVisible(false);
        } else {
            regVerifyPasswordHide.setText(regVerifyPassword.getText());
            regVerifyPasswordHide.setVisible(true);
            regVerifyPassword.setVisible(false);
        }
    }
}
