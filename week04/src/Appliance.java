//Felicia Hsieh

/*
toString
Returns a description of the current state of the appliance, matching these templates:
The ____ is off.
OR
The ____ is on, consuming ___ W of power.
Add code to the run() method in your Week4 program that creates multiple Appliance objects and demonstrates that they work as expected.
*/

public class Appliance {
	
	private String name;//Appliance name
	private int powerRating;//Power consumption in Watts
	private boolean isOn;//Is appliance ON?
	
	public Appliance(String n, int pwrRating)
	{
		this.name = n;
		this.powerRating = pwrRating;
		this.isOn = false;
	}

	public void togglePower()
	{
		this.isOn = !this.isOn;
	}


	public String toString()
	{
		if(this.isOn)
		{
			return ("The " + this.name + " is on, consuming " + this.powerRating + " W of power.");
		} else 
		{
			return ("The " + this.name + " is off.");
		}
	}//end to toString()
	
}//Public class Appliance
