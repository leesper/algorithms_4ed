public class Parentheses
{
	public static void main(String[] args)
	{
		Stack<String> pthStack = new Stack<String>();
		while (!StdIn.isEmpty())
		{
			String pth = StdIn.readString();
			if (pth.equals("{") || pth.equals("[") || pth.equals("("))
			{
				pthStack.push(pth);
			}
			else if (pth.equals("}") || pth.equals("]") || pth.equals(")"))
			{
				if (pthStack.isEmpty())
				{
					StdOut.println("false");
					return;
				}
				
				if (match(pthStack.peek(), pth))
				{
					pthStack.pop();
				}
				else
				{
					StdOut.println("false");
					return;
				}
			}
		}
		if (pthStack.isEmpty())
		{
			StdOut.println("true");
		}
		else
		{
			StdOut.println("false");
		}
	}
	
	private static boolean match(String left, String right)
	{
		if (left.equals("(") && right.equals(")"))
			return true;
		
		if (left.equals("[") && right.equals("]"))
			return true;
		
		if (left.equals("{") && right.equals("}"))
			return true;
		
		return false;
	}
}