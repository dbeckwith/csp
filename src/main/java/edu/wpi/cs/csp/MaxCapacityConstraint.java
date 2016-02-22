package edu.wpi.cs.csp;

/**
 * This class represents the constraint that checks if a bag is over its total weight capacity.
 *
 * @author Aditya Nivarthi
 */
public class MaxCapacityConstraint implements Constraint {

    private final Bag bag;

    /**
     * Creates a MaxCapacityConstraint instance with the specified bag.
     *
     * @param bag The {@link Bag} to validate.
     */
    public MaxCapacityConstraint(Bag bag) {
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

    @Override
    public Result test(CSP csp) {
        return bag.isOverCapacity() ? Result.FAILED : Result.PASSED;
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
        return "MaxCapacityConstraint{" +
                "bag=" + bag +
                '}';
    }
}
