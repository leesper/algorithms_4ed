/*-----------------------------------------------------------------------------
 *  Author:        Leesper
 *  Written:       05/13/2014
 *  Compilation:   javac SinglyLinkedList.java
 *  Execution:     java SinglyLinkedList
 *  My solution to Interview Question 2.5 of "Cracking The Coding Interview"
 *---------------------------------------------------------------------------*/

public class DigitAdder
{
	private Node pre;
	private int N;
	
	private class Node
	{
		int	 item;
		Node next;
	}
	
	public DigitAdder()
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
	
	public void reverse()
	{
		if (isEmpty())
			return;
		if (size() == 1)
			return;
		
		Node pReversed = null;
		Node curr = pre.next;
		while (curr != null)
		{
			Node oldfirst = pReversed;
			pReversed = new Node();
			pReversed.item = curr.item;
			pReversed.next = oldfirst;
			curr = curr.next;
		}
		pre.next = pReversed;
	}
	
	public int size()
	{
		return N;
	}
	
	public boolean isEmpty()
	{
		return 0 == N;
	}
	
	
	public DigitAdder add(DigitAdder another)
	{
		int c = 0;
		DigitAdder result = new DigitAdder();

		Node refdigit1 = pre.next;
		Node refdigit2 = another.pre.next;
		while(refdigit1 != null && refdigit2 != null)
		{
			int sum = refdigit1.item + refdigit2.item + c;
			c = sum / 10;
			sum = sum % 10;
			result.addFirst(sum);
			
			refdigit1 = refdigit1.next;
			refdigit2 = refdigit2.next;
		}
		
		if (refdigit1 == null)
		{
			while (refdigit2 != null)
			{
				result.addFirst(refdigit2.item);
				refdigit2 = refdigit2.next;
			}
		}
		
		if (refdigit2 == null)
		{
			while (refdigit1 != null)
			{
				result.addFirst(refdigit1.item);
				refdigit2 = refdigit2.next;
			}
		}
		result.reverse();
		return result;
	}
	
	public void printNum()
	{
		Node curr = pre.next;
		while (curr != null)
		{
			StdOut.println(curr.item);
			curr = curr.next;
		}
	}

	public static void main(String[] args)
	{
		DigitAdder adder1 = new DigitAdder();
		adder1.addFirst(6);
		adder1.addFirst(1);
		adder1.addFirst(7);
		
		DigitAdder adder2 = new DigitAdder();
		adder2.addFirst(3);
		adder2.addFirst(2);
		adder2.addFirst(9);
		adder2.addFirst(5);
		
		DigitAdder res = adder1.add(adder2);
		res.printNum();
	}
}