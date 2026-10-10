package app;

import models.*;
import utils.*;

public class Index {
    public static void main(String[] args){
        System.out.println("Hello!");

        AccountNumber steveNumber = new AccountNumber("AD7886109");
        Account newAccount = new Account(steveNumber, "STEVE");
        System.out.println(newAccount);
    }
}
