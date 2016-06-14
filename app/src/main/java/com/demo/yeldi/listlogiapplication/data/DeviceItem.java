package com.demo.yeldi.listlogiapplication.data;

/**
 * Created by pudumai on 6/13/2016.
 */
public class DeviceItem {

    public String DevicType;
    public String Model;
    public String Name;

    public String getDevicType() {
        return DevicType;
    }

    public String getModel() {
        return Model;
    }

    public String getName() {
        return Name;
    }

    public void setDevicType(String devicType) {
        DevicType = devicType;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setModel(String model) {
        Model = model;
    }
}
