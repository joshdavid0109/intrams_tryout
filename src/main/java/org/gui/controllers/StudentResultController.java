package org.gui.controllers;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.text.Text;
import org.gui.objects.TryoutSchedule;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class StudentResultController implements Initializable {

    @FXML
    private TreeTableColumn<TryoutSchedule, String> schedCodeTT;

    @FXML
    private TreeTableColumn<TryoutSchedule, String> sportTT;

    @FXML
    private TreeTableColumn<TryoutSchedule, String> dateTT;

    @FXML
    private TreeTableColumn<TryoutSchedule, String> startTimeTT;

    @FXML
    private TreeTableColumn<TryoutSchedule, String> endTimeTT;

    @FXML
    private TreeTableColumn<TryoutSchedule, String> locationTT;

    @FXML
    private TreeTableView<TryoutSchedule> treeTableView;

    @FXML
    private Text regID;

    @FXML
    private Text statusText;

    public static int studentID;

    /**
     * Called to initialize a controller after its root element has been completely processed.
     *
     * @param location  The location used to resolve relative paths for the root object, or {@code null} if the location is not known.
     * @param resources The resources used to localize the root object, or {@code null} if the root object was not localized.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {

            ArrayList<TryoutSchedule> schedules = DataPB.getTryOutSchedule(studentID);

            TreeItem<TryoutSchedule> rootItem = new TreeItem<>(new TryoutSchedule("null", null, null, null, ""));

            for (TryoutSchedule schedule : schedules) {
                TreeItem<TryoutSchedule> scheduleItem = new TreeItem<>(schedule);
                rootItem.getChildren().add(scheduleItem);
            }
            schedCodeTT.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getValue().getScheduleCode()));
            sportTT.setCellValueFactory(param -> {
                int sportsCode = param.getValue().getValue().getSportsCode();
                String sportsName = DataPB.getStringSportsCode(sportsCode);
                return new ReadOnlyStringWrapper(sportsName);
            });
            dateTT.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getValue().getDate().toString()));
            startTimeTT.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getValue().getStartTime().toString()));
            endTimeTT.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getValue().getEndTime().toString()));
            locationTT.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getValue().getLocation()));

            treeTableView.setRoot(rootItem);
            treeTableView.setShowRoot(false);


            treeTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue != null) {

                    TryoutSchedule selectedSchedule = newValue.getValue();

                    System.out.println("pindot");

                    int registrationId;
                    String status2;
                    try {
                        System.out.println("sc" +selectedSchedule.getSportsCode());
                        registrationId = DataPB.getRegID(studentID, selectedSchedule.getSportsCode());
                        status2 = DataPB.showStatusOfStudent(studentID, selectedSchedule.getSportsCode());
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }

                    regID.setText(String.valueOf(registrationId));
                    statusText.setText(status2);
                }
            });
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
