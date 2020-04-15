import java.io.PrintWriter;
import java.lang.String;

/**
 * Implementation of the Runqueue interface using a Binary Search Tree.
 *
 * Your task is to complete the implementation of this class.
 * You may add methods and attributes, but ensure your modified class compiles and runs.
 *
 * 
 */
public class BinarySearchTreeRQ implements Runqueue {

    /**
     * Constructs empty queue
     */

     //private bst root;
     BinarySearch bst;
     private BinarySearch foundProc;
     private int sum;


    public BinarySearchTreeRQ() {
        // Implement Me
        bst = null;
       // this.root = null;
        this.foundProc = null;
        this.sum = 0;
    }  // end of BinarySearchTreeRQ()


    @Override
    public void enqueue(String procLabel, int vt) {
        // Implement me
        BinarySearch newNode = new BinarySearch(procLabel, vt);
        if(bst == null){
            bst = newNode;
            return;
        }
    
    BinarySearch focusNode = bst;
    BinarySearch parent = null;
    while (true) {
        parent = focusNode;
        if (vt < focusNode.vtrun) {
            focusNode = focusNode.left;
            if (focusNode == null) {
                parent.left = newNode;
                return;
            }
        } else { // If we get here put the node on the right
            focusNode = focusNode.right;
            if (focusNode == null) {
                parent.right = newNode;
                return;
            }
        }
    }

} // end of enqueue()


    @Override
    public String dequeue() {        
    BinarySearch removed = rHighestPriority();
    if (removed != null) {
        return removed.getProcLabel();
    }
    return "";
    } // end of dequeue()


    @Override
    public boolean findProcess(String procLabel) {
        // Implement me
        foundProc = null;
        searchProcess(bst, procLabel);
        return foundProc != null;
    } // end of findProcess()


    @Override
    public boolean removeProcess(String procLabel) {
        // Implement me
        if (findProcess(procLabel)) {
			bst = delete(bst, procLabel);
			return true;
		} else {
			return false;
		} // end of removeProcess()
	}

	public BinarySearch delete(BinarySearch root, String key) {
		if (root == null) {
			return null;
		}

		root.left = delete(root.left, key);
		root.right = delete(root.right, key);
		if (key.equals(root.procLabel)) {
			System.out.println("If cn");
			if (root.left == null || root.right == null) {
				System.out.println("If");
				BinarySearch temp = root.left != null ? root.left : root.right;
				if (temp == null) {
					System.out.println("if null");
					return null;
				} else {
					System.out.println("else null");
					return temp;
				}
			} else {
				System.out.println("else");
				BinarySearch success = getSuccess(root);
				root.procLabel = success.procLabel;
				root.vtrun = success.vtrun;
				root.right = delete(root.right, success.procLabel);
				return root;
			}
		}
		return root;
    } // end of removeProcess()
    public BinarySearch getSuccess(BinarySearch n) {
		if (n == null)
			return null;
		BinarySearch temp = n.right;
		while (temp.left != null) {
			temp = temp.left;
		}
		return temp;
	}


    @Override
    public int precedingProcessTime(String procLabel) {
        // Implement me
        foundProc = null;
        sum = 0;
        searchProcess(bst, procLabel);
        if (foundProc != null) {
            precedingProc(bst,procLabel);
            return sum;
        }

        return -1; // placeholder, modify this
    } // end of precedingProcessTime()


    @Override
    public int succeedingProcessTime(String procLabel) {
        // Implement me
        foundProc = null;
        sum = 0;
        searchProcess(bst, procLabel);
        if (foundProc != null) {
            succeedingProc(bst,procLabel);
            return sum;
        }
        return -1; // placeholder, modify this
    } // end of precedingProcessTime()
    
    public void searchProcess(BinarySearch root, String procLabel) {
        if (root != null) {
            searchProcess(root.getLeft(), procLabel);
            searchProcess(root.getRight(), procLabel);
            if (root.getProcLabel().equals(procLabel)) {
                foundProc = root;
            }
        }
    }


    
    public void precedingProc(BinarySearch root,String label) {
        if (root != null) {
            if (foundProc.getVtrun() > root.getVtrun() || foundProc.getProcLabel().equalsIgnoreCase(root.getProcLabel())) {
            	System.out.println("if:"+ foundProc.getProcLabel());
            	sum += root.getVtrun();
            }
            System.out.println("else:"+ foundProc.getProcLabel());
            precedingProc(root.getLeft(),label);
            precedingProc(root.getRight(),label);
        }
    }

    
    public void succeedingProc(BinarySearch root,String label) {
        if (root != null) {
            if ((foundProc.getVtrun() <= root.getVtrun() && foundProc != root) || foundProc.getProcLabel().equalsIgnoreCase(root.getProcLabel())) {
            	System.out.println("if:"+ foundProc.getProcLabel());
                sum += root.getVtrun();
            }
            System.out.println("else:"+ foundProc.getProcLabel());
            succeedingProc(root.getLeft(),label);
            succeedingProc(root.getRight(),label);
        }
    }
    

     //  for dequeue method
     private BinarySearch rHighestPriority(BinarySearch proc) {
        if (proc.getLeft().getLeft() == null) {
            BinarySearch minProc = proc.getLeft();
            proc.setLeft(proc.getLeft().getRight());
            minProc.setRight(null);
            return minProc;
        } else {
            return rHighestPriority(proc.getLeft());
        }
    }

    // for dequeue method
    public BinarySearch rHighestPriority() {
        if (bst == null) {
            return null;
        } else if (bst.getLeft() != null) {
            return rHighestPriority(bst);
        } else {
            BinarySearch minProc = bst;
            bst = bst.getRight();
            minProc.setRight(null);
            return minProc;
        }
    }

       
    
    @Override
	public void printAllProcesses(PrintWriter os) {
		// TODO Auto-generated method stub
		if (bst != null) {
			print(bst.left);
			os.write(bst.procLabel + " " + bst.vtrun+ "/n");
			print(bst.right);
		}
		os.flush();
		os.close();

	} // end of printAllProcess()

	
	public void print(BinarySearch bst) {
		// TODO Auto-generated method stub
		if (bst != null) {
			print(bst.left);
			System.out.println(bst.procLabel + " " + bst.vtrun);
			print(bst.right);
		}

	}

	@Override
	public void print() {
		// TODO Auto-generated method stub
		print(bst);
	}

     
} // end of class BinarySearchTreeRQ