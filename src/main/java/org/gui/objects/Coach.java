package org.gui.objects;

import java.io.Serializable;

public class Coach implements Serializable {
    private int coachNo;
    private String password;
    private int sportsCode;
    private String name;
    private String sport;


    public Coach(int coachNo, String password, int sportsCode) {
        this.coachNo = coachNo;
        this.password = password;
        this.sportsCode = sportsCode;
    }

    public Coach(String name, String sport){
        this.name = name;
        this.sport = sport;
    }

    public int getCoachNo() {
        return coachNo;
    }

    public void setCoachNo(int coachNo) {
        this.coachNo = coachNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getSportsCode() {
        return sportsCode;
    }

    public void setSportsCode(int sportsCode) {
        this.sportsCode = sportsCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }
}
