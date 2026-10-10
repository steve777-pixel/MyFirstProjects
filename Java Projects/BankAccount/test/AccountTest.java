package test;

import utils.Money;
import models.Account;
import utils.AccountError;
import utils.AccountNumber;
import utils.AccountException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class AccountTest {

    private Account aliceAccount;
    private Account bobAccount;
    private AccountNumber aliceNumber;
    private AccountNumber bobNumber;

    // This runs before every single test method to give you clean, fresh objects
    @BeforeEach
    void setUp() {
        aliceNumber = new AccountNumber("AD1234567");
        bobNumber = new AccountNumber("XZ1209348");
        aliceAccount = new Account(aliceNumber, "Alice");
        bobAccount = new Account(bobNumber, "Bob");
    }

    @Test
    void testInitialBalance(){
        assertEquals(Money.zero(), aliceAccount.getBalance(), "Initial balance should be 0.0.");
        assertEquals(Money.zero(), bobAccount.getBalance());
    }

    @Test
    void testAccountNumberNullOrBlankThrows(){
        AccountException thrownFromNull = assertThrows(AccountException.class, () -> {
            AccountNumber newNumber = new AccountNumber(null);
        });

        AccountException thrownFromBlank = assertThrows(AccountException.class, () -> {
            AccountNumber newNumber = new AccountNumber("   ");
        });

        assertEquals(AccountError.INVALID_ACCOUNT_NUMBER_FORMAT, thrownFromNull.getErrorType());
        assertEquals("Invalid account number format. Expected: XX0000000.", thrownFromNull.getMessage());

        assertEquals(AccountError.INVALID_ACCOUNT_NUMBER_FORMAT, thrownFromBlank.getErrorType());
        assertEquals("Invalid account number format. Expected: XX0000000.", thrownFromBlank.getMessage());
    }

    @Test
    void testAccountNumberInvalidFormatThrows(){
        AccountException thrown = assertThrows(AccountException.class, () -> {
            AccountNumber newNumber = new AccountNumber("A883923");
        });
        assertEquals(AccountError.INVALID_ACCOUNT_NUMBER_FORMAT, thrown.getErrorType());
        assertEquals("Invalid account number format. Expected: XX0000000.", thrown.getMessage());
    }

    @Test
    void testAccountNumber(){ 
        AccountNumber expectedAliceNumber = new AccountNumber("AD1234567");
        AccountNumber expectedBobNumber = new AccountNumber("XZ1209348");

        assertEquals(expectedAliceNumber, aliceAccount.getAccountNumber());
        assertEquals(expectedBobNumber, bobAccount.getAccountNumber());
    }

    @Test
    void testOwnerName(){
        assertEquals("Alice", aliceAccount.getOwnerName());
        assertEquals("Bob", bobAccount.getOwnerName());
    }

    @Test
    void testDepositSuccess(){
        aliceAccount.deposit(Money.of(500.0));
        bobAccount.deposit(Money.of(999.99));
        assertEquals(Money.of(500.0), aliceAccount.getBalance());
        assertEquals(Money.of(999.99), bobAccount.getBalance());
    }

    @Test
    void testDepositNegativeAmountThrows(){
        AccountException thrown = assertThrows(AccountException.class, () -> {
            aliceAccount.deposit(Money.of(-100.0));
        });

        assertEquals(AccountError.NEGATIVE_AMOUNT, thrown.getErrorType());
        assertEquals("Deposit amount cannot be negative.", thrown.getMessage());
    }

    @Test
    void testWithdrawSuccess(){
        aliceAccount.deposit(Money.of(999.0));
        aliceAccount.withdraw(Money.of(555.45));
        assertEquals(Money.of(443.55), aliceAccount.getBalance());
    }

    @Test
    void testWithdrawNegativeAmountThrows(){
        bobAccount.deposit(Money.of(500.0));
        AccountException thrownWithZeroBalance = assertThrows(AccountException.class, () -> {
            aliceAccount.withdraw(Money.of(-100.0));
        });
        AccountException thrownWithPositiveBalance = assertThrows(AccountException.class, () -> {
            bobAccount.withdraw(Money.of(-200.0));
        });

        assertEquals(AccountError.NEGATIVE_AMOUNT, thrownWithZeroBalance.getErrorType());
        assertEquals("Withdraw amount cannot be negative.", thrownWithZeroBalance.getMessage());

        assertEquals(AccountError.NEGATIVE_AMOUNT, thrownWithPositiveBalance.getErrorType());
        assertEquals("Withdraw amount cannot be negative.", thrownWithPositiveBalance.getMessage());
    }

    @Test
    void testWithdrawInsufficientFundThrows(){
        bobAccount.deposit(Money.of(500.0));
        AccountException thrown = assertThrows(AccountException.class, () -> {
            bobAccount.withdraw(Money.of(1000.0));
        });

        assertEquals(AccountError.INSUFFICIENT_FUNDS, thrown.getErrorType());
        assertEquals("Insufficient funds.", thrown.getMessage());
        assertTrue(thrown.getMessage().contains("Insufficient"));
    }

    @Test
    void testTransferSuccess(){
        aliceAccount.deposit(Money.of(100.0));
        aliceAccount.transfer(bobAccount, Money.of(60.0));
        assertEquals(Money.of(40.0), aliceAccount.getBalance());
        assertEquals(Money.of(60.0), bobAccount.getBalance());
    }

    @Test
    void testTransferToNullThrows(){
        aliceAccount.deposit(Money.of(500.0));
        AccountException thrown = assertThrows(AccountException.class, () -> {
            aliceAccount.transfer(null, Money.of(400.0));
        });

        assertEquals(AccountError.NULL_USER, thrown.getErrorType());
        assertEquals("Receiver cannot be null.", thrown.getMessage());
    }

    @Test
    void testTransferToSelfThrows(){
        aliceAccount.deposit(Money.of(100.0));
        AccountException thrown = assertThrows(AccountException.class, () -> {
            aliceAccount.transfer(aliceAccount, Money.of(50.0));
        });

        assertEquals(AccountError.TRANSFER_TO_SELF, thrown.getErrorType());
        assertEquals("Cannot transfer to self.", thrown.getMessage());
    }

    @Test
    void testTransferNegativeAmountThrows(){
        aliceAccount.deposit(Money.of(400.0));
        AccountException thrown = assertThrows(AccountException.class, () -> {
            aliceAccount.transfer(bobAccount, Money.of(-50.0));
        });

        assertEquals(AccountError.NEGATIVE_AMOUNT, thrown.getErrorType());
        assertEquals("Withdraw amount cannot be negative.", thrown.getMessage());
    }

    @Test
    void testTransferInsufficientFundThrows(){
        aliceAccount.deposit(Money.of(300.0));
        AccountException thrown = assertThrows(AccountException.class, () -> {
            aliceAccount.transfer(bobAccount, Money.of(400.0));
        });

        assertEquals(AccountError.INSUFFICIENT_FUNDS, thrown.getErrorType());
        assertEquals("Insufficient funds.", thrown.getMessage());
    }

    @Test
    void testCompareDifferentAccount(){
        assertFalse(aliceAccount == bobAccount);
    }

    @Test
    void testSameAccount(){
        assertTrue(aliceAccount == aliceAccount);
    }
}
