package table_Worker;

import com.example.module6_jdbc.conections.Database;
import tables_DTO.Project;
import tables_DTO.Worker;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseQueryService {

    /**
     * Всі необхідні DTO або Описові класи які описутють поля таблиці з БД ДЗ 4 MegaSoft зніходяться
     * за даним посмланням src/main/java/tables_DTO/*
     * <p>
     * Клас підключення до БД знаходиться за посиланням src/main/java/com/example/module6_jdbc/conections/Database.java
     * <p>
     * Всі скріпти через JDBC для стврення та ініціалізації даних SQL знаходяться за посиланням src/main/java/table_Worker/*
     * <p>
     * SQL запити з ДЗ4 знаходяться за посиланням src/main/java/com/example/module6_jdbc/SQLSkripts/*
     * <p>
     * В даному класі DatabaseQueryServiceWithoutReader знаходяться методи для відпрацювання кожного із
     * скриптів для вибірки даних src/main/java/com/example/module6_jdbc/SQLSkripts/*
     */

    public static List<Project> getLongestProjectWithReadFile() {
        Connection connection;
        List<Project> projectList = new ArrayList();
        try {
            String scriptPath = "src/main/java/com/example/module6_jdbc/SQLSkripts/find_longest_project.sql";
            String sqlScript = new String(Files.readAllBytes(Path.of(scriptPath)));

            connection = DriverManager.getConnection(Database.getConnectionDB(), Database.getUserDB(), Database.getPasswordDB());

            try (PreparedStatement statement = connection.prepareStatement(sqlScript)) {
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    String name = resultSet.getString("NAME");
                    String month_count = resultSet.getString("MONTH_COUNT");
                    System.out.println("Project name " + name + ", " + "Month count " + month_count);
                }
            }

        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }

        return projectList;
    }


    public static List<Project> getCountProjectsOnClientsWithReadFile() {
        Connection connection;
        List<Project> projectList = new ArrayList<>();

        try {
            String scriptPath = "src/main/java/com/example/module6_jdbc/SQLSkripts/find_max_projects_client.sql";
            String sqlScript = new String(Files.readAllBytes(Path.of(scriptPath)));

            connection = DriverManager.getConnection(Database.getConnectionDB(), Database.getUserDB(), Database.getPasswordDB());

            try (PreparedStatement statement = connection.prepareStatement(sqlScript)) {
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    String name = resultSet.getString("NAME");
                    String project_count = resultSet.getString("PROJECT_COUNT");

                    System.out.println("Project name " + name + ", " + "Projects count " + project_count);
                }
            }

        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }

        return projectList;
    }


    public static List<Worker> getMaxSalaryWorkersWithReadFile() {
        Connection connection;
        List<Worker> workers = new ArrayList();
        try {
            String scriptPath = "src/main/java/com/example/module6_jdbc/SQLSkripts/find_max_salary_worker.sql";
            String sqlScript = new String(Files.readAllBytes(Path.of(scriptPath)));

            connection = DriverManager.getConnection(Database.getConnectionDB(), Database.getUserDB(), Database.getPasswordDB());

            try (PreparedStatement statement = connection.prepareStatement(sqlScript)) {
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    String name = resultSet.getString("NAME");
                    int salary = resultSet.getInt("SALARY");
                    System.out.println("Name: " + name + ", Salary: " + salary);
                }
            }

        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }

        return workers;
    }


    public static List<Project> getSummaryOfProjectsWithReadFile() {
        Connection connection;
        List<Project> projectPriceList = new ArrayList();
        try {
            String scriptPath = "src/main/java/com/example/module6_jdbc/SQLSkripts/print_project_prices.sql";
            String sqlScript = new String(Files.readAllBytes(Path.of(scriptPath)));

            connection = DriverManager.getConnection(Database.getConnectionDB(), Database.getUserDB(), Database.getPasswordDB());

            try (PreparedStatement statement = connection.prepareStatement(sqlScript)) {
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    String name = resultSet.getString("NAME");
                    int project_price = resultSet.getInt("price");
                    System.out.println("Project name " + name + ", " + "Projects count " + project_price);
                }
            }

        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
        return projectPriceList;
    }


    public static List<Worker> getWorkersWhoAreOldestAndYoungestWithReadFile() {
        Connection connection;
        List<Worker> workers = new ArrayList();
        try {
            String scriptPath = "src/main/java/com/example/module6_jdbc/SQLSkripts/find_youngest_eldest_workers.sql";
            String sqlScript = new String(Files.readAllBytes(Path.of(scriptPath)));

            connection = DriverManager.getConnection(Database.getConnectionDB(), Database.getUserDB(), Database.getPasswordDB());

            try (PreparedStatement statement = connection.prepareStatement(sqlScript)) {
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    String type = resultSet.getString("TYPE");
                    String name = resultSet.getString("NAME");
                    Date birthday = Date.valueOf(resultSet.getString("BIRTHDAY"));
                    Worker worker = new Worker(name, birthday, type);
                    workers.add(worker);
                    System.out.println("Type: " + type + ", Name: " + name + ", Birthday: " + birthday);
                }
            }

        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }

        return workers;
    }
}


