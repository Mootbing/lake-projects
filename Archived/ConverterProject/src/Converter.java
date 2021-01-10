import java.util.ArrayList;
import java.util.StringTokenizer;

public abstract class Converter extends Object 
{
	//data
	private double from;
	private String type;
	private int unitIndex = 0;
	
	public abstract String convert(); //all kids must perform this method
	
	
	//constructor
	public Converter(double from, String type)
	{
		setFrom(from);
		setType(type);
	}
	
	public double getFrom()
	{
		return from;
	}
	public void setFrom(double from)
	{
		this.from = from;
	}
	
	public String getType()
	{
		return type;
	}
	
	public void setType(String t)
	{
		type = t;
	}
	

	
	public ArrayList<RateRecord> readRatesForUnit(String unit, String fileName)
	{
		try
		{
			ReadFile reader = new ReadFile(fileName);
			ArrayList<String> rates = reader.getList();
			ArrayList<RateRecord> records = new ArrayList<RateRecord>();
			
			String correctRecord = "";
			
			for(int i = 0; i < rates.size(); i++) {
				correctRecord = rates.get(i);
				int colonIndex = correctRecord.indexOf(":");
				String currentUnit = correctRecord.substring(0, colonIndex);
				if(currentUnit.equals(unit)) {
					break;
				}
			}
			
			if(rates.isEmpty())
				return null;
			else
			{
				String testRecord = correctRecord;
				
				StringTokenizer t3 = new StringTokenizer(testRecord, ":");
				
				String from = "", to = "";
				double rate;
				
				from = t3.nextToken();
				testRecord = testRecord.substring(testRecord.indexOf(":") + 1, testRecord.length());
				
				StringTokenizer t2 = new StringTokenizer(testRecord, ",");

				while(t2.hasMoreTokens())
				{
					String token = t2.nextToken();
					to = token.substring(0, token.indexOf(" "));
					rate = Double.parseDouble(token.substring(token.indexOf(" ")));
					RateRecord record = new RateRecord(from, to, rate);
					records.add(record);
				}
				return records;
				
			}
		}
		catch(Exception error)
		{
			
			return null;
		}
		
	}
	

}