package models;

import utils.Validation;

public class SalesPerson extends Employee{
    private int totalSales;
    private double commissionPercentage;

    public SalesPerson(int id, String name, double baseSalary, double taxRate, double commissionPercentage){
        super(id, name, baseSalary, taxRate);
        this.commissionPercentage = Validation.validateNotNegative(commissionPercentage, "Commission percentage");
        this.totalSales = 0;
    }

    public int getTotalSales(){
        return totalSales;
    }

    public double getCommissionPercentage(){
        return commissionPercentage;
    }

    public void addSales(int value){
        this.totalSales+=Validation.validateNotNegative(value, "Sales count");
    }

    public void setCommissionPercentage(double value){
        this.commissionPercentage = Validation.validateNotNegative(value, "Commission percentage");
    }

    @Override 
    public double calculateGrossPay(){
        return (totalSales * 100.0) - (totalSales * (commissionPercentage) / 100.0);
    }

    @Override
    public String toString(){
        return String.format("%s\nTotal sales: %d\nCommission percentage: %.2f", super.toString(), totalSales, commissionPercentage);
    }
}