
package birdapp.domain;

import java.util.*;
import birdapp.dao.BirdappDao;
import birdapp.dao.UserDao;
import java.util.stream.Collectors;

public class BirdappService {
    private BirdappDao birdDao;
    private UserDao userDao;
    private User loggedIn;
    private List<String> birds;
    
    public BirdappService(BirdappDao birdDao, UserDao userDao) {
        this.birdDao=birdDao;
        this.userDao=userDao;
        birds = new ArrayList<>();
        birds.add("Alli");
        birds.add("Allihaahka");
        birds.add("Amerikanhaapana");
        birds.add("Amerikanmustalintu");
        birds.add(	"Amerikantavi");
        birds.add(	"Amerikantukkasotka");
        birds.add(	"Haahka");
        birds.add(	"Haapana");
        birds.add(	"Harmaasorsa");
        birds.add(	"Heinätavi");
        birds.add(	"Isokoskelo");
        birds.add(	"Jouhisorsa");
        birds.add(	"Kanadanhanhi");
        birds.add(	"Kiljuhanhi");
        birds.add(	"Kuparisorsa");
        birds.add(	"Kyhmyhaahka");
        birds.add(	"Kyhmyjoutsen");
        birds.add(	"Kyhmypilkkasiipi");
        birds.add(	"Lapasorsa");
        birds.add(	"Lapasotka");
        birds.add(	"Laulujoutsen");
        birds.add(	"Lumihanhi");
        birds.add(	"Lyhytnokkahanhi");
        birds.add(	"Mandariinisorsa");
        birds.add(	"Merihanhi");
        birds.add(	"Metsähanhi");
        birds.add(	"Mustalintu");
        birds.add(	"Nokisorsa");
        birds.add(	"Pikkujoutsen");
        birds.add(	"Pikkukanadanhanhi");
        birds.add(	"Pikkulapasotka");
        birds.add(	"Pilkkaniska");
        birds.add(	"Pilkkasiipi");
        birds.add(	"Punakaulahanhi");
        birds.add(	"Punapäänarsku");
        birds.add(	"Punasotka");
        birds.add(	"Ristisorsa");
        birds.add(	"Ruostesorsa");
        birds.add(	"Ruskosotka");
        birds.add(	"Sepelhanhi");
        birds.add(	"Sinisiipitavi");
        birds.add(	"Sinisorsa");
        birds.add(	"Tavi");
        birds.add(	"Telkkä");
        birds.add(	"Tiibetinhanhi");
        birds.add(	"Tukkakoskelo");
        birds.add(	"Tukkasotka");
        birds.add(	"Tundrahanhi");
        birds.add(	"Uivelo");
        birds.add(	"Valkoposkihanhi");
        birds.add(	"Virta-alli");



        
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
