package com.ssi.devicemonitor.entity;

public class GeneralDevice extends Device {
    public GeneralDevice(String name,String manufacturer, String deviceType, String version) {
        super(name, manufacturer, deviceType, version);
    }
    public GeneralDevice(String name) {
        super(name);
    }
}
