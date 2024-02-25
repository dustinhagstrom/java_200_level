// From softwaretestinghelp.com, used under academic Fair Use guidelines

/******************************************************************************
 * A doubly linked list allows us to traverse through a list of Node objects in
 * two directions. This allows us to manipulate the Nodes within the list. Each
 * doubly linked list contains a head reference and tail reference to Node
 * objects.
 *
 * @note Doubly Linked Lists can be made to contain any number of nodes, limited
 *       only by the amount of free memory in the heap.
 * @author Dustin Hagstrom
 *
 * @version Oct 07, 2022
 * 
 ******************************************************************************/

public class DoublyLinkedList<E> {
	// Invariant of the DoublyLinkedList class:
	// 1. Each DoublyLinkedList has a next reference to a head node, and a reference
	// to a tail node stored in the instance variables head and tail.
	// 2. This DoublyLinked List is not designed to hold Node values of null.

	// Initially, head and tail is set to null
	private Node<E> head;
	private Node<E> tail;

	/**
	 * Initialize a DoublyLinkedList with a null reference to head and tail.
	 * 
	 * @postcondition This DoublyLinkedList contains the does not contain any nodes.
	 **/
	public DoublyLinkedList() {
		this.head = null;
		this.tail = null;
	}

	/**
	 * Initialize a node with a specified initial data and link to the next node.
	 * Note that the initialLink may be the null reference, which indicates that the
	 * new node has nothing after it.
	 * 
	 * @param initialData the initial node of this new DoublyLinkedList
	 * @postcondition This DoublyLinkedList contains one node and head and tail
	 *                parameters both equal this node.
	 **/

	public DoublyLinkedList(E initialData) {
		Node<E> n = new Node<E>(initialData);
		head = n;
		tail = n;
	}

	/**
	 * Modification method to add a new node after this node.
	 * 
	 * @param item the object to create a new node to place at the end of the
	 *             DoublyLinkedList
	 * @postcondition A new node has been created and placed at the end of this
	 *                DoublyLinkedList. The data for the new node is item. The tail
	 *                node has been set to this new node.
	 * @exception OutOfMemoryError Indicates that there is insufficient memory for a
	 *                             new Node.
	 **/
	public void addNode(E item) {
		// Create a new node
		Node<E> newNode = new Node<E>(item);

		// if list is empty, head and tail points to newNode
		if (head == null) {
			head = tail = newNode;
			// newNode's previous and next are already null.

		} else {
			// add newNode to the end of list. tail->next set to newNode
			tail.setNext(newNode);
			// newNode->previous set to tail
			newNode.setPrev(tail);
			// newNode becomes new tail
			tail = newNode;
			// tail's next point explicitly set to null
			tail.setNext(null);
		}
	}

//print all the nodes of doubly linked list  
	public void printNodes() {
		// Node current will point to head
		Node<E> current = head;
		if (head == null) {
			System.out.println("Doubly linked list is empty");
			return;
		}
		System.out.println("Nodes of doubly linked list: ");
		while (current != null) {
			// Print each node and then go to next.
			System.out.print(current.getData() + " ");
			current = current.getNext();
		}
	}

	/**
	 * Accessor method to get the head reference from this DoublyLinkedList.
	 * 
	 * @return the head reference from this DoublyLinkedList
	 **/
	public Node<E> getHeadNode() {
		return this.head;
	}

	/**
	 * Accessor method to get the tail reference from this DoublyLinkedList.
	 * 
	 * @return the tail reference from this DoublyLinkedList
	 **/
	public Node<E> getTailNode() {
		return this.tail;
	}

	/**
	 * Modification method to set the head in this DoublyLinkedList.
	 * 
	 * @param listHead the new head reference in this DoublyLinkedList
	 * @postcondition The head reference of this DoublyLinkedList has been set to
	 *                listHead.
	 **/
	public void setHeadNode(Node<E> listHead) {
		this.head = listHead;
	}

	/**
	 * Modification method to set the tail in this DoublyLinkedList.
	 * 
	 * @param listTail the new tail reference in this DoublyLinkedList
	 * @postcondition The tail reference of this DoublyLinkedList has been set to
	 *                listTail.
	 **/
	public void setTailNode(Node<E> listTail) {
		this.tail = listTail;
	}
}