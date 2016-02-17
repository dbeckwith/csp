package edu.wpi.cs.csp;

import java.util.HashSet;

/**
 * This class represents a bag that can hold items.
 *
 * @author Aditya Nivarthi
 */
public class Bag extends HashSet<Item> {

    private final int MAX_SIZE;
    private int currentSize = 0;
    private double capacityPercentage = 0.9f;

    /**
     * Creates a new Bag instance with the specified max size.
     *
     * @param maxSize The maximum size for this bag.
     */
    public Bag(int maxSize) {
        MAX_SIZE = maxSize;
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
            currentSize++;
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

            currentSize--;
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
            currentSize--;
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
        return Double.compare(currentSize, Math.floor(MAX_SIZE * capacityPercentage)) >= 0;
    }
}
