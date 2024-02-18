package table_Worker.EachSpetialQueryFromDB;

import com.example.module6_jdbc.conections.Database;
import tables_DTO.Project;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MaxClientWithProjects {
    public List<Project> getLCountProjectsOnClients() {
        Connection connection;
        Statement statement;
        List<Project> projectList = new ArrayList();
        try {
            connection = DriverManager.getConnection(Database.getConnectionDB(), Database.getUserDB(), Database.getPasswordDB());
            statement = connection.createStatement();
            String longestProject = "SELECT client.NAME       AS NAME,\n" +
                    "       COUNT(project.ID) AS PROJECT_COUNT\n" +
                    "FROM client client\n" +
                    "         LEFT JOIN\n" +
                    "     project project ON client.ID = project.CLIENT_ID\n" +
                    "GROUP BY client.ID, client.NAME\n" +
                    "ORDER BY PROJECT_COUNT DESC\n" +
                    "LIMIT 1;";
            ResultSet resultSet = statement.executeQuery(longestProject);

            while (resultSet.next()) {
                String name = resultSet.getString("NAME");
                String project_count = resultSet.getString("PROJECT_COUNT");

                System.out.println("Project name " + name + ", " + "Projects count " + project_count);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return projectList;
    }

    public static void main(String[] args) {
        MaxClientWithProjects maxClientWithProjects = new MaxClientWithProjects();
        List<Project> projectsList = maxClientWithProjects.getLCountProjectsOnClients();
        for (Project projects : projectsList) {
            System.out.println(projects);
        }

    }
}
