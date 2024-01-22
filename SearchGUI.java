
package flightbook;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.WeekFields;
import java.util.Locale;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Callback;

public class SearchGUI extends Stage {

    public SearchGUI(String username, Ticket[] book, MyQueue<Ticket> waitList, Flight[] flight) {
        setTitle("Malaya Search Flights");

        Button btnBack = new Button("🔙");
        btnBack.setStyle("-fx-font-size: 20;");
        btnBack.setOnAction(e -> openMenuGui(username, book, waitList, flight));
        HBox hboxButtons = new HBox(btnBack);
        hboxButtons.setSpacing(10);

        Label flightStatusLabel = new Label("SEARCH MALAYA FLIGHTS");
        flightStatusLabel.setTextFill(Color.BLUE);
        flightStatusLabel.setFont(Font.font("Serif", FontWeight.BOLD, 30));

        Label dateLabel = new Label("Select Date:");
        DatePicker datePicker = new DatePicker();
        datePicker.setPromptText("Select a date");
        Button btnSearch = new Button("Search");
        btnSearch.setStyle(
            "-fx-font-size: 14;" +
            "-fx-background-color: #6CD362;" + 
            "-fx-text-fill: white;"             
        );

        // Set hover effect
        btnSearch.setOnMouseEntered(e -> {
            btnSearch.setStyle("-fx-background-color: #6CD362;-fx-text-fill: white; ");
        });

        btnSearch.setOnMouseExited(e -> {
            btnSearch.setStyle("-fx-font-size:14;-fx-background-color: #6CD362;-fx-text-fill: white; ");
        });
        
        Button btnAllFlights = new Button("Show All Flights");
        btnAllFlights.setStyle(
            "-fx-font-size: 14;" +
            "-fx-background-color: #3498db;" +  // Blue background color
            "-fx-text-fill: white;"             // White text color
        );

        // Set hover effect
        btnAllFlights.setOnMouseEntered(e -> {
            btnAllFlights.setStyle("-fx-background-color: #2980b9;-fx-text-fill: white; ");
        });

        btnAllFlights.setOnMouseExited(e -> {
            btnAllFlights.setStyle("-fx-font-size:15;-fx-background-color: #3498db;-fx-text-fill: white; ");
        });

        
        HBox searchBox = new HBox(dateLabel, datePicker, btnSearch,btnAllFlights);
        searchBox.setSpacing(10);
        searchBox.setAlignment(javafx.geometry.Pos.CENTER);

        // Set the time only to January
        EventHandler<ActionEvent> eventHandler = event -> {
            LocalDate selectedDate = datePicker.getValue();
            if (selectedDate != null) {
                WeekFields weekFields = WeekFields.of(Locale.getDefault());
                int weekNumber = selectedDate.get(weekFields.weekOfWeekBasedYear());

                if (selectedDate.getYear() != 2024 || selectedDate.getMonth() != Month.JANUARY) {
                    datePicker.setValue(LocalDate.of(2024, Month.JANUARY, 1));
                }
            }
        };

        datePicker.setOnAction(eventHandler);

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

        // Create TableView
        TableView<Flight> tableView = new TableView<>();
        btnSearch.setOnAction(e -> searchFlights(datePicker.getValue(), flight, tableView));
        btnAllFlights.setOnAction(e -> showAllFlights(flight, tableView));
        // Set the background color of the TableView
        tableView.setRowFactory(new Callback<TableView<Flight>, TableRow<Flight>>() {
            @Override
            public TableRow<Flight> call(TableView<Flight> param) {
                return new TableRow<Flight>() {
                    @Override
                    protected void updateItem(Flight item, boolean empty) {
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

        TableColumn<Flight, String> flightNameColumn = new TableColumn<>("Flight Name");
        TableColumn<Flight, String> depDateColumn = new TableColumn<>("Departure Date");
        TableColumn<Flight, String> depTimeColumn = new TableColumn<>("Departure Time");
        TableColumn<Flight, String> flightCapColumn = new TableColumn<>("Flight Capacity");
        TableColumn<Flight, String> departureColumn = new TableColumn<>("Departure");
        TableColumn<Flight, String> arrivalColumn = new TableColumn<>("Arrival");

        flightNameColumn.setPrefWidth(120);
        depDateColumn.setPrefWidth(120);
        depTimeColumn.setPrefWidth(120);
        flightCapColumn.setPrefWidth(120);
        departureColumn.setPrefWidth(120);
        arrivalColumn.setPrefWidth(120);

        flightNameColumn.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 13;");
        depDateColumn.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 13;");
        depTimeColumn.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 13;");
        flightCapColumn.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 13;");
        departureColumn.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 13;");
        arrivalColumn.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 13;");

        flightNameColumn.setCellFactory(col -> createCenterAlignedCell());
        depDateColumn.setCellFactory(col -> createCenterAlignedCell());
        depTimeColumn.setCellFactory(col -> createCenterAlignedCell());
        flightCapColumn.setCellFactory(col -> createCenterAlignedCell());
        departureColumn.setCellFactory(col -> createCenterAlignedCell());
        arrivalColumn.setCellFactory(col -> createCenterAlignedCell());

        tableView.getColumns().addAll(flightNameColumn, depDateColumn, depTimeColumn, departureColumn, arrivalColumn,
                flightCapColumn);

        ObservableList<Flight> data = FXCollections.observableArrayList();

        for (Flight test : flight) {
            data.add(test);
        }

        flightNameColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().flightName));
        depDateColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().flightDate));
        depTimeColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().flightTime));
        departureColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty("Malaysia"));
        arrivalColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty("South Korea"));
        flightCapColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(String.valueOf(cellData.getValue().flightCapacity)));
        tableView.setItems(data);

        VBox bottomVBox = new VBox(searchBox, tableView);
        VBox.setMargin(searchBox, new javafx.geometry.Insets(10, 0, 10, 0));
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(hboxButtons);
        borderPane.setCenter(flightStatusLabel);
        BorderPane.setAlignment(flightStatusLabel, javafx.geometry.Pos.CENTER);
        borderPane.setBottom(bottomVBox);
        BorderPane.setMargin(flightStatusLabel, new javafx.geometry.Insets(20, 0, 20, 0)); // Add vertical spacing
         
        BackgroundFill backgroundFill = new BackgroundFill(Color.web("#F7F1EE"), CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(backgroundFill);
        borderPane.setBackground(background);
        setScene(new Scene(borderPane, 800, 600));
        setResizable(false);
    }

    private void openMenuGui(String userName, Ticket[] book, MyQueue<Ticket> waitList, Flight[] flight) {
        MenuGui menuGui = new MenuGui(userName, book, waitList, flight);
        menuGui.show();
        close();
    }

    private TableCell<Flight, String> createCenterAlignedCell() {
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
      private void showAllFlights(Flight[] flights, TableView<Flight> tableView) {
        ObservableList<Flight> allFlights = FXCollections.observableArrayList(flights);
        tableView.setItems(allFlights);
    }
      
    private void searchFlights(LocalDate selectedDate, Flight[] flights, TableView<Flight> tableView) {
    if (selectedDate != null) {
        WeekFields weekFields = WeekFields.of(Locale.getDefault());
        int selectedWeekNumber = selectedDate.get(weekFields.weekOfWeekBasedYear());

        // Filter flights based on the selected week
        ObservableList<Flight> filteredFlights = FXCollections.observableArrayList();
        for (Flight flight : flights) {
          
            String flightWeekNumber = flight.flightWeek; 
            int flightWeekValue = Integer.parseInt(flightWeekNumber);

            if (flightWeekValue == selectedWeekNumber) {
                filteredFlights.add(flight);
            }
        }

        tableView.setItems(filteredFlights);
    }
}
    
    

}
