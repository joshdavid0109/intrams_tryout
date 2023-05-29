package org.gui.controllers;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.gui.objects.RegisteredUser;
import org.gui.objects.Sport;

import java.io.IOException;
import java.net.URL;

import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class StudentRegistrationController implements Initializable {

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
    private CheckBox regShowPassword;
    @FXML
    private TextField regPassword;
    @FXML
    private TextField contactNumberField;
    @FXML
    private TextField regStudentId;
    @FXML
    private ChoiceBox<Sport> sportChoiceBox;

    private int sportsCode;


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
        ArrayList<RegisteredUser> registeredUsers = DataPB.getRegisteredStudents();

        String studIdText = regStudentId.getText();
        String password = regPassword.getText();
        String contactNo = contactNumberField.getText();

        Sport selectedSport = sportChoiceBox.getValue();
        String stringSelectedSport = selectedSport.getSportsName();

        if (selectedSport == null) {
            showAlert("Error", "SELECT VALUES");
            return;
        }

        int studId;
        try {
            studId = Integer.parseInt(studIdText);
        } catch (NumberFormatException x) {
            showAlert("Error", "Student ID must be a numeric value.");
            return;
        }

        if (contactNo.isEmpty()) {
            showAlert("Error", "Contact No. cannot be empty.");
            return;
        }

        if (studId < 100000 || studId > 999999) {
            showAlert("Error", "Student ID must have 6 digits.");
            return;
        }

        boolean checkIfRegistered = DataPB.checkExistingStudentIdAndAppliedSport(studId, selectedSport.getSportsCode());

        if (checkIfRegistered) {
            showAlert("Error", "Provided ID number with the sport [" + stringSelectedSport + "] is already registered!");
            return;
        }

        sportsCode = selectedSport.getSportsCode();

        DataPB.addStudent(new RegisteredUser(registeredUsers.size() + 1, studId, sportsCode, contactNo, password));

        DataPB.addToTryOutSchedDetails(registeredUsers.size(), null, "PENDING");

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/org/studentMainInterface.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        Stage stage = (Stage) regButton.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
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

    private void populateSportsList(){
        try {
            System.out.println(DataPB.getAvailableSports());
            ArrayList<Sport> sports = DataPB.getAvailableSports();
            ObservableList<Sport> sportsList = FXCollections.observableArrayList(sports);
            sportChoiceBox.setItems(sportsList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        populateSportsList();

        regStudentId.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.isEmpty()) {
                int studId;
                try {
                    studId = Integer.parseInt(newValue);
                } catch (NumberFormatException e) {
                    showAlert("Error", "Student ID must be a numeric value.");
                    return;
                }

                System.out.println("hi");
                String passwordByStudentId = DataPB.getPasswordByStudentId(studId);

                if (passwordByStudentId != null && regPassword.getText().isEmpty()) {

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Registration Confirmation");
                    alert.setHeaderText(null);
                    alert.setContentText("You have already registered before. Your password will be autofilled.");
                    alert.showAndWait();



                    regPassword.setText(passwordByStudentId);

                    regPassword.setEditable(false);
                }
            }
        });
    }
}
