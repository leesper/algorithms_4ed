import java.util.Arrays;

public class Fast {
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
       
       // sort by natural order
       Arrays.sort(points);
       
       for (int i = 0; i < N-3; i++)
       {
    	   // sort by slope order [i+1 .. N)
    	   Arrays.sort(points, i+1, N, points[i].SLOPE_ORDER);
    	   // sort by slope order [0..i) to check whether i has been processed
    	   Arrays.sort(points, 0, i, points[i].SLOPE_ORDER);
    	   int head = i + 1;
    	   int tail = i + 2;
    	   while (tail < N)
    	   {
    		   while (tail < N && points[i].slopeTo(points[head]) == points[i].slopeTo(points[tail]))
    			   ++tail;
    		   if (tail - head >= 3) {
    			   int before = i - 1;
    			   boolean processed = false;
    			   while (before >= 0)
    			   {
    				   if (points[i].slopeTo(points[before]) == points[i].slopeTo(points[head]))
    				   {
    					   processed = true;
    					   break;
    				   }
    				   --before;
    			   }
    			   if (!processed)
    			   {	
    				   points[i].drawTo(points[tail-1]);
    				   String output = points[i].toString() + " -> ";
    				   for (int j = head; j < tail; j++)
    				   {
    					   output += points[j].toString();
    					   if (j != tail - 1) output += " -> ";
    				   }
    				   StdOut.println(output);
    			   }
    		   }
    		   head = tail;
    		   tail = head + 1;
    	   }
    	   // recover the natural order for the next
    	   Arrays.sort(points);
       }
       StdDraw.show(0);
   }
}