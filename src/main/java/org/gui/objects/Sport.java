package org.gui.objects;

import java.io.Serializable;

public class Sport implements Serializable {
    private int sportsCode;
    private String sportsName;
    private String sportsDescription;

    public Sport(int sportsCode, String sportsName, String sportsDescription) {
        this.sportsCode = sportsCode;
        this.sportsName = sportsName;
        this.sportsDescription = sportsDescription;
    }

    @Override
    public String toString() {
        return sportsCode+" "+sportsName+" "+sportsDescription;
    }

    public int getSportsCode() {
        return sportsCode;
    }

    public void setSportsCode(int sportsCode) {
        this.sportsCode = sportsCode;
    }

    public String getSportsName() {
        return sportsName;
    }

    public void setSportsName(String sportsName) {
        this.sportsName = sportsName;
    }

    public String getSportsDescription() {
        return sportsDescription;
    }

    public void setSportsDescription(String sportsDescription) {
        this.sportsDescription = sportsDescription;
    }
}
