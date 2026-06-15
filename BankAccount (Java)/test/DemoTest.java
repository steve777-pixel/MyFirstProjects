package test;

import models.*;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class DemoTest{
    
    @Test
    public void throwForNegativeBalance(){
        assertThrows(IllegalArgumentException.class, () -> new Account("AD18828", "Steve", -100));
    }

    @Test
    public void throwForEmptyAccountNumber(){
        assertThrows(IllegalArgumentException.class, () -> new Account("", "Steve", 100));
    }

    @Test
    public void throwForEmptyName(){
        assertThrows(IllegalArgumentException.class, () -> new Account("AD18828", "", 100));
    }

    @Test
    public void throwForNegativeDeposit(){
        Account account = new Account("AD838932", "Steve", 0);
        assertThrows(IllegalArgumentException.class, () -> account.deposit(-100));
    }

    @Test
    public void throwForNegativeWithdraw(){
        Account account = new Account("AD838932", "Steve", 100);
        assertThrows(IllegalArgumentException.class, () -> account.withdraw(-10));
    }

    @Test
    public void throwForWithdrawMoreThanBalance(){
        Account account = new Account("AD838932", "Steve", 100);
        assertThrows(IllegalStateException.class, () -> account.withdraw(200));
    }

    @Test
    public void throwForTransferToNullTarget(){
        Account account = new Account("AD838932", "Steve", 100);
        assertThrows(IllegalArgumentException.class, () -> account.transfer(null, 100));
    }

    @Test
    public void throwForTransferToSelf(){
        Account account = new Account("AD838932", "Steve", 100);
        assertThrows(IllegalArgumentException.class, () -> account.transfer(account, 100));
    }

    @Test
    public void depositShouldIncreaseBalance(){
        Account account = new Account("AD838932", "Steve", 100);
        account.deposit(50);
        assertEquals(150.0, account.getBalance(), 0.001);
    }

    @Test
    public void withdrawShouldDecreaseBalance(){
        Account account = new Account("AD838932", "Steve", 100);
        account.withdraw(40);
        assertEquals(60.0, account.getBalance(), 0.001);
    }

    @Test
    public void transferShouldMoveMoney(){
        Account account = new Account("AD838932", "Steve", 100);
        Account account1 = new Account("AF93289", "Alexa", 0);
        account.transfer(account1, 40);
        assertEquals(60.0, account.getBalance(), 0.001);
        assertEquals(40.0, account1.getBalance(), 0.001);
    }

    @Test 
    public void boundaryTest(){
        Account account = new Account("AD838932", "Steve", 100);
        account.withdraw(account.getBalance());
        assertEquals(0.0, account.getBalance(), 0.001);
    }
}