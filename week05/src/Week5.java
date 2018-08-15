import acm.program.ConsoleProgram;

@SuppressWarnings("serial")
public class Week5 extends ConsoleProgram {
	
	public void init()
	{
//resize(800, 600);
	}

	public void run()
	{
		MultidayAppointment a2 = new MultidayAppointment("Dr", 2014, 9, 25, 2014, 10, 2);
		System.out.println(a2);
		a2.setEndDate(2014, 10, 3);
		System.out.println("SetEndDate 10/3/14: " + a2);
		System.out.println("9/25/14-10/3/14 Duration: " + a2.duration() + " day(s)");
		
		MultidayAppointment a3 = new MultidayAppointment("WEEKEND", 2014, 9, 24, 2014, 9, 25);
		System.out.println("9/24-25/14 Duration: " + a3.duration() + " day(s)");
		
		MultidayAppointment a4 = new MultidayAppointment("TESTS", 2014, 9, 24, 2014, 9, 24);
		System.out.println(a4);
		System.out.println(a4.duration());
		
		for(int z=a4.day2;z<a4.day2+9;z++)
		{
			a4.setEndDate(2014, 9, z);
			System.out.println(a4);
			System.out.println(a4.duration());
		}

		a4.setEndDate(5000, 12, 31);
		System.out.println(a4);
		
		a4.setEndDate(2014, 9, 25);
		System.out.println(a4);
		a4.setEndDate(2014, 9, 23);
		System.out.println(a4);
		
	}//run()
}
//Task 1: Inheritance example
/*
 * Create two classes (One parent class, one child class).
 Give each class at least one constructor and at least two other methods.
 Post your example below the "Inheritance Examples" post.
*/
/*
Baseball mlbBaseball = new Baseball("MLB Baseball", 9, "runs", "stadium");
println(mlbBaseball);
Baseball wiffleball = new Baseball("Wiffleball", 2, "runs", "grass field");
println(wiffleball);
Baseball stickball = new Baseball("Stickball", 3, "runs", "street");
println(stickball);

//Task 2: Regular Expressions
String input;
String regularExpression = "0x[a-z]{1,3}(b|g|j)et";
println("Regular expression: " + regularExpression);

while(true)
{
input = readLine("Type string: ");
if(input.matches(regularExpression))
println("String Matches");
else
println("String DOES NOT match");
}
*/
/*
System.out.println("Invalid setDateTests");
a1.setDate(-1,9,11);
a1.setDate(3099,9,11);
a1.setDate(9999,9,11);
a1.setDate(10000,9,11);
a1.setDate(2015,0,11);
a1.setDate(2015,13,11);
a1.setDate(2015,-1,11);
a1.setDate(2015,1,-1);
a1.setDate(2015,1,32);
a1.setDate(2015,2,29);
a1.setDate(2015,3,32);
a1.setDate(2015,4,31);
a1.setDate(2015,5,32);
a1.setDate(2015,6,31);
a1.setDate(2015,7,32);
a1.setDate(2015,8,32);
a1.setDate(2015,9,31);
a1.setDate(2015,10,30);
a1.setDate(2015,11,31);
a1.setDate(2015,12,32);

a1.moveOneDayLater();
System.out.println("Day Later: " + a1);
a1.moveOneDayEarlier();
a1.moveOneDayEarlier();
System.out.println("Day Earlier: " + a1);

a1.setDate(2014, 1, 1);
for(int j=0;j<=365;j++)
{
System.out.println(j + ": " +a1);
a1.moveOneDayLater();
}
a1.setDate(2015, 1, 1);
for(int j=0;j<=366;j++)
{
System.out.println(j + ": " +a1);
a1.moveOneDayEarlier();
}
*/
