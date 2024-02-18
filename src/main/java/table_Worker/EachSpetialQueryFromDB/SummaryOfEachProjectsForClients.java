package table_Worker.EachSpetialQueryFromDB;

import com.example.module6_jdbc.conections.Database;
import tables_DTO.Project;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SummaryOfEachProjectsForClients {

    public List<Project> getSummaryOfProjects() {
        Connection connection;
        Statement statement;
        List<Project> projectPriceList = new ArrayList();
        try {
            connection = DriverManager.getConnection(Database.getConnectionDB(), Database.getUserDB(), Database.getPasswordDB());
            statement = connection.createStatement();
            String selectSumForProjects = "SELECT project.id AS project, " +
                    "client.name, " +
                    "(EXTRACT(MONTH FROM AGE(project.finish_date, project.start_date)) * SUM(worker.salary)) AS price " +
                    "FROM client " +
                    "JOIN project ON client.id = project.client_id " +
                    "JOIN project_worker ON project.id = project_worker.project_id " +
                    "JOIN worker ON project_worker.worker_id = worker.id " +
                    "GROUP BY project.id, client.name, project.start_date, project.finish_date " +
                    "ORDER BY price DESC";
            ResultSet resultSet = statement.executeQuery(selectSumForProjects);

            while (resultSet.next()) {
                String name = resultSet.getString("NAME");
                int project_price = resultSet.getInt("price");

                System.out.println("Project name " + name + ", " + "Projects count " + project_price);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return projectPriceList;
    }


    public static void main(String[] args) {
        SummaryOfEachProjectsForClients summaryOfEachProjectsForClients = new SummaryOfEachProjectsForClients();
        List<Project> projectList = summaryOfEachProjectsForClients.getSummaryOfProjects();
        for (Project priceList : projectList) {
            System.out.println(priceList);
        }
    }
}

