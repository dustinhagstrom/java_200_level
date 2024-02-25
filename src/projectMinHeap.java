//File: projectMinHeap.java
/******************************************************************************
* A Min Heap to store and manipulate Integer Objects.
*
* @author Dustin Hagstrom
*
* @version Dec 3, 2022
* 
******************************************************************************/

public class projectMinHeap {
   // Invariant of the projectMinHeap class:
   //   1. data is a fixed value.
   //   2. ELEMENTS_PER_LINE is a fixed value.
   //   3. heapSize is the number of Integer objects stored in the heap
   //	4. heapCapacity indicates the length of the data array to include
   //	   empty spaces that contain the null value.
	private Integer[] data;
	private final int ELEMENTS_PER_LINE = 20;
	private int heapSize;
	private int heapCapacity;

	/**
	 * Initialize a projectMinHeap that is empty.
	 */
	public projectMinHeap() {
		data = new Integer[ELEMENTS_PER_LINE];
		heapSize = 0;
		heapCapacity = ELEMENTS_PER_LINE;
	}

	// heap methods

	/**
	 * @param parentIndex
	 * @return the index of the parent's left child.
	 */
	private int leftChildIndex(int parentIndex) {
		int childIndex = 2 * parentIndex + 1;
		return childIndex;
	}

	/**
	 * @param parentIndex
	 * @return the index of the parent's right child.
	 */
	private int rightChildIndex(int parentIndex) {
		int childIndex = 2 * parentIndex + 2;
		return childIndex;
	}

	/**
	 * @param childIndex
	 * @return the index of the child's parent.
	 */
	private int parentIndex(int childIndex) {
		int parentIndex = (childIndex - 1) / 2;
		return parentIndex;
	}

	
	/**
	 * @param index
	 * this method puts the Integer obj located at the given index at its
	 * proper position in the min heap. It is used after an insertion.
	 */
	private void heapifyUp(int index) {
		int parentIndex = parentIndex(index);
		if(index > 0 && data[index] < data[parentIndex]){
			swapElements(index, parentIndex);
			heapifyUp(parentIndex);
		}
	}

	/**
	 * @param index
	 * @param sizeOfHeap
	 * this method puts the Integer obj located at the given index at its
	 * proper position in the min heap. It is used after an sort.
	 */
	private void heapifyDown(int index, int sizeOfHeap) {
		int smallest = index;
		int leftIndex = leftChildIndex(index);
		int rightIndex = rightChildIndex(index);

		if (leftIndex < sizeOfHeap && data[leftIndex] < data[smallest]) {
			smallest = leftIndex;
		}
		if (rightIndex < sizeOfHeap && data[rightIndex] < data[smallest]) {
			smallest = rightIndex;
		}
		if (smallest != index) {
			swapElements(smallest, index);
			heapifyDown(smallest, sizeOfHeap);
		}
	}

	/**
	 * @param element
	 * this adds an Integer obj to the heap
	 * the heap remains in a min heap after the addition.
	 */
	public void addElement(int element) {
			if (heapSize % heapCapacity == 0 && heapSize != 0) {
				increaseCapacity();
			}
			data[heapSize++] = element;
			heapifyUp(heapSize - 1);
	}

	/**
	 * @param indexOne index of the first element to be swapped.
	 * @param indexTwo index of the second element to be swapped.
	 * this swaps two elements in the heap
	 */
	private void swapElements(int indexOne, int indexTwo) {
		Integer temp = data[indexOne];
		data[indexOne] = data[indexTwo];
		data[indexTwo] = temp;
	}


	/**
	 * This increases the capacity of the heap
	 */
	private void increaseCapacity() {
		heapCapacity = heapSize * 2;
		Integer[] newArray = new Integer[heapCapacity];
        System.arraycopy(data, 0, newArray, 0, heapSize);
        data = newArray;
	}

	/**
	 * this prints the integer values of the Integer objects in the heap
	 */
	public void printElements() {
		int remainder = heapSize % 20;
		int numBlanks = ELEMENTS_PER_LINE - remainder;
		if (remainder == 0) {
			numBlanks = 0;
		}
		for (int i = 0; i < heapSize + numBlanks; i++) {
			if (data[i] == null) {
				System.out.printf("%3s", "---");
			} else{
				System.out.printf("%3d", data[i]);
			}
			if ((i + 1) % ELEMENTS_PER_LINE != 0) {
				System.out.print(" ");
			} else {
				System.out.print("\n");
			}
		}
	}

	/**
	 * @param element
	 * this adds an element to an undordered heap and does not maintain
	 * the min heap properties.
	 */
	public void addElementUnorderedHeap(int element) {
		if (heapSize % heapCapacity == 0 && heapSize != 0) {
			increaseCapacity();
		}
		data[heapSize++] = element;
	}

	/**
	 * This removes unused indices of the heap.
	 */
	public void trimHeap() {
		heapCapacity = heapSize;
		Integer[] newArray = new Integer[heapCapacity];
        System.arraycopy(data, 0, newArray, 0, heapSize);
        data = newArray;
	}

	/**
	 * @param lastIndex
	 * @param sizeOfHeap
	 * this sorts the heap
	 */
	public void heapSort(int lastIndex, int sizeOfHeap) {
		if (lastIndex <= 0) {
			return;
		} else {
			swapElements(lastIndex, 0);
			printElements();
			heapifyDown(0, sizeOfHeap - 1);
			heapSort(lastIndex - 1, sizeOfHeap - 1);
		}
	}

	/**
	 * @return data
	 * returns the data array representation of the min heap
	 */
	public Integer[] getData() {
		return data;
	}

	/**
	 * @return heapSize
	 * the number of elements stored in the heap.
	 */
	public int getSize() {
		return heapSize;
	}
}
