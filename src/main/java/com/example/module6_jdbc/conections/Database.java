package com.example.module6_jdbc.conections;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class Database {

//
//    Завдання №1 - створити утилітний клас для роботи з БД
//    Створи клас-сінглтон Database, який інкапсулює у собі логіку для роботи з БД.
//    Під час створення екземпляру цього класу має відбуватись підключення до СУБД та зберігатись екземпляр
//    Connection. Має бути можливість отримати Connection викликом методу getConnection().
//    Наприклад, ось так:  Connection conn = Database.getInstance().getConnection()


    private static final Database INSTANCE = new Database();

    private static Connection connection;

    private Database() {
        String url = Database.getConnectionDB();
        String user = Database.getUserDB();
        String pass = Database.getPasswordDB();

        try {
            connection = DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            System.out.println(String.format("SQL exception. Can not create connection. Reason: %s", e.getMessage()));
            throw new RuntimeException("Can not create connection.");
        }
    }

    public static Database getInstance() {
        return INSTANCE;
    }

    public static Connection getConnection() {
        return connection;
    }


    public static String getConnectionDB() {
        try (InputStream input = Database.class.getClassLoader()
                .getResourceAsStream("application.properties")) {

            Properties prop = new Properties();

            if (input == null) {
                System.out.println("Sorry, unable to find application.properties");
                return null;
            }

            prop.load(input);

            return new StringBuilder("jdbc:postgresql://")
                    .append(prop.getProperty("postgres.db.host"))
                    .append(":")
                    .append(prop.getProperty("postgres.db.port"))
                    .append("/")
                    .append(prop.getProperty("postgres.db.database"))
                    .append("?currentSchema=public")
                    .toString();
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static String getUserDB() {
        try (InputStream input = Database.class.getClassLoader()
                .getResourceAsStream("application.properties")) {

            Properties prop = new Properties();

            if (input == null) {
                System.out.println("Sorry, unable to find application.properties");
                return null;
            }

            prop.load(input);

            return prop.getProperty("postgres.db.username");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static String getPasswordDB() {
        try (InputStream input = Database.class.getClassLoader()
                .getResourceAsStream("application.properties")) {

            Properties prop = new Properties();

            if (input == null) {
                System.out.println("Sorry, unable to find application.properties");
                return null;
            }

            prop.load(input);

            return prop.getProperty("postgres.db.password");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

}
