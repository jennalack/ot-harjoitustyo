
package birdapp.domain;

/**
 * Järjestelmän käyttäjää edustava luokka 
 */

public class User {
    private String name;
    private String username;
    
    public User(String username, String name) {
        this.name = name;
        this.username = username;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getUsername() {
        return this.username;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof User)) {
            return false;
        }
        User other = (User) obj;
        return username.equals(other.username);
    }
}
