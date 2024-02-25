import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;


public class ProcessDriver {
	public static void main(String[] args) {
		Trace simulationLinkedList = new Trace();
		Processor queueProcessor = new Processor();
		Averager queueAverager = new Averager();
		Queue<Integer> requestQueue = new LinkedList<Integer>();
		Processor stackProcessor = new Processor();
		Averager stackAverager = new Averager();
		Stack<Integer> requestStack = new Stack<Integer>();

		
		System.out.println("Please enter a trace file.");
		Scanner inputScanner = new Scanner(System.in);
		String inputString = inputScanner.next();
		File inputFile = new File(inputString);
		inputScanner.close();

		try {
			Scanner fileScanner = new Scanner(inputFile);
			while(fileScanner.hasNextInt()) {
				int currentInt = fileScanner.nextInt();
				simulationLinkedList.addRequest(currentInt);
			}
			fileScanner.close();
			tick(requestQueue, requestStack, simulationLinkedList, 
			queueProcessor, queueAverager, stackProcessor, stackAverager);
			DecimalFormat formatPercent = new DecimalFormat("0.0");
			System.out.println("Customers served in queue = " +
			queueAverager.getPacketsServed());
			System.out.println("Customers abandoned in queue = " +
			queueAverager.getPackNotServed());
			System.out.println(
				"Mean waiting time for served customers in queue = " +
				formatPercent.format(queueAverager.getAverage()) + " seconds"
			);
			System.out.println(
				"Customers served in stack = " +
				stackAverager.getPacketsServed()
			);
			System.out.println(
				"Customers abandoned in stack = " +
			stackAverager.getPackNotServed()
			);
			System.out.println(
				"Mean waiting time for served customers in stack = " +
				formatPercent.format(stackAverager.getAverage()) + " seconds."
			);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		
	}
	public static void tick(Queue<Integer> q, Stack<Integer> s, Trace t,
	Processor qp, Averager qa, Processor sp, Averager sa) {
		final int MAX_TIME = 1000;
		Request currentRequest = t.getHead(); //grab the head request

		for(int currentSecond = 0; currentSecond < MAX_TIME; currentSecond++) {
			if (currentRequest != null &&
				currentRequest.getArrival() == currentSecond)
			{
				//if the times match then add to the queue
				q.add(currentSecond);
				s.push(currentSecond);
				currentRequest = currentRequest.getNext();
			}
			if (!qp.isBusy() && !q.isEmpty()) {
				//if not busy and  not empty get the next request
				Integer nextInQ = q.remove();
				checkPacket(currentSecond, nextInQ, qa, qp);
			}
			if (!sp.isBusy() && !s.empty()) {
				//if not busy and  not empty get the next request
				Integer nextOnStack = s.pop();
				checkPacket(currentSecond, nextOnStack, sa, sp);
			}
			qp.reduceTime();
			sp.reduceTime();
		}
	}

	public static void checkPacket(int timeNow, int requestArrivalTime,
	Averager averager, Processor processor) {
		int waitTime = timeNow - requestArrivalTime;
		if (waitTime >= 30) {
				averager.addMiss();
				processor.startCleanup();
		} else {
				averager.addHit(waitTime);
				processor.startProcess();
		}
	}

}
