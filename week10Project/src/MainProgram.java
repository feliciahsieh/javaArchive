import java.io.*;
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.io.PrintWriter;
import java.util.Random;
import java.awt.Color;

import com.sun.xml.internal.rngom.parse.compact.EOFException;

import acm.graphics.GRect;
import acm.program.GraphicsProgram;

@SuppressWarnings("serial")
public class MainProgram extends GraphicsProgram
{
	CheckerPiece [] pieces = new CheckerPiece[32];
	public void init()
	{
		resize(400, 400);
	}
    
	public void run()
	{
		//TASK 1: FILE I/O, ERROR HANDLING
		try
		{
			reportHighScores();

			//Read User Input
			String uInput, uName;
			int uScore=0;
			uName = readLine("Enter Name: ");
			uInput = readLine("Enter Score: ");
			uScore = Integer.parseInt(uInput);
			
			updateHighScores(uName, uScore);
			
			reportHighScores();

		} catch (IOException e) {
			e.printStackTrace();
			println("IO problem: " + e);
		}
		//TASK 2: CHECKERPIECES
		DrawCheckerboard();
		DrawCheckerPieces();
	}
    
	private void updateHighScores(String newname, int newscore) throws IOException
	{    
		GameRecord[] userList=new GameRecord[2];
		int maxIndex=0, minIndex=0;
		
		userList[0]=new GameRecord();
		userList[1]=new GameRecord();

		BufferedReader in = new BufferedReader(new FileReader("highscores.txt"));
		int n = 0;
		//How to make dynamic-sized list of GameRecord??

		String line;
		while (true)
		{
			//Read Name
			line = in.readLine();
			if (line == null)
				break;
			userList[n].userName = line;

			//Read Score
			line = in.readLine();
			if (line == null)
				break;

			//Print Score
			userList[n].userScore = Integer.parseInt(line);

			n++;
			
		}
		in.close();    

		//Determine higher score
		if(userList[0].userScore>userList[1].userScore)
		{
			maxIndex = 0;
			minIndex = 1;
		}
		else {
			maxIndex = 1;
			minIndex = 0;
		}
		
		//Verify highest 2 game scores
		if((newscore>userList[minIndex].userScore) && (newscore>userList[maxIndex].userScore))
		{//newscore is highest
			userList[minIndex].userScore = userList[maxIndex].userScore;
			userList[minIndex].userName  = userList[maxIndex].userName;
			userList[maxIndex].userScore = newscore;
			userList[maxIndex].userName  = newname;
		} else if(newscore>userList[minIndex].userScore)
		{//newscore is 2nd highest
			userList[minIndex].userScore = newscore;
			userList[minIndex].userName  = newname;
		}
		//else if(newscore<userList[minIndex].userScore)
		//Do nothing
		
		// TEXT - OUTPUT TO A FILE
		//Writes to file in bin directory

		PrintWriter out = new PrintWriter("highscores.txt");
		out.write(userList[maxIndex].userName+"\n");
		out.write(userList[maxIndex].userScore+"\n");
		out.write(userList[minIndex].userName+"\n");
		out.write(userList[minIndex].userScore+"\n");
		
		out.close();
		
		// This method's arguments represent a possible new
		// name and score to add to the top-two high scores.
		// It should:
		// (a) Read all 4 lines from the highscores.txt file
		// (b) Close the file
		// (c) Determine if the given name and score
		//     means the contents of the file need to change.
		//     If so, open the file and write all 4 lines,
		//     with appropriate new contents, then close the file.

		System.out.println("");
	}//updateHighScores()

	private void reportHighScores() throws IOException
	{
		//Looks for file in bin directory
		BufferedReader in = new BufferedReader(new FileReader("highscores.txt"));
		int n = 0;
		while (true)
		{
			String line = in.readLine();
			if (line == null)
				break;

			if((n%2)==0)
			{//Print Name
				System.out.print(line + ": ");
			} else
			{//Print Score
				System.out.println(line);
			}
			n++;
		}
		in.close();
		
		// This method should:
		// (a) Read all 4 lines from the highscores.txt
		// (b) Print a report to the console that lists the
		//     top two players and their scores
		//     EXAMPLE
		//       Mary: 42
		//       Bob: 23
		// (c) Close the file
	}//reportHighScores()
    
	//*****************************
	private void DrawCheckerboard()
	{
		int i, j;
		
		//Draw Checkerboard
		for(i=0;i<8;i++)
			for(j=0;j<8;j++)
			{
				GRect s = new GRect(i*50,j*50,50,50);
				if(i%2==0)
				{
					if(j%2==0)
					{
						s.setFillColor(Color.GRAY);
						s.setFilled(true);
					} else
					{
						s.setFillColor(Color.LIGHT_GRAY);
						s.setFilled(true);    
					}
				}
				else
				{
					if(j%2==0)
					{
						s.setFillColor(Color.LIGHT_GRAY);
						s.setFilled(true);
					} else
					{
						s.setFillColor(Color.GRAY);
						s.setFilled(true);    
					}    
				}
				
				add(s);
			}

	}//DrawCheckerboard()
    
	public void DrawCheckerPieces()
	{
		int x, y;
		
		Random rand = new Random();
		for(int i=0;i<8;i++)
		{
			x=rand.nextInt(8);
			y=rand.nextInt(8);
			pieces[i] = new CheckerPiece(x,y);
			
			//Toggle half the pieces
			if(i%2==0)
			{
				pieces[i].toggle();
			}
			
			add(pieces[i]);
		}
		
		try
		{
//        CheckerPiece test1 = new CheckerPiece(-1,0);
//        test1.setFillColor(Color.CYAN);
//        add(test1);
//        CheckerPiece test2 = new CheckerPiece(8,0);
//        test2.setFillColor(Color.CYAN);
//        add(test2);
//        CheckerPiece test3 = new CheckerPiece(-1,0);
//        test3.setFillColor(Color.CYAN);
//        add(test3);
//        CheckerPiece test4 = new CheckerPiece(8,0);
//        test4.setFillColor(Color.CYAN);
//        add(test4);

			//CheckerPiece test5 = new CheckerPiece(8,8);
			//test5.setFillColor(Color.CYAN);
			//add(test5);
			
		} catch (IndexOutOfBoundsException e) {
			System.err.println("I caught IndexOutOfBoundsException: " + e.getMessage());
		}

	}//DrawCheckerPieces()

}
