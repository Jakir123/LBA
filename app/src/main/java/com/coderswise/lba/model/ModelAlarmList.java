package com.coderswise.lba.model;

/**
 * Created by Jakir Hossain
 * Date: 2/27/2017.
 */
public class ModelAlarmList {
    private int alarmId;
    private String locationTitle;
    private String alarmNotification;
    private String latitude;
    private String longitude;
    private int state;

    public ModelAlarmList(int alarmId, String locationTitle, String alarmNotification, String latitude, String longitude, int state) {
        this.alarmId = alarmId;
        this.locationTitle = locationTitle;
        this.alarmNotification = alarmNotification;
        this.latitude = latitude;
        this.longitude = longitude;
        this.state = state;
    }

    public ModelAlarmList(String locationTitle, String alarmNotification, String latitude, String longitude, int state) {
        this.locationTitle = locationTitle;
        this.alarmNotification = alarmNotification;
        this.latitude = latitude;
        this.longitude = longitude;
        this.state = state;
    }

    public int getAlarmId() {
        return alarmId;
    }

    public String getLocationTitle() {
        return locationTitle;
    }

    public void setLocationTitle(String locationTitle) {
        this.locationTitle = locationTitle;
    }

    public String getAlarmNotification() {
        return alarmNotification;
    }

    public void setAlarmNotification(String alarmNotification) {
        this.alarmNotification = alarmNotification;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
