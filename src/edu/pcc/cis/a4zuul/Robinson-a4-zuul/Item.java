
/**
 * Creates a list of items and holds a desctiption and weight
 *
 * @author Colin Robinson
 * @version 2017.10.24
 */

import java.util.HashMap;
import java.util.Set;

public class Item
{
    private String description;
    private HashMap<String, Item> items;
    
    /**
     * Creates item which holds a description
     */
    public Item(String description) 
    {
        this.description = description;
        items = new HashMap<String, Item>();
    }
    
    /**
     * Returns item name and description
     */
    public String getItemString()
    {
      String returnString = "Items";
      Set<String> keys = items.keySet();
      for(String item : keys) {
          returnString += " " + item;
        }
        return returnString;
    }
    
    
    /**
     * Returns string of finding an item plus its description
     */
    public String getItemDescription()
    {
        return "You find " + getItemString();
    }
    
    
}
