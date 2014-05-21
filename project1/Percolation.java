/*----------------------------------------------------------------
 *  Author:        Leesper
 *  Written:       4/12/2014
 *
 *  Compilation:   javac Percolation.java
 *  Execution:     java Percolation
 *  
 *  My solution to the programming assignments in Week 1 for "Algorithms" by Sedgewick
 *----------------------------------------------------------------*/

public class Percolation {
	public Percolation(int N) {
		if (N < 1) {
			throw new IllegalArgumentException("N must be >= 1");
		}
		size = N;
		wquf = new WeightedQuickUnionUF(size * size + 2);
		backwash = new WeightedQuickUnionUF(size * size + 1);
		// op[vtop] is virtual top and op[vbot] is virtual bottom
		op = new boolean[size * size + 2];
		vtop = 0;
		vbot = size * size + 1;
	}
	
	/**
	 *   Open the (i, j) site in N-by-N grid.
	 */
	public void open(int i, int j) {
		if (isOpen(i, j)) 
			return;
		
		op[xyTo1D(i, j)] = true;
		
		if (isTopRow(i)) {
			op[vtop] = true;
			// make sites in top row union to virtual top
			wquf.union(vtop, xyTo1D(i, j));
			backwash.union(vtop, xyTo1D(i, j));
		}

		if (isBotRow(i)) {
			op[vbot] = true;
			// make sites in bottom row union to virtual bottom
			wquf.union(vbot, xyTo1D(i, j));
		}

		// up
		if (checkValid(i - 1, j) && isOpen(i - 1, j) 
				&& !wquf.connected(xyTo1D(i - 1, j), xyTo1D(i, j))) {
			wquf.union(xyTo1D(i - 1, j), xyTo1D(i, j));
			backwash.union(xyTo1D(i - 1, j), xyTo1D(i, j));
		}
		// down
		if (checkValid(i + 1, j) && isOpen(i + 1, j) 
				&& !wquf.connected(xyTo1D(i + 1, j), xyTo1D(i, j))) {
			wquf.union(xyTo1D(i + 1, j), xyTo1D(i, j));
			backwash.union(xyTo1D(i + 1, j), xyTo1D(i, j));
		}
		// left
		if (checkValid(i, j - 1) && isOpen(i, j - 1)
				&& !wquf.connected(xyTo1D(i, j - 1), xyTo1D(i, j))) {
			wquf.union(xyTo1D(i, j - 1), xyTo1D(i, j));
			backwash.union(xyTo1D(i, j - 1), xyTo1D(i, j));
		}
		// right
		if (checkValid(i, j + 1) && isOpen(i, j + 1)
				&& !wquf.connected(xyTo1D(i, j + 1), xyTo1D(i, j))) {
			wquf.union(xyTo1D(i, j + 1), xyTo1D(i, j));
			backwash.union(xyTo1D(i, j + 1), xyTo1D(i, j));
		}
	}
	
	/**
	 *   return true if the (i, j) site in N-by-N grid is open.
	 */
	public boolean isOpen(int i, int j) { 
		if (!checkValid(i, j)) {
			throw new IndexOutOfBoundsException("index i out of range");
		}
		return op[xyTo1D(i, j)] == true;
	}
	
	/**
	 *   return true if the (i, j) site in N-by-N grid is full.
	 *   A full site is an open site that can be connected to an 
	 *   open site in the top row via a chain of neighboring 
	 *   (left, right, up, down) open sites.	 
	 */
	public boolean isFull(int i, int j) { 
		if (!isOpen(i, j)) {
			return false;
		}
		return backwash.connected(vtop, xyTo1D(i, j));
	}
	
	/**
	 *   return true if this N-by-N system is percolated
	 */
	public boolean percolates() { 
		return wquf.connected(vtop, vbot);
	}
	
	// convert 2D index into 1D index (i, j) -> (i-1)*N+(j-1)
	private int xyTo1D(int i, int j) { return (i - 1) * size + j; }
	
	/**
	 *   return false if either i or j is out of range
	 *   note that i/j must be [1, N]
	 */
	private boolean checkValid(int i, int j) {
		if (i <= 0 || i > size) {
			return false;
		}
		if (j <= 0 || j > size) {
			return false;
		}
		return true;
	}
	
	private boolean isTopRow(final int i) {
		return i == 1;
	}
	
	private boolean isBotRow(final int i) {
		return i == size;
	}
	
	private final int size;
	private boolean[] op; // mark whether it is open or blocked
	private final int vtop;
	private final int vbot;
	private WeightedQuickUnionUF wquf;
	private WeightedQuickUnionUF backwash;
	
	public static void main(String[] args) {
		In in = new In(args[0]);      // input file
        int N = in.readInt();         // N-by-N percolation system
        Percolation perc = new Percolation(N);
        StdOut.printf("is full: %b\n", perc.isFull(1, 1));
        while (!in.isEmpty()) {
            int i = in.readInt();
            int j = in.readInt();
            perc.open(i, j);
        }
	}
}