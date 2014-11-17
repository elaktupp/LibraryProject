/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libraryproject;

import java.util.Scanner;

/**
 * The class with main method.
 * 
 * @author Kimmo T.
 */
public class LibraryProject {

    /**
     * Possible main method arguments are "client", "admin", "test" or
     * no arguments.
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // Just create a new LibraryUI and that's it.
        LibraryUI library = new LibraryUI(args);

    }
    
}
