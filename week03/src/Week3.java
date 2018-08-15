import java.awt.Color;

import acm.program.GraphicsProgram;
import acm.graphics.*;

@SuppressWarnings("serial")
public class Week3 extends GraphicsProgram {
	
	public void init()
	{
		int maxWidth=800, maxHeight=600;
		resize(maxWidth, maxHeight);
	}
	public void run()
	{
		int nRows=36;
		int maxWidth=800;
		int maxHeight=600;
		int rectWidth=20;
		int rectHeight=10;
		int x;
		int y;
		GRect box;
		
		//Task1: Draw a pyramid consisting of bricks arranged in horizontal rows, so that the number of bricks in each row decreases by one as you move up the pyramid Ñ like this, except fill the bricks with some pattern of colors (at least two different colors): 
		x=maxWidth/2 - (rectWidth/2);
		y=(maxHeight-nRows*rectHeight)/2;
		for(int j=1;j<=nRows;j++)
		{
			//Calc horizontal start position
			for(int i=1;i<=j;i++)
			{
				//Draw boxes
				box = new GRect(x, y, rectWidth, rectHeight);
				box.setFilled(true);
				if(i%2==0)
				{
					box.setFillColor(Color.PINK);

				} else
				{
					if (j%2==0)
						box.setFillColor(Color.LIGHT_GRAY);
					else
						box.setFillColor(Color.RED);
				}
				add(box);
				x=x+rectWidth;
			}
			x=(maxWidth-(j*rectWidth) ) / 2 - (rectWidth/2);
			y=y+rectHeight;
		}
		
		//Task 2: Clicker
		println("\nTask 2: Clicker\n");
		Clicker myClicker = new Clicker(0);
		System.out.println("Clicker created.    Count = " + myClicker.getCount());
		myClicker.click();
		System.out.println("Clicked Once");
		myClicker.click();
		System.out.println("Clicked Twice");
		System.out.println("Clicked 2 times.    Count = " + myClicker.getCount());
		myClicker.reset();
		System.out.println("Clicker reset:      Count = " + myClicker.getCount());

		//Task 3: Height
		println("\nTask 3: Height\n");
		Height myHeight = new Height(5,2.0);
		System.out.println("Height created.     Height = " + myHeight.toString());
		myHeight.setFeetAndInches(6,2.0);
		System.out.println("Grew 1 ft taller.   Height = " + myHeight.toString());
		System.out.println("Total height (in) = " + myHeight.getTotalInches());
		System.out.println("Total height (cm) = " + myHeight.getCentimeters());

		System.out.println("Setting 3' 14 (greater than 12)");
		myHeight.setFeetAndInches(3,14.0);
		System.out.println("My new height     = " + myHeight.toString());
		

	}//Public void run()
}//Public class Week3
