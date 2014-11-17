/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libraryproject;

import java.util.Random;
import java.util.Scanner;

/**
 * Class for Magazines.
 * 
 * @author Kimmo T.
 */
public class Magazine extends LibraryItem {

    // TODO: Could also use some List type
    // i.e. could add new stuff at runtime
    
    private final String[] genres = {
        "Undefined",
        "Science",
        "Sports",
        "Hobby",
        "Cars",
        "Lifestyle",
        "News",
        "Cooking",
        "Computers",
        "Economics",
        "Other"
    };
    
    private String genre = genres[0];
    private int issueNumber = 1;
    
    /**
     * Returns short description of this item category.
     * 
     * @return a string containing this category description.
     */
    @Override
    public String getCategory() {
        return "Magazines/Newspapers";
    }
    
    /**
     * Creates new Magazine instance and queries the information from
     * the user.
     */
    @Override
    public void createItem() {
        super.createItem(); // Query for super stuff
        
        // TODO: Input sanity checks are missing.
        
        Scanner scan = new Scanner(System.in);
        System.out.print("Issue #: ");
        issueNumber = scan.nextInt();
        genre = genres[Utils.getSelectionFromList(genres,"Genres")];
    }
    
    /**
     * Prints out the Magazine item info. The call super.printInformation()
     * needs to be done to show the common library item information.
     */
    @Override
    public void printInformation() {
        System.out.println("------------------------------");
        super.printInformation(); // query for common info
        
        // TODO: Input sanity checks are missing.
        
        System.out.println("Genre:     "+getGenre());
        System.out.println("Issue #:   "+getIssueNumber());
    }
    
    /**
     * Gets the genre of this magazine.
     * 
     * @return a string with name of the genre
     */
    public String getGenre() {
        return genre;
    }

    /**
     * Sets the genre of this magazine. Valid values are in String[] genres
     * table. First value in table the "Undefined", should be used only as
     * initial value and not anymore after its real value has been set.
     * 
     * @param genre index value of an item in genres table.
     */
    public void setGenre(int genre) {
        if (genre >= 0 && genre < genres.length) {
            this.genre = genres[genre];
        }
    }

    /**
     * Gets issue number of this magazine.
     * 
     * @return an integer issue number of this magazine
     */
    public int getIssueNumber() {
        return issueNumber;
    }

    /**
     * Sets issue number of this magazine.
     * 
     * @param issueNumber an integer issue number of this magazine
     */
    public void setIssueNumber(int issueNumber) {
        if (issueNumber > 0) {
            this.issueNumber = issueNumber;
        }
    }
    
    /*
     * FOR TESTING PURPOSES ========================================
     */
    
    /**
     * Fills this magazine instance with randomly generate information.
     * 
     * <br><br><b>For testing purposes only.</b><br>
     */
    @Override
    public void testItemCreation() {
        super.testItemCreation();
        Random r = new Random();
        setTitle(TestHelp.randomMagazineName());
        genre = genres[r.nextInt(genres.length-1)+1];
        issueNumber = r.nextInt(24) + 1;
    }
}
