import java.util.Scanner;

public class C4board {
   /* Useful for debugging.
   */
   public static void tellUser(String prompt) {
      Scanner sc = new Scanner(System.in);
      String userInput;
        
      System.out.println(prompt);
      System.out.print("<enter> to continue ...");
      userInput = sc.nextLine();
   }
		
   /* Class Constants
   */
   public static final char NO_WINNER = ' ';
   public static final int COLUMN_FULL = -2;
	private static final char EMPTY = ' ';
   private static final int NUM_TO_WIN = 4;
    
   /* Attributes
	*/
   // Common sizes: 7 columns, 6 rows, 5×4, 6×5, 8×7, 9×7, 10×7, 8×8.
	private int numRows;
   private int numCols;
 
   private char board[][];
	    
	/* Constructor(s)
	*/
	public C4board(int rows, int cols) {
      setNumRows(rows);
      setNumCols(cols);
		board = new char[numRows][numCols];
		for (int i=0; i < numRows; i++)
			for (int j=0; j < numCols; j++)
				setBoard(i,j,EMPTY);
	}
	
   /* Instance Methods
   */
	/* Getters
	*/
   public char getBoard(int row, int col) {
      return board[row][col];
	}
    
   public int getNumRows() {
      return numRows;
   }
    
   public int getNumCols() {
      return numCols;
   }
    
   /* Setters
   */
   public void setBoard(int row, int col, char gamePiece) {
      if ((row >= numRows) || (col >=numCols))
         System.out.println("C4board: row, col " + row + ", " + col + " illegal."); 
      else
         board[row][col] = gamePiece;
   }
    
   public void setNumRows(int rows) {
      numRows = rows;
   }
    
   public void setNumCols(int cols) {
      numCols = cols;
   }
 
   /* Actions
   */
	public void displayBoard() {
		System.out.println("Board (" + numRows + " x " + numCols + ") is: ");
		for (int i=numRows-1; i >= 0; i--) {
			for (int j=0; j < numCols; j++)
				if (getBoard(i,j) != EMPTY)
					System.out.print(" " + board[i][j] + " ");
			else
				System.out.print(" _ ");
			System.out.println();
		} 
	}
    
   public int getFirstAvailableRow(int column) {
      char gamePiece;
      boolean rowFound = false;       
      int row = numRows-1;
        
      while ((row >= 0) && !rowFound) {
         gamePiece = getBoard(row,column);

         if (gamePiece == EMPTY)
               row--;
         else {
            row++;
            if (row < numRows)
               rowFound = true;
            else
               row = COLUMN_FULL;
         }
      } 
      if (row == -1) row = 0;
      return row;
   }
	
	public boolean isTied() {
		boolean tie = true;
		for (int i=0; i < numRows && tie; i++)
			for (int j=0; j < numCols && tie; j++) 
				if (getBoard(i,j) == EMPTY)
					tie = false;
		return(tie);
	}
    
	/* Each one of the following 7 check*() method
        returns the player piece of the winner
        or NO_WINNER if no one won.
   */
   public char checkSouth(C4boardPosition move) { 
	
		for(int i = 0; i < this.numCols && NO_WINNER == EMPTY; i++){
		  
			if((board[6][i] == board[5][i]) && (board[5][i] == board[4][i]) && (board[4][i] == board[3][i]) && board[6][i] != ' '){
				return board[5][i];
			}
			
			if((board[5][i] == board[4][i]) && (board[4][i] == board[3][i]) && (board[3][i] == board[2][i]) && board[5][i] != ' '){
				return board[5][i];
			}
		  
			if((board[4][i] == board[3][i]) && (board[3][i] == board[2][i]) && (board[2][i] == board[1][i]) && board[4][i] != ' '){
				return board[4][i];
			}
		  
			if((board[3][i] == board[2][i]) && (board[2][i] == board[1][i]) && (board[1][i] == board[0][i]) && board[3][i] != ' '){
				return board[3][i];
			}
			
	    }
		
		return NO_WINNER;
   }
     
   public char checkEast(C4boardPosition move) {
        
		for(int i = 0; i < this.numRows && NO_WINNER == EMPTY; i++){
		  
			if((board[i][2] == board[i][3]) && (board[i][3] == board[i][4]) && (board[i][4] == board[i][5]) && board[i][2] != ' '){
				return board[i][2];
		    }
			
	    }
		
		return NO_WINNER;
   }
    
   public char checkWest(C4boardPosition move) {
	   
		for(int i = 0; i < this.numRows && NO_WINNER == EMPTY; i++){
		  
			if((board[i][0] == board[i][1]) && (board[i][1] == board[i][2]) && (board[i][2] == board[i][3]) && board[i][0] != ' '){
				return board[i][0];
		    }
		  
			if((board[i][1] == board[i][2]) && (board[i][2] == board[i][3]) && (board[i][3] == board[i][4]) && board[i][1] != ' '){
				return board[i][1];
		    }
		}
			     
		return NO_WINNER;
	}
    
   public char checkNorthWest(C4boardPosition move) {
      
		if((board[3][0] == board[4][1]) && (board[4][1] == board[5][2]) && (board[5][2] == board[6][3]) && board[3][0] != ' '){
			return board[3][0];
		}
		
		if((board[2][0] == board[3][1]) && (board[3][1] == board[4][2]) && (board[4][2] == board[5][3]) && board[2][0] != ' '){
			return board[2][0];
		}
		
		if((board[3][1] == board[4][2]) && (board[4][2] == board[5][3]) && (board[5][3] == board[6][4]) && board[3][1] != ' '){
			return board[3][1];
		}
		
		if((board[1][0] == board[2][1]) && (board[2][1] == board[3][2]) && (board[3][2] == board[4][3]) && board[1][0] != ' '){
			return board[1][0];
		}
		
		if((board[2][1] == board[3][2]) && (board[3][2] == board[4][3]) && (board[4][3] == board[5][4]) && board[2][1] != ' '){
			return board[2][1];
		}
		
		if((board[3][2] == board[4][3]) && (board[4][3] == board[5][4]) && (board[5][4] == board[6][5]) && board[3][2] != ' '){
			return board[3][2];
		}
		
		return NO_WINNER;
   }
    
   public char checkNorthEast(C4boardPosition move) {
		
		if((board[6][2] == board[5][3]) && (board[5][3] == board[4][4]) && (board[4][4] == board[3][5]) && board[6][2] != ' '){
			return board[6][2];
		}
		
		if((board[6][1] == board[5][2]) && (board[5][2] == board[4][3]) && (board[4][3] == board[3][4]) && board[6][1] != ' '){
			return board[6][1];
		}
		
		if((board[5][2] == board[4][3]) && (board[4][3] == board[3][4]) && (board[3][4] == board[2][5]) && board[5][2] != ' '){
			return board[5][2];
		}
		
		if((board[6][0] == board[5][1]) && (board[5][1] == board[4][2]) && (board[4][2] == board[3][3]) && board[6][0] != ' '){
			return board[6][0];
		}
		
		if((board[5][1] == board[4][2]) && (board[4][2] == board[3][3]) && (board[3][3] == board[2][4]) && board[5][1] != ' '){
			return board[5][1];
		}
		
		if((board[4][2] == board[3][3]) && (board[3][3] == board[2][4]) && (board[2][4] == board[1][5]) && board[4][2] != ' '){
			return board[4][2];
		}
		
		return NO_WINNER;
   }
    
   public char checkSouthWest(C4boardPosition move) {
		
		if((board[5][0] == board[4][1]) && (board[4][1] == board[3][2]) && (board[3][2] == board[2][3]) && board[5][0] != ' '){
			return board[5][0];
		}
		
		if((board[4][1] == board[3][2]) && (board[3][2] == board[2][3]) && (board[2][3] == board[1][4]) && board[4][1] != ' '){
			return board[4][1];
		}
		
		if((board[3][2] == board[2][3]) && (board[2][3] == board[1][4]) && (board[1][4] == board[0][5]) && board[3][2] != ' '){
			return board[3][2];
		}
		
		if((board[4][0] == board[3][1]) && (board[3][1] == board[2][2]) && (board[2][2] == board[1][3]) && board[4][0] != ' '){
			return board[4][0];
		}
		
		if((board[3][1] == board[2][2]) && (board[2][2] == board[1][3]) && (board[1][3] == board[0][4]) && board[3][1] != ' '){
			return board[3][1];
		}
		
		if((board[3][0] == board[2][1]) && (board[2][1] == board[1][2]) && (board[1][2] == board[0][3]) && board[3][0] != ' '){
			return board[3][0];
		}
		
		return NO_WINNER;
   }
    
   public char checkSouthEast(C4boardPosition move) {
		
		if((board[0][0] == board[1][1]) && (board[1][1] == board[2][2]) && (board[2][2] == board[3][3]) && board[0][0] != ' '){
			return board[0][0];
		}
		
		if((board[1][1] == board[2][2]) && (board[2][2] == board[3][3]) && (board[3][3] == board[4][4]) && board[1][1] != ' '){
			return board[1][1];
		}
		
		if((board[2][2] == board[3][3]) && (board[3][3] == board[4][4]) && (board[4][4] == board[5][5]) && board[2][2] != ' '){
			return board[2][2];
		}
		
		if((board[0][1] == board[1][2]) && (board[1][2] == board[2][3]) && (board[2][3] == board[3][4]) && board[0][1] != ' '){
			return board[0][1];
		}
		
		if((board[1][2] == board[2][3]) && (board[2][3] == board[3][4]) && (board[3][4] == board[4][5]) && board[1][2] != ' '){
			return board[1][2];
		}
		
		if((board[0][2] == board[1][3]) && (board[1][3] == board[2][4]) && (board[2][4] == board[3][5]) && board[0][2] != ' '){
			return board[0][2];
		}
		
		return NO_WINNER;
   }
        
	public char getWinner(C4boardPosition move) {
      /* Check for a winner in 7 directions
         only check the next direction if a winner was not seen
         Note: no need to check north!!
           
         Things to note:
            - (0,0) is the bottom left element in the board
            - Consider the following board where * is the game piece
               just played.
             
             NW   NE
              W * E
             SW S SE
             
            We must check 7 directions to see if there are 4 in a row:
            NW - north west
            W - west
            SW - south west
            S - south
            NE - north east
            E - east
            SE - south east
      */
       
      char whoWon = checkSouth(move);
      if (whoWon == EMPTY)
         whoWon = checkEast(move);
      if (whoWon == EMPTY)
         whoWon = checkWest(move);
      if (whoWon == EMPTY)
         whoWon = checkNorthWest(move);
      if (whoWon == EMPTY)
         whoWon = checkNorthEast(move);  
      if (whoWon == EMPTY)
         whoWon = checkSouthEast(move);
      if (whoWon == EMPTY)
         whoWon = checkSouthWest(move);
                            
		return(whoWon);
	}    
}