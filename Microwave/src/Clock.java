import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JLabel;
import javax.swing.Timer;

public class Clock extends ClockTimer 
{
	private Timer clockTimer;
    private JLabel timeLabel = new JLabel();
    private boolean isRunning = false;
	
	public Clock(JLabel label)
	{
		super(0, 0,true);
		setClockTimer();
		timeLabel = label;
		start();
	}

	
	public void showTime()
	{
	     LocalTime time = LocalTime.now(); 
	     DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm:ss");
		 System.out.println(time.format(formatter));
		 timeLabel.setText(time.format(formatter));
	}
	
	
	private void setClockTimer()
	{
		ActionListener timeActionListener = 
	 			  new ActionListener()
	 			  {
	 		  			public void actionPerformed(ActionEvent event)
	 		  			{
	 		  				showTime();
	 		  			}
	 			  };
	 			  
	 			 clockTimer = new Timer(1000, timeActionListener);
	}
	
	
	public void start()
	{
		clockTimer.start();
		isRunning = true;
	}
	
	
	public void stop()
	{
		clockTimer.stop();
		isRunning = false;
	}
	
	
	public Boolean isRunning()
	{
		return isRunning;
	}
	

}