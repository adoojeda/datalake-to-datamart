package org.example;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AemetService {
    public AemetService() throws IOException {
        String url = "https://opendata.aemet.es/opendata/api/observacion/convencional/todas";
        String apiKey = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG9qZW1hckBnbWFpbC5jb20iLCJqdGkiOiIyNGVmNGFiYS02N2QyLTQzNTItYjlkOS0wMzBjZmIyODhiMjYiLCJpc3MiOiJBRU1FVCIsImlhdCI6MTY3MzM1MTI2MiwidXNlcklkIjoiMjRlZjRhYmEtNjdkMi00MzUyLWI5ZDktMDMwY2ZiMjg4YjI2Iiwicm9sZSI6IiJ9.RxfzqYqpegfrtfuOocvF4qFhKnTicQKacq-TfPFGi38";
        String response = Jsoup.connect(url)
                .ignoreContentType(true)
                .header("accept", "application/json")
                .header("api_key", apiKey)
                .method(Connection.Method.GET)
                .maxBodySize(0).execute().body();

        JsonObject json = new Gson().fromJson(response, JsonObject.class);
        String datos = json.get("datos").getAsString();

        String response2 = Jsoup.connect(datos)
                .ignoreContentType(true)
                .header("accept", "application/json")
                .header("api_key", apiKey)
                .method(Connection.Method.GET)
                .maxBodySize(0).execute().body();

        JsonArray jsonArrays = new Gson().fromJson(response2, JsonArray.class);
        JsonArray f = new JsonArray();
        for (
                JsonElement jsonElement : jsonArrays){
            if(jsonElement.getAsJsonObject().get("lon").getAsDouble() > -16 && jsonElement.getAsJsonObject().get("lon").getAsDouble() < -15){
                if(jsonElement.getAsJsonObject().get("lat").getAsDouble() > 27.5 && jsonElement.getAsJsonObject().get("lat").getAsDouble() < 28.4) {
                    f.add(jsonElement);
                }
            }

        }
        System.out.println(f.toString());
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMdd");
        FileWriter fw = new FileWriter("datalakeDir/" + LocalDate.now().format(format) + ".events");
        fw.write(f.toString());
        fw.close();
    }
}
