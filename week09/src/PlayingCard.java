import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import acm.graphics.GCompound;
import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GRoundRect;
import java.awt.Color;

import java.util.Random;

public class PlayingCard extends GCompound {
	static private int cardCount;

	private String rank;
	private String suit;
	private GRoundRect cardShape;
	private GLabel cardRankLabel;
	private GImage cardSuitImage;
	private boolean isFaceUp=true;
	
	static private String[] rankName = {"Ace ", "Two ", "Three ", "Four ", "Five ", "Six ", "Seven ", "Eight ", "Nine ", "Ten ", "Jack ", "Queen ", "King "};
	static private String[] suitName = {" Clubs", " Diamonds", " Hearts", " Spades", " dummySuitName" };
	static private int cardHeight=200;
	static private int cardWidth=100;
	
	static private String[] rankList = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
	static private String[] suitList = {"C", "D", "H", "S"};
	
	PlayingCard()
	{
		this.isFaceUp = true;
		
		PlayingCard.cardCount++;
		
		Random rand = new Random();
		
		//Set Random Card Rank
		this.rank = PlayingCard.rankList[rand.nextInt(13)];
		
		//Set Random Card Suit
		this.suit = PlayingCard.suitList[rand.nextInt(4)];
		
		drawCard();
		this.addMouseListener(new CardMouseListener());
	}
	
	PlayingCard(String initialRank, String initialSuit)
	{
		boolean goodRank=false, goodSuit=false;
		
		this.isFaceUp = true;
		PlayingCard.cardCount++;

		for(String s : PlayingCard.rankList)
		{
			if(s.equals(initialRank))
				goodRank=true;
			else
				System.out.println("Cannot create Card. Bad Rank");
		}
		
		for(String s : PlayingCard.suitList)
		{
			if(s.equals(initialSuit))
				goodSuit=true;
			else
				System.out.println("Cannot create Card. Bad Suit");
		}
		
		if(goodRank&&goodSuit)
		{
			this.rank = initialRank;
			this.suit = initialSuit;
		}else
		{
			this.rank = "2";
			this.suit = "C";
			drawCard();
			this.addMouseListener(new CardMouseListener());
		}
		
	} //PlayingCard(rank, suit) constructor
	
	public String toString()
	{//Output "[rank] of [suit]"
		String rankText="", suitText="";
		
		//System.out.println("RANK:" + this.rank + "    SUIT:" + this.suit);
		for(int i=0;i<PlayingCard.rankList.length;i++)
		{
			if(PlayingCard.rankList[i].equals(this.rank))
			{
				rankText = PlayingCard.rankName[i];
				break;
			}
		}
		
		for(int i=0;i<PlayingCard.suitList.length;i++)
		{
			if(PlayingCard.suitList[i].equals(this.suit))
			{
				suitText = PlayingCard.suitName[i];
				break;
			}
		}
		
		return rankText + "of" + suitText + (this.isFaceUp ? " and is Face Up": " and is Face Down");
		
	}//String toString()
	
	public String getRank()
	{
		return this.rank;
	}//String getRank()
	
	public String getSuit()
	{
		return this.suit;
	}//String getSuit()
	
	public boolean isFaceUp()
	{
		return this.isFaceUp;
	}//boolean isFaceUp()
	
	public void flip()
	{
		this.isFaceUp = !this.isFaceUp;
		
	}//void flip()
	
	public boolean equals(PlayingCard other)
	{
		boolean result=false;
		
		//Must be valid card
		if( (other.rank.equals("A") || other.rank.equals("2") || other.rank.equals("3") || other.rank.equals("4")
		     || other.rank.equals("5") || other.rank.equals("6") || other.rank.equals("7") 
		     || other.rank.equals("8") || other.rank.equals("9") || other.rank.equals("10")
		     || other.rank.equals("J") || other.rank.equals("Q") || other.rank.equals("K") ) 
		    && (other.suit.equals("C") || other.suit.equals("D") || other.suit.equals("H") || other.suit.equals("S") ) )
		{
			if(other.rank.equals(this.rank) && other.suit.equals(this.suit))
				result = true;
		} else
			System.out.println("Invalid Card");

		return result;
		
	}//equals(PlayingCard)
	
	public void drawCard()
	{
		drawCardBackground();
		drawCardFace();
	}

	public void drawCardBackground()
	{
		int x,y;
		
		//Calc position of 10 cards (= 5 columns x 2 rows)
		if(PlayingCard.cardCount<6)
		{
			x=50+150*(PlayingCard.cardCount-1);
			y=100;
		}
		else
		{
			x=50+150*(PlayingCard.cardCount-6);
			y=350;
		}
		
		this.cardShape = new GRoundRect(x, y, cardWidth, cardHeight);
		if(this.isFaceUp)
			this.cardShape.setColor(Color.LIGHT_GRAY);
		else
			this.cardShape.setColor(Color.MAGENTA);
		this.cardShape.setFilled(true);
		
		add(this.cardShape);
		
	}//drawCardBackground
	
	public void drawCardFace()
	{
		int x,y;
		
		if(PlayingCard.cardCount<6)
		{
			x=50+150*(PlayingCard.cardCount-1);
			//System.out.println("Card:" + PlayingCard.cardCount  + "  X:"+x);
			y=100;
		}
		else
		{
			x=50+150*(PlayingCard.cardCount-6);
			//System.out.println("Card:" + PlayingCard.cardCount  + "  X:"+x);
			y=350;
		}

		//Draw Rank
		this.cardRankLabel = new GLabel(this.rank,x+32,y+50);
		this.cardRankLabel.setFont("monospaced-plain-36");
		add(this.cardRankLabel);
		
		//Draw Suit
		if(this.suit.equals("C"))
		{
			this.cardSuitImage = new GImage("club.png");
		} else if(this.suit.equals("D"))
		{
			this.cardSuitImage = new GImage("diamond.jpg");
		} else if(this.suit.equals("H"))
		{
			this.cardSuitImage = new GImage("heart.jpg");     
		} else if(this.suit.equals("S"))
		{
			this.cardSuitImage = new GImage("spade.gif");    
		} else
			this.cardSuitImage = new GImage("club.png");
		
		add(this.cardSuitImage,x+32,y+100);
	}//drawCardFace()
	
	public class CardMouseListener extends MouseAdapter
	{
		@Override
		public void mousePressed(MouseEvent e) {
			System.out.println("Card flipped!");
			((PlayingCard) e.getSource()).flip();
			
			if(isFaceUp) //Was Face Down
			{
				add(((PlayingCard) e.getSource()).cardSuitImage);
				add(((PlayingCard) e.getSource()).cardRankLabel);
				((PlayingCard) e.getSource()).cardShape.setColor(Color.LIGHT_GRAY);
			}
			else//Was Face Up
			{
				remove(((PlayingCard) e.getSource()).cardSuitImage);
				remove(((PlayingCard) e.getSource()).cardRankLabel);
				((PlayingCard) e.getSource()).cardShape.setColor(Color.MAGENTA);
			}
		}

	}


	
} //class PlayingCard


