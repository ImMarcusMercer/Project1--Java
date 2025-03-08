package Launchers;

import Accounts.*;
import Bank.*;

import java.util.*;

public class BankLauncher {
    // Private attributes
    private static final List<Bank> BANKS = new ArrayList<>();
    private Bank loggedBank = null;

    // Public methods
    public boolean isLogged() {
        return loggedBank != null;
    }

    public void bankInit() {
        // Implementation here
    }

    public void showAccounts() {
        // Implementation here
    }

    public void newAccounts() {
        // Implementation here
    }

    public void bankLogin() {
        // Implementation here
    }

    public void setLogSession(Bank b) {
        this.loggedBank = b;
    }

    public void logout() {
        this.loggedBank = null;
    }

    public void createNewBank() {
        // Implementation here
    }

    public void showBanksMenu() {
        // Implementation here
    }

    public void addBank(Bank b) {
        BANKS.add(b);
    }

    public Bank getBank(Comparator<Bank> comparator, Bank bank) {
        for (Bank b : BANKS) {
            if (comparator.compare(b, bank) == 0) {
                return b;
            }
        }
        return null; // Return null if no match is found
    }

    public Account findAccount(String accountNum) {
        // Implementation here
        return null;
    }

    public int bankSize() {
        return BANKS.size();
    }
}