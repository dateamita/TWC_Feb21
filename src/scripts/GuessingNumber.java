package scripts;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import utility.*;

public class GuessingNumber {
    Utilities ut = new Utilities();

    public int acceptGuessedNumber(int numVal) {
        int guessedNum;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Guess a number between 1 and " + numVal);
        try {
            guessedNum = Integer.parseInt(br.readLine().replaceAll("\\s", "")); // Remove spaces leading, trailing, in-between spaces.
        } catch (NumberFormatException | IOException ex) {
            return 0;
        }
        return guessedNum;
    }

    public void generateRandomGuess(int numVal) {
        int maxAttempts, attemptsTaken = 0, winAmount, loseAmount, validGuess, userRewards, randomVal;
        try {
            maxAttempts = Integer.parseInt(ut.getProperties("noOfAttempts"));
            winAmount = Integer.parseInt(ut.getProperties("winAmount"));
            loseAmount = Integer.parseInt(ut.getProperties("loseAmount"));
            randomVal = ut.randomNum(numVal);
            System.out.println("randomVal" + randomVal);
            while (attemptsTaken != maxAttempts) {
                validGuess = acceptGuessedNumber(numVal);
                if (validGuess == 0) {
                    System.out.println("Please enter valid number");
                } else if (validGuess == randomVal) {
                    userRewards = winAmount - (loseAmount * attemptsTaken);
                    System.out.println("****Congrats you won $" + userRewards + " for choosing number " + validGuess + "****");
                    break;
                } else if (validGuess < randomVal) {
                    System.out.println("Guess Again!! Lower than the actual");
                } else {
                    System.out.println("Guess Again!! Higher than the actual");
                }
                attemptsTaken++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void acceptNum(int ageInput) {
        int numVal = 0;
        boolean valid = false;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (!valid) {
            System.out.println("Choose a number greater than " + ageInput);
            try {
                numVal = Integer.parseInt(br.readLine().replaceAll("\\s", "")); // Remove spaces leading, trailing, in-between spaces.
                if (numVal > ageInput) {
                    valid = true;
                } else {
                    System.out.println("Please number greater than " + ageInput);
                }
            } catch (NumberFormatException | IOException ex) {
                System.out.println("Please enter valid number!!");
            }
        }
        generateRandomGuess(numVal);
    }

    public void validateAge() {
        int userAge;
        System.out.println("What's your age: ");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            userAge = Integer.parseInt(br.readLine().replaceAll("\\s", ""));
            if (userAge > Integer.parseInt(ut.getProperties("userMinAge")) && userAge < Integer.parseInt(ut.getProperties("userMaxAge"))) {
                acceptNum(userAge);
            } else {
                System.out.println("You are not eligible to play this game. Minimum age is 20");
            }
        } catch (NumberFormatException | IOException ex) {
            System.out.println("Please enter valid age!!");
        }
    }

    public static void main(String[] args) {
        GuessingNumber no = new GuessingNumber();
        no.validateAge();
    }
}
