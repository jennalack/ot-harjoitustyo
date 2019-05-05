
package birdapp.dao;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import birdapp.domain.User;
import birdapp.domain.Birdapp;
import birdapp.ui.BirdappUi;
import birdapp.domain.BirdappService;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.ObjectInputStream;
import java.util.Arrays;
import java.util.HashMap;

public class FileBirdappDao implements BirdappDao {
    
    /**
    * Lista käyttäjän ja checkboxien totuusarvojen tallentamiseen
    */
    public List<Birdapp> birds;
    private String file;
    /**
    * Lista, johon tallennetaan tekstitiedostosta checboxien totuusarvot
    */
    public ArrayList<Boolean> fin = new ArrayList<>();
    private String last, line;

    public FileBirdappDao(String file, UserDao users) throws Exception {
        birds = new ArrayList<>();
        this.file = file;
        try {
            Scanner reader = new Scanner(new File(file));
            BufferedReader input = new BufferedReader(new FileReader(file));
            while ((line = input.readLine()) != null) { 
                last = line;
            }
            
            String[] parts = last.split(";");
            User user = users.getAll().stream().filter(u->u.getUsername().equals(parts[0])).findFirst().orElse(null); 
            String listFromFile = parts[1];
            String replace = listFromFile.replace("[", "");
            String replace2 = replace.replace("]", "");
            String replace3 = replace2.replace(" ", "");
            List<String> tempList = new ArrayList<String>(Arrays.asList(replace3.split(",")));
            for (int i = 0; i < tempList.size(); i++) {
                Boolean add = Boolean.parseBoolean(tempList.get(i));
                fin.add(add);
            }
            Birdapp birdapp = new Birdapp(fin, user);
            birds.add(birdapp);
        } catch (Exception e) {
            FileWriter writer = new FileWriter(new File(file));
            writer.close();
        }
    }
    
   
    private void save() throws Exception {
        
        try (FileWriter writer = new FileWriter(new File(file))) {
            for (Birdapp bird : birds) {
                writer.write(bird.getUser().getUsername() + ";" + bird.getSelected() + "\n");
            }
        }
    }
    
    @Override
    public ArrayList<Boolean> getListOfChecks() {
        return this.fin;
    }
    
    @Override
    public List<Birdapp> getAll() {
        return birds;
    }
    
    @Override
    public List<Boolean> findByUsername(User username) {
        return new ArrayList<Boolean>();
    }
    
   
    @Override
    public Birdapp create(Birdapp birdapp) throws Exception {
        birds.add(birdapp);
        save();
        return birdapp;
    }


}
