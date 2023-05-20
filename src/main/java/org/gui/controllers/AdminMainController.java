package org.gui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class AdminMainController implements Initializable {

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
    private Button assignButton;

    @FXML
    private Button logOutButton;

    @FXML
    private Text homeText;

    @FXML
    private Text resultText;

    @FXML
    private Text scheduleText;

    @FXML
    private Text assignText;

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
            assignButton.setStyle("-fx-text-fill: #13202FFF");
        }

        homeText.setVisible(true);
        resultText.setVisible(false);
        scheduleText.setVisible(false);
        assignText.setVisible(false);

        resultButton.setStyle("-fx-background-color: #13202FFF");
        scheduleButton.setStyle("-fx-background-color: #13202FFF");
        assignButton.setStyle("-fx-background-color: #13202FFF");

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
            assignButton.setStyle("-fx-text-fill: #13202FFF");
        }

        homeText.setVisible(false);
        resultText.setVisible(true);
        scheduleText.setVisible(false);
        assignText.setVisible(false);

        homeButton.setStyle("-fx-background-color: #13202FFF");
        scheduleButton.setStyle("-fx-background-color: #13202FFF");
        assignButton.setStyle("-fx-background-color: #13202FFF");

        loadPage("/org/studentResult.fxml");
    }

    @FXML
    public void onPageTwo(javafx.scene.input.MouseEvent mouseEvent) {
        if (scheduleButton.isPressed()) {
            scheduleButton.setStyle("-fx-background-color: #FFFFFF");
        } else {
            scheduleButton.setStyle("-fx-background-color: #FFFFFF");
            homeButton.setStyle("-fx-text-fill: #13202FFF");
            resultButton.setStyle("-fx-text-fill: #13202FFF");
            assignButton.setStyle("-fx-text-fill: #13202FFF");
        }

        homeText.setVisible(false);
        resultText.setVisible(false);
        scheduleText.setVisible(true);
        assignText.setVisible(false);

        homeButton.setStyle("-fx-background-color: #13202FFF");
        resultButton.setStyle("-fx-background-color: #13202FFF");
        assignButton.setStyle("-fx-background-color: #13202FFF");

        loadPage("/org/studentSchedule.fxml");
    }

    @FXML
    public void onPageThree(javafx.scene.input.MouseEvent mouseEvent) {
        if (assignButton.isPressed()) {
            assignButton.setStyle("-fx-background-color: #FFFFFF");
        } else {
            assignButton.setStyle("-fx-background-color: #FFFFFF");
            homeButton.setStyle("-fx-text-fill: #13202FFF");
            resultButton.setStyle("-fx-text-fill: #13202FFF");
            scheduleButton.setStyle("-fx-text-fill: #13202FFF");
        }

        homeText.setVisible(false);
        resultText.setVisible(false);
        scheduleText.setVisible(false);
        assignText.setVisible(true);

        homeButton.setStyle("-fx-background-color: #13202FFF");
        resultButton.setStyle("-fx-background-color: #13202FFF");
        scheduleButton.setStyle("-fx-background-color: #13202FFF");

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
    public void logOut(ActionEvent event) {
    }
}
