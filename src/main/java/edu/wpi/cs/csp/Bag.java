package edu.wpi.cs.csp;

import java.util.HashSet;

public class Bag extends HashSet<Item> {
    protected final int MAX_SIZE;
    protected int currentSize = 0;
    protected double capacityPercentage = 0.9f;

    public Bag(int maxSizw) {
        this.MAX_SIZE = maxSizw;
    }

    @Override
    public boolean add(Item item) {
        if (super.add(item)) {
            item.setBag(this);
            return true;
        }

        return false;
    }

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

    public boolean move(Item item, Bag otherBag) {
        if (this.remove(item)) {
            item.setBag(otherBag);
            return otherBag.add(item);
        }

        return false;
    }

    public boolean isAtMinimumCapacity() {
        return Double.compare(currentSize, Math.floor(MAX_SIZE * capacityPercentage)) >= 0;
    }
}
