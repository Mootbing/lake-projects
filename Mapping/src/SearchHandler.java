import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class SearchHandler {
	
	public static JSONArray Search(String ADef, int Limits){
		System.out.println("Query?");
		
	   String BaseURL = "https://api.datamuse.com/words?ml=";
	   String FullURL = BaseURL + (ADef.replaceAll(" ", "+"));
	   
	   try {
		   URL url = new URL(FullURL);
		   BufferedReader Text = new BufferedReader(new InputStreamReader(url.openStream()));
		   JSONParser Parser = new JSONParser(); 
		   JSONArray Json =  (JSONArray) (Parser.parse(Text));
		   
		   //System.out.println(String.format("\n\n>>Found %s Objects With The Matching Phrase \"%s\"<< \n\n", String.valueOf(Json.size()), AWord));
		   JSONArray Final = new JSONArray();
		   for (int i = 0; i < Limits; i++)
			   Final.add(Json.get(i));
		   
		   return Final;
		
	   }catch(Exception e) {
	         e.printStackTrace();
	   }
	   return null;
	}
	
	public static ArrayList<String> GetWords(String ADef, int ALimits){
		JSONArray thing = Search(ADef, ALimits);
		ArrayList<String> PossibleDefinations = new ArrayList<String>();
		
		for (int i = 0; i < thing.size(); i++) 
			PossibleDefinations.add(((JSONObject)(thing.get(i))).get("word").toString());
		
		return PossibleDefinations;
	}
	
	public static void main(String[] args) {
		System.out.println(SearchHandler.GetWords("ringing in the ears", 10));
	}
}
