import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileSystemView;

public class UI extends JFrame{
	
	private LineBorder border = new LineBorder(Color.black, 0);
	private LineBorder ButtonBorder = new LineBorder(Color.decode("#6C5B7B"), 3);
	
	private Container ContentPane;
	
	private JTextField LDirectoryPath;
	private PlaceholderTextField RSearchBar; //put greyed out text (placeholderText) "Search"
	private JTextField BFinalSelctionBar;
	
	private JButton BSelect;
	private JButton BCancel;
	private JButton LBack;
	private ArrayList<String> History = new ArrayList<String>();
	
	private JComboBox RSortResults;
	
	private FileFilter LookupFilter = FileFilter.None;
	
	private JScrollPane LDirectoryShower;
	private JPanel LFilesPanel, RSearchPanel, BSelectPanel, LAddDirectoryButtonsPanel;
	
	private Container ContentPaneOfSelf;
	
	UI(){
		SetUpUI();
		RefreshFolder("C:", LookupFilter);
	}
	
	public ArrayList<File> SortFiles(ArrayList<File> Input, boolean IsAcending){
		
		ArrayList<String> FileNames = new ArrayList<String>();
		
		for (File file : Input) {
			FileNames.add(file.getName());
		}
		
		if (IsAcending)
			Collections.sort(FileNames);
		else
			Collections.reverse(FileNames);
		
		return Input;
	}
	
	private void RefreshFolder(String Dir, FileFilter Filter) {
		
		if (!History.contains(Dir)) //keep outside so the bototm one can work
			History.add(Dir);
		
		try {
			
			LAddDirectoryButtonsPanel.removeAll();
			
			ArrayList<File> Files = Search.search(Dir);
			
			switch(Filter) {
			case AlphabeticalOrder_Acending:
				Collections.sort(Files);
				break;
			case AlphabeticalOrder_Decending:
				Collections.reverse(Files);
				break;
					
			}
			
			int x = 0;
			int y = 0;
			int Width = 100;
			int Height = 100;
			
			for (File file : Files) {
				
				if ((Filter == FileFilter.Contains && !(file.getName().contains(Filter.GetFilterString()))) && Filter.GetFilterString().replaceAll(" ", "") != "")
					continue;
				
				if (x >= Math.floor(600/Width)) {
					x = 0;
					y++;
				}
				JButton Clicker = setUpButton((JComponent) ContentPaneOfSelf, new JButton(), file.getName(), Width * x, Height * y, Width, Height);
				Clicker.setBackground(Color.decode("#0c819c"));
				Clicker.setBorder(new LineBorder(Color.decode("#F67280"), 3));
				Clicker.addActionListener(ActionClickedFile(file));
				
				FileSystemView view = FileSystemView.getFileSystemView();
			   Clicker.setIcon(view.getSystemIcon(file));
				
				LAddDirectoryButtonsPanel.add(Clicker);
				x++;
			}
			
			LDirectoryPath.setText(Dir);
			
			LAddDirectoryButtonsPanel.repaint();
			
			System.out.println(History);
			
			LBack.setVisible(History.size() > 1);
			
		} catch (Exception e) {
			GoBackHistory(1);
			DisplayErrorMessage("Please type in a valid path dude...", "Ummm");
		}
		
	}
	
	private void DisplayErrorMessage(String message, String title) //totally didnt steal this from max's code shhhhh
    {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);
    }
	
	private ActionListener ActionClickedFile(File f) {
        ActionListener action = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(f.isDirectory()) {
                	RefreshFolder(f.getAbsolutePath(), LookupFilter); //later change the none
                }
            }
        };
        return action;
    }
	
	private void GoBackHistory(int Decrement) {
		if(!History.isEmpty()) {
        	History.remove(History.size() - Decrement);
        	RefreshFolder(History.get(History.size() - Decrement), LookupFilter);
        }
	}
	
	private ActionListener ActionHistory() {
        ActionListener action = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	GoBackHistory(1);
            }
        };
        return action;
    }
	
	private void SetUpUI() {
		
		this.ContentPaneOfSelf = getContentPane();
		this.ContentPaneOfSelf.setLayout(null);
		
		//Left
		LFilesPanel = setUpPanel(ContentPaneOfSelf, LFilesPanel, 0, 0, 600, 500);
		LFilesPanel.setBackground(Color.decode("#334553"));
		
		LDirectoryPath = SetUpTextField(LFilesPanel, LDirectoryPath, 50, 0, 550, 50);
		LDirectoryPath.setBackground(Color.decode("#6C5B7B"));
		LDirectoryPath.setForeground(Color.white);
		LDirectoryPath.addKeyListener(DirectorySearchListener());
		
		LDirectoryShower = SetUpScrollPane(LFilesPanel, LDirectoryShower, 0, 50, 600, 550);
		LDirectoryShower.setBackground(Color.decode("#334553"));
		
		LBack = setUpButton(LFilesPanel, LBack, "<", 0, 0, 50, 50);
		LBack.addActionListener(ActionHistory());
		LBack.setVisible(false);
		
		LAddDirectoryButtonsPanel = setUpPanel(LDirectoryShower, LAddDirectoryButtonsPanel, 0, 0, 600, 500);
		LAddDirectoryButtonsPanel.setBackground(Color.decode("#334553"));
		
		LDirectoryShower.add(LAddDirectoryButtonsPanel);
		
		//Right
		RSearchPanel = setUpPanel(ContentPaneOfSelf, RSearchPanel, 600, 0, 200, 500);
		RSearchPanel.setBackground(Color.decode("#355C7D"));
		
		RSearchBar = SetUpPlaceholderTextField(RSearchPanel, RSearchBar, 0, 0, 200, 50);
		RSearchBar.setPlaceholder("Search");
		RSearchBar.setBackground(Color.decode("#0c819c"));
		RSearchBar.addKeyListener(DirectoryFilterListener());
		
		ArrayList<String> Filtr = new ArrayList<String>();
		
		for (FileFilter Value : FileFilter.values()) {
			Filtr.add(Value.toString());
		}
		
		String[] SelectorOptions = (String[]) (Filtr.toArray(new String[Filtr.size()]));
		RSortResults = SetUpComboBox(RSearchPanel, SelectorOptions, RSortResults, 0, 50, 200, 25);
		RSortResults.setBackground(Color.white);
		RSortResults.addActionListener(ActionSelectedOptionsChanged());
		
		//Bottom
		BSelectPanel = setUpPanel(ContentPaneOfSelf, BSelectPanel, 0, 500, 800, 100);
		BSelectPanel.setBackground(Color.decode("#F67280"));
		
		LFilesPanel.setLayout(null);
		RSearchPanel.setLayout(null);
		BSelectPanel.setLayout(null);
		
		BSelect = setUpButton(BSelectPanel, BSelect, "Select", 500, 7, 100, 50); //450, 450, 100, 50
		BCancel = setUpButton(BSelectPanel, BCancel, "Cancel", 650, 7, 100, 50); //600, 450, 100, 50
		
		//last
		setUpWindow();
		repaint();
		setBackground(Color.DARK_GRAY);
		LFilesPanel.repaint();
		RSearchPanel.repaint();
		BSelectPanel.repaint();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private ActionListener ActionSelectedOptionsChanged() {
        ActionListener action = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	LookupFilter = FileFilter.valueOf(RSortResults.getSelectedItem().toString());
            	RefreshFolder(LDirectoryPath.getText(), LookupFilter);
            	System.out.println(LookupFilter);
            }
        };
        return action;
    }
	
	KeyListener DirectoryFilterListener()
    {
            KeyListener listener = new KeyAdapter()
            {
                public void keyPressed(KeyEvent event)
                {
                	LookupFilter.SetFilterString(RSearchBar.getText() + event.getKeyChar());
                	RefreshFolder(LDirectoryPath.getText(), LookupFilter);
                	System.out.println(LookupFilter.GetFilterString());
                }
            };
            return listener;
    }
	
	KeyListener DirectorySearchListener()
    {
            KeyListener listener = new KeyAdapter()
            {
                public void keyPressed(KeyEvent event)
                {
                    if (event.getKeyCode() == KeyEvent.VK_ENTER)
                    	RefreshFolder(LDirectoryPath.getText(), LookupFilter);
                }
            };
            return listener;
    }
	
	private JComboBox SetUpComboBox(JComponent C, String[] ArrayOfChoices, JComboBox f, int x, int y, int width, int height) {
		f = new JComboBox(ArrayOfChoices);
		f.setBounds(x, y, width, height);
		f.setBorder(border);
		C.add(f);
		return f;
	}
	
	private JScrollPane SetUpScrollPane(JComponent C, JScrollPane f, int x, int y, int width, int height) {
		f = new JScrollPane();
		f.setBounds(x, y, width, height);
		f.setBorder(border);
		C.add(f);
		return f;
	}
	
	private PlaceholderTextField SetUpPlaceholderTextField(JComponent C, PlaceholderTextField f, int x, int y, int width, int height) 
	{
		f = new PlaceholderTextField();
		f.setBounds(x, y, width, height);
		f.setBorder(border);
		C.add(f);
		return f;
	}
	
	private JTextField SetUpTextField(JComponent C, JTextField f, int x, int y, int width, int height) 
	{
		f = new JTextField();
		f.setBounds(x, y, width, height);
		f.setBorder(border);
		C.add(f);
		return f;
	}

	private JButton setUpButton(JComponent C, JButton button, String name, int x, int y, int width, int height)
	{
		button = new JButton(name);
		button.setFont(new Font("Arial Black", Font.BOLD, 9));
		button.setBounds(x, y, width, height);
		button.setBorder(ButtonBorder);
		button.setForeground(Color.white);
		button.setBackground(Color.decode("#F8B195"));
		C.add(button);
		return button;
	}
	
	//set up panel
	private JPanel setUpPanel(Container C, JPanel panel, int x, int y, int width, int height)
	{
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(x, y, width, height);
		panel.setBorder(border);
		//panel.setBackground(Color.GREEN);
		C.add(panel);
		return panel;
	}
	
	private void setUpWindow()
	{
		//last
		setSize(800,600);
		setTitle("File Chooser But Better");
		setVisible(true);
		setResizable(false);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UI U = new UI();
	}

}
