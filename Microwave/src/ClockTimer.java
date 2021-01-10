import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.Timer;

public class ClockTimer 
{
	private int seconds, minutes; // 0-59 only
	
	private Timer clockTimer;
	
	private boolean isTickUp;
	
	private boolean isRun;
	
	private JLabel whereToPutTheTime;
	
	public ClockTimer(int mins, int secs, boolean tick) 
	{
		setMinutes(mins);
		setSeconds(secs);
		isTickUp = tick;
		setUpClockTimer();
	}
	
	public void setTextField(JLabel field) {
		whereToPutTheTime = field;
	}
	
	public int getSeconds() {
		return seconds;
	}
	public void setSeconds(int sec) {
		seconds = sec;
	}
	
	public int getMinutes() {
		return minutes;
	}
	
	public void setMinutes(int min) {
		minutes = min;
	}
	public Boolean isRunning() {
		return isRun;
	}
	
	
	public String toString()
	{
		String min = "";
		if(minutes >= 0 && minutes < 10)
			min = "0" + minutes;
		else
			min = Integer.toString(minutes);
		String sec = "";
		if(seconds >= 0 && seconds < 10)
			sec = "0" + seconds;
		else
			sec = Integer.toString(seconds);
		return min + ":" + sec; 
	}
	
	
	

	private void setUpClockTimer()
	   {
	 	  ActionListener timeActionListener = 
			  new ActionListener()
			  {
		  			public void actionPerformed(ActionEvent event)
		  			{
		  				if(isTickUp)
		  					tickUp(event);
		  				else
		  					tickDown(event);
		  			}
			  };
	 			  
			  clockTimer = new Timer(1000, timeActionListener);
	 	  
	   }
	
	public void start()
	{
		clockTimer.start();
		isRun = true;
	}
	
	public void stop()
	{
		clockTimer.stop();
		isRun = false;
	}
	
	private void tickUp(ActionEvent event) {
		
	}
	
	private void tickDown(ActionEvent event) {
		switch(seconds) {
			case 0:
				switch(minutes) {
					case 0:
						stop();
						whereToPutTheTime.setText("done!");
						return;
					default:
						minutes--;
				}
				seconds = 59;
				break;
			default:
				seconds--;
		}
		whereToPutTheTime.setText(this.toString());
	}
}
