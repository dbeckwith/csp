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
     * Creates a new Bag instance with the specified name and max size.
     *
     * @param name     The name of this bag.
     * @param maxItems The maximum size for this bag.
     */
    public Bag(String name, int maxItems, int capacity) {
        this.name = name;
        this.capacity = capacity;
        items = new Item[maxItems];
        overMaxItems = false;
    }

    private Bag(String name, Item[] items, int capacity) {
        this.name = name;
        this.items = items;
        this.capacity = capacity;
        overMaxItems = false;
    }

    /**
     * Adds the specified item to the bag.
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

    public boolean contains(Item item) {
        for (int i = 0; i < items.length; i++) {
            if (Objects.equals(items[i], item)) {
                return true;
            }
        }
        return false;
    }

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

    public boolean isOverMaxItems() {
        return overMaxItems;
    }

    public boolean isAtMaxItems() {
        return size() == getMaxItems();
    }

    public int getCapacity() {
        return capacity;
    }

    public int getTotalWeight() {
        return Stream.of(items).filter(Objects::nonNull).mapToInt(Item::getWeight).sum();
    }

    public boolean isAtCapacity() {
        return getTotalWeight() == getCapacity();
    }

    public boolean isOverCapacity() {
        return getTotalWeight() > getCapacity();
    }

    public Bag copy() {
        return new Bag(name, items.clone(), capacity);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bag)) return false;

        Bag bag = (Bag) o;

        return name.equals(bag.name);

    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

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
