import java.util.TreeMap;

public class Dictionary {

	ReadFile reader = new ReadFile();
	private TreeMap<String, Word>[] Dictionary = reader.getDictionary();
	
	public Dictionary() {
		
	}
	
}

/*
 * put new word into dictionary
 * modify word and meaning
 */