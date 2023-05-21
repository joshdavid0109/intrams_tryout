package org.gui.controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import org.gui.objects.Coach;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AdminShowSportsAndCoordinatorsController implements Initializable {

    @FXML
    private TreeTableColumn<Coach, String> coachTTColumn;

    @FXML
    private TreeTableColumn<Coach, String> sportsCodeTTColumn;

    @FXML
    private TreeTableView<Coach> treeTableView;

    public static List<Coach> coaches;

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

        ObservableList<Coach> tableData = FXCollections.observableList(coaches);
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
        treeTableView.setShowRoot(false);
    }
}
