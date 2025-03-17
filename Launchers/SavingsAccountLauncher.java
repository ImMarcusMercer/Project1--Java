package Launchers;

import Accounts.*;

import java.util.*;
import Main.*;

public class SavingsAccountLauncher extends AccountLauncher {

    private static final Scanner input = new Scanner(System.in);

    public static void savingsAccountInit(SavingsAccount found)
    {
        if (found instanceof SavingsAccount)
 {
            //"Show Balance", "Deposit", "Withdraw", "Fund Transfer",
            //            "Show Transactions", "Logout"
            while (true) {
                Main.showMenuHeader("Savings Account Menu");
                Main.showMenu(51);
                Main.setOption();
                //Show balance
                if (Main.getOption() == 1) {
                }
                //Deposit
                else if (Main.getOption() == 2) {
                    System.out.print("Enter Deposit amount: ");
                    int amount = input.nextInt();
                    input.nextLine();
                    depositProcess(amount);
                }
                //Withdraw
                else if (Main.getOption() == 3)
                {
                }
                //Fund Transfer
                else if (Main.getOption() == 4)
                {
                }
                //Show Transactions
                else if (Main.getOption() == 5)
                {
                    found.getTransactionsInfo();
                }
                //logout
                else if (Main.getOption() == 6) {
                    break;
                } else {
                    Main.print("Invalid input");
                }
            }
        }
    }

    private static  void depositProcess(double amount)
    {
        System.out.println("Processing deposit of: ₱" + amount);
        
    }

    public void withdrawProcess(double amount)
    {
        System.out.println("Processing withdrawal of: ₱" + amount);
    }

    private static void fundTransferProcess(String recipientacc, double amount)
    {
        System.out.println("Transferring ₱" + amount + "To Account: " + recipientacc);
    }

    protected static SavingsAccount getLoggedAccount() {
        Account check = AccountLauncher.getLoggedAccount();
        if (check instanceof SavingsAccount) {
            return (SavingsAccount) check;
        }
        System.out.println("DEBUG: No SavingsAccount found for logged user.");
        return null;
    }
}    
        