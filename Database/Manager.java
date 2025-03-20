package Database;

import java.sql.*;
import java.util.Objects;
import Accounts.*;

public class Manager
{
    public static void main(String[] args)
    {
        String URL= "jdbc:sqlite:Database/Bank.db";

        //Creating Statements
        //Statement stmt = conn.createStatement()

        try(Connection connection= DriverManager.getConnection(URL);)
        {
            if(connection!=null)
            {
                System.out.println("Connected to the database!");
//                createtable(connection,"Banks");
//                String add = "INSERT INTO Banks (BankName, BankPasscode) VALUES (?, ?)";
//                PreparedStatement preparedStatement = connection.prepareStatement(add);
////                preparedStatement.setInt(1, );
//                preparedStatement.setString(1, "World Bank");
//                preparedStatement.setString(2, "0987");
//                preparedStatement.executeUpdate();
//
//                printDatabase(URL);
                Transaction newTransaction= new Transaction("1234",Transaction.Transactions.Deposit,"Deposited 100000 to acccount 1234");
                insertTransaction(newTransaction, URL);
                try (Connection conn = DriverManager.getConnection(URL))
                {

                    String select = "SELECT * FROM transactions";
                    Statement commandLine = conn.createStatement();
                    ResultSet query = commandLine.executeQuery(select);
                    while (query.next()) {
                        int BankID = query.getInt("id");
                        String accNum = query.getString("account_number");
                        String desc = query.getString("transaction_type");


                        System.out.println("Account Number: " + accNum + "\nTransaction details: " + desc+"\n");
                    }
                }
                catch(SQLException e)
                {
                    e.printStackTrace();
                }




            }
        }
        catch (SQLException e)
        {
            System.err.println("Error: " + e.getMessage());
        }
    }
    //Modify into necessary format
    public static void addValues(String name, String passcode, String URL) {
        try {
            Connection connection = DriverManager.getConnection(URL);
            String add = "INSERT INTO Banks (BankName, BankPasscode) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(add);
            preparedStatement.setInt(1, 1);
            preparedStatement.setString(2, passcode);
            preparedStatement.executeUpdate();

            // Print updated database
            String select = "SELECT * FROM Banks";
            Statement commandLine = connection.createStatement();
            ResultSet query = commandLine.executeQuery(select);
            printDatabase(URL);

            connection.close();
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    //Modify into necessary format
    public static void removeValues(String name, String passcode, String URL) {
        try (Connection connection = DriverManager.getConnection(URL)) {
            // Base DELETE query
            String deleteSQL = "DELETE FROM Banks WHERE ";
            // Determine which condition to use
            if (name != null) {
                deleteSQL += "BankPasscode = ?";
            }
            else if (passcode != null) {
                deleteSQL += "BankPasscode = ?";
            }
            else {
                System.out.println("Not found!");
                return;
            }

            // Prepare statement
            try (PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL)) {
                preparedStatement.setString(1, Objects.requireNonNullElse(name, passcode));

                // Execute the delete operation
                int affectedRows = preparedStatement.executeUpdate();
                if (affectedRows > 0) {
                    System.out.println("Row deleted successfully.");
                } else {
                    System.out.println("Deleting "+name+" unsuccessful.\nBank not found\n");
                }
            }

        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    //Modify into necessary format
    /**Read and print out values in database
     * @param URL- Receive Database Connection URL
     */
    public static void printDatabase(String URL) throws SQLException {
        try (Connection conn = DriverManager.getConnection(URL))
        {

            String select = "SELECT * FROM transactions";
            Statement commandLine = conn.createStatement();
            ResultSet query = commandLine.executeQuery(select);
            while (query.next()) {
                String BankName = query.getString("bankname");
                int BankID = query.getInt("bankid");

                System.out.println("Bank Name: " + BankName + "\nBank ID: " + BankID+"\n");
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }

    public static void createtable(Connection connection, String tablename)
    {
        String createTable= "CREATE TABLE "+tablename+" ("
                + "BankID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "BankName VARCHAR(20),"
                + "BankPasscode VARCHAR(4)"
                + ");";
        try(Statement stmt = connection.createStatement())
        {
            stmt.executeUpdate(createTable);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public static void insertTransaction(Transaction transaction,String URL) {
        String sql = "INSERT INTO transactions (account_number, transaction_type, description) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, transaction.accountNumber);
            pstmt.setString(2, transaction.transactionType.name()); // Store enum as string
            pstmt.setString(3, transaction.description);

            pstmt.executeUpdate();
            System.out.println("Transaction inserted successfully.");

        } catch (SQLException e) {
            System.out.println("Error inserting transaction: " + e.getMessage());
        }
    }
}


