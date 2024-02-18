package table_Worker.EachSpetialQueryFromDB;

import com.example.module6_jdbc.conections.Database;
import tables_DTO.Project;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LongestProject {


    public List<Project> getLongestProject() {
        Connection connection;
        Statement statement;
        List<Project> projectList = new ArrayList();
        try {
            connection = DriverManager.getConnection(Database.getConnectionDB(), Database.getUserDB(), Database.getPasswordDB());
            statement = connection.createStatement();
            String longestProject = "SELECT client.NAME                                                         AS NAME,\n" +
                    "       SUM(EXTRACT(DAY FROM AGE(project.FINISH_DATE, project.START_DATE))) AS MONTH_COUNT\n" +
                    "FROM client client\n" +
                    "         JOIN project project ON client.ID = project.CLIENT_ID\n" +
                    "GROUP BY client.ID, client.NAME\n" +
                    "ORDER BY MONTH_COUNT DESC\n" +
                    "LIMIT 1;";
            ResultSet resultSet = statement.executeQuery(longestProject);

            while (resultSet.next()) {
                String name = resultSet.getString("NAME");
                String month_count = resultSet.getString("MONTH_COUNT");

                System.out.println("Project name " + name + ", " + "Month count " + month_count);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return projectList;
    }

    public static void main(String[] args) {
        LongestProject longestProject = new LongestProject();
        List<Project> projectsLongList = longestProject.getLongestProject();
        for (Project projects : projectsLongList) {
            System.out.println(projects);
        }

    }
}
