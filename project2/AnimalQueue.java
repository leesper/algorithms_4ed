/*-----------------------------------------------------------------------------
 *  Author:        Leesper
 *  Written:       05/20/2014
 *  Compilation:   javac AnimalQueue.java
 *  Execution:     java AnimalQueue
 *  My solution to Interview Question 3.7 of "Cracking The Coding Interview"
 *---------------------------------------------------------------------------*/

import java.util.LinkedList;
import java.util.NoSuchElementException;

class Animal 
{
	public String toString()
	{
		return "I am an animal";
	}
}

class Cat extends Animal
{
	public String toString()
	{
		return "I am a cat";
	}
}

class Dog extends Animal
{
	public String toString()
	{
		return "I am a dog";
	}
}

public class AnimalQueue
{
	private int nCat;
	private int nDog;
	private LinkedList<Animal> queue = new LinkedList<Animal>();
	
	public void enqueue(Animal a)
	{
		if (null == a)
			throw new NullPointerException();
		
		queue.addLast(a);
		if (a instanceof Cat)	++nCat;
		if (a instanceof Dog)	++nDog;
	}
	
	public Animal dequeueAny()
	{
		if (isEmpty())
			throw new NoSuchElementException();
		Animal res = queue.removeFirst();
		if (res instanceof Cat)	--nCat;
		if (res instanceof Dog)	--nDog;
		return res;
	}
	
	public Cat dequeueCat()
	{
		if (isEmpty())
			throw new NoSuchElementException();
		if (isCatEmpty())
			throw new NoSuchElementException();
		Animal a = queue.removeFirst();
		while (!(a instanceof Cat))
		{
			queue.addLast(a);
			a = queue.removeFirst();
		}
		--nCat;
		Cat c = (Cat) a;
		return c;
	}
	
	public Dog dequeueDog()
	{
		if (isEmpty())
			throw new NoSuchElementException();
		if (isDogEmpty())
			throw new NoSuchElementException();
		Animal a = queue.removeFirst();
		while (!(a instanceof Dog))
		{
			queue.addLast(a);
			a = queue.removeFirst();
		}
		--nDog;
		Dog d = (Dog) a;
		return d;
	}
	
	public boolean isEmpty()
	{
		return (nCat + nDog) == 0;
	}
	
	public boolean isCatEmpty()
	{
		return nCat == 0;
	}
	
	public boolean isDogEmpty()
	{
		return nDog == 0;
	}
	
	public int size()
	{
		return nCat + nDog;
	}
	
	public static void main(String[] args)
	{
		AnimalQueue aq = new AnimalQueue();
		aq.enqueue(new Dog());
		aq.enqueue(new Cat());
		aq.enqueue(new Cat());
		aq.enqueue(new Dog());
		aq.enqueue(new Cat());
		aq.enqueue(new Cat());
		aq.enqueue(new Cat());
		aq.enqueue(new Dog());
		aq.enqueue(new Cat());
		aq.enqueue(new Cat());
		aq.enqueue(new Cat());
		aq.enqueue(new Dog());
		aq.enqueue(new Cat());
		aq.enqueue(new Dog());
		
		StdOut.printf("animal size: %d\n", aq.size());
		Cat c = aq.dequeueCat();
		StdOut.printf("animal size: %d, animal type: %s\n", aq.size(), c);
		Animal a = aq.dequeueAny();
		StdOut.printf("animal size: %d, animal type: %s\n", aq.size(), a);
		a = aq.dequeueAny();
		StdOut.printf("animal size: %d, animal type: %s\n", aq.size(), a);
	}
}