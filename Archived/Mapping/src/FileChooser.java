import java.io.File;
import java.nio.file.Path;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileChooser{
	
	private String Extention = "";
	private File f;

	FileChooser(String Extention)
	{
		this.Extention = Extention;
		f = ChooseFileToOpen();
	}
	
	private File ChooseFileToOpen() {
		
		JFileChooser Chooser = new JFileChooser();
	    FileNameExtensionFilter filter = new FileNameExtensionFilter(Extention, Extention);
	    Chooser.setFileFilter(filter);
	    Chooser.showOpenDialog(Chooser);
	    f = Chooser.getSelectedFile();
	    
	    if (!(f == null))
	    	return f;
	    else return null;
		
	}
	
	public Path GetPath() {
		if (f != null)
			return f.toPath();
		return Path.of("");
	}
	
	public static void main(String[] args) {
		FileChooser Ch = new FileChooser("html");
	}
	
}
