package Bank;

import Accounts.*;
import Main.*;
import java.util.*;

public class Bank {
    private int ID;
    private String name;
    private String passcode;
    private double DEPOSITLIMIT=50000.0, WITHDRAWLIMIT=50000.0, CREDITLIMIT=100000.0;
    private double processingFee=10.0;
    private ArrayList<Account> BANKACCOUNTS=new ArrayList<>();

    //Constructors
    public Bank(int ID, String Name, String Passcode)
    {
        this.ID=ID;
        this.name=Name;
        this.passcode=Passcode;
    }
    public Bank(int ID, String Name, String Passcode,double DL, double WL, double CL, double Fee)
    {
        this.ID=ID;
        this.name=Name;
        this.passcode=Passcode;
        this.DEPOSITLIMIT=DL;
        this.WITHDRAWLIMIT=WL;
        this.CREDITLIMIT=CL;
        this.processingFee=Fee;
    }

    //Bank Methods
    public ArrayList<Account> getBANKACCOUNTS()
    {
        return BANKACCOUNTS;
    }
    public int getID()
    {
        return ID;
    }
    public String getPasscode()
    {
        return passcode;
    }
    public String getName() {
        return name;
    }

    public <T> void showAccounts(Class<T> AccountType)
    {
            boolean found = false;
            for (Account account : BANKACCOUNTS) {
                if (AccountType.isInstance(account)) {
                    System.out.println(account);
                    found = true;
                }
            }
            if (!found) {
                System.out.println("There are currently no accounts stored.");
            }
//        if(AccountType.isInstance(CreditAccount.class))
//        {
//            boolean found = false;
//            Main.showMenuHeader("Credit Accounts");
//            for (Account account : BANKACCOUNTS) {
//                if (AccountType.isInstance(account)) {
//                    System.out.println(account);
//                    found = true;
//                }
//            }
//            if (!found) {
//                System.out.println("There are currently no accounts stored.");
//            }
//        }
//        else if(AccountType.isInstance(SavingsAccount.class))
//        {
//            boolean found = false;
//            Main.showMenuHeader("Savings Accounts");
//            for (Account account : BANKACCOUNTS) {
//                if (AccountType.isInstance(account)) {
//                    System.out.println(account);
//                    found = true;
//                }
//            }
//            if (!found) {
//                System.out.println("There are currently no accounts stored.");
//            }
//        }
//        else if (AccountType.isInstance(Account.class))
//        {
//            boolean found = false;
//            Main.showMenuHeader("All Accounts in "+name);
//            for (Account account : BANKACCOUNTS) {
//                if (AccountType.isInstance(account)) {
//                    System.out.println(account);
//                    found = true;
//                }
//            }
//            if (!found) {
//                System.out.println("There are currently no accounts stored.");
//            }
//        }


    }

    public Account getBankAccount(Bank bank, String AccountNumber)
    {
        for(Bank b:BankLauncher.getBankList())
        {
            if(b.equals(bank))
            {
                for(Account account:b.getBANKACCOUNTS())
                {
                    if(account.getAccountNumber().equals(AccountNumber))
                    {
                        return account;
                    }
                }
            }
        }
        return null;
    }

    public ArrayList<Field<String,?>> createNewAccount(){
        ArrayList<Field<String, ?>> accountFields = new ArrayList<>();

        Field<String, ?> idNumberField = new Field<String,String>("ID Number", String.class, "", new Field.StringFieldValidator());
        idNumberField.setFieldValue("Enter ID number: ");
        accountFields.add(idNumberField);

        Field<String, String> firstNameField = new Field<String,String>("First Name", String.class, "", new Field.StringFieldValidator());
        firstNameField.setFieldValue("Enter first name: ");
        accountFields.add(firstNameField);

        Field<String, String> lastNameField = new Field<String,String>("Last Name", String.class, "", new Field.StringFieldValidator());
        lastNameField.setFieldValue("Enter last name: ");
        accountFields.add(lastNameField);

        Field<String, String> emailField = new Field<String,String>("Email", String.class, "", new Field.StringFieldValidator());
        emailField.setFieldValue("Enter email: ");
        accountFields.add(emailField);

        Field<String, String> pinField = new Field<String,String>("PIN", String.class, "", new Field.StringFieldValidator());
        pinField.setFieldValue("Enter PIN: ");
        accountFields.add(pinField);

        Field<String, String> initialDepositField = new Field<String,String>("Initial Deposit", String.class, "", new Field.StringFieldValidator());
        initialDepositField.setFieldValue("Enter initial deposit: ");
        accountFields.add(initialDepositField);

        return accountFields;
    }

    public SavingsAccount createNewSavingsAccount() {
        List<Field<String, ?>> accountFields = createNewAccount();

        String idNumber = accountFields.get(0).getFieldValue();
        String firstName = accountFields.get(1).getFieldValue();
        String lastName = accountFields.get(2).getFieldValue();
        String email = accountFields.get(3).getFieldValue();
        String pin = accountFields.get(4).getFieldValue();
        double initialDeposit = Double.parseDouble(accountFields.get(5).getFieldValue());

        return new SavingsAccount(this, idNumber, firstName, lastName, email, pin, initialDeposit);
    }

    public CreditAccount createNewCreditAccount() {
        List<Field<String, ?>> accountFields = createNewAccount();


        String idNumber = accountFields.get(0).getFieldValue();
        String firstName = accountFields.get(1).getFieldValue();
        String lastName = accountFields.get(2).getFieldValue();
        String email = accountFields.get(3).getFieldValue();
        String pin = accountFields.get(4).getFieldValue();

        return new CreditAccount(this, idNumber, firstName, lastName, email, pin);

    }

    public void addNewAccount(Account account)
    {
        if(!accountExists(BankLauncher.getLoggedBank(),account.getAccountNumber())) {
            BANKACCOUNTS.add(account);
        }
        else {
            Main.print("Account already exists");
        }
    }
    public static boolean accountExists(Bank bank, String AccountNumber)
    {
        if(bank!=null)
        {
            for(Account account: bank.BANKACCOUNTS)
            {
                if(account.getAccountNumber().equals(AccountNumber))
                {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public String toString()
    {
        return "Bank Name: "+name+"\nRegistered Accounts: "+BANKACCOUNTS.size();
    }

    public double getDepositLimit() {
        return DEPOSITLIMIT;
    }

    public double getCreditLimit() {
        return CREDITLIMIT;
    }

    public double getProcessingFee() {
        return processingFee;
    }

    //Sub classes
    public class Bankcomparator implements Comparator<Bank> {
        @Override
        public int compare(Bank b1, Bank b2) {
            return b1.getName().compareTo(b2.getName());
        }
    }
    public class BankCredComparator implements Comparator<Bank> {
        @Override
        public int compare(Bank b1, Bank b2) {
            return b1.getPasscode().compareTo(b2.getPasscode());
        }
    }
    public static class BankIdComparator implements Comparator<Bank> {
        @Override
        public int compare(Bank b1, Bank b2) {
            return Integer.compare(b1.getID(), b2.getID());
        }
    }
}



