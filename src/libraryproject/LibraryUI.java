/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libraryproject;

import java.time.LocalDate;
import java.util.Scanner;

/**
 * Class for the Library user interface.
 * Displays different menus for the user to select different operations.
 * Uses the Library and User classes to execute the selected operations.
 * 
 * @author Kimmo T.
 */
public class LibraryUI {

    private final int INVALID = -1;
    private final int MENU = 0;
    private final int CLIENT = 1;
    private final int ADMIN = 2;
    
    private int startMode; // 0 menu, 1 client, 2 admin
    private int mode; // 0 menu, 1 client, 2 admin
    private boolean test = false;
    private Library library;
    private User user;
    
    /**
     * No default constructor, must use parameterized version.
     */
    private LibraryUI() {
        // Not to be used
    }
    
    /**
     * Creates UI for the Library according to the parameres.<br>
     * <br>"client" can loan, return, reserve, cancel reservation and search items.
     * <br>"admin"  can add and remove items and show different kinds of lists.
     * <br>"test"   is meant for testing only. It will pre-fill the library
     * with random items with random loans and reservations.<br>
     * 
     * @param args valid parameters "client", "admin", "test" or none.
     */
    public LibraryUI(String[] args) {
        // Parameter validity check
        if (args.length > 1) {
            System.out.println("Too many arguments given!");
            System.exit(0);
        } 
        // Handle valid cases of no parameters or 1 parameter
        if (args.length == 0) {
            this.startMode = MENU;
            this.user = userInfoQuery();
        } else {
            switch (args[0]) {
                case "test":
                    this.test = true;
                    this.startMode = MENU;
                    System.out.println(">>> IN TEST MODE");
                    this.user = userInfoQuery();
                    break;
                case "admin":
                    System.out.println(">>> ADMINISTRATOR MODE");
                    this.startMode = ADMIN;
                    this.user = new User("The","Librarian","1971-08-26",true);
                    break;
                case "client":
                    System.out.println(">>> CLIENT MODE");
                    this.startMode = CLIENT;
                    this.user = userInfoQuery();
                    break;
                default:
                    System.out.println("Bad argument given!");
                    System.exit(0);
                    break;
            }
        }
        
        if (test) {
            this.library = new Library(true); // Fill with test items
        } else {
            this.library = new Library();
        }
        
        this.mode = this.startMode;
        showMenu();
    }
    
    /**
     * Shows menu according to the value of mode class variable.
     * Mode value MENU shows menu for selecting client or admin operations.
     * Value ADMIN shows menu with administrative operations. Value CLIENT
     * show menu with client operations.
     */
    private void showMenu() {
        int selected = INVALID;
        
        // Welcome the user appropriately
        System.out.println(getUserWelcome()+" "+user.getFullName()+"!");
        
        do {
            switch(mode) {
                case MENU:
                    selected = showMainMenu();
                    if (selected > 0) {
                        // Admin can use both modes, Clien can use only one
                        if (selected == 1 || user.isAdmin()) {
                            mode = selected; // Assuming Modes match Menu items
                        } else {
                            System.out.println("No access rights!");
                        }
                        selected = INVALID; // to stop being executed
                    }
                    break;
                case CLIENT:
                    selected = showClientMenu();
                    if (selected == 0 && startMode == MENU) {
                        mode = MENU;
                        selected = INVALID;
                    }
                    break;
                case ADMIN:
                    selected = showAdminMenu();
                    if (selected == 0 && startMode == MENU) {
                        mode = MENU;
                        selected = INVALID;
                    } else {
                        // Admin operations ids start from 101
                        selected += 100;
                    }
                    break;
                default:
                    System.out.println("UNEXPECTED VALUE "+mode);
                    System.exit(0);
                    break;
            }
            if (selected > 0) {
                executeSelection(selected);
            }
        } while (selected != 0);
        
        System.out.println("Goodbye "+user.getFirstName()+"!");
    }
    
    /**
     * Executes Library operations according to given parameter.
     * 
     * @param selected 
     */
    private void executeSelection(int selected) {
        switch (selected) {
            case 1: // SHOW MY LOANS
                library.printLoanedItems(user);
                break;
            case 2: // LOAN ITEM
                library.loanItem(user);
                break;
            case 3: // RETURN LOANED ITEM
                library.returnItem(user);
                break;
            case 4: // SHOW MY RESERVATION
                library.printReservedItems(user);
                break;
            case 5: // RESERVE ITEM
                library.reserveItem(user);
                break;
            case 6: // CANCEL RESERVATION
                library.cancelReservation(user);
                break;
            case 7: // SEARCH WITH ISBN
                library.searchAndPrintWithIsbn();
                break;
            case 8: // SEARCH WITH TITLE
                library.searchAndPrintWithTitle();
                break;
            case 101: // ADD ITEM
                library.addNewItem();
                break;
            case 102: // REMOVE ITEM
                library.removeItem();
                break;
            case 103: // SHOW ALL LOANS
                library.printLoanedItems();
                break;
            case 104: // SHOW ALL OVERDUE LOANS
                library.printOverdueLoans();
                break;
            case 105: // SHOW ALL RESERVATIONS
                library.printReservedItems();
                break;
            case 106: // SHOW ALL ITEMS
                library.printAllItems();
                break;
            default:
                System.out.println("Unknown selection");
                break;
        }
    }
    
    /**
     * Displays the main menu for selecting client or administrator operations.
     * 
     * @return Number of selected menu item.
     */
    private int showMainMenu() {
        System.out.println("=================================================");
        System.out.print("1 - Client menu \t");
        System.out.println("2 - Administrator menu");
        System.out.println("0 - Exit");
        System.out.println("=================================================");
        
        return Utils.getSelection(2,true);
    }

    /**
     * Displays the client menu for selecting client operation.
     * 
     * @return Number of selected menu item.
     */
    private int showClientMenu() {
        System.out.println("================================================="+
                           "=================================================");
        System.out.print("1 - Show loans        \t");
        System.out.print("2 - New loan          \t");
        System.out.println("3 - Return loan");
        System.out.print("4 - Show reservations \t");
        System.out.print("5 - Reserve item      \t");
        System.out.println("6 - Cancel reservation");
        System.out.print("7 - Search by ISBN    \t");
        System.out.println("8 - Search by title");
        if (startMode == MENU) {
            System.out.println("0 - Back");
        } else {
            System.out.println("0 - Exit");
        }
        System.out.println("================================================="+
                           "=================================================");

        return Utils.getSelection(8,true);
    }
    
    /**
     * Displays the admin menu for selecting administrative operation.
     * 
     * @return Number of selected menu item.
     */
    private int showAdminMenu() {
        System.out.println("================================================="+
                           "=================================================");
        System.out.print("1 - Add new item      \t");
        System.out.print("2 - Remove item       \t");
        System.out.println("3 - Show all loans");
        System.out.print("4 - Show overdue loans\t");
        System.out.print("5 - Show reservations \t");
        System.out.println("6 - Show all items");
        if (startMode == MENU) {
            System.out.println("0 - Back");
        } else {
            System.out.println("0 - Exit");
        }
        System.out.println("================================================="+
                           "=================================================");

        return Utils.getSelection(6,true);
    }
    
    /**
     * Checks if the user has birthday and modifies welcome message.
     * 
     * @return Welcome text with or without birthday wishes.
     */
    private String getUserWelcome() {
        LocalDate birthday = user.getBirthday();
        LocalDate today = LocalDate.now();
        if (birthday.getMonth() == today.getMonth()) {
            if (birthday.getDayOfMonth() == today.getDayOfMonth()) {
                return "Welcome and Happy Birthday";
            }
        }
        return "Welcome";
    }
    
    /**
     * Queries user information, creates and returns new User instance.
     * Information required are firstname, lastname and birthday. Also
     * asks for administrator password to gain administrator rights.
     * 
     * TODO: Sanity checks for the values are missing
     * 
     * @return New user instance.
     */
    private User userInfoQuery() {
        String firstName;
        String lastName;
        String birthday;
        String password;
        boolean adminRights;
        
        // TODO: Input sanity checks are missing.
        
        Scanner scan = new Scanner(System.in);
        System.out.println(">>> WELCOME TO THE LIBRARY");
        System.out.print("Give your first name: ");
        firstName = scan.nextLine();
        System.out.print("Give your last name: ");
        lastName = scan.nextLine();
        System.out.print("Give your birthday (yyyy-mm-dd): ");
        birthday = scan.nextLine();
        System.out.print("To activate admin rights enter password: ");
        password = scan.nextLine();
        adminRights = password.equals("password");
        System.out.println();
        System.out.println();
        return new User(firstName, lastName, birthday, adminRights);
    }
}
