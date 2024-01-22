
package flightbook;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import javafx.stage.Stage;



public class LoginUserGui extends Stage {

       TextField usernameField;
   PasswordField passwordField;
    String personal_info=null;
     
        int fast_book=0;
        
    public LoginUserGui(Ticket[] book, MyQueue<Ticket> waitList, Flight[] flight) {
        
    setTitle("Login");
 
    BorderPane root = new BorderPane();
    HBox titleBar = new HBox();  
    titleBar.setAlignment(Pos.CENTER_RIGHT);
    String buttonColor = "-fx-background-color: gold;";

    GridPane loginGrid = new GridPane();
    loginGrid.setAlignment(Pos.CENTER);
    loginGrid.setHgap(10);
    loginGrid.setVgap(10);
    loginGrid.setPadding(new Insets(20, 20, 20, 20));

    usernameField = new TextField();
    passwordField = new PasswordField();

    usernameField.setPromptText("Username");
    passwordField.setPromptText("Password");

    
    Button loginButton = new Button("Login");
    loginButton.setOnAction(e -> {
         if(isAdmin()){
            personal_info=usernameField.getText();
             openAdminTicketGui(personal_info, book, waitList, flight);
        }
        else if (isInputValid()) {    
        personal_info=usernameField.getText();       
            openMenuGui(personal_info,book,waitList,flight);
        }
       
    });

    loginButton.setStyle(buttonColor);

    loginGrid.add(new Label("Username:"), 0, 0);
    loginGrid.add(usernameField, 1, 0);
    loginGrid.add(new Label("Password:"), 0, 1);
    loginGrid.add(passwordField, 1, 1);
    loginGrid.add(loginButton, 1, 2);

    // Add components to the root
    root.setTop(titleBar);
    root.setCenter(loginGrid);
     root.setStyle("-fx-background-color: #F7E2C8;");
      // Create scene and set it on the stage
    Scene scene = new Scene(root, 400, 300);
    this.setScene(scene);
     
    }

     private boolean isInputValid() {
    if (usernameField.getText().isEmpty() || passwordField.getText().isEmpty()) {
        showAlert(Alert.AlertType.ERROR, "Invalid Information", "Please enter the username and password.");
        return false;
    }
     else if(usernameField.getText().equals("ADMIN") && passwordField.getText().equals("abc123")){
         
     }
    return true;
}
     
      private boolean isAdmin() {
    if(usernameField.getText().equals("ADMIN") && passwordField.getText().equals("abc123")){
        return true;
    }
    return false;
}

    private void showAlert(Alert.AlertType type, String title, String content) {
    Alert alert = new Alert(type);
    alert.setTitle(title);
    alert.setContentText(content);
    alert.showAndWait();
}
 
    private void openMenuGui(String userName,Ticket[] book, MyQueue<Ticket> waitList,Flight[] flight) {
        MenuGui menuGui = new MenuGui(userName,book,waitList,flight);
        menuGui.show();
        close(); // Close the current LoginGui window
    }
     private void openAdminTicketGui(String userName,Ticket[] book, MyQueue<Ticket> waitList,Flight[] flight) {
        AdminTicketGui adminTicketGui = new AdminTicketGui(userName,book,waitList,flight);
        adminTicketGui.show();
        close(); // Close the current LoginGui window
    }
}
