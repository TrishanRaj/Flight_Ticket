/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package flightbook;


import java.awt.Rectangle;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
import javafx.scene.paint.Color;
import javafx.stage.Stage;



public class LoginUserGui extends Stage {

       TextField usernameField;
   PasswordField passwordField;
    String personal_info=null;
     
        int fast_book=0;
        
    public LoginUserGui(Ticket[] book, MyQueue<Ticket> waitList) {
        
    setTitle("Login");
 
    BorderPane root = new BorderPane();

    // Create title bar
    HBox titleBar = new HBox();
    titleBar.setAlignment(Pos.CENTER_RIGHT);

    // Set background color for minimize and close buttons
    String buttonColor = "-fx-background-color: gold;";

    // Create login form
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
        if (isInputValid()) {
            
        personal_info=usernameField.getText();
        System.out.println(personal_info);
        
            openMenuGui(personal_info,book,waitList);
        }
    });

    // Set background color for the login button
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
    return true;
}

    private void showAlert(Alert.AlertType type, String title, String content) {
    Alert alert = new Alert(type);
    alert.setTitle(title);
    alert.setContentText(content);
    alert.showAndWait();
}
 
    private void openMenuGui(String userName,Ticket[] book, MyQueue<Ticket> waitList) {
        MenuGui menuGui = new MenuGui(userName,book,waitList);
        menuGui.show();
        close(); // Close the current LoginGui window
    }
}
