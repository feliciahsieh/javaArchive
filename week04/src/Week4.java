//Felicia Hsieh
import acm.program.GraphicsProgram;
@SuppressWarnings("serial")
public class Week4 extends GraphicsProgram {
	
	public void init()
	{
		int maxWidth=800, maxHeight=600;
		resize(maxWidth, maxHeight);
	}
	public void run()
	{
		//Task 1: Appliance
		println("\nTask 1: Appliance\n");
		
		println("Creating Dryer with 110 W");
		Appliance appl = new Appliance("Dryer", 110);
		System.out.println(appl.toString());
		
		println("Creating Washer with 222 W");
		Appliance appl2 = new Appliance("Washer", 222);
		System.out.println(appl2.toString());
		
		println("Creating Refrigerator with 330 W");
		Appliance appl3 = new Appliance("Refrigerator", 330);
		System.out.println(appl3.toString());

		println("Toggling Power on all\n");
		appl.togglePower();
		appl2.togglePower();
		appl3.togglePower();
		
		System.out.println(appl);//Calls appl's toString() method
		System.out.println(appl2);
		System.out.println(appl3);

		//Task 2: Openable Appliance
		println("\nTask 2: Openable Appliance\n");
		OpenableAppliance openAppl = new OpenableAppliance("FRIDGE",45);
		System.out.println("My OpenableAppliance: FRIDGE");
		
		System.out.println(openAppl.toString());
		
		//Add items
		System.out.println("Toggle");
		openAppl.toggleOpen();
		System.out.println(openAppl);

		System.out.println("Insert Bread");
		openAppl.insert("Bread");
		System.out.println(openAppl);
		
		System.out.println("Remove Contents");
		openAppl.removeContents();
		System.out.println(openAppl);

		//Remove items
		System.out.println("Toggle");
		openAppl.toggleOpen();
		System.out.println(openAppl);

		System.out.println("Insert Milk");
		openAppl.insert("Milk");
		System.out.println(openAppl);

		System.out.println("Remove Contents");
		openAppl.removeContents();
		System.out.println(openAppl);

		//Task 3: Spaceship
		println("\nTask 3: Spaceship\n");
		double damage;

		String input;
		int i, shipStrength;
		input = readLine("What is the strength of the ship's hull?");
		shipStrength = Integer.parseInt(input);
		
		//Create Spaceships
		Spaceship[] ships = new Spaceship[5];
		for(i=0;i<ships.length;i++)
		{
			ships[i] = new Spaceship(shipStrength);
			add(ships[i]);
		}
		input = readLine("Type <Enter> to Launch");
		
		System.out.println("Toggling all Landing Gears");
		for(i=0;i<ships.length;i++)
		{
			ships[i].toggleLandingGear();
		}
		for(i=0;i<ships.length;i++)
		{
			ships[i].move(0,-10);
			pause(250);
		}

		int x,y;
		while(true)
		{
			for(i=0;i<ships.length;i++)
			{
				x = (int) (Math.random()*10-5);
				y = (int) (Math.random()*5-10);

				ships[i].move(x,y);
				
				//Damage Ship
				damage = Math.random()*.20 + 0.05;
				ships[i].damageShip(damage);
				
				System.out.print("Strength 1:" + ships[0].getHullStrength() +
						 " 2:" + ships[1].getHullStrength() +
						 " 3:" + ships[2].getHullStrength() +
						 " 4:" + ships[3].getHullStrength() +
						 " 5:" + ships[4].getHullStrength() +
						 "    Damaged Ship:" + (i+1) + " by ");
				System.out.printf("%6.2f%%\n", damage*100);
				pause(500);
			}
		}
		
	}//Public void run()
}//Public class Week4
