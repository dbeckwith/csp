package edu.wpi.cs.csp;

/**
 * This class represents the constraint that checks if a bag has the minimum total weight capacity filled.
 *
 * @author Aditya Nivarthi
 */
public class MinCapacityPercentageConstraint implements Constraint {

    private final Bag bag;
    private final double minPercentage = 0.9;

    /**
     * Creates a MinCapacityPercentageConstraint instance with the specified bag.
     *
     * @param bag The {@link Bag} to validate.
     */
    public MinCapacityPercentageConstraint(Bag bag) {
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
     * Returns the minimum capacity percentage that needs to be filled in the bag.
     *
     * @return a double
     */
    public double getMinPercentage() {
        return minPercentage;
    }

    @Override
    public Result test(CSP csp) {
        // if not all items in the CSP have been assigned, ignore this constraint
        if (!csp.getItems().stream().allMatch(Item::hasAssignment)) return Result.IGNORED;
        return bag.getTotalWeight() >= Math.floor(minPercentage * bag.getCapacity()) ? Result.PASSED : Result.FAILED;
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
        return "MinCapacityPercentageConstraint{" +
                "bag=" + bag +
                ", minPercentage=" + minPercentage +
                '}';
    }
}
