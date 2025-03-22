package Bank;

import java.util.*;
import Accounts.*;
import Main.*;

public class BankLauncher {
    private static ArrayList<Bank> BANKS=new ArrayList<>();
    private static Bank LoggedBank=null;
    private static final Scanner input = new Scanner(System.in);
    private static int IDCount=0;

    //Methods
    public static Bank getLoggedBank()
    {
        return LoggedBank;
    }
    public static boolean isLogged()
    {
        return LoggedBank!=null;
    }
    public static ArrayList<Bank> getBankList()
    {
        return BANKS;
    }
    public static void setBankSession(Bank bank)
    {
        if(bank!=null)
        {
            LoggedBank=bank;
        }
    }
    public static void incrementID(){ IDCount+=1;}
    public int getIDCount() {
        return IDCount;
    }

    public static void BankINIT()
    {
        if(LoggedBank!=null)
        {
            while(true) {
                Main.showMenuHeader("Bank Menu");
//        "Show Accounts", "New Accounts", "Log Out";
                Main.showMenu(31);
                System.out.print("Enter Choice: ");
                int option = input.hasNextLine() ? input.nextInt() : 0;
                switch (option) {
                    //Show Accounts
                    case 1 -> {
                        showAccounts();
                    }
                    //New Accounts
                    case 2 -> {

                        //New account creation
                        Main.showMenuHeader("Select Account Type");
                        Main.showMenu(33);
                        Main.print("[3] Exit");
                        System.out.print("Enter Choice: ");
                        int option1 = input.hasNextLine() ? input.nextInt() : 0;
                        //New Credit account
                        if (option1 == 1) {
                            CreditAccount newCred = LoggedBank.createNewCreditAccount();
                            if (newCred != null) {
                                LoggedBank.addNewAccount(newCred);
                            }
                        }
                        //New Savings Account
                        else if (option1 == 2) {
                            SavingsAccount newSave = LoggedBank.createNewSavingsAccount();
                            if (newSave != null) {
                                LoggedBank.addNewAccount(newSave);
                            }
                        } else {
                            Main.print("Invalid Option");
                        }
                    }
                    case 3 -> {
                        logOut();
                        return;
                    }
                    default -> Main.print("Invalid Input");
                }
            }
        }
        else
        {
            Main.print("No bank selected!");
        }

    }

    private static void showAccounts()
    {
        do {
            Main.showMenuHeader("Show Account Options");
            Main.showMenu(32);
            String show = Main.prompt("Enter Choice: ", true);
            //"Credit Accounts", "Savings Accounts", "All Accounts", "Go Back"
            switch (show) {
                case "1" -> {

                    Main.showMenuHeader("Credit Accounts");
                    LoggedBank.showAccounts(CreditAccount.class);
                }
                case "2" -> {
                    Main.showMenuHeader("Savings Accounts");
                    LoggedBank.showAccounts(SavingsAccount.class);
                }
                case "3" -> {
                    Main.showMenuHeader("All Accounts");
                    LoggedBank.showAccounts(Account.class);
                }
                case "4" -> {
                    return;
                }
                default -> {
                    Main.print("Invalid input!\n");
                    continue;
                }
            }
            break;
        } while (true);
    }

    private static void newAccounts()
    {

    }

    public static void bankLogin()
    {
        showBanksMenu();
        System.out.print("Enter Bank Name: ");
        String Name = input.nextLine();
        for(Bank b: BANKS)
        {
            if(!b.getName().equals(Name))
            {
                Main.print("Bank not found!");
                return;
            }
            int tries=0;
            while(true)
            {
                tries+=1;
                if(tries==4){Main.print("Too many attempts!");break;}
                System.out.print("Enter Passcode: ");
                String Passcode = input.hasNextLine() ? input.nextLine() : "";
                if(b.getPasscode().equals(Passcode))
                {
                    setBankSession(b);
                    BankINIT();
                    break;
                }
                else
                {
                    Main.print("Invalid Pin");
                }
            }

        }
    }

    private static void setLogSession(Bank bank)
    {
        LoggedBank=bank;
        Main.print("Logged in to "+bank.getName());
    }

    private static void logOut()
    {
        LoggedBank=null;
        Main.print("Logged out successfully!\n");
    }

    public static void createNewBank()
    {
        Main.showMenuHeader("Create New Bank");
        System.out.print("Enter Bank Name: ");
        String name = input.hasNextLine() ? input.nextLine().trim() : "";

        System.out.print("Enter Bank Passcode (Enter - Set to default): ");
        String passcode = input.hasNextLine() ? input.nextLine().trim() : "1234";

        double depositLimit;
        double withdrawLimit;
        double creditLimit;
        double processingFee;

        System.out.print("Enter Deposit Limit (Enter - Set to default): ");
        if (input.hasNextLine()) {
            depositLimit=50000.0;
            input.nextLine();
        } // Consume newline
        else{
            depositLimit = input.nextDouble();
        }
        System.out.print("Enter Withdraw Limit (Enter - Set to default): ");
        if (input.hasNextLine()) {
            withdrawLimit=50000.0;
            input.nextLine();
        } // Consume newline
        else{
            withdrawLimit = input.nextDouble();
        }
        System.out.print("Enter Credit Limit (Enter - Set to default): ");
        if (input.hasNextLine()) {
            creditLimit=100000.0;
            input.nextLine();
        } // Consume newline
        else{
            creditLimit = input.nextDouble();
        }
        System.out.print("Enter Processing Limit (Enter - Set to default): ");
        if (input.hasNextLine()) {
            processingFee=10.0;
            input.nextLine();
        } // Consume newline
        else{
            processingFee = input.nextDouble();
        }

        for(Bank b: getBankList())
        {
            if(b.getName().equals(name))
            {
                Main.print("Bank with name "+name+" already exists!");
                break;
            }
        }

        addBank(new Bank(IDCount, name, passcode, depositLimit, withdrawLimit, creditLimit, processingFee));







//        Main.showMenuHeader("Create New Bank");
//        try {
//            Field<String, String> bankNameField = new Field<String, String>("Bank Name", String.class, " ",
//                    new Field.StringFieldValidator());
//            bankNameField.setFieldValue("Enter bank name: ");
//            String bankname = bankNameField.getFieldValue();
//
//            Field<String, String> bankpasscodeField = new Field<String, String>("Bank Passcode", String.class, " ",
//                    new Field.StringFieldValidator());
//            bankpasscodeField.setFieldValue("Enter bank passcode: ");
//            String passcode = bankpasscodeField.getFieldValue();
//
//            //Configure Bank Attributes?
//            String confChoice=Main.prompt("Would you like to configure Bank details?(y or n)",true);
//
//            if(confChoice.equals("y"))
//            {
//                Field<Double, Double> depositlimitField = new Field<Double, Double>("Deposit Limit", Double.class, 50000.00,
//                        new Field.DoubleFieldValidator());
//                depositlimitField.setFieldValue("Enter deposit limit: ");
//                double depositlimit = depositlimitField.getFieldValue();
//
//                Field<Double, Double> withdrawlimitField = new Field<Double, Double>("Withdraw Limit", Double.class, 50000.00,
//                        new Field.DoubleFieldValidator());
//                withdrawlimitField.setFieldValue("Enter withdraw limit: ");
//                double withdrawlimit = withdrawlimitField.getFieldValue();
//
//                Field<Double, Double> creditlimitField = new Field<Double,Double>("Credit Limit", Double.class, 10.00,
//                        new Field.DoubleFieldValidator());
//                creditlimitField.setFieldValue("Enter credit limit: ");
//                double creditlimit = creditlimitField.getFieldValue();
//                double fee;
//                while(true)
//                {
//                    Field<Double, Double> feeField = new Field<Double,Double>("Processing Fee", Double.class, 0.0, new Field.DoubleFieldValidator());
//                    creditlimitField.setFieldValue("Enter Processing Fee: ");
//                    fee = feeField.getFieldValue();
//                    if(fee>0&&fee<10)
//                    {
//                        break;
//                    }
//                    Main.print("Fee must be P1 - P10 Only!");
//                }
//
//                Bank newBank = new Bank(IDCount, bankname, passcode, depositlimit, withdrawlimit, creditlimit, fee);
//                addBank(newBank);
//            }
//            else if(confChoice.equals("n"))
//            {
//                double depositlimit=50000.0;
//                double withdrawlimit=50000.0;
//                double creditlimit=100000.0;
//                double fee= 10.0;
//                Main.print("Details have been set to default!");
//                Bank newBank = new Bank(IDCount, bankname, passcode, depositlimit, withdrawlimit, creditlimit, fee);
//                addBank(newBank);
//            }
//        } catch (Exception e) {
//            System.out.println("Error creating new bank.");
//        }
    }

    public static void showBanksMenu()
    {
        if(!BANKS.isEmpty())
        {
            Main.showMenuHeader("Available Banks");
            for(Bank bank: BANKS)
            {
                System.out.println("Bank ID: "+bank.getID()+" - "+bank.getName());
            }
        }
        else {
            Main.print("No Banks!");
        }
    }
    //return to private
    public static void addBank(Bank bank)
    {
        if(bank!=null)
        {
            BANKS.add(bank);
        }
    }

    public static Bank getBank(Comparator<Bank> bankComparator, Bank bank) {
        return BANKS.stream().filter(b -> bankComparator.compare(b, bank) == 0).findFirst().orElse(null);
    }

    public static Account findAccount(String accountNumber)
    {
        for(Bank bank:BANKS)
        {
            for(Account account:bank.getBANKACCOUNTS())
//            Account account=LoggedBank.getBankAccount(bank,accountNumber);
            {
                if (account.getAccountNumber().equals(accountNumber)) {
                    return account;
                }
            }

        }
        return null;
    }

    public static int bankSize()
    {
        return BANKS.size();
    }
}
