/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libraryproject;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 *
 * @author Ohjelmistokehitys
 */
public abstract class LibraryItem {
    
    private String isbn = "<undefined>";
    private String title = "<undefined>";
    private String publisher = "<undefined>";
    private int publishingYear = 0;
    private String loanedTo = null;
    private LocalDate loanDate = null; // indicates if loaned
    private int loanTimeInDays = 0;
    private String reservedTo = null;
    private LocalDate reservedDate = null; // indicates if reserved
    private int ageLimitInYears = 0;
    
    /**
     * Returns short description of the item category.
     * 
     * @return a string containing this category description.
     */
    public abstract String getCategory();
    
    // TODO: Boolean for loan/reservation would make code more readable
    // perhaps worth adding...?
    
    // TODO: What to do if ISBN already exists, since there is no
    // visibility to Library from here.
    
    // TODO: There are no sanity checks for the set value methods.
    // Perhaps there should be "MyScanner" class that implements
    // suitable input methods with additional checks. This could also
    // solve the problem mentioned in above TODO.
    
    /**
     * Query for common item info. The child needs to override this and
     * then call super.createItem() in its implementation.
     */
    protected void createItem() {
        // 
        System.out.println(">>> FILL IN NEW ITEM INFORMATION");
        Scanner scan = new Scanner(System.in);
        System.out.print("Title: ");
        title = scan.nextLine();
        System.out.print("ISBN: ");
        isbn = scan.nextLine();
        System.out.print("Publisher: ");
        publisher = scan.nextLine();
        System.out.print("Publising year: ");
        publishingYear = scan.nextInt();
        System.out.print("Loan time in days: ");
        loanTimeInDays = scan.nextInt();
        System.out.print("Age limit in years: ");
        ageLimitInYears = scan.nextInt();
        
    }
    
    /**
     * Prints out the common item info. The child needs to override this
     * and then call super.printInformation() in its implementation.
     */
    protected void printInformation() {
        System.out.println("Category:  "+getCategory()); // a child will tell
        System.out.println("Title:     "+getTitle());
        System.out.println("ISBN:      "+getIsbn());
        System.out.println("Publisher: "+getPublisher());
        System.out.println("Published: "+getPublishingYear());
        if (loanDate != null) {
            String starts = loanDate.format(DateTimeFormatter.ISO_LOCAL_DATE);
            String ends = getReturnDate().format(DateTimeFormatter.ISO_LOCAL_DATE);
            System.out.println("Status:    IN LOAN from " + starts + " until " + ends);
        } else if (reservedDate != null) {
            System.out.println("Status:    RESERVED");
        } else {
            System.out.println("Status:    AVAILABLE");
        }
        System.out.println("Loan time: "+getLoanTimeInDays()+" days");
        if (getAgeLimitInYears() > 0) {
            System.out.println("Age limit: "+getAgeLimitInYears()+" years or older");
        } else {
            System.out.println("Age limit: None");
        }
    }
    
    /**
     * Gets the ISBN of this item.
     * 
     * @return ISBN of this item
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * Sets the ISBN of this item.
     * For example: 9876-543-21-0
     * 
     * @param title title of this item
     */
    public void setIsbn(String isbn) {
        if (isbn != null && !isbn.isEmpty() && isbn.length() == 13) {
            this.isbn = isbn;
        }
    }

    /**
     * Gets the title of this item.
     * 
     * @return title of this item
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of this item.
     * 
     * @param title title of this item
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the name of the publisher of this item.
     * 
     * @return name of the publisher
     */
    public String getPublisher() {
        return publisher;
    }

    /**
     * Sets the name of the publisher of this item.
     * 
     * @param publisher name of the publisher
     */
    public void setPublisher(String publisher) {
        if (publisher != null && !publisher.isEmpty()) {
            this.publisher = publisher;
        }
    }
    
    /**
     * Gets the date when this item was published.
     * 
     * @return publishing year as integer
     */
    public int getPublishingYear() {
        return publishingYear;
    }

    /**
     * Sets the date when this item was published.
     * 
     * @param publishingYear publishing year as integer
     */
    public void setPublishingYear(int publishingYear) {
        if (publishingYear >= 1450 &&
            publishingYear <= LocalDate.now().getYear()) {
            this.publishingYear = publishingYear;
        }
    }

    /**
     * Gets the date when this item was loaned.
     * 
     * @return date of loan as LocalDate instance
     */
    public LocalDate getLoanDate() {
        return loanDate;
    }

    /**
     * Sets the date when this item was loaned.
     * 
     * @param loanDate date of loan as LocalDate
     */
    public void setLoanDate(LocalDate loanDate) {
        this.loanDate = loanDate;
    }

    /**
     * Gets the date when this item should be returned.
     * 
     * @return date of return as LocalDate
     */
    public LocalDate getReturnDate() {
        return loanDate.plusDays(loanTimeInDays);
    }
    
    /**
     * Gets the date when this item was reserved.
     * 
     * @return date of reservation as LocalDate
     */
    public LocalDate getReservedDate() {
        return reservedDate;
    }

    public void setReservationDate(LocalDate reservedDate) {
        this.reservedDate = reservedDate;
    }
    
    /**
     * Gets the length of the loan period of this item.
     * 
     * @return length of the loan period in days
     */
    public int getLoanTimeInDays() {
        return loanTimeInDays;
    }

    /**
     * Sets the length of the loand period of this item in days.
     * 
     * @param loanTimeInDays length of the loan period in days
     */
    public void setLoanTimeInDays(int loanTimeInDays) {
        this.loanTimeInDays = loanTimeInDays;
    }
 
    /**
     * Sets the name of the user who has loaned this item.
     * The name <b>must be the full name</b> of the user.
     * 
     * @param loanedTo the full name of the loaner
     */
    public void setLoanedTo(String loanedTo) {
        this.loanedTo = loanedTo;
    }
    
    /**
     * Gets the full name of the user who has loaned this item.
     * 
     * @return full name of the loaner
     */
    public String getLoanedTo() {
        return loanedTo;
    }

    /**
     * Sets the name of the user who has reserved this item.
     * The name <b>must be the full name</b> of the user.
     * 
     * @param reservedTo the full name of the reserver
     */
    public void setReservedTo(String reservedTo) {
        this.reservedTo = reservedTo;
    }
    
    /**
     * Gets the full name of the user who has reserved this item.
     * 
     * @return the full name of the reserver
     */
    public String getReservedTo() {
        return reservedTo;
    }
    
    /**
     * Gets the age limit of this item in years.
     * 
     * @return age limit in years
     */
    public int getAgeLimitInYears() {
        return ageLimitInYears;
    }

    /**
     * Sets the age limit of this item.
     * Zero value means there is no age limit. Value above zero means
     * the client age must be at least the same as the limit in order
     * to be able to loan/reserve the item.
     * 
     * @param ageLimitInYears age limit in years
     */
    public void setAgeLimitInYears(int ageLimitInYears) {
        if (ageLimitInYears >= 0) {
            this.ageLimitInYears = ageLimitInYears;
        }
    }
    
    /**
     * Checks if the user is old enough to have this item.
     * 
     * @param user instance of User class
     * @return true if allowed for the user, false is not allowed
     */
    public boolean isAgeLimitPassed(User user) {
        return (getAgeLimitInYears() <= user.getAge());
    }
    
    /*
     * FOR TESTING PURPOSES ========================================
     */
  
    /**
     * Test item creation. Child must override this method so that it calls
     * this method to fill the common info and implement its own info.
     * 
     * <br><br><b>For testing purpose only.</b><br>
     */
    protected void testItemCreation() {
        // Test item creation. Child must implement its own details.

        // Fake ISBN generation
        TestHelp.forFakeIsbn++;
        String s = Long.toString(TestHelp.forFakeIsbn);
        if (TestHelp.forFakeIsbn > 999) {
            isbn = s+"-000-00-0";
        } else if (TestHelp.forFakeIsbn > 99) {
            isbn = "0000-"+s+"-00-0";
        } else if (TestHelp.forFakeIsbn > 9) {
            isbn = "0000-000-"+s+"-0";
        } else {
            isbn = "0000-000-00-"+s;
        }
        title = TestHelp.randomTitle();
        publisher = TestHelp.randomPublisherName();
        publishingYear = 2014 - TestHelp.rand.nextInt(100);
        if (this instanceof Book) {
            loanTimeInDays = 30;
        } else {
            loanTimeInDays = 14;
        }
        int[] limits = {0,0,0,0,0,0,0,0,0,0,0,0,7,7,7,12,12,12,15,15,15,18};
        ageLimitInYears = limits[TestHelp.rand.nextInt(limits.length)];
    }
}
