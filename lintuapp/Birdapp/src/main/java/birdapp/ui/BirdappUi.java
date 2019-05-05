
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
import java.util.*;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.util.Callback;


public class BirdappUi extends Application {
    
    private BirdappService birdService;
    private Scene birdScene;
    private Scene newUserScene;
    private Scene loginScene;
    
    private VBox birdnodes;
    private Label menuLabel = new Label();
    private ArrayList<CheckBox> boxes = new ArrayList<>();
    
    /**
    * Lista checboxien totuusarvojen tallentamiseen
    */
    public ArrayList<Boolean> selected = new ArrayList<>();
    
    /**
    * Lista, johon palautetaan tekstitiedostosta totuusarvot
    */
    public ArrayList<Boolean> returned = new ArrayList<>();
    
    public BirdappUi() {
        CheckBox box1 = new CheckBox("Kyhmyjoutsen");
        CheckBox box2 = new CheckBox("Pikkujoutsen");
        CheckBox box3 = new CheckBox("Laulujoutsen");
        CheckBox box4 = new CheckBox("Metsähanhi");
        CheckBox box5 = new CheckBox("Lyhytnokkahanhi");
        CheckBox box6 = new CheckBox("Tundrahanhi");
        CheckBox box7 = new CheckBox("Kiljuhanhi");
        CheckBox box8 = new CheckBox("Merihanhi");
        CheckBox box9 = new CheckBox("Lumihanhi");
        CheckBox box10 = new CheckBox("Tiibetinhanhi");
        CheckBox box12 = new CheckBox("Kanadanhanhi");
        CheckBox box13 = new CheckBox("Valkoposkihanhi");
        CheckBox box14 = new CheckBox("Sepelhanhi");
        CheckBox box15 = new CheckBox("Punakaulahanhi");
        CheckBox box16 = new CheckBox("Pikkukanadanhanhi");
        CheckBox box17 = new CheckBox("Ruostesorsa");
        CheckBox box18 = new CheckBox("Ristisorsa");
        CheckBox box19 = new CheckBox("Mandariinisorsa");
        CheckBox box20 = new CheckBox("Haapana");
        CheckBox box21 = new CheckBox("Pyy");
        CheckBox box22 = new CheckBox("Riekko");
        CheckBox box23 = new CheckBox("Kiiruna");
        CheckBox box24 = new CheckBox("Teeri");
        CheckBox box25 = new CheckBox("Metso");
        CheckBox box26 = new CheckBox("Peltopyy");
        CheckBox box27 = new CheckBox("Viiriäinen");
        CheckBox box28 = new CheckBox("Fasaani");
        CheckBox box29 = new CheckBox("Kaakkuri");
        CheckBox box30 = new CheckBox("Kuikka");
        CheckBox box31 = new CheckBox("Tundrakuikka");
        CheckBox box32 = new CheckBox("Amerikanjääkuikka");
        CheckBox box33 = new CheckBox("Jääkuikka");
        CheckBox box34 = new CheckBox("Pikku-uikku");
        CheckBox box35 = new CheckBox("Silkkiuikku");
        CheckBox box36 = new CheckBox("Härkälintu");
        CheckBox box37 = new CheckBox("Mustakurkku-uikku");
        CheckBox box38 = new CheckBox("Mustakaulauikku");
        CheckBox box39 = new CheckBox("Myrskylintu");
        CheckBox box40 = new CheckBox("Nokiliitäjä");
        
        boxes.add(box1);
        boxes.add(box2);
        boxes.add(box3);
        boxes.add(box4);
        boxes.add(box5);
        boxes.add(box6);
        boxes.add(box7);
        boxes.add(box8);
        boxes.add(box9);
        boxes.add(box10);
        boxes.add(box12);
        boxes.add(box13);
        boxes.add(box14);
        boxes.add(box15);
        boxes.add(box16);
        boxes.add(box17);
        boxes.add(box18);
        boxes.add(box19);
        boxes.add(box20);
        boxes.add(box21);
        boxes.add(box22);
        boxes.add(box23);
        boxes.add(box24);
        boxes.add(box25);
        boxes.add(box26);
        boxes.add(box27);
        boxes.add(box28);
        boxes.add(box29);
        boxes.add(box30);
        boxes.add(box31);
        boxes.add(box32);
        boxes.add(box33);
        boxes.add(box34);
        boxes.add(box35);
        boxes.add(box36);
        boxes.add(box37);
        boxes.add(box38);
        boxes.add(box39);
        boxes.add(box40);
    }
    
    
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


    
    public ArrayList<Boolean> checkIfChecked() {
        selected.clear();
        for(CheckBox box : boxes) {
                if(box.isSelected()) {
                    selected.add(true);
                } else {
                    selected.add(false);
                }
            }
        return selected;
    }
     
    @Override
    public void start(Stage primaryStage){
        
        VBox loginPane = new VBox(10);
        HBox inputPane = new HBox(10);
        loginPane.setPadding(new Insets(10));
        Label loginLabel = new Label("Username");
        TextField usernameInput = new TextField();
        
        inputPane.getChildren().addAll(loginLabel, usernameInput);
        Label loginMessage = new Label();
        
        Button loginButton = new Button("Login");
        Button createButton = new Button("Create new user");
        loginButton.setOnAction(e->{
            String username = usernameInput.getText();
            if(!(selected.isEmpty())) {
                int i=0;
                for(Boolean select : selected) {
                    if(select == true) {
                        boxes.get(i).setSelected(true);
                    }
                    i++;
                }
            } else {
                returned = birdService.getChecked();
                int i=0;
                for(Boolean returned : returned) {
                    if(returned == true) {
                        boxes.get(i).setSelected(true);
                    }
                    i++;
                }
            }
            menuLabel.setText(username + " logged in...");
            if(birdService.login(username)){
                loginMessage.setText("");
                primaryStage.setScene(birdScene);
                usernameInput.setText("");
            } else {
                loginMessage.setText("User does not exist");
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
            
            if(username.length() < 5 || name.length() < 2) {
                userCreationMessage.setText("Username or name too short!");
                userCreationMessage.setTextFill(Color.RED);
            } else if (birdService.createUser(username, name)) {
                userCreationMessage.setText("");
                loginMessage.setText("New user created successfully!");
                loginMessage.setTextFill(Color.CORAL);
                primaryStage.setScene(loginScene);
            } else {
                userCreationMessage.setText("Username is already in use");
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
            checkIfChecked();
            birdService.saveChecks(selected);
            birdService.logout();
            primaryStage.setScene(loginScene);
        });
        
        HBox createForm = new HBox(10);
        Button createBird = new Button("create");
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        TextField newBirdInput = new TextField();
        createForm.getChildren().addAll(newBirdInput, spacer, createBird);
        
        birdnodes = new VBox();
        birdnodes.setMaxWidth(280);
        birdnodes.setMinWidth(280);
        
       
        VBox birdSelection = new VBox();
        birdSelection.setSpacing(10);
        birdSelection.getChildren().addAll(boxes); 
 
        birdScrollbar.setContent(birdSelection);
        mainPane.setTop(menuPane);
        
        primaryStage.setTitle("Bird observations");
        primaryStage.setScene(loginScene);
        primaryStage.show();
        primaryStage.setOnCloseRequest(e-> {
            System.out.println("Closing");
            System.out.println(birdService.getLoggedUser());
            if(birdService.getLoggedUser()!=null){
                e.consume();
            }
        });
    }
    
    @Override
    public void stop(){
        System.out.println("Application closes");
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}

    
  