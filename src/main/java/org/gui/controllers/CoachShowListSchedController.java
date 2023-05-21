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
import java.util.List;
import java.util.ResourceBundle;

public class CoachShowListSchedController implements Initializable {


    @FXML
    private TreeTableColumn<TryoutSchedDetails, Integer> registrationIdColumn;


    @FXML
    private TreeTableColumn<TryoutSchedDetails, String> scheduleCodeColumn;

    @FXML
    private TreeTableColumn<TryoutSchedDetails, String> statusColumn;

    @FXML
    private TreeTableView<TryoutSchedDetails> treeTableView;

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
      /*  ObservableList<Coach> tableData = FXCollections.observableList(coaches);
        TreeItem<Coach> parent = new TreeItem<>(new Coach("", " "));

        for (Coach c :
                coaches) {
            TreeItem<Coach> c1 = new TreeItem<>(c);
            parent.getChildren().add(c1);
        }


        coachTTColumn.setCellValueFactory(
                (TreeTableColumn.CellDataFeatures<Coach,String> param) ->
                        new SimpleStringProperty(param.getValue().getValue().getName()));

        sportsCodeTTColumn.setCellValueFactory(
                (TreeTableColumn.CellDataFeatures<Coach,String> param) ->
                        new SimpleStringProperty(param.getValue().getValue().getSport()));

        treeTableView.setRoot(parent);
        treeTableView.setShowRoot(false);*/
    }
}
