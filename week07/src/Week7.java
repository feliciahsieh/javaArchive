import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import acm.graphics.*;
import acm.program.GraphicsProgram;

@SuppressWarnings("serial")
public class Week7 extends GraphicsProgram {

	public void init()
	{
		resize(800, 600);
	}
	
	private void Report(String[][] data)
	{
		println("2D array has " + data.length + " rows and " + data[0].length + " columns.");
		
		for (int i=0;i<data.length;i++)
			for(int j=0;j<data[0].length;j++)
			{
				int temp;
				temp = data[i][j].length();
				if (temp > 5)
					println("String [" + i + "][" + j +"] > 5: " + data[i][j]);
			}
	}
	
	//main()
	public void run()
	{
		class CircleKeyListener extends KeyAdapter
		{
			@Override
			public void keyPressed(KeyEvent e) {
				char whichKey = e.getKeyChar();

				if (whichKey == 'w')
					println("w pressed");
				else if (whichKey == 'z')
					println("z pressed");
				else if (whichKey == 'a')
					println("a pressed");
				else if (whichKey == 's')
					println("s pressed");
				else if (whichKey == ' ')
				{
					setBackground(Color.LIGHT_GRAY);
					println("<space> pressed");
				}
			}
		}
		
		class CircleMouseListener extends MouseAdapter
		{
			@Override
			public void mousePressed(MouseEvent e) {
				//println("Circle clicked!");
				
				((GOval) e.getSource()).sendToFront();
				((GOval) e.getSource()).setColor(Color.GREEN);
				((GOval) e.getSource()).setFillColor(Color.GREEN);
			}
		}

		/*******************************************************/
		
		//Task 1: 2d Array prints if > 5 chars long
		String[][] myArray = new String [][] {
			{ "Hello",   ", "},
			{ "there!",   "The world"},
			{ "is ",     "round,"},
			{ "not",     "square,"},
			{ "cubic",   "or a pentagonal prism"}
		};
		
		Report(myArray);
		
		/*******************************************************/

		//Task 2: GOval changes color on events
		GOval[][] myCircles = new GOval[12][16];
		int i,j;
		
		for (i=0;i<12;i++)
			for(j=0;j<16;j++)
			{
				myCircles[i][j] = new GOval(50*j, 50*i, 50, 50);

				if( (i%2) == 0 )
				{
					myCircles[i][j].setColor(Color.RED);
					myCircles[i][j].setFillColor(Color.RED);
				} else
				{
					myCircles[i][j].setColor(Color.BLUE);
					myCircles[i][j].setFillColor(Color.BLUE);
				}
				myCircles[i][j].setFilled(true);
				myCircles[i][j].addMouseListener(new CircleMouseListener());
				
				add(myCircles[i][j]);
			}
		getGCanvas().addKeyListener(new CircleKeyListener());

	}
}

/*
  ArrayList<Dragon> dragonClan = new ArrayList<Dragon>();
  dragonClan.add(new Dragon("Puff", 100, 30, "China", 8));
  dragonClan.add(new Dragon("Fire Dragon", 75, 20, "Volcano", 8));
  dragonClan.add(new Dragon("Golden Dragon", 21, 5, "Africa", 8));
  dragonClan.add(new Dragon("Water Dragon", 62, 15, "Hawaii", 8));
  dragonClan.add(new Dragon("Earth Dragon", 43, 25, "Costa Rica", 8));
  
  for(Dragon d : dragonClan)
  {
  System.out.println(d);
  System.out.println("My home is " + d.getHome());
System.out.println("My diet is " + Dragon.diet());
//System.out.println("Dragon is " + Dragon.getAge() + " years old");
System.out.println("Is my fire power even? " + Dragon.isEven(d.firePower) );
}
}
*/
