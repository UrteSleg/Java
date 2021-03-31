
package tictactoe;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class TicTacToe {
    
    private static final String fileName ="Saved";
        
    public static void main(String[] args) {
        
        TicTacToe objectIO = new TicTacToe(); 
        
        GameBoard myGame = new GameBoard();
        int counter = 1;
        int load, move;
        myGame.DisplayBoard();
        Scanner keyboard = new Scanner(System.in); // ask player for input
        System.out.println("If you want to load an earlier game, press 0, otherwise 1 ");
        load = keyboard.nextInt();
            if( load ==0 ) 
            {
                GameBoard gb = (GameBoard) objectIO.ReadObjectFromFile(fileName);
                System.out.print(gb); 
                myGame = gb;
            }
       
        while(myGame.gameActive())
        {
            System.out.printf("If you want to quit, press 0, if you want to save, press 1, to continue press 2 ");
            move = keyboard.nextInt();
            if( move == 0 ) System.exit(move);
            if( move == 1 ){
                objectIO.WriteToFile(myGame,fileName);
            }
            if(counter % 2 == 0)
                myGame.askPlayer('O');
            else
                myGame.askPlayer('X');
            counter++;
           
            System.out.println("\n");
            myGame.DisplayBoard();
            myGame.checkForWinner();
            if(myGame.checkForWinner() == true)
            {
                counter = 1;
            }
            if(counter == 10)
            {
                System.out.print("Stale \n");
                myGame = new GameBoard();
                counter = 1;
            }
        }
    }
    
    public Object ReadObjectFromFile(String filepath) {
 
        try {
 
            FileInputStream fileIn = new FileInputStream(filepath);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            Object obj = objectIn.readObject();
            System.out.println("The game has been read from the file");
            objectIn.close();
            return obj;
 
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public void WriteToFile(Object myData, String fileName)
    {
        try {  
            
            FileOutputStream saveFile=new FileOutputStream(fileName);
            ObjectOutputStream save = new ObjectOutputStream(saveFile);
            save.writeObject(myData);
            save.close();
            System.out.println("The game was succesfully saved to a file");
        }
        catch(Exception exc) {
            exc.printStackTrace();
        }
    }
    
}
