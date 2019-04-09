
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
//        birds.add("Amerikanmustalintu", loggedIn);
//        birds.add(	"Amerikantavi", loggedIn);
//        birds.add(	"Amerikantukkasotka", loggedIn);
//        birds.add(	"Haahka", loggedIn);
//        birds.add(	"Haapana", loggedIn);
//        birds.add(	"Harmaasorsa", loggedIn);
//        birds.add(	"Heinätavi", loggedIn);
//        birds.add(	"Isokoskelo", loggedIn);
//        birds.add(	"Jouhisorsa", loggedIn);
//        birds.add(	"Kanadanhanhi", loggedIn);
//        birds.add(	"Kiljuhanhi", loggedIn);
//        birds.add(	"Kuparisorsa", loggedIn);
//        birds.add(	"Kyhmyhaahka", loggedIn);
//        birds.add(	"Kyhmyjoutsen", loggedIn);
//        birds.add(	"Kyhmypilkkasiipi", loggedIn);
//        birds.add(	"Lapasorsa", loggedIn);
//        birds.add(	"Lapasotka", loggedIn);
//        birds.add(	"Laulujoutsen", loggedIn);
//        birds.add(	"Lumihanhi", loggedIn);
//        birds.add(	"Lyhytnokkahanhi", loggedIn);
//        birds.add(	"Mandariinisorsa", loggedIn);
//        birds.add(	"Merihanhi", loggedIn);
//        birds.add(	"Metsähanhi", loggedIn);
//        birds.add(	"Mustalintu", loggedIn);
//        birds.add(	"Nokisorsa", loggedIn);
//        birds.add(	"Pikkujoutsen", loggedIn);
//        birds.add(	"Pikkukanadanhanhi", loggedIn);
//        birds.add(	"Pikkulapasotka", loggedIn);
//        birds.add(	"Pilkkaniska", loggedIn);
//        birds.add(	"Pilkkasiipi", loggedIn);
//        birds.add(	"Punakaulahanhi", loggedIn);
//        birds.add(	"Punapäänarsku", loggedIn);
//        birds.add(	"Punasotka");
//        birds.add(	"Punasotka");
//        birds.add(	"Punasotka");
//        birds.add(	"Ristisorsa");
//        birds.add(	"Ruostesorsa");
//        birds.add(	"Ruskosotka");
//        birds.add(	"Sepelhanhi");
//        birds.add(	"Sinisiipitavi");
//        birds.add(	"Sinisorsa");
//        birds.add(	"Tavi");
//        birds.add(	"Telkkä");
//        birds.add(	"Tiibetinhanhi");
//        birds.add(	"Tukkakoskelo");
//        birds.add(	"Tukkasotka");
//        birds.add(	"Tundrahanhi");
//        birds.add(	"Uivelo");
//        birds.add(	"Valkoposkihanhi");
//        birds.add(	"Virta-alli");



        
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
//            return new ArrayList<>();
            return birds;
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
