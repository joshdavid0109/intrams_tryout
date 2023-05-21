package org.gui.objects;

import java.io.Serializable;

public class RegisteredUser implements Serializable {
    private int regId;
    private int studentId;
    private int department;
    private int appliedSport;
    private String schedCode;
    private String contactNumber;
    private String password;

    public RegisteredUser(int regId, int studentId,  int appliedSport,  String contactNumber, String password) {
        this.regId = regId;
        this.studentId = studentId;
        this.appliedSport = appliedSport;
        this.contactNumber = contactNumber;
        this.password = password;
    }

    public int getRegId() {
        return regId;
    }

    public void setRegId(int regId) {
        this.regId = regId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getDepartment() {
        return department;
    }

    public void setDepartment(int department) {
        this.department = department;
    }

    public int getAppliedSport() {
        return appliedSport;
    }

    public void setAppliedSport(int appliedSport) {
        this.appliedSport = appliedSport;
    }

    public String getSchedCode() {
        return schedCode;
    }

    public void setSchedCode(String schedCode) {
        this.schedCode = schedCode;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return String.format("%-20d%-20d%-20s%-20s%-15s", regId, studentId, department,
                appliedSport, schedCode);
    }
}
