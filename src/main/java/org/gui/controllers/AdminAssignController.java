package org.gui.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.StringConverter;
import org.gui.objects.Coordinator;
import org.gui.objects.Sport;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;


public class AdminAssignController implements Initializable {

    @FXML
    public Button assignSportsBTN;

    @FXML
    public Button cancelAssignSportsBTN;

    @FXML
    public ChoiceBox<Sport> sportsChoiceBox;

    @FXML
    public ChoiceBox<Coordinator> coordinatorChoiceBox;

    private Map<Integer, String> sportsMap;
    private Map<Integer, String> coordinatorsMap;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        populateSportsList();
        populateCoordinatorsList();
    }

    public void assignSport(ActionEvent event) {
        try{
            Sport selectedSport = sportsChoiceBox.getValue();
            Coordinator selectedCoordinator = coordinatorChoiceBox.getValue();

            if(selectedSport == null || selectedCoordinator == null){
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("ERROR");
                alert.setHeaderText(null);
                alert.setContentText("SELECT VALUES");
                alert.showAndWait();
            }
            else{
                DataPB.addCoach(selectedCoordinator.getCoordinatorID(), selectedSport.getSportsCode());
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("ASSIGNMENT SUCCESSFUL");
                alert.setHeaderText(null);
                alert.setContentText("COORDINATOR: " + selectedCoordinator.getFirstName() +" "+selectedCoordinator.getLastName() + " has been assigned to the sport\n"
                        + selectedSport.getSportsDescription() + "'s " + selectedSport.getSportsName());
                alert.showAndWait();
            }
        }catch (SQLException e){
            //TODO DAPAT YUNG MGA COORDINATOR LANG NA WALANG SPORT YUNG PWEDE ISELECT!!!!!!!!! PARA DI MAG ERROR NA KASJY
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("SQL EXCEPTION");
            alert.setHeaderText(null);
            alert.setContentText("COORDINATOR HAS ALREADY BEEN ASSIGNED TO A SPORT:(");
            alert.showAndWait();
        }
        catch (Exception e){
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("ERROR");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    public void cancelSport(ActionEvent event) {
        System.out.println("cancel");
    }

    private void populateSportsList(){

        try {
            System.out.println(DataPB.getAvailableSports());
            ArrayList<Sport> sports = DataPB.getAvailableSports();
            ObservableList<Sport> sportsList = FXCollections.observableArrayList(sports);
            sportsChoiceBox.setItems(sportsList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void populateCoordinatorsList() {
        try {
            System.out.println(DataPB.getAllCoordinators());
            ArrayList<Coordinator> coordinators = DataPB.getAllCoordinators();
            ObservableList<Coordinator> coordinatorsList = FXCollections.observableArrayList(coordinators);
            coordinatorChoiceBox.setItems(coordinatorsList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
