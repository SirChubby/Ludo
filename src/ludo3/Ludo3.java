package ludo3;

/**
 *
 * @author Frederik
 */
public class Ludo3 {
    /*
     This is the driver of the program, containing the main method.
     In here we enable acces to the board class and the dice class and then run the 2 methods from the Board class.
     */

    public static void main(String[] args) {
        Board board = new Board(4);
        //board.printPlayersPositions(); //This can be uncommented in when I want to debug.
        board.PrintLudoBoard();
        board.startGame();
    }
}
