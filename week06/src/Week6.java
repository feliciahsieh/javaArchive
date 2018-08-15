import java.util.ArrayList;
import java.util.Arrays;

import acm.program.ConsoleProgram;

@SuppressWarnings("serial")
public class Week6 extends ConsoleProgram {
	public void init()
	{
		setFont("monospaced-plain-32");
		resize(800, 600);
	}
	
	public void run()
	{
		//Task 1: Regex
		ArrayList<String> myStrings = new ArrayList<String>();
		String input="initial";
		String regex="";
		
		while(!input.equals(""))
		{
			input = readLine("Enter string (Type blank to end): ");
			if(!input.equals(""))
			{
				myStrings.add(input);
			}
		}
		
		//First Regex - [aeiou] x 2
		System.out.println("Task Regex, item 1 - Double Vowel");
		regex = ".*[aeiou]{2}.*";
		for(String i : myStrings)
		{
			if(i.matches(regex))
				System.out.println(i + " Matched " + regex);
		}
		
		//Second Regex - Loop through the ArrayList again and use a regular expression
		//to print out all items that begin with a capital letter.
		System.out.println("Task Regex, item 2 - Proper Name");
		regex = "[A-Z].*";
		for(String i : myStrings)
		{
			if(i.matches(regex))
				System.out.println(i + " Matched " + regex);
		}

		//Third Regex - Loop through the ArrayList again, and use a regular expression to print out all items that are in the form of a W number.
		//For the purpose of this assignment, that means that the string: optionally begins with w or W
		//has 8 digits in a row, beginning with 10, followed by one digit 0 through 7, then followed by five more digits 0 through 9
		System.out.println("Task Regex, item 3 - LasPo Student ID#");
		regex = "[wW]?10[0-7]\\d{5}";
		for(String i : myStrings)
		{
			if(i.matches(regex))
				System.out.println(i + " Matched " + regex);
		}

		//Fourth Regex - Price
		System.out.println("Task Regex, item 4 - Currency");
		regex = "\\$(0|[1-9]+)([.]\\d{2})?";
		for(String i : myStrings)
		{
			if(i.matches(regex))
				System.out.println(i + " Matched " + regex);
		}
		
		//Task 2: ListUtilities
		System.out.println("\n\nTask: ListUtilities");
		double[] myList = { 1.3, 2.5, 4.5, 6.7, 9.0, 10.0};
		double[] myList1 = { 2.3, 9.5, 3.5, 0.0, 3.0, 2.222};
		double[] myList2 = { -1.0, -0.9, 0 };
		double[] myList3 = { 1.0, 2.0, 0.9};
		double[] myList4 = { -10.0, -5.0, 4.5 };
		double[] myList5 = { 3.3, 2.5, -0.0};
		double[] myList6 = { -.3, 2.5, 0};
		double[] myList7 = { 0.0, 0.0, 0.0};

		System.out.println("\n" + Arrays.toString(myList));
		System.out.println("Average: " + ListUtilities.Average(myList));
		System.out.println("Is List In Order? " + ListUtilities.InOrder(myList));
		
		System.out.println("\n" + Arrays.toString(myList1));
		System.out.println("Average: " + ListUtilities.Average(myList1));
		System.out.println("Is List In Order? " + ListUtilities.InOrder(myList1));

		System.out.println("\n" + Arrays.toString(myList2));
		System.out.println("Average: " + ListUtilities.Average(myList2));
		System.out.println("Is List In Order? " + ListUtilities.InOrder(myList2));

		System.out.println("\n" + Arrays.toString(myList3));
		System.out.println("Average: " + ListUtilities.Average(myList3));
		System.out.println("Is List In Order? " + ListUtilities.InOrder(myList3));
		
		System.out.println("\n" + Arrays.toString(myList4));
		System.out.println("Average: " + ListUtilities.Average(myList4));
		System.out.println("Is List In Order? " + ListUtilities.InOrder(myList4));
		
		System.out.println("\n" + Arrays.toString(myList5));
		System.out.println("Average: " + ListUtilities.Average(myList5));
		System.out.println("Is List In Order? " + ListUtilities.InOrder(myList5));
		
		System.out.println("\n" + Arrays.toString(myList6));
		System.out.println("Average: " + ListUtilities.Average(myList6));
		System.out.println("Is List In Order? " + ListUtilities.InOrder(myList6));
		
		System.out.println("\n" + Arrays.toString(myList7));
		System.out.println("Average: " + ListUtilities.Average(myList7));
		System.out.println("Is List In Order? " + ListUtilities.InOrder(myList7));
		
		
		//Chargeable
		Samsung myPhone = new Samsung("Galaxy Note 3", "Lithium Ion", "Black", 599.99, 100);
		System.out.println("My New Phone: " + myPhone);
		
		System.out.println("\nGoing to charge=5");
		myPhone.setCurrentCharge(50);
		myPhone.remainingCharge();
		myPhone.charge(5);
		myPhone.remainingCharge();
		myPhone.charge(20);
		myPhone.remainingCharge();
		myPhone.charge(30);
		myPhone.remainingCharge();

		System.out.println("\nGoing to charge=110");
		myPhone.charge(110);
		myPhone.remainingCharge();

		System.out.println("\nGoing to charge= -1");
		myPhone.charge(-1);
		myPhone.remainingCharge();
		
		Samsung my2ndPhone = new Samsung("Galaxy S4", "Lithium Ion", "Black", 499.99, 100);
		System.out.println("My 2nd Phone: " + my2ndPhone);

		//2nd Phone
		my2ndPhone.setPrice(299.99);
		System.out.println("New Price:" + my2ndPhone.getPrice());
		
		my2ndPhone.setColor("Blue");
		System.out.println("New Color:" + my2ndPhone.getColor());
		
		my2ndPhone.sethasCamera();
		System.out.println("Camera-ready phone: " + my2ndPhone.gethasCamera());
		
		ArrayList<Chargeable> phoneInventory = new ArrayList<Chargeable>();
		phoneInventory.add(new Samsung("Galaxy Note 3", "Lithium Ion", "Black", 599.99, 100));
		phoneInventory.add(new Samsung("Galaxy S4", "Lithium Ion", "Black", 499.99, 100));

		System.out.println("***************************");
		double result;
		for (Object o : phoneInventory)
		{
			System.out.println(o);
			Phone c = (Phone) o;
			System.out.println("Setting 50 minutes");
			c.setCurrentCharge(50);
			System.out.println("Charging 5 minutes");
			c.charge(5);
			result=c.remainingCharge();
			System.out.println("Remaining Charge = " + result + " or " + (int) (result*100) + "%");
			System.out.println("Charging 20 minutes");
			c.charge(20);
			result=c.remainingCharge();
			System.out.println("Remaining Charge = " + result + " or " + (int) (result*100) + "%");
			System.out.println("Charging 30 minutes");
			c.charge(30);
			result=c.remainingCharge();
			System.out.println("Remaining Charge = " + result + " or " + (int) (result*100) + "%");

			System.out.println("***************************");
		}
		
	}//run()
	
}
