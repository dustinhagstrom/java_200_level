import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class IntArraySeq2bDriver {

	public static void main(String[] args) {
		System.out.println("Please enter a file name: ");
		Scanner stringScanner = new Scanner(System.in);
		String inputFileString = stringScanner.next();
		File inputFile = new File(inputFileString);
		stringScanner.close();

		try {
			Scanner fileScanner = new Scanner(inputFile);
			File outputFile = new File("SortedSequences.txt");
			PrintWriter filePrinter = new PrintWriter(outputFile);
			IntArraySeq2b sortedInputSeq = sortsFileIntoSeq(fileScanner, filePrinter);

			IntArraySeq2b copy = new IntArraySeq2b(sortedInputSeq);
			removesDuplicates(copy);

			printContentsOfSeqToConsole(sortedInputSeq, "Original Sequence");
			printContentsOfSeqToConsole(copy, "Sequence without duplicates");
			fileScanner.close();
			filePrinter.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static IntArraySeq2b sortsFileIntoSeq(Scanner intScanner, PrintWriter filePrinter) {

		IntArraySeq2b sortedSeq = new IntArraySeq2b();
		try {
			int numToInsert = intScanner.nextInt();
			sortedSeq.addFront(numToInsert);
			sortedSeq.start();

			while (intScanner.hasNextInt()) {
				numToInsert = intScanner.nextInt();

				// add to end of sequence if equal or larger than current
				if (numToInsert >= sortedSeq.getCurrent()) {
					sortedSeq.addAfter(numToInsert);
				} else {
					// start at beginning of sequence
					sortedSeq.start();
					while (numToInsert > sortedSeq.getCurrent()) {
						// while larger, advance current for next compare
						sortedSeq.advance();
					}
					// once not larger, add before, then move current to final element for next
					// compare
					sortedSeq.addBefore(numToInsert);
					sortedSeq.setCurrentLast();
				}
				printContentsOfSeqToFile(sortedSeq, filePrinter);
			}

		} catch (NoSuchElementException e) {
			// prints the element that caused exception.
			System.out.println("The integer in the file does exist, " + "the element selected is: " + e.getMessage());
		}

		return sortedSeq;
	}

	public static void printContentsOfSeqToConsole(IntArraySeq2b sequence, String name) {
		try {
			sequence.start();
			System.out.println(name + ":");
			for (int i = 0; i < sequence.size(); i++) {
				System.out.print(sequence.getCurrent() + " ");
				sequence.advance();
			}
			sequence.setCurrentLast();
			System.out.println();
		} catch (IllegalStateException e) {
			// prints the cause of this throwable exception. Thrown from class method.
			System.out.println("Exception due to: " + e.getMessage());
		}
	}

	public static void printContentsOfSeqToFile(IntArraySeq2b sequence, PrintWriter filePrinter) {
		sequence.start();
		filePrinter.println("sorted sequence:");
		for (int i = 0; i < sequence.size(); i++) {
			filePrinter.print(sequence.getCurrent() + " ");
			sequence.advance();
		}
		filePrinter.println();
		filePrinter.flush(); //must flush the file printer here.
		sequence.setCurrentLast();
	}

	public static void removesDuplicates(IntArraySeq2b sequence) {
		try {
			sequence.start();
			for (int i = 0; i < sequence.size(); i++) {
				int element = sequence.getCurrent(); // grab first element
				sequence.advance(); // advance to next element for comparison
				// must check isCurrent before grabbing current during loop condition
				while (sequence.isCurrent() && element == sequence.getCurrent()) {
					sequence.removeCurrent();
				}
			}
		} catch (IllegalStateException e) {
			// prints the cause of this throwable exception. Thrown from class method.
			System.out.println("Exception due to: " + e.getMessage());
		}
	}

}
