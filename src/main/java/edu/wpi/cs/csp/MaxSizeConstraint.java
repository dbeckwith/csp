package edu.wpi.cs.csp;

/**
 * This class represents the constraint that checks if a bag has more items than its size.
 *
 * @author Aditya Nivarthi
 */
public class MaxSizeConstraint implements Constraint {

    private final Bag bag;

    /**
     * Creates a MaxSizeConstraint instance with the specified bag.
     *
     * @param bag The {@link Bag} to validate.
     */
    public MaxSizeConstraint(Bag bag) {
        this.bag = bag;
    }

    /**
     * Returns the bag in this constraint.
     *
     * @return a {@link Bag}
     */
    public Bag getBag() {
        return bag;
    }

    /**
     * Tests the constraint for being satisfied.
     *
     * @return true if satisfied, false otherwise
     */
    @Override
    public boolean test() {
        return !bag.isOverMaxItems();
    }

    /**
     * Returns a string representation of this object.
     *
     * @return a {@link String}
     */
    @Override
    public String toString() {
        return "MaxSizeConstraint{" +
                "bag=" + bag +
                '}';
    }
}
