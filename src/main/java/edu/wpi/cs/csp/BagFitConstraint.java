package edu.wpi.cs.csp;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * This class represents the constraint on {@link Bag}s that the number of {@link Item}s within each bag is between the specified minimum and maximum sizes.
 *
 * @author Daniel Beckwith
 */
public class BagFitConstraint implements Constraint {

    private final Set<Bag> bags;
    private final int min, max;

    /**
     * Creates a BagFitConstraint instance with the specified bags, min and max.
     *
     * @param bags The {@link Set} of {@link Bag}s to validate this constraint against.
     * @param min  The minimum number of {@link Item}s in the bag.
     * @param max  The maximum number of {@link Item}s in the bag.
     */
    public BagFitConstraint(Set<Bag> bags, int min, int max) {
        this.bags = bags;
        this.min = min;
        this.max = max;
    }

    /**
     * Returns the set of bags being validated on this constraint.
     *
     * @return a {@link Set}&lt;{@link Bag}&gt;
     */
    public Set<Bag> getBags() {
        return bags;
    }

    /**
     * Returns the minimum number of items needed in the bag.
     *
     * @return an integer
     */
    public int getMin() {
        return min;
    }

    /**
     * Returns the maximum number of items needed in the bag.
     *
     * @return an integer
     */
    public int getMax() {
        return max;
    }

    /**
     * Tests the constraint for being satisfied.
     *
     * @return true if satisfied, false otherwise
     */
    @Override
    public boolean test() {
        return bags.stream().allMatch(bag -> bag.size() >= getMin() && bag.size() <= getMax());
    }

    @Override
    public String toString() {
        return "BagFitConstraint{" +
                "bags=" + bags.stream().map(Bag::getName).collect(Collectors.joining(", ", "[", "]")) +
                ", min=" + min +
                ", max=" + max +
                '}';
    }
}
