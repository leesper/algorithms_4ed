import java.util.Stack;
import java.util.NoSuchElementException;

public class QueueWithTwoStacks<Item>
{
	private Stack<Item> inStack;
	private Stack<Item> outStack;
	
	public QueueWithTwoStacks()
	{
		inStack = new Stack<Item>();
		outStack = new Stack<Item>();
	}
	
	public void enqueue(Item item) { inStack.push(item); }
	
	public Item dequeue()
	{
		if (outStack.isEmpty())
		{
			if (!inStack.isEmpty())
			{
				while (!inStack.isEmpty())
				{
					outStack.push(inStack.pop());
				}
			}
			else
			{
				throw new NoSuchElementException();
			}
		}
		
		Item item;
		if (!outStack.isEmpty())	item = outStack.pop();
		else						throw new NoSuchElementException();
		
		return item;
	}
	
	public boolean isEmpty() { return inStack.isEmpty() && outStack.isEmpty(); }
	
	public int size() { return inStack.size() + outStack.size(); }
	
	public static void main(String[] args)
	{
		QueueWithTwoStacks<String> qwts = new QueueWithTwoStacks<String>();
		qwts.enqueue("My");
		qwts.enqueue("Computer");
		qwts.enqueue("Program");
		qwts.enqueue("Is");
		qwts.enqueue("Java");
		
		while (!qwts.isEmpty())
		{
			StdOut.println(qwts.dequeue());
		}
	}
}