
package birdapp.ui;

import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.*;
import javafx.scene.layout.GridPane;
import birdapp.dao.FileUserDao;
//import birdapp.dao.FileBirdappDao;


public class BirdappUi extends Application {
    
//    @Override
//    public void init() throws Exception{
//        Properties properties = new Properties();
//        properties.load(new FileInputStream("config.properties"));
//        String userFile = properties.getProperty("userFile");
//        String birdappFile = properties.getProperty("birdappFile");
//        
//        FileUserDao userDao = new FileUserDao(userFile);
//        FileBirdappDao birdappDao = new FileBirdappDao(birdappFile, userDao);
//        birdappService=new BirdappService(birdappDao, userDao);
//    }
    
    @Override
    public void start(Stage window) {
   
        TextField logintext = new TextField();
        TextField newuser = new TextField();
        Label user = new Label("Username: ");
        Label newusertext = new Label("Write your username: ");
        BorderPane lay = new BorderPane();
        Button button = new Button("Login");
        Button create = new Button("Create new user");
        
        GridPane componentgroup2 = new GridPane();
        componentgroup2.add(newusertext, 0, 0);
        componentgroup2.add(newuser, 1, 0);
        componentgroup2.add(create, 1, 1);
        
        GridPane componentgroup = new GridPane();
        componentgroup.add(user, 0, 0);
        componentgroup.add(logintext, 1, 0);
        componentgroup.add(button, 1, 2);
        componentgroup.add(create, 1, 3);
        
        componentgroup.setHgap(10);
        componentgroup.setVgap(10);
        componentgroup.setPadding(new Insets(10, 10, 10, 10));
        
        Button test = new Button("Test");
        
        Scene view = new Scene(componentgroup);
        Scene birds = new Scene(test);
        
        button.setOnAction((event) -> {
            window.setScene(birds);
        });
        
        create.setOnAction((event) -> {
            window.setScene(new Scene(componentgroup2));
        });
        
        
//        VBox componentgroup = new VBox();
//        componentgroup.setSpacing(100);
//        componentgroup.getChildren().addAll(logintext, button);
        
     
//        lay.setCenter(button);
//        lay.setTop(logintext);
        
//        button.setOnAction((event) -> {
//            System.out.println("Pushed!");
//        });
//        
       
        
        window.setTitle("Login");
        window.setScene(view);
        window.show();
    }
    
    public static void main(String[] args) {
        launch(BirdappUi.class);
    }
    
}
