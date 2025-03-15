package Launchers;

import Accounts.*;
import Main.*;
import Bank.*;

public class AccountLauncher {
    private static Account loggedAccount;
    private static Bank assocBank;

    //Methods
    private static boolean isLoggedIn()
    {
        return loggedAccount != null;
    }

    //Done?
    public static void accountLogin()
    {
        String accountNum = Main.prompt("Enter Account Number: ", true);
        String pin = Main.prompt("Enter 4-digit PIN: ", false);

        Account account = checkCredentials(accountNum, pin);
        if (account != null) {
            setLogSession(account);
            System.out.println("Login successful!");
        } else {
            System.out.println("Invalid credentials. Please try again.");
        }
    }

    //Incomplete kay nag libog ko
    private static Bank selectBank()
    {
        BankLauncher.showBanksMenu();
        String bankId=Main.prompt("Enter Bank ID: ",true);
//        if(BankLauncher.getBank())
//        return BankLauncher.getBank(bankId);
        return null;
    }

    //Done?
    private static void setLogSession(Account account)
    {
        loggedAccount = account;
        System.out.println("Session started for account: " + loggedAccount.getAccountNumber());
    }

    //Done?
    private static void destroyLogSession()
    {
        if (loggedAccount != null) {
            System.out.println("Logging out: " + loggedAccount.getAccountNumber());
            loggedAccount = null;
        } else {
            System.out.println("No active session to log out.");
        }
    }

    //Incomplete
    public static Account checkCredentials(String accountNum, String pin)
    {
        return null;
    }

    protected static Account getLoggedAccount()
    {
        return loggedAccount;
    }
}
