package org.example;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DatalakeReader {

    List<Weather> weathers = new ArrayList<>();

    public List<Weather> DatalakeReader() throws IOException{
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMdd");
        FileReader fr = new FileReader("datalakeDir/" + LocalDate.now().format(format) + ".events");
        BufferedReader reader = new BufferedReader(fr);
        String lines = reader.readLine();
        //System.out.println(lines);
        fr.read();
        fr.close();

        JsonArray jsonArrays = new Gson().fromJson(lines, JsonArray.class);

        for (JsonElement jsonElement : jsonArrays) {
            Weather weather = new Weather();
            weather.setTime(String.valueOf(jsonElement.getAsJsonObject().get("fint")));
            weather.setPlace(String.valueOf(jsonElement.getAsJsonObject().get("ubi")));
            weather.setStation(String.valueOf(jsonElement.getAsJsonObject().get("idema")));
            weather.setMaxvalue(String.valueOf(jsonElement.getAsJsonObject().get("tamax")));
            weather.setMinvalue(String.valueOf(jsonElement.getAsJsonObject().get("tamin")));
            weathers.add(weather);
        }
        return weathers;
    }

}
