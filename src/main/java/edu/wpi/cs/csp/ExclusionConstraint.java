package edu.wpi.cs.csp;

import java.util.stream.Stream;

public class ExclusionConstraint implements Constraint {

    private final Item item;
    private final Bag[] bags;

    public ExclusionConstraint(Item item, Bag... bags) {
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
        return Stream.of(bags).noneMatch(bag -> bag.contains(item));
    }
}
