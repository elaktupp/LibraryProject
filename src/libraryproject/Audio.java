/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libraryproject;

import java.util.Random;
import java.util.Scanner;

/**
 * Class for Audio recordings.
 * 
 * @author Kimmo T.
 */
public class Audio extends LibraryItem {

    private final String[] genres = {
        "Undefined",
        "Alternative",
        "Rock",
        "Pop",
        "Classical",
        "Jazz",
        "Blues",
        "Hiphop",
        "Disco",
        "Heavy metal",
        "Hard rock",
        "Folk",
        "Country",
        "Punk",
        "Other"
    };
    
    private final String[] formats = {
        "Undefined",
        "LP",
        "CD",
        "Casette",
        "Other"
    };
    
    private String format = formats[0];
    private String genre = genres[0];
    private String performer = "<undefined>";
    private String composer = "<undefined>";
    private int durationInMinutes = 0;
    
    /**
     * Returns short description of this item category.
     * 
     * @return a string containing this category description.
     */
    @Override
    public String getCategory() {
        return "Audio recordings/Music";
    }
    
    /**
     * Creates new Audio instance and queries the information from
     * the user.
     */
    @Override
    public void createItem() {
        super.createItem(); // query for common info

        //TODO: Input sanity checks are missing.
        
        Scanner scan = new Scanner(System.in);
        System.out.print("Performer(s): ");
        performer = scan.nextLine();
        System.out.print("Composer(s): ");
        composer = scan.nextLine();
        format = formats[Utils.getSelectionFromList(formats,"Formats")];
        genre = genres[Utils.getSelectionFromList(genres,"Genres")];
        System.out.print("Duration in minutes: ");
        durationInMinutes = scan.nextInt();
    }
    
    /**
     * Shows the Audio item info. The call super.printInformation()
     * needs to be done to show the common library item information.
     */
    @Override
    public void printInformation() {
        System.out.println("------------------------------");
        super.printInformation();
        System.out.println("Performer: "+getPerformer());
        System.out.println("Composer:  "+getComposer());
        System.out.println("Genre:     "+getGenre());
        System.out.println("Format:    "+getFormat());
        System.out.println("Duration:  "+getDuration()+" minutes");
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
        if (format >= 0 && format < formats.length) {
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
        if (genre >= 0 && genre < genres.length) {
            this.genre = genres[genre];
        }
    }
    
    /**
     * Sets the performer(s) of this recording. Multiple names in one string
     * is allowed, since there is no implementation for separate names.
     * 
     * @param performer a string containing the performer(s)
     */
    public void setPerformer(String performer) {
        if (performer != null && !performer.isEmpty()) {
            this.performer = performer;
        }
    }
    
    /**
     * Gets the performer(s) of this recording.
     * 
     * @return a string containing the performer(s)
     */
    public String getPerformer() {
        return performer;
    }

    /**
     * Gets the composer(s) of this recording.
     * 
     * @return a string containing the composer(s)
     */
    public String getComposer() {
        return composer;
    }
    /**
     * Sets the name of the composer(s) of this recording. Multiple names
     * in one string is allowed, since there is no implementation for
     * separate names.
     * 
     * @param composer name(s) of the composer(s).
     */
    public void setComposer(String composer) {
        if (composer != null && !composer.isEmpty()) {
            this.composer = composer;
        }
    }

    /**
     * Gets duration of this audio recording in minutes.
     * 
     * @return duration in minutes.
     */
    public int getDuration() {
        return durationInMinutes;
    }

    /**
     * Sets duration of this audio recording in minutes.
     * 
     * @param durationInMinutes the duration of this recording in minutes.
     */
    public void setDuration(int durationInMinutes) {
        
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
        performer = TestHelp.randomBandName();
        composer = TestHelp.randomFirstName()+" "+TestHelp.randomLastName();
        format = formats[r.nextInt(formats.length-1)+1];
        genre = genres[r.nextInt(genres.length-1)+1];
        durationInMinutes = r.nextInt(54)+20;
    }
    
}
