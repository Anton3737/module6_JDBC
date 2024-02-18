package com.example.module6_jdbc;


import table_Worker.DatabaseQueryService;
import tables_DTO.Project;
import tables_DTO.Worker;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        DatabaseQueryService databaseQueryService = new DatabaseQueryService();


        System.out.println("\n\n Отримання найдовшого по тривалості проекту");
        List<Project> projectsLongList = databaseQueryService.getLongestProject();
        for (Project projectsListing : projectsLongList) {
            System.out.println(projectsListing);
        }

        System.out.println("\n\n Отримання компанії у якої найбільше проектів");
        List<Project> projectsListForEachClient = databaseQueryService.getCountProjectsOnClients();
        for (Project projectsCounter : projectsListForEachClient) {
            System.out.println(projectsCounter);
        }

        System.out.println("\n\n Отримання працівника з найбільшою заробітньою платою");
        List<Worker> workerList = databaseQueryService.getMaxSalaryWorkers();
        for (Worker workersSalary : workerList) {
            System.out.println(workersSalary);
        }

        System.out.println("\n\n Отримання ціни проекту вихідячи з затрат на ЗП та Час тривалості проектів для компанії");
        List<Project> projectsBudgetForEach = databaseQueryService.getSummaryOfProjects();
        for (Project projectsDebet : projectsBudgetForEach) {
            System.out.println(projectsDebet);
        }

        System.out.println("\n\n Отримання наймолодшого та найстаршого працівника");
        List<Worker> workersAges = databaseQueryService.getWorkersWhoAreOldestAndYoungest();
        for (Worker workersYoungAndOld : workersAges) {
            System.out.println(workersYoungAndOld);
        }

    }

}
