/*----------------------------------------------------------------
 *  Author:        Leesper
 *  Written:       4/12/2014
 *
 *  Compilation:   javac PercolationStats.java
 *  Execution:     java PercolationStats 200 100
 *  
 *  My solution to the programming assignments in Week 1 for "Algorithms" by Sedgewick
 *----------------------------------------------------------------*/

public class PercolationStats {
	public PercolationStats(int N, int T) {
		if (N <= 0) {
			throw new IllegalArgumentException("Invalid parameter N");
		}
		if (T <= 0) {
			throw new IllegalArgumentException("Invalid parameter T");
		}
		size = N;
		times = T;
		fraction = new double[times];
		
		for (int t = 0; t < times; t++) {
			int opened = 0;
			perc = new Percolation(size);
			int randi, randj;
			while (!perc.percolates()) {
				do {
					randi = StdRandom.uniform(1, size + 1);
					randj = StdRandom.uniform(1, size + 1);
				} while (perc.isOpen(randi, randj));
				perc.open(randi, randj);
				++opened;
			}
			fraction[t] = (double)opened / (size * size);
		}
	}
	
	/**
	 *   return the p* threshold
	 */
	public double mean() { return StdStats.mean(fraction); }
	
	
	/**
	 *   measures how p* sharpen
	 */
	public double stddev() {
		if (times == 1)
		{
			return Double.NaN;
		}
		return StdStats.stddev(fraction); 
	}
	
	/**
	 *   left side of confidentce integral
	 */
	public double confidenceLo() { return mean() - 1.96 * stddev() / Math.sqrt((double)times); }
	
	
	/**
	 *   right side of confidence integral
	 */
	public double confidenceHi() { return mean() + 1.96 * stddev() / Math.sqrt((double)times); }
	
	public static void main(String[] args) {
		if (args.length != 2) {
			StdOut.println("Usage: PercolationStats size times\n");
			return;
		}
		int N = Integer.parseInt(args[0]);
		int T = Integer.parseInt(args[1]);
		PercolationStats percStat = new PercolationStats(N, T);
		StdOut.printf("mean\t\t\t= %f\n", percStat.mean());
		StdOut.printf("stddev\t\t\t= %f\n", percStat.stddev());
		StdOut.printf("95%% confidence interval\t= %f, %f\n", 
				percStat.confidenceLo(), percStat.confidenceHi());
	}
	
	private final int size;
	private int times;
	private double[] fraction;
	private Percolation perc;
}