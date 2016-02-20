package edu.wpi.cs.csp;

public class MaxCapacityConstraint implements Constraint {

    private final Bag bag;

    public MaxCapacityConstraint(Bag bag) {
        this.bag = bag;
    }

    public Bag getBag() {
        return bag;
    }

    @Override
    public boolean test() {
        return !bag.isOverCapacity();
    }

    @Override
    public String toString() {
        return "MaxCapacityConstraint{" +
                "bag=" + bag +
                '}';
    }
}
