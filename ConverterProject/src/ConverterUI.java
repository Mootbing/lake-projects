import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ConverterUI extends JFrame
{
	private Container contentPane;
	private JButton submitButton;
	private JTextField amountTextField;
	private JTextArea answerTextArea;
	private JScrollPane scroll;
	private JComboBox menu;
	private JButton newButtons;
	private String[] typeButton =
		{
			"distance",			
			"time",
			"money",
			"mass",
			"electric",
			"temperature"
		};
	private String[] menuItemsMeasure = FileFunctions.loadUIArrays(typeButton[0]);
	private String[] menuItemsTime = FileFunctions.loadUIArrays(typeButton[1]);
	private String[] menuItemsMoney = FileFunctions.loadUIArrays(typeButton[2]);
	private String[] menuItemsMass = FileFunctions.loadUIArrays(typeButton[3]);
	private String[] menuItemsElectric = FileFunctions.loadUIArrays(typeButton[4]);
	private String[] menuItemsTemp = FileFunctions.loadUIArrays(typeButton[5]);
	
	private ArrayList<JButton> buttonArray = new ArrayList<JButton>();
	private ActionListener event;
	
	// get from file
	public ConverterUI()
	{	
		createrUserInterface();
	}
	
	private void createrUserInterface()
	{
		setUpContentPane();
		amountTextField = this.setUpTextField(amountTextField, 100, 100, 300, 40);
		submitButton = this.setUpButton(submitButton, "Submit", 425, 100, 90, 40);
		submitButton.addActionListener(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent event)
				{
					String fileName = "";
					ReadFromFile reader = new ReadFromFile();
					String selections = menu.getItemAt(menu.getItemCount() - 1).toString();
					
					if(selections.contains("distance")) {
						fileName = "distance.txt";
					}else if(selections.contains("time")) {
						fileName = "time.txt";
					}else if(selections.contains("money")) {
						fileName = "money.txt";
					}else if(selections.contains("time")) {
						fileName = "time.txt";
					}else if(selections.contains("mass")) {
						fileName = "mass.txt";
					}else if(selections.contains("electric")) {
						fileName = "electric.txt";
					} 
					ArrayList<RateRecord> a = reader.readRates(fileName, menu.getSelectedItem().toString());
					String infoHolder = "";
					String result = "";
					RateRecord r = null;
					answerTextArea.setText("");
					for(int i = 0; i < a.size(); i++) {
						r = a.get(i);
						Converter convert = new ConversionPerformer(menu.getSelectedItem().toString(), Double.parseDouble(amountTextField.getText()), selections, fileName);
						result = convert.convert();
					}
					answerTextArea.append(result.toString());
				}
			}
		);
		scroll = this.setupScrollPane(answerTextArea, scroll, 20, 200, 560, 360);
		menu = this.setUpMenu(menu, menuItemsMeasure,  250, 150, 100, 50);
		for (int i = 0; i < typeButton.length; i++) {
			JButton btn = this.setUpButton(newButtons, typeButton[i], (i * 95 + 10), 30, 90, 40);
			btn.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e) {
				    	for(int i = 0; i < buttonArray.size(); i++)
				    	{
				    		if(e.getSource() == buttonArray.get(i))
				    		{
				    			resetButtons(i);
				    			switch(i) 
				    			{
				    				case 0:
				    					updateMenuItems(menuItemsMeasure);
				    					break;
				    				case 1:
				    					updateMenuItems(menuItemsTime);
				    					break;
				    				case 2:
				    					updateMenuItems(menuItemsMoney);
				    					break;
				    				case 3:
				    					updateMenuItems(menuItemsMass);
				    					break;
				    				case 4:
				    					updateMenuItems(menuItemsElectric);
				    					break;
				    				default:
				    					updateMenuItems(menuItemsTemp);
				    					break;
				    			
				    			}
				    		}
				    	}
					}
				}
			);
			buttonArray.add(btn);
		}
		
		
		//last
		setUpWindow();
	}
	
	private void setUpContentPane()
	{
		contentPane = getContentPane();
		contentPane.setLayout(null);
		contentPane.setBackground(Color.DARK_GRAY);
	}
	
	
	private JTextField setUpTextField(JTextField f, int x, int y, int width, int height) 
	{
		f = new JTextField();
		f.setBounds(x, y, width, height);
		contentPane.add(f);
		return f;
	}
	private JButton setUpButton(JButton button, String name, int x, int y, int width, int height)
	{
		button = new JButton(name);
		button.setFont(new Font("Arial Black", Font.BOLD, 9));
		button.setBounds(x, y, width, height);
		contentPane.add(button);
		return button;
	}
	private JScrollPane setupScrollPane(JTextArea a, JScrollPane s, int x, int y, int width, int height) 
	{
		a = new JTextArea();
		answerTextArea = a;
		a.setEditable(false);
		s = new JScrollPane(a);
		s.setBounds(x, y, width, height);
		contentPane.add(s);
		return s;
	}
	private JComboBox setUpMenu(JComboBox b, String[] items, int x, int y, int width, int height)
	{
		b = new JComboBox(items);
		b.setBounds(x, y, width, height);
		b.setSelectedIndex(items.length - 1);
		contentPane.add(b);
		return b;
	}
	private void setUpWindow()
	{
		//last
		setSize(600,600);
		setTitle("Converter");
		setVisible(true);
		setResizable(false);
	}
	
	private void updateMenuItems(String[] items)
	{
		menu.removeAllItems();
		for(int i = 0; i < items.length; i++)
			menu.addItem(items[i]);
	}
    
    private void resetButtons(int i) {
    	for(JButton button: buttonArray) {
    		button.setOpaque(false);
    		button.setBorderPainted(true);
    		button.setBackground(Color.gray);
    	}
    	buttonArray.get(i).setBorderPainted(false);
		buttonArray.get(i).setOpaque(true);
		buttonArray.get(i).setBackground(Color.GREEN);
    }
}
