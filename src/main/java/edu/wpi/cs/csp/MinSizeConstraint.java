package edu.wpi.cs.csp;

/**
 * This class represents the constraint that checks if a bag has more items than its minimum required size.
 *
 * @author Aditya Nivarthi
 */
public class MinSizeConstraint implements Constraint {

    private final Bag bag;
    private final int minSize;

    /**
     * Creates a MinSizeConstraint instance with the specified bag.
     *
     * @param bag The {@link Bag} to validate.
     */
    public MinSizeConstraint(Bag bag, int minSize) {
        this.bag = bag;
        this.minSize = minSize;
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
     * Returns the minimum size for this bag.
     *
     * @return an integer
     */
    public int getMinSize() {
        return minSize;
    }

    /**
     * Tests the constraint for being satisfied.
     *
     * @return true if satisfied, false otherwise
     */
    @Override
    public boolean test() {
        return bag.size() >= minSize;
    }

    /**
     * Returns a string representation of this object.
     *
     * @return a {@link String}
     */
    @Override
    public String toString() {
        return "MinSizeConstraint{" +
                "bag=" + bag +
                ", minSize=" + minSize +
                '}';
    }
}
