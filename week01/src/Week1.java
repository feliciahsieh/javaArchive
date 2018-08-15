//Felicia Hsieh
//CS 31 Java Programming
//Fall 2014
//August 27, 2014

import acm.program.ConsoleProgram;
import java.text.*;

@SuppressWarnings("serial")
public class Week1 extends ConsoleProgram {
	public void init()
	{
		setFont("monospaced-plain-32");
		resize(800, 600);
	}
	public void run()
	{
		/*Ask the user for the price of two different items. Report the sum
		  of those prices. */
		DecimalFormat df = new DecimalFormat("#.00");

		println("Task 1: Sum of 2 prices\n-----------------------");
		double price1 = readDouble("Please enter Price 1: $");
		double price2 = readDouble("Please enter Price 2: $");
		double total = price1 + price2;
		println("Sum of $" + df.format(price1) + " + $" + df.format(price2) + " = $" + df.format(total));

		/* Report the result of adding a 9% tax to the total price. (Hint: Multiply the total by 1.09) */
		println("\nTask 2: Total price with 9% tax\n-------------------------------");
		println("Total = $" + df.format(total*1.09));

		/* Using a for loop, print a sequence of lines that looks like:
		   1 fish
		   2 fish
		   3 fish
		   ... [pattern continues]
		   49 fish
		   50 fish*/

		println("\nTask 3: Print Dr. Seuss book (1..50 fish)\n------------------------------------------");
		for(int i=1; i<=50; i++)
			println(i + " fish");

		/* Ask user for miles driven last week. Ask how many gallons of gas were used. Calc miles-per-gallon.*/
		println("\nTask 4: Print user's mpg\n------------------------");
		Double milesValue = readDouble("Please enter distance driven last week (miles): ");
		Double gallonsValue = readDouble("Please enter amount of gas used (gallons): ");
		df = new DecimalFormat("##.#");
		println("Miles Per Gallon = " + df.format(milesValue/gallonsValue));

		/*Using a while loop, repeatedly ask the user a question that has a numerical answer. (Example: How many provinces does Canada have?)
    Once they get the right answer, stop the loop and print "That's correct!". In addition, after each wrong answer, print feedback to tell the user
    "that's too low" or "that's too high". */
		println("\nTask 5: Guessing Number Game\n----------------------------");
		//How many counties in California : 58
		println("Guess how many California counties exist.");
		int guess = -1;
		boolean isCorrect = false;
		while (!isCorrect){
			guess = readInt("Please enter your guess: ");
			if(guess<58)
				println("Too low");
			else if(guess>58)
				println("Too high");
			else
				isCorrect = true;
		}
		println("58 is the Right Answer!\n\nThanks for playing !");
	}

}
