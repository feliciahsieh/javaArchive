
public class Connect4Game {
	private int piecesFilled=0;
	//empty, red piece, yellow piece
	private char[][] myBoard;//A 2D array of char values
	private int totalBoardPieces=0;
	private int boardWidth;
	private int boardHeight;
	
	
	public Connect4Game(int height, int width)
	{
		myBoard = new char[height][width];
		
		for(int i=0;i<height;i++)
		{
			for(int j=0;j<width;j++)
			{
				myBoard[i][j] = ' ';
				//System.out.print(myBoard[i][j] + " ");
			}
			//System.out.println("");
		}
		
		this.boardWidth       = width;
		this.boardHeight      = height;
		this.totalBoardPieces = width*height;
		this.piecesFilled     = 0;

	}//Connet4Game constructor
	
	public String toString()
	{
		StringBuilder sb = new StringBuilder();

		sb.append("  0 1 2 3 4 5 6 7 8 9\n");
		for(int i=0;i<boardHeight;i++)
		{
			sb.append(i);
			sb.append(' ');
			for(int j=0;j<boardWidth;j++)
			{
				//System.out.println("My char:" + myBoard[0][0]);
				sb.append(myBoard[i][j]);
				sb.append(' ');
			}
			sb.append("\n");
		}
		return sb.toString();
	}
	
	public boolean ColumnIsFull(int c)
	{
		for(int row=0;row<boardHeight;row++)
		{
			if(myBoard[row][c]==' ')
			{
				System.out.println("ColumnIsFull: Empty space detected at [" + row +"]["+c+"]");
				return false;
			}
		}
		return true;
	}
	
	public boolean boardIsFull()
	{
		for(int column=0;column<boardWidth;column++)
		{
			if(!ColumnIsFull(column))
				return false;
			else
				System.out.println("boardIsFull: Column "+column+" is Full. So far ok");
		}
		return true;
	}
	
	//Sets a disc of the specified color to be added to the lowest empty position
	//within the given column. To represent a disc being placed in the array,
	//set the appropriate element to 'R' or 'Y' depending on whether isRed is true or false.
	//If the requested column is full or out of range, return false; otherwise, return true.
	public boolean playDisc(int column, boolean isRed)
	{
		boolean foundPosition=false;
		int row=boardHeight-1;
		
		if( (column>=0) && (column<boardWidth) )//valid column value
		{
			while(!foundPosition)
			{
				//Start searching from bottom of board
				if (myBoard[row][column]==' ')
				{
					//Found position;
					myBoard[row][column] = (isRed)? 'R':'Y';
					foundPosition = true;
					piecesFilled++;
				} else
				{
					row--;
					if(row == -1)//Checked all Columns
						break;
				}
			}
		}//if(column<boardWidth)
		else
			System.out.println("Column "+ column + " Out of Range");
		
		return foundPosition;
	}
	
}
