import java.util.Arrays;

public class Brute {
   public static void main(String[] args)
   {
	   StdDraw.setXscale(0, 32768);
       StdDraw.setYscale(0, 32768);
       StdDraw.show(0);
       
	   String filename = args[0];
       In in = new In(filename);
       int N = in.readInt();
       Point[] points = new Point[N];
       for (int i = 0; i < N; i++) {
           int x = in.readInt();
           int y = in.readInt();
           Point p = new Point(x, y);
           points[i] = p;
           p.draw();
       }
       
       Arrays.sort(points, 0, N);
       for (int i = 0; i < N; i++) 
    	   for (int j = i+1; j < N; j++)
    		   for (int m = j+1; m < N; m++)
    			   for (int n = m+1; n < N; n++) {
    				   double slope1 = points[i].slopeTo(points[j]);
    				   double slope2 = points[i].slopeTo(points[m]);
    				   double slope3 = points[i].slopeTo(points[n]);
    				   if (slope1 == slope2 && slope2 == slope3)
    				   {
    					   points[i].drawTo(points[n]);
    					   StdOut.printf("%s -> %s -> %s -> %s\n", 
    							   points[i].toString(), points[j].toString(),
    							   points[m].toString(), points[n].toString());
    				   }
    			   }
       StdDraw.show(0);
    		   
   }
}