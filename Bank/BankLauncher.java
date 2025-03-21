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
        INIT:
        while(true)
        {
            if(LoggedBank!=null)
            {
                Main.showMenuHeader("Bank Menu");
//        "Show Accounts", "New Accounts", "Log Out";
                Main.showMenu(31);
                Main.setOption();
                switch (Main.getOption())
                {
                    //Show Accounts
                    case 1->{
                        showAccounts();
                    }
                    //New Accounts
                    case 2->{
                        //New account creation
                        Main.showMenuHeader("Select Account Type");
                        Main.showMenu(33);
                        Main.setOption();
                        //New Credit account
                        if(Main.getOption()==1)
                        {
                            CreditAccount newCred=LoggedBank.createNewCreditAccount();
                            if(newCred!=null)
                            {
                                LoggedBank.addNewAccount(newCred);
                            }
                        }
                        //New Savings Account
                        else if(Main.getOption()==2)
                        {
                            SavingsAccount newSave=LoggedBank.createNewSavingsAccount();
                            if(newSave!=null)
                            {
                                LoggedBank.addNewAccount(newSave);
                            }
                        }
                        else {Main.print("Invalid Option");}
                    }
                    case 3-> {
                        logOut();
                        break INIT;
                    }
                }
            }
            else
            {
                Main.print("No bank selected!");
                break;
            }
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
        String Name = input.hasNextLine() ? input.nextLine() : "";
        System.out.print("Enter Passcode: ");
        String Passcode = input.hasNextLine() ? input.nextLine() : "";
        for(Bank b: BANKS)
        {
            if(b.getName().equals(Name)&&b.getPasscode().equals(Passcode))
            {
                LoggedBank=b;
            }
            else
            {
                Main.print("Bank not found!");
            }

        }
//        Login:
//        while(true)
//        {
//            Main.showMenuHeader("Bank");
//            Main.showMenu(3);
//            Main.setOption();
//            if(Main.getOption()==1)
//            {
//                BankLauncher.showBanksMenu();
//                if(!BANKS.isEmpty())
//                {
//                    System.out.print("Enter Bank ID: ");
//                    int bankID=input.nextInt();
//                    input.nextLine();
//                    for(Bank b: BANKS)
//                    {
//                        if(b.getID()==bankID)
//                        {
//                            int i= 0;
//                            while(i<3)
//                            {
//                                i++;
//                                System.out.print("Enter 4-Digit PIN: ");
//                                String bankPin=input.nextLine();
//                                if(b.getPasscode().equals(bankPin))
//                                {
//                                    setLogSession(b);
//                                    BankINIT();
//                                    break Login;
//                                }
//                                if(i==3)
//                                {
//                                    Main.print("Too many unsuccessful attempts");
//                                    break;
//                                }
//                                else
//                                {
//                                    System.out.println("Invalid pin");
//                                }
//                            }
//                        }
//
//                    }
//                    Main.print("Bank Not found");
//                }
//            }
//            else if (Main.getOption()==2) {
//                Main.print("Exiting Bank Login");
//                return;
//            }
//        }
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
        //int ID, String Name, String Passcode,double DL, double WL, double CL, double Fee
        if (input == null) {
            throw new IllegalStateException("Scanner is not initialized.");
        }

        System.out.print("Enter Bank Name: ");
        String Name = input.hasNextLine() ? input.nextLine() : "";

        System.out.print("Enter Bank Passcode: ");
        String Passcode = input.hasNextLine() ? input.nextLine() : "";

        System.out.print("Enter Deposit Limit: ");
        double DL = input.hasNextDouble() ? Double.parseDouble(input.nextLine().trim()) : 50000.0;
        if (input.hasNextLine()) input.nextLine();

        System.out.print("Enter Withdraw Limit: ");
        double WL = input.hasNextDouble() ? Double.parseDouble(input.nextLine().trim()) : 50000.0;
        if (input.hasNextLine()) input.nextLine();

        System.out.print("Enter Credit Limit: ");
        double CL = input.hasNextDouble() ? Double.parseDouble(input.nextLine().trim()) : 100000.0;
        if (input.hasNextLine()) input.nextLine();

        System.out.print("Enter Processing Fee: ");
        double PF = input.hasNextDouble() ? Double.parseDouble(input.nextLine().trim()) : 10.0;
        addBank(new Bank(IDCount,Name, Passcode, DL, WL, CL, PF));
        incrementID();
        if (input.hasNextLine()) input.nextLine();







//        Main.showMenuHeader("Create New Bank");
//
//        int bankID;
//        String bankName, bankPIN;
//        bankID=1;
//
//        while (true) {
//            try {
////                bankID = Integer.parseInt(Main.prompt("Enter Bank ID: ", true));
////                int finalBankID = bankID;
////                if (BANKS.stream().anyMatch(b -> b.getID() == finalBankID)) {
////                    Main.print("Bank ID already exists! Try again.");
////                    continue;
////                }
//
//                bankName = Main.prompt("Enter Bank Name: ", true);
//                if (bankName.isEmpty()) {
//                    Main.print("Bank name cannot be empty! Try again.");
//                    continue;
//                }
//
//                bankPIN = Main.prompt("Enter 4-Digit PIN: ", true);
////                if (!bankPIN.matches("\\d{4}")) {
////                    Main.print("Invalid PIN! Must be exactly 4 digits.");
////                    continue;
////                }
//                break;
//            } catch (NumberFormatException e) {
//                Main.print("Invalid input! Bank ID must be a number.");
//            }
//        }
//        addBank(new Bank(bankID, bankName, bankPIN));
//        Main.print("Bank successfully created!");
//        try {
//            Field<Integer, Integer> bankIdField = new Field<Integer, Integer>("Bank ID", Integer.class, 0,
//                    new Field.IntegerFieldValidator());
//            bankIdField.setFieldValue("Enter bank ID: ");
//            int bankid = bankIdField.getFieldValue();
//
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
//            Field<Double, Double> depositlimitField = new Field<Double, Double>("Deposit Limit", Double.class, 50000.00,
//                    new Field.DoubleFieldValidator());
//            depositlimitField.setFieldValue("Enter deposit limit: ");
//            double depositlimit = depositlimitField.getFieldValue();
//
//            if (depositlimit <= 50000.0) {
//                System.out.println("Deposit limit has been set to " + depositlimit);
//            } else {
//                while (depositlimit > 50000.0) {
//                    System.out.println("Do not exceed 50,000.0 for the deposit limit");
//                    depositlimit = 50000.0;
//                }
//                System.out.println("Deposit limit has been set to " + depositlimit);
//            }
//            Field<Double, Double> withdrawlimitField = new Field<Double, Double>("Withdraw Limit", Double.class, 50000.00,
//                    new Field.DoubleFieldValidator());
//            withdrawlimitField.setFieldValue("Enter withdraw limit: ");
//            double withdrawlimit = withdrawlimitField.getFieldValue();
//
//            if (withdrawlimit <= 50000.0) {
//                System.out.println("withdraw limit has been set to " + withdrawlimit);
//            } else {
//                while (withdrawlimit > 50000.0) {
//                    System.out.println("Do not exceed 50,000.0 for the Withdraw limit");
//                    // Ask the user for a new input for the deposit limit
//                    // For example:
//                    // depositlimit = getUserInputForDepositLimit();
//                    withdrawlimit = 50000.0; // Set depositlimit to the maximum allowed value for now
//                }
//                System.out.println("Withdraw limit has been set to " + withdrawlimit);
//            }
//
//            // Continue with the rest of your code
//            Field<Double, Double> creditlimitField = new Field<Double,Double>("Credit Limit", Double.class, 100000.00,
//                    new Field.DoubleFieldValidator());
//            creditlimitField.setFieldValue("Enter credit limit: ");
//            double creditlimit = creditlimitField.getFieldValue();
//
//            if (creditlimit <= 50000.0) {
//                System.out.println("Deposit limit has been set to " + creditlimit);
//            } else {
//                while (creditlimit > 50000.0) {
//                    System.out.println("Do not exceed 50,000.0 for the deposit limit");
//                    // Ask the user for a new input for the deposit limit
//                    // For example:
//                    // depositlimit = getUserInputForDepositLimit();
//                    creditlimit = 100000.0; // Set depositlimit to the maximum allowed value for now
//                }
//                System.out.println("Deposit limit has been set to " + creditlimit);
//            }
////            double fee= 10;
//            Field<Double, Double> feeField = new Field<Double,Double>("Processing Fee", Double.class, 100000.00,
//                    new Field.DoubleFieldValidator());
//            creditlimitField.setFieldValue("Enter Processing Fee: ");
//            double fee = feeField.getFieldValue();
//
//            if (creditlimit <= 50000.0) {
//                System.out.println("Deposit limit has been set to " + creditlimit);
//            } else {
//                while (creditlimit > 50000.0) {
//                    System.out.println("Do not exceed 50,000.0 for the deposit limit");
//                    // Ask the user for a new input for the deposit limit
//                    // For example:
//                    // depositlimit = getUserInputForDepositLimit();
//                    creditlimit = 100000.0; // Set depositlimit to the maximum allowed value for now
//                }
//                System.out.println("Deposit limit has been set to " + creditlimit);
//            }
//
//            // Continue with the rest of your code
//            //public Bank(int ID, String Name, String Passcode,double DL, double WL, double CL, double Fee)
//            Bank newBank = new Bank(bankid, bankname, passcode, depositlimit, withdrawlimit, creditlimit, fee);
//            addBank(newBank);
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
            Account account=LoggedBank.getBankAccount(bank,accountNumber);
            if(account!=null && account.getAccountNumber().equals(accountNumber))
            {
                return account;
            }

        }
        return null;
    }

    public static int bankSize()
    {
        return BANKS.size();
    }
}
