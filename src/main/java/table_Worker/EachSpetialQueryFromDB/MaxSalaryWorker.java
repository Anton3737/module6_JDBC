package table_Worker.EachSpetialQueryFromDB;

import com.example.module6_jdbc.conections.Database;
import tables_DTO.Worker;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MaxSalaryWorker {

    public List<Worker> getMaxSalaryWorkers() {
        Connection connection;
        Statement statement;
        List<Worker> workers = new ArrayList();
        try {
            connection = DriverManager.getConnection(Database.getConnectionDB(), Database.getUserDB(), Database.getPasswordDB());
            statement = connection.createStatement();
            String maxSalaryQuery = "SELECT NAME, SALARY FROM worker WHERE SALARY = (SELECT MAX(SALARY) FROM worker)";
            ResultSet resultSet = statement.executeQuery(maxSalaryQuery);

            while (resultSet.next()) {
                String name = resultSet.getString("NAME");
                int salary = resultSet.getInt("SALARY");
                System.out.println("Name: " + name + ", Salary: " + salary);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return workers;
    }


    public static void main(String[] args) {
        MaxSalaryWorker maxSalaryWorker = new MaxSalaryWorker();
        List<Worker> getMaxSalaryWorkers = maxSalaryWorker.getMaxSalaryWorkers();
        for (Worker worker : getMaxSalaryWorkers) {
            System.out.println(worker.toString());
        }
    }
}
