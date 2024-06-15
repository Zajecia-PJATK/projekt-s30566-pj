package pj.s30566.model;

import pj.s30566.utils.SystemIdentifier;

import java.time.LocalDateTime;

public class Session {
    private int sessionId;
    private String systemUsername;
    private String appUsername;
    private String systemMac;
    private LocalDateTime time;

    public Session(String appUsername){
        this.systemUsername = SystemIdentifier.getUsername();
        this.appUsername = appUsername;
        this.systemMac = SystemIdentifier.getMacAddress();
        this.time = LocalDateTime.now();
    }

    public Session(int sessionId, String systemUsername, String appUsername, String systemMac, LocalDateTime time){
        this.sessionId = sessionId;
        this.systemUsername = systemUsername;
        this.appUsername = appUsername;
        this.systemMac = SystemIdentifier.getMacAddress();
        this.time = time;
    }

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public String getSystemUsername() {
        return systemUsername;
    }

    public void setSystemUsername(String systemUsername) {
        this.systemUsername = systemUsername;
    }

    public String getAppUsername() {
        return appUsername;
    }

    public void setAppUsername(String appUsername) {
        this.appUsername = appUsername;
    }

    public String getSystemMac() {
        return systemMac;
    }

    public void setSystemMac(String systemMac) {
        this.systemMac = systemMac;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}
