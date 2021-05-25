import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class UI extends JFrame{
	
	public JEditorPane field;

	UI(String HTML)
	{
		SetUpUI(HTML);
	}
	
	private void SetUpUI(String HTML) {
		setSize(800, 600);
		JScrollPane Pane = new JScrollPane();
		JPanel Content = new JPanel();
		try{
			field = new JEditorPane("text/html", HTML);
		}catch (Exception e){
			e.printStackTrace();
			field = new JEditorPane("text/html", "<h1>Oops. <br> Html File Failed To Parse</h1>");
		}
		field.setBounds(0, 0, 800, 600);
		Content.add(field);
		Content.setBounds(0, 0, 800, 600);
		Pane.setBounds(0, 0, 800, 600);
		Pane.add(Content); 
		add(Pane);
		setVisible(true);
	}
	
	public void GetFocus() {
		field.grabFocus();
	}
	
	public static void main(String[] args) {
		FileChooser Ch = new FileChooser("html");
		String HTML = "";
		try {
			HTML = Files.readString(Ch.GetPath(), StandardCharsets.US_ASCII);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		UI Bruh = new UI(HTML);
	}
	
}
