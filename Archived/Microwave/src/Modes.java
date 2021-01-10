import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.print.DocFlavor.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Modes {
	
	private JButton[] list;
	
	private String imageName = "";
	
	private JPanel Jdoor;
	
	private String[] cookingTime = {
		"02:00",
		"00:45",
		"05:00",
		"30:00",
		"30:00"
	};
	
	private JButton popcornJButton, pizzaJButton, fishJButton, 
	 chickenJButton, forgeJButton, backJButton;
 
	 private JButton[] buttons = {popcornJButton, pizzaJButton, fishJButton, 
			 chickenJButton, forgeJButton, backJButton};
	
	 private String[] buttonsNames = {"popcorn", "pizza", "fish", "chicken", "forge", "back"};
	 
	 private JLabel cooktime;
	
	public Modes(JButton[] hides, JPanel controlPanel, JPanel door, JLabel displayJLabel) {
		
		this.cooktime = displayJLabel;
		
		this.Jdoor = door;
		
		this.list = hides;
		
		for(JButton i:hides) {
			i.setVisible(false);
		}
		
		int y = 50; int height = 30;
		   
		   for(int i = 0; i < buttons.length; i++)
		   {
			   buttons[i] = new JButton(buttonsNames[i]);
			   buttons[i].setBounds(50, y, 100, height);
			   if(i != 6)
				   buttons[i].addActionListener(createImageAction());
			   controlPanel.add(buttons[i]);
			   y += 35;
		   }
	}
	
	private void showImage(String imageAdress) {
		
		imageName = imageAdress;
		
		JLabel image = new JLabel();
		ImageIcon food = new ImageIcon("image/" + imageName + ".jpg");
		Image scaledFood = food.getImage().getScaledInstance(Jdoor.getWidth(), Jdoor.getHeight(), Image.SCALE_AREA_AVERAGING);
		image.setIcon(new ImageIcon(scaledFood));
		image.setBounds(0, 0, Jdoor.getWidth(), Jdoor.getHeight());
		Jdoor.setLayout(null);
		
		Jdoor.removeAll();
		
		Jdoor.add(image);
		
		Jdoor.setBackground(Color.WHITE);
		
		Jdoor.repaint();
		
		buttons[5].setText("confirm");
	}
	
	private ActionListener createImageAction()
    {
	    ActionListener listener = new ActionListener()
	    {
 		   public void actionPerformed(ActionEvent event)
 		   {
	 		   perforMenuAction(event);
		   }
		   
	    };
	   
	    return listener;
	   
   }
   private void perforMenuAction(ActionEvent event)
   {
	   String selection = ((JButton)event.getSource()).getText(); 
	   if(selection.equals("back") || selection.equals("confirm")) {
		   closeMenu();
		   return;
	   }
	   showImage(selection);
   }
   
   private void closeMenu() {
	   
	   int index;
	   
	   if(imageName != ""){
		   for(index = 0; index < buttonsNames.length - 1; index++) {
			   if(imageName.equals(buttonsNames[index])) break;
		   }
		   cooktime.setText(cookingTime[index]);
	   }
	   
	   for(JButton i:buttons) {
			i.setVisible(false);
		}
	   
	   for(JButton i:list) {
			i.setVisible(true);
		}
   }
}
