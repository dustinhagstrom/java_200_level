import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class ReadWriteDemo {
	
	File inputFile;
	PrintWriter outputFile;
	String inputFileName;
	String outputFileName;
	
	public static void main( String[] args ) {
		// I want to be sure I know where the working directory is, so that I can put the
		// input files there.
		System.out.println( "Working directory = " + System.getProperty( "user.dir" ) );
		
		// I cold have made this easier by just doing it all in the main() method, 
		// but this is better design.
		ReadWriteDemo demo = new ReadWriteDemo();
	}

	// This reads and writes a file.
	// Easy way to test is to put the file in the "working directory".  
	// For Eclipse users, this is the directory containing the "src" files.
	
	// Note:  In general, I would call separate methods to read and write the String,
	// but I don't want to overcomplicate this.
	public ReadWriteDemo() {
		
		try {
			// Enter the input file.
			Scanner s = new Scanner( System.in );
			System.out.println( "Please enter file name from the working directory: " ); 
			// Note:  If you're giving me a GUI for input, and you start it in the working
			// directory, that's awesome.
			inputFileName = s.next();
			inputFile = new File( inputFileName );
		
			// Name the output file appropriately.
			outputFileName = inputFileName.substring( 0, inputFileName.length()-4 ) + "_out.txt"; 

			// Using a separate Scanner for reading from different locations.
			Scanner fileScanner = new Scanner(inputFile); 
			outputFile = new PrintWriter(outputFileName);
			
			readWriteFile( fileScanner );
			
			s.close();
			fileScanner.close();
			outputFile.close();
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	// Read and write the file.  Throw the exception so it's caught in the calling block.
	public void readWriteFile( Scanner fileScanner ) throws FileNotFoundException {
		while (fileScanner.hasNextLine()) { 
			String line = fileScanner.nextLine();
			// Split string into space-separated "words".
			// YOUR CODE HERE to split the input line into "words".
			// (I suggest you use the split(...) method from the java.lang.String class.)	
			// YOUR CODE HERE to print the last word in the line.
			
			// I just print the line.  Delete this code for handin.
			System.out.println( line );  
			outputFile.println( line ); 
		}
	}
}
