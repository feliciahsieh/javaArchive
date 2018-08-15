
public class Dragon {
	String name;
	int firePower;
	int length;
	String home;
	int age;
	static int maxAge = 100;
	
	public Dragon(String n, int fp, int l, String h, int a)
	{
		name = n;
		firePower = fp;
		length = l;
		home = h;
		age = a;
	}
	
	public String toString()
	{
		return "The " + length + "-foot Dragon " + name + " in " + home + "can breathe fire reaching " + firePower + " feet";
	}
	
	public String getHome()
	{
		return this.home;
	}
	
	public int getAge()
	{
		return age;
	}
	public static String diet()
	{
		String result="humans";
		
		return result;
	}
	public static boolean isEven(int i)
	{
		if((i%2) == 0)
			return true;
		else
			return false;
	}
}
