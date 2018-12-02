package entity;

import java.util.Date;

public class CheckingIn {
    private String ckinId;

    private String ckinContent;

    private String ckinTimes;

    private Date ckinDate;

    private String username;

    public String getCkinId() {
        return ckinId;
    }

    public void setCkinId(String ckinId) {
        this.ckinId = ckinId;
    }

    public String getCkinContent() {
        return ckinContent;
    }

    public void setCkinContent(String ckinContent) {
        this.ckinContent = ckinContent;
    }

    public String getCkinTimes() {
        return ckinTimes;
    }

    public void setCkinTimes(String ckinTimes) {
        this.ckinTimes = ckinTimes;
    }

    public Date getCkinDate() {
        return ckinDate;
    }

    public void setCkinDate(Date ckinDate) {
        this.ckinDate = ckinDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}