import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import acm.graphics.*;

public class Card extends GCompound {
	// Arrays that hold color information for your convenience
	final static String [] letters = { "R", "G", "M", "Y" };
	final static Color [] colors   = { Color.RED, Color.GREEN, Color.MAGENTA, Color.YELLOW };
	
	// Private variables for the primary properties of a card
	private int cardNumber;
	private Color cardColor;
	
	// TO DO: Add private variables to store the 
	//        graphical components of a card
	
	private GRect cardGraphic;
	private GLabel cardLabel;
	private boolean isFaceDown=true;
	
	static private int cardWidth=40;
	static private int cardHeight=60;
	static int qtyFaceUp=0;
	
	public Card(int num, String colorLetter)
	{
		System.out.println(num+" "+colorLetter);
		this.cardNumber = num;
		
		// TO DO: Implement the constructor
		cardGraphic = new GRect(cardWidth, cardHeight);
		
		cardGraphic.setFillColor(Color.WHITE);
		cardGraphic.setFilled(true);

		if(colorLetter.equals("R"))
		{
			this.cardColor = Color.RED;
		}
		else if(colorLetter.equals("G"))
		{
			this.cardColor = Color.GREEN;
		}
		else if(colorLetter.equals("M"))
		{
			this.cardColor = Color.MAGENTA;
		}
		else if(colorLetter.equals("Y"))
		{
			this.cardColor = Color.YELLOW;
		}
		else
		{
			System.out.println("Unknown color sent:" + colorLetter);
		}
		
		add(cardGraphic,100,25);
		
		// For reference -- a command to make a font:
		cardLabel = new GLabel(num+"");
		Font myFont = new Font("Serif", Font.BOLD, 24);
		cardLabel.setFont(myFont);
		cardLabel.setVisible(false);

		add(cardLabel,115,65);
		
		this.addMouseListener(new CardMouseListener());
	}
	
	public void flip()
	{
		// TO DO: Implement the flip() method.
		//        A card that is face up should have its number
		//         visible and be filled in with color.
		//        A card that is face down should just look like
		//         an empty rectangle.
		
		if( ((qtyFaceUp<3) && !isFaceDown) || ((qtyFaceUp<2) && isFaceDown) )
		{
			isFaceDown = !isFaceDown;
			
			if(isFaceDown)
			{
				cardLabel.setVisible(false);
				
				cardGraphic.setFillColor(Color.WHITE);
				cardGraphic.setFilled(false);
				qtyFaceUp--;
			}
			else
			{
				cardLabel.setVisible(true);
				
				cardGraphic.setFillColor(cardColor);
				cardGraphic.setFilled(true);
				qtyFaceUp++;
			}
		}

	}
	
	public boolean sameColor(Card otherCard)
	{
		// TO DO: Implement this method
		if(this.cardColor==otherCard.cardColor)
			return true;
		else
			return false;
	}
	
	public boolean adjacentNumber(Card otherCard)
	{
		// TO DO: Implement this method
		if( (this.cardNumber-otherCard.cardNumber==1) || 
		    (this.cardNumber-otherCard.cardNumber==-1) )
		{
			System.out.println("adj# Matched "+this.cardNumber+ " == "+ otherCard.cardNumber);
			return true;
		}
		else
		{
			return false;
		}
	}//adjacentNumber

	public boolean isFaceUp()
	{
		if(this.isFaceDown)
			return false;
		else
			return true;
	}
	
	public int getCardNumber()
	{
		return this.cardNumber;
	}
	
	// TO DO: Create an inner class for the purpose of 
	// making mouse interactions work as specified.
	public class CardMouseListener extends MouseAdapter
	{
		@Override
		public void mousePressed(MouseEvent e) {
			flip();
		}
	}//CardMouseListener

}//Card
