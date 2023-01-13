package org.example;

public class Weather {
    private String time;
    private String place;
    private String station;
    private String maxvalue;
    private String minvalue;

    public Weather(String time, String place, String station, String maxvalue, String minvalue) {
        this.time = time;
        this.place = place;
        this.station = station;
        this.maxvalue = maxvalue;
        this.minvalue = minvalue;
    }

    public Weather() {}

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public String getMaxvalue() {
        return maxvalue;
    }

    public void setMaxvalue(String maxvalue) {
        this.maxvalue = maxvalue;
    }

    public String getMinvalue() {
        return minvalue;
    }

    public void setMinvalue(String minvalue) {
        this.minvalue = minvalue;
    }
}

