package com.ssi.devicemonitor.entity;

public class HardwareDevice extends GeneralDevice{
    private String location;
    private String macAddress;
    public HardwareDevice(String name, String manufacturer,  String location, String version, String iMacAddress) {
        super(name, manufacturer, "deviHardware", version);
        setLocation(location);
        setMacAddress(iMacAddress);
    }

    public String getLocation() {
        return location;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }
}
