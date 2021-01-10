
public class RateRecord {
	private String fromUnit = "";
	private String toUnit = "";
	private double rate = 0.0;
	
	public RateRecord(String from, String to, double r) {
		fromUnit = from;
		toUnit = to;
		rate = r;
	}
	
	public String getFromUnit() {
		return this.fromUnit;
	}
	public String getToUnit() {
		return this.toUnit;
	}
	public double getRate() {
		return this.rate;
	}
	public String toString() {
		return fromUnit + " to " + toUnit + " is " + rate; 
	}
}
