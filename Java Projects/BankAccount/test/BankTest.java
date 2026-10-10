package test;

import utils.Money;
import models.Bank;
import models.Account;
import utils.BankError;
import utils.AccountError;
import utils.AccountNumber;
import utils.BankException;
import utils.AccountException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class BankTest {

    private Bank bank;

    @BeforeEach
    void setUp(){
        bank = new Bank("State Bank");
    }

    @Test
    void testBankCreation(){
        assertEquals("State Bank", bank.getName());
        assertEquals(0, bank.getAccountCount());
    }

    @Test
    void testBankAccountSuccess(){
        AccountNumber newNumber = new AccountNumber("AX9120385");
        bank.createAccount(newNumber, "Steve");

        assertEquals(1, bank.getAccountCount());

        Account account = bank.findAccount(newNumber);

        assertEquals(newNumber, account.getAccountNumber());
        assertEquals("Steve", account.getOwnerName());
    }

    @Test
    void testBankDuplicateAccountThrows(){
        AccountNumber newNumber = new AccountNumber("AX9120385");
        bank.createAccount(newNumber, "Steve");

        BankException thrown = assertThrows(BankException.class, () -> {
            bank.createAccount(newNumber, "Steve");
        });

        assertEquals(BankError.DUPLICATE_ACCOUNT, thrown.getErrorType());
        assertEquals("The account with number AX9120385 is already in the bank.", thrown.getMessage());
    }

    @Test
    void testAccountNotFoundThrows(){
        AccountNumber newNumber = new AccountNumber("AX9120385");

        BankException thrown = assertThrows(BankException.class, () -> {
            Account account = bank.findAccount(newNumber);
        });

        assertEquals(BankError.ACCOUNT_NOT_FOUND, thrown.getErrorType());
        assertEquals("The account with number AX9120385 was not found in the bank.", thrown.getMessage());
    }

    @Test
    void testRemoveAccountSuccess(){
        AccountNumber newNumber = new AccountNumber("AX9120385");
        bank.createAccount(newNumber, "Steve");

        assertTrue(bank.removeAccount(newNumber));
        assertEquals(0, bank.getAccountCount());
    }

    @Test
    void testRemoveAccountThrows(){
        AccountNumber newNumber = new AccountNumber("AX9120385");
        assertFalse(bank.removeAccount(newNumber));
    }

    @Test
    void testTransferSuccess(){
        AccountNumber steveNumber = new AccountNumber("AX9120385");
        AccountNumber aliceNumber = new AccountNumber("BH0120385");

        bank.createAccount(steveNumber, "Steve");
        bank.createAccount(aliceNumber, "Alice");

        bank.depositToAccount(steveNumber, Money.of(500.0));

        bank.transfer(steveNumber, aliceNumber, Money.of(300.0));

        assertEquals(Money.of(200.0), bank.checkBalance(steveNumber));
        assertEquals(Money.of(300.0), bank.checkBalance(aliceNumber));
    }

    @Test
    void testTransferToSelfThrows(){
        AccountNumber steveNumber = new AccountNumber("AX9120385");

        bank.createAccount(steveNumber, "Steve");

        bank.depositToAccount(steveNumber, Money.of(500.0));

        AccountException thrown = assertThrows(AccountException.class, () -> {
            bank.transfer(steveNumber, steveNumber, Money.of(300.0));
        });

        assertEquals(AccountError.TRANSFER_TO_SELF, thrown.getErrorType());
        assertEquals("Cannot transfer to self.", thrown.getMessage());
    }
}