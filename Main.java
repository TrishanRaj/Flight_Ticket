
package flightbook;

import javafx.application.Application;
import javafx.stage.Stage;

class Flight {
    String flightName, flightTime, flightDate, flightWeek;
    int flightCapacity;

    public Flight(String flightName, String flightTime, String flightDate, int flightCapacity, String flightWeek) {
        this.flightName = flightName;
        this.flightTime = flightTime;
        this.flightDate = flightDate;
        this.flightCapacity = flightCapacity;
        this.flightWeek = flightWeek;
    }
}


//Ticket Information
class Ticket {
    String passengerName, passportNo, phoneNum, username, status, flightsName,flightTime,gender,departure,arrival,flightDate;
   

    public Ticket(String passengerName, String passportNo, String phoneNum, String username,
                  String flightsName, String flightDate,String flightTime, String status, String gender,String departure,String arrival) {
        this.passengerName = passengerName;
        this.passportNo = passportNo;
        this.phoneNum = phoneNum;
        this.username = username;
        this.flightsName = flightsName;
        this.departure=departure;
        this.arrival=arrival;
        this.gender=gender;
        this.flightDate=flightDate;
        this.flightTime = flightTime;
        this.status = status;
    }

    @Override
    public String toString() {
        return "| " + passportNo + " |\t" + passengerName + "\t| " + flightsName + "\t| Waiting\t|";
    }
     public boolean belongsToUser(String username) {
        return this.username.equals(username);
    }
}
//Waiting List Queue
class MyQueue<T> {
    
    public int tail, head, maxSize;
    T[] wait_List;
    
    public MyQueue(int a) {
        this.maxSize = a;
        this.head = 0;
        this.tail = 0;
        this.wait_List = (T[]) new Object[maxSize];      
    }
    
    public boolean isEmpty() {
        return tail == 0;        
    }
    
    public boolean isFull() {
        return tail == maxSize;
    }
    
    public void enqueue(T e) {
        if (isFull()) {
            System.out.println("The waiting list is full");
        } else {
            wait_List[tail] = e;
            tail++;
        }       
    }
    
    public T dequeue() {
        if (isEmpty()) {
            return (T) "The waiting list is empty";
        } else {
            T temp = wait_List[head];
            for (int a = 0; a < tail; a++) {
                wait_List[a] = wait_List[a + 1];
            }
            tail--;
            return temp;            
        }        
    }
    
    public T peek() {
        if (isEmpty()) {
            return (T) "The waiting list is empty";
        } else {
            return wait_List[head];
        }
    }

    //Display Waiting List
    public void displayWaitList() {
         for (int i = 0; i < tail; i++) {
            System.out.println(wait_List[i].toString());
            
        }        
    }   
}
public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Ticket[] book = new Ticket[3];
        MyQueue<Ticket> myq = new MyQueue<>(10);
         Flight[] malaya = new Flight[20];
        
         
        //Flight Mock Value
        malaya[0] = new Flight("Malaya 307", "11.30 AM", "02-01-2024", 90, "1");
        malaya[1] = new Flight("Malaya 308", "12.45 PM", "03-01-2024", 120, "1");
        malaya[2] = new Flight("Malaya 309", "02.00 PM", "04-01-2024", 80, "1");
        malaya[3] = new Flight("Malaya 310", "03.15 PM", "06-01-2024", 150, "1");
        malaya[4] = new Flight("Malaya 311", "04.30 PM", "08-01-2024", 90, "2");
        malaya[5] = new Flight("Malaya 312", "05.45 PM", "09-01-2024", 110, "2");
        malaya[6] = new Flight("Malaya 313", "07.00 PM", "12-01-2024", 130, "2");
        malaya[7] = new Flight("Malaya 314", "08.15 PM", "13-01-2024", 100, "2");
        malaya[8] = new Flight("Malaya 315", "09.30 PM", "15-01-2024", 120, "3");
        malaya[9] = new Flight("Malaya 316", "10.45 PM", "17-01-2024", 80, "3");
        malaya[10] = new Flight("Malaya 317", "11.00 AM", "18-01-2024", 90, "3");
        malaya[11] = new Flight("Malaya 318", "12.15 PM", "20-01-2024", 110, "3");
        malaya[12] = new Flight("Malaya 319", "01.30 PM", "22-01-2024", 130, "4");
        malaya[13] = new Flight("Malaya 320", "02.45 PM", "23-01-2024", 100, "4");
        malaya[14] = new Flight("Malaya 321", "04.00 PM", "25-01-2024", 120, "4");
        malaya[15] = new Flight("Malaya 322", "05.15 PM", "26-01-2024", 80, "4");
        malaya[16] = new Flight("Malaya 323", "06.30 PM", "28-01-2024", 110, "4");
        malaya[17] = new Flight("Malaya 324", "07.45 PM", "29-01-2024", 130, "5");
        malaya[18] = new Flight("Malaya 325", "09.00 PM", "30-01-2024", 100, "5");
        malaya[19] = new Flight("Malaya 326", "09.00 PM", "31-01-2024", 90, "5");

        Ticket num1 = new Ticket("John", "010711050221", "0126240537", "john123", "Malaya 307", "2024-01-02","12.45 PM", "Confirmed","Male","Malaysia","South Korea");
        Ticket num2 = new Ticket("Sarah", "010711050222", "0126240537", "john123", "Malaya 307", "2024-01-02","12.45 PM", "Confirmed","Female","Malaysia","South Korea");
        Ticket num3 = new Ticket("Mira", "010711050223", "0126240537", "john123", "Malay 307", "2024-01-02", "12.45 PM","Confirmed","Female","Malaysia","South Korea");
        Ticket num4 = new Ticket("Shira", "010711050224", "0126240537", "john123", "Malaya 307", "2024-01-02","12.45 PM" ,"Waiting","Female","Malaysia","South Korea");
        Ticket num5 = new Ticket("Shirays", "010711050225", "0126240537", "john123", "Malaya 307", "2024-01-02", "12.45 PM","Waiting","Female","Malaysia","South Korea");

        book[0] = num1;
        book[1] = num2;
        book[2] = num3;
        myq.enqueue(num4);
        myq.enqueue(num5);

        openLoginGui(primaryStage, book, myq,malaya);
    }

    private void openLoginGui(Stage primaryStage, Ticket[] book, MyQueue<Ticket> waitList,Flight[] flight) {
        LoginUserGui loginGui = new LoginUserGui(book, waitList,flight);
        loginGui.show();

    }
}
