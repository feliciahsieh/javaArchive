import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

import acm.graphics.*;
import acm.program.GraphicsProgram;

@SuppressWarnings("serial")
public class MainProgram extends GraphicsProgram
{
	// board dimensions
	final static private int BOARD_WIDTH            = 600; 
	final static private int BOARD_HEIGHT           = 600;
	
	// vertical size of the paddle
	final static public int PADDLE_HEIGHT           = 10;
	final static public int PADDLE_YPOS             = 500;
	final static public int PADDLE_MOTION_PER_TICK  = 20;

	// Timer delay amount, used when the difficulty is set to 1.
	final private int STANDARD_TIMER_DELAY          = 40;
	
	// the number of misses allowed before the game ends
	final static private int MISSES_ALLOWED         = 20;

	// SWING COMPONENTS / CONTROLS
	static private JPanel controlPanel;
	static private JSlider slider;
	static private int difficultyLevel=1;
	static private JButton button;
	
	/*THE TIMER The game as a whole is "run" by a single timer object.*/
	static private Timer timer;

	/* ACTIVE GAME VARIABLES - Tracks things that change or move while the game is being played. */
	
	// The paddle and its current width
	static private GRect     paddle;
	static private int       currentPaddleWidth;
	static private boolean   isMoveLeft;
	// The set of crates
	static public ArrayList<Crate> crates;
	
	// The current score
	static private int       score = 0;
	static private JLabel    scoreLabel;
	static private int       missedLeft;
	static private JLabel    missedLabel;
	static private JLabel[]  highScoresLabel = new JLabel[5];
	static private JLabel    gameOverLabel;
	static private int[]     paddleLengths = {100,80,60,50,40};

	static private int[]     highScores = {0,0,0,0,0};
	
	private class SliderListener implements ChangeListener
	{
		@Override
		public void stateChanged(ChangeEvent e)
		{
			JSlider source = (JSlider)e.getSource();
			if (!source.getValueIsAdjusting())
			{    
				difficultyLevel = (int)source.getValue();
				//difficultyLevel = slider.getValue();
				System.out.println("Slider:"+slider.getValue()+" Diff:"+difficultyLevel);

				currentPaddleWidth = paddleLengths[difficultyLevel-1];
				paddle.setSize(currentPaddleWidth, paddle.getHeight());

				//Change difficulty Level if slider changed
				for(int c=0;c<crates.size();c++)
				{
					crates.get(c).resetDescentLevel(difficultyLevel);
				}
			}//if(!source.getValueIsAdjusting())

		}//stateChanged()
	}//ChangeListener (for Slider)

	private class StartButtonListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			System.out.println("Start button pressed");
			
			//Reset internal variables;
			score = 0;
			scoreLabel.setText("Score: "+score);

			missedLeft = MISSES_ALLOWED;
			missedLabel.setText("Missed Left: "+missedLeft);

			//Reset graphic objects
			gameOverLabel.setVisible(false);
			
			button.setEnabled(false);
			slider.setEnabled(false);
			
			paddle.setLocation((BOARD_WIDTH-currentPaddleWidth)/2, PADDLE_YPOS);
			
			drawAllCrates();
			
			//Start timer to start game
			timer.start();
			
		}//actionPerformed()
	}//StartButtonListener

	/* THE setupControls() METHOD sets up the control panel and places it on the right screen.*/
	private void setupControls()
	{
		controlPanel.add(new JLabel("Difficulty"));
		
		slider = new JSlider(1, 5, 1);
		slider.setMajorTickSpacing(1);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.setSnapToTicks(true);
		slider.addChangeListener(new SliderListener());
		controlPanel.add(slider);
		difficultyLevel = slider.getValue();

		
		button = new JButton("Start");
		button.addActionListener(new StartButtonListener());
		controlPanel.add(button);
		
		scoreLabel = new JLabel("Score: "+score);
		controlPanel.add(scoreLabel);
		missedLabel = new JLabel("Missed Left: "+missedLeft);
		controlPanel.add(missedLabel);
		
		// Creates vertical space between the button and the high score panel
		controlPanel.add(Box.createVerticalStrut(20));
		
		// Setup for the high score panel
		// NOTE: This needs to have a grid layout applied to it so the labels and numbers line up correctly.
		JPanel highscorePanel = new JPanel();
		highscorePanel.setBorder(new LineBorder(Color.WHITE, 20));
		JLabel highScoreText = new JLabel("    Level    High Score");
		highscorePanel.add(highScoreText);
		
		// Draw the high score display
		//GridLayout grid = new GridLayout(10,2);
		//highscorePanel.setLayout(highScoreBox);
		//for(int i=0;i<10;i++)
		//create new JLabel();
		for (int i = 0; i < 5; i++)
		{
			highScoresLabel[i] = new JLabel(Integer.toString(i+1)+"              "+Integer.toString(highScores[i]));
			highscorePanel.add(highScoresLabel[i]);
			highscorePanel.add(new JLabel("                                     "));
		}
		controlPanel.add(highscorePanel);
		
		// This is needed to make sure the components of the controlpanel don't "spread out" unnaturally
		controlPanel.add(Box.createGlue());
	}
	
	public void init()
	{
		missedLeft = MISSES_ALLOWED;
		
		setSize(BOARD_WIDTH+200, BOARD_HEIGHT);
		setBackground(Color.BLACK);

		//Initialize "Game Over" label but only show at game end
		gameOverLabel = new JLabel("       Game Over");
		gameOverLabel.setLocation(0, 250);
		gameOverLabel.setForeground(Color.LIGHT_GRAY);
		Font gameOverFont = new Font("Book Antiqua", Font.PLAIN, 60);
		gameOverLabel.setFont(gameOverFont);
		gameOverLabel.setVisible(false);
		getGCanvas().add(gameOverLabel);
		
		// Instantiate and set up the control panel
		controlPanel = new JPanel();
		controlPanel.setSize(200, BOARD_HEIGHT);
		controlPanel.setLocation(BOARD_WIDTH, 0);
		// line up components in a vertical stack
		controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
		
		// calling setupControls() to add all the individual components
		setupControls();
		
		// This loop makes all the components aligned nicely
		for (Component c : controlPanel.getComponents())
		{
			((JComponent)c).setAlignmentX(CENTER_ALIGNMENT);
		}
		// Add the control panel to the screen
		getGCanvas().add(controlPanel);
		
		// Instantiate the timer
		timer = new Timer(STANDARD_TIMER_DELAY, new TickTicker());
		
		//Draw Paddle
		currentPaddleWidth = paddleLengths[0];
		paddle = new GRect((BOARD_WIDTH-currentPaddleWidth)/2, PADDLE_YPOS, currentPaddleWidth, PADDLE_HEIGHT);
		paddle.setFillColor(Color.GRAY);
		paddle.setFilled(true);
		getGCanvas().add(paddle);
		
		//Draw Initial set of Crates
		drawInitCrates();

		//Add LEFT Arrow and RIGHT Arrow key listeners
		getGCanvas().addKeyListener(new KeyListener());
	}
	
	private class KeyListener extends KeyAdapter
	{
		@Override
		public void keyPressed(KeyEvent e)
		{
			int keyCode = e.getKeyCode();

			switch( keyCode )
			{ 
			case KeyEvent.VK_LEFT:
				isMoveLeft = true;
				break;
			case KeyEvent.VK_RIGHT :
				isMoveLeft = false;
				break;
			}//switch(keyCode)
		}//keyPressed()
		
	}//KeyListener
	
	public void drawAllCrates()
	{
		for(int c=0;c<crates.size();c++)
		{
			crates.get(c).crate.move(0, -crates.get(c).crateSize);
			//crates.get(c).crate.setVisible(true);

			//getGCanvas().add(crates.get(c).crate);
		}
	}//drawAllCrates
	
	public void drawInitCrates()
	{
		// Instantiate the list of crates
		Crate testCrate;
		crates = new ArrayList<Crate>();

		//Create first crate to compare to in the while loop below
		difficultyLevel = slider.getValue();
		System.out.println("drawInitCrates: Diff:"+difficultyLevel);
		crates.add(new Crate(0, difficultyLevel));
		getGCanvas().add(crates.get(0).crate);

		int i=1;
		int j;
		while(i<10)//Create 9 more types of crates
		{
			testCrate = new Crate(i, difficultyLevel);
			for(j=0;j<crates.size();j++)
			{
				if (PingUtil.objectsAreOverlapping(crates.get(j).crate, testCrate.crate))
				{
					//System.out.println("Objects overlap   i:"+i+"  j:"+j);
					break;
				}
			}
			
			if(j==crates.size())
			{
				crates.add(testCrate);
				getGCanvas().add(crates.get(i).crate);
				i++;
				System.out.println("Added crate#"+i+"  j:"+j);
			}
		}//while

	}

	public void recycleCrate(int index)
	{
		Random rand = new Random();
		int crateSize = rand.nextInt(80)+20;
		int crateXPosition = rand.nextInt(500-crateSize+50);

		System.out.println("recycleCrate():" + index);
		
		getGCanvas().remove(crates.get(index).crate);
		crates.get(index).crate.setLocation(crateXPosition, -crates.get(index).crateSize);
		getGCanvas().add(crates.get(index).crate);
	}
	
	/* THE TickTicker CLASS - Inner class that implements ActionListener.
	 * When the user hits the "Start" button, the timer object should be started to cause the actionPerformed 
	 * method in this class to get called at every timer tick.
	 * The body of the actionPerformed method needs to execute all motion and any other changes needed to
						    * handle updating the state of the game.*/
	private class TickTicker implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			int c;

			// Update the state of the game. This includes:
			
			// move the crates
			for(c=0;c<crates.size();c++)
			{
				crates.get(c).moveDown();
			}
			
			// * move the paddle (if it is moving)
			if(isMoveLeft)
			{
				if( (paddle.getX()) > 0 )
					paddle.move(-PADDLE_MOTION_PER_TICK,0);
				else//Set at left screen boundary
					paddle.setLocation(0, paddle.getY());
			}
			else
			{
				if( (paddle.getX()+currentPaddleWidth) < BOARD_WIDTH )
					paddle.move(PADDLE_MOTION_PER_TICK,0);
				else//Set at right screen boundary
					paddle.setLocation(BOARD_WIDTH-currentPaddleWidth,paddle.getY());
			}
			
			// * check for crates that have been caught
			for(c=0;c<crates.size();c++)
			{
				if (PingUtil.objectsAreOverlapping(paddle, crates.get(c).crate))
				{
					//System.out.println("Removing crate # "+ crates.get(c).id);
					recycleCrate(c);
					
					score = score + difficultyLevel;
					//System.out.println("TickTicker: Diff:"+difficultyLevel);
					
					scoreLabel.setText("Score: "+score);
					try//Play sound
					{
						InputStream inputStream = getClass().getResourceAsStream("button3.wav");
						AudioStream audioStream = new AudioStream(inputStream);
						AudioPlayer.player.start(audioStream);
					}
					catch (Exception ex)
					{
						System.out.println("Audio file error on Crate-Catch");
					}

				}//if(crates caught)
			}
			// * check for crates that have been missed
			for(c=0;c<crates.size();c++)
			{
				if (crates.get(c).crate.getY()+crates.get(c).crateSize>BOARD_HEIGHT)
				{
					//System.out.println("Crate " + crates.get(c).id + " reached bottom of board");
					recycleCrate(c);
					
					missedLeft--;
					missedLabel.setText("Missed Left: "+missedLeft);
					try//Play sound
					{
						InputStream inputStream = getClass().getResourceAsStream("click.wav");
						AudioStream audioStream = new AudioStream(inputStream);
						AudioPlayer.player.start(audioStream);
					}
					catch (Exception ex)
					{
						System.out.println("Audio file error on Crate-Miss");
					}
					
				}
			}
			
			// * play sounds as appropriate. Check whether the game is over, and react accordingly
			if(missedLeft<=0)
			{
				System.out.println("Must remove "+crates.size());
				
				//Hide all remaining crates
				for(c=0;c<crates.size();c++)
				{
					//System.out.println("Removing "+crates.size());
					getGCanvas().remove(crates.get(c).crate);
					//crates.get(c).crate.setVisible(false);
					//crates.get(c).crate.move(0,-crates.get(c).crateSize);
				}
				System.out.println("crates.size(): "+crates.size());

				gameOverLabel.setVisible(true);
				button.setEnabled(true);
				slider.setEnabled(true);

				timer.stop();
				System.out.println("Game Over");
				
				//Update High Scores
				if(highScores[difficultyLevel-1] < score)
				{
					highScores[difficultyLevel-1] = score;
					//UPDATE HIGH SCORES SCREEN
					highScoresLabel[difficultyLevel-1].setText(Integer.toString(difficultyLevel)+"              "+Integer.toString(score));
				}//if(HighScores[difficultyLevel]<score)
			}//if(missedLeft>0)

			//Focus on Canvas window so key press is registered
			getGCanvas().requestFocus();
			
		}//actionPerformed(ActionEvent e)

	}//class TickTicker
}//class MainProgram
