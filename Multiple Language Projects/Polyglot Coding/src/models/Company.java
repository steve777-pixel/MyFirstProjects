package models;

import utils.Validation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.List;

public class Company{
    private String name;
    private Map<String, Department> departments;
    private double globalBudget;

    public Company(String name, double globalBudget){
        this.name = Validation.validateNotBlank(name, "Company name");
        this.globalBudget = Validation.validateNotNegative(globalBudget, "Budget amount");
        departments = new HashMap<>();
    }

    public String getName(){
        return name;
    }

    public double getBudget(){
        return globalBudget;
    }

    public void setName(String name){
        this.name = Validation.validateNotBlank(name, "Company name");
    }

    public void addBudget(double amount){
        this.globalBudget+=Validation.validateNotNegative(amount, "Budget amount");
    }

    public List<Department> getDepartmentsList(){
        if(departments.isEmpty()){
            return Collections.emptyList();
        }
        return Collections.unmodifiableList(new ArrayList<>(departments.values()));
    }

    public void addDepartment(Department department){
        if(department == null){
            throw new IllegalArgumentException("Department cannot be null.");
        }
        if(departments.containsKey(department.getId())){
            throw new IllegalStateException(String.format("Department with ID %s already exists.", department.getId()));
        }
        departments.put(department.getId(), department);
    }

    public Department getDepartmentById(String id){
        if(id == null || id.isBlank()){
            throw new IllegalArgumentException("Department ID cannot be blank.");
        }
        if(departments.isEmpty()){
            throw new IllegalStateException("There are no departments.");
        }
        if(!departments.containsKey(id)){
            throw new IllegalStateException(String.format("Department with ID %s is not in the list.", id));
        }
        return departments.get(id);
    }

    public void removeDepartmentById(String id){
        // try{
        //     Department toRemove = getDepartmentById(id);
        //     departments.remove(toRemove.getId());
        // }catch(IllegalArgumentException e){
        //     System.out.println(e.getMessage());
        // }catch(IllegalStateException e){
        //     System.out.println(e.getMessage());
        // }
        Department toRemove = getDepartmentById(id);
        departments.remove(toRemove.getId());
    }

    public int getTotalEmployeeCount(){
        if(departments.isEmpty()){
            return 0;
        }
        return departments.values().stream().mapToInt(Department::getEmployeeCount).sum();
    }

    public double getTotalBurnRate(){
        if(departments.isEmpty()){
            return 0d;
        }
        return departments.values().stream().mapToDouble(Department::getAnnualBurnRate).sum();
    }

    public boolean isFinanciallyStable(){
        if(departments.isEmpty()){
            return true;
        }
        return departments.values().stream().mapToDouble(Department::getBudget).sum() <= globalBudget;
    }

    @Override 
    public String toString(){
        return String.format("Name: %s\nTotal departments: %d\nTotal employees: %d\nFinancial burn rate: %.2f", name, departments.size(), getTotalEmployeeCount(), getTotalBurnRate());
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj){
            return true;
        }
        if(obj == null || getClass() != obj.getClass()){
            return false;
        }
        Company other = (Company) obj;
        return Objects.equals(this.name, other.name);
    }

    @Override 
    public int hashCode(){
        return Objects.hash(name);
    }
}