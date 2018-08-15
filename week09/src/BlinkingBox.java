import java.awt.Color;
import java.awt.event.*;

import javax.swing.Timer;

import acm.graphics.GRect;

public class BlinkingBox extends GRect {

	private Timer blinkTimer;
	private boolean isOn;
  
	public BlinkingBox(int width, int height, Color color)
	{
		// TO DO: Finish writing the constructor, including setting up
		// the set up of a timer to cause blinking once per second.
		  
		super(width,height);
		blinkTimer = new Timer(1000, new blinkListener());
		addMouseListener(new clickListener());
		isOn=true;
    
		blinkTimer.start();
    
		setFillColor(color);
		setFilled(true);
	}
  
	// TO DO: Edit the blinkListener class as needed
	//        to make the timed blinking work.
  private class blinkListener implements ActionListener
  {
	  @Override
	  public void actionPerformed(ActionEvent arg0) {
		  if(isOn)
		  {
			  setFilled(false);
			  isOn=false;
		  } else {
			  setFilled(true);
			  isOn=true;
		  }
	  } 
  }  
  
	// TO DO: Edit and add to the clickListener class as needed
	//        to make clicking on the box work.
  private class clickListener extends MouseAdapter
  {
	  @Override
	  public void mousePressed(MouseEvent e) {
		  System.out.println("Cube clicked!");
	  } 
  }
  
}
