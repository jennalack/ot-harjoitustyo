package birdapp.domain;

import java.util.ArrayList;
import java.util.List;
import birdapp.dao.UserDao;

public class TestUserDao implements UserDao {
    
    List<User> users = new ArrayList<>();

    public TestUserDao() {
        users.add(new User("test", "Ted Tester"));
    }
//    
    @Override
    public User findByUsername(String username) {
        return users.stream().filter(u->u.getUsername().equals(username)).findFirst().orElse(null);
    }
//    
    @Override
    public User create(User user) {
        users.add(user);
        return user;
    } 
//    
    @Override
    public List<User> getAll() {
        return users;
    }
}
