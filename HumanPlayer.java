import java.io.*;

/**
 * Class that handles all human player commands
 */
public class HumanPlayer {

    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    /**
     * Method to accept input from command line
     * @return returns the input as a string
     */
    public String getInputFromConsole() {
        try {
            String input = reader.readLine();
            //If input is null exit the program
            if (input == null) {
                System.exit(0);
            }
            return input;
        }
        catch (IOException e) {
            System.out.println("Error.");
            System.exit(0);
            return null;
        }
    }
}