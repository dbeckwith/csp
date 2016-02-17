package edu.wpi.cs.csp;

/**
 * This class represents the constraint that checks if two items are contained within the same bag.
 *
 * @author Daniel Beckwith
 */
public class EqualityConstraint implements Constraint {

    private final Item item1, item2;

    /**
     * Creates an EqualityConstraint instance with the specified items.
     *
     * @param item1 The first {@link Item} to compare.
     * @param item2 The second {@link Item} to compare.
     */
    public EqualityConstraint(Item item1, Item item2) {
        this.item1 = item1;
        this.item2 = item2;
    }

    /**
     * Returns the first item.
     *
     * @return an {@link Item}
     */
    public Item getItem1() {
        return item1;
    }

    /**
     * Returns the second item.
     *
     * @return an {@link Item}
     */
    public Item getItem2() {
        return item2;
    }

    /**
     * Tests the constraint for being satisfied.
     *
     * @return true if satisfied, false otherwise
     */
    @Override
    public boolean test() {
        return item1.getBag() == item2.getBag();
    }
}
