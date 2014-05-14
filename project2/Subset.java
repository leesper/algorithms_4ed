/*----------------------------------------------------------------
 *  Author:        Leesper
 *  Written:       05/13/2014
 *  Compilation:   javac RandomizedQueue.java
 *  Execution:     java RandomizedQueue
 *  My solution to programming assignment 2 of "Algorithms"
 *----------------------------------------------------------------*/

public class Subset 
{
		
	public Subset() { }
	public static void main(String[] args)
	{
		if (args.length == 0)
		{
			StdOut.println(usage());
			return;
		}
		
		int k = Integer.parseInt(args[0]);
		RandomizedQueue<String> rq = new RandomizedQueue<String>();
		
		String str;
		while (!StdIn.isEmpty())
		{
			str = StdIn.readString();
			rq.enqueue(str);
		}
		
		for (int i = 0; i < k; i++)
		{
			StdOut.println(rq.dequeue());
		}
	}
	
	private static String usage()
	{
		return "usage: Subset k";
	}
}