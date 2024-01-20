/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package flightbook;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javafx.application.Application;
import javafx.stage.Stage;


//Ticket Information
class Ticket {
    String passengerName, passportNo, phoneNum, username, status, flightName,flightTime;
   

    public Ticket(String passengerName, String passportNo, String phoneNum, String username,
                  String flightName, String flightTime, String status) {
        this.passengerName = passengerName;
        this.passportNo = passportNo;
        this.phoneNum = phoneNum;
        this.username = username;
        this.flightName = flightName;
        this.flightTime = flightTime;
        this.status = status;
    }

    @Override
    public String toString() {
        return "| " + passportNo + " |\t" + passengerName + "\t| " + flightName + "\t| Waiting\t|";
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

        //Trial Value
        LocalDateTime departureTime = LocalDateTime.parse("2024-12-09T11:50");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String time = formatter.format(departureTime);

        Ticket num1 = new Ticket("John", "010711050223", "0126240537", "john123", "Malaya307", time, "Confirmed");
        Ticket num2 = new Ticket("Sarah", "010711050223", "0126240537", "john123", "Malaya307", time, "Confirmed");
        Ticket num3 = new Ticket("Mira", "010711050223", "0126240537", "john123", "Malay307", time, "Confirmed");
        Ticket num4 = new Ticket("Shira", "010711050223", "0126240537", "john123", "Malaya307", time, "Waiting");
        Ticket num5 = new Ticket("Shirays", "010711050223", "0126240537", "john123", "Malaya307", time, "Waiting");

        book[0] = num1;
        book[1] = num2;
        book[2] = num3;
        myq.enqueue(num4);
        myq.enqueue(num5);

        openLoginGui(primaryStage, book, myq);
    }

    private void openLoginGui(Stage primaryStage, Ticket[] book, MyQueue<Ticket> waitList) {
        LoginUserGui loginGui = new LoginUserGui(book, waitList);
        loginGui.show();
        // Close the current MenuGui window
    }
}
