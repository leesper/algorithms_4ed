/*-----------------------------------------------------------------------------
 *  Author:        Leesper
 *  Written:       05/17/2014
 *  Compilation:   javac Hanoi.java
 *  Execution:     java Hanoi
 *  My solution to Interview Question 3.4 of "Cracking The Coding Interview"
 *---------------------------------------------------------------------------*/

public class Hanoi
{
	// recursion pattern used
	public void hanoi(int size, String source, String medium, String dest)
	{
		if (1 == size)
		{
			StdOut.printf("%s --> %s\n", source, dest);
			return;
		}

		hanoi(size - 1, source, dest, medium);
		StdOut.printf("%s --> %s\n", source, dest);
		hanoi(size - 1, medium, source, dest);
		
	}
	
	private class HanoiParam
	{
		private int size;
		private String source;
		private String medium;
		private String dest;
		
		public HanoiParam(int sz, String s, String m, String d)
		{
			size = sz;
			source = s;
			medium = m;
			dest = d;
		}
		
		public String toString()
		{
			return "dump: " + size + source + medium + dest;
		}
	}
	
	/*
	 * recursion simulation with stacks
	 */
	public void hanoiWithStack(int size, String source, String medium, String dest)
	{
		Stack<HanoiParam> running = new Stack<HanoiParam>();
		HanoiParam param = new HanoiParam(size, source, medium, dest);
		running.push(param);
		
		while (!running.isEmpty())
		{
			param = running.pop();
			if (param.size != 1)
			{
				running.push(new HanoiParam(param.size - 1, param.medium, param.source, param.dest));
				running.push(new HanoiParam(1, param.source, param.medium, param.dest));
				running.push(new HanoiParam(param.size - 1, param.source, param.dest, param.medium));
			}
			else
			{
				StdOut.printf("%s --> %s\n", param.source, param.dest);
			}
		}
	}
	
	public static void main(String[] args)
	{
		Hanoi hanoiObj = new Hanoi();
		hanoiObj.hanoi(4, "A", "B", "C");
		StdOut.println();
		hanoiObj.hanoiWithStack(4, "A", "B", "C");
	}
}