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
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class AdminLoginController {

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

        String adminUsername = logInUsername.getText();
        String loginPassword = logInPassword.getText();

        if (adminUsername.isEmpty() || loginPassword.isEmpty()) {
            message.setContentText("Missing Credentials");
            message.setTitle("Unsuccessful Login");
            message.show();

            System.out.println("Login Unsuccessful");
            logInPasswordHide.setText("");
            logInPassword.setText("");
            logInUsername.setText("");
            return;
        }

        try {
            String userName = logInUsername.getText();
            String passWord = (logInPasswordHide.getText().equals("")
                    ? logInPassword.getText() : logInPasswordHide.getText());

            if (userName.equals("user") && passWord.equals("admin")) {
                logInButton.getScene().getWindow().hide();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/adminMainInterface.fxml"));

                Parent root = fxmlLoader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) logInButton.getScene().getWindow();
                stage.setScene(scene);
                stage.show();


            } else {
                message.setContentText("Invalid User Name or Password");
                message.setTitle("Unsuccessful Login");
                message.show();
            }

            logInPasswordHide.setText("");
            logInPassword.setText("");
            logInUsername.setText("");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}


