package table_Worker;

import com.example.module6_jdbc.conections.Database;
import tables_DTO.Clients;
import tables_DTO.Worker;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WorkerDB {

    public List<Worker> getAllWorker() {
        Connection connection;
        Statement statement;
        List<Worker> workers = new ArrayList();
        try {
            connection = DriverManager.getConnection(Database.getConnectionDB(), Database.getUserDB(), Database.getPasswordDB());
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM worker");

            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString("name");
                Date birthday = resultSet.getDate(3);
                String level = resultSet.getString("level");
                int salary = resultSet.getInt(5);
                Worker workersObj = new Worker(id, name, birthday, level, salary);
                workers.add(workersObj);
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return workers;
    }


    public static void main(String[] args) {
        WorkerDB workerDB = new WorkerDB();
        List<Worker> allWorkers = workerDB.getAllWorker();
        for (Worker worker : allWorkers) {
            System.out.println(worker.toString());
        }
    }
}
