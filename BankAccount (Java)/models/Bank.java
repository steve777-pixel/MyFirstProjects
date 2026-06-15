package models;

import java.util.HashMap;
import java.util.Map;

public class Bank{
    private Map<String, Account> accounts; // <AccountNumber, Account>

    public Bank(){
        accounts = new HashMap<String, Account>();
    }

    public void createAccount(String accountNumber, String ownerName, double balance){
        if(accounts.containsKey(accountNumber)){
            throw new IllegalStateException("This account number is already in use!");
        }
        Account newAccount = new Account(accountNumber, ownerName, balance);
        accounts.put(accountNumber, newAccount);
    }

    public Account findAccount(String accountNumber){
        Account account = accounts.get(accountNumber);
        if(account == null){
            throw new IllegalArgumentException("Account not found!");
        }
        return account;
    }

    public boolean removeAccount(String accountNumber){
        return accounts.remove(accountNumber) != null;
    }

    public void displayAccount(){
        for(Account account : accounts.values()){
            System.out.println(account);
        }
    }
}