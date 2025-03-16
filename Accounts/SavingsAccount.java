package Accounts;

import Bank.*;

import java.sql.Savepoint;
import java.util.*;
import Launchers.*;
import Main.Main;

public class SavingsAccount extends Account
{
    private double Balance;


    public SavingsAccount(Bank bank, String accountNumber,String FirstName,String LastName, String Email,String pin, double initialDeposit)
    {
        super(bank,accountNumber,FirstName,LastName,Email,pin);
        this.Balance=initialDeposit;
    }

    //Methods
    public String getAccountBalanceStatement()
    {
        return String.format("Account Balance: %f",this.Balance);
    }

    private boolean hasEnoughBalance(double amount)
    {
        return this.Balance >= amount;
    }

    private void insufficientBalance(double amount)
    {
        if(this.Balance<amount)
        {
            Main.print("Account has insufficient Balance.");
        }
    }

    private void adjustAccountBalance(double amount)
    {
        if(this.Balance+amount<0)
        {
            this.Balance=0;
        }
        this.Balance+=amount;
    }

    public String toString()
    {
        return String.format("Savings Account\nAccount Number: %s\n%s\nOwner: %s\n",getAccountNumber(),getAccountBalanceStatement(),getOwnerFullName());
    }

    //Additional Methods
    public void transfer(Account account, double amount)throws IllegalAccountType
    {
        if(account!=null&&hasEnoughBalance(amount)&&account.getClass().isInstance(SavingsAccount.class))
        {
            adjustAccountBalance(-amount);
            addNewTransaction(this.getAccountNumber(),Transaction.Transactions.FundTransfer,"Transferred Amount: "+amount+" to Recipient: "+account.getAccountNumber());
        }
    }
    public void transfer(Bank bank, Account account, double amount)
    {
        //
    }
}
