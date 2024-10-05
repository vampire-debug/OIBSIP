import javax.swing.*;
import java.util.Random;

public class GuessTheNumber {
    private static final int MAX_ATTEMPTS = 10;
    private static final int LOWER_BOUND = 1;
    private static final int UPPER_BOUND = 100;

    public static void main(String[] args) {
        int score = 0;
        int rounds = 3; // Number of rounds to play

        for (int i = 1; i <= rounds; i++) {
            int numberToGuess = generateRandomNumber();
            int attempts = 0;

            JOptionPane.showMessageDialog(null, "Round " + i + ": Guess the number between " + LOWER_BOUND + " and " + UPPER_BOUND);

            boolean hasGuessedCorrectly = false;

            while (attempts < MAX_ATTEMPTS && !hasGuessedCorrectly) {
                String userInput = JOptionPane.showInputDialog("Enter your guess:");

                // Check if the input is a valid number
                int userGuess;
                try {
                    userGuess = Integer.parseInt(userInput);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid number.");
                    continue;
                }

                attempts++;

                if (userGuess < LOWER_BOUND || userGuess > UPPER_BOUND) {
                    JOptionPane.showMessageDialog(null, "Please guess a number between " + LOWER_BOUND + " and " + UPPER_BOUND);
                } else if (userGuess < numberToGuess) {
                    JOptionPane.showMessageDialog(null, "Higher! Attempts left: " + (MAX_ATTEMPTS - attempts));
                } else if (userGuess > numberToGuess) {
                    JOptionPane.showMessageDialog(null, "Lower! Attempts left: " + (MAX_ATTEMPTS - attempts));
                } else {
                    hasGuessedCorrectly = true;
                    JOptionPane.showMessageDialog(null, "Congratulations! You guessed the number: " + numberToGuess);
                    score += (MAX_ATTEMPTS - attempts + 1); // Score based on remaining attempts
                }
            }

            if (!hasGuessedCorrectly) {
                JOptionPane.showMessageDialog(null, "Sorry! You've used all attempts. The number was: " + numberToGuess);
            }
        }

        JOptionPane.showMessageDialog(null, "Game Over! Your total score is: " + score);
    }

    private static int generateRandomNumber() {
        Random random = new Random();
        return random.nextInt(UPPER_BOUND - LOWER_BOUND + 1) + LOWER_BOUND;
    }
}
