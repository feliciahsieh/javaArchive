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
	final private int MAX_CRATEWIDTH = 80;
	final private int MIN_CRATEWIDTH = 20;
	final private int MAX_DESCENTRATE = 2;
	final static private Color[] crateColors = { Color.RED, Color.BLUE, Color.MAGENTA, Color.DARK_GRAY, Color.GREEN };
	
	// crateDescentRate: Controls how quickly the crate falls
	public int crateDescentRate = 1;
	public int    crateSize=1;
	public GRect  crate;
	public int    id;
	
	public Crate(int num, int level)
	{
		// Initialize internal data appropriately, creating a crate with a RANDOM SIZE, a RANDOM COLOR 
		// and a RANDOM DESCENT RATE. Each of these randomly set properties should be within a reasonable range.
		Random rand = new Random();
		crateSize = rand.nextInt(MAX_CRATEWIDTH)+MIN_CRATEWIDTH;

		crateDescentRate = (rand.nextInt(3)+1)*MAX_DESCENTRATE*level;
		System.out.println("Crate():crateDescentRate:"+crateDescentRate);
		
		double screenWidth = 600;
		
		int crateXPosition = rand.nextInt((int)screenWidth-crateSize+50);
		int crateYPosition = -crateSize;
		id = num;

		crate = new GRect(crateXPosition,crateYPosition,crateSize, crateSize);
		
		Color crateColor = crateColors[rand.nextInt(crateColors.length)];
		crate.setFillColor(crateColor);
		crate.setFilled(true);
	}
	public void moveDown()
	{
		// Move this crate object downward once, using the crateDescentRate to control how far it moves.
		//System.out.println("moveDown():crateDescentRate:"+crateDescentRate);
		crate.move(0, this.crateDescentRate);
	}
	
	public void resetDescentLevel(int level)
	{
		Random rand = new Random();
		crateDescentRate = (rand.nextInt(3)+1)*MAX_DESCENTRATE*level;
		
		System.out.println("Crate:resetDescentLevel:"+crateDescentRate);
	}
}
