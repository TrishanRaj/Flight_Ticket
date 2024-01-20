package flightbook;

import java.awt.Rectangle;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;



public class MenuGui extends Stage {

        
    public MenuGui(String userName,Ticket[] book, MyQueue<Ticket> waitList) {
        
     
        setTitle("Malaya Flight Booking Menu");

        Button btn = new Button("Go Back");
        btn.setOnAction(e -> close());

        Button btnSearch = new Button("Search Ticket");
        Button btnBook = new Button("Booking");
        Button btnTicket = new Button("My Ticket");
        Button btnLogout = new Button("Log Out");

        btnBook.setOnAction(e -> openBookingGui(userName,book,waitList));
        btnTicket.setOnAction(e -> openTicketGui(userName,book,waitList));
        btnLogout.setOnAction(e -> openLoginGui());

        HBox hbox = new HBox(btnSearch, btnBook, btnTicket, btnLogout);

        // Set spacing between buttons
        hbox.setSpacing(10);

        // Set padding around the HBox
        hbox.setPadding(new Insets(50));

        // You can set alignment as needed (e.g., CENTER)
        hbox.setAlignment(Pos.TOP_LEFT);
         setScene(new Scene(hbox, 800, 500));
    }

     private void openLoginGui() {
        LoginGui loginGui = new LoginGui();
        loginGui.start(new Stage()); // Create a new stage for LoginGui
        close(); // Close the current MenuGui window
    }
     
     private  void openTicketGui(String userName,Ticket[] book, MyQueue<Ticket> waitList){
         TicketGui ticketGui= new TicketGui(userName,book,waitList);
         ticketGui.show();
         close();
     }
     
     private void openBookingGui(String userName,Ticket[] book, MyQueue<Ticket> waitList){
         BookingGui bookingGui=new BookingGui(userName,book,waitList);
         bookingGui.show();
         close();
     }
     
}
