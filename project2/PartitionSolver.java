/*-----------------------------------------------------------------------------
 *  Author:        Leesper
 *  Written:       05/13/2014
 *  Compilation:   javac SinglyLinkedList.java
 *  Execution:     java SinglyLinkedList
 *  My solution to Interview Question 2.4 of "Cracking The Coding Interview"
 *---------------------------------------------------------------------------*/

import java.util.NoSuchElementException;

public class PartitionSolver
{
	private Node pre;
	private int N;
	
	private class Node
	{
		int	 item;
		Node next;
	}
	
	public PartitionSolver()
	{
		pre = new Node();
		pre.next = null;
	}
	
	public void addFirst(int item)
	{
		Node oldfirst = pre.next;
		Node curr = new Node();
		curr.item = item;
		pre.next = curr;
		curr.next = oldfirst;
		++N;
	}
	
	public int size()
	{
		return N;
	}
	
	public boolean isEmpty()
	{
		return 0 == N;
	}
	
	public void partition(int value)
	{
		if (isEmpty())
			throw new NoSuchElementException();
		
		Node curr = pre.next;
		while (curr != null)
		{
			if (curr.item == value)
				break;
			curr = curr.next;
		}
		
		if (curr == null)
			throw new NoSuchElementException();
		
		Node first = pre.next;
		int temp = first.item;
		first.item = curr.item;
		curr.item = temp;
		
		Node prev = pre.next;
		curr = prev.next;
		while (curr != null)
		{
			if (curr.item < value)
			{
				addFirst(curr.item);
				prev.next = curr.next;
			}
			else
			{
				prev = prev.next;
			}
			curr = curr.next;
			
		}
		
	}
	
	public void testPartition()
	{
		partition(9);
		
		Node curr = pre.next;
		while (curr != null)
		{
			StdOut.println(curr.item);
			curr = curr.next;
		}
	}
	
	public static void main(String[] args)
	{
		PartitionSolver ps = new PartitionSolver();
		ps.addFirst(20);
		ps.addFirst(5);
		ps.addFirst(3);
		ps.addFirst(9);
		ps.addFirst(21);
		ps.addFirst(11);
		ps.addFirst(2);
		ps.addFirst(1);
		ps.addFirst(66);
		StdOut.printf("ps size: %d\n", ps.size());
		
		ps.testPartition();
		
	}
	
}