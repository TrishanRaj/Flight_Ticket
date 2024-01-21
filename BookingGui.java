package flightbook;

import java.time.LocalDate;
import java.time.Month;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Border;

import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.DateCell;
import javafx.util.Callback;


public class BookingGui extends Stage {  

    private Label lblTitle, lblName, lblPassNo, lblPhoneNum, lblFlightName, lblFlightTime, lblGender,lblDestination;
    private TextField txtName, txtPassNo, txtPhoneNum, txtFlightName,txtDestination;
    private ToggleGroup genderGroup;
    private RadioButton rbMale, rbFemale;
    private DatePicker datePicker;

    public BookingGui(String username,Ticket[] book, MyQueue<Ticket> waitList) {

        setTitle("Malaya Flight Booking System"); 
        Button btnBack = new Button("Go Back");

        // Set spacing between label and button
        VBox vbox = new VBox(10, btnBack);
        vbox.setAlignment(Pos.TOP_LEFT);

        lblTitle = new Label("MALAYA TICKET BOOKING ");
        lblTitle.setTextFill(Color.BLUE);
        lblTitle.setFont(Font.font("Serif", FontWeight.BOLD, 30));

        // Add both VBoxes and the new components to another VBox
        VBox mainVBox = new VBox(20, vbox, lblTitle);
        mainVBox.setAlignment(Pos.TOP_CENTER);

        // Create a VBox for labels and text fields with a rectangular border
        VBox infoVBox = new VBox(10);
        infoVBox.setPadding(new Insets(50, 250, 50, 150));
        infoVBox.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(2))));
        infoVBox.setStyle("-fx-background-color: #F7E2C8;"); 

        // Labels and text fields
        lblName = new Label("Name:");
        txtName = new TextField();
        txtName.setPrefWidth(250); 
        infoVBox.getChildren().add(new HBox(100,lblName, txtName));

        lblPassNo = new Label("Passport Number:");
        txtPassNo = new TextField();
        txtPassNo.setPrefWidth(250); 
        infoVBox.getChildren().add(new HBox(40, lblPassNo, txtPassNo));

        lblGender = new Label("Gender:");
        genderGroup = new ToggleGroup();
        rbMale = new RadioButton("Male");
        rbMale.setToggleGroup(genderGroup);
        rbFemale = new RadioButton("Female");
        rbFemale.setToggleGroup(genderGroup);
        infoVBox.getChildren().add(new HBox(120, lblGender, new HBox(10, rbMale, rbFemale)));

        lblPhoneNum = new Label("Phone Number:");
        txtPhoneNum = new TextField();
        txtPhoneNum.setPrefWidth(250); 
        infoVBox.getChildren().add(new HBox(55, lblPhoneNum, txtPhoneNum));

        lblDestination=new Label("Destination: ");
        txtDestination=new TextField("Malaysia --- South Korea");
        txtDestination.setEditable(false);
        Font boldFont = Font.font("Arial",FontWeight.BOLD, 12);
        txtDestination.setFont(boldFont);
        txtDestination.setPrefWidth(250); 
        txtDestination.setAlignment(Pos.CENTER);
        infoVBox.getChildren().add(new HBox(72,lblDestination,txtDestination));
        
       

        lblFlightTime = new Label("Flight Date: ");
        datePicker = new DatePicker();
        datePicker.setPrefWidth(250); 
        infoVBox.getChildren().add(new HBox(75, lblFlightTime, datePicker));
        
       //Set the time only to January
        EventHandler<ActionEvent> eventHandler = event -> {
            LocalDate selectedDate = datePicker.getValue();
            if (selectedDate != null) {
                if (selectedDate.getYear() != 2024 || selectedDate.getMonth() != Month.JANUARY) {
                    datePicker.setValue(LocalDate.of(2024, Month.JANUARY, 1));
                }
            }
        };

        datePicker.setOnAction(eventHandler);

        // Use a custom DateCell factory to disable dates outside of January 2024
        Callback<DatePicker, DateCell> dayCellFactory = dp -> new DateCell() {
            @Override
            public void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);

                if (item.isBefore(LocalDate.of(2024, Month.JANUARY, 1)) || item.isAfter(LocalDate.of(2024, Month.JANUARY, 31))) {
                    setDisable(true);
                    setStyle("-fx-background-color: #ffc0cb;"); // Disable date cell style
                }
            }
        };

        datePicker.setDayCellFactory(dayCellFactory);
        
         lblFlightName = new Label("Flight Name: ");
        txtFlightName = new TextField();
        txtFlightName.setPrefWidth(250); 
        infoVBox.getChildren().add(new HBox(68, lblFlightName, txtFlightName));

        // GridPane for labels and text fields
        GridPane infoGrid = new GridPane();
        infoGrid.setHgap(10);
        infoGrid.setVgap(10);
        infoGrid.setAlignment(Pos.CENTER);

        // Add the VBox with labels and text fields to the GridPane
        infoGrid.add(infoVBox, 0, 0);

        // HBox for buttons
        HBox buttonBox = new HBox(10);
        Button btnBook = new Button("Book");
        Button btnCancel = new Button("Cancel");
        
        btnBook.setPrefSize(100, 40);  // Set width and height
        btnCancel.setPrefSize(100, 40);  // Set width and height        
        btnBook.setStyle("-fx-font-size: 14; -fx-background-color: #4CAF50; -fx-text-fill: white;"); // Green background, white text
        btnCancel.setStyle("-fx-font-size: 14;-fx-background-color: #F46161; -fx-text-fill: white;"); // Red background, white text

        buttonBox.getChildren().addAll(btnBook, btnCancel);
        buttonBox.setAlignment(Pos.CENTER);

        // Another VBox to contain everything
        VBox rootVBox = new VBox(20, mainVBox, infoGrid, buttonBox);
        rootVBox.setAlignment(Pos.TOP_CENTER);
            
        setScene(new Scene(rootVBox, 850, 550));
         setResizable(false);  
        
        btnBack.setOnAction(e -> {
            openMenuGui(username,book,waitList);
        });
        

        // Event handler for the Book button
        btnBook.setOnAction(e -> {
            // Check if all required fields are filled
            if (isInputValid()) {
                // Get passenger details
                String passengerName = txtName.getText();
                String passportNo = txtPassNo.getText();
                String phoneNum = txtPhoneNum.getText();
                String flightName = txtFlightName.getText();
                String flightTime = datePicker.getValue().toString();

                // Create Ticket object and book the ticket
                Ticket myTicket = new Ticket(passengerName, passportNo, phoneNum, username, flightName, flightTime, null);

                // Find the first null entry in the book array and book the ticket
                boolean booked = false;
                for (int a = 0; a < book.length; a++) {
                    if (book[a] == null) {
                        book[a] = myTicket;
                        booked = true;
                        break;
                    }
                }

                if (booked) {
                    
                    myTicket.status = "Confirmed";
                    showSuccessMessage();
                    openMenuGui(username,book,waitList);
                                        
                } 
                else {
                   
                    myTicket.status = "Waiting";
                    waitList.enqueue(myTicket);
                    showSuccessMessage(); // Display success 
                    openMenuGui(username,book,waitList);
                   
                }
              
            }
        });

        // Event handler for the Cancel button
        btnCancel.setOnAction(e -> {
            // Ask for confirmation before canceling
            Optional<ButtonType> result = showConfirmationAlert("Cancel Booking", "Are you sure you want to cancel?");
            if (result.isPresent() && result.get() == ButtonType.OK) {
                // User confirmed canceling, close the stage
                openMenuGui(username,book,waitList);
            }
        });
    }

    private void showAlert(AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void showSuccessMessage() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Booking Success");
        alert.setHeaderText(null);
        alert.setContentText("Booking Done. Thank you and kindly check your ticket status!");
        alert.showAndWait();
    }

    private Text createAsterisk() {
        Text asterisk = new Text(" *");
        asterisk.setFill(Color.RED);
        asterisk.setFont(Font.font(14)); // Adjust the font size if needed
        return asterisk;
    }

    private boolean isInputValid() {
        // Perform validation checks here
        if (txtName.getText().isEmpty() || txtPassNo.getText().isEmpty() || txtPhoneNum.getText().isEmpty()
                || txtFlightName.getText().isEmpty() || datePicker.getValue() == null) {
            showAlert(AlertType.ERROR, "Error Message", "Please fill in all the required fields.");
            return false;
        }
        return true;
    }

    private Optional<ButtonType> showConfirmationAlert(String title, String content) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        return alert.showAndWait();
    }
    
 
    private void openMenuGui(String userName,Ticket[] book, MyQueue<Ticket> waitList) {
        MenuGui menuGui = new MenuGui(userName,book,waitList);
        menuGui.show();
        close(); // Close the current LoginGui window
    }
}
