package Launchers;

import Accounts.*;
import Bank.*;
import java.util.*;

public class SavingsAccountLauncher extends AccountLauncher {
    public static void savingsAccountInit()
    {

    }

    private static  void depositProcess()
    {

    }

    private static void fundTransferProcess()
    {

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
