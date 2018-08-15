/*
 * File: FirstKarelProgram.java
 * ----------------------------
 * This program solves the problem of moving a beeper to a ledge.
 */

import stanford.karel.*;

@SuppressWarnings("serial")
public class BoringKarel extends SuperKarel {
	public void run() {
		// paints the bottom row, ending
		// with a beeper, then returns
		// to its starting position
		while (frontIsClear()) {
			paintCorner(BLUE);
			move();
		}
		putBeeper();
		turnAround();
		while (frontIsClear()) {
			move();
		}
	}	
}
