package edu.wpi.cs.csp;

public class Item {
    protected Bag bag = null;
    protected String name = "";

    public Item(Bag bag, String name) {
        this.bag = bag;
        this.name = name;
    }

    public Item(String name) {
        this.name = name;
        bag = null;
    }

    public Bag getBag() {
        return bag;
    }

    public void setBag(Bag bag) {
        this.bag = bag;
    }

    public String getName() {
        return name;
    }
}
