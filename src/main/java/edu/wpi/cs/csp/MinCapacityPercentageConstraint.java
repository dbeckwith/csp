package edu.wpi.cs.csp;

public class MinCapacityPercentageConstraint implements Constraint {

    private final Bag bag;
    private final double minPercentage = 0.9;

    public MinCapacityPercentageConstraint(Bag bag) {
        this.bag = bag;
    }

    public Bag getBag() {
        return bag;
    }

    public double getMinPercentage() {
        return minPercentage;
    }

    @Override
    public boolean test() {
        return bag.getTotalWeight() >= Math.floor(minPercentage * bag.getCapacity());
    }

    @Override
    public String toString() {
        return "MinCapacityPercentageConstraint{" +
                "bag=" + bag +
                ", minPercentage=" + minPercentage +
                '}';
    }
}
