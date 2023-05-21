package org.gui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class CoachCreateScheduleController implements Initializable {

    @FXML
    private TextField scheduleCode;
    @FXML
    private TextField startTime;
    @FXML
    private TextField endTime;
    @FXML
    private TextField location;
    @FXML
    private DatePicker datePicker;
    @FXML
    private Button cancelBtn;
    @FXML
    private Button saveBtn;

    @FXML
    private void save(ActionEvent event) throws Exception {

    }

    @FXML
    private void cancel(ActionEvent event) throws Exception {
        scheduleCode.clear();
        startTime.clear();
        endTime.clear();
        location.clear();
        datePicker.setValue(null);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
