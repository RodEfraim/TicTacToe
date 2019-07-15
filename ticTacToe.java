import java.util.Scanner;

/**
 *  Two-player game of Tic-Tac-Toe.
 */
public class ticTacToe
{
  private static char[][] board = new char[3][3];
  private static int LAST_TURN = 9;
    
  public static void main( String[] args )
  {
    Scanner keyboard = new Scanner(System.in);

    //First value is row and second value is column.
    int[] rowColumnArray = new int[2];

    boolean playerTurn = true;
    boolean tieGame = true;

		initBoard();
    displayBoard();

    int count = 0;
    while(count < LAST_TURN)
    {
      if(playerTurn)
      {
        rowColumnArray = inputPosition('X');
        turnX(rowColumnArray[0], rowColumnArray[1]);
      }
      else
      {
        rowColumnArray = inputPosition('O');
        turnO(rowColumnArray[0], rowColumnArray[1]);
      }

      count++;
      playerTurn = !playerTurn;
      displayBoard();
      if(outcome() == true)
      {
        tieGame = false;
        break;
      }
    }

    if(tieGame)
      System.out.println("It's a tied game! No one wins.");
  }

  /**
   *  Fills up the board game with blanks.
   **/
  public static void initBoard()
  {
    for ( int r=0; r<3; r++ )
      for ( int c=0; c<3; c++ )
        board[r][c] = ' ';
  }

  /**
   *  Places 'X' values on the board.
   *
   *  @param row: The row value.
   *  @param column: The column value.
   */  
  public static void turnX(int row, int column)
  {
    board[row][column] = 'X';
  }
  
  /**
   *  Places 'O' values on the board.
   *
   *  @param row: The row value.
   *  @param column: The column value.
   */
  public static void turnO(int row, int column)
  {
    board[row][column] = 'O';
  }

  /**
   *  Asks player to input a value for either row or column. Validates input by
   *  error checking the 0-2 range and string input prevention. Called by the
   *  inputPosition function.
   *
   *  @return num: The valid input value of the player.
   */
  public static int inputNum()
  {
    int num = 0;
    boolean inputError = false;

    do
    {
      inputError = false;
      //Prevents string input.
      try
      {  
        Scanner keyboardInteger = new Scanner(System.in);
        num = keyboardInteger.nextInt();
      }
      catch(Exception e)
      {
        System.out.print("ERROR: Exception Occured. Please enter an integer.");
        inputError = true;
        continue;
      }

      if(num > 2 || num < 0)
      {
        System.out.print("ERROR: The row-column range is from 0-2. Please input again: ");
        inputError = true;
      }

    }while(inputError);  

    return num;
  }

  /**
   *  Prompts the user to input values for both row and column. Error checks by
   *  preventing user from entering an already used position.
   *
   *  @param inputChar: Used for prompting either player 'X' or 'O'. 
   *  @return returnArray: Array holding the row-column values where the 'X'
   *  or 'O' figure will be placed. 
   */
  public static int[] inputPosition(char inputChar)
  {
    int row = 0;
    int column = 0;
    int[] returnArray = new int[2];
    boolean errorPosition = false;

    do
    {
      errorPosition = false;
      System.out.print("Player " + inputChar + ", Input row number: ");
      row = inputNum();
      System.out.print("Player " + inputChar  + ", Input column number: ");
      column = inputNum();
      
      returnArray[0] = row;
      returnArray[1] = column;

      //TODO: Condition that prevents from entering an already used position.
      if(board[row][column] != ' ')
      {
        System.out.println("ERROR: This position already holds a value. Please try again.");
        errorPosition = true;
      }
    }while(errorPosition);

    return returnArray;
  }

  /**
   *  First style of displaying the board game.
   */
  public static void displayBoard()
  {
    System.out.println("  0  " + board[0][0] + "|" + board[0][1] + "|" + board[0][2]);
    System.out.println("    --+-+--");
    System.out.println("  1  " + board[1][0] + "|" + board[1][1] + "|" + board[1][2]);
    System.out.println("    --+-+--");
    System.out.println("  2  " + board[2][0] + "|" + board[2][1] + "|" + board[2][2]);
    System.out.println("     0 1 2 ");
  }

  /**
   *  Second style of displaying the board game.
   */
  public static void displayBoard2()
  {
    for ( int r=0; r<3; r++ )
    {
      System.out.print("\t"+r+" ");
      for ( int c=0; c<3; c++ )
      {
        System.out.print( board[r][c] + " " );
      }
      System.out.println();
    }
    System.out.println("\t  0 1 2 ");
  }

  /**
   *  Determines if player X or O have won. If the consecutive count in
   *  horizontal (for row), vertical (for column), or diagonal is equal to 3
   *  then one of this players is declared a winner. 
   *
   *  @param conX: Consecutive count for player X.
   *  @param conO: Consecutive count for player O.
   *  @return true if a player has reached a consecutive count of 3, false
   *  otherwise.
   */
  public static boolean winner(int conX, int conO)
  {
    if(conX == 3)
    {
      System.out.println("Player 'X' Wins!");
      return true;
    }
    else if(conO == 3)
    {
      System.out.println("Player 'O' Wins!");
      return true;
    }
    else
      return false;
  }

  /*
   *  Checks for different winning conditions such as; consecutive straight
   *  rows, columns, diagonal left to right and right to left.
   *
   *  @return true if a player has won, false otherwise.
   */
  public static boolean outcome()
  {
    int consecutiveRowX = 0;  
    int consecutiveRowO = 0;
    int consecutiveColumnX = 0;
    int consecutiveColumnO = 0;
    int consecutiveDiagonalX = 0;
    int consecutiveDiagonalO = 0;

    for(int count1 = 0; count1 < 3; count1++)
    {
      consecutiveRowX = 0;  
      consecutiveRowO = 0;
      consecutiveColumnX = 0;
      consecutiveColumnO = 0;

      for(int count2 = 0; count2 < 3; count2++)
      {
        //Checks for a straight row.
        if(board[count1][count2] == 'X')
        {
          consecutiveRowX++;
          consecutiveRowO = 0;
        }
        else if(board[count1][count2] == 'O')
        {
          consecutiveRowO++;
          consecutiveRowX = 0;
        }

        //Checks for a straight column.
        if(board[count2][count1] == 'X')
        {
          consecutiveColumnX++;
          consecutiveColumnO = 0;
        }
        else if(board[count2][count1] == 'O')
        {
          consecutiveColumnO++;
          consecutiveColumnX = 0;
        }
      }
      
      if(winner(consecutiveRowX, consecutiveRowO) == true)
        return true;

      if(winner(consecutiveColumnX, consecutiveColumnO) == true)
        return true;
    }
    
    //Checks diagonal from left to right.
    for(int count = 0; count < 3; count++)
    {
      if(board[count][count] == 'X')
      {
        consecutiveDiagonalX++;
        consecutiveDiagonalO = 0;

      }
      else if(board[count][count] == 'O')
      {
        consecutiveDiagonalO++;
        consecutiveDiagonalX = 0;
      }
    }

    if(winner(consecutiveDiagonalX, consecutiveDiagonalO) == true)
      return true;

    //Checks diagonal from right to left.
    consecutiveDiagonalX = 0;
    consecutiveDiagonalO = 0;
    int columnCount = 3;
    for(int count = 0; count < 3; count++)
    {
      columnCount--;
      if(board[count][columnCount] == 'X')
      {
        consecutiveDiagonalX++;
        consecutiveDiagonalO = 0;
      }
      else if(board[count][columnCount] == 'O')
      {
        consecutiveDiagonalO++;
        consecutiveDiagonalX = 0;
      }
    }

    if(winner(consecutiveDiagonalX, consecutiveDiagonalO) == true)
      return true;

    return false;
  }
}
