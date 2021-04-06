import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.nio.file.Path;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileChooser extends JFrame{
	
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
		return f.toPath();
	}
	
	public static void main(String[] args) {
		FileChooser Ch = new FileChooser("html");
	}
	
}
