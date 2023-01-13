package org.example;

public class DmlTranslate {
    public DmlTranslate(){}
    private static final String INSERT_MAXIMUM =
            "INSERT INTO maximum(time, place, station, maxvalue) VALUES('%s', '%s', '%s', '%s')";
    public static String insertMax(Weather weather){
        return String.format(INSERT_MAXIMUM,
                weather.getTime(),
                weather.getPlace(),
                weather.getStation(),
                weather.getMaxvalue());
    }

    private static final String INSERT_MINIMUM =
            "INSERT INTO minimum(time, place, station, minvalue) VALUES('%s', '%s', '%s', '%s')";
    public static String insertMin(Weather weather){
        return String.format(INSERT_MINIMUM,
                weather.getTime(),
                weather.getPlace(),
                weather.getStation(),
                weather.getMinvalue());
    }


}
