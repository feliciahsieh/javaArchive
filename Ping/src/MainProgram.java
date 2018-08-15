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
	/*
	 * CONSTANTS
	 * All of these variables are constants ("final" in Java terms)
	 * that control various aspects of the game.
	 * You might need to "tweak" them to get the exact behavior
	 * you want, but they should also work well enough as is.
	 */
	// board dimensions
	final private int BOARD_WIDTH                   = 600; 
	final private int BOARD_HEIGHT                  = 600; 
	// vertical size of the paddle
	final private int PADDLE_HEIGHT                 = 10;
	final static public int PADDLE_YPOS             = 500;
	final private int PADDLE_MOTION_PER_TICK        = 12;
	// the standard timer delay amount,
	// used when the difficulty is set to 1.
	final private int STANDARD_TIMER_DELAY          = 40;
	// the number of misses allowed before the game ends
	final private int MISSES_ALLOWED               = 20;
	// how far the paddle moves, if it is moving, during each timer tick

	private JPanel controlPanel;
	private JSlider slider;
	private JButton button;
	
	private Timer timer;

	/*
	 * ACTIVE GAME VARIABLES
	 * This set of fields includes variables
	 * for tracking things that change or move while the game
	 * is being played.
	 */
	// The paddle and its current width
	static private GRect     paddle;
	static private int       currentPaddleWidth;
	static private boolean   isMoveLeft;

	// The set of crates
	static private ArrayList<Crate> crates = new ArrayList<Crate>(10);
	// Whether or not the game is running
	static private boolean running = false;
	
	// Internal variables
	static private int       score = 0;
	static private JLabel    scoreLabel;
	static private int       missedLeft;
	static private JLabel    missedLabel;
	static private JLabel[]  highScoresLabel = new JLabel[5];
	static private JLabel    gameOverLabel;
	static private int[]     paddleLengths = {100,80,60,50,40};
	static private int[]     highScores = {0,0,0,0,0};
	static private int       difficultyLevel;
	
	
	/*
	 * THE setupControls() METHOD
	 * This method is **partially** complete.
	 * It sets up the control panel and places it in the right
	 * place on the screen.
	 */
	private void setupControls()
	{
		slider = new JSlider(1, 5, 1);

		controlPanel.add(new JLabel("Difficulty"));
		
		slider = new JSlider(1, 5, 1);
		slider.setMajorTickSpacing(1);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.setSnapToTicks(true);
		slider.addChangeListener(new SliderListener());
		controlPanel.add(slider);
		
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
		
		//Draw Paddle
		currentPaddleWidth = paddleLengths[0];
		paddle = new GRect((BOARD_WIDTH-currentPaddleWidth)/2, PADDLE_YPOS, currentPaddleWidth, PADDLE_HEIGHT);
		paddle.setFillColor(Color.GRAY);
		paddle.setFilled(true);
		getGCanvas().add(paddle);
		
		//System.out.println("Screen Width:" + getGCanvas().getWidth());
		
	}
	
	private class SliderListener implements ChangeListener
	{
		@Override
		public void stateChanged(ChangeEvent e)
		{
			JSlider source = (JSlider)e.getSource();
			if (!source.getValueIsAdjusting())
			{    
				difficultyLevel = slider.getValue();
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

			//Reset graphic objects
			gameOverLabel.setVisible(false);

			difficultyLevel = slider.getValue();
			
			//Disable button and slider
			button.setEnabled(false);
			slider.setEnabled(false);

			//Reset internal variables;
			score = 0;
			scoreLabel.setText("Score: "+score);

			missedLeft = MISSES_ALLOWED;
			missedLabel.setText("Missed Left: "+missedLeft);
			
			createCrates();

			//Start timer to start game
			System.out.println("Before Timer Start");
			timer.start();
			System.out.println("After Timer Start");
			
		}//actionPerformed()
	}//StartButtonListener

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

	/*
	 * THE init() METHOD
	 * This is the GraphicsProgram's equivalent of
	 * a "main function." Program execution will begin here.
	 */
	public void init()
	{
		setSize(BOARD_WIDTH+200, BOARD_HEIGHT);
		setBackground(Color.BLACK);

		// Instantiate the list of crates
		crates = new ArrayList<Crate>();
		difficultyLevel = 1;

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

		//Add LEFT Arrow and RIGHT Arrow key listeners
		getGCanvas().addKeyListener(new KeyListener());
		
		//Initialize "Game Over" label but only show at game end
		gameOverLabel = new JLabel("       Game Over");
		gameOverLabel.setLocation(0, 250);
		gameOverLabel.setForeground(Color.LIGHT_GRAY);
		Font gameOverFont = new Font("Book Antiqua", Font.PLAIN, 60);
		gameOverLabel.setFont(gameOverFont);
		gameOverLabel.setVisible(false);
		getGCanvas().add(gameOverLabel);
		
		createCrates();

	}

	
	/*
	 * THE TickTicker CLASS
	 * This is an inner class that implements ActionListener.
	 * When the user hits the "Start" button, the timer object
	 * should be started. That will cause the actionPerformed 
	 * method in this class to get called at every timer tick.
	 * The body of the actionPerformed method needs to 
	 * execute all motion and any other changes needed to
	 * handle updating the state of the game.
	 */
	private class TickTicker implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			int c;
			System.out.println("TT 1");
			// Update the state of the game. This includes:
			// * move the crates
			for(c=0;c<crates.size();c++)
			{
				crates.get(c).moveDown();
			}
			
			System.out.println("TT 2");
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

			System.out.println("TT 3");
			// * check for crates that have been caught
			for(c=0;c<crates.size();c++)
			{
				if (PingUtil.objectsAreOverlapping(paddle, crates.get(c).crate))
				{
					System.out.println("TickTicker:Caught Crate:"+ crates.get(c).id);
					
					crates.get(c).recycleCrate(BOARD_WIDTH);
					
					score = score + difficultyLevel;
					scoreLabel.setText("Score: "+score);
					
					try//Play sound
					{
						InputStream inputStream = getClass().getResourceAsStream("button.wav");
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
			System.out.println("TT 4");
			for(c=0;c<crates.size();c++)
			{
				if (crates.get(c).crate.getY()+crates.get(c).crateSize>BOARD_HEIGHT)
				{
					//System.out.println("Crate " + crates.get(c).id + " reached bottom of board");
					crates.get(c).recycleCrate(BOARD_WIDTH);

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

			// * play sounds as appropriate. check whether the game is over, and react accordingly
			System.out.println("TT 5a");

			if(missedLeft<=0)
			{
				timer.stop();
				System.out.println("TT 5b");
				
				running=false;
				
				System.out.println("Game Over");
				
				for(c=0;c<crates.size();c++)
				{
					crates.get(c).crateDescentRate=0;
				}
				System.out.println("TT 5c");
				
				//Update High Scores
				if(highScores[difficultyLevel-1] < score)
				{
					highScores[difficultyLevel-1] = score;
					//UPDATE HIGH SCORES SCREEN
					highScoresLabel[difficultyLevel-1].setText(Integer.toString(difficultyLevel)+"              "+Integer.toString(score));
				}//if(HighScores[difficultyLevel]<score)

				gameOverLabel.setVisible(true);
				button.setEnabled(true);
				slider.setEnabled(true);

				System.out.println("TT 6");

				//Remove Crates
				if(crates.size()>0)
				{
					for(c=crates.size()-1;c>=0;c--)
					{
						System.out.println("Game Over:Removing Crate:"+crates.get(c).id);
						remove(crates.get(c).crate);//from screen
						//pause(100);
						crates.remove(c);//from ArrayList
						
					}
					System.out.println("Remove Crates end: crates.size():"+crates.size());
				}
			}//if missedLeft<=0

			System.out.println("TT 7");

			//Focus on Canvas window so key press is registered
			requestFocus();
			System.out.println("TT 8");
		}
	}

	public void createCrates()
	{
		System.out.println("createCratesSTARTED: difficultyLevel:"+difficultyLevel);
		for(int c=0;c<10;c++)
		{
			crates.add(new Crate(c,difficultyLevel,BOARD_WIDTH));
			add(crates.get(c).crate);
		}
		System.out.println("createCratesFINISHED: difficultyLevel:"+difficultyLevel);
	}//createCrates()
	

	public void createCrates2()
	{
		System.out.println("createCrates 2 STARTED: difficultyLevel:"+difficultyLevel);
		for(int c=0;c<10;c++)
		{
//crates.add(new Crate(c,difficultyLevel,BOARD_WIDTH));
//add(crates.get(c).crate);

			Random rand = new Random();
			//Generate random Crate Position above screen
			int crateXPosition = rand.nextInt(600-crates.get(c).crateSize);//0 to (600-crateSize)

			crates.get(c).setLocation(crateXPosition, -crates.get(c).crateSize-1);
		}
		System.out.println("createCrates 2 FINISHED: difficultyLevel:"+difficultyLevel);
	}//createCrates2()

}

