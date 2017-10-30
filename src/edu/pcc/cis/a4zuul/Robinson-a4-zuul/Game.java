/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * -
 * 
 *  This code has been modified to become the game "Rainbow Pizza".
 *  A pizza delivery type game where the player traverses through
 *  the rainbow themed neighborhood. Possibly to make deliveries and earn cash.
 * 
 * @author  Colin Robinson
 * @version 2017.10.16 for CIS-233J Assignment 1
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;
    
    /**
     * This prints all available exits for the player's current room.
     * Created to reduce duplciate code.
     * changed how exits are printed
     * changed how exits and description is accessed and printed.
     */
    private void printLocationInfo()
    {
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * This prints all availabe exits.
     */
    private void look()
    {
        System.out.println(currentRoom.getLongDescription());
    }
    
    /**
     * This prints a message about delivering a pizza.
     */
    private void deliver()
    {
        System.out.println("You Just Delivered a Pizza " + currentRoom.getDescription());
    }
        
    
// LEFT FOR GRADING    
//        private void printLocationInfo()
//    {
//        System.out.println("You are " + currentRoom.getDescription());
//        System.out.print("Exits: ");
//        if(currentRoom.getExit("north") != null) {
//            System.out.print("north ");
//        }
//        if(currentRoom.getExit("east") != null) {
//            System.out.print("east ");
//        }
//        if(currentRoom.getExit("south") != null) {
//            System.out.print("south ");
//        }
//        if(currentRoom.getExit("west") != null) {
//            System.out.print("west ");
//        }
//        System.out.println();
//    }
    
    
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
    }

    /**
     * Create all the rooms and link their exits together.
     * 
     * Modified to be all the colors of the rainbow and
     * the exits essentially connect in the pattern of the letter H.
     * Set to start in the middle at green.
     * 
     * modified to account for HashMap
     */
    private void createRooms()
    {
        Room red, orange, yellow, green, blue, indigo, violet;
      
        // create the rooms
        red = new Room("in the red neighborhood");
        orange = new Room("in the orange neighborhood");
        yellow = new Room("in the yellow neighborhood");
        green = new Room("in the green neighborhood");
        blue = new Room("in the blue neighborhood");
        indigo = new Room("in the indigo neighborhood");
        violet = new Room("in the violet neighborhood");
        
        // initialise room exits
        red.setExit("south", orange);
        
        orange.setExit("north", red);
        orange.setExit("east", green);
        orange.setExit("south", yellow);
        
        yellow.setExit("north", orange);
        
        green.setExit("east", indigo);
        green.setExit("west", orange);
        
        blue.setExit("south", indigo);
        
        indigo.setExit("north", blue);
        indigo.setExit("south", violet);
        indigo.setExit("west", green);
        
        violet.setExit("north", indigo);

        currentRoom = green;  // start game outside

        // create items
        Item pizza, cash, dog;

        pizza = new Item("A warm cheesey pizza just sitting there!");
        cash = new Item("A big wad cash lying in the street!");
        dog = new Item("A snarling dog watch out!");

        red.setItem(dog);

        violet.setItem(cash);

        orange.SetItem(pizza);

    }
    
    /**
     * Create items with their descriptions
     */
    private void createItems()
    {
        Item pizza, dog, cash;
        
        //create items
        pizza = new Item("is tasty and cheesy");
        dog = new Item("is scary and barking at you");
        cash = new Item("is money to spend");
    }
    
    /**
     *  Main play routine.  Loops until end of play.
     *  
     *  Changed exit message.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thanks for playing.  See ya!");
    }

    /**
     * Print out the opening message for the player.
     * Added print location to reduce code duplication.
     * Changed welcome message to Rainbow Pizza
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to Rainbow Pizza Delivery!");
        System.out.println("Rainbow Pizza an exciting game ");
        System.out.println("where you traverse the rainbow neighborhoods ");
        System.out.println("and make deliveries to earn cash! ");
        System.out.println();
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        printLocationInfo();
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("go")) {
            goRoom(command);
        }
        else if (commandWord.equals("look")) {
            look();
        }
        else if (commandWord.equals("deliver")) {
            deliver();
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }

        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     * 
     * Modified error message and added print location
     * to reduce code duplciation.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("This way is a dead-end!");
        }
        else {
            currentRoom = nextRoom;
            printLocationInfo();
    }
    }
    
    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
}
