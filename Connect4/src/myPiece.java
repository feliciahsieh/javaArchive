import java.awt.Color;

import acm.graphics.*;

public class myPiece
{
	protected int x;//x board coordinate
	protected int y;//y board coordinate
	protected int gx;//x pixel coordinate
	protected int gy;//y pixel coordinate
	protected Color color;//piece color
	protected GOval piece;
	
	public myPiece(int pieceX, int pieceY, int pieceWidth, Color pieceColor, int offset)
	{//offset is for smaller miniCircle width when game is won
		x=pieceX;
		y=pieceY;
		gx=pieceX*(pieceWidth+Connect4Game.xPieceBorder)+Connect4Game.xBorder;
		gy=pieceY*(pieceWidth+Connect4Game.yPieceBorder)+2*Connect4Game.yBorder;
		color=pieceColor;
		
		piece = (offset==0)? new GOval(gx,gy, pieceWidth, pieceWidth) : new GOval(gx+pieceWidth/2-offset/2,gy+pieceWidth/2-offset/2, offset, offset);
		
		piece.setFillColor(pieceColor);
		piece.setFilled(true);
	}//Piece constructor

}//class Piece
