//for Quiz 12/2/14
import java.util.ArrayList;

public class PrioritySet<T extends Comparable<T> > {
	private ArrayList<T> items;
	public PrioritySet()
	{
		items = new ArrayList<T>();
	}
    
	void insert(T item)
	{
		/// TO DO: Implement this method
		items.add(item);
	}
    
	T getLargest()
	{
		/// TO DO: Change the class and the code below
		///        so this method works.
		/// NOTE: You may assume that items is not empty.
		T maxItem = items.get(0);
		for (int i = 1; i < items.size(); i++)
		{
			if (items.get(i).compareTo(maxItem) > 0)
				maxItem = items.get(i);
		}
		return maxItem;
	}
}
