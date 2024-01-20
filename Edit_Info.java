
package flightbook;

import java.util.Scanner;

public class Edit_Info {

    // 3.Edit Passenger Information [Done]
    public static void editTicketInfo(Ticket[] book, MyQueue<Ticket> waitList, String user_login) {

        Scanner edit = new Scanner(System.in);
        boolean found_confirm = false;  // Set the info found in booking as false.

        // Edit for confirmed booking passenger
        for (Ticket book1 : book) {
            // Null checks for username and validation
            if ((book1 != null) && ((book1.username == null && user_login == null) || book1.username.equals(user_login))) {
                // Information that needed to be edited
                System.out.print("Passenger Name:");
                book1.passengerName = edit.nextLine();
                System.out.print("Passport Num: ");
                book1.passportNo = edit.nextLine();
                System.out.print("Phone Num: ");
                book1.phoneNum = edit.nextLine();
                System.out.println("\nSuccessfully Edited");
                found_confirm = true;    // Passenger is found in booking table.
                return;
            }
        }

        // Edit for waiting list passenger
        MyQueue<Ticket> tempQueue = new MyQueue<>(waitList.maxSize); // Create a temporary queue to store info of waitList queue
        boolean found_wait = false; // Set the info found in waitlist as false.

        while (!waitList.isEmpty()) {
            Ticket temp_waitList = waitList.peek();   // Peek is the info to be enqueued first (head)
            // Null checks for username and validation
            if ((temp_waitList != null) && temp_waitList.username.equals(user_login)) {
                // Information of the user in the waiting list
                System.out.print("Passenger Name:");
                temp_waitList.passengerName = edit.nextLine();
                System.out.print("Passport Num: ");
                temp_waitList.passportNo = edit.nextLine();
                System.out.print("Phone Num: ");
                temp_waitList.phoneNum = edit.nextLine();
                System.out.println("\nSuccessfully Edited");
                found_wait = true;
            }
            tempQueue.enqueue(waitList.dequeue());  // Add the peak value of waitlist in tempQueue and dequeue the peak value of WaitList
        }

        // Once waitlist is fully empty, add back all the value from tempQueue to WaitList.
        while (!tempQueue.isEmpty()) {
            waitList.enqueue(tempQueue.dequeue());
        }

        // No access if no booking.
        if (!found_wait && !found_confirm) {
            System.out.println("There is no booking under this username. Thank You.");
        }
    }
}
