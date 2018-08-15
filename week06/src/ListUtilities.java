
public class ListUtilities {

	static public double Average(double[] list)
	{//Returns the average of all values in the array.
		double sum=0.0;
		
		for(double n : list)
		{
			sum += n;
		}
		System.out.println("sum: " + sum + " Average = " + sum/list.length);
		
		return sum/list.length;
	}
	
	static public boolean InOrder(double[] list)
	{//Check if list is in order
		boolean result=true;
		int returnValue;
		
		for(int i=0;i<list.length-1;i++)
		{
			returnValue = Double.compare(list[i], list[i+1]);
			
			if(returnValue>0)
			{
				result = false;
				System.out.println("Not in order");
				break;
			}
		}
		
		return result;
	}
}
