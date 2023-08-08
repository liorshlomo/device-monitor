package com.ssi.devicemonitor.entity;

import java.time.LocalDateTime;
import java.util.Date;

public class SoftwareDevice extends GeneralDevice{
    String installationDateAndTime;
    public SoftwareDevice(String name, String manufacturer, String version, String installationDateAndTime) {
        super(name, manufacturer, "Software", version);
        setInstallationDateAndTime(installationDateAndTime);
    }
    public String getInstallationDateAndTime() {
        return installationDateAndTime;
    }

    public void setInstallationDateAndTime(String installationDateAndTime) {
        this.installationDateAndTime = installationDateAndTime;
    }
}
