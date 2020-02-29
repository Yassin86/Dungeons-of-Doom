/**
 * Class that handles all bot commands
 */
public class BotPlayer {

    private int counter = -1;

    /**
     * Method that decide what the bot will do
     * @param botX: Bot x co-ordinate
     * @param botY: Bot y co-ordinate
     * @param playerX: Player x co-ordinate
     * @param playerY: PLayer y co-ordinate
     * @return returns the command for the bot as a String
     */
    public String getBotCommand(int botX, int botY, int playerX, int playerY) {
        //Increments counter every time the method is called
        counter++;
        String command = null;
        //Bot first command is LOOK
        if (counter == 0) {
            command = "LOOK";
        }
        //Bot performs look function every 4th turn
        else if (counter % 4 == 0) {
            command = "LOOK";
        }
        //Checks if the player is withing the look functions view (5x5 around the bot)
        else if (Math.abs(botX - playerX) < 3 && Math.abs(botY - playerY) < 3) {
            //Moves towards the player depending on position
            if (botX < playerX) {
                command = "MOVE S";
            }
            else if (botX > playerX) {
                command = "MOVE N";
            }
            else if (botY > playerY) {
                command = "MOVE W";
            }
            else if (botY < playerY) {
                command = "MOVE E";
            }
        }
        //Otherwise, the bot will chose direction randomly
        else {
            int rand = (int)(Math.random()*100);
            //Modulo 4 as there are only 4 possible directions
            int b = rand % 4;
            switch (b) {
                case 0:
                    command = "MOVE N";
                    break;
                case 1:
                    command =  "MOVE E";
                    break;
                case 2:
                    command = "MOVE S";
                    break;
                case 3:
                    command = "MOVE W";
                    break;
            }
        }
        return command;
    }
}