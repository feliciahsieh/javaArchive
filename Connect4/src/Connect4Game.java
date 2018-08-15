import acm.graphics.*;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Connect4Game {
	/// The constructor's parameter list must stay as it is.
	private GCanvas canvas;
	private myPiece [][] board;
	private boardColumn connect4Column;
	private int totRows=0, totCols=0, totPieces=0;
	private boolean isRedNextTurn = true;
	private GLabel gameMessage;
	private boolean hasWon=false;
	private boolean isGameOver=false;

	private int pieceWidth=0;//Calculated piece of Width

	public final static int xBorder=100;
	public final static int yBorder=50;
	public final static int xPieceBorder=5;
	public final static int yPieceBorder=4;
	public final static int miniWidth=10;
	public String[] message = { "Red's Turn", "Yellow's Turn", "Red Won", "Yellow Won", "Tie, Game Over" };
	
	
	public Connect4Game(int w, int h, GCanvas canv)
	{
		canvas = canv;
		totRows = h;
		totCols = w;
		board = new myPiece[w][h];
		
		int checkPieceWidth  = (canvas.getWidth() -(2*xBorder) - ((w-1)*xPieceBorder) ) / w;
		int checkPieceHeight = (canvas.getHeight() -(2*yBorder) - ((h-1)*yPieceBorder) ) / h;
		pieceWidth = (checkPieceWidth < checkPieceHeight)? checkPieceWidth : checkPieceHeight;
		
		int i, j;
		
		//Draw Board
		GRect background = new GRect(xBorder, 2*yBorder, canvas.getWidth()-(2*xBorder), (h*pieceWidth)+(h-1)*yPieceBorder);
		background.setColor(Color.BLUE);
		background.setFilled(true);
		canvas.add(background);
		
		//Draw Columns
		for(i=0;i<w;i++)
		{
			connect4Column = new boardColumn(i, w, h, pieceWidth);
			connect4Column.addMouseListener(new columnMouseListener());
			canvas.add(connect4Column);
		}
		
		//Draw Empty Pieces (white circles)
		for(i=0;i<w;i++)
			for(j=0;j<h;j++)
				canvas.add((new myPiece(i,j,pieceWidth,Color.WHITE,0)).piece);
		
		//Draw Label
		gameMessage = new GLabel(message[0], 350, 35);//"Red's Turn"
		gameMessage.setFont("Helvetica-24");
		gameMessage.setColor(Color.BLUE);
		canvas.add(gameMessage);
	}
	
	private boolean boardIsFull()
	{// determine if the whole board is full
		return ( totPieces==(totRows*totCols) )? true : false;
	}//boardIsFull
	
	private boolean playDisc(int col)
	{// play a disc of the specified color in the specified column. board[0][], row 0 is TOP row
		boolean isSuccess=false;

		if (totPieces<(totRows*totCols) )
		{
			for(int i=0;i<totRows;i++)
			{
				if(board[col][i]==null)
				{
					board[col][i] = new myPiece(col, totRows-i-1, pieceWidth, (isRedNextTurn)? Color.RED : Color.YELLOW, 0);
					board[col][i].piece.setFillColor(isRedNextTurn? Color.RED : Color.YELLOW);
					canvas.add(board[col][i].piece);
					totPieces++;
					isRedNextTurn = !isRedNextTurn;
					if(isRedNextTurn)
						gameMessage.setLabel(message[0]);//Red's turn
					else
						gameMessage.setLabel(message[1]);//Yellow's turn

					isSuccess=true;
					break;
				}//if(board[col][i]==null
			}//for
		} else
		{//Max pieces played
			gameMessage.setLabel(message[4]);
			canvas.add(gameMessage);
			isGameOver=true;
			System.out.println("Full board");
		}//else (Max Pieces played)
		
		return isSuccess;
	}//playDisc

	public void checkWinner()
	{//CHECK FOR WINNER
		if(!boardIsFull())
		{
			if(totPieces>6)//Winning is possible only after min 7 turns taken
			{
				//Check Winning Horizontally
			outerloopHorizontal:
				if(!hasWon)
				{
					for(int a=0;a<totRows;a++)//y
					{
						for(int b=0;b<totCols-4+1;b++)//x
						{
							//Check if Pieces exists
							if( (board[b][a]!=null) && (board[b+1][a]!=null) && (board[b+2][a]!=null) && (board[b+3][a]!=null) )
							{
								//Check if rows contain 4 in a row
								if( (board[b][a].color==Color.RED && board[b+1][a].color==Color.RED && board[b+2][a].color==Color.RED && board[b+3][a].color==Color.RED) ||
								    (board[b][a].color==Color.YELLOW && board[b+1][a].color==Color.YELLOW && board[b+2][a].color==Color.YELLOW && board[b+3][a].color==Color.YELLOW) )
								{
									hasWon=true;
									System.out.println("b:"+b + " a:" + a +" WINNER-Horizontal!");
									//Draw Winning Circles
									canvas.add(new myPiece(b,   totRows-1-a, pieceWidth, Color.LIGHT_GRAY, miniWidth).piece);
									canvas.add(new myPiece(b+1, totRows-1-a, pieceWidth, Color.LIGHT_GRAY, miniWidth).piece);
									canvas.add(new myPiece(b+2, totRows-1-a, pieceWidth, Color.LIGHT_GRAY, miniWidth).piece);
									canvas.add(new myPiece(b+3, totRows-1-a, pieceWidth, Color.LIGHT_GRAY, miniWidth).piece);
									
									if(isRedNextTurn)
										gameMessage.setLabel(message[3]);
									else
										gameMessage.setLabel(message[2]);
									canvas.add(gameMessage);
									break outerloopHorizontal;
								}
							}//Pieces Exist
						}//for(int b=0...
					}//Check Winning Horizontally
				}//if(!hasWon)
				
				//Check Winning Vertically
			outerloopVertical:
				if(!hasWon)
				{
					for(int b=0;b<totCols;b++)//x
					{
						for(int a=0;a<totRows-4+1;a++)//y
						{
							//Check if Pieces exists
							if( (board[b][a]!=null) && (board[b][a+1]!=null) && (board[b][a+2]!=null) && (board[b][a+3]!=null) )
							{
								//Check if cols contain 4 together
								if( (board[b][a].color==Color.RED && board[b][a+1].color==Color.RED && board[b][a+2].color==Color.RED && board[b][a+3].color==Color.RED) ||
								    (board[b][a].color==Color.YELLOW && board[b][a+1].color==Color.YELLOW && board[b][a+2].color==Color.YELLOW && board[b][a+3].color==Color.YELLOW) )
								{
									hasWon=true;
									System.out.println("a:"+a + " b:" + b +" WINNER-Vertical!");
									//Draw Winning Circles
									canvas.add(new myPiece(b, totRows-1-a,   pieceWidth, Color.LIGHT_GRAY, miniWidth).piece);
									canvas.add(new myPiece(b, totRows-1-a-1, pieceWidth, Color.LIGHT_GRAY, miniWidth).piece);
									canvas.add(new myPiece(b, totRows-1-a-2, pieceWidth, Color.LIGHT_GRAY, miniWidth).piece);
									canvas.add(new myPiece(b, totRows-1-a-3, pieceWidth, Color.LIGHT_GRAY, miniWidth).piece);

									if(isRedNextTurn)
										gameMessage.setLabel(message[3]);
									else
										gameMessage.setLabel(message[2]);
									canvas.add(gameMessage);
									break outerloopVertical;
								}
							}//Pieces exist
						}//for(int b=0...
					}//Check Winning Vertically
				}//if(!hasWon)
				
			outerloopDiagonal1:
				if(!hasWon)
				{//Check Winning Diagonally with Positive Slope
					if( (totCols>3) && (totRows>3) )//No diagonal wins possible until a minimum of 4 rows and 4 cols
					{
						for(int b=0;b<totCols-4+1;b++)//x
						{
							for(int a=0;a<totRows-4+1;a++)//y
							{
								//Check Diagonal with Positive Slope
								//Check if Pieces exists
								if( (board[b][a]!=null) && (board[b+1][a+1]!=null) && (board[b+2][a+2]!=null) && (board[b+3][a+3]!=null) )
								{
									System.out.println("Checking Rows: "+a+" to "+ (a+3));
									System.out.println("Checking Cols: "+b+" to "+ (b+3));
									//Check if cols contain 4 together
									if( (board[b][a].color==Color.RED && board[b+1][a+1].color==Color.RED && board[b+2][a+2].color==Color.RED && board[b+3][a+3].color==Color.RED) ||
									    (board[b][a].color==Color.YELLOW && board[b+1][a+1].color==Color.YELLOW && board[b+2][a+2].color==Color.YELLOW && board[b+3][a+3].color==Color.YELLOW) )
									{
										hasWon=true;
										System.out.println("a:"+a + " b:" + b +" WINNER-Diagonal / ");
										//Draw Winning Circles
										canvas.add(new myPiece(b,   totRows-1-a, pieceWidth, Color.LIGHT_GRAY, miniWidth).piece);
										canvas.add(new myPiece(b+1, totRows-1-a-1, pieceWidth, Color.LIGHT_GRAY, miniWidth).piece);
										canvas.add(new myPiece(b+2, totRows-1-a-2, pieceWidth, Color.LIGHT_GRAY, miniWidth).piece);
										canvas.add(new myPiece(b+3, totRows-1-a-3, pieceWidth, Color.LIGHT_GRAY, miniWidth).piece);

										if(isRedNextTurn)
											gameMessage.setLabel(message[3]);
										else
											gameMessage.setLabel(message[2]);
										canvas.add(gameMessage);
										break outerloopDiagonal1;
									}
								}
							}//for(int a=0...
						}//for(int b=0...
					}//If board is min 4x4
				}//if !hasWon
				
			outerloopDiagonal2:
				if(!hasWon)
				{
					if( (totCols>3) && (totRows>3) )//No diagonal wins possible until a minimum of 4 rows and 4 cols
					{
						//Check Winning Diagonally with Negative Slope
						for(int b=3;b<totCols;b++)//x
						{
							for(int a=0;a<totRows-4+1;a++)//y
							{
								//Check Diagonal with Negative Slope
								//Check if Pieces exists
								if( (board[b][a]!=null) && (board[b-1][a+1]!=null) && (board[b-2][a+2]!=null) && (board[b-3][a+3]!=null) )
								{
									System.out.println("Checking Rows: "+a+" to "+ (a+3));
									System.out.println("Checking Cols: "+b+" to "+ (b-3));
									//Check if cols contain 4 together
									if( (board[b][a].color==Color.RED && board[b-1][a+1].color==Color.RED && board[b-2][a+2].color==Color.RED && board[b-3][a+3].color==Color.RED) ||
									    (board[b][a].color==Color.YELLOW && board[b-1][a+1].color==Color.YELLOW && board[b-2][a+2].color==Color.YELLOW && board[b-3][a+3].color==Color.YELLOW) )
									{
										hasWon=true;
										System.out.println("a:"+a + " b:" + b +" WINNER-Diagonal \\ ");
										//Draw Winning Circles
										canvas.add(new myPiece(b, totRows-1-a,   pieceWidth, Color.LIGHT_GRAY, miniWidth).piece);
										canvas.add(new myPiece(b-1, totRows-1-a-1, pieceWidth, Color.LIGHT_GRAY, miniWidth).piece);
										canvas.add(new myPiece(b-2, totRows-1-a-2, pieceWidth, Color.LIGHT_GRAY, miniWidth).piece);
										canvas.add(new myPiece(b-3, totRows-1-a-3, pieceWidth, Color.LIGHT_GRAY, miniWidth).piece);
										
										if(isRedNextTurn)
											gameMessage.setLabel(message[3]);
										else
											gameMessage.setLabel(message[2]);
										canvas.add(gameMessage);
										break outerloopDiagonal2;
									}
								}//if 4 pieces not null
							}//for(int a=0...
						}//for(int b=0...
					}//if board is 4x4 min
				}//if !hasWon
			}//if totPieces>6
		}//if full board
		else
		{//Max pieces played
			gameMessage.setLabel(message[4]);
			canvas.add(gameMessage);
			isGameOver=true;
			System.out.println("Full board");
		}
	}//checkWinner

	class columnMouseListener extends MouseAdapter
	{
		@Override
		public void mousePressed(MouseEvent e)
		{
			if(!hasWon && !isGameOver)
			{
				if(playDisc(((boardColumn) e.getSource()).col))
					checkWinner();
			}
		}//mousePressed

		public void mouseEntered(MouseEvent e) {
			((GRect) e.getSource()).setFilled(true);
		}//mouseEntered

		public void mouseExited(MouseEvent e) {
			((GRect) e.getSource()).setFilled(false);
		}//mouseExited
	}//columnMouseListener
	
}//Connect4Game
