
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
import birdapp.domain.User;

public class FileUserDaoTest {
    
    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();
    
    File userFile;  
    UserDao dao;
    
    @Before
    public void setUp() throws Exception {
        userFile = testFolder.newFile("users_test.txt");  
        
        try (FileWriter file = new FileWriter(userFile.getAbsolutePath())) {
            file.write("test;Ted Tester\n");
        }
        
        dao = new FileUserDao(userFile.getAbsolutePath());
    }
   
    @Test
    public void usersCanBeReadFromGivenFile() {
        List<User> users = dao.getAll();
        assertEquals(1, users.size());
        User user = users.get(0);
        assertEquals("Ted Tester", user.getName());
        assertEquals("test", user.getUsername());
    }
    
    @Test
    public void existingUserCanBeFoundFromFile() {
        User user = dao.findByUsername("test");
        assertEquals("Ted Tester", user.getName());
        assertEquals("test", user.getUsername());
    }
    
    @Test
    public void nonExistingUserCannotBeFoundFromFile() {
        User user = dao.findByUsername("jennana");
        assertEquals(null, user);
    }
  
    @Test
    public void createdUserIsFoundFromFile() throws Exception {
        User newUser = new User("lackbje", "Jenna Lackbergh");
        dao.create(newUser);
        
        User user = dao.findByUsername("lackbje");
        assertEquals("Jenna Lackbergh", user.getName());
        assertEquals("lackbje", user.getUsername());
    }
    
    @After
    public void deleteFile() {
        userFile.delete();
    }
}
