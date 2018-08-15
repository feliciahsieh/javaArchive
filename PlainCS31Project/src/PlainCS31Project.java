import java.sql.ResultSet;
import java.sql.SQLException;

import acm.program.ConsoleProgram;

@SuppressWarnings("serial")
public class PlainCS31Project extends ConsoleProgram {
	
	public void init()
	{
		resize(800, 600);
		setFont("monospace-plain-32");
	}
	public void run()
	{
		DatabaseInterface db;
		try
		{
			db = new DatabaseInterface("w10731019","w10731019", "scinett.org", "cs31db");
			println("Connected!");
		}
		catch(SQLException e)
		{
			println("Could not connect" + e.getMessage());
			return;
		}
		
		try
		{
			String name = readLine("Player's name: ");
			int score = readInt("Player's score: ");
			
			db.runUpdate("INSERT INTO Scores VALUES ('" + name + "', " + score + ")" );
			
			println("Data inserted !");
		}
		catch (SQLException e)
		{
			println("Could not insert data" + e.getMessage());
			return;
		}
		
		try
		{
			ResultSet result = db.runQuery("SELECT * FROM Scores");
			while(result.next() != false)
			{
				String name = result.getString(1);
				int score = result.getInt(2);
				println(name + " scored " + score);
			}
		}
		catch (SQLException e)
		{
			println("Query failed! "+ e.getMessage());
		}
	}
	
}
