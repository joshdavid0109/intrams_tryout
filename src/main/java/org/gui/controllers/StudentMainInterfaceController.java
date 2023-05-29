package org.gui.controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

public class StudentMainInterfaceController implements Initializable {

    public Text regID;
    @FXML
    private BorderPane borderPane;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button homeButton;

    @FXML
    private Button resultButton;

    @FXML
    private Button scheduleButton;

    @FXML
    private Button logOutButton;

    @FXML
    private Text homeText;

    @FXML
    private Text resultText;

    @FXML
    private Text statusText;

    @FXML
    private Text scheduleText;

    public static int studId;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }




    @FXML
    public void homePage(javafx.scene.input.MouseEvent mouseEvent) {
        if (homeButton.isPressed()) {
            homeButton.setStyle("-fx-background-color: #FFFFFF");
        } else {
            homeButton.setStyle("-fx-background-color: #FFFFFF");
            resultButton.setStyle("-fx-text-fill: #13202FFF");
            scheduleButton.setStyle("-fx-text-fill: #13202FFF");
        }

        homeText.setVisible(true);
        resultText.setVisible(false);
        scheduleText.setVisible(false);
        resultButton.setStyle("-fx-background-color: #13202FFF");
        scheduleButton.setStyle("-fx-background-color: #13202FFF");

        borderPane.setCenter(anchorPane);
    }

    @FXML
    public void onPageOne(javafx.scene.input.MouseEvent mouseEvent) {
        if (resultButton.isPressed()) {
            resultButton.setStyle("-fx-background-color: #FFFFFF");
        } else {
            resultButton.setStyle("-fx-background-color: #FFFFFF");
            homeButton.setStyle("-fx-text-fill: #13202FFF");
            scheduleButton.setStyle("-fx-text-fill: #13202FFF");
        }

        homeText.setVisible(false);
        resultText.setVisible(true);
        scheduleText.setVisible(false);
        homeButton.setStyle("-fx-background-color: #13202FFF");
        scheduleButton.setStyle("-fx-background-color: #13202FFF");
        StudentResultController.studentID = studId;
        loadPage("/org/studentResult.fxml");
    }

    @FXML
    public void onPageTwo(javafx.scene.input.MouseEvent mouseEvent) throws SQLException {
        if (scheduleButton.isPressed()) {
            scheduleButton.setStyle("-fx-background-color: #FFFFFF");
        } else {
            scheduleButton.setStyle("-fx-background-color: #FFFFFF");
            homeButton.setStyle("-fx-text-fill: #13202FFF");
            resultButton.setStyle("-fx-text-fill: #13202FFF");
        }

        homeText.setVisible(false);
        resultText.setVisible(false);
        scheduleText.setVisible(true);
        homeButton.setStyle("-fx-background-color: #13202FFF");
        resultButton.setStyle("-fx-background-color: #13202FFF");

        System.out.println(DataPB.getSportsCode(studId));
        StudentScheduleController.schedules = DataPB.studentGetTryoutSchedule(studId, DataPB.getSportsCode(studId));
        StudentScheduleController.studID = studId;
        loadPage("/org/studentSchedule.fxml");

    }

    private void loadPage(String page) {
        Parent root = null;

        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(page)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        borderPane.setCenter(root);
    }

    @FXML
    public void logOut(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout Confirmation");
        alert.setHeaderText("Are you sure you want to log out?");
        alert.setContentText("Press OK to confirm.");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            Stage stage = (Stage) logOutButton.getScene().getWindow();
            stage.close();
        }

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/org/loginInterface.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        Stage stage = (Stage) logOutButton.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }





}
