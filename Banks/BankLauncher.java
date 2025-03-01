package Banks;

import java.util.ArrayList;

public class BankLauncher {
    private static final ArrayList<Bank> banks = new ArrayList<>();
    private static Bank loggedBank = null;

    public boolean isLoggedIn() {
        return loggedBank != null;
    }

    public boolean bankLogin(String bankName, String passcode) {
        for (Bank bank : banks) {
            if (bank.getName().equals(bankName) && bank.getPasscode().equals(passcode)) {
                loggedBank = bank;
                System.out.println("Login successful.");
                return true;
            }
        }
        System.out.println("Invalid credentials.");
        return false;
    }

    public void showAccounts() {
        if (loggedBank != null) {
            System.out.println("Showing accounts for " + loggedBank.getName());
        } else {
            System.out.println("No bank logged in.");
        }
    }

    public void newAccount() {
        System.out.println("Creating new account...");
    }

    public void logout() {
        loggedBank = null;
    }

    public void createNewBank(Bank bank) {
        banks.add(bank);
        System.out.println("New bank created: " + bank.getName());
    }

    public int bankSize() {
        return banks.size();
    }
}