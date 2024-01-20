package flightbook;

import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Callback;

public class TicketGui extends Stage {

    public TicketGui(String username, Ticket[] book, MyQueue<Ticket> waitList) {
        setTitle("Melaya Flight Booking Ticket");

        Button btnBack = new Button("Go Back");

        btnBack.setOnAction(e -> openMenuGui(username, book, waitList));

        HBox hboxButtons = new HBox(btnBack);
        hboxButtons.setSpacing(10);

        // Label for Flight Ticket Status
        Label flightStatusLabel = new Label("MALAYA FLIGHT TICKET STATUS");
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
                            setStyle("-fx-background-color: #F7E2C8;");
                        } else {
                            // Apply odd row color
                            setStyle("-fx-background-color: #F4EDED;");
                        }
                    }
                };
            }
        });

        TableColumn<Ticket, String> idColumn = new TableColumn<>("ID");
        TableColumn<Ticket, String> nameColumn = new TableColumn<>("Name");
        TableColumn<Ticket, String> flightNameColumn = new TableColumn<>("Flight Name");
        TableColumn<Ticket, String> departureTimeColumn = new TableColumn<>("Departure Time");
        TableColumn<Ticket, String> statusColumn = new TableColumn<>("Status");

        idColumn.setPrefWidth(120); // Adjust the width as needed
        nameColumn.setPrefWidth(120); // Adjust the width as needed
        flightNameColumn.setPrefWidth(120); // Adjust the width as needed
        departureTimeColumn.setPrefWidth(120); // Adjust the width as needed
        statusColumn.setPrefWidth(120); // Adjust the width as needed

        idColumn.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 13;");
        nameColumn.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 13;");
        flightNameColumn.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 13;");
        departureTimeColumn.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 13;");
        statusColumn.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 13;");

        idColumn.setCellFactory(col -> createCenterAlignedCell());
        nameColumn.setCellFactory(col -> createCenterAlignedCell());
        flightNameColumn.setCellFactory(col -> createCenterAlignedCell());
        departureTimeColumn.setCellFactory(col -> createCenterAlignedCell());
        statusColumn.setCellFactory(col -> createCenterAlignedCell());

        // Add columns to the TableView
        tableView.getColumns().addAll(idColumn, nameColumn, flightNameColumn, departureTimeColumn, statusColumn);

        // Create a new column for "Edit" buttons
        TableColumn<Ticket, Void> editColumn = new TableColumn<>("");
        editColumn.setCellFactory(param -> new TableCell<>() {
            private final Button editButton = new Button("Edit");

            {
                // Set up the action for the "Edit" button
                editButton.setOnAction(event -> {
                    Ticket ticket = getTableView().getItems().get(getIndex());
                    int selectedIndex = getIndex();
                    handleEditTicket(ticket, selectedIndex);
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
                    handleCancelTicket(book, waitList, username, selectedIndex);
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
        flightNameColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().flightName));
        departureTimeColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().flightTime));
        statusColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().status));

        // Set data to the TableView
        tableView.setItems(data);

        // Create a BorderPane to include Label, TableView, and HBox
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(hboxButtons);
        borderPane.setCenter(flightStatusLabel);
        BorderPane.setAlignment(flightStatusLabel, javafx.geometry.Pos.CENTER);
        borderPane.setBottom(tableView);

        setScene(new Scene(borderPane, 800, 500));
        setResizable(false);

        // Coding Function
        boolean view_confirm = false, view_wait = false;

        for (Ticket ticket : book) {
            // Check if the ticket belongs to the specified user
            if (ticket != null && ticket.belongsToUser(username)) {
                data.add(ticket);
                view_confirm = true;
            }
        }

        // View for WaitingList
        MyQueue<Ticket> temp_queue = new MyQueue<>(waitList.maxSize);
        while (!waitList.isEmpty()) {
            Ticket temp_View = waitList.peek();
            // Check if the ticket belongs to the specified user
            if (temp_View != null && temp_View.belongsToUser(username)) {
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

    // Helper method to create a center-aligned cell
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

    private void openMenuGui(String userName, Ticket[] book, MyQueue<Ticket> waitList) {
        MenuGui menuGui = new MenuGui(userName, book, waitList);
        menuGui.show();
        close();
    }

    private void openTicketGui(String userName, Ticket[] book, MyQueue<Ticket> waitList) {
        TicketGui ticketGui = new TicketGui(userName, book, waitList);
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

    private void handleEditTicket(Ticket ticket, int selectedIndex) {
        // Implement the logic for editing a ticket
        System.out.println("Editing ticket at index " + selectedIndex + ": " + ticket);
        // Now you have the index of the selected item (selectedIndex) for further processing
        // You can perform editing operations here
    }

    private void handleCancelTicket(Ticket[] book, MyQueue<Ticket> myq, String username, int selectedIndex) {
        // Implement the logic for canceling a ticket
        System.out.println("Canceling ticket at index " + selectedIndex);
        cancelTicket(book, myq, username, selectedIndex);
        openTicketGui(username, book, myq);
    }

    public void cancelTicket(Ticket[] book, MyQueue<Ticket> myq, String username, int recordIndex) {
        boolean cancelBook = false, cancelWait = true;

       // Cancel ticket in confirmed booking
    for (int a = 0; a < book.length; a++) {
        // Loop to check the username
            if (a == recordIndex) {
                // Found the specific record to cancel
                for (int b = a; b < book.length - 1; b++) {
                    // Loop to move forward the value after delete
                    book[b] = book[b + 1];
                }
                book[book.length - 1] = null;
                cancelBook = true;

                // If the specific record is canceled, fill its place with the next user in the waitlist
                if (!myq.isEmpty() && cancelBook) {
                    Ticket change = myq.peek();
                    book[book.length - 1] = new Ticket(change.passengerName, change.passportNo,
                            change.phoneNum, change.username, change.flightName, change.flightTime, "Confirmed");
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
                if (!canWait.username.equals(username)) {
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
    
    
}
