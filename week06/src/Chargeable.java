
public interface Chargeable {
	
	// Plug the device in to charge for the given number of minutes.
	public void charge(int minutes);

	// Return the amount of charge remaining,
	// as a number between 0 and 1.0
	public double remainingCharge();
	
}
