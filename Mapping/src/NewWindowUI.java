import java.awt.Color;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class NewWindowUI extends JFrame{
	
	Path p;
	
	NewWindowUI(Path IPath){
		this.p = IPath;
		SetUpUI();
	}
	
	private void SetUpUI() {
		setSize(400, 300);
		JScrollPane Pane = new JScrollPane();
		JPanel Content = new JPanel();
		
		JEditorPane field = new JEditorPane("text/html", "<h1>Oops. <br> Html File Failed To Parse</h1>");
		
		String HTML = "";
		
		try {
			HTML = Files.readString(this.p, StandardCharsets.US_ASCII);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try{
			field = new JEditorPane("text/html", HTML);
		}catch (Exception e){
			e.printStackTrace();
		}
		
		field.setBounds(0, 0, 400, 300);
		setVisible(true);
		
		add(new JScrollPane(field));
		
		repaint();
	}
}
