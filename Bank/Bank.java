package Bank;

import Accounts.*;
import Main.*;
import java.util.*;

public class Bank {
    private int ID;
    private String name;
    private String passcode;
    private double DEPOSITLIMIT, WITHDRAWLIMIT, CREDITLIMIT;
    private double processingFee=10.0;
    private ArrayList<Account> BANKACCOUNTS=new ArrayList<>();
    private static final Scanner input = new Scanner(System.in);
    private int AccountID=1000;

    //Constructors
    public Bank(int ID, String Name, String Passcode)
    {
        this.ID=ID;
        this.name=Name;
        this.passcode=Passcode;
        this.DEPOSITLIMIT=50000.0;
        this.WITHDRAWLIMIT=50000.0;
        this.CREDITLIMIT=100000.0;
    }
    public Bank(int ID,String Name, String Passcode,double DL, double WL, double CL, double Fee)
    {
        this.ID=ID;
        this.name=Name;
        this.passcode=Passcode;
        this.DEPOSITLIMIT=DL;
        this.WITHDRAWLIMIT=WL;
        this.CREDITLIMIT=CL;
        this.processingFee=Fee;
        BankLauncher.incrementID();
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

        Main.print("Auto Generated ID Number: "+AccountID);
        Field<String, String> pinField = new Field<String,String>("PIN", String.class, "", new Field.StringFieldValidator());
        pinField.setFieldValue("Enter PIN: ");
        accountFields.add(pinField);

        Field<String, String> firstNameField = new Field<String,String>("First Name", String.class, "", new Field.StringFieldValidator());
        firstNameField.setFieldValue("Enter first name: ");
        accountFields.add(firstNameField);

        Field<String, String> lastNameField = new Field<String,String>("Last Name", String.class, "", new Field.StringFieldValidator());
        lastNameField.setFieldValue("Enter last name: ");
        accountFields.add(lastNameField);

        Field<String, String> emailField = new Field<String,String>("Email", String.class, "", new Field.StringFieldValidator());
        emailField.setFieldValue("Enter email: ");
        accountFields.add(emailField);



        return accountFields;
    }

    public SavingsAccount createNewSavingsAccount() {
        List<Field<String, ?>> accountFields = createNewAccount();



        String pin = accountFields.get(0).getFieldValue();
        String firstName = accountFields.get(1).getFieldValue();
        String lastName = accountFields.get(2).getFieldValue();
        String email = accountFields.get(3).getFieldValue();

        double initialDeposit;
        System.out.print("Enter Initial Deposit (Press Enter to skip): ");
        String depositInput = input.nextLine().trim(); // Read input and trim spaces

        if (depositInput.isEmpty()) {
            Main.print("Initial deposit is 0");
            initialDeposit = 0.0;
        } else {
            try {
                initialDeposit = Double.parseDouble(depositInput);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Setting initial deposit to 0.");
                initialDeposit = 0.0;
            }
        }
        AccountID+=1;
        Main.print("Account "+AccountID+" created successfully!");
        return new SavingsAccount(this, ""+AccountID, pin,firstName, lastName, email,  initialDeposit);
    }

    public CreditAccount createNewCreditAccount() {
        List<Field<String, ?>> accountFields = createNewAccount();

        String pin = accountFields.get(0).getFieldValue();
        String firstName = accountFields.get(1).getFieldValue();
        String lastName = accountFields.get(2).getFieldValue();
        String email = accountFields.get(3).getFieldValue();
        AccountID+=1;
        Main.print("Account "+AccountID+" created successfully!");
        return new CreditAccount(this, ""+AccountID, firstName, lastName, email, pin);

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
        return "\nBank Name: "+name+"\n"+getCreditLimit()+"\nRegistered Accounts: "+BANKACCOUNTS.size();
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
//        @Override
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



