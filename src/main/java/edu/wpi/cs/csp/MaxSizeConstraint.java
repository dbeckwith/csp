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
     * @param csp
     * @return true if satisfied, false otherwise
     */
    @Override
    public Result test(CSP csp) {
        return bag.isOverMaxItems() ? Result.FAILED : Result.PASSED;
    }

    @Override
    public boolean involves(Item item) {
        return false;
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
