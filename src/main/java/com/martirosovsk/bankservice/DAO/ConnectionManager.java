package com.martirosovsk.bankservice.DAO;

import org.springframework.beans.factory.annotation.Value;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    @Value("spring.datasource.url")
    private static String url;
    @Value("spring.datasource.username")
    private static String login;
    @Value("spring.datasource.password")
    private static String password;
    @Value("spring.datasource.driver-class-name")
    private static String className;

    /**
     * method for obtaining connection
     *
     * @param driverName driverName
     * @param url        url of DB
     * @param login      login
     * @param password   password
     * @return Connection to DB
     */
    public static Connection getConnection(String driverName, String url, String login, String password) {
        try {
            Class.forName(driverName);
            return DriverManager.getConnection(url, login, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Невозможно подключиться к базе");
        }
    }

    /**
     * method for closing connection
     *
     * @param con connection to close
     */
    public static void closeConnection(Connection con) {
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
