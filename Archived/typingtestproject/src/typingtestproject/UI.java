package typingtestproject;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class UI extends JFrame implements KeyListener{

	
	private Container contentPane;
	private JTextField typingField;
	private JTextArea answerTextArea;
	private JScrollPane scroll;
	private JLabel Succ;
	private JLabel Fail;
	private JLabel Ovr;
	private JLabel WPM;
	
	private int accuracy[] = {
		0, //success
		0, //fails
		1, //turns
		1, //seconds
		0 //on
	};
	
	private double SF = 0.0;  //accuracy (%) success/fail
	
	/*private String arrayOfText[] = {
		"Hello,",
		"Ruoyu",
		"has",
		"a",
		"gatbing"
	};*/
	
	private String textstuff = "Royandle thinks that Java made by Oracle, an American company is trash... Why so you may ask? Because Ruoyu goes RAAAAAA very often. As once of a wise man said one day, ruoyu's thou rage. Shakspearan Ruoyu is a madlib scientist. He thinks Python is great! But he doesn't like Java/C++ a lot ;( sorry! anyways, he hates these things called semicolins (;) because they mess up your code in other languages and you don't need them in the jack-of-all-trades class: python! He has also tried learning HTML and failed because he is so <bad>True tho</bad> Just kidding! Ruoyu is probably going @$%@^$$@ right now swearing at the screen... You're welcome :). Java will always be >= Python... MUAHAHAHAHAHAAAAA. ~~Cowland 100% being realistic. Ruoyu is also an Albert Einestine and not just a computer scientist! He loves solving complicated equations with his AMC-10 handbook! with E=MC^2 as one of his favorite topics, if not then it must be the NASA space math! Anyways he loves coding and forgets to put ``` in front of his code sometimes, but that's forgivable... right? lol. OH SHOOT U JUST FELL INTO LAVA IN MINECRAFT!!!! Type '/gamemode 0' as fast as you can! Quick! Save Steve!!!! No, its the forward slash (/) not the back slash \' or |... JUST TYPE THE SHIT ALREADY ELSE IMMA GO RuOyU RaGEy!1!!119378720765610-+_-asdf computer crashed due to no wifi... ;( ";
	
	private String arrayOfText[] = textstuff.split(" ");
	
	private ArrayList<String> arraylisttxt = new ArrayList<>(Arrays.asList(arrayOfText));
	// get from file
	public UI()
	{
		createrUserInterface();
	}
	
	private void createrUserInterface()
	{
		setUpContentPane();
		Succ = this.setTexts(Succ, "Success: 0", 20, 0, 100, 50);
		Fail = this.setTexts(Fail, "Fail: 0",  120, 0, 100, 50);
		Ovr = this.setTexts(Ovr, "Success Rate: 0%",  220, 0, 200, 50);
		WPM = this.setTexts(WPM, "WPM: NULL",  520, 0, 300, 50);
		typingField = this.setUpTextField(typingField, 50, 500, 500, 50);
		scroll = this.setupScrollPane(answerTextArea, scroll, 20, 40, 560, 425);
		typingField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) 
			{
			    if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			    	String a = typingField.getText().trim();
			    	typingField.setText(null);
			    	if(a.equals(arrayOfText[accuracy[4]])) {
			    		accuracy[0]++;
			    		accuracy[4]++;
			    		arraylisttxt.remove(0);
			    		ArrayList<String> aa = new ArrayList<>();
			    		int len = arraylisttxt.size();
			    		for(int i = 0; i<len / 20; i++) {
			    			aa.add(arraylisttxt.get(i));
			    		}
			    		((JTextArea)(scroll.getViewport().getView())).setText(String.join(" ", aa));
			    	}else {
			    		accuracy[1]++;
			    	}
			    	accuracy[2]++;
			    	if(accuracy[1] != 0 && accuracy[0] != 0){
		    			SF = accuracy[0] / accuracy[1];
		    		}else if(accuracy[1] == 0) {
		    			SF = accuracy[0];
		    		}else if (accuracy[0] == 0) {
		    			SF = -1*accuracy[1];
		    		}
		    		Succ.setText("Success: "+Integer.toString(accuracy[0]));
		    		Fail.setText("Fail: "+Integer.toString(accuracy[1]));
		    		Ovr.setText("Success Rate: "+Double.toString((SF / accuracy[2]) * 100)+"%");
			    }
			}
		});
		
		
		//last
		setUpWindow();

		this.recursiveTimer();
	}
	private void recursiveTimer() {
		new Timer().schedule( 
		        new TimerTask() {
		            @Override
		            public void run() {
		            	accuracy[3]++;
		            	WPM.setText("WPM: "+Integer.toString(accuracy[0] * 180 / accuracy[3]));
		            	recursiveTimer();
		            }
		        }, 
		        500 
		);
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
		f.setText(null);
		contentPane.add(f);
		return f;
	}
	private JLabel setTexts(JLabel f, String name, int x, int y, int width, int height) 
	{
		f = new JLabel(name);
		f.setBounds(x, y, width, height);
		contentPane.add(f);
		return f;
	}
	private JScrollPane setupScrollPane(JTextArea a, JScrollPane s,int x, int y, int width, int height) 
	{
		a = new JTextArea();
		a.setEditable(false);
		String text = String.join(" ", arrayOfText);
		a.setText(text);
		s = new JScrollPane(a);
		s.setBounds(x, y, width, height);
		contentPane.add(s);
		return s;
	}
	private void setUpWindow()
	{
		//last
		setSize(600,600);
		setTitle("Typing Stuff (MADE IN JAVA THANK YOU VERY MUCH RUOYU)");
		setVisible(true);
		setResizable(false);
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
