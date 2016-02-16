package edu.wpi.cs.csp;

public class MutualInclusivityConstraint implements Constraint {

    private final Item item1, item2;
    private final Bag bag1, bag2;

    public MutualInclusivityConstraint(Item item1, Item item2, Bag bag1, Bag bag2) {
        this.item1 = item1;
        this.item2 = item2;
        this.bag1 = bag1;
        this.bag2 = bag2;
    }

    public Item getItem1() {
        return item1;
    }

    public Item getItem2() {
        return item2;
    }

    public Bag getBag1() {
        return bag1;
    }

    public Bag getBag2() {
        return bag2;
    }

    @Override
    public boolean test() {
        return (item1.getBag() == bag1 && item2.getBag() == bag2) ||
                (item1.getBag() == bag2 && item2.getBag() == bag1) ||
                (item1.getBag() != bag1 && item1.getBag() != bag2 && item2.getBag() != bag1 && item2.getBag() != bag2);
    }
}
