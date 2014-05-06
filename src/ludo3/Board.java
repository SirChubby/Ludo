package ludo3;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Frederik
 */
public class Board {

    static Dice dice = new Dice(); // Here we enable acces to the dice class by using the constructor. This means that each player has to use the same dice.
    static final int noOfSpaces = 52;// A contant determining the length of the game board.

    public static ArrayList<Player> players = new ArrayList<>(); //An ArrayList containing the different players of the game
    public static ArrayList<Spot> board = new ArrayList<>();// An ArrayList containing all the spots of the board.
    Scanner scanner = new Scanner(System.in);

    public Board(int noOfPlayers) { // This constructor is the main force in populating the board.
        for (int i = 1; i <= noOfPlayers; i++) { // This for-loop takes each player and assign them their own ID, so that we are able to differentiate between each player.
            Player player = new Player(i);
            players.add(player);
        }
        for (int i = 0; i < noOfSpaces; i++) { // This for-loop assigns a unique index number to each spot along the board for all the number of spaces on the board.
            board.add(new Spot(i));
        }
    }

    /*
     This method is used, when needed, to check the behavior of the tokens.
     The method works by printing out the position and values of each token as owned by each player respectively.
     It is not used by default, but instead as a debugging method.
     */
    public void printPlayersPositions() {
        System.out.println("Board:");
        for (Spot spot : board) { //For each spot on the board, this loop prints out each spot on the board.
            System.out.print(spot);
        }
        System.out.println();
        for (Player player : players) { //These loops prints out the player ID, the homerun and yard spots for each player in the game.
            System.out.println("Player " + player.getPlayerId() + ":");
            System.out.println("Homerun:");
            for (Spot spot : player.homerun) {
                System.out.print(spot + " ");
            }
            System.out.println();
            System.out.println("Yard:");
            for (Spot spot : player.yard) {
                System.out.print(spot + " ");
            }
            System.out.println("\n");
        }
        System.out.println();
    }

    /*
     This method "starts" the game, meaning it checks whether the number of rounds is less than a user-defined number.
     For now this is 1 of 2 ways to end the game. The other is when all the tokens og a single player has their variable isDone = true;
     The first for loop could be changed to a while loop, that runs until all the tokens of a player has the value isDone=true,
     but I really like this feature.
     */
    public void startGame() {
        System.out.println("Type in number of computer players: ");
        int numberOfComputerPlayers = scanner.nextInt();
        System.out.println("Type in maximum number of rounds: ");
        int maxNumberOfRounds = scanner.nextInt();
        for (int i = 1; i < maxNumberOfRounds; i++) {
            System.out.println();
            System.out.println("Round: " + i);
            for (Player player : players) {//This for-loop runs the turn method and prints out the board, for each player in the game, until 1 of the 2 criterias to end the game is satisfied.
                //These statements are choosen depending on the number of computer players choosen.
                if (numberOfComputerPlayers == 1 && player.playerId == 4) {
                    player.aiTurn();
                    PrintLudoBoard();
                } else if (numberOfComputerPlayers == 2 && (player.playerId == 3 || player.playerId == 4)) {
                    player.aiTurn();
                    PrintLudoBoard();
                } else if (numberOfComputerPlayers == 3 && (player.playerId == 2 || player.playerId == 3 || player.playerId == 4)) {
                    player.aiTurn();
                    PrintLudoBoard();
                } else if (numberOfComputerPlayers == 4 && (player.playerId == 1 || player.playerId == 2 || player.playerId == 3 || player.playerId == 4)) {
                    player.aiTurn();
                    PrintLudoBoard();
                } else {
                    player.turn();
                    PrintLudoBoard();
                }
            }
            //printPlayersPositions();// Control/Debugging method to keep track of the behavoir of the tokens in the game. Printout not formatted in classic ludo visuals.
            System.out.println();
        }
    }

    public static int rollDice() { // This method enables the players to "roll" the dice, through the board, so that they all share the same dice
        return dice.roll();
    }

    public void PrintLudoBoard() { //A sort of semi-hard-coded Ludo board.

        String ludo = Color.FG_ANSI_RED + "L" + Color.FG_ANSI_BLUE + "U" + Color.FG_ANSI_GREEN + "D" + Color.FG_ANSI_YELLOW + "O" + Color.FG_ANSI_RESET;
        String player1 = Color.FG_ANSI_RED + "Player 1" + Color.FG_ANSI_RESET;
        String player2 = Color.FG_ANSI_BLUE + "Player 2" + Color.FG_ANSI_RESET;
        String player3 = Color.FG_ANSI_GREEN + "Player 3" + Color.FG_ANSI_RESET;
        String player4 = Color.FG_ANSI_YELLOW + "Player 4" + Color.FG_ANSI_RESET;

        String lineOne = "                  " + board.get(49) + " " + board.get(50) + " " + board.get(51);
        String lineTwo = "     " + players.get(3).yard[0] + players.get(3).yard[1] + "      " + board.get(48) + " " + players.get(0).homerun[0] + " " + board.get(0) + "      " + players.get(0).yard[0] + players.get(0).yard[1];
        String lineThree = "     " + players.get(3).yard[2] + players.get(3).yard[3] + "      " + board.get(47) + " " + players.get(0).homerun[1] + " " + board.get(1) + "      " + players.get(0).yard[2] + players.get(0).yard[3];
        String lineFour = "     "+player4+"     " + board.get(46) + " " + players.get(0).homerun[2] + " " + board.get(2) + "      " + player1;
        String lineFive = "                  " + board.get(45) + " " + players.get(0).homerun[3] + " " + board.get(3);
        String lineSix = "                  " + board.get(44) + " " + players.get(0).homerun[4] + " " + board.get(4);
        String lineSeven = board.get(38) + "" + board.get(39) + "" + board.get(40) + "" + board.get(41) + "" + board.get(42) + "" + board.get(43) + "  " + ludo + "!!!  " + board.get(5) + "" + board.get(6) + "" + board.get(7) + "" + board.get(8) + "" + board.get(9) + "" + board.get(10);
        String lineEight = board.get(37) + "" + players.get(3).homerun[0] + "" + players.get(3).homerun[1] + "" + players.get(3).homerun[2] + "" + players.get(3).homerun[3] + "" + players.get(3).homerun[4] + "  " + ludo + "!!!  " + players.get(1).homerun[4] + "" + players.get(1).homerun[3] + "" + players.get(1).homerun[2] + "" + players.get(1).homerun[1] + "" + players.get(1).homerun[0] + "" + board.get(11);
        String lineNine = board.get(36) + "" + board.get(35) + "" + board.get(34) + "" + board.get(33) + "" + board.get(32) + "" + board.get(31) + "  " + ludo + "!!!  " + board.get(17) + "" + board.get(16) + "" + board.get(15) + "" + board.get(14) + "" + board.get(13) + "" + board.get(12);
        String lineTen = "                  " + board.get(30) + " " + players.get(2).homerun[4] + " " + board.get(18);
        String lineEleven = "                  " + board.get(29) + " " + players.get(2).homerun[3] + " " + board.get(19);
        String lineTwelve = "                  " + board.get(28) + " " + players.get(2).homerun[2] + " " + board.get(20);
        String lineThirteen = "     " + players.get(2).yard[0] + players.get(2).yard[1] + "      " + board.get(27) + " " + players.get(2).homerun[1] + " " + board.get(21) + "      " + players.get(1).yard[0] + players.get(1).yard[1];
        String lineFourTeen = "     " + players.get(2).yard[2] + players.get(2).yard[3] + "      " + board.get(26) + " " + players.get(2).homerun[0] + " " + board.get(22) + "      " + players.get(1).yard[2] + players.get(1).yard[2];
        String lineFifteen = "     "+player3+"     " + board.get(25) + " " + board.get(24) + " " + board.get(23) + "      " + player2;

        System.out.println(lineOne);
        System.out.println(lineTwo);
        System.out.println(lineThree);
        System.out.println(lineFour);
        System.out.println(lineFive);
        System.out.println(lineSix);
        System.out.println(lineSeven);
        System.out.println(lineEight);
        System.out.println(lineNine);
        System.out.println(lineTen);
        System.out.println(lineEleven);
        System.out.println(lineTwelve);
        System.out.println(lineThirteen);
        System.out.println(lineFourTeen);
        System.out.println(lineFifteen);
    }
}
//Two Other attempts at making the board.
/*
 String ludo = Color.BG_ANSI_RED+"L"+Color.BG_ANSI_BLUE+"U"+Color.BG_ANSI_GREEN+"D"+Color.BG_ANSI_YELLOW+"O"+Color.BG_ANSI_RESET;
    
 String lineOne = "                  " + board.get(49) + " " + board.get(50) + " " + board.get(51);
 String lineTwo = "     " + players.get(3).yard[0] + players.get(3).yard[1] + "      " + board.get(48) + " " + players.get(0).homerun[0] + " " + board.get(0) + "      " + players.get(0).yard[0] + players.get(0).yard[1];
 String lineThree = "     " + players.get(3).yard[2] + players.get(3).yard[3] + "      " + board.get(47) + " " + players.get(0).homerun[1] + " " + board.get(1) + "      " + players.get(0).yard[2] + players.get(0).yard[3];
 String lineFour = "                  " + board.get(46) + " " + players.get(0).homerun[2] + " " + board.get(2);
 String lineFive = "                  " + board.get(45) + " " + players.get(0).homerun[3] + " " + board.get(3);
 String lineSix = "                  " + board.get(44) + " " + players.get(0).homerun[4] + " " + board.get(4);
 String lineSeven = board.get(38) + "" + board.get(39) + "" + board.get(40) + "" + board.get(41) + "" + board.get(42) + "" + board.get(43) + "  "+ludo+"!!!  " + board.get(5) + "" + board.get(6) + "" + board.get(7) + "" + board.get(8) + "" + board.get(9) + "" + board.get(10);
 String lineEight = board.get(37) + "" + players.get(3).homerun[0] + "" + players.get(3).homerun[1] + "" + players.get(3).homerun[2] + "" + players.get(3).homerun[3] + "" + players.get(3).homerun[4] + "  "+ludo+"!!!  " + players.get(1).homerun[4] + "" + players.get(1).homerun[3] + "" + players.get(1).homerun[2] + "" + players.get(1).homerun[1] + "" + players.get(1).homerun[0] + "" + board.get(11);
 String lineNine = board.get(36) + "" + board.get(35) + "" + board.get(34) + "" + board.get(33) + "" + board.get(32) + "" + board.get(31) + "  "+ludo+"!!!  " + board.get(17) + "" + board.get(16) + "" + board.get(15) + "" + board.get(14) + "" + board.get(13) + "" + board.get(12);
 String lineTen = "                  " + board.get(30) + " " + players.get(2).homerun[4] + " " + board.get(18);
 String lineEleven = "                  " + board.get(29) + " " + players.get(2).homerun[3] + " " + board.get(19);
 String lineTwelve = "                  " + board.get(28) + " " + players.get(2).homerun[2] + " " + board.get(20);
 String lineThirteen = "     " + players.get(2).yard[0] + players.get(2).yard[1] + "      " + board.get(27) + " " + players.get(2).homerun[1] + " " + board.get(21) + "      " + players.get(1).yard[0] + players.get(1).yard[1];
 String lineFourTeen = "     " + players.get(2).yard[2] + players.get(2).yard[3] + "      " + board.get(26) + " " + players.get(2).homerun[0] + " " + board.get(22) + "      " + players.get(1).yard[2] + players.get(1).yard[2];
 String lineFifteen = "                  " + board.get(25) + " " + board.get(24) + " " + board.get(23);
 */

/*
 String stPeasent = " \u265f ";
 String whiteSix = Color.FG_ANSI_WHITE + stPeasent + stPeasent + stPeasent + stPeasent + stPeasent + stPeasent+Color.FG_ANSI_RESET;
        
 //String whiteSix = "\u265f" + Color.FG_ANSI_WHITE+"]" + Color.FG_ANSI_RED + Color.FG_ANSI_WHITE + Color.FG_ANSI_WHITE + Color.FG_ANSI_WHITE+ Color.FG_ANSI_RESET+"]";
 String whiteFour = "\u265f" + Color.FG_ANSI_WHITE + Color.FG_ANSI_WHITE + Color.FG_ANSI_WHITE + Color.FG_ANSI_WHITE + "]";
 String whiteTwo = "\u265f" + Color.FG_ANSI_WHITE + Color.FG_ANSI_WHITE + "]";
 String whiteOne = "\u265f" + Color.FG_ANSI_WHITE + "]";

 String lineOne = whiteSix + board.get(49)  + board.get(50)  + board.get(51);
 String lineTwo = whiteTwo + players.get(3).yard[0] + players.get(3).yard[1] + whiteTwo + board.get(48) + whiteOne + players.get(0).homerun[0] + whiteOne + board.get(0) + whiteTwo + players.get(0).yard[0] + players.get(0).yard[1];
 String lineThree = whiteTwo + players.get(3).yard[2] + players.get(3).yard[3] + whiteTwo + board.get(47) + whiteOne + players.get(0).homerun[1] + whiteOne + board.get(1) + whiteTwo + players.get(0).yard[2] + players.get(0).yard[3];
 String lineFour = whiteFour + board.get(46) + whiteOne + players.get(0).homerun[2] + whiteOne + board.get(2);
 String lineFive = whiteFour + board.get(45) + " " + players.get(0).homerun[3] + " " + board.get(3);
 String lineSix = "                  " + board.get(44) + " " + players.get(0).homerun[4] + " " + board.get(4);
 String lineSeven = board.get(38) + "" + board.get(39) + "" + board.get(40) + "" + board.get(41) + "" + board.get(42) + "" + board.get(43) + "  LUDO!!!  " + board.get(5) + "" + board.get(6) + "" + board.get(7) + "" + board.get(8) + "" + board.get(9) + "" + board.get(10);
 String lineEight = board.get(37) + "" + players.get(3).homerun[0] + "" + players.get(3).homerun[1] + "" + players.get(3).homerun[2] + "" + players.get(3).homerun[3] + "" + players.get(3).homerun[4] + "  LUDO!!!  " + players.get(1).homerun[4] + "" + players.get(1).homerun[3] + "" + players.get(1).homerun[2] + "" + players.get(1).homerun[1] + "" + players.get(1).homerun[0] + "" + board.get(11);
 String lineNine = board.get(36) + "" + board.get(35) + "" + board.get(34) + "" + board.get(33) + "" + board.get(32) + "" + board.get(31) + "  LUDO!!!  " + board.get(17) + "" + board.get(16) + "" + board.get(15) + "" + board.get(14) + "" + board.get(13) + "" + board.get(12);
 String lineTen = "                  " + board.get(30) + " " + players.get(2).homerun[4] + " " + board.get(18);
 String lineEleven = "                  " + board.get(29) + " " + players.get(2).homerun[3] + " " + board.get(19);
 String lineTwelve = "                  " + board.get(28) + " " + players.get(2).homerun[2] + " " + board.get(20);
 String lineThirteen = "     " + players.get(2).yard[0] + players.get(2).yard[1] + "      " + board.get(27) + " " + players.get(2).homerun[1] + " " + board.get(21) + "      " + players.get(1).yard[0] + players.get(1).yard[1];
 String lineFourTeen = "     " + players.get(2).yard[2] + players.get(2).yard[3] + "      " + board.get(26) + " " + players.get(2).homerun[0] + " " + board.get(22) + "      " + players.get(1).yard[2] + players.get(1).yard[2];
 String lineFifteen = "                  " + board.get(25) + " " + board.get(24) + " " + board.get(23);
    
 */
