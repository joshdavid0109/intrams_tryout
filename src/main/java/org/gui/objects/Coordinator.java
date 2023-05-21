package org.gui.objects;

import java.io.Serializable;

public class Coordinator implements Serializable {
    private int coordinatorID;
    private String firstName;
    private String lastName;
    private int deptID;

    public Coordinator(int coordinatorID, String firstName, String lastName, int deptID) {
        this.coordinatorID = coordinatorID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.deptID = deptID;
    }

    @Override
    public String toString() {
        return coordinatorID+" "+firstName+" "+lastName+" "+deptID;
    }

    public int getCoordinatorID() {
        return coordinatorID;
    }

    public void setCoordinatorID(int coordinatorID) {
        this.coordinatorID = coordinatorID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getDeptID() {
        return deptID;
    }

    public void setDeptID(int deptID) {
        this.deptID = deptID;
    }
}
