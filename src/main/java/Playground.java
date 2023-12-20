import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;

public class Playground {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Variable to store user input
        AtomicReference<String> userInput = new AtomicReference<>("");

        // Start a separate thread to wait for the input
        Thread inputThread = new Thread(() -> {
            System.out.println("Please enter your input:");
            if (scanner.hasNextLine()) {
                userInput.set(scanner.nextLine());
                System.out.println("User input: " + userInput);
            }
        });

        inputThread.start();

        // Wait for the user input or 2 seconds, whichever comes first
        try {
            inputThread.join(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Check if the input thread is still alive
        if (inputThread.isAlive()) {
            inputThread.interrupt();
            userInput.set("Type faster bozo");
            System.out.println("Time's up! Input skipped.");
        }

        // Use userInput after the thread is killed or if input is received
        System.out.println("User input: " + userInput);

        System.out.println("Hello");
    }
}