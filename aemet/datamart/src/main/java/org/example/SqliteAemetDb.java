package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SqliteAemetDb {
    Connection conn = null;
    public SqliteAemetDb(String dbPath) throws SQLException {
        String url = "jdbc:sqlite:" + dbPath;
        conn = DriverManager.getConnection(url);
        System.out.println("Connection to SQLite has been established.");
    }

    public Statement init() throws SQLException {
        Statement statement = conn.createStatement();
        createTable(statement);
        return statement;
    }

    private void createTable(Statement statement) throws SQLException {
        statement.execute("CREATE TABLE IF NOT EXISTS maximum (" +
                "time TEXT," +
                "place TEXT," +
                "station TEXT," +
                "maxvalue TEXT" +
                ");");
        statement.execute("CREATE TABLE IF NOT EXISTS minimum (" +
                "time TEXT," +
                "place TEXT," +
                "station TEXT," +
                "minvalue TEXT" +
                ");");
    }

    public void insertMaxs(Weather weather) throws  SQLException{
        Statement statement = conn.createStatement();
        statement.execute(DmlTranslate.insertMax(weather));
    }

    public void insertMins(Weather weather) throws  SQLException{
        Statement statement = conn.createStatement();
        statement.execute(DmlTranslate.insertMin(weather));
    }

}
