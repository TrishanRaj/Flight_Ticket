
package flightbook;

import java.util.Scanner;

/**
 *
 * @author ASUS
 */
public class Cancel_Ticket {
    
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
}
