
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
    
    

//    private BirdappService birdService;
//    
//    private Scene birdScene;
//    private Scene newUserScene;
//    private Scene loginScene;
//    
//    private VBox birdNodes;
//    private Label menuLabel = new Label();
//    
//    @Override
//    public void init() throws Exception {
//        Properties properties = new Properties();
//
//        properties.load(new FileInputStream("config.properties"));
//        
//        String userFile = properties.getProperty("userFile");
//        String birdFile = properties.getProperty("birdFile");
//            
//        FileUserDao userDao = new FileUserDao(userFile);
//        FileBirdappDao birdDao = new FileBirdappDao(birdFile, userDao);
//        birdService = new BirdappService(birdDao, userDao);
//    }
//    
//    public Node createTodoNode(Birdapp birdapp) {
//        HBox box = new HBox(10);
//        Label label  = new Label(birdapp.getContent());
//        label.setMinHeight(28);
//        Button button = new Button("done");
//        button.setOnAction(e->{
//            birdService.markDone(birdapp.getId());
//            redrawBirdlist();
//        });
//                
//        Region spacer = new Region();
//        HBox.setHgrow(spacer, Priority.ALWAYS);
//        box.setPadding(new Insets(0,5,0,5));
//        
//        box.getChildren().addAll(label, spacer, button);
//        return box;
//    }
//    
//    public void redrawBirdlist() {
//        birdNodes.getChildren().clear();     
//
//        List<Birdapp> undoneTodos = birdService.getUndone();
//        undoneTodos.forEach(bird->{
//            birdNodes.getChildren().add(createTodoNode(bird));
//        });     
//    }
//    
//    @Override
//    public void start(Stage primaryStage) {               
//        // login scene
//        
//        VBox loginPane = new VBox(10);
//        HBox inputPane = new HBox(10);
//        loginPane.setPadding(new Insets(10));
//        Label loginLabel = new Label("username");
//        TextField usernameInput = new TextField();
//        
//        inputPane.getChildren().addAll(loginLabel, usernameInput);
//        Label loginMessage = new Label();
//        
//        Button loginButton = new Button("login");
//        Button createButton = new Button("create new user");
//        loginButton.setOnAction(e->{
//            String username = usernameInput.getText();
//            menuLabel.setText(username + " logged in...");
//            if ( birdService.login(username) ){
//                loginMessage.setText("");
//                redrawBirdlist();
//                primaryStage.setScene(birdScene);  
//                usernameInput.setText("");
//            } else {
//                loginMessage.setText("use does not exist");
//                loginMessage.setTextFill(Color.RED);
//            }      
//        });  
//        
//        createButton.setOnAction(e->{
//            usernameInput.setText("");
//            primaryStage.setScene(newUserScene);   
//        });  
//        
//        loginPane.getChildren().addAll(loginMessage, inputPane, loginButton, createButton);       
//        
//        loginScene = new Scene(loginPane, 300, 250);    
//   
//        // new createNewUserScene
//        
//        VBox newUserPane = new VBox(10);
//        
//        HBox newUsernamePane = new HBox(10);
//        newUsernamePane.setPadding(new Insets(10));
//        TextField newUsernameInput = new TextField(); 
//        Label newUsernameLabel = new Label("username");
//        newUsernameLabel.setPrefWidth(100);
//        newUsernamePane.getChildren().addAll(newUsernameLabel, newUsernameInput);
//     
//        HBox newNamePane = new HBox(10);
//        newNamePane.setPadding(new Insets(10));
//        TextField newNameInput = new TextField();
//        Label newNameLabel = new Label("name");
//        newNameLabel.setPrefWidth(100);
//        newNamePane.getChildren().addAll(newNameLabel, newNameInput);        
//        
//        Label userCreationMessage = new Label();
//        
//        Button createNewUserButton = new Button("create");
//        createNewUserButton.setPadding(new Insets(10));
//
//        createNewUserButton.setOnAction(e->{
//            String username = newUsernameInput.getText();
//            String name = newNameInput.getText();
//   
//            if ( username.length()==2 || name.length()<2 ) {
//                userCreationMessage.setText("username or name too short");
//                userCreationMessage.setTextFill(Color.RED);              
//            } else if ( birdService.createUser(username, name) ){
//                userCreationMessage.setText("");                
//                loginMessage.setText("new user created");
//                loginMessage.setTextFill(Color.GREEN);
//                primaryStage.setScene(loginScene);      
//            } else {
//                userCreationMessage.setText("username has to be unique");
//                userCreationMessage.setTextFill(Color.RED);        
//            }
// 
//        });  
//        
//        newUserPane.getChildren().addAll(userCreationMessage, newUsernamePane, newNamePane, createNewUserButton); 
//       
//        newUserScene = new Scene(newUserPane, 300, 250);
//        
//        // main scene
//        
//        ScrollPane todoScollbar = new ScrollPane();       
//        BorderPane mainPane = new BorderPane(todoScollbar);
//        birdScene = new Scene(mainPane, 300, 250);
//                
//        HBox menuPane = new HBox(10);    
//        Region menuSpacer = new Region();
//        HBox.setHgrow(menuSpacer, Priority.ALWAYS);
//        Button logoutButton = new Button("logout");      
//        menuPane.getChildren().addAll(menuLabel, menuSpacer, logoutButton);
//        logoutButton.setOnAction(e->{
//            birdService.logout();
//            primaryStage.setScene(loginScene);
//        });        
//        
//        HBox createForm = new HBox(10);    
//        Button createTodo = new Button("create");
//        Region spacer = new Region();
//        HBox.setHgrow(spacer, Priority.ALWAYS);
//        TextField newTodoInput = new TextField();
//        createForm.getChildren().addAll(newTodoInput, spacer, createTodo);
//        
//        birdNodes = new VBox(10);
//        birdNodes.setMaxWidth(280);
//        birdNodes.setMinWidth(280);
//        redrawBirdlist();
//        
//        todoScollbar.setContent(birdNodes);
//        mainPane.setBottom(createForm);
//        mainPane.setTop(menuPane);
//        
//        createTodo.setOnAction(e->{
//            birdService.createBirdapp(newTodoInput.getText());
//            newTodoInput.setText("");       
//            redrawBirdlist();
//        });
//        
//        // seutp primary stage
//        
//        primaryStage.setTitle("Todos");
//        primaryStage.setScene(loginScene);
//        primaryStage.show();
//        primaryStage.setOnCloseRequest(e->{
//            System.out.println("closing");
//            System.out.println(birdService.getLoggedUser());
//            if (birdService.getLoggedUser()!=null) {
//                e.consume();   
//            }
//            
//        });
//    }
//
//    @Override
//    public void stop() {
//      // tee lopetustoimenpiteet täällä
//      System.out.println("sovellus sulkeutuu");
//    }    
//    
//    public static void main(String[] args) {
//        launch(args);
//    }
//    
//    
//}
    
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
        CheckBox cb = new CheckBox();
        HBox box = new HBox(cb);
        Label label = new Label(birdapp.getContent());
        label.setMinHeight(28);
        
//        ObservableList<String> birds = FXCollections.observableArrayList(
//          "Alli", "Haapana", "Haahka");
//        ListView<String> lv = new ListView<>(birds);
//        lv.setCellFactory(CheckBoxListCell.forListView(item -> i));
//        VBox.setVgrow(lv, Priority.ALWAYS);
//
//        box.getChildren().addAll(cb, label, lv);
//        showTwitterListSelection();
        
        Button button = new Button("Seen");
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
        Label loginLabel = new Label("Username");
        TextField usernameInput = new TextField();
        
        inputPane.getChildren().addAll(loginLabel, usernameInput);
        Label loginMessage = new Label();
        
        Button loginButton = new Button("Login");
        Button createButton = new Button("Create new user");
        loginButton.setOnAction(e->{
            String username = usernameInput.getText();
            menuLabel.setText(username + " logged in...");
            if(birdService.login(username)){
                loginMessage.setText("");
                redrawBirdapp();
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
        
        birdnodes = new VBox();
//        birdnodes.setMaxWidth(280);
//        birdnodes.setMinWidth(280);
        redrawBirdapp();
//        ObservableList<String> birds = FXCollections.<String>observableArrayList("Alli", "Haahka", "Haapana", "Kuovi");
//        ListView<String> birdsview = new ListView<>(birds);
//        birdsview.setOrientation(Orientation.VERTICAL);
//        
//         ListView<String> checklist = new ListView<>(birds);
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
        
        
        
//    ListView<String> birdView = new ListView<>();
//        String[] birds = {"Kyhmyjoutsen","Pikkujoutsen","Laulujoutsen","Metsähanhi", "Lyhytnokkahanhi","Tundrahanhi","Kiljuhanhi","Merihanhi","Lumihanhi","Tiibetinhanhi","Kanadanhanhi","Valkoposkihanhi","Sepelhanhi","Punakaulahanhi","Pikkukanadanhanhi","Ruostesorsa","Ristisorsa","Mandariinisorsa","Haapana","Amerikanhaapana","Harmaasorsa","Tavi","Amerikantavi","Sinisorsa","Nokisorsa","Jouhisorsa","Heinätavi","Sinisiipitavi","Lapasorsa","Punapäänarsku","Punasotka","Amerikantukkasotka","Ruskosotka","Tukkasotka","Lapasotka","Pikkulapasotka","Haahka","Kyhmyhaahka","Allihaahka","Virta-alli","Alli","Mustalintu","Amerikanmustalintu","Pilkkaniska","Pilkkasiipi","Kyhmypilkkasiipi","Telkkä","Uivelo","Tukkakoskelo","Isokoskelo","Kuparisorsa"};
//        birdView.getItems().addAll(birds);
//        birdView.setCellFactory(CheckBoxListCell.forListView(new Callback<String, ObservableValue<Boolean>>() {
//            @Override
//            public ObservableValue<Boolean> call(String item) {
//            BooleanProperty observable = new SimpleBooleanProperty();
//            observable.addListener((obs, wasSelected, isNowSelected)
//                -> System.out.println("Check box for " + item + " changed from " + wasSelected + " to " + isNowSelected)
//            );
//            return observable;
//            }
//    }));
    
        VBox birdSelection = new VBox();
        birdSelection.setSpacing(10);
        birdSelection.getChildren().addAll(box1, box2, box3, box4, box5, box6, box7, box8, box9, box10, box12, box13, box14, box15, box16, box17, box18, box19, box20);
 
        birdScrollbar.setContent(birdSelection);
//        mainPane.setBottom(createForm);
        mainPane.setTop(menuPane);
        
//        ArrayList<String> birds = new ArrayList<>();
//         birds.add("Alli");
//        birds.add("Allihaahka");
//        birds.add("Amerikanhaapana");
//        birds.add("Amerikanmustalintu");
//        birds.add(	"Amerikantavi");
//        birds.add(	"Amerikantukkasotka");
//        birds.add(	"Haahka");
//        birds.add(	"Haapana");
//        birds.add(	"Harmaasorsa");
//        birds.add(	"Heinätavi");
//        birds.add(	"Isokoskelo");
//        birds.add(	"Jouhisorsa");
//        birds.add(	"Kanadanhanhi");
//        birds.add(	"Kiljuhanhi");
//        birds.add(	"Kuparisorsa");
//        birds.add(	"Kyhmyhaahka");
//        birds.add(	"Kyhmyjoutsen");
//        birds.add(	"Kyhmypilkkasiipi");
//        birds.add(	"Lapasorsa");
//        birds.add(	"Lapasotka");
//        birds.add(	"Laulujoutsen");
//        birds.add(	"Lumihanhi");
//        birds.add(	"Lyhytnokkahanhi");
//        birds.add(	"Mandariinisorsa");
//        birds.add(	"Merihanhi");
//        birds.add(	"Metsähanhi");
//        birds.add(	"Mustalintu");
//        birds.add(	"Nokisorsa");
//        birds.add(	"Pikkujoutsen");
//        birds.add(	"Pikkukanadanhanhi");
//        birds.add(	"Pikkulapasotka");
//        birds.add(	"Pilkkaniska");
//        birds.add(	"Pilkkasiipi");
//        birds.add(	"Punakaulahanhi");
//        birds.add(	"Punapäänarsku");
//        birds.add(	"Punasotka");
//        birds.add(	"Ristisorsa");
//        birds.add(	"Ruostesorsa");
//        birds.add(	"Ruskosotka");
//        birds.add(	"Sepelhanhi");
//        birds.add(	"Sinisiipitavi");
//        birds.add(	"Sinisorsa");
//        birds.add(	"Tavi");
//        birds.add(	"Telkkä");
//        birds.add(	"Tiibetinhanhi");
//        birds.add(	"Tukkakoskelo");
//        birds.add(	"Tukkasotka");
//        birds.add(	"Tundrahanhi");
//        birds.add(	"Uivelo");
//        birds.add(	"Valkoposkihanhi");
//        birds.add(	"Virta-alli");

        
//        createBird.setOnAction(e->{
//            birdService.createBirdapp(newBirdInput.getText());
////            birdService.createBirdapp(birds);
//            newBirdInput.setText("");
//            redrawBirdapp();
//        });
        
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
