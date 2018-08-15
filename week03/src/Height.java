/*
 * Create another new class, called Height, representing a height measured in inches and feet. It has
Two fields: an amount of feet (an integer), an amount of inches (a non-integer)
Four methods:
A constructor that sets both the feet and inches amounts.
* This method should check that the number of inches is less than 12.
* If it isn't, print an error message and set the number of inches to 0.
* setFeetAndInches: Changes both the feet and inches amounts. *This method should also
* check that the number of inches is less than 12.
* If it isn't, print an error message and do NOT change the private variables at all.
* toString: Returns a String that represents the height in the form ft' in''.
* (Ex: 6' 3'' means 6 feet and 3 inches)
* getTotalInches: Returns the height as a total number of inches (non-integer)
* getCentimeters: Returns the height as a number of centimeters (non-integer) (1 inch is 2.54 centimeters)
*/
public class Height {
	private int feet;
	private double inches;
	
	public Height(int f, double i) {
		if (i<12)
		{
			this.inches = i;
		}
		else
		{
			System.out.println("Incorrect Inches in Constructor");
			this.inches=0;
		}
		this.feet = f;
	}
	
	public void setFeetAndInches(int f,double i)
	{
		if (i<12) 
		{
			this.inches = i;
			this.feet = f;
		}
		else
		{
			System.out.println("setFeetAndInches: Incorrect Inches ERROR");
		}
		this.feet = f;
	}
	
	public String toString()
	{
		return(this.feet + "' " + this.inches + "\" ");
	}
	
	public double getTotalInches()
	{
		return this.feet * 12 + this.inches;
	}
	
	public double getCentimeters()
	{
		return getTotalInches() * 2.54;
	}
}
