import java.io.PrintWriter;

/**
 * Implementation of the run queue interface using an Ordered Link List.
 *
 * Your task is to complete the implementation of this class. You may add
 * methods and attributes, but ensure your modified class compiles and runs.
 *
 */
public class OrderedLinkedListRQ implements Runqueue {

	protected LinkedList mHead;

	/** Length of list. */
	protected int mLength;

	/**
	 * Constructs empty linked list
	 */
	public OrderedLinkedListRQ() {
		// Implement me
		mHead = null;
		mLength = 0;

	} // end of OrderedLinkedList()

	@Override
	public void enqueue(String procLabel, int vt) {
		LinkedList newNode = new LinkedList(procLabel, vt);

		// If head is empty, then list is empty and head reference need to be
		// initialised.
		if (mHead == null) {
			mHead = newNode;
		}
		// otherwise, add node to the head of list.
		else {
			newNode.setmNext(mHead);
			mHead = newNode;
		}
		mLength++;
		/*
		 * if (mLength > 1) { for (int i = 0; i < mLength - 1; i++) { LinkedList
		 * currentNode = mHead; LinkedList next = currentNode.getmNext();
		 * System.out.println("In I loop" + mLength); for (int j = 0; j < mLength - i -
		 * 1; j++) { if (currentNode.getVtrun() > next.getVtrun()) {
		 * System.out.println("C: " + currentNode.getProcLabel() + "  N:" +
		 * next.getProcLabel()); LinkedList temp = currentNode; currentNode = next; next
		 * = temp; } currentNode = next; next = next.getmNext(); } } print(); }
		 */

		if (mLength > 1) {
			for (int i = 0; i < mLength; i++) {
				LinkedList currentNode = mHead;
				LinkedList next = mHead.mNext;
				for (int j = 0; j < mLength - 1; j++) {
					if (currentNode.vtrun > next.vtrun) {
						String label = currentNode.procLabel;
						int run = currentNode.vtrun;
						currentNode.vtrun = next.vtrun;
						currentNode.procLabel = next.procLabel;
						next.procLabel = label;
						next.vtrun = run;
					}
					currentNode = next;
					next = next.mNext;
				}
			}
		}

	} // end of enqueue()

	@Override
	public String dequeue() {
		// Implement me
		LinkedList currNode = mHead;
		mHead = currNode.getmNext();
		mLength--;
		return currNode.getProcLabel(); // placeholder, modify this
	} // end of dequeue()

	@Override
	public boolean findProcess(String procLabel) {
		LinkedList node = mHead;
		boolean flag = true;
		// TODO Auto-generated method stub
		while (node != null) {
			if (node.getProcLabel().equals(procLabel)) {
				flag = true;
				mLength--;
				break;
			} else {
				flag = false;
				node = node.getmNext();
			}

		}
		return flag;
	} // end of findProcess()

	@Override
	public boolean removeProcess(String procLabel) {
		LinkedList node = mHead;
		LinkedList prev = null;
		boolean flag = true;
		// TODO Auto-generated method stub

		while (node.getmNext() != null) {
			prev = node.getmNext();
			if (node.getmNext().getProcLabel().equals(procLabel)) {
				flag = true;
				node.setmNext(prev.getmNext());
				mLength--;
				break;
			} else {
				flag = false;
				node = node.getmNext();
			}

		}
		return flag;
	} // End of removeProcess()

	@Override
	public int precedingProcessTime(String procLabel) {
		LinkedList node = mHead;
		int count = 0;
		// TODO Auto-generated method stub
		while (node != null) {
			count += node.getVtrun();
			if (node.getProcLabel().equals(procLabel)) {
				return count;
			}
			System.out.println("if Label: " + node.getProcLabel());
			node = node.getmNext();
		}
		return count;
	} // end of precedingProcessTime()

	@Override
	public int succeedingProcessTime(String procLabel) {
		LinkedList node = mHead;
		int count = 0;
		// TODO Auto-generated method stub
		while (node != null) {
			System.out.println("Value: "+node.getProcLabel().compareTo(procLabel));
			if (node.getProcLabel().compareTo(procLabel) == 0) {
				System.out.println("In the if loop");
				while(node != null) {
					System.out.println("In the inner loop");
					count += node.vtrun;
					node = node.mNext;
				}
				return count;
			}
			System.out.println("else Label: " + node.getProcLabel());
			node = node.mNext;
		}
		return count;
	} // end of precedingProcessTime()

	@Override
	public void printAllProcesses(PrintWriter os) {
		LinkedList node = mHead;
		// TODO Auto-generated method stub
		while (node != null) {
			os.write(node.getProcLabel() + " " + node.getVtrun()+"\n");
			node = node.getmNext();
		}
		os.flush();
		os.close();

	} // end of printAllProcess()

	@Override
	public void print() {
		LinkedList node = mHead;
		// TODO Auto-generated method stub
		while (node != null) {
			System.out.println(node.getProcLabel() + " " + node.getVtrun());
			node = node.getmNext();
		}

	}

} // end of class OrderedLinkedListRQ
