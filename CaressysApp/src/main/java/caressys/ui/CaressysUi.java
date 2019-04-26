package caressys.ui;

import caressys.dao.FileCaresDao;
import caressys.dao.FileUserDao;
import caressys.domain.Cares;
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
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;
import java.util.*;

public class CaressysUi extends Application {

    // let's set all the required scenes here
    private Scene loginScene; // for login
    private Scene newUserScene; // for creating a new user
    private Scene userScene; // the first scene after logging in
    private Scene newReservationScene; // for creating a new reservation

    private CaressysService service;
    private Label menuLabel = new Label();
    private VBox reservations = new VBox(10);

    ;
    
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
        //reservations = new VBox(10);
        //reservations.setPadding(new Insets(10));
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
            menuLabel.setText("Welcome to CaressysApp " + username + "!");
            if (service.login(username)) {
                getReservations();
                loginMessage.setText("");
                primaryStage.setScene(userScene);
                inputUsername.setText("");
            } else {
                loginMessage.setText("User does not exist");
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

        // set the createNewUserScene
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

        Label characterInfo = new Label("Minimum 3 characters");
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
                loginMessage.setText("New user created");
                loginMessage.setTextFill(Color.GREEN);
                primaryStage.setScene(loginScene);
            } else { // createUser method returns false
                userCreationMessage.setText("Username has to be unique");
                userCreationMessage.setTextFill(Color.RED);
            }
        });
        newUserPane.getChildren().addAll(userCreationMessage, newUsernamePane, newNamePane, characterInfo, createNewUserButton);
        newUserScene = new Scene(newUserPane, 300, 250);

        // set the user scene when the user has succesfully logged in
        BorderPane userPane = new BorderPane();
        userPane.setPadding(new Insets(10));
        VBox leftSidePane = new VBox(10);
        leftSidePane.setPadding(new Insets(10));

        Button signOutButton = new Button("Sign out");
        Button createReservationButton = new Button("New reservation");

        createReservationButton.setOnAction((event) -> {
            primaryStage.setScene(newReservationScene);
        });

        signOutButton.setOnAction((event) -> {
            loginMessage.setText("Signed out succesfully");
            loginMessage.setTextFill(Color.GREEN);
            reservations.getChildren().clear();
            primaryStage.setScene(loginScene);
        });

        leftSidePane.getChildren().addAll(menuLabel, reservations, createReservationButton);
        userPane.setRight(signOutButton);
        userPane.setLeft(leftSidePane);
        userScene = new Scene(userPane, 500, 300);

        // set the create a new reservation scene
        BorderPane newReservationPane = new BorderPane();
        newReservationPane.setPadding(new Insets(10));

        VBox createReservationPane = new VBox(10);
        createReservationPane.setPadding(new Insets(10));

        VBox arrivalPane = new VBox(10);
        arrivalPane.setPadding(new Insets(10));

        Label arrivalLabel = new Label("Arrival: ");
        DatePicker insertArrivalDate = new DatePicker();
        insertArrivalDate.setValue(LocalDate.now());
        insertArrivalDate.setShowWeekNumbers(true);
        arrivalPane.getChildren().addAll(arrivalLabel, insertArrivalDate);

        VBox departurePane = new VBox(10);
        departurePane.setPadding(new Insets(10));

        Label departureLabel = new Label("Departure: ");
        DatePicker insertDepartureDate = new DatePicker();
        insertDepartureDate.setValue(LocalDate.now().plusDays(1));
        insertDepartureDate.setShowWeekNumbers(true);
        departurePane.getChildren().addAll(departureLabel, insertDepartureDate);

        Button newReservationButton = new Button("Create new reservation"); // add the functionality later
        Label createReservationInfo = new Label(); // if the reservation isn't available
        createReservationPane.getChildren().addAll(arrivalPane, departurePane, newReservationButton, createReservationInfo);
        newReservationButton.setPadding(new Insets(5));

        newReservationButton.setOnAction((event) -> {
            LocalDate arrival = insertArrivalDate.getValue();
            LocalDate departure = insertDepartureDate.getValue();

            if (!service.createReservation(arrival, departure)) {
                createReservationInfo.setText("Reservation overlaps with an \n existing reservation");
                createReservationInfo.setTextFill(Color.RED);
            } else {

                createReservationInfo.setText("");
                menuLabel.setText("New reservation created succesfully");
                menuLabel.setTextFill(Color.GREEN);
                primaryStage.setScene(userScene);
            }
        });

        Button returnToCalendarButton = new Button("Return");
        returnToCalendarButton.setPadding(new Insets(5));
        returnToCalendarButton.setOnAction((event) -> {
            primaryStage.setScene(userScene);
        });

        newReservationPane.setRight(returnToCalendarButton);
        newReservationPane.setCenter(createReservationPane);

        newReservationScene = new Scene(newReservationPane, 300, 280);

        primaryStage.setScene(loginScene);
        primaryStage.setTitle("CaressysApp");
        primaryStage.show();
    }
    
    /**
     * lists all the reservations to reservations pane that all of the users have made.
     * Method 
     */
    public void getReservations() {
        reservations.getChildren().clear();
        List<Cares> reservationlist = service.listAllReservations();

        if (!reservationlist.isEmpty()) {
            reservationlist.forEach((res) -> {
                reservations.getChildren().add(new Label(res.toString()));
            });
        }

    }

    public void setDatePickerView(DatePicker datePicker) {
        final Callback<DatePicker, DateCell> dayCellFactory
                = new Callback<DatePicker, DateCell>() {
            @Override
            public DateCell call(final DatePicker datePicker) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);

                        if (item.isBefore(
                                datePicker.getValue().plusDays(1))) {
                            setDisable(true);

                            setTooltip(new Tooltip(service.getLoggedInUser().getUsername()));
                            setStyle("-fx-background-color: #ffc0cb;");
                        }
                    }
                };
            }
        };
        datePicker.setDayCellFactory(dayCellFactory);
    }

    public static void main(String[] args) {
        launch(CaressysUi.class);
    }

}
