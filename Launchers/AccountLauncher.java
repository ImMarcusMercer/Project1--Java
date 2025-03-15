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

    public static void accountLogin()
    {

    }

    private static Bank selectBank()
    {
        BankLauncher.showBanksMenu();
        String bankId=Main.prompt("Enter Bank ID: ",true);
//        if(BankLauncher.getBank())
        return null;
    }

    private static void setLogSession()
    {

    }

    private static void destroyLogSession()
    {

    }

    public static Account checkCredentials()
    {
        return null;
    }

    protected static Account getLoggedAccount()
    {
        return loggedAccount;
    }
}
