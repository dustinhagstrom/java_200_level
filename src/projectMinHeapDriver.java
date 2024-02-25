import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class projectMinHeapDriver {

	public static void main(String[] args) {
		System.out.println("Please enter a file name.");
		projectMinHeap ourMinHeap = new projectMinHeap();
		projectMinHeap unOrderedPile = new projectMinHeap();
		Scanner inputScanner = new Scanner(System.in);
		String inputString = inputScanner.next();
		File inputFile = new File(inputString);
		inputScanner.close();

		try{
		Scanner fileScanner = new Scanner(inputFile);
		
		System.out.println("\n****************************   Building min heap"
		 +	"   ****************************\n");
		while(fileScanner.hasNextInt()) {
			int currentInt = fileScanner.nextInt();
			unOrderedPile.addElementUnorderedHeap(currentInt);
			ourMinHeap.addElement(currentInt);
			ourMinHeap.printElements();
		}
		System.out.println("\n*********************************   Heapsort" +
		"   ********************************\n");
		
		int sizeOfHeap = ourMinHeap.getSize();
		int lastIndex = ourMinHeap.getSize() - 1;
		ourMinHeap.heapSort(lastIndex, sizeOfHeap);
		
		System.out.println("\n*********************************   Hashing" +
		"   *********************************\n");

		unOrderedPile.trimHeap();
		projectHash ourHash = new projectHash(unOrderedPile.getData());

		fileScanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
