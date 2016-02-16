package edu.wpi.cs.csp;

import java.util.Set;

public class BagFitConstraint implements Constraint {

    private final Set<Bag> bags;
    private final int min, max;

    public BagFitConstraint(Set<Bag> bags, int min, int max) {
        this.bags = bags;
        this.min = min;
        this.max = max;
    }

    public Set<Bag> getBags() {
        return bags;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    @Override
    public boolean test() {
        return bags.stream().allMatch(bag -> bag.size() >= getMin() && bag.size() <= getMax());
    }
}
