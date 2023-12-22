/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ds_flightbookingsystem;

/**
 *
 * @author Acer
 */
import java.util.Random;
import java.util.Scanner;

class Ticket {
    String passengerName, passportNo, phoneNum, username, status ,feedback;

    public Ticket(String passengerName, String passportNo, String phoneNum, String username, String status) {
        this.passengerName = passengerName;
        this.passportNo = passportNo;
        this.phoneNum = phoneNum;
        this.username = username;
        this.status = status;
    }

    @Override
    public String toString() {
        return "| " + passportNo + " |\t" + passengerName + "\t| Waiting\t|";
    }

    //FEEDBACK SYSTEM  
    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
   
}

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

    public void displayWaitList() {
        for (int i = 0; i < tail; i++) {
            System.out.println(wait_List[i].toString());
        }
    }
}

public class FlightBookingSystem {
    
    private static final int WEEK_1 = 1;
    private static final int WEEK_2 = 2;
    private static final int WEEK_3 = 3;
    private static final int WEEK_4 = 4;
    private static final int WEEK_5 = 5;
    private static final int MAX_SEATS = 3;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String personal_info = null;

        System.out.println("Welcome to our Flight Booking System!");

        System.out.print("Enter the month of your travel: ");
        String month = input.nextLine();
        if (!isValidMonth(month)) {
            System.out.println("Invalid month. Please input another month");
        } else {
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
                    switch (month) {
                        case "January", "March", "May", "July", "August", "October", "December":
                            BookingFlight(week, month, 29, 31);
                            break;
                        case "February":
                            System.out.println("Invalid week for this particular month. Please choose another week");
                            break;
                        case "April", "June", "September", "November":
                            BookingFlight(week, month, 29, 30);
                            break;
                    }
                    break;
                default:
                    System.out.println("Invalid week selection. Please enter a value between 1 to 5.");
                    break;
            }
        }

        boolean exit = false;

        do {
            if (personal_info == null) {
                personal_info = login();
            }

            int option;
            System.out.println("Welcome to Cloud Wings");
            System.out.println("*************************");
            System.out.println("1. Search for flight");
            System.out.println("2. Book a ticket");
            System.out.println("3. Edit ticket information");
            System.out.println("4. View ticket status");
            System.out.println("5. Cancel ticket");
            System.out.println("6. Log Out\n");
            System.out.print("Please select: ");
            Scanner key = new Scanner(System.in);
            option = key.nextInt();

            switch (option) {
                case 1:
                    // Implement flight search logic
                    break;
                case 2:
                    bookTicket(personal_info);
                    break;
                case 3:
                    // Implement edit ticket information logic
                    //editTicketInfo();
                    break;
                case 4:
                    // Implement view ticket status logic
                    //viewTicketStatus();
                    break;
                case 5:
                    // Implement cancel ticket logic
                    //cancelTicket();
                    break;
                case 6:
                    personal_info = logout(personal_info);
                    break;
                default:
                    System.out.println("Wrong number inputted. Please try again.");
                    break;
            }
        } while (!exit);
    }
    
    
    private static void BookingFlight(int week, String month, int starting, int ending) {
        Random random = new Random();
        Scanner scan = new Scanner(System.in);

        System.out.println("You have entered Week " + week + ". You will be travelling from " + starting + " of " +
                month + " to " + ending + " of " + month + ".");

        System.out.print("Enter number of tickets you would like to purchase: ");
        int tickets = scan.nextInt();

        System.out.println("\n---FLIGHT DETAILS---");

        for (int i = 1; i <= MAX_SEATS; i++) {
            System.out.println("Flight " + i + ": Malaya " + (random.nextInt(900) + 100) +
                    "\nDeparture Time: " + (random.nextInt(12) + 1) + (random.nextBoolean() ? "AM" : "PM"));
            System.out.println("----------");
        }

        System.out.print("\nChoose Your Flight (1/" + MAX_SEATS + "): Flight ");
        int flight = scan.nextInt();

        System.out.println("You have chosen Flight " + flight + ".");

        System.out.print("Confirm booking? Yes or No? ");
        String confirm = scan.next();

        if (confirm.equalsIgnoreCase("Yes")) {
            System.out.println("You have successfully booked " + tickets + " ticket(s) for Flight " + flight + "!");
        } else {
            System.out.println("We're sorry to see you go. Thank you for choosing us.");
        }
    }

    private static String login() {
        Scanner logkey = new Scanner(System.in);
        System.out.print("Username: ");
        String username = logkey.nextLine();
        System.out.print("Password: ");
        String password = logkey.nextLine();
        return username;
    }
    
    //2. Book a ticket 
    private static void bookTicket(String username) {
        // Implement booking logic            
    }
    
    //3. Edit a ticket 
    public static void editTicketInfo(Ticket[] book, MyQueue<Ticket>waitList,String user_login) {
        //implement editing logic 
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
    
    //4. View all booking and waitlist status 
    public static void viewTicketStatus(Ticket[] book,MyQueue<Ticket> waitList,String username ){
        //implement viewing logic
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
    
    //5. Cancel_Ticket  
     public static void cancelTicket(Ticket[] book,MyQueue<Ticket> myq,String username){
         //implement cancelling logic 
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

    private static String logout(String personal_info) {
        Scanner log = new Scanner(System.in);
        String log_out = null;
        System.out.print("Are you sure to log out? [1:Yes | 2:No]: ");
        log_out = log.nextLine();
        switch (log_out) {
            case "1":
                System.out.println("Thank You for using our system. Have a safe journey");
                log_out = null;
                break;
            case "2":
                log_out = personal_info;
                break;
            default:
                System.out.println("Wrong Input, Please Try Again.");
                break;
        }
        return log_out;
    }

    private static boolean isValidMonth(String month) {
        return switch (month) {
            case "January", "February", "March", "May", "June", "July", "August", "September", "October", "November",
                    "December" -> true;
            default -> false;
        };
    }
    
    //FEEDBACK SYSTEM 
    private static String getFeedbackFromUser() {
        Scanner feedbackScanner = new Scanner(System.in);
        System.out.print("Please provide feedback (optional): ");
        return feedbackScanner.nextLine();
    }
    
     private static void provideFeedback(Ticket[] book) {
        Scanner feedbackScanner = new Scanner(System.in);
        System.out.print("Enter your passport number to provide feedback for your booking: ");
        String passportToFeedback = feedbackScanner.nextLine();

        for (Ticket ticket : book) {
            if (ticket != null && ticket.passportNo.equals(passportToFeedback)) {
                System.out.print("Please provide feedback for your booking: ");
                ticket.setFeedback(feedbackScanner.nextLine());
                System.out.println("Thank you for your feedback: " + ticket.feedback);
                return;
            }
        }

        System.out.println("No booking found with the provided passport number.");
    }
    
    
}

//Feedback havent tried running yet will do it once i managed to complete the others 
//Fix question 2
//Modify question 1 
//add more features (if possible)
//implement gui 
