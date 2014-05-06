package ludo3;

/**
 *
 * @author Frederik
 */

/*
 This class is created to control all the colors in the game. I've choosen to make this a seperate class
because I think it is a good way to ensure that potential changes all be managed from the same place.
 */
public class Color {
// Constants used to color the tokens in the game.
    public static final String FG_ANSI_RESET = "\u001B[0m";
    public static final String FG_ANSI_RED = "\u001B[31m";
    public static final String FG_ANSI_GREEN = "\u001B[32m";
    public static final String FG_ANSI_YELLOW = "\u001B[33m";
    public static final String FG_ANSI_BLUE = "\u001B[34m";
    public static final String FG_ANSI_WHITE = "\u001B[37m";

// Optionals to color background in the game.    
    public static final String BG_ANSI_RESET = "\u001B[0m";
    public static final String BG_ANSI_RED = "\u001B[41m";
    public static final String BG_ANSI_GREEN = "\u001B[42m";
    public static final String BG_ANSI_YELLOW = "\u001B[43m";
    public static final String BG_ANSI_BLUE = "\u001B[44m";
    public static final String BG_ANSI_WHITE = "\u001B[47m";

}
// A "backup" set of colors, in case I would need them.
//public static final String ANSI_BLACK = "\u001B[30m";
//public static final String ANSI_PURPLE = "\u001B[35m";
//public static final String ANSI_CYAN = "\u001B[36m";

