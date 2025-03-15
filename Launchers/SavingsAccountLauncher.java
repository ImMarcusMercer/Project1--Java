package Launchers;

import Accounts.*;
import Bank.*;
import java.util.*;

public class SavingsAccountLauncher extends AccountLauncher {
    public static void savingsAccountInit()
    {
        System.out.println("Welcomet to Savings Account. Displaying main menu");
    }

    private static  void depositProcess(double amount)
    {
        System.out.println("Processing deposit of: ₱" + amount);
    }

    public void withdrawProcess(double amount)
    {
        System.out.println("Processing withdrawal of: ₱" + amount);
    }

    private static void fundTransferProcess(String recipientacc, double amout)
    {
        System.out.println("Trasferring ₱" + amout + "To Account: " + recipientacc);
    }

    protected static SavingsAccount getLoggedAccount()
    {
        Account check =AccountLauncher.getLoggedAccount();
        if(check!=null)
        {
            if(check.getClass().isInstance(SavingsAccount.class))
            {
                return null;
            }
        }
        return null;
    }
}
