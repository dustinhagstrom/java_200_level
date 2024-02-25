/******************************************************************************
 * A Student allows us to store student data together. We can then take this
 * data and store in within a Node. With this Node we can make a
 * DoublyLinkedList so that we can compare lists of students for equality and
 * duplication.
 *
 * @author Dustin Hagstrom
 *
 * @version Oct, 07, 2022
 * 
 ******************************************************************************/
public class Student implements Comparable<Student> {

	// Invariant of the Student class:
	// 1. Each Student stores three strings representing a first
	// name, last name, and unique starID.

	String firstName;
	String lastName;
	String starID;

	/**
	 * Construct a Student with null values for first name, last name, and starID.
	 * 
	 * @postcondition This Student contains null for first name, last name, and
	 *                StarID.
	 **/
	public Student() {
		firstName = null;
		lastName = null;
		starID = null;
	}

	/**
	 * Construct a Student that has String values for first name, last name, and
	 * starID
	 * 
	 * @param first  the first name data of this Student
	 * @param last   the last name of this Student
	 * @param starID the starID of this Student
	 * @postcondition This Student contains the specified first name, last name, and
	 *                starId.
	 **/
	public Student(String first, String last, String starID) {
		firstName = first;
		lastName = last;
		this.starID = starID;

	}

	/**
	 * Comparison method to add compare this student with argument student
	 * 
	 * @param s s is a student that is to be compared lexicographically. first, the
	 *          students are compared by lastname. If these are equivalent, then
	 *          they are compared by firstname, if these are equal then they are
	 *          compared by unique starID.
	 * @return An int value that can be less than zero to signify that this student
	 *         precedes the student given by the argument. If the value is zero then
	 *         this student is equivalent to the student given by the argument. If
	 *         the value is greater than one, then this student follows the student
	 *         given by the argument.
	 **/
	public int compareTo(Student s) {

		int retval = this.lastName.compareTo(s.lastName);

		if (retval == 0) {
			retval = this.firstName.compareTo(s.firstName);
			if (retval == 0) {
				retval = this.starID.compareTo(s.starID);
			}
		}
		return retval;

	}

	/**
	 * Helper method to return the values of the firstName, lastName, and starID
	 * concatenated in a String.
	 * 
	 * @return This method returns the values of a Student object concatenated in a
	 *         String.
	 **/
	public String toString() {
		return (firstName + ", " + lastName + ": " + starID);
	}
}
