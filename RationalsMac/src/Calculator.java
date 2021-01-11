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

//put the buttons on the app
//put on the text area for answer
//make the fractions look better (use unicode character)

public class Calculator extends JFrame
{
	private Container contentPane;
	private JTextField[] TextFieldForInput = {null, null, null, null, null};
	private int TextFieldCounter = 0;
	private JButton[] ButtonNums, ButtonCalc = {};
	
	private int Length = 300;
	private int Width = 100;
	
	public Calculator() {
		createrUserInterface();
	}
	
	private void createrUserInterface() {
		setUpContentPane();
		makeFields();
		//TextFieldForInput = this.setUpTextField(TextFieldForInput, 0, 0, Length, Width);
		//TextFieldForInput.setEditable(false);
		//TextFieldForInput.setFont(new Font("", Font.BOLD, Width/2));
		ButtonNums = makeButtonsNumPad();
		ButtonCalc = makeButtonsCalculations();
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
			TextFieldForInput[i].setFont(new Font("", Font.BOLD, Width/2));
		}
	}
	
	private JButton[] makeButtonsNumPad() { //make the fraction buttons
		//String[] inputs
		int xCounter = 0, yCounter = 0;
		JButton[] ButtonReturns= {null, null, null, null, null, null, null, null, null, null, null, null, null};
		for(int i = 3; i < ButtonReturns.length - 1; i++) {
			if((i - 3) % 3 == 0) {
				yCounter += 75;
				xCounter = 0;
			}
			ButtonReturns[i] = setUpButton(ButtonReturns[i], String.valueOf(i - 2), 200 + xCounter, 450 - yCounter, 50, 50);
			ButtonReturns[i].addActionListener(ActionNumberClicked());
			xCounter += 75;
		}
		
		JButton tempButton = null;
		ButtonReturns[0] = setUpButton(tempButton, "0", 200, 450, 125, 50);
		ButtonReturns[0].addActionListener(ActionNumberClicked());
		
		ButtonReturns[1] = setUpButton(tempButton, "CL", 350, 450, 50, 50);
		ButtonReturns[1].addActionListener(ActionClear());
		
		ButtonReturns[2] = setUpButton(tempButton, "...", 550, 0, 50, 50);
		ButtonReturns[2].addActionListener(ActionSwitchPad());
		
		return ButtonReturns;
	}
	
	private JButton[] makeButtonsCalculations() {
		int xCounter = 0, yCounter = 0;
		JButton[] ButtonReturns= {null, null, null, null, null, null, null, null, null, null, null, null}; 
		//                       to dec, +      -    *     /     ans   flip  =     
		return null;
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
		if (TextFieldCounter < TextFieldForInput.length) {
			String ClickedNumber = ((JButton)event.getSource()).getText();
			String Before = TextFieldForInput[TextFieldCounter].getText();
			String After = Before + ClickedNumber;
			TextFieldForInput[TextFieldCounter].setText(After);
			System.out.println(TextFieldForInput[TextFieldCounter].getText());
			TextFieldCounter += 1;
		}
	}
	
	private void SwitchPad(ActionEvent event) {
		for(int i = 0; i < ButtonNums.length; i++) {
			ButtonNums[i].setVisible(false);
		}
		for(int i = 0; i < ButtonCalc.length; i++) {
			ButtonCalc[i].setVisible(true);
		}
		System.out.println("switch");
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
