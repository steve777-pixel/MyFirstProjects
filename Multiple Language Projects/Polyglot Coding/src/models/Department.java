package models;

import utils.Validation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.List;

public class Department{
    private final String id;
    private String name;
    private Employee manager;
    private Map<Integer, Employee> employees;
    private double budget;

    public Department(String id, String name, double budget){
        this.id = Validation.validateNotBlank(id, "Department ID");
        this.name = Validation.validateNotBlank(name, "Department name");
        this.budget = Validation.validateNotNegative(budget, "Budget");
        employees = new HashMap<>();
        this.manager = null;
    }

    public String getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public Employee getManager(){
        return manager;
    }

    public List<Employee> getEmployeeList(){
        return Collections.unmodifiableList(new ArrayList<>(employees.values()));
    }

    public double getBudget(){
        return budget;
    }

    public void assignManager(Employee manager){
        if(manager == null){
            throw new IllegalArgumentException("Manager cannot be null.");
        }
        this.manager = manager;
    }

    public void setName(String name){
        this.name = Validation.validateNotBlank(name, "Department name");
    }

    public void addBudget(double amount){
        this.budget+=Validation.validateNotNegative(amount, "Amount");
    }

    public void addEmployee(Employee employee){
        if(employee == null){
            throw new IllegalArgumentException("Employee cannot be null.");
        }
        if(employees.containsKey(employee.getId())){
            throw new IllegalStateException("Employee with this ID is already in this department.");
        }
        employees.put(employee.getId(), employee);
    }

    public void removeEmployeeById(int id){
        if(employees.isEmpty()){
            throw new IllegalStateException("No employees in this department.");
        }
        if(!employees.containsKey(id)){
            throw new IllegalArgumentException("No employee with such ID in this department.");
        }
        employees.remove(id);
    }

    public void removeEmployee(Employee employee){
        if(employee == null){
            throw new IllegalArgumentException("Employee cannot be null.");
        }
        removeEmployeeById(employee.getId());
    }

    public Employee searchEmployeeById(int id){
        if(employees.isEmpty()){
            throw new IllegalStateException("No employees in this department.");
        }
        if(!employees.containsKey(id)){
            throw new IllegalArgumentException("No employee with such ID in this department.");
        }
        return employees.get(id);
    }

    public int getEmployeeCount(){
        return employees.size();
    }

    public double getAnnualBurnRate(){
        return employees.values().stream().mapToDouble(Employee::getBaseSalary).sum();
    } 

    public boolean hasRemainingBudget(double amount){
        return this.budget >= amount;
    }

    public void displayAllEmployeeNames(){
        if(employees.isEmpty()){
            return;
        }
        for(Employee employee : employees.values()){
            System.out.println(employee.getName());
        }
        System.out.println("Manager: " + manager.getName());
    }

    @Override
    public String toString(){
        if(manager == null){
            return String.format("ID: %s\nName: %s\nManager: None\nBudget: %.2f", id, name, budget);
        }
        return String.format("ID: %s\nName: %s\nManager: %s\nBudget: %.2f", id, name, manager.getName(), budget);
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj){
            return true;
        }
        if(obj == null || getClass() != obj.getClass()){
            return false;
        }
        Department other = (Department) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public int hashCode(){
        return Objects.hash(id);
    }
}