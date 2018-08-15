import java.awt.Color;
import acm.graphics.*;

public class Spaceship extends GCompound {
	private boolean isGearDown;
	private int strength;
	
	private int xpos;
	private int ypos;
	private int shipWidth = 50;
	private int shipHeight = 25;

	public Spaceship(int s)
	{
		this.strength = s;
		
		//Draw ship
		int x = (int) (Math.random() * 600 + 100);
		int y = 500;
		
		this.xpos = x;
		this.ypos = y;

		GRoundRect body = new GRoundRect(x, y, shipWidth, shipHeight);
		GOval oval = new GOval(x+5, y-5, shipWidth-10, 10);
		GRect leftgear = new GRect(x+10,y+shipHeight+2,10,10);
		GRect rightgear = new GRect(x+shipWidth-20,y+shipHeight+2,10,10);
		
		this.isGearDown = true;
		
		if(Math.random()<0.5)
		{
			body.setColor(Color.BLUE);
			oval.setColor(Color.BLUE);
		} else
		{
			body.setColor(Color.CYAN);
			oval.setColor(Color.CYAN);
		}
		leftgear.setColor(Color.DARK_GRAY);
		rightgear.setColor(Color.DARK_GRAY);
		
		body.setFilled(true);
		oval.setFilled(true);
		leftgear.setFilled(true);
		rightgear.setFilled(true);

		add(body);
		add(oval);
		add(rightgear);
		add(leftgear);
		
	}
	
	public void toggleLandingGear()
	{
		GRect left, right;
		
		left  = new GRect(this.xpos+10,this.ypos+this.shipHeight+2,10,10);
		right = new GRect(this.xpos+shipWidth-20,this.ypos+this.shipHeight+2,10,10);
		if(this.isGearDown)
		{
			left.setColor(Color.WHITE);
			right.setColor(Color.WHITE);
			left.setFilled(true);
			right.setFilled(true);
			add(left);
			add(right);
		} else
		{
			left.setColor(Color.DARK_GRAY);
			right.setColor(Color.DARK_GRAY);
			left.setFilled(true);
			right.setFilled(true);
			add(left);
			add(right);
		}
		this.isGearDown = !this.isGearDown;

	}
	
	public boolean getLandingGearState()
	{
		return this.isGearDown;
	}
	
	public int getHullStrength()
	{
		return this.strength;
	}
	
	public void damageShip(double damage)
	{
		this.strength = (int) (Math.round(this.strength * (1.0 - damage)));
		if(this.strength<10)
		{
			GRoundRect body = new GRoundRect(this.xpos, this.ypos, shipWidth, shipHeight);
			GOval oval = new GOval(this.xpos+5, this.ypos-5, shipWidth-10, 10);
			
			body.setColor(Color.RED);
			body.setFilled(true);
			add(body);

			oval.setColor(Color.RED);
			oval.setFilled(true);
			add(oval);
			
			if(this.getLandingGearState())
			{
				GRect left  = new GRect(this.xpos+10,this.ypos+this.shipHeight+2,10,10);
				GRect right = new GRect(this.xpos+shipWidth-20,this.ypos+this.shipHeight+2,10,10);
				if(this.isGearDown)
				{
					left.setColor(Color.RED);
					right.setColor(Color.RED);
					left.setFilled(true);
					right.setFilled(true);
					add(left);
					add(right);
				} else
				{
					left.setColor(Color.WHITE);
					right.setColor(Color.WHITE);
				}
			}
		}
	}
	
	public void setNewPosition(int x, int y)
	{
		this.xpos += x;
		this.ypos += y;
	}
	
}
