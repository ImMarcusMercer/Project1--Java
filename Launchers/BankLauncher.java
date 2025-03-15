package Launchers;

import java.util.*;
import Accounts.*;
import Main.*;

import Bank.*;
public class BankLauncher {
    private static ArrayList<Bank> BANKS=new ArrayList<>();
    private static Bank LoggedBank=null;
    private static final Scanner input = new Scanner(System.in);

    //Methods
    public static Bank getLoggedBank()
    {
        return LoggedBank;
    }
    public static boolean isLogged()
    {
        return LoggedBank!=null;
    }
    public static ArrayList<Bank> getBankList()
    {
        return BANKS;
    }

    public static void BankINIT()
    {
        INIT:
        while(true)
        {
            if(LoggedBank!=null)
            {
                Main.showMenuHeader("Bank Menu");
//        "Show Accounts", "New Accounts", "Log Out";
                Main.showMenu(31);
                Main.setOption();
                switch (Main.getOption())
                {
                    //Show Accounts
                    case 1->{
                        //"Credit Accounts", "Savings Accounts", "All Accounts", "Go Back"
                        Main.showMenuHeader(LoggedBank.getName()+" Accounts");
                        Main.showMenu(32);
                        Main.setOption();
                        if(Main.getOption()==1)
                        {
                            Main.showMenuHeader("Credit Accounts");
                            LoggedBank.showAccounts(CreditAccount.class);
                        }
                        else if(Main.getOption()==2)
                        {
                            Main.showMenuHeader("Savings Accounts");
                            LoggedBank.showAccounts(SavingsAccount.class);
                        }
                        else if(Main.getOption()==3)
                        {
                            Main.showMenuHeader("All Accounts");
                            LoggedBank.showAccounts(Account.class);
                        }
                        else
                        {
                            Main.print("Invalid Choice");
                            return;
                        }
                    }
                    //New Accounts
                    case 2->{}
                    case 3-> {
                        logOut();
                        break INIT;
                    }
                }
            }
            else
            {
                Main.print("No bank selected!");
                break;
            }
        }

    }

    public static void showAccounts()
    {
        do {
            Main.showMenuHeader("Show Account Options");
            Main.showMenu(32);
            String show = Main.prompt("Enter Choice: ", true);
            //"Credit Accounts", "Savings Accounts", "All Accounts", "Go Back"
            switch (show) {
                case "1" -> {
                    LoggedBank.showAccounts(CreditAccount.class);
                }
                case "2" -> {
                    LoggedBank.showAccounts(SavingsAccount.class);
                }
                case "3" -> {
                    LoggedBank.showAccounts(Account.class);
                }
                case "4" -> {
                    return;
                }
                default -> {
                    Main.print("Invalid input!\n");
                    continue;
                }
            }
            break;
        } while (true);

    }

    public static void newAccounts()
    {

    }

    public static void bankLogin()
    {
        Login:
        while(true)
        {
            Main.showMenuHeader("Bank");
            Main.showMenu(3);
            Main.setOption();
            if(Main.getOption()==1)
            {
                BankLauncher.showBanksMenu();
                if(!BANKS.isEmpty())
                {
                    System.out.print("Enter Bank ID: ");
                    int bankID=input.nextInt();
                    input.nextLine();
                    for(Bank b: BANKS)
                    {
                        if(b.getID()==bankID)
                        {
                            int i= 0;
                            while(i<3)
                            {
                                i++;
                                System.out.print("Enter 4-Digit PIN: ");
                                String bankPin=input.nextLine();
                                if(b.getPasscode().equals(bankPin))
                                {
                                    setLogSession(b);
                                    BankINIT();
                                    break Login;
                                }
                                if(i==3)
                                {
                                    Main.print("Too many unsuccessful attempts");
                                    break;
                                }
                                else
                                {
                                    System.out.println("Invalid pin");
                                }
                            }
                        }
                        else
                        {
                            Main.print("Bank Not found");

                        }
                    }

                }
            }
            else if (Main.getOption()==2) {
                return;
            }
        }
    }

    private static void setLogSession(Bank bank)
    {
        LoggedBank=bank;
        Main.print("Logged in to "+bank.getName());
    }

    private static void logOut()
    {
        LoggedBank=null;
        Main.print("Logged out successfully!\n");
    }

    public static void createNewBank()
    {

    }

    public static void showBanksMenu()
    {
        if(!BANKS.isEmpty())
        {
            Main.showMenuHeader("Available Banks");
            for(Bank bank: BANKS)
            {
                System.out.println("Bank ID: "+bank.getID()+" - "+bank.getName());
            }
        }
        else {
            Main.print("No Banks!");
        }
    }
    //return to private
    public static void addBank(Bank bank)
    {
        if(bank!=null)
        {
            BANKS.add(bank);
        }
    }

    public static Bank getBank(Comparator<Bank> comparator, Bank bank )
    {
        for(Bank BANK:BANKS)
        {
            if(BANK.getClass().isInstance(comparator.getClass())||BANK.getClass().isInstance(bank.getClass()))
            {
                return BANK;
            }
        }
        return null;
    }

    public static Account findaccount(String accountNumber)
    {
        for(Bank bank:BANKS)
        {
            Account account=LoggedBank.getBankAccount(bank,accountNumber);
            if(account!=null&&account.getAccountNumber().equals(accountNumber))
            {
                return account;
            }
        }
        return null;
    }

    public static int bankSize()
    {
        return BANKS.size();
    }
}
