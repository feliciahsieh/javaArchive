
public class Samsung extends Phone {
	int maxCharge;
	String color;
	double price;
	
	public Samsung(String name, String batteryType, String c, double p, int mc)
	{
		super(name, batteryType);
		maxCharge = mc;
		color = c;
		price = p;
	}
	
	public String toString()
	{
		return "Name:" + name + " Battery type:" + batteryType + " Color:"+ color + " Price:" + price + " Max Charge:" + maxCharge;
	}
	
	public String getColor()
	{
		return color;
	}
	
	public void setColor(String c)
	{
		if( (c.equals("Red") || c.equals("Orange") || c.equals("Yellow") || c.equals("Green") || c.equals("Blue") ||
		     c.equals("Purple") || c.equals("White") || c.equals("Pink") || c.equals("Brown")) )
		{
			color = c;
		}
		else
		{
			color = "Black";
		}
	}
	
	public double getPrice()
	{
		return price;
	}
	
	public void setPrice(double p)
	{
		int tempInt;
		double tempDouble;
		
		if(p>=0.0)
		{
			tempDouble = p*100.0;
			tempInt = (int) tempDouble;
			price = (double) (tempInt/100);
			System.out.println("Price:"+price);
		}
	}

	public void sethasCamera()
	{
		hasCamera = true;
	}

}
