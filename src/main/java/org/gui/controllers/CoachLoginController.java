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
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.gui.main.CoachMain;

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
    private AnchorPane loginAnchorPane;
    @FXML
    private StackPane parentContainer;

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
        Alert message = new Alert(Alert.AlertType.INFORMATION);

        String coachNoCheck = logInUsername.getText();

        if (coachNoCheck.isEmpty()) {
            message.setContentText("Invalid Coach No or Password");
            message.setTitle("Unsuccessful Login");
            message.show();

            System.out.println("Login Unsuccessful");
            logInPasswordHide.setText("");
            logInPassword.setText("");
            logInUsername.setText("");
            return;
        }

        int coachNo = Integer.parseInt(coachNoCheck);
        String password = getPassword();

        boolean isLoggedIn = DataPB.loginCoach(coachNo, password);

        if (isLoggedIn) {
            CoachMainController.coachNo = coachNo;
            System.out.println("Welcome Coach");

            Stage loginStage = (Stage) logInButton.getScene().getWindow();
            loginStage.close();

            Stage primaryStage = new Stage();
            Image image = new Image("SLU_LOGO.jpg");

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/coachMain.fxml"));
            Parent root = fxmlLoader.load();
            CoachMainController coachMainController = fxmlLoader.getController();
            coachMainController.setCoachNo(coachNo);

            Scene scene = new Scene(root);
            primaryStage.setTitle("Coach");
            primaryStage.getIcons().add(image);
            primaryStage.setResizable(false);
            primaryStage.setScene(scene);
            primaryStage.show();

        } else {
            message.setContentText("Invalid Coach No or Password");
            message.setTitle("Unsuccessful Login");
            message.show();
        }

        logInPasswordHide.setText("");
        logInPassword.setText("");
        logInUsername.setText("");
    }

    private String getPassword() {
        if (showPassword.isSelected()) {
            return logInPassword.getText();
        } else {
            return logInPasswordHide.getText();
        }
    }
}


