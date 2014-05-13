/*----------------------------------------------------------------
 *  Author:        Leesper
 *  Written:       05/13/2014
 *  Compilation:   javac RandomizedQueue.java
 *  Execution:     java RandomizedQueue
 *  My solution to programming assignment 2 of "Algorithms"
 *----------------------------------------------------------------*/

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> 
{
	
	private Item[] arr = (Item[]) new Object[1];
	private int N;
	
	public RandomizedQueue() {}
	
	public boolean isEmpty() { return 0 == N; }
	
	public int size() { return N; }
	
	/*
	 * Add an item
	 * Throws NullPointerException if item is null
	 */
	public void enqueue(Item item)
	{
		if (null == item) 
			throw new NullPointerException();
		
		if (N == arr.length) resize(2 * arr.length);
		
		arr[N++] = item;
	}
	
	/*
	 * Delete and return a random item
	 * Throws NoSuchElementException if empty
	 */
	public Item dequeue() 
	{
		if (isEmpty())	
			throw new NoSuchElementException();
		
		int r = StdRandom.uniform(N);
		Item random = arr[r];
		arr[r] = arr[N - 1];
		arr[N - 1] = random;
		
		Item item = arr[--N];
		arr[N] = null;
		return item;
	}
	
	/* 
	 * Return (but not delete) a random item
	 * Throws NoSuchElementException if empty
	 */
	public Item sample()
	{
		if (isEmpty())
			throw new NoSuchElementException();
		int r = StdRandom.uniform(N);
		return arr[r];
	}
	
	/*
	 * Return an independent iterator over items in random order
	 */
	public Iterator<Item> iterator() 
	{
		return new RandomizedIterator();
	}
	
	private class RandomizedIterator implements Iterator<Item>
	{
		private int curr;
		private int[] index;
		
		public RandomizedIterator() 
		{
			index = new int[N];
			for (int i = 0; i < N; i++)
			{
				index[i] = i;
			}
			StdRandom.shuffle(index);
			curr = N - 1;
		}
		
		public boolean hasNext()
		{
			return curr >= 0;
		}
		
		public Item next()
		{
			if (curr < 0)
				throw new NoSuchElementException();
			
			return arr[index[curr--]];
		}
		
		public void remove()
		{
			throw new UnsupportedOperationException();
		}
	}
	
	public static void main(String[] args)
	{
		RandomizedQueue<String> rq = new RandomizedQueue();
		rq.enqueue("Love");
		rq.enqueue("Computer");
		rq.enqueue("Program");
		rq.enqueue("Algorithm");
		rq.enqueue("Data Structure");
		
		for (String s : rq)
		{
			StdOut.println(s);
		}
		StdOut.println();
		rq.dequeue();
		
		for (String s : rq)
		{
			StdOut.println(s);
		}
		StdOut.println();
		rq.dequeue();

		for (String s : rq)
		{
			StdOut.println(s);
		}
		StdOut.println();
	}
	
	private void resize(int cap) 
	{
		Item[] temp = (Item[]) new Object[cap];
		int j = 0;
		for (int i = 0; i < N; i++)
		{
			if (arr[i] != null)
				temp[j++] = arr[i];
		}

		arr = temp;
	}
}