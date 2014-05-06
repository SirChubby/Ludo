package ludo3;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Frederik
 */
public class Dice {
// Method that rolls the dice and returns the result of the roll. The result is a random integer between 1 and 6.
// Creating an object r of the type random.

    Random r = new Random();

    public int roll() {

        return r.nextInt(6) + 1;
        //The following is used for debuggning
//        System.out.print("Roll number: "); //Test method used to control the roll of the dice.
//        return new Scanner(System.in).nextInt();
    }
}
