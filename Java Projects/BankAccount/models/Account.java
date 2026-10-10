package models;

import utils.Money;
import utils.Validation;
import java.util.Objects;
import utils.AccountError;
import utils.AccountNumber;
import utils.AccountException;

public class Account{
    private AccountNumber accountNumber;
    private String ownerName;
    private Money balance;

    // Making it STATIC saves memory because it belongs to the class itself.
    // private static final double INITIAL_BALANCE = 0.0;

    public Account(AccountNumber accountNumber, String ownerName){
        this.accountNumber = accountNumber;
        this.ownerName = Validation.validateNotBlank(ownerName, "Owner name");
        this.balance = Money.zero();
    }

    public AccountNumber getAccountNumber(){
        return accountNumber;
    }

    public String getOwnerName(){
        return ownerName;
    }

    public Money getBalance(){
        return balance;
    }

    public void deposit(Money amount){ 
        if(amount == null || amount.isLessThanOrEqualZero()){
            throw new AccountException(AccountError.NEGATIVE_AMOUNT, "Deposit amount cannot be negative.");
        }
        this.balance = this.balance.add(amount);
    }

    public void withdraw(Money amount){
        if(amount == null || amount.isLessThanOrEqualZero()){
            throw new AccountException(AccountError.NEGATIVE_AMOUNT, "Withdraw amount cannot be negative.");
        }
        if(this.balance.isLessThan(amount)){
            throw new AccountException(AccountError.INSUFFICIENT_FUNDS, "Insufficient funds.");
        }
        this.balance = this.balance.subtract(amount);
    }

    public void transfer(Account receiver, Money amount){
        if(receiver == null){
            throw new AccountException(AccountError.NULL_USER, "Receiver cannot be null.");
        }
        if(this == receiver){
            throw new AccountException(AccountError.TRANSFER_TO_SELF, "Cannot transfer to self.");
        }
        this.withdraw(amount);
        receiver.deposit(amount);
    }

    @Override
    public String toString(){
        return 
        "Account number: " + accountNumber + 
        "\nOwner name: " + ownerName + 
        "\nBalance: " + balance;
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj){
            return true;
        }
        if(obj == null || getClass() != obj.getClass()){
            return false;
        }
        Account other = (Account) obj;
        return Objects.equals(this.accountNumber, other.accountNumber);
    }

    @Override
    public int hashCode(){
        return Objects.hash(accountNumber);
    }
}