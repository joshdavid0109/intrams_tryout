package org.gui.controllers;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Objects;
import java.util.ResourceBundle;

public class StudentRegistrationInfoController {
    @FXML
    private ComboBox<String> genderBox;
    @FXML
    private DatePicker datePicker;


    @FXML
    private TextField lastNameField, firstNameField;


    @FXML
    private AnchorPane registerInformationAnchorPane;
    @FXML
    private StackPane parentContainer;
    @FXML
    private Button nextButton, loadGuiButton;

    @FXML
    public void loadLoginGUI() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/org/loginInterface.fxml")));
        Scene scene = loadGuiButton.getScene();

        root.translateYProperty().set(scene.getHeight());
        parentContainer.getChildren().add(root);

        Timeline timeline = new Timeline();
        KeyValue keyValue = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_IN);
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(1), keyValue);

        timeline.getKeyFrames().add(keyFrame);
        timeline.setOnFinished(event1 -> parentContainer.getChildren().remove(registerInformationAnchorPane));
        timeline.play();
    }

    @FXML
    public void loadRegisterGUI() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/org/registerInterface.fxml")));
        Scene scene = nextButton.getScene();

        root.translateXProperty().set(scene.getWidth());
        parentContainer.getChildren().add(root);

        Timeline timeline = new Timeline();
        KeyValue keyValue = new KeyValue(root.translateXProperty(), 0, Interpolator.EASE_IN);
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(1), keyValue);

        timeline.getKeyFrames().add(keyFrame);
        timeline.setOnFinished(event1 -> parentContainer.getChildren().remove(registerInformationAnchorPane));
        timeline.play();
    }

    private static void calculateAge(LocalDate birthDate, LocalDate currentDate) {

    }


    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    public void clicky(MouseEvent mouseEvent) {
        ObservableList<String> list = FXCollections.observableArrayList("Male", "Female", "Other");
        genderBox.getItems().setAll(list);
        genderBox.getSelectionModel().selectFirst();
    }
}
