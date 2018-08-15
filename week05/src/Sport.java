public class Sport {
	protected String name;
	protected int teamSize;
	protected String scoringType;
	
	public Sport(String n, int ts, String st)
	{
		this.name = n;
		this.teamSize = ts;
		this.scoringType = st;
	}
	public String toString()
	{
		return "Sport name: " + this.name + ". \nSport needs " + this.teamSize + " players."
			+ "\nTo win, you score " + this.scoringType + ".";
	}
	public String getName()
	{
		return this.name;
	}
	public int getTeamSize()
	{
		return this.teamSize;
	}
	public String getScoringType()
	{
		return this.scoringType;
	}
	
}//public class Sport
