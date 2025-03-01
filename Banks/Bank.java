package Banks;
// package Accounts;

import Accounts.Account;
import Accounts.CreditAccount;
import Accounts.SavingsAccount;
import java.util.ArrayList;

// import Banks.Account;

// import Account;

// import Banks.Account;

public class Bank {
    private int id;
    private String name;
    private String passcode;
    private double depositLimit, withdrawLimit, creditLimit;
    private double processingFee;
    private ArrayList<Account> bankAccounts;

    public Bank(int id, String name, String passcode) {
        this.id = id;
        this.name = name;
        this.passcode = passcode;
        this.bankAccounts = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPasscode() {
        return passcode;
    }

    public Account getBankAccount(String accountNum) {
        for (Account acc : this.bankAccounts) {
            if (acc.getAccountNumber().equals(accountNum)) {
                return acc;
            }
        }
        return null;
    }

    public void addNewAccount(Account account) {
        bankAccounts.add(account);
    }

    public CreditAccount createNewCreditAccount(String accountNumber, String ownerName, String ownerEmail, String pin, double loan) {
        CreditAccount account = new CreditAccount(this, accountNumber, ownerName, ownerEmail, pin, loan);
        this.addNewAccount(account);
        return account;
    }

    public SavingsAccount createNewSavingsAccount(String accountNumber, String ownerName, String ownerEmail, String pin, double balance) {
        SavingsAccount account = new SavingsAccount(this, accountNumber, ownerName, ownerEmail, pin, balance);
        this.addNewAccount(account);
        return account;
    }

    @Override
    public String toString() {
        return "Bank{" + "name='" + name + '\'' + ", id=" + id + '}';
    }
}
