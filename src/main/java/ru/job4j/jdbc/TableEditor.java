package ru.job4j.jdbc;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection cn;

    private final Properties properties;

    public TableEditor(Properties properties) {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() {
        try (InputStream in = TableEditor.class.getClassLoader().getResourceAsStream("app.properties")) {
            properties.load(in);
            Class.forName(properties.getProperty("driver-class-name"));
            cn = DriverManager.getConnection(
                    properties.getProperty("url"),
                    properties.getProperty("username"),
                    properties.getProperty("password")
            );
        } catch (Exception e) {
            throw new IllegalStateException();
        }
    }

    public void query(String query) {
        try (Statement st = cn.createStatement()) {
            st.execute(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createTable(String tableName) {
        query(String.format("create table if not exists %s();", tableName));
    }

    public void dropTable(String tableName) {
        query(String.format("drop table if exists %s;", tableName));
    }

    public void addColumn(String tableName, String columnName, String type) {
        query(String.format("alter table %s add column %s %s;",
                tableName, columnName, type));
    }

    public void dropColumn(String tableName, String columnName) {
        query(String.format("alter table %s drop column %s;",
                tableName, columnName));
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) {
        query(String.format("alter table %s rename column %s to %s;",
                tableName, columnName, newColumnName));
    }


    public static String getTableScheme(Connection connection, String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (cn != null) {
            cn.close();
        }
    }
}