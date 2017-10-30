/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  The exits are labelled north, 
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 * 
 * @author  Colin Robinson
 * @version 2017.10.16
 */

import java.util.HashMap;
import java.util.Set;

public class Room 
{
    private String description;
    private HashMap<String, Room> exits;
    private Item item;

    /**
     * Return a description of the room’s exits,
     * for example, "Exits: north west".
     * @return A description of the available exits.
     */
    
//    public String getExitString()
//    {
//        String exitString = "Exits: ";
//        if(northExit != null)
//            exitString += "north ";
//        if(eastExit != null)
//            exitString += "east ";
//        if(southExit != null)
//            exitString += "south ";
//        if(westExit != null)
//            exitString += "west ";
//        return exitString;
//    }
            
    public String getExitString()
    {
      String returnString = "Exits";
      Set<String> keys = exits.keySet();
      for(String exit : keys) {
          returnString += " " + exit;
        }
        return returnString;
    }
    
    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<String, Room>();
    }

    /**
     * Add an item to this room
     */
    public void setItem(Item item)
    {
        this.item = item;
    }


     /**
     * Define an exit from this room.
     * @param direction The direction of the exit.
     * @param neighbor The room in the given direction.
     */
    public void setExit(String direction, Room neighbor)
    {
        exits.put(direction, neighbor);
    }
    
    /**
     * Return the room that is reached if we go from this
     * room in direction "direction "If there is no room in
     * that direction, return null.
     */
    public Room getExit(String direction)
    {
        return exits.get(direction);
    }
    
    /**
     * Return a long description of this room, of the form:
     *      You are in the kitchen.
     *      Exits: north west
     * @return A description of the room, including exits.
     */
    public String getLongDescription()
    {
        String descript = "You are " + description + ".\n" + getExitString();
        if (item != null) {
            descript += "This room contains " + item.getDescription() + "\n";
        }
        return descript + getExitString();

    }
    
    /**
     * @return The description of the room (the one that was defined in the constructor).
     */
    public String getDescription()
    {
        return description;
    }

//    public Room getExit(String direction)
//    {
//        if(direction.equals("north")) {
//           return northExit;
//        }
//        if(direction.equals("east")) {
//            return eastExit;
//        }
//        if(direction.equals("south")) {
//          return southExit;
//        }
//       if(direction.equals("west")) {
//            return westExit;
//        }
//        return null;
//    }

}
