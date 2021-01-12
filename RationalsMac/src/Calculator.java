import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

//put the buttons on the app
//put on the text area for answer
//make the fractions look better (use unicode character)

public class Calculator extends JFrame
{
	private Container contentPane;
	private JTextField[] TextFieldForInput = {null, null, null, null, null};
	private JTextField TextFieldForAnswer = null;
	private int TextFieldCounter = 0;
	private JButton[] ButtonNums, ButtonCalc = {};
	private JToggleButton SwitchModesButton = null;
	
	private double Ans = 0.0;
	
	public Calculator() {
		createrUserInterface();
	}
	
	private void createrUserInterface() {
		setUpContentPane();
		makeFields();
		TextFieldForAnswer = this.setUpTextField(TextFieldForAnswer, 200, 0, 200, 50);
		TextFieldForAnswer.setEditable(false);
		TextFieldForAnswer.setFont(new Font("", Font.BOLD, 50));
		SwitchModesButton = setUpToggleButton(null, "...", false, 550, 0, 50, 50);
		SwitchModesButton.addActionListener(ActionSwitchPad());
		makeButtonsNumPad();
		makeButtonsCalculations();
		
		
		//last
		setUpWindow();
		//laster last
		repaint();
	}
	
	
	private void makeFields() { // make the fraction fields
		int[] LocationsX = {200, 200, 275, 350, 350};
		int[] LocationsY = {75, 125, 100, 75, 125};
		for (int i = 0; i < TextFieldForInput.length; i++) {
			TextFieldForInput[i] = this.setUpTextField(TextFieldForInput[i], LocationsX[i], LocationsY[i], 50, 50);
			TextFieldForInput[i].setEditable(false);
			TextFieldForInput[i].setFont(new Font("", Font.BOLD, 50));
		}
	}
	
	private void makeButtonsNumPad() { //make the fraction buttons
		//String[] inputs
		int xCounter = 0, yCounter = 0;
		JButton[] ButtonNums= {null, null, null, null, null, null, null, null, null, null, null, null};
		for(int i = 2; i < ButtonNums.length - 1; i++) {
			if((i - 2) % 3 == 0) {
				yCounter += 75;
				xCounter = 0;
			}
			ButtonNums[i] = setUpButton(ButtonNums[i], String.valueOf(i - 1), 200 + xCounter, 450 - yCounter, 50, 50);
			ButtonNums[i].addActionListener(ActionNumberClicked());
			xCounter += 75;
			//ButtonNums[i].setVisible(false);
		}
		
		ButtonNums[0] = setUpButton(null, "0", 200, 450, 125, 50);
		ButtonNums[0].addActionListener(ActionNumberClicked());
		
		ButtonNums[1] = setUpButton(null, "CL", 350, 450, 50, 50);
		ButtonNums[1].addActionListener(ActionClear());
		
		this.ButtonNums = ButtonNums;
	}
	
	private void makeButtonsCalculations() {
		int xCounter = 0, yCounter = 0;
		String[] TextForButtons = {"DEC", "ANS", "FLIP", "+", "-", "*", "/", "=", "^"};
		JButton[] ButtonCalc = {null, null, null, null, null, null, null, null, null, null, null, null};
		for(int i = 0; i < TextForButtons.length; i++) {
			if((i) % 3 == 0) {
				yCounter += 100;
				xCounter = 0;
			}
			ButtonCalc[i] = setUpButton(ButtonCalc[i], TextForButtons[i], 175 + xCounter, 500 - yCounter, 75, 75);
			ButtonCalc[i].addActionListener(ActionCalcClicked());
			xCounter += 100;
			ButtonCalc[i].setVisible(false);
		}
		this.ButtonCalc = ButtonCalc;
		//                                    to dec, +      -    *     /     ans   flip  =     
	}
	
	private ActionListener ActionNumberClicked()
	   {
		   ActionListener listener = new ActionListener()
		   {
			   public void actionPerformed(ActionEvent event)
			   {
				   NumberClicked(event);
			   }
		   };
		   return listener;
	  }
	
	private ActionListener ActionCalcClicked() {
		{
			   ActionListener listener = new ActionListener()
			   {
				   public void actionPerformed(ActionEvent event)
				   {
					   CalcClicked(event);
				   }
			   };
			   return listener;
		   }
	}
	
	private void CalcClicked(ActionEvent event) {
		String ClickedNumber = ((JButton)event.getSource()).getText();
		switch(ClickedNumber){
		case "ANS":
			if (TextFieldCounter <= 2) {
				TextFieldForInput[0].setText("0");
				TextFieldForInput[1].setText("1");
				TextFieldCounter = 2;
			}else {
				TextFieldForInput[3].setText("3");
				TextFieldForInput[4].setText("4");
				TextFieldCounter = 5;
			}
			return;
		case "FLIP":
			if (TextFieldCounter <= 2) {
				String temp = TextFieldForInput[0].getText();
				TextFieldForInput[0].setText(TextFieldForInput[1].getText());
				TextFieldForInput[1].setText(temp);
				TextFieldCounter = 2;
			}else {
				String temp = TextFieldForInput[3].getText();
				TextFieldForInput[3].setText(TextFieldForInput[4].getText());
				TextFieldForInput[4].setText(temp);
				TextFieldCounter = 5;
			}
			return;
		case "DEC":
			if (TextFieldCounter <= 2) {
				System.out.println(Double.parseDouble(TextFieldForInput[0].getText())/Double.parseDouble(TextFieldForInput[1].getText()));
			}else {
				System.out.println(Double.parseDouble(TextFieldForInput[3].getText())/Double.parseDouble(TextFieldForInput[4].getText()));
			}
			return;
		};
		
		// leave this last, only should have + - * / here
		if (TextFieldCounter == 2) {
			TextFieldForInput[2].setText(ClickedNumber);
			TextFieldCounter = 3;
		}
	}
	
	private ActionListener ActionSwitchPad()
	   {
		   ActionListener listener = new ActionListener()
		   {
			   public void actionPerformed(ActionEvent event)
			   {
				   SwitchPad(event);
			   }
		   };
		   return listener;
	   }
	
	private void NumberClicked(ActionEvent event) {
		if (TextFieldCounter < TextFieldForInput.length && TextFieldCounter != 2) {
			String ClickedNumber = ((JButton)event.getSource()).getText();
			String Before = TextFieldForInput[TextFieldCounter].getText();
			String After = Before + ClickedNumber;
			TextFieldForInput[TextFieldCounter].setText(After);
			System.out.println(TextFieldForInput[TextFieldCounter].getText());
			TextFieldCounter += 1;
		}
	}
	
	private void SwitchPad(ActionEvent event) {
		Boolean Visibility = ((JToggleButton) event.getSource()).isSelected();
		for(int i = 0; i < ButtonNums.length - 1; i++) {
			if(ButtonNums[i] != null)
				ButtonNums[i].setVisible(!Visibility);
		}
		for(int i = 0; i < ButtonCalc.length - 1; i++) {
			if(ButtonCalc[i] != null)
				ButtonCalc[i].setVisible(Visibility);
		}
		System.out.println();
	}

	private ActionListener ActionClear()
	   {
		   ActionListener listener = new ActionListener()
		   {
			   public void actionPerformed(ActionEvent event)
			   {
				   ClearScreen(event);
			   }
			   
		   };
		   return listener;
	   }
	
	private void ClearScreen(ActionEvent event) {
		for(int i = 0; i < TextFieldForInput.length; i++) 
			TextFieldForInput[i].setText("");
		TextFieldCounter = 0;
		System.out.println("clear");
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
	private JToggleButton setUpToggleButton(JToggleButton button, String name, Boolean toggled, int x, int y, int width, int height)
	{
		button = new JToggleButton(name, toggled);
		button.setFont(new Font("Arial Black", Font.BOLD, 9));
		button.setBounds(x, y, width, height);
		contentPane.add(button);
		return button;
	}
	private void setUpWindow()
	{
		//last
		setSize(600,600);
		setTitle("Converter");
		setVisible(true);
		setResizable(false);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Calculator calc = new Calculator();
		calc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
