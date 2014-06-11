public class Board {
	private final int[][] tiles;
	private final int N; 			// dimension
	
    public Board(int[][] blocks) 
    {
    	int nrows = blocks.length;
    	int ncols = blocks[0].length;
    	if (nrows != ncols)
    		throw new IllegalArgumentException("passing non-square matrix");
    	
    	N = nrows;
    	tiles = new int[N][N];
    	for (int i = 0; i < blocks.length; i++)
    		for (int j = 0; j < blocks[i].length; j++) 
    			tiles[i][j] = blocks[i][j];
    }
    
    public int dimension()
    {
    	return N;
    }
    
    public int hamming()
    {
    	int count = 0;
    	for (int i = 0; i < tiles.length; i++)
    		for (int j = 0; j < tiles[i].length; j++)
    		{
    			if (tiles[i][j] == 0)	continue;
    			if (!inPlace(i, j, tiles[i][j])) 
    			{
    				++count;
    			}
    		}
    	return count;
    }
    
    private boolean inPlace(int i, int j, int val) {
    	if (val == i * N + j + 1) {
    		return true;
    	}
    	return false;
    }
    
    public int manhattan()
    {
    	int count = 0;
    	for (int i = 0; i < tiles.length; i++)
    		for (int j = 0; j < tiles[i].length; j++)
    		{
    			if (tiles[i][j] == 0)	continue;
    			int remainder = tiles[i][j] % N;
    			int posi, posj;
    			if (remainder != 0)
    			{
    				posi = tiles[i][j] / N;
    				posj = remainder - 1;
    			} 
    			else
    			{
    				posi = tiles[i][j] / N - 1;
    				posj = N - 1;
    			}
    			int diff = Math.abs(posi - i) + Math.abs(posj - j);
    			count += diff;
    			StdOut.printf("%d -> (%d, %d) - (%d, %d) = %d\n", 
    					tiles[i][j], posi, posj, i, j, diff);
    		}
    	return count;
    }
    
    /*
    public boolean isGoal()                // is this board the goal board?
    public Board twin()                    // a board obtained by exchanging two adjacent blocks in the same row
    public boolean equals(Object y)        // does this board equal y?
    public Iterable<Board> neighbors()     // all neighboring boards
    */
    
    public String toString()      
    {
    	StringBuilder s = new StringBuilder();
    	s.append(N + "\n");
    	for (int i = 0; i < N; i++) {
    		for (int j = 0; j < N; j++) {
    			s.append(String.format("%2d ", tiles[i][j]));
    		}
    		s.append("\n");
    	}
    	return s.toString();
    }
    
    public static void main(String[] args) 
    {
    	int[][] blocks = {
    		{8, 1, 3},
    		{4, 0, 2},
    		{7, 6, 5}
    	};
    	Board b = new Board(blocks);
    	StdOut.printf("dimensions: %d\n", b.dimension());
    	StdOut.printf("hamming: %d\n", b.hamming());
    	StdOut.printf("manhattan: %d\n", b.manhattan());
    	StdOut.printf("Board: %s\n", b.toString());
    }
}