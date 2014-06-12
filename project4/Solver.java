public class Solver {
	private class SNode implements Comparable<SNode>
	{
		public Board board;
		public int	 moves;
		public SNode previous;
		
		public SNode(Board initial) 
		{
			board = initial;
			moves = 0;
			previous = null;
		}
		
		public SNode(Board initial, int mov, SNode prev)
		{
			board = initial;
			moves = mov;
			previous = prev;
		}
		
		public int compareTo(SNode that)
		{
			int thisMove = this.board.manhattan() + this.moves;
			int thatMove = that.board.manhattan() + that.moves;
			if (thisMove < thatMove)	return -1;
			if (thisMove > thatMove)	return 1;
			return 0;
		}
	}
	private Stack<Board> solutions;
	private boolean solvable;
	private int M;	// minimal moves
	
	// find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial)            
    {
    	solvable = false;
    	solutions = null;
    	M = -1;
    	MinPQ<SNode> nq = new MinPQ<SNode>();
    	MinPQ<SNode> tq = new MinPQ<SNode>();
    	
    	nq.insert(new SNode(initial));
    	tq.insert(new SNode(initial.twin()));
    	
    	while (!nq.isEmpty() && !tq.isEmpty())
    	{
    		SNode n = nq.delMin();
    		SNode t = tq.delMin();
    		
    		// test if we have reached the goal
    		if (t.board.manhattan() == 0)	break;

    		if (n.board.manhattan() == 0)
    		{
    			// rebuild the solutions
    			solutions = new Stack<Board>();
    			solutions.push(n.board);
    			solvable = true;
    			M = n.moves;
    			
    			SNode prev = n.previous;
    			while (prev != null)
    			{
    				solutions.push(prev.board);
    				M += prev.moves;
    				prev = prev.previous;
    			}
    			break;
    		}
    		
    		// putting neighbors into queues
    		for (Board nbr : n.board.neighbors())
    			if (n.previous == null || !n.previous.board.equals(nbr))
    				nq.insert(new SNode(nbr, 1, n));
    			
    		for (Board tbr : t.board.neighbors())
    			if (t.previous == null || !t.previous.board.equals(tbr))
    				tq.insert(new SNode(tbr, 1, t));
    		
    	}
    }
    
    // is the initial board solvable?
    public boolean isSolvable()
    {
    	return solvable;
    }

    // min number of moves to solve initial board; -1 if no solution
    public int moves()                      
    {
    	return M;
    }
    
    // sequence of boards in a shortest solution; null if no solution
    public Iterable<Board> solution()
    {
    	return solutions;
    }
    
    public static void main(String[] args)
    {
    	// create initial board from file
        In in = new In(args[0]);
        int N = in.readInt();
        int[][] blocks = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }
}