import static org.junit.Assert.*;

import org.junit.Test;


public class MultidayEventTest {

	@Test
	public void testConstructor() {
		MultidayAppointment a = new MultidayAppointment("party", 2014, 1, 1, 2014,  1, 1);
		assertEquals("party is happening on 1/1/2014 to 1/1/2014", a.toString());
		
		MultidayAppointment a2 = new MultidayAppointment("party", 2014, 1, 1, 2014,  1, 2);
		assertEquals("party is happening on 1/1/2014 to 1/2/2014", a2.toString());
	}

	@Test
	public void testsetEndDate() {
		MultidayAppointment a = new MultidayAppointment("party", 2014, 1, 1, 2014, 2, 1);
		assertEquals("party is happening on 1/1/2014 to 2/1/2014", a.toString());
		a.setEndDate(2014, 2, 28);
		assertEquals("party is happening on 1/1/2014 to 2/28/2014", a.toString());
		a.setEndDate(2014, 3, 31);
		assertEquals("party is happening on 1/1/2014 to 3/31/2014", a.toString());

		//Test invalid arguments
		//Test invalid year
		a.setEndDate(0, 1, 31);
		assertEquals("party is happening on 1/1/2014 to 3/31/2014", a.toString());
		a.setEndDate(-1, 1, 31);
		assertEquals("party is happening on 1/1/2014 to 3/31/2014", a.toString());
		a.setEndDate(5000, 12, 31);  //valid year
		assertEquals("party is happening on 1/1/2014 to 12/31/5000", a.toString());
		//Test invalid month
		a.setEndDate(2014, 0, 31);
		assertEquals("party is happening on 1/1/2014 to 12/31/5000", a.toString());
		a.setEndDate(2014, 13, 31);
		assertEquals("party is happening on 1/1/2014 to 12/31/5000", a.toString());
		a.setEndDate(2014, -1, 31);
		assertEquals("party is happening on 1/1/2014 to 12/31/5000", a.toString());
		
		//Test invalid day
		a.setEndDate(2014, 1, 0);
		assertEquals("party is happening on 1/1/2014 to 12/31/5000", a.toString());
		a.setEndDate(2014, 1, 32);
		assertEquals("party is happening on 1/1/2014 to 12/31/5000", a.toString());
		a.setEndDate(2014, 1, -1);
		assertEquals("party is happening on 1/1/2014 to 12/31/5000", a.toString());

		//Test Month Boundaries
		a.setEndDate(2014, 0, 1);
		assertEquals("party is happening on 1/1/2014 to 12/31/5000", a.toString());
		a.setEndDate(2014, 13, 1);
		assertEquals("party is happening on 1/1/2014 to 12/31/5000", a.toString());
		a.setEndDate(2014, -1, 1);
		assertEquals("party is happening on 1/1/2014 to 12/31/5000", a.toString());
 
		//Test After the date
		MultidayAppointment after = new MultidayAppointment("party", 2014, 2, 1, 2014, 2, 1);
		assertEquals("party is happening on 2/1/2014 to 2/1/2014", after.toString());
		after.setEndDate(2014, 1, 31);
		assertEquals("party is happening on 2/1/2014 to 2/1/2014", after.toString());
		assertEquals(1, after.duration());
	}

	@Test
	public void testduration()
	{
		MultidayAppointment a = new MultidayAppointment("Game", 2014, 1, 1, 2014,  1, 1);
		
		assertEquals("Game is happening on 1/1/2014 to 1/1/2014", a.toString());
		assertEquals(1, a.duration());

		a.setEndDate(2014,1,2);
		assertEquals(2, a.duration());
		
		a.setEndDate(2014,1,3);
		assertEquals(3, a.duration());
		
		//Test Month Boundaries
		a.setDate(2014, 1, 31);
		a.setEndDate(2014,2,1);
		assertEquals(2, a.duration());

		a.setDate(2014, 2, 28);
		a.setEndDate(2014,3,1);
		assertEquals(2, a.duration());

		a.setDate(2014, 3, 31);
		a.setEndDate(2014,4,1);
		assertEquals(2, a.duration());

		a.setDate(2014, 4, 30);
		a.setEndDate(2014,5,1);
		assertEquals(2, a.duration());

		a.setDate(2014, 5, 31);
		a.setEndDate(2014,6,1);
		assertEquals(2, a.duration());

		a.setDate(2014, 6, 30);
		a.setEndDate(2014,7,1);
		assertEquals(2, a.duration());

		a.setDate(2014, 7, 31);
		a.setEndDate(2014,8,1);
		assertEquals(2, a.duration());

		a.setDate(2014, 8, 31);
		a.setEndDate(2014,9,1);
		assertEquals(2, a.duration());

		a.setDate(2014, 9, 30);
		a.setEndDate(2014,10,1);
		assertEquals(2, a.duration());

		a.setDate(2014, 10, 31);
		a.setEndDate(2014,11,1);
		assertEquals(2, a.duration());

		a.setDate(2014, 11, 30);
		a.setEndDate(2014,12,1);
		assertEquals(2, a.duration());

		a.setDate(2014, 12, 31);
		a.setEndDate(2015,1,1);
		assertEquals(2, a.duration());
		a.setEndDate(2015,1,2);
		assertEquals(3, a.duration());
	}

}
