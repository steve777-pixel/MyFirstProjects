package models;

import java.util.HashMap;
import java.util.Map;
import utils.Validation;
import java.util.Objects;
import utils.AccountNumber;
import utils.BankException;
import utils.BankError;
import utils.Money;

public class Bank{
    private final String name;
    private Map<AccountNumber, Account> accounts; // <AccountNumber, Account>

    public Bank(String name){
        this.name = Validation.validateNotBlank(name, "Bank name");
        accounts = new HashMap<AccountNumber, Account>();
    }

    public String getName(){
        return name;
    }

    public int getAccountCount(){
        return accounts.size();
    }

    public void createAccount(AccountNumber accountNumber, String ownerName){
        if(accounts.containsKey(accountNumber)){
            throw new BankException(BankError.DUPLICATE_ACCOUNT, String.format("The account with number %s is already in the bank.", accountNumber.getValue()));
        }
        Account newAccount = new Account(accountNumber, ownerName);
        accounts.put(accountNumber, newAccount);
    }

    public Account findAccount(AccountNumber accountNumber){
        Account account = accounts.get(accountNumber);
        if(account == null){
            throw new BankException(BankError.ACCOUNT_NOT_FOUND, String.format("The account with number %s was not found in the bank.", accountNumber.getValue()));
        }
        return account;
    }

    public boolean removeAccount(AccountNumber accountNumber){
        return accounts.remove(accountNumber) != null;
    }

    public void displayAccount(){
        for(Account account : accounts.values()){
            System.out.println(account);
        }
    }

    public void depositToAccount(AccountNumber accountNumber, Money amount){
        findAccount(accountNumber).deposit(amount);
    }

    public void withdrawFromAccount(AccountNumber accountNumber, Money amount){
        findAccount(accountNumber).withdraw(amount);
    }

    public void transfer(AccountNumber senderNumber, AccountNumber receiverNumber, Money amount){
        Account sender = findAccount(senderNumber);
        Account receiver = findAccount(receiverNumber);

        sender.transfer(receiver, amount);
    }

    public Money checkBalance(AccountNumber accountNumber){
        return findAccount(accountNumber).getBalance();
    }

    @Override 
    public String toString(){
        return String.format("Bank name: %s\nNumber of accounts: %d", name, accounts.size());
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj){
            return true;
        }
        if(obj == null || getClass() != obj.getClass()){
            return false;
        }
        Bank other = (Bank) obj;
        return Objects.equals(this.name, other.name);
    }

    @Override
    public int hashCode(){
        return Objects.hash(name);
    }
}