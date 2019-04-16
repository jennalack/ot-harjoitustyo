
package birdapp.domain;

import java.util.*;
import birdapp.dao.BirdappDao;
import birdapp.dao.UserDao;
import java.util.stream.Collectors;

public class BirdappService {
    private BirdappDao birdDao;
    private UserDao userDao;
    private User loggedIn;
    private List<Birdapp> birds;
    
    public BirdappService(BirdappDao birdDao, UserDao userDao) {
        this.birdDao=birdDao;
        this.userDao=userDao;
        birds = new ArrayList<>();
        birds.add(new Birdapp("Alli", loggedIn));
        birds.add(new Birdapp("Allihaahka", loggedIn));
        birds.add(new Birdapp("Amerikanhaapana", loggedIn));

    }
    
    public boolean createBirdapp(String content) {
        Birdapp birdapp = new Birdapp(content, loggedIn);
        try {
            birdDao.create(birdapp);
        } catch (Exception ex) {
            return false;
        }
        return true;
    }
    
    public List<Birdapp> getUndone() {
        if(loggedIn==null) {
            return new ArrayList<>();
//            return birds;
        }
        
        return birdDao.getAll().stream().filter(b->b.getUser().equals(loggedIn)).filter(b->!b.isDone()).collect(Collectors.toList());
    }
    
    public void markDone(int id) {
        try{
            birdDao.setDone(id);
        } catch (Exception ex) {
            
        }
    }
    
    public boolean login(String username) {
        User user = userDao.findByUsername(username);
        if(user == null) {
            return false;
        }
        loggedIn = user;
        return true;
    }
    
    public User getLoggedUser() {
        return loggedIn;
    }
    
    public void logout() {
        loggedIn = null;
    }
    
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
}
