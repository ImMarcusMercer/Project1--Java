package Launchers;

import Accounts.*;
import Bank.*;
import Launchers.*;
import Main.*;

public class CreditAccountLauncher extends AccountLauncher
{
    public static void credAccountInit()
    {
        Account current= AccountLauncher.getLoggedAccount();
        Class<CreditAccount> creditAccountClass = CreditAccount.class;
        if (current.getClass().isInstance(creditAccountClass))
        {
            Main.showMenuHeader("Credit Account Menu");
            Main.showMenu(41);
            Main.setOption();
            //"Show Credits", "Pay", "Recompense", "Show Transactions", "Logout"
            switch (Main.getOption())
            {
                case 1->{
//                    current.
                }
                case 2->{

                }
            }
        }
        else
        {
            Main.print("Invalid account");
        }

    }

    private static void creditPaymentProcess()
    {

    }

    private static void creditRecompenseProcess()
    {

    }
    protected static CreditAccount getLoggedAccount()
    {
        return null;
    }
}
