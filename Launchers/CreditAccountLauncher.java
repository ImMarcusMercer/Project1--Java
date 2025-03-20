package Launchers;

import Accounts.*;
import Bank.*;
import Launchers.*;
import Main.*;

public class CreditAccountLauncher extends AccountLauncher
{
    //Check if correct implementation & Simplyfied the code structure thus easy to read
    public static void credAccountInit()
    {
        Account current = AccountLauncher.getLoggedAccount();
        Class<CreditAccount> creditAccountClass = CreditAccount.class;

        if (creditAccountClass.isInstance(current)) {
            CreditAccount creditAccount = (CreditAccount) current;

            Main.showMenuHeader("Credit Account Menu");
            Main.showMenu(41);
            Main.setOption();

            //"Show Credits", "Pay", "Recompense", "Show Transactions", "Logout"
            switch (Main.getOption()) {
                case 1 -> Main.print(creditAccount.getLoanStatement());
                case 2 -> creditPaymentProcess(creditAccount);
                case 3 -> creditRecompenseProcess(creditAccount);
                case 4 -> Main.print(creditAccount.getTransactionsInfo());
                case 5 -> Main.print("Logging out...");
                default -> Main.print("Invalid option! Please select again.");
            }
        } else {
            Main.print("Invalid account.");
        }

    }

    //Check if correct implementation
    private static void creditPaymentProcess(CreditAccount creditAccount)
    {
        if (creditAccount != null) {
            Main.print("Processing credit payment...");

            String description = "Credit Payment Processed";
            creditAccount.addNewTransaction(creditAccount.getAccountNumber(), Transaction.Transactions.Payment, description);

            Main.print("Payment successful!");
        } else {
            Main.print("Invalid Credit Account.");
        }
    }

    //Check if correct implementation
    private static void creditRecompenseProcess(CreditAccount creditAccount)
    {
        if (creditAccount != null) {
            Main.print("Processing recompense...");

            String description = "Credit Recompense Processed";
            creditAccount.addNewTransaction(creditAccount.getAccountNumber(), Transaction.Transactions.Recompense, description);

            Main.print("Recompense successful!");
        } else {
            Main.print("Invalid Credit Account.");
        }
    }

    //Check if correct implementation
    protected static CreditAccount getLoggedAccount()
    {
        Bank loggedBank = BankLauncher.getLoggedBank();
        if (loggedBank != null)
        {
            for (Account account : loggedBank.getBANKACCOUNTS())
            {
                if (account instanceof CreditAccount)
                {
                    return (CreditAccount) account;
                }
            }
        }
        return null;
    }


}
