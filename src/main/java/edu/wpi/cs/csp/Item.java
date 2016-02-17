package edu.wpi.cs.csp;

/**
 * This class represents an item that can be added into a bag.
 *
 * @author Aditya Nivarthi
 */
public class Item {

    private Bag bag = null;
    private String name = "";

    /**
     * Creates an Item instance with the specified container bag and name.
     *
     * @param bag  The {@link Bag} containing this item.
     * @param name The name of this item.
     */
    public Item(Bag bag, String name) {
        this.bag = bag;
        this.name = name;
    }

    /**
     * Creates an Item instance with the specified name. Sets the containing bat to null.
     *
     * @param name The name of this item.
     */
    public Item(String name) {
        this.name = name;
        bag = null;
    }

    /**
     * Returns the bag containing this item.
     *
     * @return a {@link Bag}
     */
    public Bag getBag() {
        return bag;
    }

    /**
     * Sets the bag that is containing this item.
     *
     * @param bag The {@link Bag} containing this item.
     */
    public void setBag(Bag bag) {
        this.bag = bag;
    }

    /**
     * Returns the name of this item.
     *
     * @return a {@link String}
     */
    public String getName() {
        return name;
    }

    /**
     * Returns whether the specified object is "equal" to this object.
     *
     * @param o The object to compare to this object.
     * @return true if equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        if (bag != null ? !bag.equals(item.bag) : item.bag != null) return false;
        return name.equals(item.name);
    }

    /**
     * Returns the hashcode for this class.
     *
     * @return an integer
     */
    @Override
    public int hashCode() {
        int result = bag != null ? bag.hashCode() : 0;
        result = 31 * result + name.hashCode();
        return result;
    }
}
