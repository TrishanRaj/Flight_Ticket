package flightbook;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;



public class MenuGui extends Stage {

        
    public MenuGui(String userName,Ticket[] book, MyQueue<Ticket> waitList,Flight[] flight) {
        
     
        setTitle("Malaya Flight Booking Menu");

        Button btn = new Button("Go Back");
        btn.setOnAction(e -> close());

        Button btnSearch = new Button("Search Ticket");
        Button btnBook = new Button("Booking");
        Button btnTicket = new Button("My Ticket");
        Button btnLogout = new Button("Log Out");

        btnBook.setOnAction(e -> openBookingGui(userName,book,waitList,flight));
        btnTicket.setOnAction(e -> openTicketGui(userName,book,waitList,flight));
        btnLogout.setOnAction(e -> openLoginGui(book,waitList,flight));

        HBox hbox = new HBox(btnSearch, btnBook, btnTicket, btnLogout);

        // Set spacing between buttons
        hbox.setSpacing(10);

        // Set padding around the HBox
        hbox.setPadding(new Insets(50));

        // You can set alignment as needed (e.g., CENTER)
        hbox.setAlignment(Pos.TOP_LEFT);
         setScene(new Scene(hbox, 800, 500));
    }

     private void openLoginGui(Ticket[] book, MyQueue<Ticket> waitList,Flight[] flight) {
        LoginUserGui loginGui = new LoginUserGui(book,waitList,flight);
        loginGui.show(); // Create a new stage for LoginGui
        close(); // Close the current MenuGui window
    }
     
     private  void openTicketGui(String userName,Ticket[] book, MyQueue<Ticket> waitList,Flight[] flight){
         TicketGui ticketGui= new TicketGui(userName,book,waitList,flight);
         ticketGui.show();
         close();
     }
     
     private void openBookingGui(String userName,Ticket[] book, MyQueue<Ticket> waitList,Flight[] flight){
         BookingGui bookingGui=new BookingGui(userName,book,waitList,flight);
         bookingGui.show();
         close();
     }
     
}
