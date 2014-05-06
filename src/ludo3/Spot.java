package ludo3;

import java.util.ArrayList;

/**
 *
 * @author Frederik
 */
public class Spot {

    ArrayList<Token> tokens = new ArrayList<>(); //An ArrayList containing the tokens.
    int position; // An integer used to keep track of the tokens positions.

    public Spot(int position) { // This method links a given spot with the respective position of a token by invoking the constructor.
        this.position = position;
    }

    public int getNumberOfTokens() { // A getter method, returning the amount of tokens (as an expression of the size of the ArrayList of the tokens.)
        return tokens.size();
    }

    public int whereIsToken(Token token) { // This method runs through each token and returns the position of each token respectively.
        for (int i = 0; i < tokens.size(); i++) {
            if (tokens.get(i) == token) {
                return i;
            }
        }
        return -1;
    }

    public void removeToken(int position) { // This method is removing the token from its last position, so that we don't get more than 4 token per player.
        tokens.remove(position);
    }

    public void addToken(Token token) { // This method add the token to the new position it has moved to.
        tokens.add(token);
    }

    public ArrayList<Token> getTokens() { // A getter method returning the tokens in the ArrayList.
        return tokens;
    }

    @Override // Override annotation that 
    /*
     This method is coloring the spot that the tokens is moving to in their respective colors. 
    
    @Overriding - see p444
    
    toString - see p459
     */
    public String toString() {
        String output = "";
        if (tokens.isEmpty()) { // And if there's no tokens, the method will just print a e"normal" spot.
            output = "[ ]";
        } else {
            output = "[";
            //output += tokens.get(0).getOwner().getPlayerId();
            switch (tokens.get(0).getOwner().getPlayerId()) { // This switch case is reacting on whose turn it is, thereby know what color to print.
                case 1:
                    output += Color.FG_ANSI_RED; // Each of these colors are defined in the color class
                    break;
                case 2:
                    output += Color.FG_ANSI_BLUE;
                    break;
                case 3:
                    output += Color.FG_ANSI_GREEN;
                    break;
                case 4:
                    output += Color.FG_ANSI_YELLOW;
                    break;
                default:
                    break;
            }
            output += "\u2659" + Color.BG_ANSI_RESET + "]"; // Line 50 and 70 makes the brackets around the colored token in the reset color.
        }
        return output; //and then returns the collective output.
    }
}
/*
 U+2659 gennemsigtig

 "\u265f" solid

 U+265D bishop

 26C4 ghost

 */
