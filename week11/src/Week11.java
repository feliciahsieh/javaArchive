
//import PlayingCard.CardMouseListener;
import acm.program.ConsoleProgram;

@SuppressWarnings("serial")
public class Week11 extends ConsoleProgram {
	public void init()
	{
		setFont("monospaced-plain-32");
		resize(800, 600);
	}
	
	public void run()
	{
		int myHeight = 5;//row
		int myWidth  = 10;//column
		Connect4Game myGame = new Connect4Game(myHeight, myWidth);
		println("Connect Four Game\n" + myGame);
		
		//Test if Column is Full
		
		for(int i=0;i<myWidth;i++)
		{
			if(myGame.ColumnIsFull(i))
				println("Main: Col: " + i + " is FULL");
			else
				println("Main: Col: " + i + " is Not Full");
		}
		
		if(myGame.boardIsFull())
			println("Board is FULL");
		else
			println("Board is NOT Full");
		
		myGame.playDisc(0, true);
		println(myGame);

		myGame.playDisc(1, false);
		println(myGame);
		
		myGame.playDisc(1, true);
		println(myGame);
		
		myGame.playDisc(0, false);
		println(myGame);
		
		myGame.playDisc(1, true);
		println(myGame);

		myGame.playDisc(0, false);
		println(myGame);

		myGame.playDisc(5, true);
		println(myGame);

		myGame.playDisc(5, false);
		println(myGame);

		myGame.playDisc(4, true);
		println(myGame);
		
		myGame.playDisc(3, false);
		println(myGame);
		
		myGame.playDisc(23, false);
		println(myGame);
	}//run()
	
}//Class Week11

