
public class Baseball extends Sport {
	private String fieldName;

	public Baseball(String name, int players, String scoringType, String fieldName)
	{
		super(name, players, scoringType);
		this.fieldName = fieldName;
	}
	public String toString()
	{
		return super.toString() + "\nGame is played in/on a " + this.fieldName + "\n";
	}
	public String getFieldName()
	{
		return this.fieldName;
	}
	public void setFieldName(String fn)
	{
		this.fieldName = fn;
	}
}
