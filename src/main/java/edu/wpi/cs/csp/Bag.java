package edu.wpi.cs.csp;

import java.util.HashSet;
import java.util.stream.Collectors;

/**
 * This class represents a bag that can hold items.
 *
 * @author Aditya Nivarthi
 */
public class Bag extends HashSet<Item> {

    private static final double CAPACITY_PERCENTAGE = 0.9f;

    private final String name;
    private final int maxSize;

    /**
     * Creates a new Bag instance with the specified name and max size.
     *
     * @param name    The name of this bag.
     * @param maxSize The maximum size for this bag.
     */
    public Bag(String name, int maxSize) {
        this.name = name;
        this.maxSize = maxSize;
    }

    /**
     * Adds the specified item to the bag.
     *
     * @param item The {@link Item} to add to the bag.
     * @return true if added, false otherwise
     */
    @Override
    public boolean add(Item item) {
        if (super.add(item)) {
            item.setBag(this);
            return true;
        }

        return false;
    }

    /**
     * Removes the specified object from the bag. If the object can be removed, it is. If it is of type {@link Item}, then that item's bag will be set to null.
     *
     * @param o The object to be removed.
     * @return true if removes, false otherwise
     */
    @Override
    public boolean remove(Object o) {
        if (super.remove(o)) {
            if (o instanceof Item) {
                ((Item) o).setBag(null);
            }
            return true;
        }

        return false;
    }

    /**
     * Moves the specified item from one bag to the other specified bag.
     *
     * @param item     The {@link Item} to move to the other bag.
     * @param otherBag The other bag to move the {@link Item} into.
     * @return true if the {@link Item} is removed from this bag and moved to the other bag, false otherwise
     */
    public boolean move(Item item, Bag otherBag) {
        if (remove(item)) {
            item.setBag(otherBag);
            return otherBag.add(item);
        }

        return false;
    }

    /**
     * Returns whether this bag is at the minimum required capacity.
     *
     * @return true if at minimum capacity, false otherwise
     */
    public boolean isAtMinimumCapacity() {
        return size() >= Math.floor(maxSize * CAPACITY_PERCENTAGE);
    }

    /**
     * Returns the name of this bag.
     *
     * @return a {@link String}
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the maximum size of this bag.
     *
     * @return an integer.
     */
    public int getMaxSize() {
        return maxSize;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bag)) return false;
        if (!super.equals(o)) return false;

        Bag items = (Bag) o;

        return name.equals(items.name);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + name.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Bag{" +
                "name='" + name + '\'' +
                ", maxSize=" + maxSize +
                ", items=" + stream().map(Item::getName).collect(Collectors.joining(", ", "[", "]")) +
                '}';
    }
}
