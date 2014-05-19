/*-----------------------------------------------------------------------------
 *  Author:        Leesper
 *  Written:       05/19/2014
 *  Compilation:   javac FindCircular.java
 *  Execution:     java FindCircular
 *  My solution to Interview Question 2.6 of "Cracking The Coding Interview"
 *---------------------------------------------------------------------------*/

public class FindCircular
{
	private class Node
	{
		int	 item;
		Node next;
	}
	
	public void testFindCircle()
	{
		Node a = new Node();
		a.item = 1;
		Node b = new Node();
		b.item = 2;
		Node c = new Node();
		c.item = 3;
		Node d = new Node();
		d.item = 4;
		Node e = new Node();
		e.item = 5;
		a.next = b;
		b.next = c;
		c.next = d;
		d.next = e;
		e.next = c;
		
		Node loopHead = findCircle(a);
		if (loopHead != null)
			StdOut.printf("head: %d\n", loopHead.item);
	}
	
	public Node findCircle(Node first)
	{
		if (first == null || first.next == null)
			return null;
		
		Node loophead = null;
		Node slow = first;
		Node fast = first;
		boolean isCircled = false;
		while (fast.next != null)
		{
			slow = slow.next;
			fast = fast.next.next;
			if (slow.equals(fast))
			{
				isCircled = true;
				break;
			}
		}
		
		if (isCircled)
		{
			fast = first;
			while (!fast.equals(slow))
			{
				fast = fast.next;
				slow = slow.next;
			}
			loophead = slow;
			return loophead;
		}
		return null;
	}
	
	public static void main(String[] args)
	{
		FindCircular fc = new FindCircular();
		fc.testFindCircle();
	}
}