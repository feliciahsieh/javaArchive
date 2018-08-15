import java.awt.Color;
import acm.graphics.GRect;

public class boardColumn extends GRect{

	int col;
	
	boardColumn(int i, int w, int h, int pieceWidth)
	{
		super(i*(pieceWidth+Connect4Game.xPieceBorder)+Connect4Game.xBorder, 0, pieceWidth+2, 2*Connect4Game.yBorder+(h*pieceWidth)+(h-1)*Connect4Game.yPieceBorder+1);

		//Use partially transparent, RGB value (float)
		Color c=new Color(.75f, .75f, .75f, .5f );
		this.setColor(c);
		this.setFillColor(c);
		this.setFilled(false);
		col = i;
	}
}
