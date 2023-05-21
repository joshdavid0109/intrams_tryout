package org.gui.objects;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

public class TryoutSchedule implements Serializable {
    private String scheduleCode;
    private int sportsCode;
    private int deptTryoutCode;
    private Date date;
    private Time startTime;
    private Time endTime;
    private String location;
    private int totalStudents;

    public TryoutSchedule(String scheduleCode, int sportsCode, int deptTryoutCode, Date date, Time startTime,
                          Time endTime, String location, int totalStudents) {
        this.scheduleCode = scheduleCode;
        this.sportsCode = sportsCode;
        this.deptTryoutCode = deptTryoutCode;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.location = location;
        this.totalStudents = totalStudents;
    }

    //
    public TryoutSchedule(int sportsCode, int deptTryoutCode, Date date, Time startTime,
                          Time endTime, String location) {
        this.sportsCode = sportsCode;
        this.deptTryoutCode = deptTryoutCode;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.location = location;
    }

    public String getScheduleCode() {
        return scheduleCode;
    }

    public void setScheduleCode(String scheduleCode) {
        this.scheduleCode = scheduleCode;
    }

    public int getSportsCode() {
        return sportsCode;
    }

    public void setSportsCode(int sportsCode) {
        this.sportsCode = sportsCode;
    }

    public int getDeptTryoutCode() {
        return deptTryoutCode;
    }

    public void setDeptTryoutCode(int deptTryoutCode) {
        this.deptTryoutCode = deptTryoutCode;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getTotalStudents() {
        return totalStudents;
    }

    public void setTotalStudents(int totalStudents) {
        this.totalStudents = totalStudents;
    }

    @Override
    public String toString() {
        return String.format("%-20d%-20d%-20s%-15s%-10s%-45s", sportsCode, deptTryoutCode,
                date, startTime, endTime, location);
    }
}
