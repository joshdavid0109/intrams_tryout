package org.gui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.gui.objects.Sport;


public class AdminAssignController {

    @FXML
    public TextField coachNo;

    @FXML
    public TextField SportsCode;

    @FXML
    public Button assignSportsBTN;

    @FXML
    public Button cancelAssignSportsBTN;

    public void assignSport(ActionEvent event) {
        System.out.println(coachNo.getText());
        System.out.println(SportsCode.getText());
    }

    public void cancelSport(ActionEvent event) {
        coachNo.setText("");
        SportsCode.setText("");
    }
}
