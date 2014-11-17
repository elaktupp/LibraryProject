/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libraryproject;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

/**
 * Class for the library. This class implements operations and items needed
 * to use the library.
 * 
 * @author Kimmo T.
 */
public class Library {
    
    private final ArrayList items;
    
    private final String[] itemsToAdd = {
        "Undefined", // <- because of getSelectionFromList
        "Book",
        "Magazine",
        "Audio/Music",
        "Video/Movie"
    };
    
    /**
     * Constructor initialisez the items list that contains
     * every item in this library.
     */
    public Library() {
        this.items = new ArrayList();
    }
    
    /**
     * Prints out full information of all items in the library.
     */
    public void printAllItems() {
        Iterator<LibraryItem> it = items.iterator();
        System.out.println(">>> ALL ITEMS:");
        if (it.hasNext()) {
            for ( ; it.hasNext(); ) {
                it.next().printInformation();
            }
        } else {
            System.out.println("The library is empty.");
        }
    }
    
    /**
     * Prints out all loaned items in the library.
     * 
     * If no match is found an appropriate message is shown.
     */
    public int printLoanedItems() {
        int loans = 0;
        Iterator<LibraryItem> it = items.iterator();
        System.out.println(">>> ALL LOANS:");
        for ( ; it.hasNext(); ) {
            LibraryItem item = it.next();
            if (item.getLoanDate() != null) {
                loans++; // Intentionally make this start from 1
                System.out.print(loans+") ");
                printOneLineLoanInfo(item);
            }
        }
        if (loans == 0) {
            System.out.println("No loans found.");
        }
        return loans;
    }
    
    /**
     * Prints out all items in the library loaned by the given user.
     * 
     * If no match is found an appropriate message is shown.
     * 
     * @param user the user whose loans we want to see
     */
    public int printLoanedItems(User user) {
        int loans = 0;
        Iterator<LibraryItem> it = items.iterator();
        System.out.println(">>> LOANS:");
        for ( ; it.hasNext(); ) {
            LibraryItem item = it.next();
            if (item.getLoanDate() != null) {
                if (item.getLoanedTo().equals(user.getFullName())) {
                    loans++; // Intentionally make this start from 1
                    System.out.print(loans+") ");
                    printOneLineLoanInfo(item);
                }
            }
        }
        if (loans == 0) {
            System.out.println("No loans found.");
        }
        return loans;
    }
    
    /**
     * Queries for the ISBN of the item to be loaned.
     * If the item is found and it is not reserved and age check is passed,
     * mark it as loaned to the given user and the loan date is set to today.
     * If the found item was reserved to the user, the pending reservation
     * is also removed automatically.
     * If the item is not found or other checks failed an appropriate message
     * is shown.
     * 
     * @param user the user who wants to reserve this item
     */
    public void loanItem(User user) {
        boolean itemAvailable = true;
        System.out.println(">>> LOAN ITEM:");
        LibraryItem item = searchWithIsbn();
        if (item == null) {
            System.out.println("Sorry, nothing found on that ISBN.");
        } else {
            if (item.isAgeLimitPassed(user)) {
                if (item.getReservedDate() != null &&
                    item.getReservedTo().equals(user.getFullName())) {
                    System.out.println("You had pending reservation to this title.");
                    item.setReservationDate(null);
                    item.setReservedTo(null);
                } else if (item.getReservedDate() != null) {
                    System.out.println("Sorry, this title has been reserved.");
                    itemAvailable = false;
                }
            } else {
                System.out.println("Sorry, this title is only for "+
                                   item.getAgeLimitInYears()+
                                   " years or older.");
                itemAvailable = false;            
            }
            if (itemAvailable) {
                item.setLoanDate(LocalDate.now());
                item.setLoanedTo(user.getFullName());
                System.out.println(item.getTitle()+" loaned to "+
                                   item.getLoanedTo()+" until "+
                                   item.getReturnDate()+". - Enjoy!");
            }
        }
    }
    
    /**
     * Queries for the ISBN of the item to be returned.
     * If the item is found the loaned status is cleared.
     * If the item is not found an appropriate message is shown.
     * 
     * @param user the user who loans this item
     */
    public void returnItem(User user) {
        System.out.println(">>> RETURN ITEM:");
        LibraryItem item = searchWithIsbn();
        if (item == null) {
            System.out.println("Sorry, nothing found on that ISBN.");
        } else {
            if (item.getLoanedTo().equals(user.getFullName())) {
                System.out.println(item.getTitle()+" returned. - Thank you!");
            } else {
                System.out.println(item.getTitle()+" returned on behalf of "+
                                   item.getLoanedTo()+". - Thank you!");
            }
            item.setLoanDate(null);
            item.setLoanedTo(null);
        }
    }
    
    /**
     * Prints out all reserved items in the library.
     * 
     * If no match is found an appropriate message is shown.
     */
    public int printReservedItems() {
        int reservations = 0;
        Iterator<LibraryItem> it = items.iterator();
        System.out.println(">>> ALL RESERVATIONS:");
        for ( ; it.hasNext(); ) {
            LibraryItem item = it.next();
            if (item.getReservedDate() != null) {
                reservations++; // Intentionally make this start from 1
                System.out.print(reservations+") ");
                printOneLineReservationInfo(item);
            }
        }
        if (reservations == 0) {
            System.out.println("No reservations found.");
        }
        return reservations;
    }
    
    /**
     * Prints out all items in the library reserved by the given user.
     * 
     * If no match is found an appropriate message is shown.
     * 
     * @param user the user whose reservations we want to see
     */
    public void printReservedItems(User user) {
        int reservations = 0;
        Iterator<LibraryItem> it = items.iterator();
        System.out.println(">>> RESERVATIONS:");
        for ( ; it.hasNext(); ) {
            LibraryItem item = it.next();
            if (item.getReservedDate() != null) {
                if (item.getReservedTo().equals(user.getFullName())) {
                    reservations++; // Intentionally make this start from 1
                    System.out.print(reservations+") ");
                    printOneLineReservationInfo(item);
                }
            }
        }
        if (reservations == 0) {
            System.out.println("No reservations found.");
        }
    }
    
    /**
     * Queries for the ISBN of the item to be reserved.
     * If the item is found and it is not reserved and age check is passed,
     * mark it as reserved to the given user and the reservation date is
     * set to today.
     * If the item is not found or other checks failed an appropriate message
     * is shown.
     * 
     * @param user the user who wants to reserve this item
     */
    public void reserveItem(User user) {
        System.out.println(">>> RESERVE ITEM:");
        LibraryItem item = searchWithIsbn();
        if (item == null) {
            System.out.println("Sorry, nothing found on that ISBN.");
        } else {
            if (item.isAgeLimitPassed(user)) {
                int status = itemReservationStatus(item,user);
                switch (status) {
                    case Library.AVAILABLE:
                        if (itemLoanStatus(item,user) == Library.USERS) {
                            System.out.println("Sorry, it is not allowed to "+
                                    "reserve item you have already in loan.");
                        } else {
                            item.setReservationDate(LocalDate.now());
                            item.setReservedTo(user.getFullName());
                            System.out.println(item.getTitle()+
                                            " is now reserved. - Thank you!");
                        }
                        break;
                    case Library.UNAVAILABLE:
                        System.out.println("Sorry, that item is not currently "+
                                           "available for you.");
                        break;
                    case Library.USERS:
                        System.out.println("You have already reserved this "+
                                           "item.");
                        break;
                    default:
                        System.out.println("ERROR: Unexpected status value");
                        System.exit(0);
                }
            } else {
                System.out.println("Sorry, this title is only for "+
                   item.getAgeLimitInYears()+
                   " years or older.");
            }
        }
    }
    
    /**
     * Queries for the ISBN of the item which reservation is to be canceled.
     * If the item is found the reservation in cleared.
     * If the item is not found an appropriate message is shown.
     * 
     * @param user the user who wants to reserve this item
     */
    public void cancelReservation(User user) {
        System.out.println(">>> CANCEL RESERVATION:");
        LibraryItem item = searchWithIsbn();
        if (item == null) {
            System.out.println("Sorry, nothing found on that ISBN.");
        } else {
            if (isItemAvailable(item,user)) {
                item.setReservationDate(null);
                item.setReservedTo(null);
                System.out.println(item.getTitle()+" reservation is cancelled. - Thank you!");
            } else {
                System.out.println("Sorry, that item is not currently available for you.");
            }            
        }
    }
    
    /**
     * Queries for the ISBN of the item to be searched.
     * 
     * Here is assumed that the ISBN is unique id for the item.
     * The search stops to the first <b>exact</b> match.
     * 
     * If the item is found a reference to the item is returned.
     * If the item is not found an appropriate message is shown
     * and null returned.
     * 
     * @return item reference to the found library item, null if nothing found
     */
    public LibraryItem searchWithIsbn() {
        Scanner scan = new Scanner(System.in);
        LibraryItem item = null;
        Iterator<LibraryItem> it = items.iterator();
        System.out.print("Give ISBN: ");
        String isbn = scan.nextLine();
        for ( ; it.hasNext(); ) {
            item = it.next();
            if (item.getIsbn().equals(isbn)) {
                break; // Found first occurence
            } else {
                item = null; // return value needs to be null if not found
            }
        }
        return item;
    }
    
    /**
     * Queries for the ISBN of the item to be searched.
     * 
     * Here is assumed that the ISBN is unique id for the item.
     * The search stops to the first <b>exact</b> match.
     * 
     * If the item is found its info is printed out.
     * If the item is not found an appropriate message is shown.
     */
    public void searchAndPrintWithIsbn() {
        boolean notFound = true;
        Scanner scan = new Scanner(System.in);
        System.out.println(">>> SEARCH ITEM WITH ISBN:");
        LibraryItem item;
        Iterator<LibraryItem> it = items.iterator();
        System.out.print("Give ISBN: ");
        String isbn = scan.nextLine();
        for ( ; it.hasNext(); ) {
            item = it.next();
            if (item.getIsbn().equals(isbn)) {
                item.printInformation();
                notFound = false;
                break;
            }
        }
        if (notFound) {
            System.out.println("Sorry, there is no item with that ISBN.");
        }
    }
    
    /**
     * Queries for the title to be searched.
     * Title search queries the title (or part of it) and executes a case
     * insensitive search of items with title containing the search string.
     * If a match is found the item information printed out.
     * If no match is found an appropriate message is shown.
     */
    public void searchAndPrintWithTitle() {
        boolean notFound = true;
        Scanner scan = new Scanner(System.in);
        System.out.println(">>> SEARCH ITEM WITH TITLE:");
        LibraryItem item = null;
        Iterator<LibraryItem> it = items.iterator();
        System.out.print("Give title: ");
        String title = scan.nextLine();
        String itemTitle;
        String searchTitle;
        for ( ; it.hasNext(); ) {
            item = it.next();
            itemTitle = item.getTitle().toUpperCase();
            searchTitle = title.toUpperCase().toString();
            if (itemTitle.contains(searchTitle.subSequence(0, searchTitle.length()))) {
                item.printInformation();
                notFound = false;                
            }
        }
        if (notFound) {
            System.out.println("Sorry, there is no item with that title.");
        }
    }
    
    /**
     * Creates a new item and adds in the library items list.
     * First queries the item type to be added and then asks to fill in the
     * needed information.
     */
    public void addNewItem() {
        LibraryItem item = null;
        int selected = Utils.getSelectionFromList(itemsToAdd, ">>> ADDING NEW ITEM");
        
        switch (selected) {
            case 1:
                item = new Book();
                break;
            case 2:
                item = new Magazine();
                break;
            case 3:
                item = new Audio();
                break;
            case 4:
                item = new Video();
                break;
            default:
                break;
        }
        
        if (item != null) {
            item.createItem();
            items.add(item);
        }
    }
    
    /**
     * Queries for the ISBN of the item to be removed.
     * If a match is found the item is permanently removed from the library
     * items list.
     * If no match is found an appropriate message is shown.
     */
    public void removeItem() {
        System.out.println(">>> REMOVE ITEM FROM LIBRARY:");
        LibraryItem item = searchWithIsbn();
        if (item == null) {
            System.out.println("Sorry, nothing found on that ISBN.");
        } else {
            String removed = item.getTitle()+" ["+item.getIsbn()+"]";
            if (items.remove(item)) {
                System.out.println(removed + " was succesfully removed.");
            }
        }
    }
    
    /**
     * Prints out all loaned items in the library that are overdue.
     * 
     * If no match is found an appropriate message is shown.
     */
    public void printOverdueLoans() {
        boolean notFound = true;
        LibraryItem item = null;
        Iterator<LibraryItem> it = items.iterator();
        for ( ; it.hasNext(); ) {
            item = it.next();
            if (item.getLoanDate() != null && daysToDueDate(item) < 0) {
                printOneLineLoanInfo(item);
                notFound = false;
            }
        }
        if (notFound) {
            System.out.println("No overdue loans found.");
        }
    }
    
    /*
     * HELPER METHODS ========================================
     */
    
    /**
     * Item loan information as one liner.
     * 
     * @param item which informatio is to be shown
     */
    private void printOneLineLoanInfo(LibraryItem item) {
        String status;
        int daysLeft = daysToDueDate(item);
        
        if (daysLeft == 0) {
            status = "DUE TODAY";
        } else if (daysLeft < 0) {
            status = Math.abs(daysLeft) + " days OVERDUE";
        } else {
            status = "due in " + daysLeft + " days";

        }
        System.out.println(item.getTitle()+" ["+
                           item.getIsbn()+"] is "+
                           status+". Loaned to "+
                           item.getLoanedTo()+".");
    }
    
    /**
     * Item reservation information as one liner.
     * 
     * @param item which informatio is to be shown
     */
    private void printOneLineReservationInfo(LibraryItem item) {
        System.out.println(item.getTitle()+" ["+
                           item.getIsbn()+"] is reserved to "+
                           item.getReservedTo()+".");
    }
    
    
    /**
     * Counts days from today to the return date of the item.
     * 
     * @param item which return date is used in calculation
     * @return days from the due date, is negative if past the due date.
     */
    private int daysToDueDate(LibraryItem item) {
        LocalDate dueDate = item.getReturnDate();
        LocalDate today = LocalDate.now();

        int yearsLeft = Period.between(today, dueDate).getYears();
        int monthsLeft = Period.between(today, dueDate).getMonths();
        int daysLeft = Period.between(today, dueDate).getDays();
        
        // TODO: daysLeft is not calculated properly, just approximately.
        
        if (yearsLeft != 0) {
            daysLeft += 12 * 30 * monthsLeft; // yearsLeft can be negative
        }
        
        if (monthsLeft != 0) {
            daysLeft += 30 * monthsLeft; // monthsLeft can be negative
        }
        
        return daysLeft;
    }
    
    // TODO: These helper methods could probably be used more efficiently.
    // Perhaps the age limit check could be incorporated to these methods?
    
    private final static int AVAILABLE = 0;
    private final static int UNAVAILABLE = 1;
    private final static int USERS = 2;
    
    /**
     * Checks if the user can access the item or if already has it.
     * 
     * If item is not in loan, it is AVAILABLE for anybody.
     * If item is loaned by someone else than the user, it is UNAVAILABLE.
     * if item is already loaned by the user, it is USERS.
     * 
     * @param item which relation to the user is checked
     * @param user whose relation to the item is checked
     * @return status of the item user relation
     */
    private int itemLoanStatus(LibraryItem item, User user) {
        int result = AVAILABLE;
       
        if (item.getLoanDate() != null) {
            result = UNAVAILABLE;
            if (item.getLoanedTo().equals(user.getFullName())) {
                result = USERS;
            }
        }
              
        return result;
    }
    
    /**
     * Checks if the user can access the item or if already has it.
     * 
     * If item is not reserved, it is AVAILABLE for anybody.
     * If item is reserved by someone else than the user, it is UNAVAILABLE.
     * if item is already reserved by the user, it is USERS.
     * 
     * @param item which relation to the user is checked
     * @param user whose relation to the item is checked
     * @return status of the item user relation
     */
    private int itemReservationStatus(LibraryItem item, User user) {
        int result = AVAILABLE;
       
        if (item.getReservedDate() != null) {
            result = UNAVAILABLE;
            if (item.getReservedTo().equals(user.getFullName())) {
                result = USERS;
            }
        }
              
        return result;
    }
    
    /**
     * Checks if the item is accessable for the user.
     * 
     * @param item which relation to the user is checked
     * @param user whose relation to the item is checked
     * @return true if user can access the item, false if not
     */
    private boolean isItemAvailable(LibraryItem item, User user) {
        boolean loaned = (item.getLoanDate() == null)?(false):(true);
        boolean reserved = (item.getReservedDate() == null)?(false):(true);
        boolean available = (loaned||reserved)?(false):(true);
        
        if (loaned) {
            // If the item is loaned by the user, it is available for the user
            available = item.getLoanedTo().equals(user.getFullName());
        } else if (reserved) {
            // If the item is reserved by the user, it is available for the user
            available = item.getReservedTo().equals(user.getFullName());
        }
              
        return available;
    }
    
    /*
     * FOR TESTING PURPOSES ========================================
     */
    
    /**
     * Constructor for testing purposes. Creates random items with
     * random information and adds them to the library items list.
     * 
     * <br><br><b>For testing purposes only.</b><br>
     * 
     * @param test parameter value is not used
     */
    public Library(boolean test) {
        this(); // call default constructor first
        LibraryItem item = null;
        Random r = new Random();
        for (int i = 0; i < 150; i++) {
            switch (itemsToAdd[r.nextInt(4)+1]) {
                case "Book":
                    item = new Book();
                    break;
                case "Magazine":
                    item = new Magazine();
                    break;
                case "Audio/Music":
                    item = new Audio();
                    break;
                case "Video/Movie":
                    item = new Video();
                    break;
                default:
                    System.out.println("TEST ERROR: Bad item");
                    System.exit(0);
            }
            if (item != null) {
                item.testItemCreation();
                // Add some items to loaned or reserved
                int mod = r.nextInt(10);
                switch (mod) {
                    case 0:
                        item.setLoanedTo("Ted Tester");
                        item.setLoanDate(LocalDate.parse(TestHelp.randomDateAroundToday()));
                        break;
                    case 1:
                        item.setReservedTo("Ted Tester");
                        item.setReservationDate(LocalDate.parse(TestHelp.randomDateAroundToday()));
                        break;
                    case 2:
                        item.setLoanedTo("Verity Verifier");
                        item.setLoanDate(LocalDate.parse(TestHelp.randomDateAroundToday()));
                        break;
                    case 3:
                        item.setReservedTo("Verity Verifier");
                        item.setReservationDate(LocalDate.parse(TestHelp.randomDateAroundToday()));
                        break;
                    default:
                        // Do nothing to the item
                }
                items.add(item);
            } else {
                System.out.println("TEST ERROR: Null item");
                System.exit(0);
            }
        }
    }
    
    /**
     * Adds to the library an item instance with randomly generate information.
     * 
     * <br><br><b>For testing purposes only.</b><br>
     */
    public void testAddNewItem() {
        LibraryItem item = null;
        int selected = Utils.getSelectionFromList(itemsToAdd, "ADDING NEW TEST ITEM");
        
        switch (selected) {
            case 1:
                item = new Book();
                break;
            case 2:
                item = new Magazine();
                break;
            case 3:
                item = new Audio();
                break;
            case 4:
                item = new Video();
                break;
            default:
                break;
        }
        
        if (item != null) {
            item.testItemCreation();
            items.add(item);
            item.printInformation();
        } else {
            System.out.println("TEST ERROR: item is null");
            System.exit(0);
        }
    }
}
