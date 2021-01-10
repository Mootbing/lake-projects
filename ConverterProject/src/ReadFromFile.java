import java.util.ArrayList;

public class ReadFromFile {
	
	private double rate;
	private String from, to;
	
	public ArrayList<RateRecord> readRates(String filename, String unit)
	{
		ArrayList<RateRecord> rrecords = new ArrayList<RateRecord>();
		try
		{
			ReadFile reader = new ReadFile(filename);
			ArrayList<String> rates = reader.getList();
			String record = "";
			boolean ifFound = false;
			for(int i = 0; i < rates.size(); i++) {
				 record = rates.get(i);
				 String fromUnit = record.substring(0, record.indexOf(":"));
				 if(fromUnit.equals(unit)) {
					 ifFound = true;
					 break;
				 }
			}
			if(!ifFound){
				return rrecords;
			}
			ArrayList<String> nameToElement = new ArrayList<String>();
			String restOfIt = record.substring(record.indexOf(":") + 1); 
			while(true){
				try {
					nameToElement.add(restOfIt.substring(0, restOfIt.indexOf(",")));
					restOfIt = restOfIt.substring(restOfIt.indexOf(",") + 1);
				}
				catch(StringIndexOutOfBoundsException e)
				{
					break;
				}
			}
			ArrayList<String> element = new ArrayList<String>();
			ArrayList<String> convertRate = new ArrayList<String>();
			for(int i = 0; i < nameToElement.size(); i++) {
				element.add(nameToElement.get(i).toString().substring(0, nameToElement.get(i).toString().indexOf(" ")));
				convertRate.add(nameToElement.get(i).toString().substring(nameToElement.get(i).toString().indexOf(" ") + 1));
				RateRecord recordData = new RateRecord(unit, element.get(i), Double.parseDouble(convertRate.get(i)));
				rrecords.add(recordData);
			}
			
			return rrecords;
			
			
		}
		catch(Exception error)
		{
			System.out.println(error);
			return rrecords;
		}
		
	}
	
	public double getRate() {
		return this.rate;
	}
	
	public String getFrom() {
		return this.from;
	}
	
	public String getTo() {
		return this.to;
	}
	
	public String toString() {
		return "Rate Stored: " + this.rate + "\n From (measurement) " + this.from + "\n To (measurement) " + this.to;
	}
	
}
