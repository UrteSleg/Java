
package tictactoe;
import java.util.Arrays;
import java.util.Scanner;
import java.io.Serializable;
import java.io.*;

/**
 *
 * @author inemil
 */
public class GameBoard implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private char[][] gameBoard;
    private boolean gameOnGoing = true;
    private int Xscore =0;
    private int Oscore = 0;
    
    
    
    /**
     * This is the constructor of a game board class
     */
    public GameBoard()
    {
        gameBoard = new char[3][3]; //3x3 table
        
        for (int row = 0; row < gameBoard.length; row++) {
            Arrays.fill(gameBoard[row], ' ');//It will fill row with blank space
        }
    }
    // End of the constructor
    
    public GameBoard(char[][] gameboard, boolean gameongoing, int xscore, int oscore)
    {
        this.gameBoard = gameboard;
        this.gameOnGoing = gameongoing;
        this.Xscore = xscore;
        this.Oscore = oscore;
    }
    
    public void setGameBoard(char[][] gameboard)
    {
        this.gameBoard = gameboard;
    }
    
    public char[][] getGameBoard()
    {
        return this.gameBoard;
    }
    
     public void setGameOnGoing(boolean gameongoing)
    {
        this.gameOnGoing = gameongoing;
    }
    
    public boolean getgameOnGoing()
    {
        return this.gameOnGoing;
    }
     public void setXScore(int xscore)
    {
        this.Xscore = xscore;
    }
    
    public int getXscore()
    {
        return this.Xscore;
    }
     public void setOscore(int oscore)
    {
        this.Oscore = oscore;
    }
    
    public int getOscore()
    {
        return this.Oscore;
    }
    
    @Override
    public String toString() {
         String temp = "";
    
    for(int r = 0; r < gameBoard.length; r++) {
      for(int c = 0; c < gameBoard.length; c++) {
        temp += gameBoard[r][c] + "|";
      }
      temp = temp.substring(0, temp.length() - 1);
      temp += "\n";
      
      for(int i = 0; r != gameBoard.length-1 && i < gameBoard.length; i++){
        temp += "- ";
      }
      temp += "\n";
    }
    
    return temp;
  } 
    /**
     * This method will display the game board to the screen
     */
    public void DisplayBoard()
    {
        for (int row = 0; row < gameBoard.length; row++) {
            for (int col = 0; col < gameBoard[0].length; col++) {
                System.out.print("\t"+gameBoard[row][col]);
                if(col== 0 || col==1)
                    System.out.print("|");
            }
            if(row==0|| row==1)
            System.out.print("\n----------------------------\n");
        }
        System.out.println();
    } //End of method display
   /**
    * This method will validate if a players move is allowed and return true if the move was completed
    */
    public boolean makeMove(char player, int row, int col)
    {
        if(row>= 0 && row <= 2 && col >= 0 && col<= 2)
        {
            if(gameBoard[row][col] != ' ')
                return false;
            else
            {
                gameBoard[row][col] = player;
                return true;
            }
        }
        else 
            return false;
    } // eend of make move method
    
    /**
     * This method will return true if the game is still active
     */
    public boolean gameActive()
    {
        return gameOnGoing;
    }
    /**
     * This method will ask the user to pick a row and column,validate the inputs 
     * and call the method makeMove
     */
    
    
    public void askPlayer(char player) 
    {
        Scanner keyboard = new Scanner(System.in); // ask player for input
        int row, col, move, load;
        do
        {
            
            System.out.printf("Player %s Please enter a row (1-3): ", player);
            row = keyboard.nextInt();
            
            
            System.out.printf("Player %s Please enter a column (1-3): ", player);
            col = keyboard.nextInt();
            
        } while(notValid(row, col));
        
        makeMove(player, row-1, col-1);
        //end of askPlayer method
    }
    /**
     * This method will validate if the row and colomn are between 1-3
     * and if the position is currently empty
     */
    public boolean notValid(int row, int col)
    {
        if(row>3 || row < 1 || col>3 || col<1 || !isEmpty(row, col))
            return true;
        else
            return false; 
    
    }
    
    /**
     * This method will check if a position is empty
     * return true if the postion is empty, false otherwise
     */
    
    public boolean isEmpty(int row, int col)
    {
        if(gameBoard[row-1][col-1] == ' ')
            return true;
        else
        {
            System.out.print("That position is taken. \n");
            return false;
        }
       
    }
    
    /**
     * This method will check to see if there are 3 x or o in a row
     * return true if there is a winner false otherwise
     */
        
    public boolean checkForWinner()
    {
        
     // loop over each row and check for a winner
        for (int row = 0 ; row< gameBoard.length; row ++)
        {
            if(gameBoard[row][0]== gameBoard[row][1] && gameBoard[row][1]==gameBoard[row][2]
                    &&gameBoard[row][0] != ' ')
            {
                System.out.print("The winner is " + gameBoard[row][0] + '\n');
                if(gameBoard[row][0] == 'X') Xscore ++;
                else Oscore++;
                System.out.print("X score: "+ Xscore + '\n' + "O score: " + Oscore + '\n');
                gameBoard = new char[3][3]; //3x3 table
        
                for (int col = 0; col < gameBoard.length; col++) {
                    Arrays.fill(gameBoard[col], ' ');//It will fill row with blank space
                }
            }
                
        }
        
        // loop over each col and check for winner
        
        for(int col = 0; col< gameBoard[0].length; col++)
        {
             if(gameBoard[0][col]== gameBoard[1][col] && gameBoard[1][col]==gameBoard[2][col]
                    &&gameBoard[0][col] != ' ')
                {
                    System.out.print("The winner is " + gameBoard[0][col] +'\n' );
                    if(gameBoard[0][col] == 'X') Xscore ++;
                    else Oscore++;
                    System.out.print("X score: "+ Xscore + '\n' + "O score: " + Oscore + '\n');
                    gameBoard = new char[3][3]; //3x3 table
        
                    for (int row = 0; row < gameBoard.length; row++) {
                        Arrays.fill(gameBoard[row], ' ');//It will fill row with blank space
                    }
                }
               
        }
        // check the crosses
         if(gameBoard[0][0]== gameBoard[1][1] && gameBoard[1][1] == gameBoard[2][2] && gameBoard[2][2] !=' ')
           {
                System.out.print("The winner is " + gameBoard[0][0] +'\n' );
                if(gameBoard[0][0] == 'X') Xscore ++;
                    else Oscore++;
                    System.out.print("X score: "+ Xscore + '\n' + "O score: " + Oscore + '\n');
                for (int row = 0; row < gameBoard.length; row++) {
            Arrays.fill(gameBoard[row], ' ');//It will fill row with blank space
        }
            }
         if(gameBoard[0][2]== gameBoard[1][1] && gameBoard[1][1] == gameBoard[2][0] && gameBoard[2][0] !=' ')
           {
                System.out.print("The winner is " + gameBoard[0][2] +'\n' );
                if(gameBoard[0][2] == 'X') Xscore ++;
                    else Oscore++;
                    System.out.print("X score: "+ Xscore + '\n' + "O score: " + Oscore + '\n');
                    for (int row = 0; row < gameBoard.length; row++) {
            Arrays.fill(gameBoard[row], ' ');//It will fill row with blank space
        }
            }
          
         return false;
    }
    
} 
