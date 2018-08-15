import java.awt.Color;

import acm.program.GraphicsProgram;
import acm.graphics.*;

@SuppressWarnings("serial")
public class Week2 extends GraphicsProgram {
	public void init()
	{
		int maxWidth=800, maxHeight=600;
		resize(maxWidth, maxHeight);
	}
	public void run()
	{
		/* Task 1: ax2 + bx + c = 0.
		   Ask the user for the three coefficients (a, b, c), then report the solution(s).
		   (The function to take the square root is Math.sqrt()) */
		
		println("Task 1");
		int a = readInt("Enter a: ");
		int b = readInt("Enter b: ");
		int c = readInt("Enter c: ");

		double numerator1, numerator2, denominator;
        
		numerator1 = (-b) + Math.sqrt(b*b-(4*a*c) );
		numerator2 = (-b) - Math.sqrt(b*b-(4*a*c) );
		denominator = 2*a;
        
		println("Answer1 = " + numerator1/denominator);
		println("Answer2 = " + numerator2/denominator);
        
		//Display an image of the standard "male" and "female" symbols side by side: 
		println("Task 2");
		GImage imgFemale = new GImage("female.jpeg");
		GImage imgMale = new GImage("male.jpg");
		add(imgFemale,200,50);
		add(imgMale,200+imgFemale.getWidth(),50);

		println("Task 3");
		//Display a simple hierarchy diagram showing the relationship between different categories of things, something like this:
		//Draw boxes
		int centerx=350, centery=300;
		GRoundRect h1 = new GRoundRect(centerx, centery, 100, 50);
		GRoundRect h2 = new GRoundRect(centerx-200, centery+150, 100, 50);
		GRoundRect h3 = new GRoundRect(centerx,     centery+150, 100, 50);
		GRoundRect h4 = new GRoundRect(centerx+200, centery+150, 100, 50);
		h1.setColor(Color.BLUE);
		h2.setColor(Color.BLUE);
		h3.setColor(Color.BLUE);
		h4.setColor(Color.BLUE);
		add(h1);
		add(h2);
		add(h3);
		add(h4);
		
		//Draw Lines
		int offsetx=50, offsety=50;
		GLine l1 = new GLine(centerx+offsetx,centery+offsety,centerx-200+offsetx,centery+150);
		GLine l2 = new GLine(centerx+offsetx,centery+offsety,centerx+offsetx,centery+150);
		GLine l3 = new GLine(centerx+offsetx,centery+offsety,centerx+200+offsetx,centery+150);
		l1.setColor(Color.ORANGE);
		l2.setColor(Color.CYAN);
		l3.setColor(Color.RED);
		add(l1);
		add(l2);
		add(l3);
		
		//Draw Labels
		int offsetytext=30, offsetxtext=15;     
		GLabel label1= new GLabel("Oceans",centerx+offsetxtext,centery+offsetytext);
		GLabel label2= new GLabel("Atlantic",centerx-200+offsetxtext,centery+150+offsetytext);
		GLabel label3= new GLabel("Pacific",centerx+offsetxtext,centery+150+offsetytext);
		GLabel label4= new GLabel("Indian",centerx+200+offsetxtext,centery+150+offsetytext);
		label1.setFont("monospaced-plain-16");
		label2.setFont("monospaced-plain-16");
		label3.setFont("monospaced-plain-16");
		label4.setFont("monospaced-plain-16");
		add(label1);
		add(label2);
		add(label3);
		add(label4);
		
		//Task 4
		println("Task 4");
		GImage imgDonatello = new GImage("ninjaturtle.gif");
		double xpos=5, ypos=10;
		add(imgDonatello, 0, 0);
		double widthTurtle=imgDonatello.getWidth();
		double heightTurtle=imgDonatello.getHeight();
		int maxWidth=800, maxHeight=600;
		while(true)
		{     
			imgDonatello.move(xpos,ypos);
			if(imgDonatello.getLocation().getX()<0 || imgDonatello.getLocation().getX()>(maxWidth-widthTurtle) )
			{
				xpos = -xpos;
			}
			if(imgDonatello.getLocation().getY()<0 || imgDonatello.getLocation().getY()>(maxHeight-heightTurtle) )
			{
				ypos = -ypos;
			}

			pause(50);
			//println("x:" + imgDonatello.getLocation().getX() +"  y:" + imgDonatello.getLocation().getY());
		}

	}
}
