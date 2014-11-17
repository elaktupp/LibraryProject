/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libraryproject;

import java.util.Random;
import java.util.Scanner;

/**
 * Class for Books and other written documents.
 * 
 * @author Kimmo T.
 */
public class Book extends LibraryItem {

    // TODO: Could also use some List type
    // i.e. could add new stuff at runtime
    
    private final String[] genres = {
        "Undefined",
        "ScienceFiction",
        "Science",
        "Fantasy",
        "Memoires",
        "Romance",
        "Other"
    };
    
    private final String[] formats = {
        "Undefined",
        "Hardcover",
        "Paperback",
        "E-book",
        "Other"
    };
    
    private String edition = "<undefined>";
    private String author = "<undefined>";
    private String summary = "<undefined>";;
    private String format = formats[0];
    private String genre = genres[0];

    /**
     * Returns short description of this item category.
     * 
     * @return a string containing this category description.
     */
    @Override
    public String getCategory() {
        return "Books/Papers";
    }
    
    /**
     * Creates new Book instance and queries the information from
     * the user.
     */
    @Override
    public void createItem() {
        super.createItem(); // query for common info
        
        // TODO: Input sanity checks are missing.

        Scanner scan = new Scanner(System.in);
        System.out.print("Edition: ");
        edition = scan.nextLine();
        System.out.print("Author: ");
        author = scan.nextLine();
        System.out.print("Summary: ");
        summary = scan.nextLine();
        format = formats[Utils.getSelectionFromList(formats,"Formats")];
        genre = genres[Utils.getSelectionFromList(genres,"Genres")];
    }
    
    /**
     * Shows the Book item info. The call super.printInformation()
     * needs to be done to show the common library item information.
     */
    @Override
    public void printInformation() {
        System.out.println("------------------------------");
        super.printInformation();
        System.out.println("Edition:   "+getEdition());
        System.out.println("Author(s): "+getAuthor());
        System.out.println("Genre:     "+getGenre());
        System.out.println("Format:    "+getFormat());
        System.out.println("Summary:   "+getSummary());
    }

    /**
     * Gets the edition of this book.
     * 
     * @return a string with edition
     */
    public String getEdition() {
        return edition;
    }

    /**
     * Sets the edition of this book.
     * 
     * @param edition a string with edition, cannot be not empty
     */    
    public void setEdition(String edition) {
        if (edition != null && !edition.isEmpty()) {
            this.edition = edition;
        }
    }
    
    /**
     * Gets the author(s) of this book.
     * 
     * @return a string with the author name(s)
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Sets the author(s) of this book.
     * 
     * @param author a string with author(s), cannot be not empty
     */ 
    public void setAuthor(String author) {
        if (author != null && !author.isEmpty()) {
            this.author = author;
        }
    }

    /**
     * Gets the summary of this book.
     * 
     * @return a string with the summary
     */
    public String getSummary() {
        return summary;
    }

    /**
     * Sets the summary of this book.
     * 
     * @param summary a string with the summary, cannot be not empty
     */
    public void setSummary(String summary) {
        if (summary != null && !summary.isEmpty()) {
            this.summary = summary;
        }
    }

    /**
     * Gets the format of this book.
     * 
     * @return a string with the format
     */
    public String getFormat() {
        return format;
    }

    /**
     * Sets the format of this book. Valid values are in String[] formats
     * table. First value in table the "Undefined", should be used only as
     * initial value and not anymore after its real value has been set.
     * 
     * @param format index value of an item in formats table.
     */
    public void setFormat(int format) {
        if (format >= 0 && format < formats.length) {
            this.format = formats[format];
        }
    }

    /**
     * Gets the genre of this book.
     * 
     * @return a string with name of the genre
     */
    public String getGenre() {
        return genre;
    }

    /**
     * Sets the genre of this book. Valid values are in String[] genres
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
        edition = Integer.toString(r.nextInt(10));
        author = TestHelp.randomFirstName()+" "+TestHelp.randomLastName();
        summary = "Summa summarum.";
        format = formats[r.nextInt(formats.length-1)+1];
        genre = genres[r.nextInt(genres.length-1)+1];
    }
    
}
