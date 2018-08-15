import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;



//import acm.program.ConsoleProgram;
import acm.graphics.*;
import acm.program.GraphicsProgram;

@SuppressWarnings("serial")
public class Week14 extends GraphicsProgram {
	public void init()
	{
		//setFont("monospaced-plain-32");
		//resize(800, 600);
	}
	public void run()
	{
		Box<String> box = new Box<String>(3);
		Box<Integer> i = new Box<Integer>(3);
		
		box.add("hello");
		box.add("world");
		i.add(1);
		i.add(2);
		
		System.out.println("box" + box);
		System.out.println("i" + i);
		
		//***********
		Box<String> ss = new Box<String>(3);
		ss.add("Felicia");
		ss.add("Hsieh");
		ss.add("Cool");
		ss.add("yo");
		System.out.println("ss1" + ss);
		ss.remove("Cool");
		ss.add("Super Cool");
		System.out.println("ss2" + ss);
		
		//***********
		System.out.println("Binary Search Tree Example");
		
		BST<Integer> myBST = new BST<Integer>();
		myBST.insert(3);
		myBST.insert(1);
		myBST.insert(0);
		myBST.insert(5);
		myBST.insert(10);
		myBST.insert(12);
		myBST.insert(7);
		myBST.insert(2);
		myBST.insert(4);

		
		myBST.delete(5);

		myBST.printTree();
		
	}//run

	//************FOR QUIZ 12/2/14
	/*
	  PrioritySet<Integer> ps = new PrioritySet<Integer>();
	  ps.insert(3);
	  ps.insert(100);
	  ps.insert(50);
	  
	  System.out.println("ps: "+ ps);
	  System.out.println("Largest: "+ ps.getLargest());
	  
	  //************FOR QUIZ 12/2/14
	  Player a = new Player("Alice");
	  Player b = new Player("Bob");
	  Player c = new Player("Carl");
	  a.setTarget(b);
	  b.setTarget(c);
	  //System.out.println("Player a has Target: "+a);
	  //System.out.println("Player b: has Target: "+b);
	  //System.out.println("Player c: has Target: "+c);
	  a.printTargetChain();
	  
	  //****************
	  GRect rect = new GRect(0,50,50,50);
	  getGCanvas().addKeyListener(new KeyListener());
	  rect.setFillColor(Color.BLUE);
	  rect.setFilled(true);
	  add(rect);
	  pause(1000);
	  rect.move(50, 0);
	  
	  //**************
	  */
}
