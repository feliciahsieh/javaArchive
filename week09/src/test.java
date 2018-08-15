import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.Timer;

import acm.graphics.GOval;
import acm.program.GraphicsProgram;

@SuppressWarnings("serial")
public class test extends GraphicsProgram {
	private Random r = new Random();
	
	public void init()
	{
		resize(800, 600);
		//setFont("monospace-plain-32");
	}
	public void run()
	{
		Timer faster = new Timer(1000, new CircleGenerator(Color.BLUE));
		Timer slower = new Timer(3000, new CircleGenerator(Color.RED));
		
		faster.start();
		slower.start();
	}
	
	private class CircleGenerator implements ActionListener
	{
		private Color circleColor;
		
		public CircleGenerator(Color desiredColor)
		{
			circleColor = desiredColor;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			GOval circle = new GOval(r.nextInt(getWidth()-75), r.nextInt(getHeight()-75), 75, 75);
			circle.setFillColor(circleColor);
			circle.setFilled(true);
			add(circle);
		}
	}
}
