
package birdapp.dao;

import java.util.*;
import birdapp.domain.Birdapp;

public interface BirdappDao {
    
    Birdapp create(Birdapp birdapp) throws Exception;
    
    List<Birdapp> getAll();
  
    void setDone(int id) throws Exception;
}
