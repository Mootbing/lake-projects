import java.awt.Color;
import java.awt.Container;
import java.awt.Font;

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
	private JTextField TextFieldForInput;
	
	private int Length = 300;
	private int Width = 100;
	
	public Calculator() {
		createrUserInterface();
	}
	
	private void createrUserInterface() {
		setUpContentPane();
		TextFieldForInput = this.setUpTextField(TextFieldForInput, 0, 0, Length, Width);
		TextFieldForInput.setEditable(false);
		TextFieldForInput.setFont(new Font("", Font.BOLD, Width/2));
		makeButtons();
		//last
		setUpWindow();
		
		TextFieldForInput.setLocation(this.getWidth()/2 - (Length/2), this.getHeight()/10 - (Width/2));	
		
		//laster last
		repaint();
	}
	
	private JButton[] makeButtons() {
		//String[] inputs
		int xCounter = 0, yCounter = 0;
		JButton[] ButtonReturns= {null, null, null, null, null, null, null, null, null, null, null};
		for(int i = 1; i < ButtonReturns.length - 1; i++) {
			if((i - 1) % 3 == 0) {
				yCounter += 75;
				xCounter = 0;
			}
			JButton tempButton = null;
			tempButton = setUpButton(tempButton, String.valueOf(i), 200 + xCounter, 400 - yCounter, 50, 50);
			ButtonReturns[i] = tempButton;
			xCounter += 75;
		}
		
		JButton tempButton = null;
		ButtonReturns[0] = setUpButton(tempButton, "0", 200, 400, 200, 50);;
		
		return ButtonReturns;
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
