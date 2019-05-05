
package birdapp.domain;

/**
 * Sovelluslogiikasta vastaava luokka 
 */

import java.util.*;
import birdapp.dao.BirdappDao;
import birdapp.dao.UserDao;
import birdapp.ui.BirdappUi;
import java.util.stream.Collectors;

public class BirdappService {
    private BirdappDao birdDao;
    private UserDao userDao;
    private User loggedIn;
    
    /**
    * Lista checkboxien totuusarvojen tallentamiseen
    */
    public ArrayList<Boolean> selected;
    
    /**
    * Lista, johon tallennetaan tekstitiedostosta totuusarvot checboxin checkien palauttamiseksi
    */
    public ArrayList<Boolean> returned = new ArrayList<>(); 
    
    public BirdappService(BirdappDao birdDao, UserDao userDao) {
        this.birdDao=birdDao;
        this.userDao=userDao;
    }
    
    /**
    * sisäänkirjautuminen
    * 
    * @param   username   käyttäjätunnus
    * 
    * @return true jos käyttäjätunnus on olemassa, muuten false 
    */    
    
    public boolean login(String username) {
        User user = userDao.findByUsername(username);
        if(user == null) {
            return false;
        }
        loggedIn = user;
        return true;
    }
    
    /**
    * Tekstitiedostosta totuusarvojen palauttaminen
    * 
    * @return returned eli lista tekstitiedostosta haetuista totuusarvoista
    */ 
    
    public ArrayList<Boolean> getChecked() {
       returned = birdDao.getListOfChecks();
       return returned;
    }
    
    /**
    * kirjautuneena oleva käyttäjä
    * 
    * @return kirjautuneena oleva käyttäjä 
    */   
    
    public User getLoggedUser() {
        return loggedIn;
    }
    
    /**
    * uloskirjautuminen
    */  
    
    public void logout() {
        loggedIn = null;
    }
    
    /**
    * uuden käyttäjän luominen
    * 
    * @param   username   käyttäjätunnus
    * @param   name   käyttäjän nimi
    * 
    * @return true jos käyttäjätunnus on luotu onnistuneesti, muuten false 
    */ 
    
    public boolean createUser(String username, String name) {
        if(userDao.findByUsername(username) != null) {
            return false;
        }
        User user = new User(username, name);
        try{
            userDao.create(user);
        } catch(Exception e) {
            return false;
        }
        return true;
    }
    
    /**
    * Checkboxien checkien tallentaminen
    * 
    * @param   list   lista, jossa true, jos boxissa check ja muuten false
    * 
    * @return true birdapp-olio luotu onnistuneesti, muuten false 
    */ 
    
    public boolean saveChecks(ArrayList<Boolean> list) {
        Birdapp birdapp = new Birdapp(list, loggedIn);
        try {
            birdDao.create(birdapp);
        } catch (Exception ex) {
            return false;
        }
        return true;
    }
    
}
