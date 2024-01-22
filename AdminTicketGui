
package flightbook;

import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Callback;


public class AdminTicketGui extends Stage {
    
    public AdminTicketGui(String username, Ticket[] book, MyQueue<Ticket> waitList,Flight[] flight){
        
       setTitle("Melaya Flight Booking Ticket");

        Button btnBack = new Button("🔐");
        btnBack.setStyle("-fx-font-size: 20;");

        btnBack.setOnAction(e -> openLoginGui(book, waitList, flight));

         Button btnBookTicket = new Button("📝");
        btnBookTicket.setStyle("-fx-font-size: 20;");
        HBox hboxButtons = new HBox(createTopLeftPane(btnBack), createTopRightPane(btnBookTicket));
        hboxButtons.setSpacing(10);
        btnBookTicket.setOnAction(e -> openBookingAdminGui(username, book, waitList, flight));
        
        // Label for Flight Ticket Status
        Label flightStatusLabel = new Label("MALAYA ADMIN TICKET STATUS");
        flightStatusLabel.setTextFill(Color.BLUE);
        flightStatusLabel.setFont(Font.font("Serif", FontWeight.BOLD, 30));

        // Create TableView
        TableView<Ticket> tableView = new TableView<>();

        // Set the background color of the TableView
        tableView.setRowFactory(new Callback<TableView<Ticket>, TableRow<Ticket>>() {
            @Override
            public TableRow<Ticket> call(TableView<Ticket> param) {
                return new TableRow<Ticket>() {
                    @Override
                    protected void updateItem(Ticket item, boolean empty) {
                        super.updateItem(item, empty);

                        if (getIndex() % 2 == 0) {
                            // Apply even row color
                            setStyle("-fx-background-color: #C0E0F2;");
                        } else {
                            // Apply odd row color
                            setStyle("-fx-background-color: #E5E6E7;");
                        }
                    }
                };
            }
        });

        TableColumn<Ticket, String> idColumn = new TableColumn<>("ID");
        TableColumn<Ticket, String> nameColumn = new TableColumn<>("Name");
        TableColumn<Ticket, String> genderColumn = new TableColumn<>("Gender");
        TableColumn<Ticket, String> phoneColumn = new TableColumn<>("Phone Num");
        TableColumn<Ticket, String> departureColumn = new TableColumn<>("Departure");
        TableColumn<Ticket, String> arrivalColumn = new TableColumn<>("Arrival");
        TableColumn<Ticket, String> flightNameColumn = new TableColumn<>("Flight Name");
        TableColumn<Ticket, String> departureDateColumn = new TableColumn<>("Departure Date");
        TableColumn<Ticket, String> departureTimeColumn = new TableColumn<>("Departure Time");
        TableColumn<Ticket, String> statusColumn = new TableColumn<>("Status");

        idColumn.setPrefWidth(120); // Adjust the width as needed
        nameColumn.setPrefWidth(120); // Adjust the width as needed
        genderColumn.setPrefWidth(120); // Adjust the width as needed
        phoneColumn.setPrefWidth(120); // Adjust the width as needed
        departureColumn.setPrefWidth(120); // Adjust the width as needed
        arrivalColumn.setPrefWidth(120); // Adjust the width as needed
        flightNameColumn.setPrefWidth(120); // Adjust the width as needed
        departureTimeColumn.setPrefWidth(120); // Adjust the width as needed
         departureDateColumn.setPrefWidth(120); // Adjust the width as needed
        statusColumn.setPrefWidth(120); // Adjust the width as needed

        idColumn.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 13;");
        nameColumn.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 13;");
        genderColumn.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 13;");
        phoneColumn.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 13;");
        departureColumn.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 13;");
        arrivalColumn.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 13;");
        flightNameColumn.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 13;");
        departureTimeColumn.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 13;");
        departureDateColumn.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 13;");
        statusColumn.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 13;");

        idColumn.setCellFactory(col -> createCenterAlignedCell());
        nameColumn.setCellFactory(col -> createCenterAlignedCell());
        genderColumn.setCellFactory(col -> createCenterAlignedCell());
        phoneColumn.setCellFactory(col -> createCenterAlignedCell());
        departureColumn.setCellFactory(col -> createCenterAlignedCell());
        arrivalColumn.setCellFactory(col -> createCenterAlignedCell());
        flightNameColumn.setCellFactory(col -> createCenterAlignedCell());
        departureTimeColumn.setCellFactory(col -> createCenterAlignedCell());
        departureDateColumn.setCellFactory(col -> createCenterAlignedCell());
        statusColumn.setCellFactory(col -> createCenterAlignedCell());

        tableView.getColumns().addAll(idColumn, nameColumn,genderColumn,phoneColumn,departureColumn,arrivalColumn, flightNameColumn,departureDateColumn ,departureTimeColumn, statusColumn);

        TableColumn<Ticket, Void> editColumn = new TableColumn<>("");
        editColumn.setCellFactory(param -> new TableCell<>() {
           
            //Edit Button
            private final Button editButton = new Button("Edit");
            {
                // Set up the action for the "Edit" button
                editButton.setOnAction(event -> {
                    Ticket ticket = getTableView().getItems().get(getIndex());
                    int selectedIndex = getIndex();
                    handleEditTicket(ticket, selectedIndex,username,book,waitList,flight);
                });
                editButton.setPrefSize(70, 40);
                editButton.setStyle("-fx-font-size: 13; -fx-background-color: #4CAF50; -fx-text-fill: black;");
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(editButton);
                }
            }
        });

        editColumn.setStyle("-fx-alignment: CENTER;");
        tableView.getColumns().add(editColumn);

        // Create a new column for "Cancel" buttons
        TableColumn<Ticket, Void> cancelColumn = new TableColumn<>("");
        cancelColumn.setCellFactory(param -> new TableCell<>() {

            private final Button cancelButton = new Button("Cancel");

            {
                // Set up the action for the "Cancel" button
                cancelButton.setOnAction(event -> {
                     Optional<ButtonType> result = showConfirmationAlert("Cancel Booking", "Are you sure you want to cancel?");
            if (result.isPresent() && result.get() == ButtonType.OK) {
                     Ticket ticket = getTableView().getItems().get(getIndex());
                    int selectedIndex = getIndex();
                    handleCancelTicket(book, waitList, username, selectedIndex,ticket,flight);
            }
                   
                });
                cancelButton.setPrefSize(70, 40);
                cancelButton.setStyle("-fx-font-size: 13; -fx-background-color: #EE9898; -fx-text-fill: black;");
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(cancelButton);
                }
            }
        });

        cancelColumn.setStyle("-fx-alignment: CENTER;");
        tableView.getColumns().add(cancelColumn);

        // Create data
        ObservableList<Ticket> data = FXCollections.observableArrayList();

        // Set data to the TableView directly
        idColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().passportNo));
        nameColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().passengerName));
        phoneColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().phoneNum));
        genderColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().gender));
        departureColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().departure));
        arrivalColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().arrival));
        flightNameColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().flightsName));
        departureDateColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().flightDate));
        departureTimeColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().flightTime));
        statusColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().status));

        tableView.setItems(data);

        BorderPane borderPane = new BorderPane();
         borderPane.setTop(hboxButtons);

        // Add margin to create space between the label and the table
        BorderPane.setMargin(flightStatusLabel, new Insets(10, 0, 10, 0));

        borderPane.setCenter(flightStatusLabel);
        BorderPane.setAlignment(flightStatusLabel, Pos.CENTER);
        borderPane.setBottom(tableView);
        borderPane.setStyle("-fx-background-color: #EEF7F0;");

        setScene(new Scene(borderPane, 1380, 500));
        setResizable(false);

        // Coding Function
        boolean view_confirm = false, view_wait = false;

        for (Ticket ticket : book) {
             if (ticket != null) {
                data.add(ticket);
                view_confirm = true;
             }
        }

        // View for WaitingList
        MyQueue<Ticket> temp_queue = new MyQueue<>(waitList.maxSize);
        while (!waitList.isEmpty()) {
            Ticket temp_View = waitList.peek();
            if (temp_View != null ) {
                data.add(temp_View);
                view_wait = true;
           }
            temp_queue.enqueue(waitList.dequeue());
        }

        while (!temp_queue.isEmpty()) {
            waitList.enqueue(temp_queue.dequeue());
        }

        if (!view_confirm && !view_wait) {
            System.out.println("There is no booking under this username. Thank You.");
        }
    
    }
    private TableCell<Ticket, String> createCenterAlignedCell() {
        return new TableCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item);
                    setAlignment(javafx.geometry.Pos.CENTER);
                }
            }
        };
    }

    private Optional<ButtonType> showConfirmationAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        return alert.showAndWait();
    }

    private void handleEditTicket(Ticket ticket, int selectedIndex,String username, Ticket[] book, MyQueue<Ticket> waitList,Flight[] flight) {
        // Implement the logic for editing a ticket
        System.out.println("Editing ticket at index " + selectedIndex + ": " + ticket.passportNo);
        EditTicketAdminGui editTicketAdminGui = new EditTicketAdminGui(username,book,waitList,ticket,flight);
        editTicketAdminGui.show();
        close();
        
    }

    private void handleCancelTicket(Ticket[] book, MyQueue<Ticket> myq, String username, int selectedIndex,Ticket ticket,Flight[] flight) {
        cancelTicket(book, myq, username, ticket);
        openAdminTicketGui(username, book, myq,flight);
    }

    public void cancelTicket(Ticket[] book, MyQueue<Ticket> myq, String username, Ticket ticket) {
        boolean cancelBook = false, cancelWait = true;

       // Cancel ticket in confirmed booking
    for (int a = 0; a < book.length; a++) {
        // Loop to check the username
           
                    // Change the condition from == to equals
        if (book[a].passportNo.equals(ticket.passportNo)) {
            // Found the specific record to cancel
            for (; a < book.length - 1; a++) {
                // Loop to move forward the value after delete
                book[a] = book[a + 1];
            }
            book[book.length - 1] = null;
            cancelBook = true;

            // If the specific record is canceled, fill its place with the next user in the waitlist
            if (!myq.isEmpty() && cancelBook) {
                Ticket change = myq.peek();
                book[book.length - 1] = new Ticket(change.passengerName, change.passportNo,
                        change.phoneNum, change.username, change.flightsName, change.flightDate, change.flightTime, "Confirmed", change.gender, change.departure, change.arrival);
                myq.dequeue();
            }
        }

        
    }
        // Cancel for waiting list queue
        if (!cancelBook) {
            MyQueue<Ticket> cancelQueue = new MyQueue<>(myq.maxSize);
            while (!myq.isEmpty()) {
                Ticket canWait = myq.dequeue();
                // Dequeue value in the main list to the temporary queue
                if (!canWait.passportNo.equals(ticket.passportNo)) {
                    // Enqueue the value if the username is different.
                    cancelQueue.enqueue(canWait);      
                }
            }
            while (!cancelQueue.isEmpty()) {
                // Enqueue back the value from the temporary queue to the main queue
                myq.enqueue(cancelQueue.dequeue());
            }
            cancelWait = true;
        }

        // Username that doesn't book any ticket couldn't access it.
        if (!cancelBook && !cancelWait) {
            System.out.println("There is no booking under this username. Thank You.");
        }
    }
    
     private HBox createTopRightPane(Button btnBack) {
        HBox topRightPane = new HBox(btnBack);
        topRightPane.setAlignment(Pos.TOP_RIGHT);
        topRightPane.setPadding(new Insets(10));
        return topRightPane;
    }

    private HBox createTopLeftPane(Button btn) {
        HBox topLeftPane = new HBox(btn);
        topLeftPane.setAlignment(Pos.CENTER);
        topLeftPane.setPadding(new Insets(10));
        return topLeftPane;
    }
      //AdminTicketGui
     private void openAdminTicketGui(String userName,Ticket[] book, MyQueue<Ticket> waitList,Flight[] flight) {
        AdminTicketGui adminTicketGui = new AdminTicketGui(userName,book,waitList,flight);
        adminTicketGui.show();
        close(); // Close the current LoginGui window
    }
    
      //AdminTicketGui
     private void openBookingAdminGui(String userName,Ticket[] book, MyQueue<Ticket> waitList,Flight[] flight) {
        BookingAdminGui bookingAdminGui = new BookingAdminGui(userName,book,waitList,flight);
        bookingAdminGui.show();
        close(); // Close the current LoginGui window
    }
    
     //LogOut
     private void openLoginGui(Ticket[] book, MyQueue<Ticket> waitList,Flight[] flight) {
        LoginUserGui loginGui = new LoginUserGui(book,waitList,flight);
        loginGui.show(); 
        close(); 
    }
    
}
