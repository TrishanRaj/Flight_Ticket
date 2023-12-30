
package flightbook;

public class View_Status {

    // 4. View all booking and waitlist status [Done]
    public static void viewTicketStatus(Ticket[] book, MyQueue<Ticket> waitList, String username) {
        boolean view_confirm = false, view_wait = false;  // Similar to edit ticket coding

        for (Ticket ticket : book) {
            // Check if the ticket is not null and the username matches
            if (ticket != null && ticket.username.equals(username)) {
                System.out.println("View Status");
                System.out.println("*********************");
                System.out.println("ID: " + ticket.passportNo);
                System.out.println("Name: " + ticket.passengerName);
                System.out.println("Flight Name: " + ticket.flightName);
                System.out.println("Departure Date [Time]: " + ticket.flightTime);
                System.out.println("Status: " + ticket.status);
                System.out.println("*********************");
                System.out.println("");
                view_confirm = true;
                return;
            }
        }

        // View for WaitingList (Similar to edit ticket_coding
        MyQueue<Ticket> temp_queue = new MyQueue<>(waitList.maxSize);
        while (!waitList.isEmpty()) {
            Ticket temp_View = waitList.peek();
            // Check if the ticket is not null and the username matches
            if (temp_View != null && temp_View.username.equals(username)) {
                System.out.println("\n****************************");
                System.out.println("View Status");
                System.out.println("*********************");
                System.out.println("ID: " + temp_View.passportNo);
                System.out.println("Name: " + temp_View.passengerName);
                System.out.println("Flight Name: " + temp_View.flightName);
                System.out.println("Departure Date [Time]: " + temp_View.flightTime);
                System.out.println("Status: " + temp_View.status);
                System.out.println("*********************");
                view_wait = true;
            }
            temp_queue.enqueue(waitList.dequeue());
        }

        while (!temp_queue.isEmpty()) {
            waitList.enqueue(temp_queue.dequeue());
        }

        if (!view_confirm && !view_wait) {
            // If the user doesn't have a booking, nothing will show.
            System.out.println("There is no booking under this username. Thank You.");
        }
    }
}
