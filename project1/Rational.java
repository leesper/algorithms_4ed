public class Rational {
	
	public Rational(int numerator, int denominator) {
		if (denominator == 0) {
			throw new IllegalArgumentException("Invalid denominator == 0");
		}
		long gcd = euclid(numerator, denominator);
		this.numerator = numerator / gcd;
		this.denominator = denominator / gcd;
	}
	
	public Rational plus(Rational b) {
		long numerator1 = this.numerator * b.denominator;
		long numerator2 = b.numerator * this.denominator;
		long numerator = numerator1 + numerator2;
		long denominator = this.denominator * b.denominator;
		
		long gcd = euclid(numerator, denominator);
		if (gcd > 1) {
			numerator /= gcd;
			denominator /= gcd;
		}
		Rational res = new Rational((int)numerator, (int)denominator);
		return res;
	}
	
	public Rational minus(Rational b) {
		long numerator1 = this.numerator * b.denominator;
		long numerator2 = b.numerator * this.denominator;
		long numerator = numerator1 - numerator2;
		long denominator = this.denominator * b.denominator;
		
		long gcd = euclid(numerator, denominator);
		if (gcd > 1) {
			numerator /= gcd;
			denominator /= gcd;
		}
		Rational res = new Rational((int)numerator, (int)denominator);
		return res;
	}
	
	public Rational times(Rational b) {
		long numerator = this.numerator * b.numerator;
		long denominator = this.denominator * b.denominator;
		long gcd = euclid(numerator, denominator);
		if (gcd > 1) {
			numerator /= gcd;
			denominator /= gcd;
		}
		Rational res = new Rational((int)numerator, (int)denominator);
		return res;
	}
	
	public Rational divides(Rational b) {
		long numerator = this.numerator * b.denominator;
		long denominator = this.denominator * b.numerator;
		long gcd = euclid(numerator, denominator);
		if (gcd > 1) {
			numerator /= gcd;
			denominator /= gcd;
		}
		Rational res = new Rational((int)numerator, (int)denominator);
		return res;
	}
	
	public String toString() {
		return Long.toString(numerator) + "/" + Long.toString(denominator);
	}
	
	public boolean equals(Rational that) {
		if (that == null) {
			return false;
		}
		if (that == this) {
			return true;
		}
		return this.numerator == that.numerator && this.denominator == that.denominator;
	}
	
	private long euclid(long num1, long num2) {
		num1 = Math.abs(num1);
		num2 = Math.abs(num2);
		if (num1 < num2) {
			long temp = num1;
			num1 = num2;
			num2 = temp;
		}
		
		if (0 == num2) {
			throw new IllegalArgumentException("Invalid num2 == 0");
		}
		
		while (num2 > 0) {
			long remainder = num1 % num2;
			num1 = num2;
			num2 = remainder;
		}
		return num1;
	}
	
	public static void main(String[] args) {
		Rational r1 = new Rational(1, 6);
		Rational r2 = new Rational(3, 6);
		Rational sum = r1.plus(r2);
		Rational diff = r1.minus(r2);
		Rational times = r1.times(r2);
		Rational divi = r1.divides(r2);
		StdOut.println(sum);
		StdOut.println(diff);
		StdOut.println(times);
		StdOut.println(divi);
		StdOut.println(r1.equals(r2));
	}
	
	private final long numerator;
	private final long denominator;
}