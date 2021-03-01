import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

class Test
{

   public static void main(String[] args) {
	   
	   System.out.println("Query?");
	   
	   Scanner ReadInput = new Scanner(System.in);
	   String BaseURL = "https://api.datamuse.com/words?ml=";
	   String Input = ReadInput.nextLine();
	   String FullURL = BaseURL + (Input.replaceAll(" ", "+"));
	   ReadInput.close();
	   
	   try {
		   URL url = new URL(FullURL);
		   BufferedReader Text = new BufferedReader(new InputStreamReader(url.openStream()));
		   JSONParser Parser = new JSONParser(); 
		   JSONArray Json =  (JSONArray) (Parser.parse(Text));
		   
		   System.out.println(String.format("\n\n>>Found %s Objects With The Matching Phrase \"%s\"<< \n\n", String.valueOf(Json.size()), Input));
		   
		   for (Object i : Json)
			   System.out.println(i);
	   }catch(Exception e) {
	         e.printStackTrace();
	  }
   }
}