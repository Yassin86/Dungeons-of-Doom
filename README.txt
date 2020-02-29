=====================================================================
READ ME FOR DUNGEONS OF DOOM
=====================================================================
INSTALLATION INSTRUCTIONS:
---------------------------------------------------------------------
This game must be installed and played on a command line.
On a linux system, type javac *.java to compile the game files.
Then type java GameLogic to begin running the game.
---------------------------------------------------------------------
LOADING THE MAP:
---------------------------------------------------------------------
Ensure that if the map is to be loaded into the game, the map file
must be a text file and should be stored in the same folder as the 
source files. When prompted to enter that map name, there is no 
need to add .txt, as this is done automatically. If the map specif
-ied doesn't exist, the program will stop running and will need to 
be run again. If you do not enter "Y" when asked if you want to 
load a map, a default map will be used.
---------------------------------------------------------------------
CREATING YOUR OWN MAPS:
---------------------------------------------------------------------
If you wish to create your own maps, there is a format that needs
to be followed. The first two lines MUST be as specified below.
name MAP-NAME-GOES-HERE
win AMOUNT-OF-GOLD-TO-WIN-GOES-HERE
The map begins on the 3rd line. The map MUST be surrounded by walls.
(see MAP KEY)
See example below.
##########
#...G.E..#
#..E...G.#
#........#
##########
Amount of gold needed to win cannot be more than the number of 
gold tiles but can be less. There must be at least 1 exit tile 
otherwise winning is impossible.
If you do not follow the rules above, you will not be able to play
the game properly.
---------------------------------------------------------------------
MAP KEY:
---------------------------------------------------------------------
'P': represents the position of a human player.
'B': represents the position of a bot player.
'.': an empty floor that you can walk over.
'G': a tile containing gold to be collected.
'E': an exit tile.
'#': a wall which cannot be passed through.
---------------------------------------------------------------------
HOW TO PLAY:
---------------------------------------------------------------------
The dungeon is a rectangular map. You are a brave fortune-hunter 
and you move around the dungeon collecting gold. The goal is to get 
enough gold and then exit the dungeon.
The dungeon is made up of different types of characters. 
[SEE THE MAP KEY]
You play the game by entering commands into the command line.
Valid commands are:
"HELLO": Shows how much gold is needed to win.
"GOLD": Shows how much gold you currently own.
"PICKUP": Picks up gold if you're on a tile containing gold.
"MOVE <direction>": Moves in the direction specified. Direction
	can be N,E,S or W.
"LOOK": Will print a 5x5 grid around you allowing you to see parts
	of the dungeon.
"QUIT": Quits the game. In order to win, you must have enough gold
	AND enter QUIT while on an exit tile (see Map Key).
	Otherwise, you will LOSE.

All commands will take up your turn, regardless of whether they
are valid or not. Inputs must be in the EXACT format specified
above otherwise they will be invalid. Commands are case-sensitive.
If you attempt to move into a wall, you will get an fail message
to indiccate your movement has not worked. In this case, you will
stay in the same location you were before the move command and 
you give up your turn.
---------------------------------------------------------------------
HOW THE BOT WORKS:
---------------------------------------------------------------------
The bot's first command will always be "LOOK". After that, the bot
checks if the player is within the LOOK range. If the player is 
within range, the bot will move towards the player. Otherwise, 
the bot will generate random commands and move accordingly.
If the bot encounters a wall, it will move in the opposite direction,
meaning that it cannot have invalid commands or fail movements.
After your turn, you will see which command the bot has entered.
---------------------------------------------------------------------
IMPLEMENTATION OF THE GAME:
---------------------------------------------------------------------
My code consists of 4 classes:
GameLogic: this class is the main class of the game. It will spawn
	players in and handle inputs from the player and the bot.
HumanPlayer: this class takes user input from to console and feeds
	the input into the GameLogic class.
BotPlayer: this class decides what the bot will do and feeds the
	command into the GameLogic class.
Map: this class contains the map for the dungeon. It also allows
	other maps to be read in from text files. 
---------------------------------------------------------------------
GOOD LUCK + ENJOY THE GAME.
---------------------------------------------------------------------