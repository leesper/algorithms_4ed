/*************************************************************************
 * Name:	Leesper
 * Email:	394683518@qq.com
 *
 * Compilation:  javac Point.java
 * Execution:
 * Dependencies: StdDraw.java
 *
 * Description: An immutable data type for points in the plane.
 *
 *************************************************************************/

import java.util.Arrays;
import java.util.Comparator;

public class Point implements Comparable<Point> {

    // compare points by slope
    public final Comparator<Point> SLOPE_ORDER = new SlopOrder();       // YOUR DEFINITION HERE

    private final int x;                              // x coordinate
    private final int y;                              // y coordinate
    
    private class SlopOrder implements Comparator<Point>
    {
    	public int compare(Point p0, Point p1) {
    		double slope0 = slopeTo(p0);
    		double slope1 = slopeTo(p1);
    		if 		(slope0 > slope1)	return 1;
    		else if (slope0 < slope1)	return -1;
    		else						return 0;
    	}
    }

    // create the point (x, y)
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    // plot this point to standard drawing
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    // draw line between this point and that point to standard drawing
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // slope between this point and that point
    public double slopeTo(Point that) {
    	final int x0 = this.x;
    	final int y0 = this.y;
    	final int x1 = that.x;
    	final int y1 = that.y;
    	
    	if (x0 == x1 && y0 == y1)	return Double.NEGATIVE_INFINITY;	// degenerate
    	if (x0 == x1)	return Double.POSITIVE_INFINITY;				// vertical
    	if (y0 == y1)	return 0.0;										// horizontal
    	return (double)(y1 - y0) / (x1 - x0);
    }

    // is this point lexicographically smaller than that one?
    // comparing y-coordinates and breaking ties by x-coordinates
    public int compareTo(Point that) {
    	final int x0 = this.x;
    	final int y0 = this.y;
    	final int x1 = that.x;
    	final int y1 = that.y;
    	
    	if (y0 < y1)	return -1;
    	if (y0 > y1)	return 1;
    	
    	if (y0 == y1) 
    	{
    		if (x0 < x1)	return -1;
    		if (x0 > x1)	return 1;
    	}
    	return 0;
    }

    // return string representation of this point
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    // unit test
    public static void main(String[] args) {
    	String filename = args[0];
        In in = new In(filename);
        int N = in.readInt();
        Point[] points = new Point[N];
        for (int i = 0; i < N; i++) {
            int x = in.readInt();
            int y = in.readInt();
            Point p = new Point(x, y);
            points[i] = p;
        }
        Arrays.sort(points, points[0].SLOPE_ORDER);
        for (Point p : points)
        	StdOut.println(p);
    }
}
