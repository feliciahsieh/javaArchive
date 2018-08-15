import java.util.ArrayList;

public class Box<T> {

	ArrayList<T> items;
	private int capacity;//Max items allowed in ArrayList
	
	Box(int capacity)
	{
		this.capacity = capacity;
		items = new ArrayList<T>();
	}
	
	public void add(T item)
	{
		if (items.size() < capacity)
			items.add(item);
	}
	
	public void remove(T item)
	{
		if(items.contains(item))
		{
			items.remove(item);
			System.out.println("Removed "+ item);
		}
	}
	
	public String toString()
	{
		return items.toString();
	}
	
}
