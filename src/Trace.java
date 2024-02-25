// File: Trace.java
/******************************************************************************
*
*
* @note
*   Trace is a singly linked list with one instance variable that points to the
*	head of the linked list.
* @author Dustin Hagstrom
*
* @version Nov 1, 2022
* 
******************************************************************************/
public class Trace {
   // Invariant of the Trace class:
   //   1. Each Trace has a reference to a head Request [node].
	private Request head;
	

   /**
	* Initialize a Trace singly linked list with a null reference for head.
	* 
	* @postcondition This Trace is an empty linked list
	**/ 	
	public Trace() {
		this.head = null;
	}
	
   /**
   * Modification method to add a new Request after this Request.   
   * @param time
   *   The time that will be supplied to Request constructor to create a
   *   new Request that can be added to the end of this trace list
   * @postcondition
   *   A new Request has been placed after the last Request in this Trace.
   *   If the Trace is empty, then this new Request is the head of this Trace.
   **/	
	public void addRequest(int time) {
		Request newReq = new Request(time);

		if (head == null) {
			head = newReq;

		} else {
			Request current = head;
			while(current.hasNext()) {
				current = current.getNext();
			}
			current.setNext(newReq);
		}
	}

   /**
   * Accessor method to get a reference to the head of this Trace.
   * @return
   *   a reference to the head Request in this Trace
   **/	
	public Request getHead() {
		return this.head;
	}
	
}
