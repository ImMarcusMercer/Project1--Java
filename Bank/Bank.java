package Bank;

import Accounts.*;
import Launchers.BankLauncher;
import Main.*;
import java.util.ArrayList;
import java.util.List;

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
        for(Account acc:BANKACCOUNTS)
        {
            if(acc.getClass().isInstance(AccountType))
            {
                System.out.println(acc);
            }
        }
    }

    public Account getBankAccount(Bank bank, String AccountNumber)
    {
        for(Account account:BANKACCOUNTS)
        {
            if(account.getAccountNumber().equals(AccountNumber))
            {
                return account;
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

        SavingsAccount newAcc = new SavingsAccount(this, idNumber, firstName, lastName, email, pin, initialDeposit);
        BANKACCOUNTS.add(newAcc);
        return newAcc;
    }

    public CreditAccount createNewCreditAccount() {
        List<Field<String, ?>> accountFields = createNewAccount();


        String idNumber = accountFields.get(0).getFieldValue();
        String firstName = accountFields.get(1).getFieldValue();
        String lastName = accountFields.get(2).getFieldValue();
        String email = accountFields.get(3).getFieldValue();
        String pin = accountFields.get(4).getFieldValue();

        CreditAccount newAcc = new CreditAccount(this, idNumber, firstName, lastName, email, pin);
        BANKACCOUNTS.add(newAcc);
        return newAcc;
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
        return "";
    }
}
