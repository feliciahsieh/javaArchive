import acm.graphics.*;

/*
 * This class is set up to provide you a convenience method
 * for checking whether two rectangular GObjects are overlapping
 * each other. 
 * YOU SHOULD NOT CHANGE ANYTHING IN THIS FILE.
 * 
 * Example use:
 * 
 * GRect rect = new GRect(....);
 * Crate crate = new Crate();
 * 
 * if (PingUtil.objectsAreOverlapping(rect, crate))
 * {
 *    ...
 * }
 */

public class PingUtil
{
	static boolean objectsAreOverlapping(GObject a, GObject b)
	{
		if (a.getX() >= (b.getX() + b.getWidth()) )
			return false;
		if ((a.getX() + a.getWidth()) <= b.getX() )
			return false;
		if (a.getY() >= (b.getY() + b.getHeight()) )
			return false;
		if ((a.getY() + a.getHeight()) <= b.getY() )
			return false;
		
		return true;
	}
}
