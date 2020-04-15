import java.io.PrintWriter;
import java.lang.String;

/**
 * Implementation of the Runqueue interface using an Ordered Array.
 *
 * Your task is to complete the implementation of this class. You may add
 * methods and attributes, but ensure your modified class compiles and runs.
 *
 */
public class OrderedArrayRQ implements Runqueue {

	protected Arrays array[];
	int length;

	/**
	 * Constructs empty queue
	 */
	public OrderedArrayRQ() {
		// Implement Me
		array = null;
		length = 0;
	} // end of OrderedArrayRQ()

	@Override
	public void enqueue(String procLabel, int vt) {
		try {
			// Implement me
			if (array == null) {
				array = new Arrays[1];
				array[0] = new Arrays(procLabel, vt);
			} else {
				// increase size of array by one (not terribly efficient, but for this
				// lab we assume increase array size by one.
				Arrays newArray[] = new Arrays[array.length + 1];

				// copy all existing values of array to newArray
				for (int i = 0; i < array.length; i++) {
					newArray[i] = array[i];
				}

				// new entry, add to end of newArray
				newArray[array.length] = new Arrays(procLabel, vt);

				// update reference of array to point to newArray
				array = newArray;
			}
			length = array.length;
			Arrays temp;
			for (int i = 0; i < length; i++) {
				for (int j = 1; j < (length - i); j++) {
					if (array[j - 1].vt > array[j].vt) {
						// swap elements
						temp = array[j - 1];
						array[j - 1] = array[j];
						array[j] = temp;
					}

				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	} // end of enqueue()

	@Override
	public String dequeue() {
		// Implement me
		Arrays[] anotherArray = new Arrays[array.length - 1];
		String deElem = array[0].PT;
		for (int i = 0; i < anotherArray.length; i++) {
			anotherArray[i] = array[i + 1];
		}
		array = anotherArray;
		return deElem;
	} // end of dequeue()

	@Override
	public boolean findProcess(String procLabel) {

		// If the array is empty
		// or the index is not in array range
		// return the original array
		if (array == null) {

			System.out.println("Process does not exists");
			return false;
		}

		// Create another array of size one less

		// Copy the elements except the index
		// from original array to the other array

		for (int i = 0, k = 0; i < array.length; i++) {
			// if the index is
			// the removal element index
			if (array[i].PT.equalsIgnoreCase(procLabel)) {
				return true;
			}

			// if the index is not
			// the removal element index
		}

		return false; // placeholder, modify this
	} // end of findProcess()

	@Override
	public boolean removeProcess(String procLabel) {

		// If the array is empty
		// or the index is not in array range
		// return the original array
		if (array == null) {

			System.out.println("Process does not exists");
			return false;
		}

		// Create another array of size one less
		Arrays[] anotherArray = new Arrays[array.length - 1];

		// Copy the elements except the index
		// from original array to the other array

		for (int i = 0, k = 0; i < array.length; i++) {
			// if the index is
			// the removal element index
			if (array[i].PT.equalsIgnoreCase(procLabel)) {
				continue;
			}

			// if the index is not
			// the removal element index
			anotherArray[k++] = array[i];
		}
		array = anotherArray;

		return true; // placeholder, modify this
	} // end of removeProcess()

	@Override
	public int succeedingProcessTime(String procLabel) {
		// Implement me

		int x = 0, counter = 0;
		for (int i = 0; i < array.length; i++) {
			if (array[i].PT.equals(procLabel)) {
				x = i;
				break;
			} /*
				 * else { System.out.println("Process Does not exist"); }
				 */
		}
		while (x < array.length) {
			counter = counter + array[x].vt;
			x++;
		}
		return counter; // placeholder, modify this
	}// end of precedingProcessTime()

	@Override
	public int precedingProcessTime(String procLabel) {
		// Implement me

		int x = 0, counter = 0;
		for (int i = array.length - 1; array.length - 1 >= i; i--) {
			if (array[i].PT.equals(procLabel)) {
				x = i;
				break;
			} /*
				 * else { System.out.println("Process Does not exist"); }
				 */
		}
		while (x >= 0) {
			counter = counter + array[x].vt;
			x--;
		}
		return counter; // placeholder, modify this
	} // end of precedingProcessTime()

	@Override
	public void printAllProcesses(PrintWriter os) {
		if (array != null) {
			for (int i = 0; i < array.length; i++) {
				System.out.println();
				os.write(array[i].PT + " " + array[i].vt+"\n");
			}
		}
		os.flush();
		os.close();
	} // end of printAllProcesses()

	public void print() {
		if (array != null) {
			for (int i = 0; i < array.length; i++) {
				System.out.println();
				System.out.print(array[i].PT + " " + array[i].vt);
			}
		}
	}
} // end of class OrderedArrayRQ
