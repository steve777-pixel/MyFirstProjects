package models;

import utils.Validation;

public class Executive extends Employee{
    private int bonusTargets;
    private double salary;

    public Executive(int id, String name, double baseSalary, double taxRate, double salary){
        // Bonus targets are 0 by default when created
        super(id, name, baseSalary, taxRate);
        this.salary = Validation.validateNotNegative(salary, "Salary");
        this.bonusTargets = 0;
    }

    public int getBonusTargets(){
        return bonusTargets;
    }

    public double getSalary(){
        return salary;
    }

    public void addBonusTargets(int value){
        this.bonusTargets+=Validation.validateNotNegative(value, "Bonus targets"); // ???
    }

    public void setSalary(double newAmount){
        this.salary = Validation.validateNotNegative(newAmount, "Salary");
    }

    @Override 
    public double calculateGrossPay(){
        return salary + (bonusTargets * 100);
    }

    @Override
    public String toString(){
        return String.format("%s\nSalary: %.2f\nBonus targets: %d", super.toString(), salary, bonusTargets);
    }
}