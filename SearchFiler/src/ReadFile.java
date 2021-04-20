import java.io.*;

import java.util.ArrayList;



public class ReadFile 
{
	
	private String fileName;
	
	ReadFile(String fileName)
	{
		this.fileName = fileName;
	}
	
	
	public ArrayList<String>getList() throws Exception
	{
		ArrayList<String>list = new ArrayList<String>();
		
		FileReader fstream = new FileReader(fileName);
		BufferedReader in = new BufferedReader(fstream);
		
		String value = ""; 
		while( value != null)
		{
		    value = in.readLine();
		    if(value != null)
		    {
		    	list.add(value);
		    }
		}
		
		in.close();
		
		return list;
	}
	
	
	
	public int[] getArrayAtPosition(int pos, ArrayList<String> list)
	{
		String line = list.get(pos);
		String numbers[] = line.split(",");
		int values[] = new int[numbers.length];
		
		for(int i = 0; i < numbers.length; i++)
			values[i] = Integer.parseInt(numbers[i]);
		
		return values;
	}
	
}
