/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ds_flightbookingsystem;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Acer
 */
class Ticket {
    String passengerName,passportNo,phoneNum,username,status;
    
    public Ticket(String passengerName, String passportNo, String phoneNum, String username,String status) {
        
        this.passengerName = passengerName;
        this.passportNo = passportNo;
        this.phoneNum = phoneNum;
        this.username=username;
        this.status=status;      
    }
  @Override
    public String toString() {

            return "| "+passportNo+" |\t"+passengerName+"\t| Waiting\t|";  
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

//Main Page
public class FlightBook {
    
    public static void main(String[] args) {
        
        boolean exit = false;
        String personal_info=null;
        Ticket[] book = new Ticket[3];
        MyQueue<Ticket> myq = new MyQueue<>(10);
              
       //Trial Value 
        Ticket num1 = new Ticket("John", "010711050223", "0126240537","john123","Confirmed");
        Ticket num2 = new Ticket("Sarah", "010711050223", "0126240537","sarah113","Confirmed");
        Ticket num3 = new Ticket("Mira", "010711050223", "0126240537","mira123","Confirmed");
        Ticket num4 = new Ticket("Shira", "010711050223", "0126240537","shira123","Waiting");
        Ticket num5 = new Ticket("Lishnu", "010711050223", "0126240537","lishnu123","Waiting");
        Ticket num6 = new Ticket("Sharwin", "010711050523", "0126240537","shar123","Waiting");
        
        book[0]=num1;
        book[1]=num2;
        book[2]=num3;
        myq.enqueue(num4);
        myq.enqueue(num5);
        myq.enqueue(num6);

        do {
           
            if(personal_info==null){
                personal_info=Login();
            }
            //Menu
            int option;
            System.out.println("Welcome to Cloud Wings");
            System.out.println("*************************");            
            System.out.println("1. Search for flight");
            System.out.println("2. Book a ticket");
            System.out.println("3. Edit ticket information");
            System.out.println("4. View ticket status");
            System.out.println("5. Cancel ticket");
            System.out.println("6. Help us with a Feedback");
            System.out.println("7. Log Out\n");
            System.out.print("Please select: ");
            Scanner key = new Scanner(System.in);
            option = key.nextInt();
            
            switch (option) {
                
                case 1:
                    getFlightInfo();
                    break;
                case 2:
                    bookTicket(book, myq,personal_info);
                    break;
                case 3:
                    editTicketInfo(book,myq,personal_info);
                    break;
                case 4:
                    viewTicketStatus(book,myq,personal_info);
                    break;
                case 5:
                    cancelTicket(book,myq,personal_info);
                    break;
                case 6 :
                    collectFeedback();
                    break;
                case 7:
                   personal_info=LogOut(personal_info);
                    break;
                default:
                    System.out.println("Wrong number inputed, Please Try Again.");
                    break;
                
            }
        } while (exit == false);
        
        
    }

    //Personal Information [Done]  //Conside everyone who uses the system has the username.
    public static String Login(){

        String username,password;
        Scanner logkey=new Scanner(System.in);
        System.out.print("Username: ");
        username=logkey.nextLine();
        System.out.print("Password: ");
        password=logkey.nextLine();
        return username;
    }
    
    public static void getFlightInfo(){
        Scanner input = new Scanner (System.in);
        final int WEEK_1 = 1;   
        final int WEEK_2 = 2;
        final int WEEK_3 = 3;
        final int WEEK_4 = 4;
        final int WEEK_5 = 5;
        
        System.out.print("Enter the month of your travel: ");
        String month = input.nextLine();
        if (!month.equals("January") && !month.equals("February") && !month.equals("March") &&
            !month.equals("May") && !month.equals("June") && !month.equals("July") &&
            !month.equals("August") && !month.equals("September") && !month.equals("October") &&
            !month.equals("November") && !month.equals("December")) {
            
                System.out.println("Invalid month. Please input another month");
        }

        else {
         
        System.out.print("Enter the week of your travel (Week 1/Week 2/Week 3/Week 4/Week 5): Week ");
        int week = input.nextInt();
        
        switch (week) {
            case WEEK_1:
                BookingFlight(week, month, 1, 7);
                break;
            case WEEK_2:
                BookingFlight(week, month, 8, 14);
                break;
            case WEEK_3:
                BookingFlight(week, month, 15, 21);
                break;
            case WEEK_4:
                BookingFlight(week, month, 22, 28);
                break;
            case WEEK_5:
                switch(month) {
                    case "January" , "March" , "May" , "July" , "August" , "October" , "December" :
                        BookingFlight(week , month , 29 , 31);
                        break;
                    case "February" :
                        System.out.println("Invalid week for this particular month. Please choose another week");
                        break;
                    case "April" , "June" , "Septmber" , "November" :
                        BookingFlight(week , month , 29 , 30);
                        break;
                }        
                break;
            default:
                System.out.println("Invalid week selection. Please enter a value between 1 to 5.");
            break;
        
            }
        }    
    }
    
    
    private static void BookingFlight (int week, String month, int starting, int ending) {
        
        Random random = new Random ();
        Scanner scan = new Scanner (System.in);
        
        System.out.println("You have entered Week " + week + ". You will be travelling from " + starting + " of " + month + " to " + ending + " of " + month + ".");
        
        System.out.print("Enter number of tickets you would like to purchase: ");
        int tickets = scan.nextInt();
        
        System.out.println("\n---FLIGHT DETAILS---");
        
        
        
        for (int i = 0; i < tickets + 5; i++) {
            System.out.println("Flight " + i + ": Malaya " + (random.nextInt(900) + 100) + "\nDeparture Time: " + (random.nextInt(12) + 1) + "AM");
            System.out.println("----------");
        }

        System.out.print("\nChoose Your Flight : Flight ");
        int flight = scan.nextInt();
        
        System.out.println("You have chosen Flight " + flight + ".");
        
        System.out.print("Confirm booking? Yes or No? ");
        String confirm = scan.next();
        
        if (confirm.equalsIgnoreCase("Yes")) {
            System.out.println("\n" +"You have sucessfully booked " + tickets + " ticket(s) for Flight " + flight + "!");
        }
        else {
            System.out.println("We're sorry to see you go. Thank you for choosing us.");
        }
        
    }   
     
    // 2.Book Ticket [Done]
    public static void bookTicket(Ticket[] book, MyQueue<Ticket> waitList, String username) {   //In parameter have the queue for waiting list and confirm_booked array     
        
        String passengerName, passportNo, phoneNum;   //Information needed to book the flight.
        Scanner obj = new Scanner(System.in);
        //Key in all the information
        System.out.print("Passanger Name: ");
        passengerName = obj.nextLine();
        System.out.print("Passport Number: ");
        passportNo = obj.nextLine();
        System.out.print("Phone Number: ");
        phoneNum = obj.nextLine();
        
        Ticket myTicket = new Ticket(passengerName, passportNo, phoneNum,username,null); //Send the value to Ticket Class
        boolean booked = false;  //Booked set to false beacuse so far no booking.
        for (int a = 0; a < book.length; a++) {  //Loop the book array to check there is empty place to fill.
            if (book[a] == null) {
                book[a].status="Confirmed";
                book[a] = myTicket;
                booked = true;
                break;
            }                     
        }
        
        if (booked == false) {   //If the ocnfirm book is full, the user will be added in the waitlist queue
            getFlightInfo();
            //System.out.println("\nSuccessfully Booked"); 
            myTicket.status="Waiting";
            waitList.enqueue(myTicket);  //Enqueue the value into waitlist.
        }       
    }

    // 3.Edit Passanger Information [Done]
    public static void editTicketInfo(Ticket[] book, MyQueue<Ticket>waitList,String user_login) {
        
        Scanner edit=new Scanner(System.in);
        boolean found_confirm=false;  //Set the info found in booking as false.

        //Edit for confirmed booking passenger
        for (Ticket book1 : book) {   //For loop used to ready all the information in booking array
            if (book1.username == null ? user_login == null : book1.username.equals(user_login)) { //Validation for username in confrim booking
                
                //Information that needed to be edited
                System.out.print("Passenger Name:");
                book1.passengerName = edit.nextLine();
                System.out.print("Passport Num: ");
                book1.passportNo = edit.nextLine();
                System.out.print("Phone Num: ");
                book1.phoneNum = edit.nextLine();
                System.out.println("\nSuccessfully Edited");
                found_confirm=true;    //Passenger is found in booking table.
                return;
            }      
        }
        
        // 3.Edit for waiting list passenger    
        MyQueue<Ticket>tempQueue=new MyQueue<>(waitList.maxSize); // Create temporary queue to store info of waitList queue
        boolean found_wait=false; //Set the info found in waitlist as false.
        
        while (!waitList.isEmpty()) {  //Loop until waitlist to get empty fully.
        Ticket temp_waitList=waitList.peek();   //Peek is the info to be enqueue first (head)
            if (temp_waitList.username.equals(user_login)) { //Validation for username in waitlist
                
                //Information of user in waiting list
                System.out.print("Passenger Name:");
                temp_waitList.passengerName = edit.nextLine();
                System.out.print("Passport Num: ");
                temp_waitList.passportNo = edit.nextLine();
                System.out.print("Phone Num: ");
                temp_waitList.phoneNum = edit.nextLine();
                System.out.println("\nSuccessfully Edited");
                found_wait=true;
            }
            tempQueue.enqueue(waitList.dequeue());  //Add the peak value of waitlist in tempQueue, and dequeue the peak value of WaitList
        }
        
        //Once waitlist is fully empty, add back all the value from tempQueue to WaitList.
        while(!tempQueue.isEmpty()){
            waitList.enqueue(tempQueue.dequeue());
        }
        
        //NO access if no booking.
        if(found_wait==false && found_confirm==false){
             System.out.println("There is no booking under this username. Thank You.");    
        }
            }

    // 4. View all booking and waitlist status  [Done]
    public static void viewTicketStatus(Ticket[] book,MyQueue<Ticket> waitList,String username ){
        boolean view_confirm=false,view_wait=false;  //Similar to editticket coding
        
        for (Ticket ticket: book) {
            if(ticket.username.equals(username))        
            {
              System.out.println("\n****************************");
              System.out.println("View Statue");
              System.out.println("");
              System.out.println("______________________________________________");
              System.out.println("|\tID\t|\tNAME\t|\tStatus\t|");
              System.out.println("| "+ticket.passportNo+" |\t"+ticket.passengerName+"\t|"+ ticket.status+"\t|");   
              System.out.println("______________________________________________");
              System.out.println("");
              view_confirm=true;
              return;               
            }     
        }
        
        //View for WaitingList  (Similar to edit ticket_coding
        MyQueue<Ticket> temp_queue=new MyQueue<>(waitList.maxSize);
        while(!waitList.isEmpty()){
            Ticket temp_View=waitList.peek();
            if(temp_View.username.equals(username)){
              System.out.println("\n****************************");
              System.out.println("View Statue");
              System.out.println("");
              System.out.println("______________________________________________");
              System.out.println("|\tID\t|\tNAME\t|\tStatus\t|");
              System.out.println("| "+temp_View.passportNo+" |\t"+temp_View.passengerName+"\t|"+ temp_View.status+"\t|");   
              System.out.println("______________________________________________");
              System.out.println("");
              view_wait=true;   
            }
            temp_queue.enqueue(waitList.dequeue());            
        }
        
        while(!temp_queue.isEmpty()){
            waitList.enqueue(temp_queue.dequeue());
        }
        
        if(view_confirm==false && view_wait==false){   //If the user doesnt have booking nothing will show.
           System.out.println("There is no booking under this username. Thank You.");     
        }  
    }
    
    // 5.Cancel_Ticket [DONE]
    public static void cancelTicket(Ticket[] book,MyQueue<Ticket> myq,String username){
        
        int ans;
        Boolean cancel_book=false,cancel_wait=true;
        Scanner confirm=new Scanner(System.in);
        System.out.print("Are you sure to cancel the ticket? [ 1.Yes| 2.No ]: ");  //Confirmation to delete
        ans=confirm.nextInt();
        if(ans==1){   
           
            //Cancel ticket in confirmed booking
            for(int a=0;a<book.length;a++){               //Loop to check the username
               if(book[a].username.equals(username)){ 

                 for(;a<book.length-1;a++)  {            //Loop to move forward the value after delete
                   book[a].passengerName=book[a+1].passengerName;
                   book[a].passportNo=book[a+1].passportNo;
                   book[a].phoneNum=book[a+1].phoneNum;
                   book[a].username=book[a+1].username;
                   book[a].status="Confirmed";  
                   cancel_book=true;
                 }                
                  Ticket change=myq.peek();   //If the confirm book user is deleted, the peek user in waitlist will be confirmed
                  book[book.length-1].passengerName= change.passengerName;
                  book[book.length-1].passportNo= change.passportNo;
                  book[book.length-1].phoneNum= change.phoneNum;
                  book[book.length-1].username= change.username;
                  book[a].status="Confirmed";
                  myq.dequeue();
               }
            }
            //Cancel for waiting list queue
            if(cancel_book!=true){
                MyQueue<Ticket> cancel_queue=new MyQueue<>(myq.maxSize);
                while(!myq.isEmpty()){
                    Ticket can_wait=myq.dequeue();              //Dequeue value in the mainlist to the temporary queue
                    if(!can_wait.username.equals(username)){
                        cancel_queue.enqueue(can_wait);         //Enqueue the value if the username is different.
                    }
                }
                while(!cancel_queue.isEmpty()){                //Enqueue back the value from temporary queue to the main queue
                    myq.enqueue(cancel_queue.dequeue());
                }
                cancel_wait=true;                              
            }
           //Username that doesnt book any ticket couldnt access it. 
            if(cancel_book==false && cancel_wait==false){
                System.out.println("There is no booking under this username. Thank You.");    
            }
        } 
    }
    
    //6. Feedback(Extra Feature)
     public static void collectFeedback() {
        Scanner scanner = new Scanner(System.in);

        
        System.out.println("Please provide your feedback on how on we can improve this system or what "
                + "do you think of our system.");

        // Allow the user to input feedback
        System.out.print("Feedback: ");
        String feedback = scanner.nextLine();

       
        System.out.println("Thank you for your feedback!");

        // Close the scanner to prevent resource leaks
        scanner.close();
    }
    
    
    //7. LogOut [Done]
    public static String LogOut(String personal_info){
        
        Scanner log=new Scanner(System.in);
        String log_out=null;
        System.out.print("Are you sure to log out? [1:Yes | 2:No]: ");
        log_out=log.nextLine();
        switch (log_out) {
            case "1":
                System.out.println("Thank You for using our system.Have a safe journey");
                log_out=null;
                break;
            case "2":
                log_out=personal_info;
                break;
            default:
                System.out.println("Wrong Input, Please Try Again.");
                break;
        } 
        return log_out;
    }
}
