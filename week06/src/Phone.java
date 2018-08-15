
public class Phone implements Chargeable {
	int maxCharge=100;
	int currentCharge=0;
	boolean hasCamera=false;
	
	String name;
	String batteryType;
	
	public Phone(String n, String bt)
	{
		name = n;
		batteryType = bt;
	}
	
	public String toString()
	{
		return this.name + " uses " + batteryType + " batteries," + " charges to max " + maxCharge + "and has " + currentCharge;
	}

	public int getCurrentCharge()
	{
		//System.out.println("getCurrentCharge():" + this.currentCharge);
		return this.currentCharge;
	}
	
	public void setCurrentCharge(int newCharge)
	{
		//System.out.println("setCurrentCharge(): setting " + newCharge);

		if (newCharge>0)
		{
			if(newCharge>maxCharge)
				this.currentCharge = 100;
			else
				this.currentCharge = newCharge;
		}
		
		//System.out.println("setCurrentCharge(): finished setting " + this.currentCharge);
	}
	
	// Plug the device in to charge for the given number of minutes.
	public void charge(int minutes)
	{
		if (minutes>0)
		{
			if((minutes+currentCharge) >= maxCharge)
			{
				currentCharge=100;
				//System.out.println("charge(): ok. Charged to max " + currentCharge);
			}
			else
			{
				currentCharge += minutes;
				//System.out.println("charge(): ok. Charged minutes to " + currentCharge);
			}
		}
		else
			System.out.println("charge(): Neg. #'s not allowed. Charge stays at " + currentCharge);
	}

	// Return the amount of charge remaining,
	// as a number between 0 and 1.0
	public double remainingCharge()
	{
		double result;
		
		result = (currentCharge*1.0) / maxCharge;
		//System.out.println("Remaining Charge = " + result + " or " + (int) (result*100) + "%");
		
		return result;
	}
	
	public void sethasCamera()
	{
		hasCamera = false;
	}
	public boolean gethasCamera()
	{
		return hasCamera;
	}
}
