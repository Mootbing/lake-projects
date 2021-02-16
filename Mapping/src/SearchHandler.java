import java.util.ArrayList;

public class SearchHandler {
	
	public String Search(String AWord){
		ReadFile r = new ReadFile();
		ArrayList<Word> Items = r.Read("File.txt");
		for(Word i : Items) {
			if(i.GetText().contains(AWord)) {
				return i.GetDef();
			}else if(i.GetDef().contains(AWord)) {
				return i.GetText();
			}
		}
		return null;
	}
	
	
}
