package org.gui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
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

public class CoachMainController implements Initializable {

    public Text coachID;
    public Text sportsCode;
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

    public static int coachNo;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        coachID.setText(String.valueOf(coachNo));
        try {
            sportsCode.setText(String.valueOf(DataPB.getCoachSportsCode(coachNo)));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
    public void onPageOne(javafx.scene.input.MouseEvent mouseEvent) throws Exception {
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

        CoachShowTryOutController.scheduleList = DataPB.getTryOutSchedDetails(coachNo);
        CoachShowTryOutController.coachNo =coachNo;
        loadPage("/org/coachShowTryOut.fxml");
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

        loadPage("/org/coachCreateSchedule.fxml");
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

//        CoachShowListSchedController.scheduleList = DataPB.get
        loadPage("/org/coachShowListSched.fxml");
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
        loader.setLocation(getClass().getResource("/org/coachLoginInterface.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        Stage stage = (Stage) logOutButton.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
