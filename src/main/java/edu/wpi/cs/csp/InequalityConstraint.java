package edu.wpi.cs.csp;

/**
 * This class represents the constraint that checks if two items are not contained within the same bag.
 *
 * @author Daniel Beckwith
 */
public class InequalityConstraint implements Constraint {

    private final Item item1, item2;

    /**
     * Creates an InequalityConstraint instance with the specified items.
     *
     * @param item1 The first {@link Item} to compare.
     * @param item2 The second {@link Item} to compare.
     */
    public InequalityConstraint(Item item1, Item item2) {
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

    @Override
    public Result test(CSP csp) {
        // if either of this constraint's items haven't been assigned, ignore this constraint
        if (!item1.hasAssignment() || !item2.hasAssignment()) return Result.IGNORED;
        return item1.getBag() != item2.getBag() ? Result.PASSED : Result.FAILED;
    }

    @Override
    public boolean involves(Item item) {
        return item1.equals(item) || item2.equals(item);
    }

    /**
     * Returns a string representation of this object.
     *
     * @return a {@link String}
     */
    @Override
    public String toString() {
        return "InequalityConstraint{" +
                "item1=" + item1.getName() +
                ", item2=" + item2.getName() +
                '}';
    }
}