/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libraryproject;

/**
 *
 * @author Ohjelmistokehitys
 */
public abstract class Playable extends LibraryItem {
    
    private String duration;
    
    @Override
    public void showInformation() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }    
    
    public void setDuration(String duration) {
        this.duration = duration;
    }
    
    public String getDuration() {
        return duration;
    }
}
