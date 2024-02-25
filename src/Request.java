// File: Request.java
/******************************************************************************
* A Request is a Node that stores an Integer in a linked list. Each Request 
* contains a piece of data (a reference to an Integer object) and a link
* (which is a reference to the next Request of the list). The reference stored
* in a Request can be null.
*
* @note
*   Lists of Requests can be made of any length, limited only by the amount of
*   free memory in the heap. But beyond Integer.MAX_VALUE (2,147,483,647),
*   the answer from listLength is incorrect because of arithmetic
*   overflow. 
* @author Dustin Hagstrom
*
* @version Nov 1, 2022
* 
******************************************************************************/
public class Request {
   // Invariant of the Request class:
   //   1. Each Request has a next reference to another Request Object,
   //   2. For the final Request of a list, the next part is null.
   //      Otherwise, the next part is a reference to the
   //      next Request of the list.
	private Integer arrivalTime;
	private Request nextRequest;

   /**
	 * Initialize a Request with a specified arrivalTime and link to the next
    * Request.
	 * Note that the nextRequest may be the null reference, which indicates
    * that the new Request has nothing after it.
	 * 
	 * @param time the initial arrivalTime of this new Request
	 * @postcondition This Request contains the time as arrivalTime and has a
    *                null reference for the next Request.
	 **/ 
	public Request(int time) {
		arrivalTime = time;
		nextRequest = null;
	}

   /**
   * Accessor method to get the arrivalTime from this node.   
   * @return
   *   the arrivalTime from this node
   **/
   public int getArrival()   
   {
      return arrivalTime;
   }
   
   /**
   * Accessor method to get a reference to the nextRequest after this Request. 
   * @return
   *   a reference to the Request after this Request (or the null reference if
   *   there is nothing after this Request)
   **/

   public Request getNext()
   {
      return nextRequest;                                               
   }
   
   /**
   * Modification method to add a new Request after this Request.   
   * @param next
   *   the Request to place next in the LinkedList
   * @postcondition
   *   A new Request has been placed after this Request in a LinkedList.
   **/

   public void setNext(Request next)
   {
      this.nextRequest = next;                                               
   }
   
   /**
   * Boolean method to check if this Request has a nextRequest.
   * @return
   *   True if there is a next, false if not.
   **/
   public boolean hasNext()
   {
      if (this.nextRequest != null) {
    	  return true;
      } else {
    	  return false;
      }                                               
   } 

}

