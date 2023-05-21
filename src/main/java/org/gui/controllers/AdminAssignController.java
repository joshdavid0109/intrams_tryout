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

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;


public class AdminAssignController implements Initializable {

    @FXML
    public Button assignSportsBTN;

    @FXML
    public Button cancelAssignSportsBTN;

    @FXML
    public ChoiceBox<String> sportsChoiceBox;

    @FXML
    public ChoiceBox<String> coordinatorChoiceBox;

    private Map<Integer, String> sportsMap;
    private Map<Integer, String> coordinatorsMap;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        populateSportsList();
        populateCoordinatorsList();
    }



    public void assignSport(ActionEvent event) {
        System.out.println();
    }

    public void cancelSport(ActionEvent event) {
        System.out.println("cancel");
    }

    private void populateSportsList(){

        try {
            System.out.println(DataPB.getAllCoordinators());
            System.out.println(DataPB.getAvailableSports());
        } catch (Exception e) {
            e.printStackTrace();
        }

        sportsMap = new HashMap<>();
        sportsMap.put(1, "BasketBall-Men");
        sportsMap.put(2, "Football-Women");
        sportsMap.put(3, "Hockey-Women");
        sportsMap.put(4, "BasketBall-Women");

        ObservableList<String> sportsList = FXCollections.observableArrayList();
        for (Map.Entry<Integer, String> entry : sportsMap.entrySet()) {
            int id = entry.getKey();
            String description = entry.getValue();
            String[] sportParts = description.split("-");
            if (sportParts.length == 2) {
                String name = sportParts[0];
                String category = sportParts[1];
                sportsList.add(new Sport(id, name, category).toString());
            }
        }

        sportsChoiceBox.setItems(sportsList);
    }
    private void populateCoordinatorsList() {
        /*coordinatorsMap = new HashMap<>();
        coordinatorsMap.put(1, "John Brown");
        coordinatorsMap.put(2, "bla bla");
        coordinatorsMap.put(3, "asd asd");
        coordinatorsMap.put(4, "we we");

        ObservableList<String> coordinatorList = FXCollections.observableArrayList();
        for (Map.Entry<Integer, String> entry : coordinatorsMap.entrySet()) {
            int id = entry.getKey();
            String description = entry.getValue();
            String[] sportParts = description.split("-");
            //coordinatorList.add(new Coordinator(id, firstname, lastName, deptID).toString());
            }

        coordinatorChoiceBox.setItems(coordinatorList);*/
    }

}
