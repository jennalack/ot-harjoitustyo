
package birdapp.dao;

    import java.io.File;
    import java.io.FileWriter;
    import java.util.ArrayList;
    import java.util.List;
    import java.util.Scanner;
    import birdapp.domain.User;
    import birdapp.domain.Birdapp;

public class FileBirdappDao implements BirdappDao {
    public List<Birdapp> birds;
    private String file;
    
    public FileBirdappDao(String file, UserDao users) throws Exception {
        birds = new ArrayList<>();
        this.file = file;
        try {
            Scanner reader = new Scanner(new File(file));
            while (reader.hasNextLine()) {
                String[] parts = reader.nextLine().split(";");
                int id = Integer.parseInt(parts[0]);
                boolean done = Boolean.parseBoolean(parts[2]);
                User user = users.getAll().stream().filter(u->u.getUsername().equals(parts[3])).findFirst().orElse(null);
                Birdapp birdapp = new Birdapp(id, parts[1], done, user);
                birds.add(birdapp);
            }
        } catch (Exception e) {
            FileWriter writer = new FileWriter(new File(file));
            writer.close();
        }
    }
    
    private void save() throws Exception {
        try (FileWriter writer = new FileWriter(new File(file))) {
            for (Birdapp bird : birds) {
                writer.write(bird.getId() + ";" + bird.getContent() + ";" + bird.isDone() + ";" + bird.getUser().getUsername() + "\n");
            }
        }
    }
    
    private int generateId() {
        return birds.size() + 1;
    }
    
    @Override
    public List<Birdapp> getAll() {
        return birds;
    }
    
    
    
    @Override
    public Birdapp create(Birdapp birdapp) throws Exception {
        birdapp.setId(generateId());
        birds.add(birdapp);
        save();
        return birdapp;
    }
    
    @Override
    public void setDone(int id) throws Exception {
        for (Birdapp b:birds) {
            if (b.getId() == id) {
                b.setDone();
            }
        }
        save();
    }
}
