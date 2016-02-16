package edu.wpi.cs.csp;

import java.util.stream.Stream;

public class InclusionConstraint implements Constraint {

    private final Item item;
    private final Bag[] bags;

    public InclusionConstraint(Item item, Bag... bags) {
        this.item = item;
        this.bags = bags;
    }

    public Item getItem() {
        return item;
    }

    public Bag[] getBags() {
        return bags;
    }

    @Override
    public boolean test() {
        return Stream.of(bags).anyMatch(bag -> bag.contains(item));
    }
}
