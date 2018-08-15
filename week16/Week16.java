//Week16 - Extra Credit

import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import acm.program.ConsoleProgram;

@SuppressWarnings("serial")
public class Week16 extends ConsoleProgram {
	
	public void init()
	{
		resize(800, 600);
		setFont("monospace-plain-32");
	}
	public void run()
	{
//Games - individual game titles (e.g., Tic-Tac-Toe, Battleship, Stratego), along with a short genre description for each game (e.g., strategy, casual, cardgame).
//Players - individual players, including their first and last names, and a "profile" (long string) for each player.
//Gameplays - individual instances of two people playing one game, including the title of the game, the winner and the loser, and the date it occurred. (You do NOT need to handle ties at all.)
		String Games[] = {"Tic-Tac-Toe","Battleship","Stratego", "Word with Friends"};
		String Player[] = {"Abe","Bob","Cindy", "Deb"};
		String Gameplays[][] = { {"Tic-Tac-Toe","Deb","Abe", "Now()"},
					 {"Battleship","Cindy","Abe", "Now()"},
					 {"World with Friends","Cat","Abe", "Now()"}};
		
		DatabaseInterface db;
		try
		{
			db = new DatabaseInterface("fhsieh","lexqS82Q", "scinett.org", "fhsieh_database");
			println("Connected!");
		}
		catch(SQLException e)
		{
			println("Could not connect to database" + e.getMessage());
			return;
		}
		
		try
		{
			
			String firstName = readLine("Enter Player's First Name: ");
			String lastName  = readLine("Enter Player's Last Name: ");
			String profile  = readLine("Enter Player's Profile: ");
			
//db.runUpdate("INSERT INTO Players ('" + name + "', " + score + ")" );
//INSERT INTO `fhsieh_database`.`Players` (`id`, `name`, `password`) VALUES ('3', 'Foozball', 'sports');
			
			db.runUpdate("INSERT INTO Players (firstname,lastname,profile) VALUES ('" + firstName + "', '" + lastName + "', '" + profile + "')");
			
			println("Data inserted !");
		}
		catch (SQLException e)
		{
			println("Could not insert new Player data" + e.getMessage());
			return;
		}
		

		try
		{
			ResultSet result = db.runQuery("SELECT * FROM Players");
			int i=0;
			println("Player List");
			while(result.next() != false)
			{
				String firstName = result.getString(2);
				String lastName = result.getString(3);
				String profile = result.getString(4);
				println(i + ": " + firstName + " " + lastName + "  Profile: " + profile);
				i++;
			}
		}
		catch (SQLException e)
		{
			println("Players Table Query failed! "+ e.getMessage());
		}//catch
		
		try
		{
			ResultSet result = db.runQuery("SELECT * FROM Games");
			int i=0;
			println("Games List");
			while(result.next() != false)
			{
				String gameName = result.getString(2);
				String genre = result.getString(3);
				println(i + ": " + gameName + "  Genre: " + genre);
				i++;
			}
		}
		catch (SQLException e)
		{
			println("Games Table Query failed! "+ e.getMessage());
		}//catch

		try
		{
			
			String name = readLine("Enter New Game: ");
String genre  = readLine("Enter Game Genre: ");

db.runUpdate("INSERT INTO Games (name,genre) VALUES ('" + name + "', '" + genre + "')");

println("New Game inserted !");
}
catch (SQLException e)
{
println("Could not insert New Game data" + e.getMessage());
return;
}

try
{
String gamename = readLine("Enter Name of Game Play: ");
String winner = readLine("Enter Winner of Game Play: ");
String loser = readLine("Enter Loser of Game Play: ");
String date = readLine("Enter Game Date: ");
Date myDate = new Date(2014,10,30);
System.out.println("Date: "+myDate);

java.util.Date dt = new java.util.Date();
dt.setYear(2014);
dt.setMonth(12);
dt.setDate(25);
//java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
String currentDate = sdf.format(dt);

System.out.println("Date:" + currentDate);

//String date = readLine("Enter Date of Game Play (YYYY-MM-DD): ");

//DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//Date myDate = formatter.parse(date);
//java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());

db.runUpdate("INSERT INTO Gameplays (gamename, winner, loser, gamedate) VALUES ('" + gamename + "', '" + winner + "', '" + loser + "'," + myDate + "')");

println("New Gameplay inserted !");
}
catch (SQLException e)
{
println("Could not insert data" + e.getMessage());
return;
}
 
}

}
