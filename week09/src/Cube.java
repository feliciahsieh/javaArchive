public class Cube {
	private int sideLength;
	private String material;
   
	public Cube (int sideLength, String material)
	{
		this.sideLength = sideLength;
		this.material = material;
	}
	public void setMaterial(String newMaterial)
	{
		material = newMaterial;
	}
	public void setSize(int s)
	{
		sideLength = s;
	}
	private int volume()
	{
		return sideLength*sideLength*sideLength;
	}
	public String toString()
	{
		return (material + " cube " + Integer.toString(volume()) + " in^3");
	}
}
