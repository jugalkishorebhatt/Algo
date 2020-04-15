
public class BinarySearch {

	String procLabel;
	int vtrun;
	BinarySearch parent,left, right;
	
	public BinarySearch(String procLabel,int vtrun) {
		// TODO Auto-generated constructor stub
		this.procLabel = procLabel;
		this.vtrun = vtrun;
		this.left = this.right = null;
	}

	public int getVtrun() {
		return vtrun;
	}

	public void setVtrun(int vtrun) {
		this.vtrun = vtrun;
	}

	public BinarySearch getLeft() {
        return this.left;
    }

    public void setLeft(BinarySearch left) {
        this.left = left;
	}
	
	public BinarySearch getRight() {
        return this.right;
    }

    public void setRight(BinarySearch right) {
		this.right = right;
	}

	public String getProcLabel() {
        return this.procLabel;
	}
	
	public BinarySearch() {
		// TODO Auto-generated constructor stub
	}
}