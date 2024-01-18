package flightbook;


import java.util.Scanner;

public class Book_Ticket {

    public static void bookTicket(Ticket[] book, MyQueue<Ticket> waitList, String username) {
        Scanner obj = new Scanner(System.in);


        // Get passenger details
        System.out.print("Passenger Name: ");
        String passengerName = obj.next();
        System.out.print("Passport Number: ");
        String passportNo = obj.next();
        System.out.print("Phone Number: ");
        String phoneNum = obj.next();
        String flightName = "flightname";
        String flightTime = "time";

        // Create Ticket object and book the ticket
        Ticket myTicket = new Ticket(passengerName, passportNo, phoneNum, username, flightName, flightTime, null);

        // Find the first null entry in the book array and book the ticket
        boolean booked = false;
        for (int a = 0; a < book.length; a++) {
            if (book[a] == null) {
                book[a] = myTicket;
                booked = true;
                break;
            }
        }

        if (booked) {
            System.out.println("\nSuccessfully Booked");
            myTicket.status = "Confirmed";
        } else {
            System.out.println("\nConfirm book is full. Currently in the waiting list");
            myTicket.status = "Waiting";
            waitList.enqueue(myTicket);
        }
    }
}
