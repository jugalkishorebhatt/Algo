
public class LinkedList {
    /** Stored value of node. */
    protected String procLabel;
    protected int vtrun;
    /** Reference to next node. */
    protected LinkedList mNext;

    public LinkedList(String procLabel,int vtrun) {
        this.procLabel = procLabel;
        this.vtrun = vtrun;
        mNext = null;
    }

	public String getProcLabel() {
		return procLabel;
	}

	public void setProcLabel(String procLabel) {
		this.procLabel = procLabel;
	}

	public int getVtrun() {
		return vtrun;
	}

	public void setVtrun(int vtrun) {
		this.vtrun = vtrun;
	}

	public LinkedList getmNext() {
		return mNext;
	}

	public void setmNext(LinkedList mNext) {
		this.mNext = mNext;
	}

	/*
	 * public String getValue() { return procLabel; }
	 * 
	 * 
	 * public LinkedList getNext() { return mNext; }
	 * 
	 * 
	 * public void setValue(String value) { procLabel = value; }
	 * 
	 * 
	 * public void setNext(LinkedList next) { mNext = next; }
	 */
}
