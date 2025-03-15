package Launchers;

import Accounts.*;
import Main.*;
import Bank.*;

import java.util.Scanner;

public class AccountLauncher {
    private static Account loggedAccount;
    private static Bank assocBank;
    private static final Scanner input = new Scanner(System.in);

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

    //Done
    private static Bank selectBank()
    {
        Main.showMenuHeader("Bank Selection");
        BankLauncher.showBanksMenu();
        Main.print("Enter Bank ID: ");
        int bankId=input.nextInt();
        input.nextLine();
        for(Bank b :BankLauncher.getBankList())
        {
            if(b.getID()==bankId)
            {
                return b;
            }
        }
        return null;
    }

    //Done
    private static void setLogSession(Account account)
    {
        if(account!=null)
        {
            loggedAccount = account;
            System.out.println("Session started for account: " + loggedAccount.getAccountNumber());
        }
        else
        {
            Main.print("Invalid Account!");
        }
    }

    //Done
    private static void destroyLogSession()
    {
        if (loggedAccount != null) {
            System.out.println("Logging out: " + loggedAccount.getAccountNumber());
            loggedAccount = null;
        }
        else {
            System.out.println("No active session to log out.");
        }
    }

    //Use in Account Login
    public static Account checkCredentials(String accountNum, String pin)
    {
        Account found=BankLauncher.findaccount(accountNum);
        if(found!=null)
        {
            if(found.getPin().equals(pin))
            {
                return found;
            }
        }
        return null;
    }

    protected static Account getLoggedAccount()
    {
        return loggedAccount;
    }
}