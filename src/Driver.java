import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class Driver {

	public static void main(String[] args) {
		System.out.println("Please enter a file name: ");
		Scanner stringScanner = new Scanner(System.in);
		String inputFileString = stringScanner.next();
		File inputFile = new File(inputFileString);
		stringScanner.close();

		try {
			String line;
			String[] splitLine;
			boolean notBlank = true;
			DoublyLinkedList<Student> inputStudentList;
			Scanner fileScanner = new Scanner(inputFile);
			// check to see if anything is in the Scanner and make first student in list
//			if (fileScanner.hasNextLine()) {
			line = fileScanner.nextLine();
			splitLine = line.split(" ");
			String firstName = splitLine[0];
			String lastName = splitLine[1];
			String starID = splitLine[2];
			// make the doubly linked list and add the first node
			inputStudentList = new DoublyLinkedList<>(new Student(firstName, lastName, starID));

			while (fileScanner.hasNextLine() && notBlank) { // stops at blank line
				line = fileScanner.nextLine();
				if (line.length() == 0) {
					notBlank = false;
				} else {
					splitLine = line.split(" "); // split string to use in student
					// while we have more students, make more nodes
					Student currentStudent = new Student(splitLine[0], splitLine[1], splitLine[2]);
					sortsStudentsIntoLinkedList(inputStudentList, currentStudent);
				}
			}
			Node<Student> headNode = inputStudentList.getHeadNode();
			for (int i = 1; i <= Node.listLength(headNode); i++) {
				System.out.println(Node.listPosition(headNode, i).getData().toString());
			}
			notBlank = true;
			while (fileScanner.hasNextLine() && notBlank) {
				line = fileScanner.nextLine();
				if (line.length() == 0) {
					notBlank = false;
				} else {
					splitLine = line.split(" ");
					Student currentStudent = new Student(splitLine[0], splitLine[1], splitLine[2]);
					removesDuplicatesFromList(inputStudentList, currentStudent);
				}
			}
			headNode = inputStudentList.getHeadNode();
			System.out.println("\nUpdated Student List below.\n");
			for (int i = 1; i < Node.listLength(headNode); i++) {
				System.out.println(Node.listPosition(headNode, i).getData().toString());
			}

			fileScanner.close();
		} catch (

		FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	public static void sortsStudentsIntoLinkedList(DoublyLinkedList<Student> dubLinkedList, Student studentToInsert) {
		Student tailStudent = dubLinkedList.getTailNode().getData();
		Student headStudent = dubLinkedList.getHeadNode().getData();
		Node<Student> headNode = dubLinkedList.getHeadNode();
		Node<Student> studentNode = new Node<>(studentToInsert);
		if (studentToInsert.compareTo(tailStudent) > 1) { // if last, then add to end
			dubLinkedList.addNode(studentToInsert); // this sets the tail already

		} else if (studentToInsert.compareTo(headStudent) < 1) { // if first, then add to front
			// make studentToInsert a node, then set next to headNode
			// make sure that its previous is null, set headStudent previous
			// to this new node and set the head of the list to studentToInsert
			studentNode.setNext(headNode);
			headNode.setPrev(studentNode);
			dubLinkedList.setHeadNode(studentNode);
		} else { // the new student falls somewhere b/n head and tail
			// start at the headStudent and get next, then perform
			// compareTo function of Student class, if negative
			// then grab current node's previous link and add
			// a new node after that.
			Node<Student> cursor = headNode;
			while (studentToInsert.compareTo(cursor.getData()) > 1) {
				cursor = cursor.getNext();
			}
			// put student node b/n cursor and cursor's current previous
			Node<Student> nodeBefore = cursor.getPrev();
			nodeBefore.setNext(studentNode);
			studentNode.setPrev(nodeBefore);
			studentNode.setNext(cursor);
			cursor.setPrev(studentNode);
		}

	}

	public static void removesDuplicatesFromList(DoublyLinkedList<Student> dubLinkedList, Student studentToRemove) {
		Node<Student> nodeBefore;
		Node<Student> nodeAfter;
		Node<Student> headNode = dubLinkedList.getHeadNode();
		Node<Student> tailNode = dubLinkedList.getTailNode();
		Node<Student> foundNode;
		foundNode = customSearch(headNode, studentToRemove);
		if (foundNode == null) {
			// do nothing
		} else { // remove node by removing references to it
			nodeBefore = foundNode.getPrev();
			nodeAfter = foundNode.getNext();
			// check for head and tail before trying to reference links on null value
			if (foundNode.getData().compareTo(headNode.getData()) == 0) {
				nodeAfter.setPrev(null);
				dubLinkedList.setHeadNode(nodeAfter);
			} else if (foundNode.getData().compareTo(tailNode.getData()) == 0) {
				nodeBefore.setNext(null);
				dubLinkedList.setTailNode(nodeBefore);
			} else {
				nodeBefore.setNext(nodeAfter);
				nodeAfter.setPrev(nodeBefore);
			}
		}
	}

	public static Node<Student> customSearch(Node<Student> head, Student target) {
		Node<Student> cursor;
		// this search is similar to node listSearch, but uses Student's compareTo
		// method instead of the equals method of class object.
		for (cursor = head; cursor != null; cursor = cursor.getNext()) {
			if (target.compareTo(cursor.getData()) == 0) {
				return cursor;
			}
		}
		return null;
	}

}
