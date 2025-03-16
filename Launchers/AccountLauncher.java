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

    public static void accountLogin()
    {
        Main:
        while(true)
        {
            Bank loginBank=selectBank();
            if(loginBank!=null)
            {
                BankLauncher.setBankSession(loginBank);
                if(BankLauncher.getLoggedBank()!=null)
                {
                    Main.showMenuHeader("Select Account Type");
                    Main.showMenu(33);
                    Main.setOption();
                    //Credit account
                    if(Main.getOption()==1)
                    {
                        int tries=0;
                        Main.showMenuHeader("Credit Account Login");
                        while(true)
                        {
                            tries+=1;
                            String accnum=Main.prompt("Enter Account Number: ",true);
                            Account found = BankLauncher.getLoggedBank().getBankAccount(loginBank,accnum);
                            if(found!=null&&found.getClass().isInstance(CreditAccount.class))
                            {
                                int tris2=0;
                                while(true)
                                {
                                    tris2+=1;
                                    String pin=Main.prompt("Enter Pin: ",true);
                                    if(found.getPin().equals(pin))
                                    {
                                        setLogSession(found);
                                        if(getLoggedAccount()!=null)
                                        {
                                            CreditAccountLauncher.credAccountInit();
                                            destroyLogSession();
                                        }
                                    }
                                    else {
                                        Main.print("Invalid Pin!");
                                    }
                                    if(tris2==3)
                                    {
                                        Main.print("Too many unsuccessful attempts!");
                                        break;
                                    }
                                }
                            }
                            if(tries==3)
                            {
                                Main.print("Too many unsuccessful attempts!");
                                break Main;
                            }
                            else {
                                Main.print("Invalid Account!");
                            }
                        }
                    }
                    //Savings Account
                    else if(Main.getOption()==2){}
                    else {
                        Main.print("Invalid Input!");
                        break;
                    }

                }
            }
            else
            {
                Main.print("Bank Not Found");
                break;
            }
        }
    }

    //Done
    private static Bank selectBank()
    {
        Main.showMenuHeader("Bank Selection");
        BankLauncher.showBanksMenu();
        System.out.print("Enter Bank ID: ");
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