import java.io.File;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.accessibility.Accessible;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.time.LocalDateTime;

public class WriteWord{
	ArrayList<String> Definitions;
	String Word;
	
	WriteWord(String AWord, ArrayList<String> ADefinitions){
		Word = AWord;
		Definitions = ADefinitions;
	}
	
	public Boolean WriteTheWord() {
		
		try {
			File f = PromptUserSaveLocation();
		
			if (f == null)
				return false;
			
			FileWriter filewriter = new FileWriter(f);
			
			filewriter.append(String.format("Searched at: %s", LocalDateTime.now()) + "\n\n");
			
			filewriter.append("" + Word + "\n");
			
			for (String def : Definitions) {
				filewriter.append("\n" + def);
			}
			
			filewriter.close();
			
			System.out.println("Done!");
			
			return true;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	
	private File PromptUserSaveLocation(){
		JFileChooser chooser = new JFileChooser();
	    FileNameExtensionFilter filter = new FileNameExtensionFilter("rtf","rtf");
	    chooser.setFileFilter(filter);
	    chooser.showSaveDialog(chooser);
	    File f = chooser.getSelectedFile();
	    return f;
	}
	
	public static void main(String[] args) {
		
		WriteWord w = new WriteWord("bull", SearchHandler.FindDefinitionReturnArrayListString("bull", 10));
		w.WriteTheWord();
	}
}
