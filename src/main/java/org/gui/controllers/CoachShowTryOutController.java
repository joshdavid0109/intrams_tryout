package org.gui.controllers;

import javafx.beans.InvalidationListener;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.gui.objects.Coach;
import org.gui.objects.TryoutSchedDetails;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CoachShowTryOutController {
/*
    @FXML
    private TreeTableColumn<TryoutSchedDetails, Integer> registrationIdColumn;

    @FXML
    private TreeTableColumn<TryoutSchedDetails, String> scheduleCodeColumn;

    @FXML
    private TreeTableColumn<TryoutSchedDetails, String> statusColumn;

    @FXML
    private TreeTableView<TryoutSchedDetails> treeTableView;

    private static List<TryoutSchedDetails> tryoutSchedDetails;

    *//**
     * Called to initialize a controller after its root element has been
     * completely processed.
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  {@code null} if the location is not known.
     * @param resources The resources used to localize the root object, or {@code null} if
     *                  the root object was not localized.
     *//*
    public void initialize(URL location, ResourceBundle resources) {

        ObservableList<TryoutSchedDetails> tableData = FXCollections.observableList(tryoutSchedDetails);
        TreeItem<TryoutSchedDetails> parent = new TreeItem<>(new TryoutSchedDetails(0, " ", ""));

        for (TryoutSchedDetails c :
                tryoutSchedDetails) {
            TreeItem<TryoutSchedDetails> c1 = new TreeItem<>(c);
            parent.getChildren().add(c1);
        }

*//*
        registrationIdColumn.setCellValueFactory(
                (TreeTableColumn.CellDataFeatures<TryoutSchedDetails, Integer> param) ->
                        param.getValue().getValue().getRegistrationId());*//*


        scheduleCodeColumn.setCellValueFactory(
                (TreeTableColumn.CellDataFeatures<TryoutSchedDetails,String> param) ->
                        new SimpleStringProperty(param.getValue().getValue().getScheduleCode()));

        statusColumn.setCellValueFactory(
                (TreeTableColumn.CellDataFeatures<TryoutSchedDetails,String> param) ->
                        new SimpleStringProperty(param.getValue().getValue().getStatus()));

        treeTableView.setRoot(parent);
        treeTableView.setShowRoot(false);
    }*/
}
