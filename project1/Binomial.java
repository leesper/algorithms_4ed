/*************************************************************************
 *  Compilation:  javac Binomial.java
 *  Execution:    java Binomial N k p
 *  Dependencies: StdOut.java
 *
 *  Reads in N, k, and p as command-line arguments and prints out
 *  (N choose k) p^k (1-p)^N-k.
 *
 *  % java Binomial 5 2 .25
 *  0.263671875
 *  0.263671875
 *
 *  % java Binomial 5 3 .25
 *  0.087890625
 *  0.087890625
 *
 *  % java Binomial 5 0 .25
 *  0.2373046875
 *  0.2373046875
 *
 *  % java Binomial 5 5 .25
 *  9.765625E-4
 *  9.765625E-4
 *
 *************************************************************************/

public class Binomial { 

    // slow
    public static double binomial(int N, int k, double p) {
        if (N == 0 && k == 0) return 1.0;
        if (N < 0 || k < 0) return 0.0;
        return (1.0 - p) * binomial(N-1, k, p) + p * binomial(N-1, k-1, p);
    }
    
    public static double binomial_DP(int N, int k, double p, double[][] calculated) {
    	if (N < 0 || k < 0) return 0.0;
    	if (N == 0 && k == 0) {
    		calculated[N][k] = 1.0;
    		return calculated[N][k];
    	}
    	if (calculated[N][k] != Double.POSITIVE_INFINITY) {
    		return calculated[N][k];
    	}
    	calculated[N][k] = (1.0 - p) * binomial_DP(N-1, k, p, calculated) + 
    			p * binomial_DP(N-1, k-1, p, calculated);
    	return calculated[N][k];
    }

    public static void main(String[] args) { 
        int N = Integer.parseInt(args[0]);
        int k = Integer.parseInt(args[1]);
        double p = Double.parseDouble(args[2]);
        //StdOut.println(binomial(N, k, p));
        
        double[][] calculated = new double[N + 1][k + 1];
        for (int i = 0; i < N + 1; i++) {
        	for (int j = 0; j < k + 1; j++) {
        		calculated[i][j] = Double.POSITIVE_INFINITY;
        	}
        }
        
        StdOut.println(binomial_DP(N, k, p, calculated));
    }

}
