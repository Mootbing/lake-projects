import java.util.ArrayList;

public class Rational {
	
	//hw 12/1: do:
	//What are the methods?
	//simplify - reduce the fraction (done)
	//add (done)
	//subtract (done)
	//multiply (done)
	//reciprocal (done)
	//divide (done)
	//equality (done)
	//avoid a zero denominator (done)
	//display - print (done)
	//Integer numerator and denominator (done)
	//getters and setters (done) 
	//Rational is a fraction (done)
	//expoent (done)
	//turn into decimal (done)
	
	private int numerator, denominator;
	
	public Rational (int numerator, int denominator) {
		try {
			setNumerator(numerator);
			setDenominator(denominator);
		}catch(Exception e){
			if(ValidateError(e).equals("Denominator is zero! Update refused!"))
				System.out.println("Instance not created! Bad denominator/numerator!");
		}
	}
	
	private String ValidateError(Exception e)
	{
		return e.getMessage();
	}
	
	private Rational ValidateRational(Rational r) {
		if(r.getDenominator() == 0)
			return null;
		return r;
	}
	
	public int getNeumerator() 
	{
		return numerator;
	}
	
	public int getDenominator() 
	{
		return denominator;
	}
	
	public void setDenominator(int d) throws Exception 
	{
		if(d == 0)
			throw new Exception("Denominator is zero! Update refused!");
		denominator = d;
	}
	
	public void setNumerator(int n) 
	{
		numerator = n;
	}
	
	public double toDecimal() 
	{
		return (double)numerator / (double)denominator;
	}
	
	public String toString() 
	{
		/*if(this.getDenominator() == 1)
			return String.valueOf(numerator);*/ //this is causing issues 5/1 -> 5 
		if(this.getNeumerator() == 0)
			return "0";
		return numerator + " / " + denominator;
	}
	
	public Rational simplify() 
	{
		//this is self reference
		//find the highest common factor in both numerator and denominator
		//divide each by the number
		
		int gcd = gcd(numerator, denominator);
		try {
			this.setDenominator(denominator/gcd);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.setNumerator(numerator/gcd);
		
		return this;
	}
	
	public int gcd(int a, int b) {
		if (b == 0)
			return a;
		else if (a == 0)
			return b;
		else 
			return gcd(b, a%b);
	}
	
	public int lcd(Rational r) 
	{
		int gcd = gcd(this.getNeumerator(), this.getDenominator());
		
		return gcd * (this.denominator/gcd) * (r.denominator/gcd);
	}
	
	public Rational Reciprocal() 
	{
		if (this.getNeumerator() != 0) {
			Rational answer = new Rational (this.getDenominator(), this.getNeumerator());
			return answer.simplify();
		}
		return null;
	}
	
	public Rational add(Rational r) 
	{
		if (ValidateRational(r) != null && ValidateRational(this) != null) {
			int lcd = this.lcd(r);	
	
			int n1 = this.getNeumerator() * (lcd/this.denominator);
			int n2 = r.getNeumerator() * (lcd/r.denominator);
			
			Rational sum = new Rational (n1+n2, lcd);
			
			return sum.simplify();
		}
		return null;
	}
	
	public Rational subtract(Rational r) 
	{
		if (ValidateRational(r) != null && ValidateRational(this) != null) {
			int lcd = this.lcd(r);	
	
			int n1 = this.getNeumerator() * (lcd/this.denominator);
			int n2 = r.getNeumerator() * (lcd/r.denominator);
			
			Rational sum = new Rational (n1-n2, lcd);
			
			return sum.simplify();
		}
		return null;
	}
	
	public Rational multiply(Rational r) 
	{
		if (ValidateRational(r) != null && ValidateRational(this) != null) {
			int lcd = this.lcd(r);	
	
			int n1 = this.getNeumerator();
			int n2 = r.getNeumerator();
			int d1 = this.getDenominator();
			int d2 = r.getDenominator();
			
			
			Rational multiple = new Rational (n1*n2, d1*d2);
			
			return multiple.simplify();
		}
		return null;
	}
	
	public Rational divide(Rational r) 
	{
		Rational flippedR = r.Reciprocal();
		return this.multiply(flippedR).simplify();
	}
	
	public Rational expoential(double expoent) 
	{
		double NumeratorPowered = Math.pow(this.getNeumerator(), expoent);
		double DenominatorPowered = Math.pow(this.getDenominator(), expoent);
		Rational powered = new Rational((int)NumeratorPowered, (int)DenominatorPowered);
		return powered.simplify();
	}
	
	public float expoentialv2(double expoent) 
	{
		double NumeratorPowered = Math.pow(this.getNeumerator(), expoent);
		double DenominatorPowered = Math.pow(this.getDenominator(), expoent);
		return ((float) (NumeratorPowered/DenominatorPowered));
	}
	
	public Boolean equal(Rational r) {
		Rational simpR = r.simplify();
		Rational simpT = this.simplify();
		if((simpR.getDenominator() == simpT.getDenominator()) && (simpR.getNeumerator() == simpT.getNeumerator()))
			return true;
		return false;
	}
	
	public static void main(String[] args) {
		Calculator m = new Calculator();
		System.out.println(m.ProcessCurrentStringWithSymbols("1/2+-1898924389/-3/1/4"));
	}

}
