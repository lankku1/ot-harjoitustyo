package caressys.ui;

import caressys.dao.FileCaresDao;
import caressys.dao.FileUserDao;
import caressys.domain.User;
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
import caressys.domain.CaressysService;
import java.io.File;
import java.io.FileInputStream;
import java.time.LocalDate;
import java.util.Properties;
import javafx.geometry.Pos;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.BorderPane;

public class CaressysUi extends Application {

    // let's set all the required scenes here
    private Scene loginScene; // for login
    private Scene newUserScene; // for creating a new user
    private Scene userScene; // the first scene after logging in
    private Scene reservationScene; // layout for all the users reservations
    private Scene calendarScene; // for the calendar view
    private Scene newReservationScene; // for creating a new reservation

    private CaressysService service;
    private Label menuLabel = new Label();
    
    @Override
    public void init() throws Exception {
        Properties properties = new Properties();

        File configProperties = new File("config.properties");
        properties.load(new FileInputStream(configProperties));
        
        String userFile = properties.getProperty("userFile");
        String resFile = properties.getProperty("resFile"); 
        //file for reservations
            
        FileUserDao userDao = new FileUserDao(userFile);
        FileCaresDao caresDao = new FileCaresDao(resFile, userDao);
        service = new CaressysService(userDao, caresDao);
    }

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
        Button loginButton = new Button("Log in");
        Button createButton = new Button("Create new user");
        
        // set this action, when pressing the "Log in"-button
        loginButton.setOnAction((event) -> {
            String username = inputUsername.getText();
            menuLabel.setText(username);
            if (service.login(username)) {
                loginMessage.setText("");
                primaryStage.setScene(userScene);
                inputUsername.setText("");
            } else {
                loginMessage.setText("user does not exist");
                loginMessage.setTextFill(Color.RED);
            }
        });

        // set this action, when pressing the "create new user"-button
        createButton.setOnAction((event) -> {
            inputUsername.setText("");
            primaryStage.setScene(newUserScene);
        });

        loginPane.getChildren().addAll(loginMessage, loginButton, createButton);

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
            } else if (service.createUser(username, name)) {
                userCreationMessage.setText("");
                loginMessage.setText("new user created");
                loginMessage.setTextFill(Color.GREEN);
                primaryStage.setScene(loginScene);
            } else { // createUser method returns false
                userCreationMessage.setText("Username has to be unique"); 
                userCreationMessage.setTextFill(Color.RED);
            }
        });
        newUserPane.getChildren().addAll(userCreationMessage, newUsernamePane, newNamePane, characterInfo, createNewUserButton);
        newUserScene = new Scene(newUserPane, 300, 250);
        
        // set the user scene
        BorderPane userPane = new BorderPane();
        userPane.setPadding(new Insets(10));
        VBox leftSidePane = new VBox(10);
        leftSidePane.setPadding(new Insets(10));
        
        Button signOutButton = new Button("Sign out");
        Button reservationsButton = new Button("My reservations");
        Button calendarButton = new Button("Calendar");
        
        
        signOutButton.setOnAction((event) -> {
            loginMessage.setText("Signed out succesfully");
            loginMessage.setTextFill(Color.GREEN);
            primaryStage.setScene(loginScene);
        });
        reservationsButton.setOnAction((event) -> {
            primaryStage.setScene(reservationScene);
        });
        calendarButton.setOnAction((event) -> {
            primaryStage.setScene(calendarScene);
        });
        
        leftSidePane.getChildren().addAll(menuLabel, reservationsButton, calendarButton);
        userPane.setRight(signOutButton);
        userPane.setLeft(leftSidePane);
        userScene = new Scene(userPane, 300, 250);
        
        // set calendar scene
        BorderPane calendarPane = new BorderPane();
        calendarPane.setPadding(new Insets(10));
        
        
        Button createReservationButton = new Button("New reservation");
        Button returnUserSceneButton1 = new Button("Return");
        createReservationButton.setOnAction((event) -> {
            primaryStage.setScene(newReservationScene);
        });
        returnUserSceneButton1.setOnAction((event) -> {
            primaryStage.setScene(userScene);
        });
        
        
        /*
        // customizing the date picker
        DatePicker insertStartDate = new DatePicker();
        insertStartDate.setValue(LocalDate.now());
        insertStartDate.setShowWeekNumbers(true);
        */
        calendarPane.setRight(returnUserSceneButton1);
        calendarPane.setLeft(createReservationButton);
        calendarScene = new Scene(calendarPane, 300, 250);
        
        
        // set the reservation scene
        BorderPane reservationPane = new BorderPane();
        reservationPane.setPadding(new Insets(10));
        
        Button returnUserSceneButton2 = new Button("Return");
        
        returnUserSceneButton2.setOnAction((event) -> {
            primaryStage.setScene(userScene);
        });
        reservationPane.setRight(returnUserSceneButton2);
        reservationScene = new Scene(reservationPane, 300, 250);
        
        // set the create a new reservation scene
        BorderPane newReservationPane = new BorderPane();
        newReservationPane.setPadding(new Insets(10));
        
        VBox createReservationPane = new VBox(10);
        createReservationPane.setPadding(new Insets(10));
        
        HBox arrivalPane = new HBox(10);
        arrivalPane.setPadding(new Insets(10));
        
        Label arrivalLabel = new Label("Arrival: ");
        DatePicker insertArrivalDate = new DatePicker();
        insertArrivalDate.setValue(LocalDate.now());
        insertArrivalDate.setShowWeekNumbers(true);
        arrivalPane.getChildren().addAll(arrivalLabel, insertArrivalDate);
        
        HBox departurePane = new HBox(10);
        departurePane.setPadding(new Insets(10));
        
        Label departureLabel = new Label("Departure: ");
        DatePicker insertDepartureDate = new DatePicker();
        insertDepartureDate.setValue(LocalDate.now());
        insertDepartureDate.setShowWeekNumbers(true);
        departurePane.getChildren().addAll(departureLabel, insertDepartureDate);
        
        Button newReservationButton = new Button("Create new reservation"); // add the functionality later
        Label createReservationInfo = new Label(); // if the reservation isn't available
        createReservationPane.getChildren().addAll(arrivalPane, departurePane, newReservationButton, createReservationInfo);
        
        Button returnToCalendarButton = new Button("Return");
        returnToCalendarButton.setPadding(new Insets(10));
        
        returnToCalendarButton.setOnAction((event) -> {
            primaryStage.setScene(calendarScene);
        });
        
        newReservationPane.setRight(returnToCalendarButton);
        newReservationPane.setCenter(createReservationPane);
        
        newReservationScene = new Scene(newReservationPane, 300, 250);
        

        primaryStage.setScene(loginScene);
        primaryStage.setTitle("CaressysApp");
        primaryStage.show();

    }
    

    public static void main(String[] args) {
        launch(CaressysUi.class);
    }

}
