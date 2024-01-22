package flightbook;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;



public class MenuGui extends Stage {

        
    public MenuGui(String userName,Ticket[] book, MyQueue<Ticket> waitList,Flight[] flight) {
        
     
       setTitle("Malaya Flight Booking Menu");
        Image backgroundImage = new Image(getClass().getResource("menuflight.png").toExternalForm());
        ImageView backgroundImageView = new ImageView(backgroundImage);
        backgroundImageView.setFitWidth(1050);
        backgroundImageView.setFitHeight(600);
        backgroundImageView.setTranslateX(-115);
        backgroundImageView.setTranslateY(40);
        
                // Make the first image less opaque (adjust the opacity value as needed)
        backgroundImageView.setOpacity(0.8);
        
        // Set a dark color overlay to make the image darker
        backgroundImageView.setEffect(new javafx.scene.effect.ColorAdjust(0, 0.2, 0.2, 0.5));

        backgroundImageView.setPreserveRatio(false);


        Button btnSearch = new Button("Search Ticket  🔍");
        btnSearch.setStyle(
            "-fx-font-size: 16;" +
            "-fx-background-color: #CFC283;" +  
            "-fx-text-fill: black;"             
        );

        // Set hover effect
        btnSearch.setOnMouseEntered(e -> {
            btnSearch.setStyle("-fx-font-size:15;-fx-background-color: #CFC283;-fx-text-fill: black; ");
        });

        btnSearch.setOnMouseExited(e -> {
            btnSearch.setStyle("-fx-font-size: 16;-fx-background-color: #CFC283;-fx-text-fill: black; ");
        });
        
        Button btnBook = new Button("Booking  📝");
        btnBook.setStyle(
            "-fx-font-size: 16;" +
            "-fx-background-color: #6CD362;" +  
            "-fx-text-fill: black;"            
        );

        // Set hover effect
        btnBook.setOnMouseEntered(e -> {
            btnBook.setStyle("-fx-font-size:15;-fx-background-color: #6CD362;-fx-text-fill: black; ");
        });

        btnBook.setOnMouseExited(e -> {
            btnBook.setStyle("-fx-font-size:16;-fx-background-color: #6CD362;-fx-text-fill: black; ");
        });
        
        Button btnTicket = new Button("My Ticket  🛬");
        btnTicket.setStyle(
            "-fx-font-size: 16;" +
            "-fx-background-color: #F5F52D;" +  
            "-fx-text-fill: black;"             
        );

        // Set hover effect
        btnTicket.setOnMouseEntered(e -> {
            btnTicket.setStyle("-fx-font-size:15;-fx-background-color: #F5F52D;-fx-text-fill: black; ");
        });

        btnTicket.setOnMouseExited(e -> {
            btnTicket.setStyle("-fx-font-size:16;-fx-background-color: #F5F52D;-fx-text-fill: black; ");
        });
        
        Button btnLogout = new Button("Log Out  🔐");
        btnLogout.setStyle(
            "-fx-font-size: 16;" +
            "-fx-background-color: #FF5D52;" + 
            "-fx-text-fill: black;"             
        );

        // Set hover effect
        btnLogout.setOnMouseEntered(e -> {
            btnLogout.setStyle("-fx-font-size:15;-fx-background-color: #FF5D52;-fx-text-fill: black; ");
        });

        btnLogout.setOnMouseExited(e -> {
            btnLogout.setStyle("-fx-font-size:16;-fx-background-color: #FF5D52;-fx-text-fill: black; ");
        });

        btnBook.setOnAction(e -> openBookingGui(userName, book, waitList, flight));
        btnSearch.setOnAction(e -> openSearchGui(userName, book, waitList, flight));
        btnTicket.setOnAction(e -> openTicketGui(userName, book, waitList, flight));
        btnLogout.setOnAction(e -> openLoginGui(book, waitList, flight));

        HBox hbox = new HBox(btnSearch, btnBook, btnTicket, btnLogout);
        hbox.setSpacing(10);
        hbox.setPadding(new Insets(50));
        hbox.setAlignment(Pos.TOP_LEFT);

        StackPane root = new StackPane();
        root.getChildren().addAll(backgroundImageView, hbox);

        setScene(new Scene(root, 800, 500));
        
    }

     private void openLoginGui(Ticket[] book, MyQueue<Ticket> waitList,Flight[] flight) {
        LoginUserGui loginGui = new LoginUserGui(book,waitList,flight);
        loginGui.show(); 
        close(); 
    }
     
     private  void openSearchGui(String userName,Ticket[] book, MyQueue<Ticket> waitList,Flight[] flight){
         SearchGUI searchGUI= new SearchGUI(userName,book,waitList,flight);
         searchGUI.show();
         close();
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
