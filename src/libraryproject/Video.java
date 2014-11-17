/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libraryproject;

import java.util.Random;
import java.util.Scanner;

/**
 * Class for Video recordings, Movies and such.
 * 
 * @author Kimmo T.
 */
public class Video extends LibraryItem {

    private final String[] genres = {
        "Undefined",
        "Comedy",
        "Drama",
        "War",
        "Documentary",
        "Musical",
        "Romance",
        "Sport",
        "Other"
    };
    
    private final String[] formats = {
        "Undefined",
        "BlueRay",
        "DVD",
        "VHS",
        "Other"
    };
    
    private String format = formats[0];
    private String genre = genres[0];
    private String actor = "<undefined>";
    private String director = "<undefined>";
    private int durationInMinutes = 0;
    
    /**
     * Returns short description of this item category.
     * 
     * @return a string containing this category description.
     */
    @Override
    public String getCategory() {
        return "Video recordings/Movies";
    }
    
    /**
     * Creates new Video instance and queries the information from
     * the user.
     */
    @Override
    public void createItem() {
        super.createItem(); // Query for super stuff
        
        // TODO: Input sanity checks are missing.
        
        Scanner scan = new Scanner(System.in);
        System.out.print("Actor(s): ");
        actor = scan.nextLine();
        System.out.print("Director: ");
        director = scan.nextLine();
        format = formats[Utils.getSelectionFromList(formats,"Formats")];
        genre = genres[Utils.getSelectionFromList(genres,"Genres")];
        System.out.print("Duration in minutes: ");
        durationInMinutes = scan.nextInt();
    }
    
    /**
     * Shows the Video item info. The call super.printInformation()
     * needs to be done to show the common library item information.
     */
    @Override
    public void printInformation() {
        System.out.println("------------------------------");
        super.printInformation(); // query for common info
        
        // TODO: Input sanity checks are missing.
        
        System.out.println("Actor(s):  "+getActor());
        System.out.println("Director:  "+getDirector());
        System.out.println("Genre:     "+getGenre());
        System.out.println("Format:    "+getFormat());
        System.out.println("Duration:  "+getDurationInMinutes()+" minutes");
    }

    /**
     * Gets the actor(s) of this recording.
     * 
     * @return a string containing the actor(s)
     */
    public String getActor() {
        return actor;
    }

    /**
     * Sets the actor(s) of this recording. Multiple names in one string
     * is allowed, since there is no implementation for separate names.
     * 
     * @param actor a string containing the actor(s)
     */
    public void setActor(String actor) {
        if (actor != null && !actor.isEmpty()) {
            this.actor = actor;
        }
    }

    /**
     * Gets the director(s) of this recording.
     * 
     * @return a string containing the director(s)
     */
    public String getDirector() {
        return director;
    }

    /**
     * Sets the director(s) of this recording. Multiple names in one string
     * is allowed, since there is no implementation for separate names.
     * 
     * @param director a string containing the director(s)
     */
    public void setDirector(String director) {
        if (director != null && !director.isEmpty()) {
            this.director = director;
        }
    }
    
    /**
     * Gets the format of this recording.
     * 
     * @return a string with name of the format
     */
    public String getFormat() {
        return format;
    }

    /**
     * Sets the format of this recording. Valid values are in String[] formats
     * table. First value in table the "Undefined", should be used only as
     * initial value and not anymore after its real value has been set.
     * 
     * @param format index value of an item in formats table.
     */
    public void setFormat(int format) {
        if (format > 0 && format < formats.length) {
            this.format = formats[format];
        }
    }

    /**
     * Gets the genre of this recording.
     * 
     * @return a string with name of the genre
     */
    public String getGenre() {
        return genre;
    }

    /**
     * Sets the genre of this recording. Valid values are in String[] genres
     * table. First value in table the "Undefined", should be used only as
     * initial value and not anymore after its real value has been set.
     * 
     * @param genre index value of an item in genres table.
     */
    public void setGenre(int genre) {
        if (genre > 0 && genre < genres.length) {
            this.genre = genres[genre];
        }
    }

    /**
     * Gets the duration of this recording in minutes.
     * 
     * @return the recorsing duration in minutes
     */
    public int getDurationInMinutes() {
        return durationInMinutes;
    }

    /**
     * Sets the duration of this recording in minutes.
     * 
     * @param durationInMinutes the recorsing duration in minutes
     */
    public void setDurationInMinutes(int durationInMinutes) {
        
        // TODO: Input sanity checks is no complete. Depends on format.
        
        if (durationInMinutes > 0) {
            this.durationInMinutes = durationInMinutes;
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
        actor = null;
        do {
            if (actor == null) {
                actor = TestHelp.randomFirstName()+" "+TestHelp.randomLastName();
            } else {
                actor += ", "+TestHelp.randomFirstName()+" "+TestHelp.randomLastName();
            }
        } while (r.nextBoolean());
        director = TestHelp.randomFirstName()+" "+TestHelp.randomLastName();
        format = formats[r.nextInt(formats.length-1)+1];
        genre = genres[r.nextInt(genres.length-1)+1];
        durationInMinutes = r.nextInt(180)+20;
    }
}
