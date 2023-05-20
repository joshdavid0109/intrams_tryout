package org.gui.objects;

import java.io.Serializable;

public class TryoutSchedDetails implements Serializable {
    private int registrationId;
    private String scheduleCode;
    private String status;

    public TryoutSchedDetails(int registrationId, String scheduleCode, String status) {
        this.registrationId = registrationId;
        this.scheduleCode = scheduleCode;
        this.status = status;
    }

    public String getScheduleCode() {
        return scheduleCode;
    }

    public void setScheduleCode(String scheduleCode) {
        this.scheduleCode = scheduleCode;
    }

    public int getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(int registrationId) {
        this.registrationId = registrationId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return String.format("%-20d%-20s%-20s",registrationId, scheduleCode, status);
    }
}
