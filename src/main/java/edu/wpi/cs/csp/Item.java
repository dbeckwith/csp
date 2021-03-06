package edu.wpi.cs.csp;

/**
 * This class represents an item that can be added into a bag.
 *
 * @author Aditya Nivarthi
 */
public class Item {

    private final String name;
    private final int weight;
    private Bag bag;

    /**
     * Creates an Item instance with the specified name and weight. Sets the containing bag to null.
     *
     * @param name   The name of this item.
     * @param weight The weight of this item.
     */
    public Item(String name, int weight) {
        this(name, weight, null);
    }

    /**
     * Creates an Item instance with the specified name, weight, and container bag.
     *
     * @param name   The name of this item.
     * @param weight The weight of this item.
     * @param bag    The {@link Bag} containing this item.
     */
    public Item(String name, int weight, Bag bag) {
        this.name = name;
        this.weight = weight;
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
     * Returns the weight of this item.
     *
     * @return an integer
     */
    public int getWeight() {
        return weight;
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
     * Returns true if this item has been assigned a bag.
     *
     * @return
     */
    public boolean hasAssignment() {
        return bag != null;
    }

    /**
     * Creates and returns a copy of this object.
     *
     * @return an Item
     */
    public Item clone() {
        return new Item(name, weight, bag);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;

        Item item = (Item) o;

        return name.equals(item.name);

    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    /**
     * Returns a string representation of this object.
     *
     * @return a {@link String}
     */
    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", weight=" + weight +
                ", bag=" + (bag == null ? "<none>" : bag.getName()) +
                '}';
    }
}
