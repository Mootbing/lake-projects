import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.LineBorder;


/*
 * Menu of selection buttons that changes the panel
 * and use different buttons from different selections
 * when person clicks on the button for pizza it automatically shows a pizza cooking.
 * */

public class Microwave extends JFrame
{
   private Container contentPane;
   
   private JPanel doorJPanel, controlJPanel;
   
   private JLabel displayJLabel;
   
   private JButton oneJButton, twoJButton, threeJButton, 
      fourJButton, fiveJButton, sixJButton, sevenJButton, 
      eightJButton, nineJButton, zeroJButton, startJButton, 
      clearJButton, clockJButton, modeJButton;
   
   private JButton[] buttons = {oneJButton, twoJButton, threeJButton, 
		      fourJButton, fiveJButton, sixJButton, sevenJButton, 
		      eightJButton, nineJButton, zeroJButton, startJButton, 
		      clearJButton, clockJButton, modeJButton};
   private String[] buttonsNames = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "Start", "0", "Clear", "Clock", "Modes"};

   private String timeToDisplay = "";
   
   private ClockTimer clockTimer  =  new ClockTimer(0, 0, false);
   
   private Clock clock;  //Microwave has a clock
   
   //constructor
   public Microwave()
   {
	   createUserInterface();  
   }
   
   
   //methods
   private void createUserInterface()
   {
	   this.setUpContentPane();
	   
	   doorJPanel =  this.setUpPanel(doorJPanel, 16, 16, 350, 284);
	   controlJPanel = this.setUpPanel(controlJPanel, 368, 16, 182, 284);
	   
	   displayJLabel = this.setUpLabel(displayJLabel, "12:00", 31, 13, 120, 30);
	   displayJLabel.addPropertyChangeListener(this.setUpPropertyChangeListener());
	   clock = new Clock(displayJLabel);
	   this.setUpButtons();
	   setUpWindow();  
   }
   
   
   private PropertyChangeListener setUpPropertyChangeListener()
   {
	   PropertyChangeListener p = new PropertyChangeListener() 
	   {
	
			public void propertyChange(PropertyChangeEvent evt) 
			{
				if(clockTimer.isRunning())
				{
					System.out.println("clock timer running");
				}
				else
				{
					System.out.println("clock timer NOT running");
					if(displayJLabel.getText().equals("clear") || displayJLabel.getText().equals("done!"))
					{
						doorJPanel.removeAll();
						doorJPanel.repaint();
						clock.start();
					}
				}
			}
	 };
 
	 return p;
   }
   
   private void setUpWindow()
   {
	   setBounds(300, 300, 574, 346);
	   setTitle("Microwave");
	   setVisible(true);
	   setResizable(false);
   }
   
   private void setUpContentPane()
   {
	   contentPane = getContentPane();
	   contentPane.setLayout(null);
	   contentPane.setBackground(Color.DARK_GRAY);
   }
   
   
   private JPanel setUpPanel(JPanel panel, int x, int y, int width, int height)
   {
	   panel = new JPanel();
	   panel.setLayout(null);
	   panel.setBorder(new LineBorder(Color.GREEN));
	   panel.setBounds(x, y, width, height);
	   panel.setBackground(Color.LIGHT_GRAY);
	   contentPane.add(panel);
	   return panel;
   }
   
   private JLabel setUpLabel(JLabel label, String text, int x, int y, int width, int height)
   {
	   label = new JLabel(text);
	   label.setHorizontalAlignment(JLabel.CENTER);
	   label.setFont(new Font("Comic Sans", Font.BOLD, 18));
	   label.setBounds(x, y, width, height);
	   controlJPanel.add(label);
	   return label;
   }
   
   private void setUpButtons()
   {
	   int x = 23; int y = 56; int width = 45; int height = 45;
	   
	   for(int i = 0; i < buttons.length; i++)
	   {
			   if(x > 113)
			   {
				  x = 23;
				  y += 45;
			   }
			       buttons[i] = new JButton(buttonsNames[i]);
				   buttons[i].setBounds(x, y, width, height);
				   if(i != 9 && i < 11)
					   buttons[i].addActionListener(createNumberButtonsAction());
				   controlJPanel.add( buttons[i]);  

			   x += 45;
	   }
	   
	
	   buttons[9].setBounds(23,252, 74, 24);
	   buttons[9].addActionListener(createStartButtonAction());
	   buttons[11].setBounds(100,252,74, 24);
	   buttons[11].addActionListener(createClearButtonAction());
	   buttons[12].setBounds(130, 0, 60, 24);
	   buttons[12].addActionListener(createClockAction());
	   buttons[13].setBounds(-10, 0, 70, 24);
	   buttons[13].addActionListener(createModesButtonsAction());
	   
   }
   
 
   //1-9 actions
   private ActionListener createNumberButtonsAction()
   {
	   ActionListener listener = new ActionListener()
	   {
		   public void actionPerformed(ActionEvent event)
		   {
			   performNumberButtonsAction(event);
		   }
		   
	   };
	   
	   return listener;
	   
   }
   private void performNumberButtonsAction(ActionEvent event)
   {
	    clock.stop();
	    String number = ((JButton)event.getSource()).getText(); 
	   this.updateTime(String.valueOf(number));
   }
   
 
   
   //start action
   private ActionListener createStartButtonAction()
   {
	   ActionListener listener = new ActionListener()
	   {
		   public void actionPerformed(ActionEvent event)
		   {
			   performStartButtonAction(event);
		   }
		   
	   };
	   
	   return listener;
	   
   }
   private void performStartButtonAction(ActionEvent event)
   {
	   if(!clock.isRunning())
	   {
		   String time = this.displayJLabel.getText();
		   int minutes = Integer.parseInt(time.substring(0, 2));
		   int seconds = Integer.parseInt(time.substring(3));
		   clockTimer.setMinutes(minutes);
		   clockTimer.setSeconds(seconds);
		   clockTimer.start();
		   clockTimer.setTextField(this.displayJLabel);
		   this.displayJLabel.setText(clockTimer.toString());
	   }
	  
   }
   
   private ActionListener createClockAction()
   {
	   ActionListener listener = new ActionListener()
	   {
		   public void actionPerformed(ActionEvent event)
		   {
			   performClockAction(event);
		   }
		   
	   };
	   
	   return listener;
	   
   }
   private void performClockAction(ActionEvent event)
   {
	   if(!clock.isRunning())
	   {
		   this.clockTimer.stop();
		   this.displayJLabel.setText("clear");
		   this.timeToDisplay = "";
	   }
   }
   
   private ActionListener createModesButtonsAction()
   {
	   ActionListener listener = new ActionListener()
	   {
		   public void actionPerformed(ActionEvent event)
		   {
			   performModesButtonsAction(event);
		   }
		   
	   };
	   
	   return listener;
	   
   }
   private void performModesButtonsAction(ActionEvent event)
   {
	   clock.stop();
	    Modes mode = new Modes(buttons, controlJPanel, doorJPanel, displayJLabel);
   }
   
   private String formatTime()
   {
 	  String currentTime = timeToDisplay;
 	  
 	  for(int i = currentTime.length(); i < 4; i++)
 	  {
 		  currentTime = "0" + currentTime;//padding
 	  }
 	  
 	  if(currentTime.length() > 4)
 	  {
 		  currentTime = currentTime.substring(0, 4);
 	  }
 	  
 	  return  currentTime;
   }
  
   
   private String fixIncorrectTime(String min, String sec)
   {
	   int minutes = Integer.parseInt(min);
	   int seconds = Integer.parseInt(sec);
	   
	   String minuteString = "";
	   String secondString = "";
	   
	   if(seconds > 59)
	   {
		   minutes += 1;
		   seconds %= 60;
	   }
	   
	   if(minutes > 30)
	   {
		   seconds = 0;
		   minutes = 30;
	   }
	   
	   if(minutes < 10)
	   {
		   minuteString = "0"+minutes;
	   }
	   else
	   {
		   minuteString = "" + minutes;
	   }
	   
	   if(seconds < 10)
	   {
		   secondString  = "0" + seconds;
	   }
	   else
	   {
		   secondString = "" + seconds;
	   }
	  return minuteString + ":" + secondString ;
	 
	   
   }
   
    private void  updateTime(String digit)
    {
	   	 timeToDisplay += digit;
	   	 
	   	 String fourDigitTime = formatTime();
	 
	   	 String minute = fourDigitTime.substring(0, 2);
	   	 String second = fourDigitTime.substring(2);
	   	 
	   	 String correctTime = this.fixIncorrectTime(minute, second);
	   	 
	   	 displayJLabel.setText(correctTime);
    }



   
   //clear action
   private ActionListener createClearButtonAction()
   {
	   ActionListener listener = new ActionListener()
	   {
		   public void actionPerformed(ActionEvent event)
		   {
			   performClearButtonAction(event);
		   }
		    
	   };
	   
	   return listener;
	   
   }
   
   
   private void performClearButtonAction(ActionEvent event)
   {
	   if(!clock.isRunning())
	   {
		   this.clockTimer.stop();
		   this.displayJLabel.setText("clear");
		   this.timeToDisplay = "";
	   }
   }
   
   
    // main method
    public static void main(String[] args) 
    {
    	Microwave app =  new Microwave();
    	app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    } 

}
