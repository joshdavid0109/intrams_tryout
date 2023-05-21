package org.gui.controllers;

import javafx.beans.InvalidationListener;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.gui.objects.Coach;
import org.gui.objects.TryoutSchedDetails;

import java.io.InterruptedIOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CoachShowTryOutController implements Initializable {

    @FXML
    private TreeTableColumn<TryoutSchedDetails, Integer> registrationIdColumn;

    @FXML
    private TreeTableColumn<TryoutSchedDetails, String> scheduleCodeColumn;

    @FXML
    private TreeTableColumn<TryoutSchedDetails, String> statusColumn;

    @FXML
    private TreeTableColumn<TryoutSchedDetails, String> fullNameColumn;

    @FXML
    private TreeTableView<TryoutSchedDetails> treeTableView;

    @FXML
    private Button rejectBtn;

    @FXML
    private Button acceptBtn;


    private static List<TryoutSchedDetails> tryoutSchedDetails;

    public static List<TryoutSchedDetails> scheduleList;
    TreeItem<TryoutSchedDetails> parent;

    public static int coachNo;


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


        System.out.println("this is running in the background");
        parent = new TreeItem<>(new TryoutSchedDetails(0, "", ""));

        if (scheduleList != null){
            for (TryoutSchedDetails s :
                    scheduleList) {
                TreeItem<TryoutSchedDetails> c1 = new TreeItem<>(s);
                parent.getChildren().add(c1);
            }


            registrationIdColumn.setCellValueFactory(
                    (TreeTableColumn.CellDataFeatures<TryoutSchedDetails, Integer> param) ->
                            new SimpleObjectProperty<>(param.getValue().getValue().getRegistrationId()));

            fullNameColumn.setCellValueFactory(
                    (TreeTableColumn.CellDataFeatures<TryoutSchedDetails, String> param) ->
                            new SimpleStringProperty(param.getValue().getValue().getFullName()));

            scheduleCodeColumn.setCellValueFactory(
                    (TreeTableColumn.CellDataFeatures<TryoutSchedDetails, String> param) ->
                            new SimpleStringProperty(param.getValue().getValue().getScheduleCode()));
            statusColumn.setCellValueFactory(
                    (TreeTableColumn.CellDataFeatures<TryoutSchedDetails, String> param) ->
                            new SimpleStringProperty(param.getValue().getValue().getStatus()));


            treeTableView.setRoot(parent);
            treeTableView.setShowRoot(false);

            // Retrieve the selection model
            TreeTableView.TreeTableViewSelectionModel<TryoutSchedDetails> selectionModel = treeTableView.getSelectionModel();

            // Add a selection listener to the selection model
            selectionModel.selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue != null) {
                    TryoutSchedDetails selectedSchedDetails = newValue.getValue();

                    rejectBtn.setOnMouseClicked(event -> {
                        if (selectedSchedDetails.getStatus().equalsIgnoreCase("rejected")) {
                            DataPB.updateStatusOfStudent(selectedSchedDetails.getRegistrationId(), "PENDING");
                        } else {
                            DataPB.updateStatusOfStudent(selectedSchedDetails.getRegistrationId(), "REJECTED");
                        }


                        refreshTable();

                    });


                }
            });

            // Retrieve the selection model
            TreeTableView.TreeTableViewSelectionModel<TryoutSchedDetails> model = treeTableView.getSelectionModel();

            // Add a selection listener to the selection model
            selectionModel.selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue != null) {
                    TryoutSchedDetails selectedSchedDetails = newValue.getValue();

                    acceptBtn.setOnMouseClicked(event -> {
                        if (selectedSchedDetails.getStatus().equalsIgnoreCase("accepted")) {
                            DataPB.updateStatusOfStudent(selectedSchedDetails.getRegistrationId(), "PENDING");
                        } else {
                            DataPB.updateStatusOfStudent(selectedSchedDetails.getRegistrationId(), "ACCEPTED");
                        }

                        refreshTable();

                    });

                }
            });
        }
    }

    private void refreshTable() {
        scheduleList.clear();
        try {
            scheduleList = DataPB.getTryOutSchedDetails(coachNo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        parent = new TreeItem<>(new TryoutSchedDetails(0, "", ""));

        for (TryoutSchedDetails s :
                scheduleList) {
            TreeItem<TryoutSchedDetails> c1 = new TreeItem<>(s);
            parent.getChildren().add(c1);
        }

        treeTableView.setRoot(parent);
        treeTableView.setShowRoot(false);
    }

    public void reject(ActionEvent actionEvent) {
    }

    public void accept(ActionEvent actionEvent) {


    }
}
