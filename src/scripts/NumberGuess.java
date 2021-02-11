package scripts;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import utility.*;

public class NumberGuess {
    int userAge = 0, numVal = 0, guessedNum = 0, randomVal = 0, userRewards = 0, maxAttempts = 0, attemptsTaken=0, winAmount=0, loseAmount=0, validGuess = 0;
    boolean valid = false;

    /** Accept number from user which would be in between 1 and number entered by user*/
    public int acceptGuessedNumber(int numVal){
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Guess a number between 1 and " + numVal);
        try {
            guessedNum = Integer.parseInt(br.readLine().replaceAll("\\s","")); // Remove spaces leading, trailing, in-between spaces.
        } catch (NumberFormatException | IOException ex) {
            return 0;
        }
        return guessedNum;
    }

    /** This is function to check whether guessed number is equal to system generated random number*/
    public void generateRandomGuess(){
        try{
            Utilities ut = new Utilities();
            maxAttempts = Integer.parseInt(ut.getProperties("noOfAttempts"));
            winAmount = Integer.parseInt(ut.getProperties("winAmount"));
            loseAmount = Integer.parseInt(ut.getProperties("loseAmount"));
            System.out.println("What's your age: ");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            userAge = Integer.parseInt(br.readLine().replaceAll("\\s", ""));
            if (userAge> Integer.parseInt(ut.getProperties("userMinAge")) && userAge<Integer.parseInt(ut.getProperties("userMaxAge"))){
                while(!valid){
                    System.out.println("Choose a number greater than " + userAge);
                    try {
                        numVal = Integer.parseInt(br.readLine().replaceAll("\\s","")); // Remove spaces leading, trailing, in-between spaces.
                        if (numVal > userAge){
                            valid = true;
                        }
                    } catch (NumberFormatException | IOException ex) {
                        System.out.println("Please enter valid number!!");
                    }
                }
                randomVal = ut.randomNum(numVal);
                System.out.println("Random No: " + randomVal);
                while(attemptsTaken!=maxAttempts){
                    validGuess = acceptGuessedNumber(numVal);
                    if(validGuess == 0){
                        System.out.println("Please enter valid number");
                    }else if(validGuess == randomVal){
                        userRewards = winAmount - (loseAmount * attemptsTaken);
                        System.out.println("****Congrats you won $" + userRewards +" for choosing number " + validGuess + "****");
                        break;
                    }else if(validGuess < randomVal){
                        System.out.println("Guess Again!! Lower than the actual");
                    }else {
                        System.out.println("Guess Again!! Higher than the actual");
                    }
                    attemptsTaken++;
                }
            }else{
                System.out.println("You are not eligible to play this game. Minimum age is 20");
            }
        }catch(NumberFormatException | IOException ex){
            System.out.println("Please enter valid age!!");
        }
    }

    public static void main(String[] args){
        NumberGuess no = new NumberGuess();
        no.generateRandomGuess();
    }
}
