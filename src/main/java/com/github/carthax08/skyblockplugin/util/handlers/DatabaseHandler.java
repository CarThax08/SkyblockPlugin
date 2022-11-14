package com.github.carthax08.skyblockplugin.util.handlers;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseHandler {

    public HikariDataSource dataSource;

    public DatabaseHandler(String type, String url, int port, String username, String password, String database){
        HikariConfig config = new HikariConfig();
        switch (type.toLowerCase()){
            case "mysql":
                config.setJdbcUrl("jdcb:mysql://" + url + "/" + port + "/" + database);
                config.setUsername(username);
                config.setPassword(password);
                config.setPoolName("Skyblock-DB");
                break;
            case "mariadb":
                config.setUsername(username);
                config.setPassword(password);
                config.setJdbcUrl("jdcb:mariadb://" + url + "/" + port + "/" + database);
                config.setPoolName("Skyblock-DB");
                break;
        }

        dataSource = new HikariDataSource(config);

    }

    public boolean executeStatement(String statement){
        try(Connection connection = dataSource.getConnection()){

            PreparedStatement pstatement = connection.prepareStatement(statement);
            pstatement.execute();
            return true;

        } catch (SQLException ignored) {
            return false;
        }
    }

    public ResultSet executeStatementWithResults(String statement){
        try(Connection connection = dataSource.getConnection()){

            PreparedStatement pstatement = connection.prepareStatement(statement);
            return pstatement.executeQuery();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void disconnect(){
        dataSource.close();
    }
}
