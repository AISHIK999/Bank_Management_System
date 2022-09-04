// By AISHIK999

import Attributes.Attributes;

import java.util.Scanner;
import java.util.HashMap;
import java.lang.Character;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.io.File;
import java.io.FileNotFoundException;

public class BankManagementSystem {
    private static Scanner sc = new Scanner(System.in);
    /*We will use hashmap to use the concept of primary key of a database system for our basic program
    * We are going to use account number as our primary key*/
    private static HashMap<Integer, Attributes> accountList = new HashMap<Integer, Attributes>();

    public static void main(String[] args) {
        int choice = 0;
        while (choice != 6) {
            banner();
            System.out.println("WELCOME TO THE BANK MANAGEMENT SYSTEM");
            banner();
            System.out.println("Add account [1]");          // Add a new account
            System.out.println("Read account [2]");         // Read an existing account
            System.out.println("Update account [3]");       // Update an existing account
            System.out.println("Delete account [4]");       // Delete an existing account
            System.out.println("Print database [5]");       // Print the banking database
            System.out.println("Exit [6]");                 // Exit the program
            System.out.println();
            System.out.println("Enter your choice: ");
            choice = sc.nextInt();
            startApp(choice);
        }
    }

    // Our banner design
    public static void banner() {
        System.out.println();
        for(int i = 0; i <= 50; i++) {
            System.out.print("=");
        }
        System.out.println();
    }

    // Start the application
    // This method will call the respective methods according to the user's choice
    public static void startApp(int choice) {
        switch (choice) {
            case 1:
                AppendAccount();
                break;
            case 2:
                ViewAccount();
                break;
            case 3:
                UpdateAccount();
                break;
            case 4:
                RemoveAccount();
                break;
            case 5:
                PrintDB();
                break;
            case 6:
                System.out.println("System stopped.");
                break;
            default:
                System.out.println("Please select a valid choice");
        }
    }
    
    //Add new account
    public static void AppendAccount() {
        int accountNumber = 0;      // We will use this as a primary key
        String name = " ";
        String aadhaar = " ";
        String mobile = " ";
        String balance = " ";
        boolean primaryKey = false;

        banner();
        System.out.println("CREATE ACCOUNT");
        banner();

        // Add account number
        System.out.print("Provide Account Number: ");
        accountNumber = sc.nextInt();

        // Never add accounts with duplicate account number
        for (Integer key : accountList.keySet()) {
            if (accountNumber == key) {
                banner();
                System.out.println("Duplicate account. Please check account number.");
                primaryKey = true;
                break;
            } else
                primaryKey = false;
        }

        if (primaryKey == false) {
            sc.nextLine();

            // Add account name
            System.out.print("Enter the name of the account holder: ");
            name = sc.nextLine();

            // Add aadhaar number
            System.out.print("Enter Aadhaar number: ");
            aadhaar = sc.nextLine();

            // Add mobile number
            System.out.print("Enter mobile number: ");
            mobile = sc.nextLine();

            // Add account balance
            System.out.print("Enter account balance: ");
            balance = sc.nextLine();

            // Finalize account
            banner();
            System.out.print("Added the bank account " + accountNumber + " to our database.");
            Attributes attributes = new Attributes(accountNumber, name, aadhaar, mobile, balance);
            accountList.put(accountNumber, attributes);
        }
    }

    // View Account(s)
    public static void ViewAccount() {
        char choice = '\0';
        // Return null for empty database
        if (accountList.size() == 0) {
            System.out.println("Database is empty. Please add an account.");
        } else {
            banner();
            System.out.println("VIEW ACCOUNT");
            banner();

            System.out.println("View entire database [A]");         // View entire banking database
            System.out.println("View a specific account [B]");      // View a specific account's database
            System.out.println("Enter your choice: ");
            choice = sc.next().charAt(0);
            choice = Character.toUpperCase(choice);                 // a or A. All are accepted

            switch (choice) {
                case 'A':
                    for (Attributes attributes : accountList.values()) {
                        banner();
                        System.out.println(attributes);
                        banner();
                    }
                    break;
                case 'B':
                    // Since account numbers are unique, we can use it as a primary key and call each instance by their account number
                    System.out.print("Enter the account number of the account you want to view: ");
                    int primaryKey = sc.nextInt();
                    boolean present = false;
                    for (Integer key : accountList.keySet()) {
                        if (primaryKey == key) {
                            banner();
                            System.out.println(accountList.get(primaryKey));
                            present = true;
                            break;
                        } else {
                            present = false;
                        }
                    }
                    // Return error if the account number is not found within the database
                    if (present == false) {
                        banner();
                        System.out.println("Account not found. Please check the account number.");
                    }
                    break;
                default:
                    banner();
                    System.out.println("Please select a valid choice");
            }
        }
    }

    // Nuke an account
    public static void RemoveAccount() {
        char choice = '\0';
        // Return null for empty database
        if (accountList.size() == 0) {
            System.out.println("No account!");
        } else {
            banner();
            System.out.println("REMOVE ACCOUNT");
            banner();

            System.out.println("Remove entire database [A]: ");         // Nuke everything from the DB
            System.out.println("Remove a specific account [B]: ");      // Nuke a specific account
            System.out.println("Enter your choice: ");
            choice = sc.next().charAt(0);
            choice = Character.toUpperCase(choice);

            switch (choice) {
                case 'A':
                    accountList.clear();
                    banner();
                    System.out.println("Removed all entries from database.");
                    break;
                case 'B':
                    boolean isAccount = false;

                    System.out.print("Enter remove account id: ");
                    int primaryKey = sc.nextInt();

                    for (Integer key : accountList.keySet()) {
                        if (key == primaryKey) {
                            accountList.remove(primaryKey);
                            banner();
                            System.out.println("Database for account number " + primaryKey + " has been removed.");
                            isAccount = true;
                            break;
                        } else {
                            isAccount = false;
                        }
                    }
                    if (isAccount == false) {
                        banner();
                        System.out.println("Account number " + primaryKey + " not found. Please check the account number.");
                    }
                    break;
                default:
                    System.out.println("Please select a valid choice");
            }
        }
    }

    // Update an account
    public static void UpdateAccount() {
        Scanner sc = new Scanner(System.in);
        char choice = '\0';
        int primaryKey = 0;
        String name = " ";
        String aadhaar = " ";
        String mobile = " ";
        String balance = " ";
        boolean isAcc = false;

        // Return null for empty database
        if (accountList.size() == 0) {
            System.out.println("No account!");
        } else {
            banner();
            System.out.println("UPDATE ACCOUNT");
            banner();
            System.out.print("Enter the account number of the bank account to update: ");
            primaryKey = Integer.parseInt(sc.nextLine());
        }
        for (Integer key : accountList.keySet()) {
            if (primaryKey == key) {
                System.out.println("Update name [A]: ");
                System.out.println("Update Aadhaar number [B]: ");
                System.out.println("Update mobile number [C]: ");
                System.out.println("Update account balance [D]: ");
                System.out.println("Enter your choice:");
                choice = sc.next().charAt(0);
                choice = Character.toUpperCase(choice);
                sc.nextLine();      // Clear buffer. Credits to [https://www.geeksforgeeks.org/why-is-scanner-skipping-nextline-after-use-of-other-next-functions/]

                // Since all of the attributes are strings, we can use the same code for all of them
                switch (choice) {
                    case 'A':
                        System.out.println("Enter the name of the account holder: ");
                        name = sc.nextLine();
                        accountList.get(primaryKey).setAccountName(name);
                        banner();
                        System.out.println("Successfully updated.");
                        break;
                    case 'B':
                        System.out.print("Enter new Aadhaar number of the account holder: ");
                        aadhaar = sc.nextLine();
                        accountList.get(primaryKey).setAadhaarNumber(aadhaar);
                        banner();
                        System.out.println("Successfully updated.");
                        break;
                    case 'C':
                        System.out.print("Enter new mobile number of the account holder: ");
                        mobile = sc.nextLine();
                        accountList.get(primaryKey).setMobileNumber(mobile);
                        banner();
                        System.out.println("Successfully updated.");
                        break;
                    case 'D':
                        System.out.print("Enter new account balance: ");
                        balance = sc.nextLine();
                        accountList.get(primaryKey).setAccountBalance(balance);
                        banner();
                    default:
                        System.out.println("Please select a valid choice");
                        break;
                }
            }
            else {
                banner();
                System.out.println("Wrong ID Number! Try another one!");
            }
        }
    }

    // Print out account database in a text file
    public static void PrintDB() {
        if (accountList.size() == 0) {
            System.out.println("Database is empty. Please add an account.");
        } else {
            try {
                // Credit to [https://www.geeksforgeeks.org/reading-writing-text-files-java/]
                FileWriter fw = new FileWriter("database.csv");
                Date date = new Date();
                // Credit to [https://www.geeksforgeeks.org/java-date-tostring-method/]
                // Credit to [https://www.w3schools.com/java/java_date.asp]
                SimpleDateFormat currentDate = new SimpleDateFormat("dd(E)/MM(MMM)/yyyy");
                SimpleDateFormat currentTime = new SimpleDateFormat("hh:mm:ss a");

                /*
                * The variables were written in a hurry :P
                * They were created in the first place as file write does not accept String imput directly
                * So I had to format to Str
                * Credits [https://stackoverflow.com/questions/19509271/no-suitable-method-found-for-writestring]
                * */
                String a = "DATE";
                String b = "TIME";
                String c = "INDEX";
                String d = "ACCOUNT NUMBER";
                String e = "NAME";
                String f = "AADHAAR NUMBER";
                String g = "MOBILE NUMBER";
                String h = "ACCOUNT BALANCE";
                // All the values written will be separated by a comma from here on
                // The output file has been changed from .txt to .csv for better readability
                fw.write(String.format("%s,%s,%s,%s,%s,%s,%s,%s\n", a, b, c, d, e, f, g, h));

                int ind = 1;
                for (Attributes attributes : accountList.values()) {
                    fw.write(currentDate.format(date) +"," + currentTime.format(date) +"," + ind + "," + attributes + "\n");
                    ind++;
                }
                fw.close();

                System.out.println("Check database.csv for more information");
                banner();
            }
            // Error handling
            catch (IOException e) {
                System.out.println(e);
            }
            try {
                File file = new File("database.csv");

                Scanner sc = new Scanner(file);
                System.out.println(sc.nextLine());
            } catch (FileNotFoundException e) {
                System.out.println(e);
            }

        }
    }
}