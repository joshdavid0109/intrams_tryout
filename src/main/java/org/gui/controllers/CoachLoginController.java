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
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Objects;

public class CoachLoginController {

    @FXML
    public TextField logInUsername;
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
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/org/coachRegisterInterfacePersonalDetails.fxml")));
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
            logInPasswordHide.setText(logInPassword.getText());
            logInPasswordHide.toFront();
        }
    }


    public void logInNa(ActionEvent actionEvent) throws Exception {

        String coachNoCheck = logInUsername.getText();
        if (coachNoCheck.isEmpty()) {
            System.out.println("Student ID is required.");
            return;
        }

        int coachNo = Integer.parseInt(coachNoCheck);
        String password = getPassword();

        boolean isLoggedIn = DataPB.loginCoach(coachNo, password);

        if (isLoggedIn) {
            System.out.println("Welcome Coach");

            Stage loginStage = (Stage) logInButton.getScene().getWindow();
            loginStage.close();

            Stage primaryStage = new Stage();
            Image image = new Image("SLU_LOGO.jpg");

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/coachMain.fxml"));
            Parent root = fxmlLoader.load();

            Scene scene = new Scene(root);
            primaryStage.setTitle("Coach");
            primaryStage.getIcons().add(image);
            primaryStage.setResizable(false);
            primaryStage.setScene(scene);
            primaryStage.show();
        } else {
            System.out.println("Invalid Credentials");
        }
    }

    private String getPassword() {
        if (showPassword.isSelected()) {
            return logInPassword.getText();
        } else {
            return logInPasswordHide.getText();
        }
    }
}


