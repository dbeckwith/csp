package edu.wpi.cs.csp;

import java.util.HashSet;
import java.util.Set;

public class CSP {

    private final Set<Item> items;
    private final Set<Bag> bags;
    private final Set<Constraint> constraints;

    public CSP() {
        items = new HashSet<>();
        bags = new HashSet<>();
        constraints = new HashSet<>();
    }

    public Set<Item> getItems() {
        return items;
    }

    public Item getItem(String name) {
        return items.stream().filter(item -> item.getName().equals(name)).findAny().orElse(null);
    }

    public Set<Bag> getBags() {
        return bags;
    }

    public Bag getBag(String name) {
        return bags.stream().filter(bag -> bag.getName().equals(name)).findAny().orElse(null);
    }

    public Set<Constraint> getConstraints() {
        return constraints;
    }

    public boolean isValid() {
        return constraints.stream().allMatch(Constraint::test) && getItems().stream().allMatch(item -> item.getBag() != null);
    }

    @Override
    public String toString() {
        return "CSP{" +
                "items=" + items +
                ", bags=" + bags +
                ", constraints=" + constraints +
                '}';
    }
}
