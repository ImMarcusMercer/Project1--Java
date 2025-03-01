package Banks;
import Accounts.Transaction;
import java.util.ArrayList;

public abstract class Account {
    protected Bank bank;
    protected String accountNumber;
    protected String ownerName;
    protected String ownerEmail;
    protected String pin;
    protected ArrayList<Transaction> transactions;

    public Account(Bank bank, String accountNumber, String ownerName, String ownerEmail, String pin) {
        this.bank = bank;
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.ownerEmail = ownerEmail;
        this.pin = pin;
        this.transactions = new ArrayList<>();
    }

    public String getOwnerFullName() {
        return ownerName;
    }
    
    public String getAccountNumber() {
        return accountNumber;
    }
    
    public String getOwnerEmail() {
        return ownerEmail;
    }
    
    public void addNewTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    @Override
    public String toString() {
        return "Account{accountNumber='" + accountNumber + "', ownerName='" + ownerName + "', ownerEmail='" + ownerEmail + "'}";
    }
}
