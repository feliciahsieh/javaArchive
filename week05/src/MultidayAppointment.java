
public class MultidayAppointment extends Appointment{
	protected int year2;
	protected int month2;
	protected int day2;
	   
	public MultidayAppointment(String what, int y1, int m1, int d1, int y2, int m2, int d2)
	{
		super(what, y1, m1, d1);
		this.year2 = y2;
		this.month2 = m2;
		this.day2 = d2;
	}
	
	public String toString()
	{
		return super.toString() + " to " + this.month2 + "/" + this.day2 + "/" + this.year2;
	}
	
	public void setEndDate(int y, int m, int d)
	{
		boolean isGoodDate=false;
		   
		//Check Year, Month, and Day ranges
		if(y>0)
		{
			if((m>0) && (m<13))
			{
				if(d>0)
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
						System.out.println("setEndDate: Day " + d + " is out of range");
				}
				else
					System.out.println("setEndDate: Day " + d + " is out of range");   
			}
			else
				System.out.println("setEndDate: Month " + m + " is out of range");
		} else
			System.out.println("setEndDate: Year " + y + " is out of range");

		//Check to make sure new End Date is SAME or AFTER Start Date
		if(isGoodDate)
		{
			if(y>this.year1)
			{
				isGoodDate = true;
			} else
			{
				if(y==this.year1)
				{
					if(m>this.month1)
						isGoodDate=true;
					else
					{
						if(m==this.month1)
						{
							if(d>this.day1)
								isGoodDate =true;
							else if (d==this.day1)
								isGoodDate=true;
							else
							{
								isGoodDate = false;
								System.out.println("setEndDate: Bad Day");   
							}
						} else
						{
							isGoodDate = false;
							System.out.println("setEndDate: Bad Month");
						}
					}
				} else
				{
					isGoodDate = false;
					System.out.println("setEndDate: Bad Year");
				}
			}
		}
		   
		if(isGoodDate)
		{
			this.year2 = y;
			this.month2 = m;
			this.day2 = d;
		}

	}
	
	public int duration()
	{
		int date1, date2;
		
		//Days in each month, starting with index=1
		int[] monthDays = {0,31,28,31,30,31,30,31,31,30,31,30,31,31};
		int[] monthDaysCumulative = new int[15];
		//0:0 1:31 2:59 3:90 4:120 5:151 6:181 7:212 8:243 9:273 10:304 11:334 12:365
		
		
		monthDaysCumulative[0] = 0;
		for(int i=0;i<=12;i++)
		{
			monthDaysCumulative[i+1] = monthDaysCumulative[i] + monthDays[i+1];
			//System.out.print(i + ":" + monthDaysCumulative[i] + " ");
		}
		System.out.println("");
		
		//31: Jan, Mar, May, Jul, Aug, Oct, Dec
		//30: Apr, Jun, Sep, Nov
		//28: Feb
		
		date1 = 365*this.year1 + monthDaysCumulative[this.month1-1] + this.day1;
		date2 = 365*year2 + monthDaysCumulative[month2-1] + day2;
		
		System.out.println("Date1: " + date1);
		System.out.println("Date2: " + date2);

		return date2 - date1 + 1;
	}
}
