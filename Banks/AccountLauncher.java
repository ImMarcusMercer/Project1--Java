package Banks;

public class AccountLauncher {
    private Account loggedAccount;
    private Bank assocBank;

    public boolean isLoggedIn() {
        return loggedAccount != null;
    }

    public void accountLogin() {
        System.out.println("Account login...");
    }

    public void selectBank(Bank bank) {
        this.assocBank = bank;
    }

    public void setLoggedSession(Account account) {
        this.loggedAccount = account;
    }

    public void destroyLoggedSession() {
        this.loggedAccount = null;
    }

    public boolean checkCredentials() {
        return loggedAccount != null;
    }

    public Account getLoggedAccount() {
        return loggedAccount;
    }
}
