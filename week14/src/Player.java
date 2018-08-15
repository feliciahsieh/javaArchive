//for Quiz 12/2/14
class Player
{
	protected String name;
	protected Player target;
	public Player(String n)
	{
		name = n;
		target = null;
	}
    
	public void setTarget(Player p)
	{
		target = p;
	}
    
	
	public void printTargetChain()
	{
		Player current = this;
		
		while(true)
		{
			if (current!=null)
			{
				if(current.target==null)
				{
					System.out.println(current.name + " does not have a target.");
					break;
				}
				else
				{
					System.out.println(current.name + "'s target is " + current.target.name + ".");
					current = current.target;
				}
			} else
			{
				break;
			}
		}
	}

}
