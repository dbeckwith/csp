package edu.wpi.cs.csp;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This class represents the constraint that checks if a specified item is not in any of the specified bags.
 *
 * @author Daniel Beckwith
 */
public class ExclusionConstraint implements Constraint {

    private final Item item;
    private final Bag[] bags;

    /**
     * Creates an ExclusionConstraint instance with the specified item and list of bags.
     *
     * @param item The {@link Item} to test for being contained in the bags.
     * @param bags The list of {@link Bag}s to check for the specified item.
     */
    public ExclusionConstraint(Item item, Bag... bags) {
        this.item = item;
        this.bags = bags;
    }

    /**
     * Returns the item in this constraint.
     *
     * @return an {@link Item}
     */
    public Item getItem() {
        return item;
    }

    /**
     * Returns the list of bags in this constraint.
     *
     * @return a {@link Bag}[]
     */
    public Bag[] getBags() {
        return bags;
    }

    @Override
    public Result test(CSP csp) {
        // if this constraint's item hasn't been assigned, ignore this constraint
        if (!item.hasAssignment()) return Result.IGNORED;
        return Stream.of(bags).noneMatch(bag -> bag.contains(item)) ? Result.PASSED : Result.FAILED;
    }

    @Override
    public boolean involves(Item item) {
        return this.item.equals(item);
    }

    /**
     * Returns a string representation of this object.
     *
     * @return a {@link String}
     */
    @Override
    public String toString() {
        return "ExclusionConstraint{" +
                "item=" + item.getName() +
                ", bags=" + Stream.of(bags).map(Bag::getName).collect(Collectors.joining(", ", "[", "]")) +
                '}';
    }
}
