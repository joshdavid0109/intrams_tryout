package org.gui.controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.text.Text;
import org.gui.objects.TryoutSchedule;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class StudentResultController implements Initializable {

    @FXML
    private TreeTableColumn<String, String> schedCodeTT;

    @FXML
    private TreeTableColumn<String, String> dateTT;

    @FXML
    private TreeTableColumn<String, String> timeTT;

    @FXML
    private TreeTableColumn<String, String> locationTT;

    @FXML
    private TreeTableView<String> treeTableView;

    @FXML
    private Text regID;



    @FXML
    private Text statusText;


    public static int studentID;
    public static String status;


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
        System.out.println("test");
        regID.setText(String.valueOf(studentID));
        try {
            int sportsCode = DataPB.getSportsCodeOfUser(studentID);
            int deptId = DataPB.getDeptId(studentID);

            DataPB.getTryOutSchedule(sportsCode, deptId);



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        statusText.setText(status);
    }


}
