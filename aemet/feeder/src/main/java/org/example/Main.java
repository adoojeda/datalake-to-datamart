package org.example;

import java.io.IOException;
import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

public class Main {
    public static void main(String[] args) throws IOException {

        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                try{
                    AemetService data = new AemetService();
                }catch(IOException e) {
                    e.getMessage();
                }
            }
        };
        timer.schedule(timerTask, 60, 60*60*1000);
    }

}