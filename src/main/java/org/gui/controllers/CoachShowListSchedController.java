package org.gui.controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.gui.objects.Coach;
import org.gui.objects.TryoutSchedDetails;
import org.gui.objects.TryoutSchedule;

import java.net.URL;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.ResourceBundle;

public class CoachShowListSchedController implements Initializable {


    @FXML
    private TreeTableColumn<TryoutSchedule, String> dateTT;

    @FXML
    private TreeTableColumn<TryoutSchedule, String> endTimeTT;

    @FXML
    private TreeTableColumn<TryoutSchedule, String> locationTT;

    @FXML
    private TreeTableColumn<TryoutSchedule, String> schedCodeTT;

    @FXML
    private TreeTableColumn<TryoutSchedule, String> startTimeTT;

    @FXML
    private TreeTableView<TryoutSchedule> treeTableView;

    public static List<TryoutSchedule> scheduleList;


    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  {@code null} if the location is not known.
     * @param resources The resources used to localize the root object, or {@code null} if
     *                  the root object was not localized.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<TryoutSchedule> tableData = FXCollections.observableList(scheduleList);
        TreeItem<TryoutSchedule> parent = new TreeItem<>(new TryoutSchedule("", null, null,null, ""));

        for (TryoutSchedule S :
                scheduleList) {
            TreeItem<TryoutSchedule> c1 = new TreeItem<>(S);
            parent.getChildren().add(c1);
        }


        schedCodeTT.setCellValueFactory(
                (TreeTableColumn.CellDataFeatures<TryoutSchedule,String> param) ->
                        new SimpleStringProperty(param.getValue().getValue().getScheduleCode()));

        dateTT.setCellValueFactory(
                (TreeTableColumn.CellDataFeatures<TryoutSchedule,String> param) ->
                        new SimpleStringProperty(param.getValue().getValue().getDate().toString()));

        startTimeTT.setCellValueFactory(
                (TreeTableColumn.CellDataFeatures<TryoutSchedule,String> param) ->
                        new SimpleStringProperty(param.getValue().getValue().getStartTime().toString()));

        endTimeTT.setCellValueFactory(
                (TreeTableColumn.CellDataFeatures<TryoutSchedule,String> param) ->
                        new SimpleStringProperty(param.getValue().getValue().getEndTime().toString()));

        locationTT.setCellValueFactory(
                (TreeTableColumn.CellDataFeatures<TryoutSchedule,String> param) ->
                        new SimpleStringProperty(param.getValue().getValue().getLocation()));
        treeTableView.setRoot(parent);
        treeTableView.setShowRoot(false);
    }
}
