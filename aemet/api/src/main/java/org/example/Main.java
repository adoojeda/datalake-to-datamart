package org.example;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import spark.Spark;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;


import static spark.Spark.*;

public class Main {

    public static void main(String[] args) {
        Gson gson = new Gson();

        after((request, response) -> {
            response.type("application/json");
        });

        notFound((request, response) -> {
            response.status(404);
            return gson.toJson("El recurso al que trata de acceder no existe");
        });

        String url = "jdbc:sqlite:" + "/Users/adoojeda/Desktop/GCID/Primer semestre/DACD/aemet/datamart/src/main/java/org/example/datamart.db";

        Spark.get("/v1/places/with-max-temperature", ((req, res) -> {
            String from = req.queryParams("from");
            String to = req.queryParams("to");
            Connection conn = null;
            JsonArray response = new JsonArray();
            try{
                conn = DriverManager.getConnection(url);
                Statement stmt = conn.createStatement();
                ResultSet resultSet = stmt.executeQuery("SELECT time, place, station, maxvalue FROM maximum WHERE time BETWEEN" + from + "AND" + to + "ORDER BY maxvalue DESC LIMIT 1");
                while (resultSet.next()){
                    JsonObject jsonObject = new JsonObject();
                    String time = resultSet.getString("time");
                    String place = resultSet.getString("place");
                    String station = resultSet.getString("station");
                    String maxvalue = resultSet.getString("maxvalue");

                    jsonObject.addProperty("time", time);
                    jsonObject.addProperty("place", place);
                    jsonObject.addProperty("station", station);
                    jsonObject.addProperty("maxvalue", maxvalue);

                    response.add(jsonObject);
                }
            }catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            return response;
        }));

        Spark.get("/v1/places/with-min-temperature", ((req, res) -> {
            String from = req.queryParams("from");
            String to = req.queryParams("to");
            Connection conn = null;
            JsonArray response = new JsonArray();
            try{
                conn = DriverManager.getConnection(url);
                Statement stmt = conn.createStatement();
                ResultSet resultSet = stmt.executeQuery("SELECT time, place, station, minvalue FROM minimum WHERE time BETWEEN" + from + "AND" + to + "ORDER BY minvalue DES ASC LIMIT 1");
                while (resultSet.next()){
                    JsonObject jsonObject = new JsonObject();
                    String time = resultSet.getString("time");
                    String place = resultSet.getString("place");
                    String station = resultSet.getString("station");
                    String minvalue = resultSet.getString("minvalue");

                    jsonObject.addProperty("time", time);
                    jsonObject.addProperty("place", place);
                    jsonObject.addProperty("station", station);
                    jsonObject.addProperty("maxvalue", minvalue);

                    response.add(jsonObject);
                }
            }catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            return response;
        }));



    }
}
