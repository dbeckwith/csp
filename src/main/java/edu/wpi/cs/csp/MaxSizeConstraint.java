package edu.wpi.cs.csp;

public class MaxSizeConstraint implements Constraint {

    private final Bag bag;

    public MaxSizeConstraint(Bag bag) {
        this.bag = bag;
    }

    public Bag getBag() {
        return bag;
    }

    @Override
    public boolean test() {
        return !bag.isOverMaxItems();
    }

    @Override
    public String toString() {
        return "MaxSizeConstraint{" +
                "bag=" + bag +
                '}';
    }
}
