package models;

import utils.Validation;

public class Account{
    private String accountNumber;
    private String ownerName;
    private double balance;

    public Account(String accountNumber, String ownerName, double balance){
        if(balance < 0){
            throw new IllegalArgumentException("Initial balance cannot be negative!");
        }
        this.accountNumber = Validation.validateNotBlank(accountNumber, "Account number");
        this.ownerName = Validation.validateNotBlank(ownerName, "Owner name");
        this.balance = balance;
    }

    public String getAccountNumber(){
        return accountNumber;
    }

    public String getOwnerName(){
        return ownerName;
    }

    public double getBalance(){
        return balance;
    }

    public void deposit(double amount){
        if(amount <= 0){
            throw new IllegalArgumentException("Deposit amount cannot be negative!");
        }
        this.balance+=amount;
    }

    public void withdraw(double amount){
        if(amount <= 0){
            throw new IllegalArgumentException("Withdraw amount must be stricly positive!");
        }
        if(amount > this.balance){
            throw new IllegalStateException("Withdraw amount is more than balance.");
        }
        this.balance-=amount;
    }

    public void transfer(Account target, double amount){
        if(target == null){
            throw new IllegalArgumentException("Target cannot be null.");
        }
        if(this == target){
            throw new IllegalArgumentException("Cannot transfer to self.");
        }
        this.withdraw(amount);
        target.deposit(amount);
    }

    @Override
    public String toString(){
        return "Account number: " + accountNumber + "\nOwner name: " + ownerName + "\nBalance: " + balance;
    }
}