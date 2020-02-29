import java.io.*;

/**
 * Class that stores the map of the game
 * in memory and also enables the map to
 * be loaded from a file.
 */
public class Map {

    private char[][] map;
    private int goldOwned = 0;
	private int counter = 0;
	private String mapName;
	private int goldRequired;
	private int mapLengthX;
	private int mapLengthY;

	/**
	 * Constructor for if the map is to be read
	 * from a file
	 * @param fileName: takes in the name of the file
	 *                  to be read from
	 */
    public Map(String fileName) {
        //First gathers information about the file
        mapInfo(fileName);
        //Then reads from it into the map array
        readMap(fileName);
    }

	/**
	 * Constructor for the default map
	 */
	public Map() {
		mapName = "Very small Labyrinth of Doom";
		goldRequired = 2;
		map = new char[][] {
		{'#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#'},
		{'#','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','#'},
		{'#','.','.','.','.','.','.','G','.','.','.','.','.','.','.','.','.','E','.','#'},
		{'#','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','#'},
		{'#','.','.','E','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','#'},
		{'#','.','.','.','.','.','.','.','.','.','.','.','G','.','.','.','.','.','.','#'},
		{'#','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','#'},
		{'#','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','#'},
		{'#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#'}
		};
		mapLengthX = map.length;
		mapLengthY = map[0].length;
	}

	/**
	 * Get method for the goldOwned field
	 * @return goldOwned (integer)
	 */
    public int getGoldOwned() {
        return goldOwned;
    }

	/**
	 * Set method for the goldOwned field
	 * @param goldOwned: Takes in an integer input
	 *                 which will be assigned to the
	 *                 goldOwned field
	 */
	public void setGoldOwned(int goldOwned) {
        this.goldOwned = goldOwned;
    }

	/**
	 * Get method for the goldRequired field
	 * @return goldRequired (integer)
	 */
	public int getGoldRequired() {
        return goldRequired;
    }

	/**
	 * Method that returns the contents of the map
	 * at a specific location
	 * @param x: the x co-ordinate of the position
	 * @param y: the y co-ordinate of the position
	 * @return The character stored in the map at that location
	 */
	public char getMap(int x, int y) {
    	return map[x][y];
	}

	/**
	 * Changes the tile in the grid to empty
	 * @param x: x co-ordinate of position to be changed
	 * @param y: y co-ordinate of position to be changed
	 */
	public void setMap(int x, int y) {
    	this.map[x][y] = '.';
	}

	/**
	 * Get method for getMapName field
	 * @return getMapName (String)
	 */
    public String getMapName() {
        return this.mapName;
    }

	/**
	 * Get method for mapLengthX field
	 * @return mapLengthX (integer)
	 */
	public int getMapLengthX() {
    	return this.mapLengthX;
	}

	/**
	 * Get method for mapLengthY field
	 * @return mapLengthY (integer)
	 */
	public int getMapLengthY() {
    	return this.mapLengthY;
	}

	/**
	 * Method that reads the file for the first
	 * time in order to store the goldRequired, mapName,
	 * mapLengthX and mapLengthY
	 * @param fileName Name of the file to be read
	 */
    private void mapInfo(String fileName) {
    	try {
    	    String line;
			FileReader fileIn = new FileReader(fileName + ".txt");
			BufferedReader readFile = new BufferedReader(fileIn);
			//While NOT EOF (end-of-file)
			while ((line = readFile.readLine()) != null) {
				//counter to count number of lines
				//increments each time line is read
				this.counter += 1;
				if (counter == 1) {
				   this.mapName = line.substring(5);
				}
				else if (counter == 2) {
				   this.goldRequired = Integer.parseInt(line.substring(4));
				}
				else if (counter == 3) {
					this.mapLengthY = line.length();
				}
			}
			readFile.close();
			//Number of lines minus the name and win amount
            this.mapLengthX = counter - 2;
		}
		catch (IOException e) {
    	    //Smooth exit if exception is caught
            System.out.println("Error. File not found.");
            System.exit(0);
        }
    }

	/**
	 * Read map for second time in order to
	 * store the characters of the grid in the
	 * map 2d array
	 * @param fileName: Name of file to be read
	 */
	private void readMap(String fileName) {
		try {
			int counter2 = 0;
			//map array is initialised with length of axes
			map = new char[mapLengthX][mapLengthY];
			String line;
			//Adds .txt at the end so the user doesn't have to
			FileReader fileIn = new FileReader(fileName + ".txt");
			BufferedReader readFile = new BufferedReader(fileIn);
			while ((line = readFile.readLine()) != null) {
				counter2 += 1;
				if (counter2 >= 3) {
					//Skips the first 2 lines as they do not contain the grid
					//Line from the file is converted to a character array
					char[] charArray = line.toCharArray();
					//Loop through the character array
					for (int i = 0; i < charArray.length; i++) {
						//line is copied into the map array
						map[counter2 - 3][i] = charArray[i];
					}
				}
			}
			readFile.close();
		}
		catch (IOException e) {
			System.out.println("Error.");
			System.exit(0);
		}
	}
}