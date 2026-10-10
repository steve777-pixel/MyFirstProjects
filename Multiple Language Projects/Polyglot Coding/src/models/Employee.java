package models;

import utils.Validation;

public abstract class Employee{
    private final int id;
    private String name;
    private double baseSalary;
    private double taxRate;

    public Employee(int id, String name, double baseSalary, double taxRate){
        this.id = Validation.validateNotNegative(id, "Employee ID");
        this.name = Validation.validateNotBlank(name, "Employee name");
        this.baseSalary = Validation.validateNotNegative(baseSalary, "Base salary");
        this.taxRate = Validation.validateNotNegative(taxRate, "Tax rate");
    }

    public int getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public double getBaseSalary(){
        return baseSalary;
    }

    public double getTaxRate(){
        return taxRate;
    }

    public void setName(String name){
        this.name = Validation.validateNotBlank(name, "Employee name");
    }

    public void setBaseSalary(double value){
        this.baseSalary = Validation.validateNotNegative(value, "Base salary");
    }

    public void setTaxRate(double value){
        this.taxRate = Validation.validateNotNegative(value, "Base salary");
    }

    @Override
    public String toString(){
        return String.format("ID: %d\nName: %s\nBase salary: %.2f\nTax rate: %.2f", id, name, baseSalary, taxRate);
    }

    public abstract double calculateGrossPay();
}