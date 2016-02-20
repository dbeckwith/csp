package edu.wpi.cs.csp;

import java.util.HashSet;
import java.util.Set;

/**
 * This class represents the constraint satisfaction problem that is trying to be solved using backtracking.
 *
 * @author Daniel Beckwith
 */
public class CSP {

    private final Set<Item> items;
    private final Set<Bag> bags;
    private final Set<Constraint> constraints;

    /**
     * Creates a CSP instance.
     */
    public CSP() {
        items = new HashSet<>();
        bags = new HashSet<>();
        constraints = new HashSet<>();
    }

    /**
     * Returns the {@link Item}s in this CSP.
     *
     * @return a {@link Set&lt;{@link Item}&gt;}
     */
    public Set<Item> getItems() {
        return items;
    }

    /**
     * Returns the item with the specified name.
     *
     * @param name The string name of the {@link Item} to find.
     * @return a {@link Item}
     */
    public Item getItem(String name) {
        return items.stream().filter(item -> item.getName().equals(name)).findAny().orElse(null);
    }

    /**
     * Returns the set of bags in this CSP.
     *
     * @return a {@link Set&lt;{@link Bag}&gt;}
     */
    public Set<Bag> getBags() {
        return bags;
    }

    /**
     * Returns the bag with the specified name.
     *
     * @param name The string name of the {@link Bag} to find.
     * @return a {@link Bag}
     */
    public Bag getBag(String name) {
        return bags.stream().filter(bag -> bag.getName().equals(name)).findAny().orElse(null);
    }

    /**
     * Returns the set of constraints for this CSP.
     *
     * @return a {@link Set&lt;{@link Constraint}&gt;}
     */
    public Set<Constraint> getConstraints() {
        return constraints;
    }

    /**
     * Returns whether the current CSP satisfies all the associated constraints and if all the items are placed within a bag.
     *
     * @return true if conditions satisfied, false otherwise
     */
    public boolean isValid() {
        return constraints.stream().allMatch(Constraint::test) && getItems().stream().allMatch(item -> item.getBag() != null);
    }

    /**
     * Returns a string representation of this object.
     *
     * @return a {@link String}
     */
    @Override
    public String toString() {
        return "CSP{" +
                "items=" + items +
                ", bags=" + bags +
                ", constraints=" + constraints +
                '}';
    }
}
