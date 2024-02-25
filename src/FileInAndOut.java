/*
@author Dustin Hagstrom

This program takes in a text file (located at same level as source folder)
and reads the contents of that file. It creates an output file on the same
level as the original file and it reuses the original file name given by 
the user, but it appends "_out" to the name before the file extension.
It then prints the last string on each line of the input file to the output
file.

import File, PrintWriter, Scanner from java

define class FileInAndOut
	create main method
		output request to user to enter the name of input file
		create a scanner to capture user input file name
		create a file object from input name
		close this scanner
		
		find the starting index of the file extension from input name
		remove the file extension from input string
		
		use try and catch to handle a file not found exception
		try
			make a scanner to go through input file
			make an output file obj and add "_out" to original name
			use printWriter to make an actual file using name above
			
			loop through input file object and check for lines
			while there are lines then store line in one string
			find last index of a string that is a whitespace and add 1
			take the substring starting at this value, this is last string
			send it to output file on its own line
			close the fileScanner and filePrinter
		
		catch
			make a message to tell user that the file was not found.
			
 */

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class FileInAndOut {

	public static void main(String[] args) {
		System.out.println("Please enter a file name: ");
		Scanner stringScanner = new Scanner(System.in);
		String inputFileString = stringScanner.next();
		File inputFile = new File(inputFileString);
		stringScanner.close();
		
		int fileExtIndex = inputFileString.lastIndexOf(".");
		String newInputFileString = inputFileString.substring(0, fileExtIndex);

		try {
			Scanner fileScanner = new Scanner(inputFile);
			File outputFile = new File(newInputFileString + "_out.txt");
			PrintWriter filePrinter = new PrintWriter(outputFile);
			
			while(fileScanner.hasNextLine()) {
				String currentLine = fileScanner.nextLine();
				int index = currentLine.lastIndexOf(" ") + 1;
				String lastString = currentLine.substring(index);
				filePrinter.println(lastString);
			}
			fileScanner.close();
			filePrinter.close();
		} catch (Exception e) {
			System.out.println("File not found!");
		}
	}

}
