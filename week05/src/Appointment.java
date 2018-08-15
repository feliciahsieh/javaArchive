public class Appointment
{
	protected String event;
	protected int year1;
	protected int month1;
	protected int day1;
   
	public Appointment(String what, int y, int m, int d)
	{
		event = what;
		year1 = y;
		month1 = m;
		day1 = d;
	}

	//Create and return a String that includes information about the appointment,
	//including what the event is and its full date. (The format is up to you!)
	public String toString()
	{
		return this.event + " is happening on " + this.month1 + "/" + this.day1 + "/" + this.year1;
	}
   
	//Change the appointment so its date is changed to the year, month and date given.
	public void setDate(int y, int m, int d)
	{
		boolean isGoodDate=false;
		   
		if( (y>0) && (y<10000) )
		{
			if(m>0 && m<13)
			{
				//Month is good
				if(d<1)
				{
					System.out.println("setDate: Day " + d + " is out of range");
				} else
				{
					if( (m==1 || m==3 || m==5 || m==7 || m==8 || m==10 || m==12 ) && d<32)//31 days in month
					{
						isGoodDate = true;
					}
					else if ( (m==4 || m==6 || m==9 || m==11) && d<31)//30 days in month
					{
						isGoodDate = true;   
					}
					else if( (m==2) && d<29)//28 days in month (not counting leap year)
					{
						isGoodDate = true;
					}
					else
						System.out.println("setDate: Day " + d + " is out of range");
				}
			}
			else
				System.out.println("setDate: Month " + m + " is out of range");
		} else
			System.out.println("setDate: Year " + y + " is out of range");

		if(isGoodDate)
		{
			this.year1 = y;
			this.month1 = m;
			this.day1 = d;
		}
	}
	//Change the appointment so its event is set to the string given.
	public void setEvent(String what)
	{   
		if(!what.equals(""))
			this.event = what;
		else
			System.out.println("setEvent: Invalid event name");
	}
   
	//Change the appointment so its date is one day later.
	public void moveOneDayLater()
	{
		//31: Jan, Mar, May, Jul, Aug, Oct, Dec
		//30: Apr, Jun, Sep, Nov
		//28: Feb
		this.day1++;
		   
		if ((this.month1==2) && (this.day1==29))
		{
			this.month1 = 3;
			this.day1 = 1;
		} else
		{
			   
			switch (this.month1)
			{
			case 1:
			case 3:
			case 5:
			case 7:
			case 8:
			case 10:
				if (this.day1==32)
				{
					this.day1 = 1;
					this.month1++;
				}
				break;
				
			case 4:
			case 6:
			case 9:
			case 11:
				if (this.day1==31)
				{
					this.day1 = 1;
					this.month1++;
				}
				break;
				
			case 2:
				if (this.day1==29)
				{
					this.day1 = 1;
					this.month1++;
				}   
				break;
				
			case 12:
				if (this.day1==32)
				{
					this.month1 = 1;
					this.day1 = 1;
					this.year1++;
				}
				break;

			default: System.out.println("moveOneDayLater: Invalid Date");
				break;
			}
		}
	}
   
	//Change the appointment so its date is one day earlier.
	//31: Jan, Mar, May, Jul, Aug, Oct, Dec
	//30: Apr, Jun, Sep, Nov
	//28: Feb
	public void moveOneDayEarlier()
	{
		this.day1--;
		   
		switch (this.month1)
		{
		case 1:
			if(this.day1==0)
			{
				this.month1 = 12;
				this.day1 = 31;
				this.year1--;
			}
			break;
			
		case 3:
			if (this.day1==0)
			{
				this.day1 = 28;
				this.month1--;
			}
			break;
			
		case 5:
		case 7:
		case 10:
		case 12:
			if (this.day1==0)
			{
				this.day1 = 30;
				this.month1--;
			}
			break;
			
		case 2:
		case 4:
		case 6:
		case 8:
		case 9:
		case 11:
			if (this.day1==0)
			{
				this.day1 = 31;
				this.month1--;
			}
			break;

		default: System.out.println("moveOneDayEarlier: Invalid Date");
			break;
		}
	}
   
}//class Appointment
