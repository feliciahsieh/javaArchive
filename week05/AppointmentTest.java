import static org.junit.Assert.*;

import org.junit.Test;


public class AppointmentTest {

	@Test
	public void testConstructor() {
		Appointment a = new Appointment("party", 2014, 1, 1);
		assertEquals("party is happening on 1/1/2014", a.toString());
	}
	@Test
	public void testsetDate() {
		Appointment a = new Appointment("party", 2014, 1, 31);
		assertEquals("party is happening on 1/31/2014", a.toString());
		a.setDate(2014, 2, 28);
		assertEquals("party is happening on 2/28/2014", a.toString());
		a.setDate(2014, 3, 31);
		assertEquals("party is happening on 3/31/2014", a.toString());
		a.setDate(0, 1, 31);
		//Test invalid arguments
		assertEquals("party is happening on 3/31/2014", a.toString());
		a.setDate(-1, 1, 31);
		assertEquals("party is happening on 3/31/2014", a.toString());
		a.setDate(5000, 12, 31);
		assertEquals("party is happening on 12/31/5000", a.toString());
		a.setDate(2014, 0, 31);
		assertEquals("party is happening on 12/31/5000", a.toString());
		a.setDate(2014, 13, 31);
		assertEquals("party is happening on 12/31/5000", a.toString());
		a.setDate(2014, -1, 31);
		assertEquals("party is happening on 12/31/5000", a.toString());
		//Test day
		a.setDate(2014, 1, 0);
		assertEquals("party is happening on 12/31/5000", a.toString());
		a.setDate(2014, 1, 32);
		assertEquals("party is happening on 12/31/5000", a.toString());
		a.setDate(2014, 1, -1);
		assertEquals("party is happening on 12/31/5000", a.toString());
		//Test Month Boundaries
	}
	@Test
	public void testDayLater()
	{
		Appointment a = new Appointment("party", 2014, 1, 1);
		
		a.setDate(2014, 1, 31);
		a.moveOneDayLater();
		assertEquals("party is happening on 2/1/2014", a.toString());
		a.setDate(2014, 2, 28);
		a.moveOneDayLater();
		assertEquals("party is happening on 3/1/2014", a.toString());
		a.setDate(2014, 3, 31);
		a.moveOneDayLater();
		assertEquals("party is happening on 4/1/2014", a.toString());
		a.setDate(2014, 4, 30);
		a.moveOneDayLater();
		assertEquals("party is happening on 5/1/2014", a.toString());
		a.setDate(2014, 5, 31);
		a.moveOneDayLater();
		assertEquals("party is happening on 6/1/2014", a.toString());
		a.setDate(2014, 6, 30);
		a.moveOneDayLater();
		assertEquals("party is happening on 7/1/2014", a.toString());
		a.setDate(2014, 7, 31);
		a.moveOneDayLater();
		assertEquals("party is happening on 8/1/2014", a.toString());
		a.setDate(2014, 8, 31);
		a.moveOneDayLater();
		assertEquals("party is happening on 9/1/2014", a.toString());
		a.setDate(2014, 9, 30);
		a.moveOneDayLater();
		assertEquals("party is happening on 10/1/2014", a.toString());
		a.setDate(2014, 10, 31);
		a.moveOneDayLater();
		assertEquals("party is happening on 11/1/2014", a.toString());
		a.setDate(2014, 11, 30);
		a.moveOneDayLater();
		assertEquals("party is happening on 12/1/2014", a.toString());
		a.setDate(2014, 12, 31);
		a.moveOneDayLater();
		assertEquals("party is happening on 1/1/2015", a.toString());
	}
	@Test
	public void testDayEarlier()
	{
		Appointment a = new Appointment("party", 2015, 1, 1);
		a.moveOneDayEarlier();
		assertEquals("party is happening on 12/31/2014", a.toString());
		a.setDate(2014, 2, 1);
		a.moveOneDayEarlier();
		assertEquals("party is happening on 1/31/2014", a.toString());
		a.setDate(2014, 3, 1);
		a.moveOneDayEarlier();
		assertEquals("party is happening on 2/28/2014", a.toString());
		a.setDate(2014, 4, 1);
		a.moveOneDayEarlier();
		assertEquals("party is happening on 3/31/2014", a.toString());
		a.setDate(2014, 5, 1);
		a.moveOneDayEarlier();
		assertEquals("party is happening on 4/30/2014", a.toString());
		a.setDate(2014, 6, 1);
		a.moveOneDayEarlier();
		assertEquals("party is happening on 5/31/2014", a.toString());
		a.setDate(2014, 7, 1);
		a.moveOneDayEarlier();
		assertEquals("party is happening on 6/30/2014", a.toString());
		a.setDate(2014, 8, 1);
		a.moveOneDayEarlier();
		assertEquals("party is happening on 7/31/2014", a.toString());
		a.setDate(2014, 9, 1);
		a.moveOneDayEarlier();
		assertEquals("party is happening on 8/31/2014", a.toString());
		a.setDate(2014, 10, 1);
		a.moveOneDayEarlier();
		assertEquals("party is happening on 9/30/2014", a.toString());
		a.setDate(2014, 11, 1);
		a.moveOneDayEarlier();
		assertEquals("party is happening on 10/31/2014", a.toString());
		a.setDate(2014, 12, 1);
		a.moveOneDayEarlier();
		assertEquals("party is happening on 11/30/2014", a.toString());
		a.setDate(2014, 1, 1);
		a.moveOneDayEarlier();
		assertEquals("party is happening on 12/31/2013", a.toString());
	}

	@Test
	public void testsetEvent()
	{
		Appointment a = new Appointment("party", 2014, 1, 1);
		
		a.setEvent("Book reading");
		assertEquals("Book reading is happening on 1/1/2014", a.toString());

		a.setEvent("");
		assertEquals("Book reading is happening on 1/1/2014", a.toString());

	}
}
