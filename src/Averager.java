// File: Averager.java
/******************************************************************************
* An Averager tracks the number of Request [packets] served by a Processor.
* It will track the number of packets served and the number not served.
* It tracks the totalWaitTime which is the total time spent successfully
* processing packets. We can then use this information to return average wait
* time for packets that get processed. 
* @note
* The Averager is not used to calculate  time data for unserved packets.
* @author Dustin Hagstrom
*
* @version Nov 1, 2022
* 
******************************************************************************/
public class Averager {
   // Invariant of the Averager class:
   //   1. packetsServed is number of packets served.
   //   2. packNotServed is number of packets not served.
   //   3. totalWaitTime is the summation of all the wait times of Requests
   //	   that were processed successfully

	private int packetsServed;
	private int packNotServed;
	private double totalWaitTime;
	
	/**
	* Initialize a Averager with a zero value for all instance variables
	* totalWaitTime has a widening conversion from int to double
	* @postcondition This Averager contains timeRemaining of zero
	**/ 
	public Averager() {
		packetsServed = 0;
		packNotServed = 0;
		totalWaitTime = 0;
	}
	
   /**
   * Modification method to track the totalWaitTime and packetsServed
   * when a packet will be served by the Processor
   * @param time
   *   the wait time of the Request that just started getting processed.
   * @postcondition
   *   totalWaitTime and packetsServed have been updated.
   **/

	public void addHit(int time) { //widening conversion int to double
		totalWaitTime += time;
		packetsServed++;
	}
	
   /**
   * Modification method to increment packNotServed.
   * @postcondition
   *   packNotServed has been incremented to account for unsuccessful packet
   **/	
	public void addMiss() {
		packNotServed++;
	}
	
   /**
   * Derivation method that computes the average wait time for successfully
   * processed requests.
   * @return
   *   the average wait time for successfully processed requests.
   **/	
	public double getAverage() {
		if (packetsServed == 0) {
			return Double.NaN;
		} else {
			return totalWaitTime / packetsServed;	
		}
	}
	
   /**
   * Accessor method to number of packetsServed.
   * @return
   *   the number of packetsServed by the processor.
   **/	
	public int getPacketsServed() {
		return packetsServed;
	}
	
   /**
   * Accessor method to number of packNotServed.
   * @return
   *   the number of packNotServed by the processor.
   **/		
	public int getPackNotServed() {
		return packNotServed;
	}
}
