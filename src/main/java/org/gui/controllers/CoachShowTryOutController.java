package org.gui.controllers;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import org.gui.objects.Coach;
import org.gui.objects.TryoutSchedDetails;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CoachShowTryOutController implements Initializable {

    @FXML
    private TreeTableColumn<TryoutSchedDetails, String> registrationIdColumn;

    @FXML
    private TreeTableColumn<TryoutSchedDetails, String> scheduleCodeColumn;

    @FXML
    private TreeTableColumn<TryoutSchedDetails, String> statusColumn;

    @FXML
    private TreeTableView<TryoutSchedDetails> treeTableView;

    private static List<TryoutSchedDetails> tryoutSchedDetails;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<TryoutSchedDetails> tableData = FXCollections.observableList(tryoutSchedDetails);
        TreeItem<TryoutSchedDetails> parent = new TreeItem<>(new TryoutSchedDetails(0, " ", ""));

        for (TryoutSchedDetails c :
                tryoutSchedDetails) {
            TreeItem<TryoutSchedDetails> c1 = new TreeItem<>(c);
            parent.getChildren().add(c1);
        }


/*        registrationIdColumn.setCellValueFactory(
                (TreeTableColumn.CellDataFeatures<TryoutSchedDetails,String> param) ->
                          new IntegerProperty(param.getValue().getValue().getRegistrationId()));
*//*                        new SimpleStringProperty(param.getValue().getValue().getRegistrationId()));*/

        scheduleCodeColumn.setCellValueFactory(
                (TreeTableColumn.CellDataFeatures<TryoutSchedDetails,String> param) ->
                        new SimpleStringProperty(param.getValue().getValue().getScheduleCode()));

        statusColumn.setCellValueFactory(
                (TreeTableColumn.CellDataFeatures<TryoutSchedDetails,String> param) ->
                        new SimpleStringProperty(param.getValue().getValue().getStatus()));

        treeTableView.setRoot(parent);
        treeTableView.setShowRoot(false);
    }
}
