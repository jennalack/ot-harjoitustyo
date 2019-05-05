
package birdapp.dao;

import java.util.*;
import birdapp.domain.Birdapp;
import birdapp.domain.User;

public interface BirdappDao {
    
    Birdapp create(Birdapp birdapp) throws Exception;
    
    ArrayList<Boolean> getListOfChecks();
            
    List<Boolean> findByUsername(User username);
    
    List<Birdapp> getAll();
  
}
