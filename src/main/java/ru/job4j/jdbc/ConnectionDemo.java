package ru.job4j.jdbc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ConnectionDemo {
    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public ConnectionDemo(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            String line;
            while ((line = read.readLine()) != null) {
                String[] properties = line.split("=");
                values.put(properties[0], properties[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Map<String, String> getValues() {
        return values;
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        ConnectionDemo connectionDemo = new ConnectionDemo("src/main/resources/app.properties");
        connectionDemo.load();
        Map<String, String> values = connectionDemo.getValues();
        Class.forName(values.get("driver-class-name"));
        try (Connection connection = DriverManager.getConnection(values.get("url"), values.get("username"), values.get("password"))) {
            DatabaseMetaData metaData = connection.getMetaData();
            System.out.println(metaData.getUserName());
            System.out.println(metaData.getURL());
        }
    }
}
