import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.DecimalFormat;
import java.util.ArrayList;

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
	private JTextField TextFieldForEverything = null; //the chosen one
	private int TextIndexCounter = 0;
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
		TextFieldForEverything = this.setUpTextField(TextFieldForEverything, 0, 0, 500, 50);
		TextFieldForEverything.setEditable(true);
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
			if (TextIndexCounter > 0) 
				TextIndexCounter--;
			break;
		case ">":
			if (TextIndexCounter < TextFieldForEverything.getText().length() - 1) 
				TextIndexCounter++;
			break;
		}
		TextFieldForEverything.requestFocus();
		TextFieldForEverything.select(TextIndexCounter, TextIndexCounter + 1);
		System.out.println(TextIndexCounter);
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
				yCounter += 100;
				xCounter = 0;
			}
			ButtonNums[i] = setUpButton(ButtonNums[i], String.valueOf(i - 1),  150 + xCounter, 400 - yCounter, 75, 75);
			ButtonNums[i].addActionListener(ActionNumberClicked());
			xCounter += 100;
			//ButtonNums[i].setVisible(false);
		}
		
		ButtonNums[0] = setUpButton(null, "0", 150, 400, 275, 50);
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
			ButtonCalc[i] = setUpButton(ButtonCalc[i], TextForButtons[i], 150 + xCounter, 400 - yCounter, 75, 75);
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
		DecimalFormat formatter = new DecimalFormat("0.00000");
		String ClickedNumber = ((JButton)event.getSource()).getText();
		switch(ClickedNumber){
		case "ANS":
			if(Ans != null)
				TextFieldForEverything.setText(TextFieldForEverything.getText() + Ans.toString());
			return;
		case "FLIP":
			//todo
			return;
		case "DEC":
			TextFieldForEverything.setText(String.valueOf(Ans.toDecimal()));
			return;
		};
		
		// leave this last, only should have + - * / here
		InsertText(ClickedNumber);
	}
	
	private void InsertText(String Text) {
		if (TextFieldForEverything.getText().length() > TextIndexCounter) {
			ArrayList<String> ArrayOfTextByChar = new ArrayList<String>();
			for(int i = 0; i < TextFieldForEverything.getText().length(); i++) {
				ArrayOfTextByChar.add(String.valueOf(TextFieldForEverything.getText().charAt(i)));
			}
			
			ArrayOfTextByChar.set(TextIndexCounter, Text);
			TextFieldForEverything.setText(String.join("", ArrayOfTextByChar));
			}
		else {
			TextFieldForEverything.setText(TextFieldForEverything.getText() + Text);
		}
		TextIndexCounter++;
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
		if (ClickedNumber == "0" && (TextIndexCounter == 1 || TextIndexCounter == 4)) 
			return;
		InsertText(ClickedNumber);
			//System.out.println(TextFieldForInput[TextIndexCounter].getText());
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
		if(String.valueOf(AChar).equals("/"))
			return true;
		try{
			Integer.parseInt(String.valueOf(AChar)); 
		}catch (Exception e) {
			return false;
		}
		return true;
	}
	
	private ArrayList<String> SeperateEquations(){
		
		int counter = 0;
		ArrayList<String> EquationsSplitUpReturn = new ArrayList<String>();
		String AnswerText = TextFieldForEverything.getText().replaceAll(" ", "");
		while(true) {
			if (AnswerText.length() - 1 == counter) {
				EquationsSplitUpReturn.add(AnswerText.substring(0, AnswerText.length()));
				break;
			}else if (!isNumber(AnswerText.charAt(counter))) {
				EquationsSplitUpReturn.add(AnswerText.substring(0, counter));
				EquationsSplitUpReturn.add(AnswerText.substring(counter, counter + 1));
				AnswerText = AnswerText.substring(counter + 1, AnswerText.length());
				counter = 0;
				System.out.println(AnswerText);
			} 
			counter++;
		}
		
		return EquationsSplitUpReturn;
	}
	
	private Rational ConvertSlashedStringToRational(String SlashedValue) {
		
		if(!SlashedValue.contains("/"))
			return null;
		
		String[] Splitted = SlashedValue.split("/");
		int Neumarator = Integer.parseInt(Splitted[0]);
		int Denominator = Integer.parseInt(Splitted[1]);
		
		Rational ReturnValue= new Rational(Neumarator, Denominator);
		
		return ReturnValue;
	}
	
	private Rational SolveByOrderOfOps(ArrayList<String> Values) {
		Rational Answer = null;
		
		ArrayList<Integer> Indexes = new ArrayList<Integer>();
		for(int i = 0; i < Values.size(); i++ ) {
			String Item = Values.get(i);
			if (Item.equals("*") || Item.equals("d")) {
				Indexes.add(i);
			}
		}
		
		System.out.println(Indexes);
		
		int HowManyDecremented = 0;
		for (int i = 0; i < Indexes.size(); i++) {
			int CurrentIndex = Indexes.get(i) - HowManyDecremented;
			System.out.println(CurrentIndex);
			Rational FirstValue = ConvertSlashedStringToRational(Values.get(CurrentIndex - 1));
			Rational SecondValue = ConvertSlashedStringToRational(Values.get(CurrentIndex + 1));
			String Op = Values.get(CurrentIndex);
			
			if(Op.equals("d")) {
				Answer = FirstValue.divide(SecondValue);
			} else if(Op.equals("*")) {
				Answer = FirstValue.multiply(SecondValue);
			}
			
			Values.set(CurrentIndex - 1, Answer.toString().replaceAll(" ", ""));
			Values.remove(CurrentIndex);
			Values.remove(CurrentIndex);
			
			HowManyDecremented += 2;
			
			System.out.println(Values);
		}
		
		//1/2+1/3*1/4*1/5
		
		HowManyDecremented = 0;
		int counter = 1;
		while (Values.size() != 1) {
			Rational FirstValue = ConvertSlashedStringToRational(Values.get(counter - 1));
			Rational SecondValue = ConvertSlashedStringToRational(Values.get(counter + 1));
			String Op = Values.get(counter);
			
			if(Op.equals("+")) {
				Answer = FirstValue.add(SecondValue);
			} else if(Op.equals("-")) {
				Answer = FirstValue.subtract(SecondValue);
			}
			
			Values.set(counter - 1, Answer.toString().replaceAll(" ", ""));
			Values.remove(counter);
			Values.remove(counter);
			
			System.out.println(Values);
		}
		System.out.println(Values);
		
		return Answer;
	}
	
	private void Solve() {	
		
		ArrayList<String> SeperatedEquation = SeperateEquations();
		
		Ans = SolveByOrderOfOps(SeperatedEquation);
		
		TextFieldForEverything.setText(Ans.toString()); //1/2+1/3*1/4*1/5d1/3
		/*
		 * [1 / 2, 1 / 3, 1 / 4, 1 / 5, 1 / 6]
		 * [+, +, *, //]
		* for fractions 4, 5 (operator), 6
		//1/2 + 1/3 + 1/4 * 1/5 // 1/6
		//step 1
		//[1 / 2, 1 / 3, 1/20, 1 / 6]
		//[+, +, //]
		//step 2
		//[1 / 2, 1 / 3, 3/10]
		//[+, +]
		//step 2
		//[1 / 2, 1 / 3, 3/10]
		//[+, +]
		 * [5 / 6, 3/10]
		 * [+]
		 * = 68/60
		 * 
		 * make a method that will remove the rations done and replace it with answer at the index of the operation
		 * order matters here
		 * 
		 * */
	}
	
	private void UpdateList(){
		
	}
	
	private Rational MakeRationals(double ANumber1, double ANumber2) {
		Rational r = new Rational((int)ANumber1, (int)ANumber2);
		TextFieldForEverything.setText(r.toString());
		System.out.println(r.toString());
		return r;
	}
	
	private Rational Eval(String Aop, Rational ANumber1, Rational ANumber2) {
		switch(Aop) {
			case "+":
				//System.out.println(ANumber1.add(ANumber2).toString());
				return ANumber1.add(ANumber2);
			case "-":
				return ANumber1.subtract(ANumber2);
			case "*":
				return ANumber1.multiply(ANumber2);
			case "//":
				return ANumber1.divide(ANumber2);
			case "^":
				return ANumber1.expoential(ANumber2.toDecimal());
		}
		return null;
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
