import java.util.Date;
import java.util.Random;
import acm.program.ConsoleProgram;

@SuppressWarnings("serial")
public class Week12 extends ConsoleProgram {
	private Random r = new Random();
	
	public void init()
	{
		resize(800, 600);
		setFont("monospace-plain-32");
	}
	public void run()
	{
		Date date = new Date(0,1,1950);
		String s = "nowhere";

		try
		{
			LifeRecord a = new LifeRecord("Grandma A", date, s);
			LifeRecord b = new LifeRecord("Mom B", date, s);
			LifeRecord c = new LifeRecord("Uncle C", date, s);
			b.setParents(a,null);
			c.setParents(a,null);
			LifeRecord d = new LifeRecord("Daughter D", date, s);
			d.setParents(b, null);
			LifeRecord e = new LifeRecord("Son E", date, s);
			e.setParents(b, null);
			LifeRecord f = new LifeRecord("Daughter F", date, s);
			f.setParents(b, null);

			//Missing some code here
			f.setParents(c, null);

			System.out.println(f.maternalAncestors());
			//System.out.println("Siblings:" + d.countSiblings());
		}
		catch(BadDataException ex)
		{
			println("Sorry, bad data was given: " + ex.getMessage());
		}
		
	}
}
