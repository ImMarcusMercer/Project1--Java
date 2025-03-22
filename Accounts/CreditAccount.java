package Accounts;

import Bank.*;
import java.util.*;
import Launchers.*;

public class CreditAccount extends Account implements Payment,Recompense
{
    private double Loan;
    private double CreditLimit;
    public CreditAccount(Bank bank, String accountNumber,String pin,String FirstName,String LastName, String Email)
    {
        super(bank, accountNumber, pin, FirstName, LastName, Email);
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
        if(canCredit(amountAdjustment))
        {
            if(Loan+amountAdjustment<0)
            {
                Loan=0;
            }
            Loan+=amountAdjustment;
        }
    }

    public String toString()
    {
        return "Credit Account\nAccount Number: "+getAccountNumber()+"\nOwner: "+getOwnerFullName()+"\n"+getLoanStatement()+"\n";
    }

    public double getLoan() {
        return Loan;
    }

    @Override
    public boolean pay(Account target_account, double amount_to_pay) throws IllegalAccountType
    {
        if (!(target_account instanceof CreditAccount))
        {
            throw new IllegalAccountType("Credit accounts cannot pay to other credit accounts.");
        }
        else{
            if (Loan >= amount_to_pay){
                adjustLoanAmount(-amount_to_pay);
                System.out.println("Payment successful.");
                return true;
            }
            else{
                System.out.println("Loan account insufficient. Please Retry.");
                return false;
            }
        }
    }

    @Override
    public boolean recompense(double amount)
    {
        if (amount <= 0 || amount > Loan)
        {
            return false;
        }
        adjustLoanAmount(-amount); // Deducts the recompense amount from the loan
        return true;
    }
}
