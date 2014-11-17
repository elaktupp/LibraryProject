/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libraryproject;

import java.time.LocalDate;
import java.util.Random;
import java.util.Scanner;

/**
 * Class for Utility methods used in the library.
 * 
 * @author Kimmo T.
 */
public class Utils {
    public static final int INVALID = -1;
    public static final int MENU = 0;
    public static final int CLIENT = 1;
    public static final int ADMIN = 2;

    // TODO: Could also add possibility to use Ctrl + C to abort
    // just catch correct exception and do abort stuff.
    
    // TODO: Could implement here getters for different item infos
    // and for example ISBN input with validation.
    
    // TODO: Not really that generic, but it serves.
   
    /**
     * Input query for the menu item selection from 1 to range.
     * If parameter hasExitZero is true then 0 value can be returned
     * to the caller and also the input query contains the zero.
     * If parameter hasExitZero is false then the 0 is INVALID value and
     * will return INVALID.
     * 
     * @param range determines the highest valid selection value.
     * @param hasExitZero determines if zero is valid selection value.
     * 
     * @return valid input value given by the user or INVALID.
     */
    public static int getSelection(int range, boolean hasExitZero) {
        int selected = 0;
        Scanner scan = new Scanner(System.in);
        if (hasExitZero) {
            System.out.print("Please select (1 to "+range+" or 0): ");
            try {
                selected = scan.nextInt();
            } catch (Exception e) {
                selected = INVALID;
            } finally {
                if (selected < 0 || selected > range) {
                    System.out.println("Invalid selection, try again.");
                    selected = INVALID;
                }
            }
        } else {
            System.out.print("Please select (1 to "+range+"): ");
            try {
                selected = scan.nextInt();
            } catch (Exception e) {
                selected = INVALID;
            } finally {
                if (selected <= 0 || selected > range) {
                    System.out.println("Invalid selection, try again.");
                    selected = INVALID;
                }
            }
        }


        return selected;
    }
    
    /**
     * First value on the list is expected to be the "Undefined" value
     * that is used as a initial value before actual value is set and
     * should not be otherwise used.
     * 
     * @param list items (text strings) to be displayed to the user
     * @param title query phrase shown to the user
     * @return 
     */
    public static int getSelectionFromList(String[] list, String title) {
        int selected = INVALID;
        do {
            System.out.println(title+":");
            // index = 1, not including undefined to selection
            for (int index = 1; index < list.length; index++) {
                System.out.println(index + " - " +list[index]);
            }
            selected = Utils.getSelection((list.length-1),false);
        } while(selected <= 0);
        
        return selected;
    }

}
