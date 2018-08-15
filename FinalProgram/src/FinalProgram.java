import acm.program.ConsoleProgram;

import java.util.Random;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

@SuppressWarnings("serial")
public class FinalProgram extends ConsoleProgram {
	static private JFrame window;

	static private JButton topButton, bottomButton;
	static private JLabel label;
    
	static private int score=0;
	static private boolean isTopButtonActive=true;
    
	static private Thread th;

    public class myMouseListener extends MouseAdapter
    {
	    public void mousePressed(MouseEvent e)
	    {
		    //System.out.println("myMouseListener");
		    //System.out.println("f:" + ((JButton) e.getSource()).getText()  );

		    if( ((JButton) e.getSource()).getText().equals("Click Now") )
		    {
			    score++;
		    }
		    else
		    {
			    score -= 5;
		    }
		    //Update score
		    label.setText(score+"");
	    }
    }
    public class myThread implements Runnable
    {
	    public void run()
	    {
		    int myDelay=0;
		    int turn;
		    
		    Random rand = new Random();
		    
		    while(true)
		    {
			    myDelay = rand.nextInt(6);
			    System.out.println("myThread: Start Pause of "+myDelay*1000);
			    pause(myDelay*1000);
			    System.out.println("myThread: Finished Pause");
			    
			    turn = rand.nextInt(2);
			    System.out.println("myThread: turn="+turn);
			    if (turn==0)
				    isTopButtonActive = true;
			    else if(turn==1)
				    isTopButtonActive = false;
			    else
				    System.out.println("Unexpected turn value");
			    
			    if(isTopButtonActive)
			    {
				    System.out.println("myThread: topButtonActive");
				    topButton.setText("Click Now");
				    bottomButton.setText("");
				    System.out.println("before pause");
				    pause(750);
				    System.out.println("after pause");
				    topButton.setText("");
				    System.out.println("End of if/else");
			    } else
			    {
				    System.out.println("myThread: NOT topButtonActive");
				    bottomButton.setText("Click Now");
				    topButton.setText("");
				    System.out.println("before pause");
				    pause(750);
				    System.out.println("after pause");
				    bottomButton.setText("");
				    System.out.println("End of if/else");
			    }
		    }//while            
	    }//run()

    }//class myThread

	public void run()
	{
		System.out.println("main():");
	}

	public void init()
	{
		window = new JFrame();
		window.setSize(300, 200);
		
		JPanel panel = new JPanel();

		panel.setLayout(new GridLayout(3,1));
		
		topButton = new JButton("Click Now");
		topButton.setName("topButton");
		topButton.addMouseListener(new myMouseListener());
		bottomButton = new JButton();
		bottomButton.setName("bottomButton");
		bottomButton.addMouseListener(new myMouseListener());
		
		label = new JLabel(score+"");
		
		panel.add(topButton);
		panel.add(bottomButton);
		panel.add(label);
		
		window.add(panel);
		window.setVisible(true);
		
		th = new Thread(new myThread());
		th.start();
	}
}
