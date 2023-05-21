package org.gui.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Modality;
import org.gui.objects.Sport;
import org.gui.objects.TryoutSchedule;

import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CoachCreateScheduleController implements Initializable {

    public ChoiceBox<String> LocationChoiceBox;
    @FXML
    private TextField scheduleCode;
    @FXML
    private TextField startTime;
    @FXML
    private TextField endTime;
    @FXML
    private DatePicker datePicker;
    @FXML
    private Button cancelBtn;
    @FXML
    private Button saveBtn;

    public int coachNo;

    public int sportsCode;

    ArrayList<String> locations = new ArrayList<String>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        populateLocationChoiceBox();
    }

    @FXML
    private void save(ActionEvent event) throws Exception {

        try{
            sportsCode = DataPB.getSportsCodeOfCoach(coachNo);

            TryoutSchedule tryoutSchedule = new TryoutSchedule(sportsCode, coachNo, Date.valueOf(datePicker.getValue()), Time.valueOf(LocalTime.parse(startTime.getText(), DateTimeFormatter.ofPattern("HH:mm:ss"))), Time.valueOf(LocalTime.parse(endTime.getText(), DateTimeFormatter.ofPattern("HH:mm:ss"))), LocationChoiceBox.getValue());
            System.out.println(tryoutSchedule);
            DataPB.addSchedule(tryoutSchedule);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("SUCCESS");
            alert.setHeaderText(null);
            alert.setContentText("Tryout successfully added!");
            alert.showAndWait();

        }catch (Exception e){
            showAlert("ERROR", e.getMessage());
        }

    }

    @FXML
    private void cancel(ActionEvent event) throws Exception {
        scheduleCode.clear();
        startTime.clear();
        endTime.clear();
        datePicker.setValue(null);
        LocationChoiceBox.setValue(null);
    }
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    private void populateLocationChoiceBox() {
        locations.add("Prince Bernhard Gymnasium");
        locations.add("Bishop Carlito Cenzon Sports Center");
        locations.add("Bakakeng Campus Oval");
        ObservableList<String> locationList = FXCollections.observableArrayList(locations);
        LocationChoiceBox.setItems(locationList);
    }

    public void setCoachNo(int coachn) {
        this.coachNo  = coachn;
        System.out.println("SET"+this.coachNo);
    }
}
