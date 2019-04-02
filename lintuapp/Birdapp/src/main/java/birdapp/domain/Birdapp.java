
package birdapp.domain;


public class Birdapp {
    private int id;
    private String content;
    private boolean done;
    private User user;
    
    public Birdapp(int id, String content, boolean done, User user) {
        this.id=id;
        this.content=content;
        this.done=done;
        this.user=user;
    }
    
    public Birdapp(String content, User user) {
        this.content=content;
        this.user=user;
        this.done=false;
    }
    
    public void setId(int id) {
        this.id=id;
    }
    
    public String getContent() {
        return content;
    }
    
    public User getUser() {
        return user;
    }
    
    public int getId() {
        return id;
    }
    
    public boolean isDone() {
        return done;
    }
    
    public void setDone() {
        done=true;
    }
    
    @Override
    public boolean equals(Object obj){
        if(!(obj instanceof Birdapp)) {
            return false;
        }
        Birdapp other = (Birdapp) obj;
        return id == other.id;
    }
    
}
