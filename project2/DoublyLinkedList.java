/*----------------------------------------------------------------
 *  Author:        Leesper
 *  Written:       05/15/2014
 *  Compilation:   javac DoublyLinkedList.java
 *  Execution:     java DoublyLinkedList
 *  My solution to web exercise 21 of "Algorithms 4ed"
 *  reference: http://algs4.cs.princeton.edu/13stacks/
 *----------------------------------------------------------------*/

import java.util.ListIterator;
import java.util.NoSuchElementException;

public class DoublyLinkedList<Item> implements Iterable<Item>
{
	/* this may consider as "dummy" pattern */
	private Node	pre;	// dummy node before the first
	private Node 	post;	// dummy node after the last
	private int		N;		// size
	
	private class Node 
	{
		private Item item;
		private Node prev;
		private Node next;
	}
	
	public DoublyLinkedList() { 
		pre = new Node();
		post = new Node();
		pre.next = post;
		post.prev = pre;
	}
	
	public boolean isEmpty() { return N == 0; }
	
	public int size() {	return N; }

	public void addFirst(Item item)
	{
		Node oldfirst = pre.next;
		Node node = new Node();
		node.item = item;
		pre.next = node;
		node.prev = pre;
		node.next = oldfirst;
		oldfirst.prev = node;
		++N;
	}
	
	public void addLast(Item item)
	{
		Node oldlast = post.prev;
		Node node = new Node();
		node.item = item;
		post.prev = node;
		node.next = post;
		node.prev = oldlast;
		oldlast.next = node;
		++N;
	}
	
	public Item removeFirst()
	{
		Item item = pre.next.item;
		Node newfirst = pre.next.next;
		pre.next = newfirst;
		newfirst.prev = pre;
		--N;
		return item;
	}
	
	public Item removeLast()
	{
		Item item = post.prev.item;
		Node newlast = post.prev.prev;
		post.prev = newlast;
		newlast.next = post;
		--N;
		return item;
	}
	
	public ListIterator<Item> iterator()
	{
		return new ForwardBackwardIterator();
	}
	
	private class ForwardBackwardIterator implements ListIterator<Item>
	{
		private	Node	lastAccessed;
		private Node	curr = pre.next; 	
		int				nextindex;
		
		public void add(Item item)
		{
			if (lastAccessed == null)
				throw new NullPointerException();
			Node node = new Node();
			node.item = item;
			Node before = curr.prev;
			before.next = node;
			node.prev = before;
			node.next = curr;
			curr.prev = node;
			// subsequent add/remove/set not allowed
			lastAccessed = null;
			++nextindex;
			++N;
		}
		
		public boolean hasNext() { return nextindex < size(); }
		
		public boolean hasPrevious() { return nextindex > 0; }
		
		public Item next()
		{
			if (!hasNext())
				throw new NoSuchElementException();
			Item item = curr.item;
			lastAccessed = curr;
			curr = curr.next;
			++nextindex;
			return item;
		}
		
		public int nextIndex() { return nextindex; }
		
		public Item previous()
		{
			if (!hasPrevious())
				throw new NoSuchElementException();
			curr = curr.prev;
			Item item = curr.item;
			lastAccessed = curr;
			--nextindex;
			return item;
		}
		
		public int previousIndex() { return nextindex - 1; }
		
		public void remove()
		{
			if (lastAccessed == null)
				throw new NullPointerException();
			Node before = lastAccessed.prev;
			Node after = lastAccessed.next;
			before.next = after;
			after.prev = before;
			if (curr == lastAccessed)	curr = after;
			else						--nextindex;
			lastAccessed = null;
			--N;
		}
		
		public void set(Item item) { 
			if (lastAccessed == null)
				throw new NullPointerException();
			lastAccessed.item = item;
		}
	}
	
	public static void main(String[] args)
	{
		DoublyLinkedList<String> dll = new DoublyLinkedList<String>();

		/*
		dll.addLast("My");
		dll.addLast("Computer");
		dll.addLast("Program");
		dll.addLast("Is");
		dll.addLast("Java");
		
		StdOut.println(dll.removeFirst());
		StdOut.println(dll.removeFirst());
		StdOut.println(dll.removeFirst());
		StdOut.println(dll.removeFirst());
		StdOut.println(dll.removeFirst());
		StdOut.println(dll.size());
		
		dll.addFirst("My");
		dll.addFirst("Computer");
		dll.addFirst("Program");
		dll.addFirst("Is");
		dll.addFirst("Java");
		StdOut.println(dll.size());
		
		StdOut.println(dll.removeLast());
		StdOut.println(dll.removeLast());
		StdOut.println(dll.removeLast());
		StdOut.println(dll.removeLast());
		StdOut.println(dll.removeLast());
		*/
		
		dll.addLast("My");
		dll.addLast("Computer");
		dll.addLast("Program");
		dll.addLast("Is");
		dll.addLast("Java");
		ListIterator<String> iter = dll.iterator();
		
		while (iter.hasNext())
		{
			if (iter.next().equals("My"))
				iter.add("Excellent");
		}
		
		while (iter.hasPrevious())	StdOut.println(iter.previous());
		
		while (iter.hasNext())
		{
			if (iter.next().equals("Excellent"))
				iter.set("Brilliant");;
		}
		
		while (iter.hasPrevious())	StdOut.println(iter.previous());
	}
}