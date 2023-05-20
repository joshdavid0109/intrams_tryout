package org.gui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.gui.database.DataPB;

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
    private Button showButton;

    @FXML
    private Button createButton;

    @FXML
    private Button assignButton;

    @FXML
    private Button logOutButton;

    @FXML
    private Text homeText;

    @FXML
    private Text showText;

    @FXML
    private Text createText;

    @FXML
    private Text assignText;

    DataPB dataPB;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dataPB = new DataPB();
    }

    @FXML
    public void homePage(javafx.scene.input.MouseEvent mouseEvent) {
        if (homeButton.isPressed()) {
            homeButton.setStyle("-fx-background-color: #FFFFFF");
        } else {
            homeButton.setStyle("-fx-background-color: #FFFFFF");
            showButton.setStyle("-fx-text-fill: #13202FFF");
            createButton.setStyle("-fx-text-fill: #13202FFF");
            assignButton.setStyle("-fx-text-fill: #13202FFF");
        }

        homeText.setVisible(true);
        showText.setVisible(false);
        createText.setVisible(false);
        assignText.setVisible(false);

        showButton.setStyle("-fx-background-color: #13202FFF");
        createButton.setStyle("-fx-background-color: #13202FFF");
        assignButton.setStyle("-fx-background-color: #13202FFF");

        borderPane.setCenter(anchorPane);
    }

    @FXML
    public void onPageOne(javafx.scene.input.MouseEvent mouseEvent) {
        if (showButton.isPressed()) {
            showButton.setStyle("-fx-background-color: #FFFFFF");
        } else {
            showButton.setStyle("-fx-background-color: #FFFFFF");
            homeButton.setStyle("-fx-text-fill: #13202FFF");
            createButton.setStyle("-fx-text-fill: #13202FFF");
            assignButton.setStyle("-fx-text-fill: #13202FFF");
        }

        homeText.setVisible(false);
        showText.setVisible(true);
        createText.setVisible(false);
        assignText.setVisible(false);

        homeButton.setStyle("-fx-background-color: #13202FFF");
        createButton.setStyle("-fx-background-color: #13202FFF");
        assignButton.setStyle("-fx-background-color: #13202FFF");

        AdminShowSportsAndCoordinatorsController.coaches = DataPB.getCoaches();
        loadPage("/org/adminShowSportsAndCoordinators.fxml");
    }

    @FXML
    public void onPageTwo(javafx.scene.input.MouseEvent mouseEvent) {
        if (createButton.isPressed()) {
            createButton.setStyle("-fx-background-color: #FFFFFF");
        } else {
            createButton.setStyle("-fx-background-color: #FFFFFF");
            homeButton.setStyle("-fx-text-fill: #13202FFF");
            showButton.setStyle("-fx-text-fill: #13202FFF");
            assignButton.setStyle("-fx-text-fill: #13202FFF");
        }

        homeText.setVisible(false);
        showText.setVisible(false);
        createText.setVisible(true);
        assignText.setVisible(false);

        homeButton.setStyle("-fx-background-color: #13202FFF");
        showButton.setStyle("-fx-background-color: #13202FFF");
        assignButton.setStyle("-fx-background-color: #13202FFF");

        loadPage("/org/adminCreateSports.fxml");
    }

    @FXML
    public void onPageThree(javafx.scene.input.MouseEvent mouseEvent) {
        if (assignButton.isPressed()) {
            assignButton.setStyle("-fx-background-color: #FFFFFF");
        } else {
            assignButton.setStyle("-fx-background-color: #FFFFFF");
            homeButton.setStyle("-fx-text-fill: #13202FFF");
            showButton.setStyle("-fx-text-fill: #13202FFF");
            createButton.setStyle("-fx-text-fill: #13202FFF");
        }

        homeText.setVisible(false);
        showText.setVisible(false);
        createText.setVisible(false);
        assignText.setVisible(true);

        homeButton.setStyle("-fx-background-color: #13202FFF");
        showButton.setStyle("-fx-background-color: #13202FFF");
        createButton.setStyle("-fx-background-color: #13202FFF");

        loadPage("/org/adminAssign.fxml");
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

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/org/adminLoginInterface.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        Stage stage = (Stage) logOutButton.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
