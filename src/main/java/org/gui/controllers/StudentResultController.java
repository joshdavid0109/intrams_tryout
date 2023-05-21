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
    private TreeTableColumn<TryoutSchedule, String> dateTT;

    @FXML
    private TreeTableColumn<TryoutSchedule, String> timeTT;

    @FXML
    private TreeTableColumn<TryoutSchedule, String> locationTT;

    @FXML
    private TreeTableView<TryoutSchedule> treeTableView;

    @FXML
    private Text regID;

    @FXML
    private Text statusText;

    public static int studentID;
    public static String status;

    /**
     * Called to initialize a controller after its root element has been completely processed.
     *
     * @param location  The location used to resolve relative paths for the root object, or {@code null} if the location is not known.
     * @param resources The resources used to localize the root object, or {@code null} if the root object was not localized.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        regID.setText(String.valueOf(studentID));
        try {
            int regId = DataPB.showRegistrationId(studentID);
            regID.setText(String.valueOf(regId));
            int sportsCode = DataPB.getSportsCodeOfUser(studentID);
            int deptId = DataPB.getDeptId(studentID);

            // retrieve da tryout sched
            ArrayList<TryoutSchedule> schedules = DataPB.getTryOutSchedule(sportsCode, deptId);

            TreeItem<TryoutSchedule> rootItem = new TreeItem<>(new TryoutSchedule("null", null, null, null, ""));
            // populate da treee table
            for (TryoutSchedule schedule : schedules) {
                TreeItem<TryoutSchedule> scheduleItem = new TreeItem<>(schedule);
                rootItem.getChildren().add(scheduleItem);
            }
            schedCodeTT.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getValue().getScheduleCode()));
            dateTT.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getValue().getDate().toString()));
            timeTT.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getValue().getStartTime().toString()));
            locationTT.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getValue().getLocation()));



            treeTableView.setRoot(rootItem);
            treeTableView.setShowRoot(false);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        statusText.setText(status);
    }

}
