package Launchers;

import Accounts.*;
import Bank.*;

public class AccountLauncher {
    protected Account loggedAccount; // Holds the currently logged-in account
    protected Bank assocBank;        // Associated bank

    // Method to check if a user is logged in
    public boolean isLoggedIn() {
        return loggedAccount != null;
    }

    // Method to set a user login session
    public void login(Account account) {
        this.loggedAccount = account;
        System.out.println("Login successful for account: " + account.getAccountNumber());
    }

    // Method to set the bank associated with this launcher
    public void selectBank(Bank bank) {
        this.assocBank = bank;
    }

    // Method to destroy the current user session
    public void logout() {
        this.loggedAccount = null;
        System.out.println("Logged out successfully.");
    }

    // Method to check credentials (dummy implementation)
    public Account checkCredentials(Bank bank, String accountNumber, String pin) {
        if (assocBank != null) {
            Account account = Bank.getBankAccount(bank, accountNumber);
            if (account != null && account.getPin().equals(pin)) {
                return account;
            }
        }
        return null;
    }

    // Method to return the logged-in account
    public Account getLoggedAccount() {
        return loggedAccount;
    }
}