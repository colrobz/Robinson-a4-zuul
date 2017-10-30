
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
    private int weight;

    /**
     * Creates item which holds a description
     */
    public Item(String description, int weight)
    {
        this.description = description;
        this.weight = weight;
    }
    
    /**
     * Returns item name and description
     */
    public String getDescription()
    {
        return description;
    }

}
