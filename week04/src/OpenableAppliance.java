public class OpenableAppliance extends Appliance {
	private boolean isOpen;//if Appliance is currently open. the appliance is currently on.
	private String contents;//Appliance contents
	
	public OpenableAppliance(String nm, int pwr)
	{
		super(nm, pwr);
		this.isOpen = false;
		this.contents = ""; 
	}
	
	public void toggleOpen()
	{
		this.isOpen = !this.isOpen;
	}
	
	public void insert(String newContent)
	{
		if (this.isOpen)
		{
			if (this.contents.equals(""))
			{
				this.contents = newContent;
			} else
			{
				System.out.println("Openable Appliance has contents already");
			}
		} else
		{
			System.out.println("Openable Appliance is Closed");
		}
	}
	
	public void removeContents()
	{
		if(this.isOpen)
		{
			this.contents = "";
		} else  //Appliance not open
		{
			System.out.println("Cannot removeContents(). Appliance is Closed");
		}
	}
	
	public String toString()
	{
		if (this.contents.equals(""))
		{
			return super.toString() + " It is " + (this.isOpen? "OPEN":"CLOSED") + ", and is empty.";
		} else
		{
			return super.toString() + " It is " + (this.isOpen? "OPEN":"CLOSED") + ", and contains " + this.contents + ".";
		}
	}
}
