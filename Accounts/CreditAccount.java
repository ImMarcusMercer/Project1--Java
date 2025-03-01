package Accounts;

import Banks.Bank;

public class CreditAccount extends Account implements Payment, Recompense {
    private double loan;

    public CreditAccount(Bank bank, String accNum, String ownerName, String ownerEmail, String pin, double loan) {
        super();
        this.loan = loan;
    }

    public double getLoan() {
        return loan;
    }

    public void setLoan(double loan) {
        this.loan = loan;
    }

    public String getLoanStatement() {
        return "Loan Balance: " + loan;
    }

    // @Override
    public boolean canCredit(double amount) {
        return this.loan >= amount;
    }

    // @Override
    public void adjustLoanAmount(double amount) {
        this.loan += amount;
    }

    @Override
    public boolean recompense(double amount) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean pay(Account account, double amount) throws IllegalAccountType {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
