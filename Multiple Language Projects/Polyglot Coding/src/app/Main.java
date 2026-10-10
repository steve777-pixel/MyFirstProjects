package app;

import models.*;

public class Main{
    public static void main(String[] args){

        //  MATH ENGINE
        double[] sample = {1000.0, 930.0, 500.0, 2400.0, 5000.0};
        double variance = Index.getPopulationVariance(sample);
        double sd = Index.getPopulationSD(sample);
        System.out.println(String.format("Population variance: %.2f\nPopulation standard deviation: %.2f", variance, sd));

        // CONTRACTOR
        Contractor contractor = new Contractor(9908, "Steve", 10_000, 0.2, 25);
        // System.out.println(contractor.getHoursWorked());
        contractor.addWorkHours(10);
        // System.out.println(contractor.getHoursWorked());
        
        
        // EXECUTIVE 
        Executive executive = new Executive(9284, "Alexa", 9_700, 2.5, 9_700);



        // SALESPERSON  



        // DEPARTMENT
        Department department = new Department("AF829232", "Human Resources", 150_000);
        department.assignManager(contractor);
        department.addEmployee(executive);
        // department.displayAllEmployeeNames();
        // System.out.println(department);

        // COMPANY
        Company company = new Company("GlobalX", 999_995_000);
        company.addDepartment(department);
        // company.removeDepartmentById("AX83923");
        // company.removeDepartmentById(null);
        // System.out.println(company.getTotalEmployeeCount());
    }
}