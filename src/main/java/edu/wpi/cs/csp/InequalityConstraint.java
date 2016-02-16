package edu.wpi.cs.csp;

public class InequalityConstraint implements Constraint{

    private final Item item1, item2;

    public InequalityConstraint(Item item1, Item item2) {
        this.item1 = item1;
        this.item2 = item2;
    }

    public Item getItem1() {
        return item1;
    }

    public Item getItem2() {
        return item2;
    }

    @Override
    public boolean test() {
        return item1.getBag() != item2.getBag();
    }
}