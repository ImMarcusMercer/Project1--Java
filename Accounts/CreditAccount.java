package Accounts;

import Bank.*;
import java.util.*;
import Launchers.*;

public class CreditAccount extends Account{
    private double Loan;
    private double CreditLimit=100000.0;
    public CreditAccount(Bank bank, String accountNumber,String FirstName,String LastName, String Email,String pin)
    {
        super(bank, accountNumber, FirstName, LastName, Email, pin);
    }

    //Methods
    public String getLoanStatement()
    {
        return "Loan: P"+Loan;
    }

    private boolean canCredit(double amountAdjustment)
    {
        return Loan >= amountAdjustment;
    }

    private void adjustLoanAmount(double amountAdjustment)
    {
        Loan+=amountAdjustment;
    }

    public String toString()
    {
        return "CA";
    }
}
