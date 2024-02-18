package table_Worker.EachSpetialQueryFromDB;

import com.example.module6_jdbc.conections.Database;
import tables_DTO.Worker;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class YoungestAndOldestWorkers {


    public List<Worker> getWorkersWhoAreOldestAndYoungest() {
        Connection connection;
        Statement statement;
        List<Worker> workers = new ArrayList();
        try {
            connection = DriverManager.getConnection(Database.getConnectionDB(), Database.getUserDB(), Database.getPasswordDB());
            statement = connection.createStatement();
            String youngestAndOlderWorkers = "WITH RankedWorkers AS (SELECT NAME, BIRTHDAY, " +
                    "RANK() OVER (ORDER BY BIRTHDAY ASC) AS YoungestRank, " +
                    "RANK() OVER (ORDER BY BIRTHDAY DESC) AS OldestRank " +
                    "FROM worker) " +
                    "SELECT CASE WHEN YoungestRank = 1 THEN 'OLDEST' ELSE 'YOUNGEST' END AS TYPE, " +
                    "NAME, BIRTHDAY " +
                    "FROM RankedWorkers " +
                    "WHERE YoungestRank = 1 OR OldestRank = 1";
            ResultSet resultSet = statement.executeQuery(youngestAndOlderWorkers);

            while (resultSet.next()) {

                String type = resultSet.getString("TYPE");
                String name = resultSet.getString("NAME");
                Date birthday = Date.valueOf(resultSet.getString("BIRTHDAY"));

//                Worker worker = new Worker(name, birthday, type);
//                workers.add(worker);

                System.out.println("Type: " + type + ", Name: " + name + ", Birthday: " + birthday);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return workers;
    }


    public static void main(String[] args) {
        YoungestAndOldestWorkers youngestAndOldestWorkers = new YoungestAndOldestWorkers();
        List<Worker> workerList = youngestAndOldestWorkers.getWorkersWhoAreOldestAndYoungest();
        for (Worker yongAndOld : workerList) {
            System.out.println(yongAndOld.toString());
        }

    }

}
