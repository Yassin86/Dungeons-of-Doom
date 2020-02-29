import java.util.*;

/**
 * Class that carries out the game.
 * It creates a map and spawns all players and
 * tracks whose turn it is.
 */
public class GameLogic {

    private Map map;
    private HumanPlayer player;
    private BotPlayer bot;
    private int playerX;
    private int playerY;
    private int botX;
    private int botY;
    private boolean humanTurn = true;
    private boolean running = true;

    /**
     * Constructor for the game logic class
     * @param mapName The name of the map
     */
    private GameLogic(String mapName) {
        if (mapName == null) {
            map = new Map();
        }
        else {
            map = new Map(mapName);
        }
        player = new HumanPlayer();
        bot = new BotPlayer();
    }

    /**
     * Method to check if bot has caught player
     * @return True if bot co-ordinates match the players
     *         False otherwise
     */
    private boolean botCatch() {
        return (botX == playerX && botY == playerY);
    }

    /**
     * Method to check if enough gold has been collected
     * @return True if enough or more gold has been collected
     *         False otherwise
     */
    private boolean enoughGold() {
        return (map.getGoldOwned() >= map.getGoldRequired());
    }

    /**
     * Method that prints the map name
     */
    private void getMapN() {
        System.out.println(map.getMapName());
    }

    /**
     * Method to print a 5x5 grid around the player
     * to the command line
     */
    private void look() {
        for (int x = playerX - 2; x <= playerX + 2; x++) {
            for (int y = playerY - 2; y <= playerY + 2; y++) {
                //Checks x and y are within the map dimensions
                if (x >= 0 && y >= 0 && x < map.getMapLengthX()
                        && y < map.getMapLengthY()) {
                    //Outputs P if the player is at (x,y)
                    if (x == playerX && y == playerY) {
                        System.out.print('P');
                    }
                    //Outputs B if the bot is at (x,y)
                    else if (x == botX && y == botY) {
                        System.out.print('B');
                    }
                    //Outputs the character at the location (x,y)
                    else {
                        System.out.print(map.getMap(x, y));
                    }
                }
                //If x and y aren't within map dimensions, another wall is outputted
                else {
                    System.out.print('#');
                }
            }
            //New line after each line of map outputted
            System.out.println();
        }
    }

    /**
     * Method that moves a player a certain direction
     * @param direction: the direction of movement
     * @return String dictating whether the move was successful or not
     */
    private String move(char direction) {
        switch (direction) {
            case 'N':
                if (humanTurn) {
                    //First checks the position is not a wall
                    if (notWall(playerX - 1, playerY)) {
                        //Then moves the player corresponding to direction
                        this.playerX--;
                        //Returns the outcome of movement
                        return "SUCCESS.";
                    }
                    else {
                        //Error message outputted otherwise
                        return "FAIL.";
                    }
                }
                //If it is the bots turn....
                else {
                    if (notWall(botX - 1, botY)) {
                        this.botX--;
                        //No need to return outcome if it's the bot's turn
                        return null;
                    }
                    else {
                        //If the position to be moved to is a wall, the bot will
                        //move in the other direction
                        this.botX++;
                        return null;
                    }
                }
            case 'E':
                if (humanTurn) {
                    if (notWall(playerX, playerY + 1)) {
                        this.playerY++;
                        return "SUCCESS.";
                    }
                    else {
                        return "FAIL.";
                    }
                }
                else {
                    if (notWall(botX, botY + 1)) {
                        this.botY++;
                        return null;
                    }
                    else {
                        this.botY--;
                        return null;
                    }
                }
            case 'S':
                if (humanTurn) {
                    if (notWall(playerX + 1, playerY)) {
                        this.playerX++;
                        return "SUCCESS.";
                    }
                    else {
                        return "FAIL.";
                    }
                }
                else {
                    if (notWall(botX + 1, botY)) {
                        this.botX++;
                        return "SUCCESS.";
                    }
                    else {
                        this.botX--;
                        return null;
                    }
                }
            case 'W':
                if (humanTurn) {
                    if (notWall(playerX, playerY - 1)) {
                        this.playerY--;
                        return "SUCCESS.";
                    }
                    else {
                        return "FAIL.";
                    }
                }
                else {
                    if (notWall(botX , botY - 1)) {
                        this.botY--;
                        return null;
                    }
                    else {
                        this.botY++;
                        return null;
                    }
                }
            default:
                //Error message for invalid direction
                return "FAIL. \nMove must be N,E,S or W.";
        }
    }

    /**
     * Method which checks that a position in the map is not a wall
     * @param x: x co-ordinate of player
     * @param y: y co-ordinate of player
     * @return True if position at (x,y) is not a wall
     *         False otherwise
     */
    private boolean notWall(int x, int y) {
        return !(map.getMap(x,y) == '#');
    }

    /**
     * Method to pickup gold
     */
    private void pickUpGold() {
        //Checks player is standing on a tile containing gold
        if (map.getMap(playerX,playerY) == 'G') {
            //Increments goldOwned
            map.setGoldOwned(map.getGoldOwned() + 1);
            //Replaces G tile with an empty floor tile '.'
            map.setMap(playerX,playerY);
            System.out.print("SUCCESS. ");
            System.out.print("Gold owned: " + map.getGoldOwned() + "\n");
        }
        //Otherwise error message outputted
        else {
            System.out.print("FAIL. ");
            System.out.print("Gold owned: " + map.getGoldOwned() + "\n");
        }
    }

    /**
     * Method that handles inputs from both the bot
     * and human player classes
     */
    private void playGame() {
        String[] command;
        if (humanTurn) {
            //Convert command to string array
            command = player.getInputFromConsole().split(" ");
        }
        else {
            String commandStr = bot.getBotCommand(botX,botY,playerX,playerY);
            command = commandStr.split(" ");
            System.out.println("BOT COMMAND: " + commandStr);
        }
        //Performs switch-case on first element of array
        switch (command[0]) {
            case "HELLO":
                System.out.println("Gold to win: " + map.getGoldRequired());
                break;
            case "GOLD":
                System.out.println("Gold owned: " + map.getGoldOwned());
                break;
            case "LOOK":
                //Only prints look function if it's the player's turn
                if (humanTurn) {
                    look();
                    break;
                }
                else {
                    break;
                }
            case "PICKUP":
                pickUpGold();
                break;
            case "MOVE":
                if (humanTurn) {
                    //Checks if there is a second string after "MOVE" catching the
                    //exception which is thrown otherwise
                    try {
                        if (command[1].length() == 1) {
                            //Sends the first character of the second string to the move method
                            System.out.println(move(command[1].charAt(0)));
                            break;
                        }
                        else {
                            System.out.println("INVALID.");
                            break;
                        }
                    }
                    catch (ArrayIndexOutOfBoundsException e) {
                        //Error message if command[1] doesn't exist
                        System.out.println("INVALID.");
                        break;
                    }
                }
                else {
                    //If it's the bots turn, send character to move method
                    move(command[1].charAt(0));
                    break;
                }
            case "QUIT":
                quitGame();
                break;
            default:
                //Error message output if unrecognised input entered
                System.out.println("INVALID.");
        }
        //Switches turns between human and bot players
        humanTurn = !humanTurn;
    }

    /**
     * Method that generates a random x co-ordinate for the bot
     */
    private void randomBotX() {
        this.botX = (int) (Math.random() * map.getMapLengthX());
    }

    /**
     * Method that generates a random y co-ordinate for the bot
     */
    private void randomBotY() {
        this.botY = (int) (Math.random() * map.getMapLengthY());
    }

    /**
     * Method that generates a random x co-ordinate for the player
     */
    private void randomX() {
        this.playerX = (int) (Math.random() * map.getMapLengthX());
    }

    /**
     * Method that generates a random y co-ordinate for the player
     */
    private void randomY() {
        this.playerY = (int) (Math.random() * map.getMapLengthY());
    }

    /**
     * Method that spawns the bot into the game
     * by generating random co-ordinates
     */
    private void spawnBot() {
        //Generates random x and y co-ordinates
        randomBotX();
        randomBotY();
        boolean cantSpawn = true;
        while (cantSpawn) {
            //Checks that the bot isn't spawning in a wall or on a player
            if (map.getMap(botX, botY) != '#' && botX != playerX
                    && botY != playerY) {
                cantSpawn = false;
            }
            //Otherwise, generate bot co-ordinates again
            else {
                randomBotX();
                randomBotY();
            }
        }
    }

    /**
     * Method that spawns the player into the game
     * by generating random co-ordinates
     */
    private void spawnPlayer() {
        randomX();
        randomY();
        boolean cantSpawn = true;
        while (cantSpawn) {
            //Checks player is not spawning in a wall or on gold
            if (map.getMap(playerX, playerY) != '#' &&
                    map.getMap(playerX, playerY) != 'G') {
                cantSpawn = false;
            }
            //Otherwise, generate player co-ordinates again
            else {
                randomX();
                randomY();
            }
        }
    }

    /**
     * Method that handles quitting the game
     */
    private void quitGame() {
        //Check first that the player is on an EXIT tile and has enough gold
        if (map.getMap(playerX,playerY) == 'E' && enoughGold()) {
            //Print winning messages
            System.out.println("WIN");
            System.out.println("Well done, you have collected all " +
                    "the gold required.");
            //Flip the running boolean so that game stops running
            running = !running;
        }
        else {
            //Otherwise, print LOSE.
            System.out.println("LOSE.");
            //Extra messages when player has lost based on quitting guidelines
            if (enoughGold()) {
                System.out.println("You did not quit on an exit tile.");
                running = !running;
            }
            else {
                System.out.println("You did not collect enough gold to win.");
                running = !running;
            }
        }
    }

	public static void main(String[] args) {
        GameLogic logic;

        System.out.println("Welcome to Dungeons of Doom.");
        System.out.println("You can either play with the default map" +
                " or your own map.");
        System.out.println("Would you like to load your own map? " +
                "[Enter Y for YES]");

        //Scanner to take in user input
        Scanner x = new Scanner(System.in);
        String input = x.next();

        //If input is yes, load the specified file, otherwise, use default map
        if (input.equals("Y")) {
            System.out.println("Enter file name:");
            String fileName = x.next();
            logic = new GameLogic(fileName);
        }
        else {
            logic = new GameLogic(null);
        }

        //Tells the player the name of the map they've chosen
        System.out.print("You have chosen the map: ");
        logic.getMapN();
        System.out.println("Game has begun. You go first.");

        //Spawns both the player and the bot
		logic.spawnPlayer();
        logic.spawnBot();

        /*
        Plays the game while its running and the bot
        hasn't caught the player
         */
		while (logic.running && !logic.botCatch()) {
		    logic.playGame();
        }

        //Losing messages for if bot catches player
        if (logic.botCatch()) {
            System.out.println("Bot has caught you.");
            System.out.println("LOSE.");

        }
    }
}