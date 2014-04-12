public class Percolation {
	public Percolation(int N) {
		if (N < 1) {
			throw new IllegalArgumentException("N must be >= 1");
		}
		size = N;
		wquf = new WeightedQuickUnionUF(size * size);
		op = new boolean[size * size];
		for (int i = 0; i < size * size; i++) {
			op[i] = false;
		}
	}
	
	public void open(int i, int j) {
		if (!isOpen(i, j)) op[xyTo1D(i, j)] = true;

		// up
		if (checkValid(i - 1, j) && isOpen(i - 1, j) && 
			!wquf.connected(xyTo1D(i - 1, j), xyTo1D(i, j))) {
			wquf.union( xyTo1D(i - 1, j), xyTo1D(i, j) );
		}
		// down
		if (checkValid(i + 1, j) && isOpen(i + 1, j) &&
			!wquf.connected(xyTo1D(i + 1, j), xyTo1D(i, j))) {
			wquf.union( xyTo1D(i + 1, j), xyTo1D(i, j) );
		}
		// left
		if (checkValid(i, j - 1) && isOpen(i, j - 1) &&
			!wquf.connected(xyTo1D(i, j - 1), xyTo1D(i, j))) {
			wquf.union( xyTo1D(i, j - 1), xyTo1D(i, j) );
		}
		// right
		if (checkValid(i, j + 1) && isOpen(i, j + 1) &&
			!wquf.connected(xyTo1D(i, j + 1), xyTo1D(i, j))) {
			wquf.union( xyTo1D(i, j + 1), xyTo1D(i, j) );
		}
	}
	
	public boolean isOpen(int i, int j) { 
		if (!checkValid(i, j)) {
			throw new IndexOutOfBoundsException("index i out of range");
		}
		return op[xyTo1D(i, j)] == true;
	}
	
	public boolean isFull(int i, int j) { 
		if (!checkValid(i, j)) {
			throw new IndexOutOfBoundsException("index i out of range");
		}
		return op[xyTo1D(i, j)] == false;
		
	}
	
	public boolean percolates() { return true; }
	
	// convert 2D index into 1D index (i, j) -> (i-1)*N+(j-1)
	private int xyTo1D(int i, int j) { return (i - 1) * size + (j - 1); }
	
	private boolean checkValid(int i, int j) {
		if (i <= 0 || i > size) {
			return false;
		}
		if (j <= 0 || j > size) {
			return false;
		}
		return true;
	}
	
	private WeightedQuickUnionUF wquf;
	private final int size;
	private boolean[] op; // mark whether it is open or blocked
	
	public static void main(String[] args) {
		Percolation p = new Percolation(4);
		StdOut.printf("(1, 1) status %b\n", p.isOpen(1, 1));
		StdOut.printf("(1, 2) status %b\n", p.isOpen(1, 2));
		p.open(1, 1);
		p.open(1, 2);
		StdOut.printf("(1, 1) status %b, (1, 2) status %b, connected %b\n",
				p.isOpen(1, 1), p.isOpen(1, 2), p.wquf.connected(p.xyTo1D(1, 1), p.xyTo1D(1, 2)));
	}
}