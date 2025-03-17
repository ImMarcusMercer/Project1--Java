package Accounts;

import Bank.*;
import java.util.*;
import Launchers.*;

public class CreditAccount extends Account implements Payment,Recompense{

    private double Loan;
    private double CreditLimit=100000.0;
    public CreditAccount(Bank bank, String accountNumber,String FirstName,String LastName, String Email,String pin)
    {
        super(bank, accountNumber, FirstName, LastName, Email, pin);
    }

    //Methods
    public double getLoan() {
        return Loan;
    }
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
        return "Credit Account\nAccount Number: "+getAccountNumber()+"\nOwner: "+getOwnerFullName()+"\n"+getLoanStatement()+"\n";
    }
    @Override
    public boolean pay(Account target_account, double amount_to_pay) throws IllegalAccountType{
        if (!(target_account instanceof CreditAccount || target_account instanceof SavingsAccount)){
            throw new IllegalAccountType("Credit accounts cannot pay to other credit accounts."); // Ensures payments are only to valid account types
        }
        else{
            if (Loan >= amount_to_pay){
                adjustLoanAmount(-amount_to_pay); // Deducts the payment amount from the loan
                System.out.println("\u001B[32mPayment successful.\u001B[0m");
                return true;
            }
            else{
                System.out.println("\u001B[31mLoan account insufficient. Please Retry.\u001B[0m"); // Payment fails due to insufficient loan amount
                return false;
            }
        }
    }

    // Recompenses a specified amount from the loan, reducing the total loan amount
    @Override
    public boolean recompense(double amount){
        if (amount <= 0 || amount > Loan){
            return false; // Recompense amount is invalid
        }
        adjustLoanAmount(-amount); // Deducts the recompense amount from the loan
        return true;
    }
}
