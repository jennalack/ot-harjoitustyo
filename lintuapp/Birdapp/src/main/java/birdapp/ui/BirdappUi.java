
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
import birdapp.dao.FileBirdappDao;
import birdapp.domain.BirdappService;
import birdapp.domain.Birdapp;


public class BirdappUi extends Application {
    
    private BirdappService birdService;
    
    private Scene birdScene;
    private Scene newUserScene;
    private Scene loginScene;
    
    private VBox birdnodes;
    private Label menuLabel = new Label();
    
    
    @Override
    public void init() throws Exception{
        Properties properties = new Properties();
        properties.load(new FileInputStream("config.properties"));
        String userFile = properties.getProperty("userFile");
        String birdFile = properties.getProperty("birdFile");
        
        FileUserDao userDao = new FileUserDao(userFile);
        FileBirdappDao birdDao = new FileBirdappDao(birdFile, userDao);
        birdService = new BirdappService(birdDao, userDao);
    }
//    
    public Node createBirdNode(Birdapp birdapp){
        HBox box = new HBox(10);
        Label label = new Label(birdapp.getContent());
        label.setMinHeight(28);
        Button button = new Button("done");
        button.setOnAction(e->{birdService.markDone(birdapp.getId());
        redrawBirdapp();
        });
        
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        box.setPadding(new Insets(0,5,0,5));
        
        box.getChildren().addAll(label, spacer, button);
        return box;
    }
    
    public void redrawBirdapp() {
        birdnodes.getChildren().clear();
        
        List<Birdapp> unseenBirds = birdService.getUndone();
        unseenBirds.forEach(bird ->{birdnodes.getChildren().add(createBirdNode(bird));
        });
        
    }
    
    @Override
    public void start(Stage primaryStage){
        VBox loginPane = new VBox(10);
        HBox inputPane = new HBox(10);
        loginPane.setPadding(new Insets(10));
        Label loginLabel = new Label("username");
        TextField usernameInput = new TextField();
        
        inputPane.getChildren().addAll(loginLabel, usernameInput);
        Label loginMessage = new Label();
        
        Button loginButton = new Button("login");
        Button createButton = new Button("Create new user");
        loginButton.setOnAction(e->{
            String username = usernameInput.getText();
            menuLabel.setText(username + " logged in...");
            if(birdService.login(username)){
                loginMessage.setText("");
//                redrawBirdapp();
                primaryStage.setScene(birdScene);
                usernameInput.setText("");
            } else {
                loginMessage.setText("user does not exist");
                loginMessage.setTextFill(Color.RED);
            }
        });
        
        createButton.setOnAction(e-> {
            usernameInput.setText("");
            primaryStage.setScene(newUserScene);
        });
        
        loginPane.getChildren().addAll(loginMessage, inputPane, loginButton, createButton);
        loginScene = new Scene(loginPane, 300, 250);
        
        VBox newUserPane = new VBox(10);
        
        HBox newUsernamePane = new HBox(10);
        newUsernamePane.setPadding(new Insets(10));
        TextField newUsernameInput = new TextField();
        Label newUsernameLabel = new Label("Username");
        newUsernameLabel.setPrefWidth(100);
        newUsernamePane.getChildren().addAll(newUsernameLabel, newUsernameInput);
        
        HBox newNamePane = new HBox(10);
        newNamePane.setPadding(new Insets(10));
        TextField newNameInput = new TextField();
        Label newNameLabel = new Label("Name");
        newNameLabel.setPrefWidth(100);
        newNamePane.getChildren().addAll(newNameLabel, newNameInput);
        
        Label userCreationMessage = new Label();
        
        Button createNewUserButton = new Button("Create");
        createNewUserButton.setPadding(new Insets(10));
        createNewUserButton.setOnAction(e->{
            String username=newUsernameInput.getText();
            String name = newNameInput.getText();
            
            if(username.length()==2 || name.length()<2) {
                userCreationMessage.setText("Username or name too short!");
                userCreationMessage.setTextFill(Color.RED);
            } else if (birdService.createUser(username, name)) {
                userCreationMessage.setText("");
                loginMessage.setText("New user created!");
                loginMessage.setTextFill(Color.CORAL);
                primaryStage.setScene(loginScene);
            } else {
                userCreationMessage.setText("username has to be unique");
                userCreationMessage.setTextFill(Color.RED);
            }
        });
        
        newUserPane.getChildren().addAll(userCreationMessage, newUsernamePane, newNamePane, createNewUserButton);
        newUserScene = new Scene(newUserPane,300,250);
        
        ScrollPane birdScrollbar = new ScrollPane();
        BorderPane mainPane = new BorderPane(birdScrollbar);
        birdScene = new Scene(mainPane,300,250);
        
        HBox menuPane = new HBox(10);
        Region menuSpacer = new Region();
        HBox.setHgrow(menuSpacer, Priority.ALWAYS);
        Button logoutButton = new Button("logout");
        menuPane.getChildren().addAll(menuLabel, menuSpacer, logoutButton);
        logoutButton.setOnAction(e->{
            birdService.logout();
            primaryStage.setScene(loginScene);
        });
        
        HBox createForm = new HBox(10);
        Button createBird = new Button("create");
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        TextField newBirdInput = new TextField();
        createForm.getChildren().addAll(newBirdInput, spacer, createBird);
        
        birdnodes = new VBox(10);
        birdnodes.setMaxWidth(280);
        birdnodes.setMinWidth(280);
        redrawBirdapp();
        
        birdScrollbar.setContent(birdnodes);
        mainPane.setBottom(createForm);
        mainPane.setTop(menuPane);
        
        createBird.setOnAction(e->{
            birdService.createBirdapp(newBirdInput.getText());
            newBirdInput.setText("");
            redrawBirdapp();
        });
        
        primaryStage.setTitle("Birds");
        primaryStage.setScene(loginScene);
        primaryStage.show();
        primaryStage.setOnCloseRequest(e-> {
            System.out.println("closing");
            System.out.println(birdService.getLoggedUser());
            if(birdService.getLoggedUser()!=null){
                e.consume();
            }
        });
    }
    
    @Override
    public void stop(){
        System.out.println("Sovellus sulkeutuu");
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}

    
    
    //Oma tekemä, Käytä tätä kun toimii!
//    @Override
//    public void start(Stage window) {
//   
//        TextField logintext = new TextField();
//        TextField newuser = new TextField();
//        Label user = new Label("Username: ");
//        Label newusertext = new Label("Write your username: ");
//        BorderPane lay = new BorderPane();
//        Button button = new Button("Login");
//        Button create = new Button("Create new user");
//        
//        GridPane componentgroup2 = new GridPane();
//        componentgroup2.add(newusertext, 0, 0);
//        componentgroup2.add(newuser, 1, 0);
//        componentgroup2.add(create, 1, 1);
//        
//        GridPane componentgroup = new GridPane();
//        componentgroup.add(user, 0, 0);
//        componentgroup.add(logintext, 1, 0);
//        componentgroup.add(button, 1, 2);
//        componentgroup.add(create, 1, 3);
//        
//        componentgroup.setHgap(10);
//        componentgroup.setVgap(10);
//        componentgroup.setPadding(new Insets(10, 10, 10, 10));
//        
//        Button test = new Button("Test");
//        
//        Scene view = new Scene(componentgroup);
//        Scene birds = new Scene(test);
//        
//        button.setOnAction((event) -> {
//            window.setScene(birds);
//        });
//        
//        create.setOnAction((event) -> {
//            window.setScene(new Scene(componentgroup2));
//        });
        
        
    
    //TÄMÄ HARJOITTELUA; EI TÄTÄ LOPULLISEEN!
//        VBox componentgroup = new VBox();
//        componentgroup.setSpacing(100);
//        componentgroup.getChildren().addAll(logintext, button);
        
     
//        lay.setCenter(button);
//        lay.setTop(logintext);
        
//        button.setOnAction((event) -> {
//            System.out.println("Pushed!");
//        });
//        
       
        //NÄMÄ LOPULLISEEN!
//        window.setTitle("Login");
//        window.setScene(view);
//        window.show();
//    }
//    
//    public static void main(String[] args) {
//        launch(BirdappUi.class);
//    }
    
//}
