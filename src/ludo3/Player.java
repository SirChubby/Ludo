package ludo3;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Frederik
 */
public class Player {

    //This class is the Player(s) playing the Ludo game.
    ArrayList<Token> tokens = new ArrayList<>();// This ArrayList contains the Tokens for each player in the Ludo game.
    int playerId;// An integer variable to keep track of the ID's of each player, so that we don't mistake one player for the other.
    Spot[] yard = new Spot[4];
    Spot[] homerun = new Spot[7];
    static Scanner scanner = new Scanner(System.in); // By making this scanner static, enables all the user to use the same scanner!

    /*
     This method takes the parameters playerId of the variable type integer.
     The method uses the this keyword to refer to the particular player, 
     so that it can assign 4 tokens, a yard, and a homerun to the player using a for loop.
     */
    public Player(int playerId) {
        this.playerId = playerId;
        for (int i = 1; i <= 4; i++) {
            yard[i - 1] = new Spot(i);
            Token token = new Token(i, this);
            yard[i - 1].addToken(token);
            token.setCurrentSpot(yard[i - 1]);
            tokens.add(token);
        }
        for (int i = 0; i < homerun.length; i++) {
            homerun[i] = new Spot(i + 1);
        }
    }

    public boolean isTheGameDone() {//Method to check if the game is done.
        int done = 0;
        for (int i = 0; i < tokens.size(); i++) {//If all the tokens of a player has the value is isDone = true, the game is done.
            if ((tokens.get(i).isDone())) {
                done++;
            }
        }
        return done == tokens.size();
    }

    public void captureToken() { // Method to handle what happens when one player captures another players token.
        for (int i = 0; i < tokens.size(); i++) {
            if (!tokens.get(i).isHome()) {
                for (int j = 0; j < tokens.get(i).getCurrentSpot().getNumberOfTokens(); j++) {
                    Token spotToken = tokens.get(i).getCurrentSpot().getTokens().get(j);
                    if (spotToken.getOwner().getPlayerId() != playerId) {
                        spotToken.getCurrentSpot().tokens.remove(spotToken);
                        spotToken.setHome(true);
                        spotToken.position = 0;
                        spotToken.getOwner().yard[spotToken.tokenId - 1].addToken(spotToken);
                        spotToken.currentSpot = spotToken.player.yard[spotToken.tokenId - 1];
                        System.out.println("Player " + playerId + " captured token a number from player " + spotToken.getOwner().getPlayerId() + "!");
                    }
                }
            }
        }
    }

    public void turn() {// This method is used to control the way the turn is played. 
        System.out.println("Player " + playerId + "'s turn!");// First we print out whose turn it is.
        System.out.println("Press enter to roll dice..."); // Then we ask the player to roll the dice
        scanner.nextLine(); // Then we enable input from the user, so that we can roll the dice and select which token to move.
        int roll = Board.rollDice();// then we initialize a variable roll of the type integer and store it as the result of the rollDice method from the class PrintBoard.
        /*
         The reason we call it from the Board and not directly from the 
         class Dice, is because that all the players share the same dice, 
         instead of using one dice each. This is also represented in the (pseudo)UML-Diagram.
         */
        System.out.println("Player " + playerId + " rolled " + roll);//Printing the result of the dice roll.
        System.out.println();
        printTokens();

        int n = 0;
        for (int i = 0; i < 4; i++) {//If all the tokens of a player is in the yard and there's not rolled a 6, then the player cannot move any tokens.
            if ((tokens.get(i).isHome() == true) && !(roll == 6) || tokens.get(i).isDone()) {
                n++;
            }
        }
        if (n > 3) {
            System.out.println("You cannot move any tokens.");
        } else {
            System.out.print("Choose which token to move " + roll + " spaces: "); // So far the code asks the player which token she/he wishes to move.
            int choice = -1;
            while (choice < 0 || choice > tokens.size()) {// A while loop checking if the choosen token is a valid token.
                try {// If the token choosen is not a valid token (ie. the integer inputted by the user) the system will tell the user that it is not a valid number or not a valid token.
                    // This is just a way of letting the program not terminate if a wrong value is inputted by the user.
                    String temp = scanner.nextLine();
                    choice = Integer.parseInt(temp);
                } catch (NumberFormatException e) {
                    System.out.println(choice + " is not a valid number, try again!"); // This tells the user that she/he has not input an integer.
                    choice = -1;// and then resets the choice to -1 so the user can try again
                    continue;
                }
                if (choice < 0 || choice > tokens.size() || tokens.get(choice - 1).isDone() || ((tokens.get(choice - 1).isHome()) && roll != 6)) {
                    System.out.println(choice + " is not a valid piece, try again!"); // This tells the user that the integer they've typed is not equal to any of the tokens they have.
                    choice = -1;
                }
            }
            /*
             // This takes the token that we have choosen-1 (becuase it is from an arrayList, 
             the first index is 0, and since we don't want to use token number 0,1,2,3 we then have to 
             take the choice the user just inputtet and substract 1) and apply to it 
             the method moveToken with the result of the roll of the dice.
             */
            tokens.get(choice - 1).moveToken(roll);
        }
        captureToken();
        //If statement checking whether the spot moved to contains another player.
        if (isTheGameDone()) {
            System.out.println("***Congratulations player " + playerId + "! You Won the Game!***");
            System.exit(0);
        }
    }

    public void aiTurn() {
        /*
         This method is almost identical to the turn() method.
         The difference is that instead of getting the choice through the console,
         it gets the choice from a random method.
         */
        System.out.println("Player " + playerId + "'s turn!");
        int roll = Board.rollDice();
        System.out.println("Player " + playerId + " rolled " + roll);
        System.out.println();
        printTokens();

        int n = 0;
        for (int i = 0; i < 4; i++) {
            if ((tokens.get(i).isHome() == true && !(roll == 6)) || tokens.get(i).isDone()) {
                n++;
            }
        }
        if (n > 3) {
            System.out.println("You cannot move any tokens.");
        } else {
            System.out.print("Choose which token to move " + roll + " spaces: ");
            System.out.println();
            int choice = -1;
            while (choice < 0 || choice > tokens.size()) {
                try {
                    choice = (int) (Math.random() * 4 + 1);
                } catch (NumberFormatException e) {
                    System.out.println(choice + " is not a valid number, try again!");
                    choice = -1;
                    continue;
                }//  || (tokens.get(choice - 1).isHome()) && roll != 6
                if (choice < 0 || choice > tokens.size() || tokens.get(choice - 1).isDone()) {
                    System.out.println(choice + " is not a valid piece, try again!");
                    choice = -1;
                }
            }
            System.out.println("Player " + playerId + " has moved token " + choice);
            tokens.get(choice - 1).moveToken(roll);
        }
        System.out.println();
        captureToken();
        if (isTheGameDone()) {
            System.out.println("***Congratulations player " + playerId + "! You Won the Game!***");
            System.exit(0);
        }
    }

    private void moveToken(int id, int spaces) { // This method gets the id from the tokens and asks it to move according to the method moveToken, which receives its information from the Token class.
        tokens.get(id).moveToken(spaces);
    }

    public void printTokens() { //This method prints all the tokens for each player, using an enhanced for-loop.
        System.out.println("Player: " + playerId);
        for (Token token : tokens) {
            token.printToken();
        }
        System.out.println();
    }

    public int getPlayerId() {// This method enables easy access to the playerId variable from other classes.
        return playerId;
    }
}
