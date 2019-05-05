
package birdapp.domain;

/**
 * Käyttäjän havainnoimia lintuja kuvaava luokka
 */

import birdapp.ui.BirdappUi;
import java.util.*;


public class Birdapp {
    private int id;
    private List<Boolean> selected;
    private String content;
    private User user;
    
    
    public Birdapp(List selected, User user) {
        this.user = user;
        this.selected = selected;
    }
    
    public List getSelected() {
        return selected;
    }
    
    public User getUser() {
        return user;
    }
    
    @Override
    public boolean equals(Object obj){
        if(!(obj instanceof Birdapp)) {
            return false;
        }
        Birdapp other = (Birdapp) obj;
        return id == other.id;
    }
    
}
