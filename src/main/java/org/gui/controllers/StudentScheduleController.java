package org.gui.controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import org.gui.objects.TryoutSchedDetails;
import org.gui.objects.TryoutSchedule;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class StudentScheduleController implements Initializable {

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

    @FXML
    private Button withdrawBtn;

    @FXML
    private Button acceptBtn;

    public static List<TryoutSchedule> schedules;

    public static int studID;


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
            ObservableList<TryoutSchedule> tableData = FXCollections.observableList(schedules);
            TreeItem<TryoutSchedule> parent = new TreeItem<>(new TryoutSchedule("", null, null,null, ""));
        System.out.println(schedules);

            if (schedules != null) {
                for (TryoutSchedule S :
                        schedules) {
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
                // Retrieve the selection model
                TreeTableView.TreeTableViewSelectionModel<TryoutSchedule> selectionModel = treeTableView.getSelectionModel();

                // Add a selection listener to the selection model
                selectionModel.selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        TryoutSchedule selectedSchedDetails = newValue.getValue();

                        acceptBtn.setOnMouseClicked(event -> {
                            DataPB.updateSched(DataPB.getRegID(studID), selectedSchedDetails.getScheduleCode());
                        });

                    }
                });

                // Retrieve the selection model
                TreeTableView.TreeTableViewSelectionModel<TryoutSchedule> model = treeTableView.getSelectionModel();

                // Add a selection listener to the selection model
                selectionModel.selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        TryoutSchedule selectedSchedDetails = newValue.getValue();

                        withdrawBtn.setOnMouseClicked(event -> {
                            DataPB.updateSched(DataPB.getRegID(studID),"");
                        });

                    }
                });
        }
    }
}
