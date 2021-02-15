import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class UI extends JFrame{
	
	private Container Pane;
	
	//Selection
	private JButton SelectLookUp;
	private JButton SelectDefineNew;
	
	//Lookup
	private JTextField SearchBox;
	private JButton Enter;
	
	//Define
	private JTextField DefineWord;
	private JTextField DefineDefinition;
	private JButton SubmitWord;
	
	public UI(){
		createrUserInterface();
	}
	
	private void setUpContentPane()
	{
		Pane = getContentPane();
		Pane.setLayout(null);
		Pane.setBackground(Color.DARK_GRAY);
	}
	
	private void createrUserInterface() {
		setUpContentPane();
		
		//Selection
		SelectLookUp = setUpButton(SelectLookUp, "Look Up", 150, 200, 100, 100);
		SelectLookUp.addActionListener(ActionSelect());
		
		SelectDefineNew = setUpButton(SelectDefineNew, "Define New", 350, 200, 100, 100);
		SelectDefineNew.addActionListener(ActionSelect());
		
		//LookUp
		SearchBox = SetUpTextField(SearchBox, 0, 0, 500, 100);
		Enter = setUpButton(Enter, "Search", 500, 0, 100, 100);
		
		//Define
		DefineWord = SetUpTextField(DefineWord, 0, 0, 600, 100);
		DefineDefinition = SetUpTextField(DefineDefinition, 0, 200, 600, 200);
		SubmitWord = setUpButton(SubmitWord, "Submit", 100, 500, 300, 50);
		
		HideAll();
		
		SelectLookUp.setVisible(true);
		SelectDefineNew.setVisible(true);
		
		setUpWindow();
		//laster last
		repaint();
	}
	
	private JTextField SetUpTextField(JTextField f, int x, int y, int width, int height) 
	{
		f = new JTextField();
		f.setBounds(x, y, width, height);
		Pane.add(f);
		return f;
	}
	
	private ActionListener ActionSelect() {
		{
			   ActionListener listener = new ActionListener()
			   {
				   public void actionPerformed(ActionEvent event)
				   {
					   if(((AbstractButton) event.getSource()).getText().equals("Look Up")) {
						   HideAll();
						   SearchBox.setVisible(true);
						   Enter.setVisible(true);
					   }else if(((AbstractButton) event.getSource()).getText().equals("Define New")) {
						   HideAll();
						   DefineWord.setVisible(true);
						   DefineDefinition.setVisible(true);
						   SubmitWord.setVisible(true);
					   }
				   }
			   };
			   return listener;
		   }
	}
	
	private void HideAll() {
		SelectLookUp.setVisible(false);
		SelectDefineNew.setVisible(false);
		SearchBox.setVisible(false);
		Enter.setVisible(false);
		DefineWord.setVisible(false);
		DefineDefinition.setVisible(false);
		SubmitWord.setVisible(false);
	}
	
	private JButton setUpButton(JButton button, String name, int x, int y, int width, int height)
	{
		button = new JButton(name);
		button.setFont(new Font("Arial Black", Font.BOLD, 9));
		button.setBounds(x, y, width, height);
		Pane.add(button);
		return button;
	}
	
	private void setUpWindow()
	{
		//last
		setSize(600,600);
		setTitle("Dictionary");
		setVisible(true);
		setResizable(false);
	}
	
	public static void main(String[] args) 
	{
		UI ui = new UI();
		//UI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
