//Felicia Hsieh

/*
Create a new class named Clicker, representing a simple counting device. (The kind someone might hold at their side and click to quickly count the number of people entering a large room or building.) It has two "buttons": one to incremement its running count, and one to reset the count to zero. Your class should therefore have:
Just one field: The current count, an integer
Four methods:
A constructor that takes one integer parameter and sets the current count accordingly.
getCount: returns the current count
click: incremements the count by one
reset: sets the count to 0
Create the class in its own file, named Clicker.java. In addition, add code to your main program (i.e. in the run bethon) (i.e. in the run() method) that creates and uses two different Clicker objects to demonstrate that the class is working.
*/

public class Clicker{
	private int count;
	
	public Clicker(int num)
	{
		this.count=num;
	}

	public int getCount()
	{
		return this.count;
	}

	public void click()
	{
		this.count++;
	}

	public void reset()
	{
		this.count=0;
	}
}//Public class Week3
