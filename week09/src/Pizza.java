import acm.graphics.*;

import java.awt.Color;
import java.util.*;

public class Pizza extends GOval
{
	// ADD: A field representing the diameter
	private int diameter;

	// this array list contains the set of toppings
	private ArrayList<String> toppings = new ArrayList<String>();

	// ADD: a field representing whether there is sauce or not
	private boolean hasSauce;

	// This constructor creates a 'default' pizza, which is
	// does have sauce, has a size of 10, and has one topping, "cheese"

	public Pizza()
	{
		super(100,100);
		
		// ADD: the body of this constructor
		this.hasSauce = true;
		this.diameter = 10;
		this.toppings.add("cheese");

		this.setColor(Color.RED);
		this.setFillColor(Color.RED);
		this.setFilled(true);
	}
  
	// This constructor creates a one-topping pizza with the designated
	// topping, size and sauce status
	public Pizza(String topping, int size, boolean hasSauce)
	{
		// ADD: the body of this constructor
		super(100,100);

		this.toppings.add(topping);
		this.diameter = size;
		this.hasSauce = hasSauce;

		if(this.hasSauce==true)
		{
			this.setColor(Color.RED);
			this.setFillColor(Color.RED);
		}
		else
		{
			setColor(Color.LIGHT_GRAY);
			setFillColor(Color.LIGHT_GRAY);
		}
		this.setFilled(true);
		
	}

	// This method adds an additional topping to the pizza
	public void AddTopping(String topping)
	{
		// ADD: the body of this method
		toppings.add(topping);
	}

	// This method changes the pizza from having sauce to not
	// having sauce, or vice versa
	public void SwitchSauce()
	{
		// ADD: the body of this method
		hasSauce = !hasSauce;
		if(this.hasSauce==true)
		{
			this.setColor(Color.RED);
			this.setFillColor(Color.RED);
		}
	}

	// The toString method returns a description of the current
	// pizza, in the form:
	//
	//  [diameter]-inch [red/white] pizza with [toppings]
	//
	// where:
	//       * [diameter] is substituted with the diameter
	//       * [toppings] is substituted with list of all toppings separated
	//          by spaces and the word "and"
	//       * [red/white] is the word "red" if there is sauce, and
	//          "white" if there is no sauce

	public String toString()
	{
		// ADD: the body of this method
		String result;
		String mySauce;
		String myToppings="";
		
		if(this.hasSauce)
			mySauce = "red";
		else
			mySauce = "white";

		if(this.toppings.size()==1)
			myToppings = this.toppings.get(0);
		else
		{
			int total = this.toppings.size();
			for(int i=0;i<total-1;i++)
			{
				myToppings = myToppings + this.toppings.get(i) + " and ";
			}
			myToppings = myToppings + this.toppings.get(total-1);
		}
		result = diameter + "-inch "+ mySauce + " pizza with " + myToppings;
		
		return result;
	}
}
