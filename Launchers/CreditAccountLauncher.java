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
        if (creditAccountClass.isInstance(current))

        {
            Main.showMenuHeader("Credit Account Menu");
            Main.showMenu(41);
            Main.setOption();
            //"Show Credits", "Pay", "Recompense", "Show Transactions", "Logout"
            switch (Main.getOption())
            {
                case 1->
                {
                    current.
                }
                case 2->
                {
                    creditPaymentProcess();
                }
                case 3->
                {
                    current.getTransactionsInfo();
                }
            }
        }
        else
        {
            Main.print("Invalid account");
        }

    }

    private static void creditPaymentProcess(CreditAccount loggedCreditAccount) throws IllegalAccountType {
        // Field for entering the target account number
        Field<String, String> targetaccountnumberField = new Field<>("targetAccount",
                String.class, " ", new Field.StringFieldValidator());
        targetaccountnumberField.setFieldValue("Enter target account number: ");
        String accountNumber = targetaccountnumberField.getFieldValue();

        // Field for entering the amount to pay
        Field<Double, Double> amountField = new Field<>("amount",
                Double.class, 0.0, new Field.DoubleFieldValidator());
        amountField.setFieldValue("Enter target account number: "); // Mistake in prompt text, should be "Enter amount to pay:"
        double amountToPay = amountField.getFieldValue();

        // Find the target account and attempt the payment
        Account targetAccount = BankLauncher.findAccount(accountNumber);
        double processingFee = loggedCreditAccount.getBank().getprocessingFee();
        double payAmountWithFee = amountToPay + processingFee;
        boolean paySuccess = loggedCreditAccount.pay(targetAccount, payAmountWithFee);

        if (paySuccess){
            System.out.println("\u001B[32mTransfer of ₱" + amountToPay + " processed successfully.\u001B[0m");
        }
        else{
            System.out.println("\u001B[31mInsufficient Balance. Please Retry.\u001B[0m");
        }
    }

    private static void creditRecompenseProcess()
    {

    }
    protected static CreditAccount getLoggedAccount()
    {
        return null;
    }
}
