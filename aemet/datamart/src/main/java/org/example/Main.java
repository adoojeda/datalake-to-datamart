package org.example;


import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Main {
    public static void main(String[] args) throws Exception {

        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                try{
                    DatalakeReader reader = new DatalakeReader();
                    List<Weather> weathers = reader.DatalakeReader();
                    SqliteAemetDb sq = new SqliteAemetDb("/Users/adoojeda/Desktop/GCID/Primer semestre/DACD/aemet/datamart/src/main/java/org/example/datamart.db");
                    sq.init();
                    for (Weather weather: weathers) {
                        sq.insertMaxs(weather);
                        sq.insertMins(weather);
                    }
                }catch(IOException | SQLException e) {
                    e.getMessage();
                }
            }
        };
        timer.schedule(timerTask, 60, 60*60*1000);
    }
}