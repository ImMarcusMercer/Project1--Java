package Launchers;

import Accounts.*;
import Bank.*;

import java.util.Scanner;

public class AccountLauncher {


    private static Account loggedAccount;
    private static  Bank assocBank;
    private static final Scanner input = new Scanner(System.in);

    private static void print(String Value)
    {
        System.out.print(Value);
    }
    public static Bank getAssocBank() {
        return assocBank;
    }

    public static boolean isLoggedIn() {
        return loggedAccount != null;
    }

    public static void login()
    {
        if(assocBank == null)
        {
            print("Error: No Bank Available\n");
            return;
        }
        //Login
        int tries = 0;
        login:
        while(true)
        {

            print("Enter Account Number:");
            String accNum = input.nextLine();
            Account check = BankLauncher.findAccount(accNum);
            if(check != null)
            {
                print("Enter account Pin: ");
                String pin = input.nextLine();
                if(check.getPin().equals(pin))
                {
                    Account Acc = checkCredentials(accNum,pin);
                    if(Acc != null)
                    {
                        loggedAccount=Acc;
                        setLogSession(Acc);
                        print("Login successful!\n");
                        break login;
                    }
                }
                else {print("Incorrect Pin\nLogin unsuccessful, try again!\n");return;}
            }
            if(tries==2){print("Too many unsuccessful attempts!\n");break login;}
            else
            {
                print("Account not Found!\n");
                tries++;
            }
        }

    }

    public static void selectBank(Bank bank) {
        assocBank = bank;
    }
    public static void setLogSession(Account acc) {
        loggedAccount = acc;
        if (loggedAccount != null)
        {
            System.out.println("New Session for: " + acc.getAccountNumber());
        }
        else
        {
            System.out.println("No bank selected.");
        }
    }
    public static void logout() {
        loggedAccount = null;
        System.out.println("Logged out successfully.");
    }

    public static Account checkCredentials( String accountNumber, String pin) {
        if (assocBank != null) {
            Account account = assocBank.getBankAccount( accountNumber);
            if (account != null && account.getPin().equals(pin)) {
                return account;
            }
        }
        return null;
    }

    public static Account getLoggedAccount() {
        return loggedAccount;
    }
}