import java.awt.Font;
import java.awt.font.TextAttribute;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.time.LocalDateTime;

public class WriteWord{
	ArrayList<String> Definitions;
	String Word;
	String Extention = ".html";
	
	WriteWord(String AWord, ArrayList<String> ADefinitions){
		Word = AWord;
		Definitions = ADefinitions;
	}
	
	public Boolean WriteTheWord(boolean isBigFile) {
		
		File f;
		
		try {
			if (!isBigFile) {
				f = PromptUserSaveLocation();
			}else {
				f = new File("./BigFile.html");
			}
		
			if (f == null)
				return false;
			
			String Before = "";
			
			if (f.exists()) {
				ReadFileWorking Reader = new ReadFileWorking(f.getPath());
	
				try {
					Before = String.join("" ,Reader.getList());
					System.out.println(Before);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
			FileWriter filewriter = new FileWriter(f);
			
			filewriter.append(String.format("<br><br>" + Before + "<i>Searched at: %s", LocalDateTime.now()) + "</i>\n\n");
			
			filewriter.append("<h1>" + Word + "</h1>");
			
			for (String def : Definitions) {
				filewriter.append("<br> <b>" + def.substring(0, 1) + "</b>" + def.substring(1) + "<br> <br> ===================== <br> ");
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
		chooser.setSelectedFile(new File(Word.replaceAll(" ", "_") + Extention));
	    FileNameExtensionFilter filter = new FileNameExtensionFilter(Extention, Extention);
	    chooser.setFileFilter(filter);
	    chooser.showSaveDialog(chooser);
	    File f = chooser.getSelectedFile();
	    return f;
	}
	
	public static void main(String[] args) {
		
		WriteWord w = new WriteWord("bull", SearchHandler.FindDefinitionReturnArrayListString("bull", 10));
		w.WriteTheWord(false);
	}
}
