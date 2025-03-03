package Accounts;
import Banks.Bank;

public class SavingsAccount extends Account implements Withdrawal, Deposit, FundTransfer {
    private double balance;

    public SavingsAccount(Bank bank, String accNum, String ownerName, String ownerEmail, String pin, double balance) {
        super(bank, accNum, ownerName, ownerEmail, pin);
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getAccountBalance() {
        return "Balance: " + balance;
    }

    // @Override
    public void insufficientBalance() {
        System.out.println("Insufficient balance!");
    }

    // @Override
    public void adjustAccountBalance(double amount) {
        this.balance += amount;
    }

    // @Override
    public boolean hasEnoughBalance(double amount) {
        return this.balance >= amount;
    }

    // @Override
    // public boolean transfer(Bank bank, Account account, double amount) throws IllegalAccountType {
    //     throw new UnsupportedOperationException("Unimplemented method 'transfer'");
    // }

    @Override
    public boolean transfer(Account account, double amount) throws IllegalAccountType {
        throw new UnsupportedOperationException("Unimplemented method 'transfer'");
    }

    @Override
    public boolean cashDeposit(double amount) {
        throw new UnsupportedOperationException("Unimplemented method 'cashDeposit'");
    }

    @Override
    public boolean withdrawal(double amount) {
        throw new UnsupportedOperationException("Unimplemented method 'withdrawal'");
    }

    @Override
    public boolean transfer(Accounts.Bank bank, Account account, double amount) throws IllegalAccountType {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean transfer(Bank bank, Account account, double amount) throws IllegalAccountType {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'transfer'");
    }
}
