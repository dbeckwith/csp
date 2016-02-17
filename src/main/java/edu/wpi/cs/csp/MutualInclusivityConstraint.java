package edu.wpi.cs.csp;

/**
 * This class represents the constraint that checks if two items are in different bags within a pair of two bags, or if there are not in any of the two bags.
 *
 * @author Daniel Beckwith
 */
public class MutualInclusivityConstraint implements Constraint {

    private final Item item1, item2;
    private final Bag bag1, bag2;

    /**
     * Creates a MutualInclusivityConstraint instance with the specified items and bags.
     *
     * @param item1 The first {@link Item} to validate.
     * @param item2 The second {@link Item} to validate.
     * @param bag1  The first {@link Bag} to validate.
     * @param bag2  The second {@link Bag} to validate.
     */
    public MutualInclusivityConstraint(Item item1, Item item2, Bag bag1, Bag bag2) {
        this.item1 = item1;
        this.item2 = item2;
        this.bag1 = bag1;
        this.bag2 = bag2;
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
     * Returns the first bag.
     *
     * @return an {@link Bag}
     */
    public Bag getBag1() {
        return bag1;
    }

    /**
     * Returns the second bag.
     *
     * @return an {@link Bag}
     */
    public Bag getBag2() {
        return bag2;
    }

    /**
     * Tests the constraint for being satisfied.
     *
     * @return true if satisfied, false otherwise
     */
    @Override
    public boolean test() {
        return (item1.getBag() == bag1 && item2.getBag() == bag2) ||
                (item1.getBag() == bag2 && item2.getBag() == bag1) ||
                (item1.getBag() != bag1 && item1.getBag() != bag2 && item2.getBag() != bag1 && item2.getBag() != bag2);
    }
}
