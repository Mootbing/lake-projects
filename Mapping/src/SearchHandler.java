import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/*
 * include the part of speech, also put the definition in the textarea and display to screen, you can also highlight and reverse lookup
 * */

public class SearchHandler {
	
	private static JSONArray Search(String ADef, int Limits, boolean IsSearchingFromDefinition){
		
		String FullURL = "";
		
		if(IsSearchingFromDefinition) 
			FullURL = "https://api.datamuse.com/words?ml=" + (ADef.replaceAll(" ", "+"));
		else
			FullURL = "https://api.datamuse.com/words?sp=" + (ADef.replaceAll(" ", "+")) + "&md=d";
	   
	   
	   try {
		   URL url = new URL(FullURL);
		   BufferedReader Text = new BufferedReader(new InputStreamReader(url.openStream()));
		   JSONParser Parser = new JSONParser(); 
		   JSONArray Json =  (JSONArray) (Parser.parse(Text));
		   
		   //System.out.println(String.format("\n\n>>Found %s Objects With The Matching Phrase \"%s\"<< \n\n", String.valueOf(Json.size()), AWord));
		   JSONArray Final = new JSONArray();
		   
		   if (Json.size() < Limits)
			   Limits = Json.size();
		   
		   for (int i = 0; i < Limits; i++)
			   Final.add(Json.get(i));
		   
		   return Final;
		
	   }catch(Exception e) {
	         e.printStackTrace();
	   }
	   return null;
	}
	
	public static ArrayList<String> GetWords(String ADef, int ALimits){
		JSONArray thing = Search(ADef, ALimits, true);
		ArrayList<String> PossibleDefinations = new ArrayList<String>();
		
		for (int i = 0; i < thing.size(); i++) 
			PossibleDefinations.add(((JSONObject)(thing.get(i))).get("word").toString());
		
		return PossibleDefinations;
	}
	
	public static ArrayList<String> GetDefs(String AWord, int ALimits){
		JSONArray thing = Search(AWord, ALimits, false);
		ArrayList<String> PossibleDefinations = new ArrayList<String>();
		for (int i = 0; i < thing.size(); i++) 
			PossibleDefinations.add(((JSONObject)(thing.get(i))).get("defs").toString());
		
		return PossibleDefinations;
	}
	
	public static String FindDefinition(String AWord, int ALimit, int Index) {
		if ((Index >= 0 && Index < ALimit)) {
			ArrayList<String> list = GetDefs(AWord, ALimit);
			ArrayList<String> listv2 = FindDefinitions(list.get(0));
			return listv2.get(Index);
		}
		return "NOTHING FOUND";
	}
	
	private static ArrayList<String> FindDefinitions (String DefinitionToSplit){
		
		ArrayList<String> Definition = new ArrayList<String>();
		
		while (DefinitionToSplit.indexOf("\\t") != -1) {
			int SplitPlace = DefinitionToSplit.indexOf("\\t");
			DefinitionToSplit = DefinitionToSplit.substring(SplitPlace + 2);
			int Split2 = DefinitionToSplit.indexOf("\\t");
			if(DefinitionToSplit.indexOf("\\t") != -1) {
				Definition.add(DefinitionToSplit.substring(0, Split2 - 4));
				DefinitionToSplit = DefinitionToSplit.substring(Split2 + 2); 
			}
		}
		
		return Definition;
	}
	
	public static void main(String[] args) {
		System.out.println(SearchHandler.FindDefinition("ring", 10, 4));
	}
}
