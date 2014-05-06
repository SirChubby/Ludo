package ludo3;

/**
 *
 * @author Frederik
 */
public class Token {

    // First we initialize a number of different variables used by the class Token.
    boolean home = true;// By setting home to true makes the tokens stay in the yard, as a default..
    boolean done = false;// done is used to check if each token is done.
    int position = 0;// the default position, that the tokens move to, once a 6 has been "rolled" by the dice.
    int tokenId;//Use to ID each token.
    Spot currentSpot;
    Player player;

    public Token(int tokenId, Player player) {// This method stores the tokenId for each token as their tokenId for each player respectively.
        this.tokenId = tokenId;
        this.player = player;
    }
    /*
     This is one of the more important methods. First it checks whether a given token 
     is in the yard (or at home, as you could say). If that is true, the token needs the 
     roll from the dice to equal 6. If it equals 6, it changes the value of home to false, hereby "moving"
     the roken to the board. If home = false, the token get to move the rolled amount of spaces along the board.
     If the current position of the token is equal to or larger than the size of the board,
     the value of done is set to true, and the token has reached its goal.
     */

    public boolean moveToken(int spaces) {
        if (done) {
            System.out.println("Token " + tokenId + " can't be moved!");
            return false;
        } else if (spaces == 6 && home) {
            home = false;
            getCurrentSpot().getTokens().remove(this);
            position = (player.playerId - 1) * 13;
            setCurrentSpot(Board.board.get(position));
            getCurrentSpot().addToken(this);
            return true;
        } else if (!home && !done) {
            position += spaces;
            int whereIsToken = getCurrentSpot().whereIsToken(this);
            if (whereIsToken > -1) {
                getCurrentSpot().removeToken(whereIsToken);
//                System.out.println("Token removed! " + whereIsToken); //Used for debugging, to see if the token really was removed.
            }
            if (position - (player.playerId - 1) * 13 > Board.board.size()) { // If statement checking whether the token is done.
                if (position - (player.playerId - 1) * 13 >= Board.board.size() + player.homerun.length) {
                    this.done = true;
                } else {
                    setCurrentSpot(player.homerun[position - (player.playerId - 1) * 13 - Board.board.size()]);
                    getCurrentSpot().addToken(this);
                }
            } else {
                if (position >= Board.board.size()) {
                    setCurrentSpot(Board.board.get(position - Board.board.size()));
                    getCurrentSpot().addToken(this);
                } else {
                    setCurrentSpot(Board.board.get(position));
                    getCurrentSpot().addToken(this);
                }
            }
//            System.out.println("MoveToken");
        }
        return true;
    }

    public int getTokenId() {// a getter method, returning a token ID.
        return tokenId;
    }

    public boolean isHome() { // a getter method, returning whether or not a token is done, meaning wherther or not it has reached finnish? 
        return home;
    }

    public void setHome(boolean home) {
        this.home = home;
    }

    public boolean isDone() {
        return done;
    }

    public void printToken() { // This method is used to print the IDs and the corresponding positions of each token respectively.
        System.out.print("Token id: " + tokenId + " | ");
        System.out.print("Pos: " + position + " | ");
        System.out.print("isHome: " + home + " | ");
        System.out.println("isDone: " + done);
    }

    public Player getOwner() {
        return player;
    }

    /**
     * @return the currentSpot
     */
    public Spot getCurrentSpot() {
        return currentSpot;
    }

    /**
     * @param currentSpot the currentSpot to set
     */
    public void setCurrentSpot(Spot currentSpot) {
        this.currentSpot = currentSpot;
    }
}
