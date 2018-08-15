import acm.program.*;

@SuppressWarnings("serial")
public class MainProgram extends GraphicsProgram
{
	Connect4Game game;

	public void init()
	{
		resize(800, 600);
	}
	public void run()
	{
		//Original Game
		game = new Connect4Game(7, 6, getGCanvas());
	}
}
