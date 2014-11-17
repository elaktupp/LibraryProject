/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libraryproject;

import java.time.LocalDate;
import java.util.Random;

/**
 * This class contains helper methods for getting random content for
 * the library items. Mainly done to amuse the author.
 * 
 * <br><br><b>For testing purposes only.</b><br>
 * 
 * @author Kimmo T.
 */
public class TestHelp {
    public static String[] firstNames = {"Ada","Abraham","Sylvester","Arnold",
    "Theodor","George","Hillary","Britanny","Kylie","Bruce","Juha","Esa-Pekka",
    "Stephen","Markus","William","Billy-Ray","Miley","Sue-Ann","Bubba","Simon"};
    public static String[] lastNames = {"Lovelace","Lincoln","Stallone",
    "Schwarzenegger","Roosevelt","Bush","Clinton","Spears","Minogue","Willis",
    "Vainio","Salonen","King","Veijola","Shakespear","Cyrus","Jones","Smith",
    "Cowell"};
    public static String[] nameEndAdds = {" Jr."," the III"," Sr."," the IX",
    "","","","","","","","","","","","","","","","","","","","","","","",""};
    public static String[] wordsForTitle = {"Murder","Study","Journey","Ballad","Us",
    "Memory","Hello","War","Blackness","Modern","Art","Jolly","Laugh","Any",
    "How","Building","Lifestyle","Economics","Learning","Hitchhicker","Star",
    "Forever","Sounds","Hell","Heaven","Computer","Cousin","King","World",
    "Burger","Human","Women","Men","Gentleman","Mountain","Madness","Moon",
    "Lady","Cat","Dog","Factory","Home","World","There","Bed","Oulu","Light",
    "Dummies","Tea","Fantasy","Blue","Red","Black","Green","Play","Game",
    "U.S.A.","Finland","Germany","China","Guide","Enlightment","Self","Truth",
    "Cooking","Health","Wars","Trek","Cave","Life","Living","Dying","Point",
    "Sister","Brother","Sleep","Ice","Fire","Thunder","Storm","Sea","Cash",
    "Money","Rich","Treasure","Island","Flame","Doctor","Order","Help","Things",
    "Everyday","Psychology","Answer","Question","Engineer","Artist","Wasted",
    "I","Little","Big","Travelling","Around","Digging","Break","Even","Odd",
    "Times","Observer","Chronicle","Daily","Mail","Vanity","Vogue","National"};
    public static String[] mids = {" in "," of "," made of "," by "," made in ",
    " and "," or "," as ",", ","... "," to "," for ","'s "," according to ",
    " away from "," because of "," says "," means "," like "," is my "," goes ",
    " "," "," "," "," "};
    public static String[] ends = {"?","!","...","!!!","","","","","","","",""};
    public static String[] publisherEnds = {" publishing"," studios"," and sons",
    " #1"," prints"," productions"," publication","'s"};
    public static String[] bandFirstParts = {"Oulu","Flower","One","Monster","Iron",
    "Led","Def","Alice","Royal","Rock","Mad","The","Electric","Steel","Northern",
    "Metal","Sky","Black","Blue","Red","Big","Harmony","Holy","Rolling","Fire",
    "Dire","Blues","Jazz","Machine","Hip","Arctic"};
    public static String[] bandEnds = {"Philharmonics","Power","Direction",
    "Quartet","Maiden","Zeppelin","Leppard","Cooper","Bastards","Forever","Man",
    "Band","Quartet","Boys","Girls","Sisters","Brothers","Hilly-Billies","Yo!",
    "Rocks","Stones","Kings","Lights","Orchestra","Anarchy","Brass","Blues",
    "Jazz","Strings","Machine","Rappers","Hippies","Hoppers"};

    public static long forFakeIsbn = 0; // common counter for all instances
    
    public static final Random rand = new Random();
    
    public static String randomFirstName() {
        return firstNames[rand.nextInt(firstNames.length)];
    }
    
    public static String randomLastName() {
        return lastNames[rand.nextInt(lastNames.length)]+randomNameEndAdd();
    }

    public static String randomNameEndAdd() {
        return nameEndAdds[rand.nextInt(nameEndAdds.length)];
    }
    
    public static String randomTitle() {
        switch (rand.nextInt(10)) {
            case 0:
                return wordsForTitle[rand.nextInt(wordsForTitle.length)];
            case 1:
                return wordsForTitle[rand.nextInt(wordsForTitle.length)]+"! "+
                       wordsForTitle[rand.nextInt(wordsForTitle.length)]+"! "+
                       wordsForTitle[rand.nextInt(wordsForTitle.length)]+"!";
            case 2:
                return wordsForTitle[rand.nextInt(wordsForTitle.length)]+
                       mids[rand.nextInt(mids.length)]+
                       wordsForTitle[rand.nextInt(wordsForTitle.length)]+
                       mids[rand.nextInt(mids.length)]+
                       wordsForTitle[rand.nextInt(wordsForTitle.length)];
            case 3:
                return randomFirstName()+
                       mids[rand.nextInt(mids.length)]+
                       wordsForTitle[rand.nextInt(wordsForTitle.length)]+
                       mids[rand.nextInt(mids.length)]+
                       wordsForTitle[rand.nextInt(wordsForTitle.length)]+
                       ends[rand.nextInt(ends.length)];
            case 4:
                return randomFirstName()+" "+randomLastName()+
                       mids[rand.nextInt(mids.length)]+
                       wordsForTitle[rand.nextInt(wordsForTitle.length)]+
                       mids[rand.nextInt(mids.length)]+
                       wordsForTitle[rand.nextInt(wordsForTitle.length)]+
                       ends[rand.nextInt(ends.length)];
            case 5:
                return randomBandName()+
                       mids[rand.nextInt(mids.length)]+
                       wordsForTitle[rand.nextInt(wordsForTitle.length)];
            default:
                return wordsForTitle[rand.nextInt(wordsForTitle.length)]+
                       mids[rand.nextInt(mids.length)]+
                       wordsForTitle[rand.nextInt(wordsForTitle.length)];
        }
    }
    
    public static String randomMagazineName() {
        switch (rand.nextInt(5)) {
            case 0:
                return bandFirstParts[rand.nextInt(bandFirstParts.length)]+" "+
                       wordsForTitle[rand.nextInt(wordsForTitle.length)];
            case 1:
                return wordsForTitle[rand.nextInt(wordsForTitle.length)]+" "+
                       wordsForTitle[rand.nextInt(wordsForTitle.length)];
            case 2:
                return bandFirstParts[rand.nextInt(bandFirstParts.length)];
            default:
                return wordsForTitle[rand.nextInt(wordsForTitle.length)];
        }
    }
    
    public static String randomBandName() {
        String band;
        if (rand.nextBoolean()) {
            band = (bandFirstParts[rand.nextInt(bandFirstParts.length)]+" "+
                    bandEnds[rand.nextInt(bandEnds.length)]);
        } else {
            band = "The "+randomFirstName()+" "+randomLastName()+" band";
        }
        return band;
    }
    
    public static String randomPublisherName() {
        String publisher;
        switch (rand.nextInt(4)) {
            case 0:
                publisher = randomLastName()+publisherEnds[rand.nextInt(publisherEnds.length)];
                break;
            case 1:
                publisher = wordsForTitle[rand.nextInt(wordsForTitle.length)]+
                            publisherEnds[rand.nextInt(publisherEnds.length)];
                break;
            case 2:
                publisher = bandFirstParts[rand.nextInt(bandFirstParts.length)]+
                            publisherEnds[rand.nextInt(publisherEnds.length)];
                break;
            case 3:
                publisher = "#"+rand.nextInt(667)+publisherEnds[rand.nextInt(publisherEnds.length)];
                break;
                
            default:
                publisher = "Default"; // never happens
        }
        
        return publisher;
    }
    
    public static String randomDate() {
        int year = rand.nextInt(515)+1500;
        int month = rand.nextInt(12)+1;
        int day = rand.nextInt(28)+1; // keeps it safe
        String date = Integer.toString(year)+"-";
        if (month < 10) {
            date += "0"+Integer.toString(month)+"-";
        } else {
            date += Integer.toString(month)+"-";
        }
        if (day < 10) {
            date += "0"+Integer.toString(day);
        } else {
            date += Integer.toString(day);
        }
        return date;
    }
    
    public static String randomDateAroundToday() {
        int year = rand.nextInt(2)-1;
        int month = rand.nextInt(3)-1;
        int day = rand.nextInt(30)-15;
        
        LocalDate now = LocalDate.now();

        now = now.plusWeeks(year);
        now = now.plusMonths(month);
        now = now.plusDays(day);
        
        year = now.getYear();
        month = now.getMonthValue();
        day = now.getDayOfMonth();
        
        String date = Integer.toString(year)+"-";
        if (month < 10) {
            date += "0"+Integer.toString(month)+"-";
        } else {
            date += Integer.toString(month)+"-";
        }
        if (day < 10) {
            date += "0"+Integer.toString(day);
        } else {
            date += Integer.toString(day);
        }
       
        return date;
    }   
}
