/*-----------------------------------------------------------------------------
 *  Author:        Leesper
 *  Written:       05/20/2014
 *  Compilation:   javac StackSorter.java
 *  Execution:     java StackSorter
 *  My solution to Interview Question 3.6 of "Cracking The Coding Interview"
 *---------------------------------------------------------------------------*/

import java.util.NoSuchElementException;

public class StackSorter
{
	public Stack<Integer> sort(Stack<Integer> stk)
	{
		if (null == stk)
			throw new NullPointerException();
		if (stk.size() == 1)
			return stk;

		Stack<Integer> tmpStk1 = new Stack<Integer>();
		Stack<Integer> tmpStk2 = new Stack<Integer>();
		while (!stk.isEmpty())
		{
			Integer item = stk.pop();
			if (tmpStk1.isEmpty())
			{
				tmpStk1.push(item);
			}
			else
			{
				while (!tmpStk1.isEmpty() && item.compareTo(tmpStk1.peek()) > 0)
					tmpStk2.push(tmpStk1.pop());
				
				tmpStk1.push(item);
				
				while (!tmpStk2.isEmpty())
					tmpStk1.push(tmpStk2.pop());
			}
		}
		
		while (!tmpStk1.isEmpty())
			stk.push(tmpStk1.pop());
		
		return stk;
	}
	
	public void testSort()
	{
		Stack<Integer> stk = new Stack<Integer>();
		stk.push(1);
		stk.push(6);
		stk.push(2);
		stk.push(4);
		stk.push(5);
		stk.push(3);
		stk = sort(stk);
		
		for (Integer i : stk)
		{
			StdOut.println(i);
		}
	}
	
	public static void main(String[] args)
	{
		StackSorter ss = new StackSorter();
		ss.testSort();
	}
}