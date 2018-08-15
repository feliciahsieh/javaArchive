import java.awt.Color;
import java.util.Random;

import acm.graphics.*;

/*
 * The Crate class is used for individual falling crates
 * to be placed in the game area and then animated.
 * 
 * Be creative in deciding how to arrange the appearance of a crate
 * but MAKE SURE IT IS RECTANGULAR overall - otherwise, detecting
 * whether it has hit the paddle will not work.
 */

public class Crate extends GCompound
{
	// descentRate: Controls how quickly the crate falls
	final private int MAX_CRATEWIDTH = 80;
	final private int MIN_CRATEWIDTH = 20;
	final private double MAX_DESCENTRATE = 5.0;

	final static private Color[] crateColors = { Color.RED, Color.BLUE, Color.MAGENTA, Color.DARK_GRAY, Color.GREEN };
	
	// crateDescentRate: Controls how quickly the crate falls
	public int    crateDescentRate = 1;
	public int    crateSize=1;
	public GRect  crate;
	public GLabel crateLabel;
	public int    id;

	public Crate(int num, int level, int boardWidth)
	{
		// Initialize internal data appropriately, creating
		// a crate with a RANDOM SIZE, a RANDOM COLOR 
		// and a RANDOM DESCENT RATE. Each of these randomly
		// set properties should be within a reasonable range.
		
		Random rand = new Random();
		//Generate random Crate Size
		crateSize = rand.nextInt(MAX_CRATEWIDTH)+MIN_CRATEWIDTH; //20-100 pixels

		//Generate random Crate Descent Rate
		crateDescentRate = (int) (rand.nextDouble()*MAX_DESCENTRATE*level+level);
		//System.out.println("Crate():crateDescentRate:"+crateDescentRate);
		
		//Generate random Crate Position above screen
		int crateXPosition = rand.nextInt(boardWidth-crateSize);//0 to (600-crateSize)
		int crateYPosition = -crateSize-1;

		id = num;

		crate = new GRect(crateXPosition,crateYPosition,crateSize, crateSize);
		//Generate random Crate Color
		Color crateColor = crateColors[rand.nextInt(crateColors.length)];
		crate.setFillColor(crateColor);
		crate.setFilled(true);
		crate.setVisible(true);
		
		//crateLabel = new GLabel(""+num,crateXPosition,crateYPosition);
		
		add(crate);
		//add(crateLabel);
	}
	
	public void moveDown()
	{
		// Move this crate object downward once, using the descentRate to control how far it moves.
		//System.out.println("moveDown: crateDescentRate:"+crateDescentRate);
		crate.move(0, this.crateDescentRate);
	}
	
	public void resetDescentLevel(int level)
	{
		Random rand = new Random();
		crateDescentRate = (int) (rand.nextDouble()*MAX_DESCENTRATE*level+ level);
		
		System.out.println("Crate:resetDescentLevel:"+crateDescentRate);
	}
	
	public void recycleCrate(int boardWidth)
	{
		System.out.println("recycleCrate Start");
		Random rand = new Random();

		//Generate random Crate Position above screen
		int crateXPosition = rand.nextInt(boardWidth-crateSize);//0 to (600-crateSize)
		int crateYPosition = -crateSize-1;

		System.out.println("recycleCrate:"+id);
		
		crate.setLocation(crateXPosition, crateYPosition);
		System.out.println("recycleCrate End:Just set location");
	}

}
