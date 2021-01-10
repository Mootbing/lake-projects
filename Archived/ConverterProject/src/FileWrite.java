import java.io.*;

public class FileWrite 
{
	private String fileName;
	
	
	public FileWrite (String fileName)
	{
		this.fileName = fileName + ".txt";
	}
	

	
	public void writeToFile(String s) throws Exception
	{
		try
	  	{
	  		//Create file 
	  		FileWriter fstream = new FileWriter(this.fileName);
	  		BufferedWriter out = new BufferedWriter(fstream);
	  		out.write(s);
	  		out.close();  //Close the output stream
	    }
		catch (Exception e) //Catch exception if any
		{
	  	   System.err.println("Error: " + e.getMessage());
	  	  
		}
	}
	
	public static void main(String[] args) 
	{
		FileWrite writer = new FileWrite("PolynomialTransactions");
		try
		{
			writer.writeToFile("Hello");
		}
		catch(Exception error)
		{
			System.out.println(error.getMessage());		
		}
		
	}
	

}// end class FileWrite