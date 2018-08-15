import java.awt.Color;
import acm.graphics.GOval;


public class CheckerPiece extends GOval {
	private int x;
	private int y;
	private boolean isRed=true;
	
	public CheckerPiece(int xPos, int yPos) {
		
		super(xPos*50, yPos*50, 50.0, 50.0);

		if( ((xPos<0) || (xPos>7)) ||
		    ((yPos<0) || (yPos>7)) )
		{
			throw new IndexOutOfBoundsException();
		} else
		{
			this.x = xPos;
			this.y = yPos;

			this.setColor(Color.RED);
			this.setFillColor(Color.RED);
			this.setFilled(true);
		}
	}
	
	public boolean isRed()
	{
		return this.isRed;
	}

	public void toggle()
	{
		this.isRed = !this.isRed;

		if(this.isRed)
		{
			this.setColor(Color.RED);
			this.setFillColor(Color.RED);
		}
		else
		{
			this.setColor(Color.BLACK);
			this.setFillColor(Color.BLACK);
		}
		this.setFilled(true);
	}//toggle()
	
}//CheckerPiece class
