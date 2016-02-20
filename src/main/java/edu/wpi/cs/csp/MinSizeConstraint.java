package edu.wpi.cs.csp;

public class MinSizeConstraint implements Constraint {

    private final Bag bag;
    private final int minSize;

    public MinSizeConstraint(Bag bag, int minSize) {
        this.bag = bag;
        this.minSize = minSize;
    }

    public Bag getBag() {
        return bag;
    }

    public int getMinSize() {
        return minSize;
    }

    @Override
    public boolean test() {
        return bag.size() >= minSize;
    }

    @Override
    public String toString() {
        return "MinSizeConstraint{" +
                "bag=" + bag +
                ", minSize=" + minSize +
                '}';
    }
}
