package Accounts;

import java.util.*;
import Bank.*;
import Main.*;

public abstract class Account {
    private Bank BANK;
    private String AccountNumber;
    private String OwnerFName,OwnerLName, OwnerEmail;
    private String Pin;
    private ArrayList<Transaction> TRANSACTIONS;

    //Methods
    public Account(Bank bank, String accountNumber,String FirstName,String LastName, String Email,String pin)
    {
        this.BANK=bank;
        this.AccountNumber=accountNumber;
        this.OwnerFName=FirstName;
        this.OwnerLName=LastName;
        this.OwnerEmail=Email;
        this.Pin=pin;
        this.TRANSACTIONS=new ArrayList<>();
    }
    public String getPin()
    {
        return this.Pin;
    }

    public String getAccountNumber()
    {
        return this.AccountNumber;
    }

    public String getOwnerFullName()
    {
        return OwnerLName+", "+ OwnerFName;
    }

    public void addNewTransaction(String accountNumber, Transaction.Transactions type, String description)
    {
        Transaction newTransaction= new Transaction(accountNumber,type,description);
        this.TRANSACTIONS.add(newTransaction);
    }

    public String getTransactionsInfo()
    {
        Main.showMenuHeader("Transactions");

        StringBuilder result= new StringBuilder();
        if(TRANSACTIONS.isEmpty())
        {
            result.append("No Transactions");
            return result.toString();
        }
        for(Transaction transaction:this.TRANSACTIONS)
        {
            String result1=String.format("Account Number: "+transaction.accountNumber+"\tTransaction Type: "+transaction.transactionType+"\tDescription: "+transaction.description+"\n");
            result.append(result1);
        }
        return result.toString();
    }

    public String toString()
    {
        return String.format("Account Owner: %s\n",this.getOwnerFullName());
    }

    public String getOwnerEmail() {
        return OwnerEmail;
    }
}
