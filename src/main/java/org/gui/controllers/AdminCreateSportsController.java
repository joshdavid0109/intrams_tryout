package org.gui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminCreateSportsController implements Initializable {

    public TextField sportsNameTxtField;
    public RadioButton maleRadioBTN;
    public RadioButton femaleRadioBTN;
    public RadioButton selectedRadioButton;
    private ToggleGroup toggleGroup;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        toggleGroup = new ToggleGroup();
        maleRadioBTN.setToggleGroup(toggleGroup);
        femaleRadioBTN.setToggleGroup(toggleGroup);
    }

    public void saveSport(ActionEvent event) {
        try{
            String sport = sportsNameTxtField.getText();
            selectedRadioButton = (RadioButton) toggleGroup.getSelectedToggle();
            DataPB.addSport(sport, selectedRadioButton.getText());

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("SPORT ADDED!");
            alert.setHeaderText(null);
            alert.setContentText(selectedRadioButton.getText()+"' " +sport+" has been added!");
            alert.showAndWait();

        }catch (Exception e){
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ERROR!");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    public void cancelSport(ActionEvent event) {
        sportsNameTxtField.setText("");
        toggleGroup.selectToggle(null);
    }


}
