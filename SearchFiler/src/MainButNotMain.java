import java.io.File;

import javax.swing.JFrame;
import javax.swing.JTextField;

public class MainButNotMain extends JFrame{
	
	File f;
	JTextField TxtArea;
	
	MainButNotMain()
	{
		SetUpUI();
	}
	
	private void SetUpUI() 
	{
		TxtArea = SetUpPlaceholderTextField(TxtArea, 0, 0, 800, 600);
		
		setUpWindow();
	}
	
	private JTextField SetUpPlaceholderTextField(JTextField f, int x, int y, int width, int height) 
	{
		f = new JTextField();
		f.setBounds(x, y, width, height);
		getContentPane().add(f);
		return f;
	}
	
	private void setUpWindow()
	{
		//last
		setSize(800,600);
		setTitle("Something");
		setVisible(true);
		setResizable(false);
	}
	
	public void setFile(File file) 
	{
		
		ReadFile Reader = new ReadFile(file.getAbsolutePath());
		
		try {
			TxtArea.setText(Reader.getList().toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		setUpWindow();
		
		this.f = file;
		System.out.println(file.getAbsolutePath());
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//JFileExplorer m = new JFileExplorer();
		MainButNotMain m = new MainButNotMain();
		//do wtv with the file
	}

}
