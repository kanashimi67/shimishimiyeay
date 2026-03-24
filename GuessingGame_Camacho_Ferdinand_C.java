import java.util.Scanner;
import java.util.Random;

public class GuessingGame_Camacho_Ferdinand_C{

    private String name;
    private int target;
    private int count;

    private final int MAX_TRY = 10;
    private final int MIN_NUM = 1;
    private final int MAX_NUM = 100;

    Scanner sc = new Scanner(System.in);
    Random rand = new Random();

    public void displayWelcome() {
        System.out.println("========================================");
        System.out.println("    WELCOME TO THE GUESSING GAME!");
        System.out.println("========================================");
        System.out.println("Hi, " + name + "!");
        System.out.println("Guess a number between " + MIN_NUM + " and " + MAX_NUM + ".");
        System.out.println("You only have " + MAX_TRY + " tries.");
        System.out.println("Hints will be provided.\n");
    }

    public int generateSecretNumber() {
        return rand.nextInt(MAX_NUM - MIN_NUM + 1) + MIN_NUM;
    }

    public int getUserGuess() {
        int inputNum;

        while (true) {
            System.out.print("Enter your guess (1-100): ");
            inputNum = sc.nextInt();

            if (inputNum >= MIN_NUM && inputNum <= MAX_NUM) {
                return inputNum;
            } else {
                System.out.println("Invalid! Please enter a number between 1 and 100.");
            }
        }
    }

    public void giveHint(int inputNum) {
        if (inputNum < target) {
            System.out.println("Too low! Try a higher number.");
        } else if (inputNum > target) {
            System.out.println("Too high! Try a lower number.");
        }
    }

    public void playGame() {
        count = 0;
        boolean win = false;

        while (count < MAX_TRY) {
            count++;
            System.out.println("\n--- Attempt #" + count + " ---");

            int guess = getUserGuess();

            if (guess == target) {
                System.out.println("\nCongratulations " + name + "!");
                System.out.println("You guessed the number " + target + " in " + count + " attempts!");
                win = true;
                break;
            } else {
                giveHint(guess);
            }
        }

        if (!win) {
            System.out.println("\nGAME OVER!");
            System.out.println("You've used all " + MAX_TRY + " attempts.");
            System.out.println("The secret number was " + target + ".");
            System.out.println("Better luck next time, " + name + "!");
        }
    }

    public void displayStats() {
        String rating;

        if (count == 1) {
            rating = "Perfect!";
        } else if (count <= 3) {
            rating = "Excellent!";
        } else if (count <= 6) {
            rating = "Good job!";
        } else if (count <= 10) {
            rating = "Nice try!";
        } else {
            rating = "Better luck next time!";
        }

        System.out.println("\n========================================");
        System.out.println("            GAME STATISTICS");
        System.out.println("========================================");
        System.out.println("Player: " + name);
        System.out.println("Secret Number: " + target);
        System.out.println("Attempts Used: " + count);
        System.out.println("Rating: " + rating);
        System.out.println("========================================");
    }

    public boolean askPlayAgain() {
        System.out.print("\nWould you like to play again, " + name + "? (Y/N): ");
        char ans = sc.next().toLowerCase().charAt(0);
        return ans == 'y';
    }

    public void startGame() {
        System.out.print("Enter your name: ");
        name = sc.nextLine();

        boolean again;

        do {
            target = generateSecretNumber();
            displayWelcome();
            playGame();
            displayStats();
            again = askPlayAgain();
        } while (again);

        System.out.println("\n========================================");
        System.out.println("Thanks for playing, " + name + "!");
        System.out.println("See you next time!");
        System.out.println("========================================");
    }

    public static void main(String[] args) {
        GuessingGame obj = new GuessingGame();
        obj.startGame();
    }
}