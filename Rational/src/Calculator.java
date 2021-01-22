import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class Calculator extends JFrame
{
	private Container contentPane;
	private JTextField[] TextFieldForInput = {null, null, null, null, null}; 
	private JTextField TextFieldForEverything = null; //the chosen one
	private int TextFieldCounter = 0;
	private JButton[] ButtonNums, ButtonCalc = {};
	private JToggleButton SwitchModesButton = null;
	private Boolean Solved = false;
	private JButton Next = null;
	private JButton Previous = null;
	private JButton Submit = null;
	private JButton DelLast = null;
	private JButton ClearAll = null;
	
	//delete one character
	//delete all in textbox
	//debug expoents
	
	private Rational Ans = null;
	
	public Calculator() {
		createrUserInterface();
	}
	
	private void createrUserInterface() {
		setUpContentPane();
		makeFields();
		TextFieldForEverything = this.setUpTextField(TextFieldForEverything, 0, 0, 500, 50);
		TextFieldForEverything.setEditable(false);
		TextFieldForEverything.setFont(new Font("", Font.BOLD, 50));
		SwitchModesButton = setUpToggleButton(null, "...", false, 550, 0, 50, 50);
		SwitchModesButton.addItemListener(ActionSwitchPad());
		Submit = setUpButton(Submit, "Submit", 400, 500, 200, 50);
		Submit.addActionListener(ActionSubmit());
		DelLast = setUpButton(null, "B", 75, 500, 50, 50);
		DelLast.addActionListener(ActionBackspace());
		ClearAll = setUpButton(null, "CL", 150, 500, 50, 50);
		ClearAll.addActionListener(ActionClear());
		SetUpPreviousAndNextButtons();
		makeButtonsNumPad();
		makeButtonsCalculations();
		
		
		//last
		setUpWindow();
		//laster last
		repaint();
	}
	
	private ActionListener ActionBackspace() {
		{
			   ActionListener listener = new ActionListener()
			   {
				   public void actionPerformed(ActionEvent event)
				   {
					   Backspace(event);
				   }
			   };
			   return listener;
		   }
	}
	
	private void Backspace(ActionEvent event) {
		String Before = TextFieldForEverything.getText();
		String After = Before.substring(0, Before.length() - 1);
		TextFieldForEverything.setText(After);
	}
	
	private void SetUpPreviousAndNextButtons() {
		Next = setUpButton(Next, ">", 550, 275, 50, 50);
		Previous = setUpButton(Previous, "<", 0, 275, 50, 50);
		
		Next.addActionListener(ActionForwardBackwards());
		Previous.addActionListener(ActionForwardBackwards());
	}
	
	private ActionListener ActionForwardBackwards() {
		{
			   ActionListener listener = new ActionListener()
			   {
				   public void actionPerformed(ActionEvent event)
				   {
					   PrevNext(event);
				   }
			   };
			   return listener;
		   }
	}
	
	private void PrevNext(ActionEvent event) {
		String ButtonText = ((JButton)event.getSource()).getText();
		switch(ButtonText) {
		case "<":
			if (TextFieldCounter == 0) {
				return;
			}else {
				TextFieldCounter--;
			}
			break;
		case ">":
			if (TextFieldCounter == 4) {
				return;
			}else {
				TextFieldCounter++;
			}
			break;
		}
		for(int i = 0; i < TextFieldForInput.length; i++) {
			TextFieldForInput[i].setBorder(new LineBorder(Color.BLACK, 0));
		}
		if (TextFieldCounter == 2) {
			SwitchModesButton.setSelected(true);
		}else {
			SwitchModesButton.setSelected(false);
		}
		TextFieldForInput[TextFieldCounter].setBorder(new LineBorder(Color.BLACK, 3)); 
	}
	
	private void makeFields() { // make the fraction fields
		int[] LocationsX = {0, 0, 275, 400, 400};
		int[] LocationsY = {75, 125, 100, 75, 125};
		for (int i = 0; i < TextFieldForInput.length; i++) {
			TextFieldForInput[i] = this.setUpTextField(TextFieldForInput[i], LocationsX[i], LocationsY[i], 200, 50);
			TextFieldForInput[i].setEditable(false);
			TextFieldForInput[i].setHorizontalAlignment(JTextField.CENTER);
			TextFieldForInput[i].setFont(new Font("", Font.BOLD, 50));
		}
		TextFieldForInput[2].setBounds(275, 100, 50, 50);
		TextFieldForInput[0].setBorder(new LineBorder(Color.BLACK, 3)); 
	}
	
	private ActionListener ActionSubmit() {
		{
			   ActionListener listener = new ActionListener()
			   {
				   public void actionPerformed(ActionEvent event)
				   {
					   Solve();
				   }
			   };
			   return listener;
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
	
	private Boolean isNotEmpty(int a, int b) {
		return (!TextFieldForInput[a].getText().equals("0")) && (!TextFieldForInput[b].getText().equals("0")) && (!TextFieldForInput[a].getText().equals("")) && (!TextFieldForInput[b].getText().equals(""));
	}
	
	private void CalcClicked(ActionEvent event) {
		DecimalFormat formatter = new DecimalFormat("0.00000");
		String ClickedNumber = ((JButton)event.getSource()).getText();
		switch(ClickedNumber){
		case "ANS":
			if(Ans != null){
				if (TextFieldCounter <= 2) {
					TextFieldForInput[0].setText(String.valueOf(Ans.getNeumerator()));
					TextFieldForInput[1].setText(String.valueOf(Ans.getDenominator()));
				}else {
					TextFieldForInput[3].setText(String.valueOf(Ans.getNeumerator()));
					TextFieldForInput[4].setText(String.valueOf(Ans.getDenominator()));
				}
			}
			return;
		case "FLIP":
			if (TextFieldCounter <= 2) {
				if(isNotEmpty(0, 1)) {
					String temp = TextFieldForInput[0].getText();
					TextFieldForInput[0].setText(TextFieldForInput[1].getText());
					TextFieldForInput[1].setText(temp);
				}
			}else {
				if(isNotEmpty(3, 4)) {
					String temp = TextFieldForInput[3].getText();
					TextFieldForInput[3].setText(TextFieldForInput[4].getText());
					TextFieldForInput[4].setText(temp);
				}
			}
			return;
		case "DEC":
			/*if(!Solved) {
				if (TextFieldCounter <= 2) {
					TextFieldForAnswer.setText(String.valueOf(Double.parseDouble(TextFieldForInput[0].getText())/Double.parseDouble(TextFieldForInput[1].getText())));
				}else {
					TextFieldForAnswer.setText(String.valueOf(Double.parseDouble(TextFieldForInput[3].getText())/Double.parseDouble(TextFieldForInput[4].getText())));
				}
			}else {
				TextFieldForAnswer.setText(formatter.format(Ans.toDecimal())); 
			}
			return;*/
		};
		
		// leave this last, only should have + - * / here
		TextFieldForEverything.setText(TextFieldForEverything.getText() + ClickedNumber);
	}
	
	private ItemListener ActionSwitchPad()
	   {
		ItemListener listener = new ItemListener()
		   {
			   public void itemStateChanged(ItemEvent event)
			   {
				   SwitchPad(event);
			   }
		   };
		   return listener;
	   }
	
	private void NumberClicked(ActionEvent event) {
		String ClickedNumber = ((JButton)event.getSource()).getText();
		if (ClickedNumber == "0" && (TextFieldCounter == 1 || TextFieldCounter == 4)) 
			return;
			String Before = TextFieldForEverything.getText();
			String After = Before + ClickedNumber;
			TextFieldForEverything.setText(After);
			//System.out.println(TextFieldForInput[TextFieldCounter].getText());
	}
	
	private Boolean isValid(String check) {
		int index = 0;
		for(index = 0; index < TextFieldForEverything.getText().length(); index++) {
			if(TextFieldForEverything.getText().charAt(index) == '/')
				// 1/4 / 2/3 + 1/5 - 1/2 
				break;
		}
		String Numerator = TextFieldForEverything.getText().substring(0, index);
		String Denominator = TextFieldForEverything.getText().substring(0, index);
		
		return true;
	}
	
	private Boolean isNumber(char AChar) {
		try{
			Integer.parseInt(String.valueOf(AChar)); 
		}catch (Exception e) {
			return false;
		}
		return true;
	}
	
	private void Solve() {
		
		String SolveField = TextFieldForEverything.getText();
		int i = -1;
		while(true) {
			i++;
			char CharAtPointer = SolveField.charAt(i);
			System.out.println(CharAtPointer);
			if(!isNumber(CharAtPointer)) {
				String Neumerator = SolveField.substring(0, i);
				System.out.println(Neumerator);
				if(i != SolveField.length() - 1) {
					String Op = String.valueOf(SolveField.charAt(i));
					SolveField = SolveField.substring(i + 1, SolveField.length());
				}
				i = 0;
			}else if (i == SolveField.length() - 1) {
				String Neumerator = SolveField.substring(0, SolveField.length());
				System.out.println(Neumerator);
				break;
			}
		}
		/*for(JTextField ATextField:TextFieldForInput) {
			if (ATextField.getText().equals(""))
				return;
		}
		try {
			Rational Frac1 = new Rational(Integer.parseInt(TextFieldForInput[0].getText()), Integer.parseInt(TextFieldForInput[1].getText()));
			Rational Frac2 = new Rational(Integer.parseInt(TextFieldForInput[3].getText()), Integer.parseInt(TextFieldForInput[4].getText()));
			Rational Answer = null;
			switch(TextFieldForInput[2].getText()){
				case "+":
					Answer = Frac1.add(Frac2);
					Ans = Answer;
					Solved = true;
					break;
				case "-":
					Answer = Frac1.subtract(Frac2);
					Ans = Answer;
					Solved = true;
					break;
				case "*":
					Answer = Frac1.multiply(Frac2);
					Ans = Answer;
					Solved = true;
					break;
				case "/":
					Answer = Frac1.divide(Frac2);
					Ans = Answer;
					Solved = true;
					break;
				case "=":
					TextFieldForAnswer.setText(String.valueOf(Frac1.equal(Frac2)));
					return;
				case "^":
					TextFieldForAnswer.setText(String.valueOf(Frac1.expoentialv2(Frac2.toDecimal())));
					Solved = true;
					return;
			};
			TextFieldForAnswer.setText(Answer.toString());
		}
		catch(Exception e) {
			e.printStackTrace();
		}*/
	}
	
	private void SwitchPad(ItemEvent event) {
		SwitchVisiPad(((JToggleButton) event.getSource()).isSelected());
	}
	
	private void SwitchVisiPad(Boolean Visibility) {
		for(int i = 0; i < ButtonNums.length - 1; i++) {
			if(ButtonNums[i] != null)
				ButtonNums[i].setVisible(!Visibility);
			
		}
		for(int i = 0; i < ButtonCalc.length - 1; i++) {
			if(ButtonCalc[i] != null)
				ButtonCalc[i].setVisible(Visibility);
		}//*/
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
		Solved = false;
		TextFieldForEverything.setText("");
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
		setTitle("Fraction Destruction");
		setVisible(true);
		setResizable(false);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Calculator calc = new Calculator();
		calc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
