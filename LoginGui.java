package flightbook;

import javafx.application.Application;
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

public class LoginGui extends Application {
    
     TextField usernameField;
   PasswordField passwordField;

    public static void main(String[] args) {
        launch(args);
    }

  @Override
public void start(Stage primaryStage) {
    primaryStage.setTitle("Login");
    
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
            openMenuGui(primaryStage, usernameField.getText());
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
    primaryStage.setScene(scene);
    primaryStage.show();
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
 
    private void openMenuGui(Stage primaryStage, String userName) {
        MenuGui menuGui = new MenuGui(userName);
        menuGui.show();
        primaryStage.close(); // Close the current LoginGui window
    }
}
