
package flightbook;

import java.util.Optional;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
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


public class EditTicketAdminGui extends Stage {
    
       private Label lblTitle, lblName, lblPassNo, lblPhoneNum, lblFlightName, lblFlightTime, lblGender,lblDestination;
    private TextField txtName, txtPassNo, txtPhoneNum,txtDestination,txtflightTime, txtflightDate,txtflightName;
    private ToggleGroup genderGroup;
    private RadioButton rbMale, rbFemale;
    
    public EditTicketAdminGui(String username, Ticket[] book, MyQueue<Ticket> waitList,Ticket ticket,Flight[] flight){
        
        setTitle("Malaya Flight Booking System"); 
        Button btnBack = new Button("🔙");
        btnBack.setStyle("-fx-font-size: 20;");

        // Set spacing between label and button
        VBox vbox = new VBox(10, btnBack);
        vbox.setAlignment(Pos.TOP_LEFT);

        lblTitle = new Label("ADMIN EDIT TICKET BOOKING ");
        lblTitle.setTextFill(Color.BLUE);
        lblTitle.setFont(Font.font("Serif", FontWeight.BOLD, 30));

        // Add both VBoxes and the new components to another VBox
        VBox mainVBox = new VBox(20, vbox, lblTitle);
        mainVBox.setAlignment(Pos.TOP_CENTER);

        // Create a VBox for labels and text fields with a rectangular border
        VBox infoVBox = new VBox(10);
        infoVBox.setPadding(new Insets(50, 250, 50, 150));
        infoVBox.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(2))));
        infoVBox.setStyle("-fx-background-color: #D2EBFC;"); 

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

        lblDestination = new Label("Destination: ");
        txtDestination = new TextField();
        txtDestination.setEditable(false);
        Font boldFont = Font.font("Arial", FontWeight.BOLD, 12);
        txtDestination.setFont(boldFont);
        txtDestination.setPrefWidth(250);
        txtDestination.setAlignment(Pos.CENTER);
         infoVBox.getChildren().add(new HBox(72,lblDestination,txtDestination));

        lblFlightName = new Label("Flight Name: ");
        txtflightName = new TextField(); // You may replace this with a proper time picker
        txtflightName.setPrefWidth(250);
        txtflightName.setEditable(false);
         infoVBox.getChildren().add(new HBox(68, lblFlightName, txtflightName));

        Label lblFlightDate = new Label("Flight Date: ");
        txtflightDate = new TextField(); // You may replace this with a proper time picker
        txtflightDate.setPrefWidth(250);
        txtflightDate.setEditable(false);
        infoVBox.getChildren().add(new HBox(70, lblFlightDate, txtflightDate));

        Label lblFTime = new Label("Flight Time: ");
        txtflightTime = new TextField(); // You may replace this with a proper time picker
        txtflightTime.setPrefWidth(250);
        txtflightTime.setEditable(false);
        infoVBox.getChildren().add(new HBox(70, lblFTime, txtflightTime));

        GridPane infoGrid = new GridPane();
        infoGrid.setHgap(10);
        infoGrid.setVgap(10);
        infoGrid.setAlignment(Pos.CENTER);

        infoGrid.add(infoVBox, 0, 0);
        HBox buttonBox = new HBox(10);
        Button btnUpdate = new Button("Update");
        Button btnCancel = new Button("Cancel");
        
        btnUpdate.setPrefSize(100, 40);  
        btnCancel.setPrefSize(100, 40);        
        btnUpdate.setStyle("-fx-font-size: 14; -fx-background-color: #4CAF50; -fx-text-fill: white;"); // Green background, white text
        btnCancel.setStyle("-fx-font-size: 14;-fx-background-color: #F46161; -fx-text-fill: white;"); // Red background, white text

        buttonBox.getChildren().addAll(btnUpdate, btnCancel);
        buttonBox.setAlignment(Pos.CENTER);

        VBox rootVBox = new VBox(20, mainVBox, infoGrid, buttonBox);
        rootVBox.setAlignment(Pos.TOP_CENTER);
        rootVBox.setStyle("-fx-background-color: #EEF7F0;");
            
        setScene(new Scene(rootVBox, 830, 570));
         setResizable(false);

        setInitialValues(ticket);
        
           btnBack.setOnAction(e -> {
               openAdminTicketGui(username, book, waitList,flight);
        });
           
            btnUpdate.setOnAction(e -> {
                updateValue(ticket,username,book,waitList,flight);
        });

        btnCancel.setOnAction(e -> {
            // Ask for confirmation before canceling
            Optional<ButtonType> result = showConfirmationAlert("Cancel Booking", "Are you sure you want to cancel?");
            if (result.isPresent() && result.get() == ButtonType.OK) {
                openAdminTicketGui(username,book,waitList,flight);
            }
        });
        
    }
    private void setInitialValues(Ticket ticket) {
        
       
        txtName.setText(ticket.passengerName);
        txtPassNo.setText(ticket.passportNo);
        txtPhoneNum.setText(ticket.phoneNum);
        txtDestination.setText("Malaysia --- South Korea");
        txtflightName.setText(ticket.flightsName);
        txtflightDate.setText(ticket.flightDate);
        txtflightTime.setText(ticket.flightTime);

    if ("Male".equals(ticket.gender)) {
        rbMale.setSelected(true);
    } else if ("Female".equals(ticket.gender)) {
        rbFemale.setSelected(true);
    }
        
    }
    
    private void updateValue(Ticket ticket,String userName,Ticket[] book, MyQueue<Ticket> waitList,Flight[] flight) {
        
        if (txtName.getText().isEmpty() || txtPassNo.getText().isEmpty() || txtPhoneNum.getText().isEmpty() ||
            txtflightName.getText().isEmpty() || !rbMale.isSelected() && !rbFemale.isSelected()) {
        showAlert(Alert.AlertType.ERROR, "Error Message", "Please fill in all the required fields.");
        return;
    }
          Toggle selectedToggle = genderGroup.getSelectedToggle();
                RadioButton selectedRadioButton = (RadioButton) selectedToggle;
                
        
       ticket.passengerName=txtName.getText();
       ticket.passportNo=txtPassNo.getText();
       ticket.phoneNum=txtPhoneNum.getText();
       ticket.flightsName=txtflightName.getText();
        ticket.gender= selectedRadioButton.getText();
        openAdminTicketGui(userName, book, waitList,flight);
    
    }
    
    private void showAlert(Alert.AlertType type, String title, String content) {
    Alert alert = new Alert(type);
    alert.setTitle(title);
    alert.setContentText(content);
    alert.showAndWait();
}
    
  
     
        private Optional<ButtonType> showConfirmationAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        return alert.showAndWait();
    }
    
         //AdminTicketGui
     private void openAdminTicketGui(String userName,Ticket[] book, MyQueue<Ticket> waitList,Flight[] flight) {
        AdminTicketGui adminTicketGui = new AdminTicketGui(userName,book,waitList,flight);
        adminTicketGui.show();
        close(); // Close the current LoginGui window
    }
    


    
}
