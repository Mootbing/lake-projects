import java.util.ArrayList;

import javax.swing.JButton;

public class FileFunctions {
		
	private static ReadFile read = new ReadFile("units.txt");
	
	public static String[] loadUIArrays(String type) {
		
		try {
			ArrayList<String> units = read.getList();
			String startTag = "<" + type + ">";
			String stopTag = "</" + type + ">";
			int start = 0;
			int stop = 0;
			for(int i = 0; i < units.size(); i++) {
				if(units.get(i).toString().equals(startTag)){ 
					start = i;
				}
				if(units.get(i).toString().equals(stopTag)) {
					stop = i;
				}
			}
			String[] unitArray = new String[stop - start];
			ArrayList<String> unitSublist = new ArrayList<String>(units.subList(start + 1, stop));
			for(int i = 0; i < unitArray.length - 1; i++) {
				unitArray[i] = unitSublist.get(i);
			}
			unitArray[unitArray.length - 1] = type.toString();
			return unitArray;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
