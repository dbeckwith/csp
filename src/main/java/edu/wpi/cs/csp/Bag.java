package edu.wpi.cs.csp;

import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This class represents a bag that can hold items.
 *
 * @author Aditya Nivarthi
 */
public class Bag {

    private final String name;
    private final int capacity;
    private final Item[] items;
    private boolean overMaxItems;

    /**
     * Creates a new Bag instance with the specified name, max size and total weight capacity.
     *
     * @param name     The name of this bag.
     * @param maxItems The maximum size for this bag.
     * @param capacity The total weight capacity this bag can hold.
     */
    public Bag(String name, int maxItems, int capacity) {
        this.name = name;
        this.capacity = capacity;
        items = new Item[maxItems];
        overMaxItems = false;
    }

    /**
     * Creates a new Bag instance with the specified name, item list and total weight capacity.
     *
     * @param name     The name of this bag.
     * @param items    The list of items in this bag.
     * @param capacity The total weight capacity this bag can hold.
     */
    private Bag(String name, Item[] items, int capacity) {
        this.name = name;
        this.items = items;
        this.capacity = capacity;
        overMaxItems = false;
    }

    /**
     * Adds the specified item to the bag. If the item cannot be added because the bag is full, the overflow flag is set to true and the item is not added.
     *
     * @param item The {@link Item} to add to the bag.
     * @return true if added, false otherwise
     */
    public boolean add(Item item) {
        for (int i = 0; i < items.length; i++) {
            if (items[i] == null) {
                items[i] = item;
                item.setBag(this);
                overMaxItems = false;
                return true;
            }
        }
        overMaxItems = true;
        return false;
    }

    /**
     * Removes the specified item from the bag.
     *
     * @param item The {@link Item} to remove from the bag.
     * @return true if removed, false otherwise
     */
    public boolean remove(Item item) {
        for (int i = 0; i < items.length; i++) {
            if (Objects.equals(items[i], item)) {
                items[i] = null;
                item.setBag(null);
                return true;
            }
        }
        return false;
    }

    /**
     * Returns whether the bag contains the specified item.
     *
     * @param item The {@link Item} to search for in this bag.
     * @return true if the item is contained, false otherwise
     */
    public boolean contains(Item item) {
        for (int i = 0; i < items.length; i++) {
            if (Objects.equals(items[i], item)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns a stream of the items in this bag, excluding the null item slots.
     *
     * @return a {@link Stream&lt;{@link Item}&gt;}
     */
    public Stream<Item> stream() {
        return Stream.of(items).filter(Objects::nonNull);
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
     * Returns the number of items contained in the bag.
     *
     * @return an integer
     */
    public int size() {
        return (int) Stream.of(items).filter(Objects::nonNull).count();
    }

    /**
     * Returns the maximum size of this bag.
     *
     * @return an integer.
     */
    public int getMaxItems() {
        return items.length;
    }

    /**
     * Returns whether the bag is over filled with items.
     *
     * @return true if over filled, false otherwise
     */
    public boolean isOverMaxItems() {
        return overMaxItems;
    }

    /**
     * Returns whether the bag is filled completely.
     *
     * @return true if full, false otherwise
     */
    public boolean isAtMaxItems() {
        return size() == getMaxItems();
    }

    /**
     * Returns the total weight capacity of the bag.
     *
     * @return an integer
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Returns the total weight of all the items in the bag.
     *
     * @return an integer
     */
    public int getTotalWeight() {
        return Stream.of(items).filter(Objects::nonNull).mapToInt(Item::getWeight).sum();
    }

    /**
     * Returns whether the total weight of the items in the bag is equal to the bag's total weight capacity.
     *
     * @return true if equal, false otherwise
     */
    public boolean isAtCapacity() {
        return getTotalWeight() == getCapacity();
    }

    /**
     * Returns whether the total weight of the items in the bag is larger than the bag's total weight capacity.
     *
     * @return true if the total weight is over the capacity, false otherwise
     */
    public boolean isOverCapacity() {
        return getTotalWeight() > getCapacity();
    }

    /**
     * Creates and returns a clone of this object.
     *
     * @return a Bag
     */
    @Override
    public Bag clone() {
        return new Bag(name, items.clone(), capacity);
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param o The object to compare to this object.
     * @return true if equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bag)) return false;

        Bag bag = (Bag) o;

        return name.equals(bag.name);

    }

    /**
     * Returns a hash code value for the object.
     *
     * @return an integer
     */
    @Override
    public int hashCode() {
        return name.hashCode();
    }

    /**
     * Returns a string representation of the object.
     *
     * @return a {@link String}
     */
    @Override
    public String toString() {
        return "Bag{" +
                "name='" + name + '\'' +
                ", capacity=" + capacity +
                ", maxSize=" + items.length +
                ", size=" + size() +
                ", items=" + Stream.of(items).filter(Objects::nonNull).map(Item::getName).collect(Collectors.joining(", ", "[", "]")) +
                '}';
    }
}
