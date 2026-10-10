package models;

import utils.Validation;

public class Contractor extends Employee{
    private double hourlyRate;
    private double hoursWorked;

    public Contractor(int id, String name, double baseSalary, double taxRate, double hourlyRate){
        super(id, name, baseSalary, taxRate);
        this.hourlyRate = Validation.validateNotNegative(hourlyRate, "Hourly rate");
        this.hoursWorked = 0.0;
    }

    public double getHourlyRate(){
        return hourlyRate;
    }

    public double getHoursWorked(){
        return hoursWorked;
    }

    public void setHourlyRate(double value){
        this.hourlyRate = Validation.validateNotNegative(value, "Hourly rate");
    }

    public void addWorkHours(double amount){
        this.hoursWorked+=Validation.validateNotNegative(amount, "Worked hours");
    }

    @Override
    public double calculateGrossPay(){
        return hoursWorked * hourlyRate;
    }

    @Override 
    public String toString(){
        return String.format("%s\nHourly rate: %.2f\nHours worked: %.2f", super.toString(), hourlyRate, hoursWorked);
    }
}