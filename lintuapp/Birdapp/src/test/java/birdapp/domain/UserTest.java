
package birdapp.domain;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class UserTest {
    
    @Test
    public void sameWhenUsernamesSame() {
        User u1 = new User("test", "Ted Tester");
        User u2 = new User("test", "Ted Tester");
        assertTrue(u1.equals(u2));
    }
    
    @Test
    public void differentWhenUsernamesDiffer() {
        User u1 = new User("test", "Ted Tester");
        User u2 = new User("lackbje", "Jenna Lackbergh");
        assertFalse(u1.equals(u2));
    } 
    
    @Test
    public void differentWhenTypesDiffer() {
        User u = new User("test", "Ted Tester");
        Object o = new Object();
        assertFalse(u.equals(o));
    }     
}
