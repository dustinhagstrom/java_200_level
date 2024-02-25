// File: Processor.java
/******************************************************************************
* A Processor object simulates a processor that processes network requests
* It can only process one request at a time, and if the request has been
* waiting for more than 30 seconds it will cleanup the request (failed service)
* and move on to the next request to process
*
* @note
* The processor can only handle one request at a time, whether for processing
* or for cleanup.
* @author Dustin Hagstrom
*
* @version Nov 1, 2022
* 
******************************************************************************/
public class Processor {
   // Invariant of the Processor class:
   //   1. PROCESS_TIME is a fixed value.
   //   2. CLEANUP_TIME is a fixed value.
   //   3. timeRemaining takes the process time or the cleanup time and is used
   //      to indicate whether the processor is currently busy or whether it
   //	   be utilized to clean up or start another service of packets.
	private final int PROCESS_TIME = 5;
	private final int CLEANUP_TIME = 3;
	private int timeRemaining;


	/**
	* Initialize a Processor with a zero value for timeRemaining
	* 
	* @postcondition This Processor contains timeRemaining of zero
	**/ 
	public Processor() {
		timeRemaining = 0;
	}

   /**
   * Modification method to reduce timeRemaining.
   * @postcondition
   *   The timeRemaining has been decremented.
   **/
	public void reduceTime() {
		if (timeRemaining > 0) {
			timeRemaining--;	
		}
	}

   /**
   * Modification method to set timeRemaining to CLEANUP_TIME.
   * @postcondition
   *   The timeRemaining has been reset.
   **/
	public void startCleanup() {
		timeRemaining = CLEANUP_TIME;
	}

   /**
   * Modification method to set timeRemaining to PROCESS_TIME.
   * @postcondition
   *   The timeRemaining has been reset.
   **/	
	public void startProcess() {
		timeRemaining = PROCESS_TIME;
	}

   /**
   * Boolean method to check if this Processor is busy.
   * @return
   *   True if there is a next, false if not.
   **/
	public boolean isBusy() {
		return timeRemaining > 0;
	}
}
