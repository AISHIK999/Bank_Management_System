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
    private static HashMap<Integer, Attributes> accountList = new HashMap<Integer, Attributes>();

    public static void main(String[] args) {
        int choice = 0;
        while (choice != 6) {
            banner();
            System.out.println("WELCOME TO THE BANK MANAGEMENT SYSTEM");
            banner();
            System.out.println("Add account [1]");
            System.out.println("Read account [2]");
            System.out.println("Update account [3]");
            System.out.println("Delete account [4]");
            System.out.println("Print database [5]");
            System.out.println("Exit [6]");
            System.out.println();
            System.out.println("Enter your choice: ");
            choice = sc.nextInt();

            runAction(choice);
        }
    }

    // Break line
    public static void banner() {
        System.out.println("==================================================");
        System.out.println();
    }

    // Run action
    public static void runAction(int choice) {
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

            System.out.println("View entire database [A]");
            System.out.println("View a specific account [B]");
            System.out.println("Enter your choice: ");
            choice = sc.next().charAt(0);
            choice = Character.toUpperCase(choice);

            switch (choice) {
                case 'A':
                    for (Attributes attributes : accountList.values()) {
                        banner();
                        System.out.println(attributes);
                        banner();
                    }
                    break;
                case 'B':
                    System.out.print("Enter the account number of the account you want to view: ");
                    int id = sc.nextInt();
                    boolean present = false;
                    for (Integer key : accountList.keySet()) {
                        if (id == key) {
                            banner();
                            System.out.println(accountList.get(id));
                            present = true;
                            break;
                        } else {
                            present = false;
                        }
                    }
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

            System.out.println("Remove entire database [A]: ");
            System.out.println("Remove a specific account [B]: ");
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
                    int id = sc.nextInt();

                    for (Integer key : accountList.keySet()) {
                        if (key == id) {
                            accountList.remove(id);
                            banner();
                            System.out.println("Database for account number " + id + " has been removed.");
                            isAccount = true;
                            break;
                        } else {
                            isAccount = false;
                        }
                    }
                    if (isAccount == false) {
                        banner();
                        System.out.println("Account number " + id + " not found. Please check the account number.");
                    }
                    break;
                default:
                    System.out.println("Please select a valid choice");
            }
        }
    }

    // Update an account
    public static void UpdateAccount() {
        char choice = '\0';
        int id = 0;
        String name = "";
        String aadhaar = "";
        String mobile = "";
        String balance = "";
        boolean isAcc = false;

        // Return null for empty database
        if (accountList.size() == 0) {
            System.out.println("No account!");

        } else if (accountList.size() != 0) {
            banner();
            System.out.println("UPDATE ACCOUNT");
            banner();
            System.out.print("Enter the account number of the bank account to update: ");
            id = sc.nextInt();
        }
        for (Integer key : accountList.keySet()) {
            if (id == key) {
                System.out.println("Update name [A]: ");
                System.out.println("Update Aadhaar number [B]: ");
                System.out.println("Update mobile number [C]: ");
                System.out.println("Update account balance [D]: ");
                System.out.println("Enter your choice:");
                choice = sc.next().charAt(0);
                choice = Character.toUpperCase(choice);

                switch (choice) {
                    case 'A':
                        System.out.print("Enter new name of the account holder: ");
                        name = sc.nextLine();
                        accountList.get(id).setAccountName(name);
                        banner();
                        System.out.println("Successfully updated.");
                        break;
                    case 'B':
                        System.out.print("Enter new Aadhaar number of the account holder: ");
                        aadhaar = sc.nextLine();
                        accountList.get(id).setAadhaarNumber(aadhaar);
                        banner();
                        System.out.println("Successfully updated.");
                        break;
                    case 'C':
                        System.out.print("Enter new mobile number of the account holder: ");
                        mobile = sc.nextLine();
                        accountList.get(id).setMobileNumber(mobile);
                        banner();
                        System.out.println("Successfully updated.");
                        break;
                    case 'D':
                        System.out.println("Enter new account balance: ");
                        balance = sc.nextLine();
                        accountList.get(id).setAccountBalance(balance);
                        banner();

                    default:
                        System.out.println("Please select a valid choice");
                        break;
                }
            } else {
                banner();
                System.out.println("Wrong ID Number! Try another one!");
            }
        }
    }
    
    public static void PrintDB() {
        if (accountList.size() == 0) {
            System.out.println("Database is empty. Please add an account.");
        } else {
            try {
                FileWriter fw = new FileWriter("database.txt");
                Date date = new Date();
                SimpleDateFormat currentDate = new SimpleDateFormat("dd(E)/MM(MMM)/yyyy");
                SimpleDateFormat currentTime = new SimpleDateFormat("hh:mm:ss a");

                fw.write("******************************\n");
                fw.write("*    DATABASE INFORMATION    *\n");
                fw.write("******************************");
                fw.write("\n\n");
                fw.write("AS OF DATE: " + currentDate.format(date) + "\n");
                fw.write("AS OF TIME: " + currentTime.format(date) + "\n");
                fw.write("\n\n");

                int no = 1;
                for (Attributes attributes : accountList.values()) {
                    fw.write("INDEX: " + no + "\n" + attributes + "\n\n");
                    no++;
                    System.out.println();
                }
                fw.close();

                System.out.println("Check database.txt for more information");
                banner();
            }
            // Error handling
            catch (IOException e) {
                System.out.println(e);
            }
            try {
                File file = new File("database.txt");

                Scanner sc = new Scanner(file);
                System.out.println(sc.nextLine());
            } catch (FileNotFoundException e) {
                System.out.println(e);
            }

        }
    }
}