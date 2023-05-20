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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import org.gui.objects.Department;
import org.gui.objects.RegisteredUser;
import org.gui.objects.Sport;
import org.gui.objects.Student;

import java.io.IOException;
import java.net.URL;

import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class StudentRegistrationController {

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
    private TextField regStudentId;

    private int sportsCode;



    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void loadLoginGUI() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/org/LoginInterface.fxml")));
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
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/org/registerInterfacePersonalDetails.fxml")));
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

    public void registerNa(ActionEvent e) throws Exception {

        ArrayList<Sport> sports = DataPB.getAvailableSports();
        ArrayList<Department> departments = DataPB.getDepartments();
        ArrayList<RegisteredUser> registeredUsers = DataPB.getRegisteredStudents();
        System.out.println(sportsCode);

        String studIdText = regStudentId.getText();
        String password = regPassword.getText();
        String verifyPassword = regVerifyPassword.getText();

        if (password != verifyPassword) {
            showAlert("Error", "Password does not match.");
        }

        if (studIdText.isEmpty() || password.isEmpty()) {
            showAlert("Error", "Student ID and password cannot be empty.");
            return;
        }

        int studId;

        try {
            studId = Integer.parseInt(studIdText);
        } catch (Exception x) {
            showAlert("Error", "Student ID must be a numeric value.");
            return;
        }

        if (studId < 100000 || studId > 999999) {
            showAlert("Error", "Student ID must have 6 digits.");
            return;
        }

        boolean checkExist = DataPB.checkExistingStudentId(studId);

        if (password.length() < 8) {
            showAlert("Error", "Password must have at least 8 characters.");
        }

        if (!checkExist) {
            showAlert("Error", "Student with the provided ID number does not exist.");
            return;
        }

        boolean checkIfRegistered = DataPB.checkExistingStudentIdSaRegistrationsHAHAHA(studId);

        if (checkIfRegistered) {
            showAlert("Error", "Student with the provided ID number is already registered :(");
            return;
        }

//        int deptId = DataPB.getDeptId(studId);

        DataPB.addStudent(new org.gui.objects.RegisteredUser(registeredUsers.size() + 1, studId, sportsCode,
                "wala pa", password));
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
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
    public void setSelectedSportCode(int sportsCode) {
        this.sportsCode = sportsCode;
    }
}
