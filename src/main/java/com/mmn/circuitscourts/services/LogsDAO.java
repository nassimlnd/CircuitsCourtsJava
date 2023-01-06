package com.mmn.circuitscourts.services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class LogsDAO {

    static Connection connection = ConnectionMySQL.getInstance();

    public ArrayList<ArrayList<String>> getAll() throws SQLException {
        String query = "SELECT * FROM logs";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        ArrayList<ArrayList<String>> logs = new ArrayList<>();
        while (resultSet.next()) {
            ArrayList<String> log = new ArrayList<>();
            log.add(String.valueOf(resultSet.getInt(1)));
            log.add(String.valueOf(resultSet.getInt(2)));
            log.add(resultSet.getString(3));
            log.add(String.valueOf(resultSet.getInt(4)));
            log.add(String.valueOf(resultSet.getDate(5)));
            log.add(String.valueOf(resultSet.getTime(6)));
            logs.add(log);
        }
        return logs;
    }

    public ArrayList<ArrayList<String>> getById() throws SQLException {
        String query = "SELECT * FROM logs";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        ArrayList<ArrayList<String>> logs = new ArrayList<>();
        while (resultSet.next()) {
            ArrayList<String> log = new ArrayList<>();
            log.add(String.valueOf(resultSet.getInt(1)));
            log.add(String.valueOf(resultSet.getInt(2)));
            log.add(resultSet.getString(3));
            log.add(String.valueOf(resultSet.getInt(4)));
            log.add(String.valueOf(resultSet.getDate(5)));
            log.add(String.valueOf(resultSet.getTime(6)));
            logs.add(log);
        }
        return logs;
    }


}
