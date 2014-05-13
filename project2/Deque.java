/*----------------------------------------------------------------
 *  Author:        Leesper
 *  Written:       05/13/2014
 *  Compilation:   javac Deque.java
 *  Execution:     java Deque
 *  My solution to programming assignment 2 of "Algorithms"
 *----------------------------------------------------------------*/

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item>
{
	private Node first;	// first node in list
	private Node last;	// last node in list
	private int	 N;		// size of deque
	
	private class Node 
	{
		Item item;
		Node prev;
		Node next;
	}

	/**
	 *	Initialize an empty Deque
	 */
	public Deque() {}           
	
	public boolean isEmpty() { return 0 == N; }                 
	
	public int size() { return N; }                       
	
	/**
	 * 	Add item to the front of deque
	 */
	public void addFirst(Item item) 
	{
		if (item == null) 
			throw new NullPointerException();
		
		Node oldfirst = first;
		first = new Node();
		first.item = item;
		first.next = oldfirst;
		if (isEmpty()) 	{ last = first; }
		else			{ oldfirst.prev = first; }
		N++;
	}   
	
	/*
	 * Add item to the tail of deque
	 */
	public void addLast(Item item) {
		if (item == null)
			throw new NullPointerException();
		
		Node oldlast = last;
		last = new Node();
		last.item = item;
		last.prev = oldlast;
		if (isEmpty()) 	{ first = last; }
		else			{ oldlast.next = last; }
		N++;
	}           
	
	/*
	 * Remove item from the front
	 */
	public Item removeFirst() 
	{
		if (isEmpty())
			throw new NoSuchElementException();
		
		Item item = first.item;
		first = first.next;
		N--;
		if (isEmpty())	{ last = first; /* null */}
		else			{ first.prev = null; }
		return item;
	}                
	
	/*
	 * Remove item from the tail
	 */
	public Item removeLast() 
	{
		if (isEmpty())
			throw new NoSuchElementException();

		Item item = last.item;
		last = last.prev;
		N--;
		if (isEmpty())	{ first = last; /* null */}
		else			{ last.next = null; }
		return item;
	}        
	
	public Iterator<Item> iterator() {
		return new ListIterator();
	}
	
	private class ListIterator implements Iterator<Item>
	{
		private Node curr = first;
		
		public boolean hasNext()
		{
			return curr != null;
		}
		
		public Item	next()
		{
			if (null == curr)
				throw new NoSuchElementException();

			Item item = curr.item;
			curr = curr.next;
			return item;
		}
		
		public void	remove() { throw new UnsupportedOperationException(); }
	}
	
	public static void main(String[] args) {
		Deque<String> dq = new Deque<String>();
		StdOut.println(dq.isEmpty());
		StdOut.println(dq.size());
		
		dq.addLast("Love");
		dq.addLast("Computer");
		dq.addLast("Program");
		StdOut.println(dq.size());
		StdOut.println(dq.removeFirst());
		StdOut.println(dq.removeFirst());
		StdOut.println(dq.removeFirst());
		
		dq.addFirst("Love");
		dq.addFirst("Computer");
		dq.addFirst("Program");
		StdOut.println(dq.removeLast());
		StdOut.println(dq.removeLast());
		StdOut.println(dq.removeLast());
		StdOut.println(dq.size());
		
	}
}