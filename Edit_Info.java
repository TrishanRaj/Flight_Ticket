
package flightbook;

import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
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
import javafx.stage.Stage;
import javafx.util.Callback;


public class EditTicket extends Stage {
    
      private Label lblTitle, lblName, lblPassNo, lblPhoneNum, lblFlightName, lblFlightTime, lblGender;
    private TextField txtName, txtPassNo, txtPhoneNum, txtFlightName;
    private ToggleGroup genderGroup;
    private RadioButton rbMale, rbFemale;
    private DatePicker datePicker;
    
    public EditTicket(String username, Ticket[] book, MyQueue<Ticket> waitList,Ticket ticket,Flight[] flight){
        
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
        infoVBox.getChildren().add(new HBox(100,lblName, txtName));

        lblPassNo = new Label("Passport Number:");
        txtPassNo = new TextField();
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
        infoVBox.getChildren().add(new HBox(55, lblPhoneNum, txtPhoneNum));
        
        lblFlightTime = new Label("Flight Date: ");
        datePicker = new DatePicker();
        infoVBox.getChildren().add(new HBox(70, lblFlightTime, datePicker));

              // Set an event handler to reset the date to January 2024 when the user tries to change it
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
        Button btnUpdate = new Button("Update");
        Button btnCancel = new Button("Cancel");
        
        btnUpdate.setPrefSize(100, 40);  // Set width and height
        btnCancel.setPrefSize(100, 40);  // Set width and height        
        btnUpdate.setStyle("-fx-font-size: 14; -fx-background-color: #4CAF50; -fx-text-fill: white;"); // Green background, white text
        btnCancel.setStyle("-fx-font-size: 14;-fx-background-color: #F46161; -fx-text-fill: white;"); // Red background, white text

        buttonBox.getChildren().addAll(btnUpdate, btnCancel);
        buttonBox.setAlignment(Pos.CENTER);

        // Another VBox to contain everything
        VBox rootVBox = new VBox(20, mainVBox, infoGrid, buttonBox);
        rootVBox.setAlignment(Pos.TOP_CENTER);
            
        setScene(new Scene(rootVBox, 800, 500));
         setResizable(false);
        
         // Set initial values in text fields and date picker
        setInitialValues(ticket);
        
           btnBack.setOnAction(e -> {
               openTicketGui(username, book, waitList,flight);
        });
           
            btnUpdate.setOnAction(e -> {
                updateValue(ticket,username,book,waitList,flight);
        });
 
            // Event handler for the Cancel button
        btnCancel.setOnAction(e -> {
            // Ask for confirmation before canceling
            Optional<ButtonType> result = showConfirmationAlert("Cancel Booking", "Are you sure you want to cancel?");
            if (result.isPresent() && result.get() == ButtonType.OK) {
                // User confirmed canceling, close the stage
                openTicketGui(username,book,waitList,flight);
            }
        });
    }
    
    private void setInitialValues(Ticket ticket) {
        txtName.setText(ticket.passengerName);
        txtPassNo.setText(ticket.passportNo);
        txtPhoneNum.setText(ticket.phoneNum);
        txtFlightName.setText(ticket.flightName);
        datePicker.setValue(LocalDate.parse(ticket.flightTime));
    /*    if (ticketToEdit.gender.equals("Male")) {
            rbMale.setSelected(true);
        } else {
            rbFemale.setSelected(true);
        } */
    }
    
    private void updateValue(Ticket ticket,String userName,Ticket[] book, MyQueue<Ticket> waitList,Flight[] flight) {
       ticket.passengerName=txtName.getText();
       ticket.passportNo=txtPassNo.getText();
       ticket.phoneNum=txtPhoneNum.getText();
       ticket.flightName=txtFlightName.getText();
        ticket.flightTime=datePicker.getValue().toString();
    /*    if (ticketToEdit.gender.equals("Male")) {
            rbMale.setSelected(true);
        } else {
            rbFemale.setSelected(true);
        } */
        openTicketGui(userName, book, waitList,flight);
    
    }
    
    
     private  void openTicketGui(String userName,Ticket[] book, MyQueue<Ticket> waitList, Flight[] flight){
         TicketGui ticketGui= new TicketGui(userName,book,waitList,flight);
         ticketGui.show();
         close();
     }
     
        private Optional<ButtonType> showConfirmationAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        return alert.showAndWait();
    }
    
}
