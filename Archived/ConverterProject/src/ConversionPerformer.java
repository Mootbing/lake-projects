import java.util.ArrayList;

public class ConversionPerformer extends Converter 
{
	String fileName = "";
	String unit = "";
	
	public ConversionPerformer(String unit, double from, String type, String fileName)
	{
		super(from, type);
		this.fileName = fileName;
		this.unit = unit;
	}
	
	
	public String convert()
	{
		System.out.println(this.fileName);
		ArrayList<RateRecord> records = this.readRatesForUnit(this.unit, this.fileName);
		System.out.println(records);
		double value =  super.getFrom();
		String solution = "";
		for(RateRecord record: records)
			solution += value +  " " + record.getFromUnit() +
			 " = " + value * record.getRate() + " " + record.getToUnit() + "\n";
			
		return solution;
	}

}