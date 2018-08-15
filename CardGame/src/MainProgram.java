//import java.io.*;

//import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

//import com.sun.xml.internal.rngom.parse.compact.EOFException;

import acm.graphics.GCanvas;
import acm.program.GraphicsProgram;

@SuppressWarnings("serial")
public class MainProgram extends GraphicsProgram
{
	private ArrayList<Card> deck = new ArrayList<Card>();    

	public void init()
	{
		resize(800, 400);
	}
    
	public void run()
	{
		// TO DO: Read the lines from the cards.txt file
		//        Based on their contents, create and position
		//        new Card objects in the appropriate locations.
		
		int lineRows=0;
		int lineCount=0;
		int totalCards=0;
		
		try
		{
			GCanvas myCanvas = getGCanvas();   //Creates a GCanvas component so that addMouseListener could be applied to it.
			myCanvas.addMouseListener(this);
			
			BufferedReader buffer = new BufferedReader(new FileReader("cards.txt"));
			int c = 0;
			String colorString;
			int numInt;
			while(c != -1)
			{
				//Read Color
				c = buffer.read();
				
				if ( !(c == 'R' || c=='G' || c=='M' || c=='Y') ) {
					throw new UnsupportedOperationException();
				}
				
				colorString = String.valueOf((char)c);
				
				//Read Number
				c = buffer.read();
				if( (c<'1') || (c>'9') )
					throw new IndexOutOfBoundsException();
				numInt = c - '0';
				
				//Create Card
				deck.add(totalCards, new Card(numInt,colorString));
				add(deck.get(totalCards),lineCount*50,lineRows*70);
				totalCards++;

				lineCount++;

				//Read Space
				c = buffer.read();
				
				if(c=='\n')
				{
					System.out.println("Detected return character");
					lineRows++;
					lineCount=0;
				}
			}//while

			buffer.close();
			
		} catch (IOException e) {
			e.printStackTrace();
			println("IO problem: " + e);    
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
			println("Invalid Card Number: " + e);            
		} catch (UnsupportedOperationException e) {
			e.printStackTrace();
			println("Invalid Card Color: " + e);    
		}
		
		//**Connect4***************************************************
		
		int myHeight = 5;//row
		int myWidth  = 10;//column
		Connect4Game myGame = new Connect4Game(myHeight, myWidth);
		println("Connect Four Game\n" + myGame);
		
		//Test if Column is Full
		for(int i=0;i<myWidth;i++)
		{
			if(myGame.ColumnIsFull(i))
				println("Main: Col: " + i + " is FULL");
			else
				println("Main: Col: " + i + " is Not Full");
		}
		
		if(myGame.boardIsFull())
			println("Board is FULL");
		else
			println("Board is NOT Full");
		
		myGame.playDisc(0, true);
		println(myGame);
		myGame.playDisc(1, false);
		println(myGame);
		myGame.playDisc(1, true);
		println(myGame);
		myGame.playDisc(0, false);
		println(myGame);
		myGame.playDisc(1, true);
		println(myGame);
		myGame.playDisc(0, false);
		println(myGame);
		myGame.playDisc(5, true);
		println(myGame);
		myGame.playDisc(5, false);
		println(myGame);
		myGame.playDisc(4, true);
		println(myGame);
		myGame.playDisc(3, false);
		println(myGame);
		//*****************************************************
	}//run

	@Override 
	public void mousePressed(MouseEvent e) {
		System.out.println("Canvas Mouse Pressed");
		
		NamedRunnable greetings = new NamedRunnable("Felicia");
		Thread greetingsThread = new Thread(greetings);
		greetingsThread.start();
	}//MousePressed
	
	//THREAD
	public class NamedRunnable implements Runnable
	{
		private String name;  // The name of this Runnable.
		
		public NamedRunnable(String name)
		{  // Constructor gives name to object.
			this.name = name;
		}
		
		public void run()
		{  // The run method prints a message to standard output.
			System.out.println("Greetings from runnable '" + name +"'!");
			
			Card card1, card2, tempcard;
			int card1Index=0, card2Index=0;

			boolean foundCard1=false, foundCard2=false;
			
			try {
				Thread.sleep(250);
			} catch (InterruptedException e) {
				e.printStackTrace();
				// handle the exception...        
				// For example consider calling Thread.currentThread().interrupt(); here.
			}
			
			for(int d=0;d<deck.size();d++)
			{
				//Find FaceUps
				tempcard = deck.get(d);
				if(!foundCard1)
				{
					if(tempcard.isFaceUp())
					{
						card1Index = d;
						foundCard1 = true;
					}
				} else
				{
					if(tempcard.isFaceUp())
					{
						card2Index = d;
						foundCard2 = true;
						break;
					}
				}
			}//for
			
			if(foundCard1 && foundCard2)
			{
				card1 = deck.get(card1Index);
				card2 = deck.get(card2Index);
				if(card1.sameColor(card2) && card1.adjacentNumber(card2))
				{
					remove(card1);
					remove(card2);
					deck.remove(card1);
					deck.remove(card2);
					Card.qtyFaceUp = Card.qtyFaceUp - 2;
				}//if(card.sameColor(card2)...
			}//if(foundCard1 && foundCard2)
			
		}//run
	}//NamedRunnable
}//MainProgram
