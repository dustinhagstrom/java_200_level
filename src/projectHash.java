//File: projectHash.java
/******************************************************************************
* A hash table is generated. When the hash table is generated, this class
* automatically prints the location of the elements or reports that the 
* elements could not be stored within the hash table if the element collides
* with other elements and it reaches the end of the hash table
*
* @author Dustin Hagstrom
*
* @version Dec 3, 2022
* 
******************************************************************************/
public class projectHash {
   // Invariant of the projectHash class:
   //   1. manyItems is a the number of elements to hash.
   //   2. keys is an array that holds elements to be stored in hash table
   //   3. data is the hash table that stores keys.
		private int manyItems;
		private Integer[] keys;
        private Integer[] data;


    /**
     * @param hashData the array that holds the values to be hashed.
     * this constructs a hash table and begins the process for storing and printing
     */
    projectHash(Integer[] hashData){
        manyItems = hashData.length;
        keys = hashData;
        int i = 3;

        while((Math.pow(2, i) - 5) < manyItems) {
            i++;
        }
        int sizeOfData = (int) Math.pow(2, i);
        data = new Integer[sizeOfData];
        
        for (int j = 0; j < manyItems; j++){
            this.insert(keys[j], manyItems, sizeOfData);
        }
    }



    /**
     * @param n the element to be stored
     * @param manyItems the number of elements to store.
     * @param tableSize the size of the hash table
     * if the element fits in the hash table then this method inserts it into the
     * table and then prints its location. If it can not be stored, then this
     * method reports that.
     */
    public void insert (int n, int manyItems, int tableSize) {
            int numCollisions = 0;
            int indexToHash = hash(n, tableSize);

            int retval = probePosition(indexToHash);
            while (retval == -1 && indexToHash < tableSize) {
                indexToHash = nextPositionToProbe(indexToHash, ++numCollisions, tableSize);
                retval = probePosition(indexToHash);
            }

            if (retval == -1) {
                System.out.print("Key ");
                System.out.printf("%3d", n);
                System.out.print(" could not hash to table after ");
                System.out.printf("%2d", numCollisions);
                System.out.println(" collisions.");
            } else {
                data[retval] = n;
                System.out.print("Key ");
                System.out.printf("%3d", n);
                System.out.print(" hashes to position ");
                System.out.printf("%2d", retval);
                System.out.print(" after ");
                System.out.print(numCollisions);
                System.out.println(" collisions.");
            }
    }

    /**
     * @param index the index to probe to see if an element is already stored.
     * @return the index that is available for storage or a -1 to indicate
     * that something is already stored there.
     */
    private int probePosition (int index) {

        if (data[index] == null) {
            return index;
        } else {
            return -1;
        }
    }


    /**
     * @param k the element to hash
     * @param t the size of the hash table
     * @return
     * this function returns the hashed value of the element.
     */
    public static int hash (int k, int t) {
		int h = (( k >> 16) ^ k) * 0x119df1f3;
		h = (( h >> 16) ^ h ) * 0x119df1f3;
		return (( h >> 16) ^ h ) % t;
	}

	/**
	 * @param lastIndexProbed the previous index checked to be empty, initially
     * this value is zero.
	 * @param n this is the number of keys that we must store
	 * @param t this is the table size of the hash table.
	 * @return this method returns the index value of the next position within
     * the hash table that we must check.
	 */
	public static int nextPositionToProbe (int lastIndexProbed, int n, int t) {
		return (lastIndexProbed + n * (n +1) / 2) % t;
	}
}