package com.example.module6_jdbc;

import com.example.module6_jdbc.conections.Database;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitService {


    // Зчитуємо SQL файл для того щоб передати запити в стрінгове значення
    private static String readCreatTableQuery(String path) throws FileNotFoundException {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String sqlQuery = "";
            while ((sqlQuery = reader.readLine()) != null) {
                stringBuilder.append(sqlQuery).append("\n");
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return stringBuilder.toString();
    }


    private static void createTableQuery(String query) {
        Connection connection = Database.getConnection();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            int count = 0;
            while (resultSet.next()) {
                System.out.println("Create table = " + count);
                count++;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static void initDataBase() throws FileNotFoundException {
        try {
            String pathFileInit = "src/Main/java/com/example/module6_jdbc/SQLSkripts/inti_db.sql";
            createTableQuery(readCreatTableQuery(pathFileInit));
            System.out.println("Таблиці ініціалізовано");
        } catch (Exception e) {
            throw new FileNotFoundException("Файл не знайдено");
        }
    }

}


//Завдання №2 - створити клас для ініціалізації структури БД
//        Створи клас з назвою DatabaseInitService. У цьому класі має бути метод public static void Main(String[] args),
//        який зчитуватиме файл sql/init_db.sql і виконуватиме запити з цього класу у БД.
//        Для роботи з БД використовуй написаний раніше тобою клас Database.
//        Результат запуску цього класу - проініцалізована база даних з коректно створеними таблицями та зв'язками між цими таблицями.