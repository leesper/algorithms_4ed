/*
 * Solutions for exercises: 1.2.13 and 1.2.14
 * */
public class Transaction implements Comparable<Transaction>
{
	public Transaction(String who, Date when, double amount) {
		this.who = who;
		this.when = when;
		this.amount = amount;
	}
	
	public Transaction(String trans) {
		String[] fields = trans.split("-");
		if (fields.length != 3) {
			throw new IllegalArgumentException("Invalid transaction");
		}
		who = fields[0];
		when = new Date(fields[2]);
		amount = Double.parseDouble(fields[3]);
	}
	
	public String who() {
		return who;
	}
	
	public Date when() {
		return when;
	}
	
	public double amount() {
		return amount;
	}
	
	public String toString() {
		return who + "-" + when.toString() + Double.toString(amount);
	}
	
	public boolean equals(Object that) {
		if (that == this) return true;
		if (that == null) return false;
		if (this.getClass() != that.getClass()) return false;
		Transaction trans = (Transaction)that;
		return (this.who == trans.who) && this.when.equals(trans) && this.amount == trans.amount;
	}
	
	public int	compareTo(Transaction that) {
		if (this.amount < that.amount) return -1;
		if (this.amount > that.amount) return 1;
		return 0;
	}
	
	public int hashCode() {
		return when.hashCode();
	}
	
	private String who;
	private Date when;
	private double amount;
}