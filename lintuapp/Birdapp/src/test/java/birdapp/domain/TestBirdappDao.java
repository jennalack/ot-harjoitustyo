
package birdapp.domain;

import java.util.ArrayList;
import java.util.List;
import birdapp.dao.BirdappDao;

public class TestBirdappDao implements BirdappDao{
    ArrayList<Birdapp> checksAndUser;
    ArrayList<Boolean> checks;

    public TestBirdappDao() {
        checksAndUser = new ArrayList<>();
        checks = new ArrayList<>();
    }   
   
    @Override
    public List<Birdapp> getAll() {
        return checksAndUser;
    }
    
    @Override
    public ArrayList<Boolean> getListOfChecks() {
        return checks;
    }
    
    @Override
    public List<Boolean> findByUsername(User username) {
        return new ArrayList<Boolean>();
    }
    
    @Override
    public Birdapp create(Birdapp birdapp) {
        checksAndUser.add(birdapp);
        return birdapp;
    }   

}
