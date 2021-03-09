import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

public class UI extends JFrame{
	
	private Container Pane;
	private int DefCounter = 0;
	
	//Selection
	private JButton SelectLookUp;
	private JButton SelectDefineNew;
	
	//Lookup
	private JTextField SearchBox;
	private JButton Enter;
	private JToggleButton SearchHistoryButton;
	private JLabel WordSearched;
	private JLabel DefFound;
	private JButton ButtonToSwitchDef;
	private JButton ButtonToSwitchDefPrev;
	private ArrayList<String> SearchHistory = new ArrayList<String>();
	private ArrayList<JButton> ButtonsOfHistory;
	private ArrayList<JButton> ResultsForWordAndDefButtons;
	private JComboBox<String> Dropdown = new JComboBox<String>();
	
	//Define
	private JTextField DefineWord;
	private JTextField DefineDefinition;
	private JButton SubmitWord;
	private JButton BackToSelection;
	
	
	
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
		Enter.addActionListener(ActionSearch());
		WordSearched = SetUpTextArea(WordSearched, "", 50, 200, 600, 100);
		DefFound = SetUpTextArea(DefFound, "", 50, 225, 600, 100);
		DefFound.setForeground(Color.WHITE);
		DefFound.setFont(new Font("Arial", Font.PLAIN, 10));
		WordSearched.setForeground(Color.WHITE);
		WordSearched.setFont(new Font("Arial", Font.PLAIN, 40));
		ButtonToSwitchDef = setUpButton(ButtonToSwitchDef, "Next Def", 300, 300, 100, 100);
		ButtonToSwitchDef.addActionListener(ActionSwitchDef());
		ButtonToSwitchDefPrev = setUpButton(ButtonToSwitchDefPrev, "Previous Def", 100, 300, 100, 100);
		ButtonToSwitchDefPrev.addActionListener(ActionSwitchDef());
		SearchHistoryButton = new JToggleButton("Logs");
		SearchHistoryButton.setBounds(500, 500, 50, 50);
		Pane.add(SearchHistoryButton);
		SearchHistoryButton.addItemListener(ActionSearchHistory());
		Dropdown.addItem("Word");
		Dropdown.addItem("Def");
		Dropdown.setSelectedItem("Word");
		Dropdown.setBounds(500, 100, 100, 50);
		Pane.add(Dropdown);
		
		//Define
		DefineWord = SetUpTextField(DefineWord, 0, 0, 600, 100);
		DefineDefinition = SetUpTextField(DefineDefinition, 0, 200, 600, 200);
		SubmitWord = setUpButton(SubmitWord, "Submit", 100, 500, 300, 50);
		SubmitWord.addActionListener(ActionSubmitNewWord());
		
		BackToSelection = setUpButton(BackToSelection, "<", 0, 500, 50, 50);
		BackToSelection.addActionListener(ActionGoBack());
		
		HideAll();
		
		SelectLookUp.setVisible(true);
		SelectDefineNew.setVisible(true);
		
		//SearchHandler s = new SearchHandler();
		//System.out.println(s.Search(""));
		
		setUpWindow();
		//laster last
		repaint();
	}
	
	private ActionListener ActionSwitchDef() {
		{
			   ActionListener listener = new ActionListener()
			   {
				   public void actionPerformed(ActionEvent event)
				   {
					   switch(((JButton)event.getSource()).getText()) {
						   case "Previous Def":
							   DefCounter -= 1;
							   break;
						   case "Next Def":
							   DefCounter += 1;
							   break;
					   }
					   SearchForWordOrDef();
				   }
			   };
			   return listener;
		   }
	}
	
	private ItemListener ActionSearchHistory() {
		{
			   ItemListener listener = new ItemListener()
			   {
				   public void itemStateChanged(ItemEvent event)
				   {
					   if(event.getStateChange() == ItemEvent.SELECTED) {
					   
						   int limit;
						   
						   if(SearchHistory.size() - 10 < 0) 
							   limit = 0;
						   else 
							   limit = SearchHistory.size() - 10;
							   
						   ArrayList<String> NewList = new ArrayList<String>(SearchHistory.subList(limit, SearchHistory.size()));
						   
						   ButtonsOfHistory = MakeJButtonArray(NewList, 300);
						   for(JButton btn : ButtonsOfHistory)
								btn.addActionListener(ActionRedirectWord(null));
					   }else {
						   for(JButton btn : ButtonsOfHistory) 
							   getContentPane().remove(btn);
						   repaint();
					   }
				   }
			   };
			   return listener;
		   }
	}
	
	private JTextField SetUpTextField(JTextField f, int x, int y, int width, int height) 
	{
		f = new JTextField();
		f.setBounds(x, y, width, height);
		Pane.add(f);
		return f;
	}

	private JLabel SetUpTextArea(JLabel t, String s, int x, int y, int width, int height) 
	{
		t = new JLabel(s);
		t.setBounds(x, y, width, height);
		Pane.add(t);
		return t;
	}
	
	private ActionListener ActionGoBack() {
		{
			   ActionListener listener = new ActionListener()
			   {
				   public void actionPerformed(ActionEvent event)
				   {
					   BackToSelectionMenu();
				   }
			   };
			   return listener;
		   }
	}
	
	private void BackToSelectionMenu() {
		HideAll();
		SelectLookUp.setVisible(true);
		SelectDefineNew.setVisible(true);
	}

	private ActionListener ActionSubmitNewWord() {
		{
			   ActionListener listener = new ActionListener()
			   {
				   public void actionPerformed(ActionEvent event)
				   {
					   WriteNewWordAndDef();
				   }
			   };
			   return listener;
		   }
	}

	private void WriteNewWordAndDef() {
		String AWord = DefineWord.getText();
		String ADef = DefineDefinition.getText();
		Word w = new Word(AWord, ADef);
		WriteFile wr = new WriteFile();
		wr.Write("File.txt", w);
		BackToSelectionMenu();
	}
	
	private ActionListener ActionSearch() {
		{
			   ActionListener listener = new ActionListener()
			   {
				   public void actionPerformed(ActionEvent event)
				   {
					   DefCounter = 0;
					   SearchForWordOrDef();
				   }
			   };
			   return listener;
		   }
	}

	private void SearchForWordOrDef() {
		
		if(ResultsForWordAndDefButtons != null) {
		
			for (JButton b : ResultsForWordAndDefButtons)
				   getContentPane().remove(b);
		   repaint();
		   ResultsForWordAndDefButtons = new ArrayList<JButton>();
		   
		}
		
		ArrayList<String> Result = SearchHandler.GetWords(SearchBox.getText(), 10);
		
		if (Result.isEmpty()) {
			SearchBox.setText("Nothing found");
			return;
		}
	
		ResultsForWordAndDefButtons = MakeJButtonArray(Result, 0);
		for(JButton btn : ResultsForWordAndDefButtons)
			btn.addActionListener(ActionRedirectWord(ResultsForWordAndDefButtons));
		
		if(Dropdown.getSelectedItem().equals("Word")) {
			ArrayList<String> Resultv2 = SearchHandler.FindDefinitionReturnArrayListString(SearchBox.getText(), 10);
			
			if (Resultv2.isEmpty()) {
				SearchBox.setText("Nothing found");
				return;
			}else {
				WordSearched.setText(SearchBox.getText());
				if(DefCounter >= Resultv2.size())
					DefCounter = 0;
				else if (DefCounter < 0)
					DefCounter = Resultv2.size() - 1;
				
				DefFound.setText(Resultv2.get(DefCounter));
			}
		}
		
		if(SearchHistory.contains(SearchBox.getText()))
			SearchHistory.remove(SearchBox.getText());
		SearchHistory.add(SearchBox.getText());
		
	}
	
	private ArrayList<JButton> MakeJButtonArray(ArrayList<String> Input, int OffsetY){
		ArrayList<JButton> ButtonStuff = new ArrayList<JButton>();
		int x = 50;
		int y = OffsetY + 75;
		
		for (int i = 0; i < Input.size(); i++) {
			x += 100;
			if(i % 5 == 0) {
				x = 50;
				y += 50;
			}
			JButton temp = setUpButton(null, Input.get(i), x, y, 100, 50);
			ButtonStuff.add(temp);
		}
		
		repaint();
		
		return ButtonStuff;
	}
	
	private ActionListener ActionRedirectWord(ArrayList<JButton> allbtns) {
		{
			   ActionListener listener = new ActionListener()
			   {
				   public void actionPerformed(ActionEvent event)
				   {
					   SearchBox.setText(((JButton)event.getSource()).getText()); 
					   SearchForWordOrDef();
				   }
			   };
			   return listener;
		   }
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
						   SearchHistoryButton.setVisible(true);
						   Dropdown.setVisible(true);
						   ButtonToSwitchDef.setVisible(true);
						   ButtonToSwitchDefPrev.setVisible(true);
					   }else if(((AbstractButton) event.getSource()).getText().equals("Define New")) {
						   HideAll();
						   DefineWord.setVisible(true);
						   DefineDefinition.setVisible(true);
						   SubmitWord.setVisible(true);
					   }
					   BackToSelection.setVisible(true);
				   }
			   };
			   return listener;
		   }
	}
	
	private void HideAll() {
		SelectLookUp.setVisible(false);
		SearchHistoryButton.setVisible(false);
		SelectDefineNew.setVisible(false);
		SearchBox.setVisible(false);
		Enter.setVisible(false);
		DefineWord.setVisible(false);
		DefineDefinition.setVisible(false);
		SubmitWord.setVisible(false);
		BackToSelection.setVisible(false);
		Dropdown.setVisible(false);
		ButtonToSwitchDef.setVisible(false);
		ButtonToSwitchDefPrev.setVisible(false);
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
		ui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
