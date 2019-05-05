package birdapp.dao;


import java.io.File;
import java.io.FileWriter;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;
import birdapp.domain.TestUserDao;
import birdapp.domain.Birdapp;
import birdapp.domain.User;
import java.util.ArrayList;



public class FileBirdappDaoTest {
    
         @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();    
  
    File userFile;  
    BirdappDao dao;    
    
    @Before
    public void setUp() throws Exception {
        userFile = tempFolder.newFile("users_test.txt");  
        UserDao userDao = new TestUserDao();
        userDao.create(new User("test", "Ted Tester"));
        
        try (FileWriter file = new FileWriter(userFile.getAbsolutePath())) {
            file.write("test;[true, false, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, false, false, false, false, false]\n");
        }
        
    } 
    
    @After
    public void tearDown() {
        userFile.delete();
    }
}

