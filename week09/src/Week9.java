import acm.graphics.GOval;
import acm.program.GraphicsProgram;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.Timer;

@SuppressWarnings("serial")
public class Week9 extends GraphicsProgram {
	
	public void init()
	{
		//setFont("monospaced-plain-32");
		resize(800, 600);
	}

	public void run()
	{
		BlinkingBox b = new BlinkingBox(100,100,Color.BLUE);
		add(b,100,100);
		
		pause(500);
		BlinkingBox c = new BlinkingBox(100,100,Color.RED);
		add(c,300,100);
		
		/*
		  this.setBackground(Color.darkGray);

		  PlayingCard myCard1 = new PlayingCard();
		  add(myCard1);
		  //2
		  PlayingCard myCard2 = new PlayingCard();
		  add(myCard2);
		  //3
		  PlayingCard myCard3 = new PlayingCard();
		  add(myCard3);
		  //4
		  PlayingCard myCard4 = new PlayingCard();
		  add(myCard4);
		  //5
		  PlayingCard myCard5 = new PlayingCard();
		  add(myCard5);
		  //6
		  PlayingCard myCard6 = new PlayingCard();
		  add(myCard6);
		  //7
		  PlayingCard myCard7 = new PlayingCard();
		  add(myCard7);
		  //8
		  PlayingCard myCard8 = new PlayingCard();
		  add(myCard8);
		  //9
		  PlayingCard myCard9 = new PlayingCard();
		  add(myCard9);
		  //10
		  PlayingCard myCard10 = new PlayingCard();
		  add(myCard10);
		*/
		
		/*
		  Pizza p = new Pizza();
		  add(p,100,100);
		  System.out.println(p);

		  Pizza p2 = new Pizza("peppers",15,false);
		  add(p2,350,100);
		  System.out.println(p2);
		  pause(500);
		  p2.SwitchSauce();
		  System.out.println(p2);
		*/
	}
	
}
