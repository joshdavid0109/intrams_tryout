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
import org.gui.objects.Student;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Objects;
import java.util.ResourceBundle;

public class StudentRegistrationInfoController {
    @FXML
    private ComboBox<String> sportBox;
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

    private static int sportsCode;

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
        String selectedSport = sportBox.getValue(); // Get the selected option from the ComboBox


        if (selectedSport == null || selectedSport.isEmpty()) {
            showAlert("Error", "Please select a sport!");
            return;
        }

        switch (selectedSport) {
            case "Basketball":
                sportsCode = 1;
                break;
            case "Hockey":
                sportsCode = 2;
                break;
            case "Association Football":
                sportsCode = 3;
                break;
            default:
                sportsCode = 0;
                break;
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/registerInterface.fxml"));
        Parent root = loader.load();
        StudentRegistrationController controller2 = loader.getController();
        controller2.setSelectedSportCode(sportsCode);

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

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private static void calculateAge(LocalDate birthDate, LocalDate currentDate) {

    }


    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void clicky(MouseEvent mouseEvent) {
        ObservableList<String> list = FXCollections.observableArrayList("Basketball", "Hockey", "Association Football");
        sportBox.getItems().setAll(list);
        sportBox.getSelectionModel().selectFirst();
    }
}
