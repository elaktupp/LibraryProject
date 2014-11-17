/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libraryproject;

import java.time.LocalDate;

/**
 * Class for User creation for the library.
 * 
 * @author Kimmo T.
 */
public class User {
    private String firstName;
    private String lastName;
    private boolean isAdmin;
    private LocalDate birthday;
    
    /**
     * No default constructor, must use parameterized version.
     */
    private User() {
        // Not to be used
    }
    
    /**
     * Creates a new user for the library. This class does not have
     * set methods because the constructor requires all values.
     * 
     * @param firstName first name of this user
     * @param lastName last name of this user
     * @param birthday in "yyyy-mm-dd" format
     * @param isAdmin do this user have admin rights
     */
    public User(String firstName, String lastName, String birthday, boolean isAdmin) {
        
        // TODO: No sanity check for the values. What to do if... insane?
        
        // TODO: Perhaps this class should be implemented like the LibraryItem
        // children i.e. have set methods with input query and do-while?
        
        this.firstName = firstName;
        this.lastName = lastName;
        this.isAdmin = isAdmin;
        this.birthday = LocalDate.parse(birthday);
    }
    
    /**
     * Gets the firstName of this user.
     * 
     * @return the firstName string
     */
    public String getFirstName() {
        return firstName;
    }
    
    /**
     * Gets the lastName of this user.
     * 
     * @return the lastName string
     */
    public String getLastName() {
        return lastName;
    }
    
    /**
     * Conveniently returns string with first and last name concatanated.
     * For example: "Joe" and "Doe" results "Joe Doe".
     * 
     * @return a string with concatanated firstName and lastName
     */
    public String getFullName() {
        return (firstName+" "+lastName);
    }
    
    /**
     * Returns true if this user has admin rights.
     * 
     * @return true if this user has admin rights, false if not.
     */
    public boolean isAdmin() {
        return isAdmin;
    }
    
    /**
     * Gets the birthday of this user.
     * 
     * @return the birthday as LocalDate
     */
    public LocalDate getBirthday() {
        return birthday;
    }
    
    /**
     * Gets the age of this user in years.
     * 
     * @return the age of this user in years
     */
    public int getAge() {
        // How many years from the birthday until now
        return birthday.until(LocalDate.now()).getYears();
    }

}
