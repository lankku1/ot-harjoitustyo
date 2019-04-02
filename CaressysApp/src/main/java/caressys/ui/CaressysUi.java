
package caressys.ui;


import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import caressys.domain.caressysService;

public class CaressysUi extends Application {
    
    // let's set all the required scenes here
    private Scene loginScene; // for login
    private Scene newUserScene; // for creating a new user
    private Scene userScene; // the first scene after logging in
    private Scene reservationScene; // layout for all the users reservations
    private Scene calendarScene; // for the calendar view
    private Scene newReservationScene; // for creating a new reservation
    
    private Label menuLabel = new Label();
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        // this is where the app starts
        // set the login scene
        VBox loginPane = new VBox(10);
        loginPane.setPadding(new Insets(10));
        
        Label loginLabel = new Label("Username:");
        TextField inputUsername = new TextField();
        loginPane.getChildren().addAll(loginLabel, inputUsername);
        Label loginMessage = new Label(); // if the username input doesn't exist yet
        
        // let's create the buttons
        Button loginButton = new Button("Log In"); 
        Button createButton = new Button("Create new user");
        
        
        loginButton.setOnAction((event) -> {
            System.out.println("Trying to login");
            String username = inputUsername.getText();
            System.out.println(username);
            // let's add the functionality later for login button
        });
        
        // set this action, when pressing the "create new user"-button
        createButton.setOnAction((event) -> {
            inputUsername.setText("");
            primaryStage.setScene(newUserScene);
        });
        loginPane.getChildren().addAll(loginButton, createButton, loginMessage);
        
        loginScene = new Scene(loginPane, 300, 250);
        
        // set the create newUserScene
        VBox newUserPane = new VBox(10);
        newUserPane.setPadding(new Insets(10));
        
        HBox newUsernamePane = new HBox(10);
        newUsernamePane.setPadding(new Insets(10));
        TextField newUsernameInput = new TextField();
        Label newUsernameLabel = new Label("Username: ");
        newUsernamePane.getChildren().addAll(newUsernameLabel, newUsernameInput);
        
        HBox newNamePane = new HBox(10);
        newNamePane.setPadding(new Insets(10));
        Label newNameLabel = new Label("Name: ");
        TextField newNameInput = new TextField();
        newNamePane.getChildren().addAll(newNameLabel, newNameInput);
        
        Label characterInfo = new Label("minimum 3 characters");
        characterInfo.setPadding(new Insets(10));
        
        Label userCreationMessage = new Label();
        
        Button createNewUserButton = new Button("Create");
        createNewUserButton.setPadding(new Insets(10));
        
        // when pressing createNewUserButton, this action will happen as follows
        createNewUserButton.setOnAction((event) -> {
            String username = newUsernameInput.getText();
            String name = newNameInput.getText();
            
            if (username.length() == 2 || name.length() < 3) {
                userCreationMessage.setText("Username or name too short!");
                userCreationMessage.setTextFill(Color.RED);
            }//else if (caressysService.createUser(username, name)) {
                
            //}
            else {
                System.out.println("new user created");
            }
        });
        newUserPane.getChildren().addAll(userCreationMessage, newUsernamePane, newNamePane, characterInfo, createNewUserButton);
        newUserScene = new Scene(newUserPane, 300, 250);
        
        
        primaryStage.setScene(loginScene);
        primaryStage.setTitle("CaressysApp");
        primaryStage.show();
        
    }
    
    public static void main(String[] args) {
        launch(CaressysUi.class);
    }
    
    
}
