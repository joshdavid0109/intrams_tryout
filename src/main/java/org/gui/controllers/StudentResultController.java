package org.gui.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class StudentResultController implements Initializable {


    @FXML
    private TreeTableColumn<?, ?> dateTT;

    @FXML
    private TreeTableColumn<?, ?> locationTT;

    @FXML

    private Text regID;

    @FXML
    private TreeTableColumn<?, ?> schedCodeTT;

    @FXML
    private Text statusText;

    @FXML
    private TreeTableColumn<?, ?> timeTT;

    @FXML
    private TreeTableView<?> treeTableView;

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
        statusText.setText(status);
    }
}
