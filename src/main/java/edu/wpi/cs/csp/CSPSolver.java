package edu.wpi.cs.csp;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * This class represents the solver for the constraint satisfaction problems.
 *
 * @author Daniel Beckwith
 */
public class CSPSolver {

    private static CSPSolver instance = new CSPSolver();

    /**
     * Creates a CSPSolver instance.
     */
    private CSPSolver() {}

    /**
     * Returns the singleton CSPSolver instance.
     *
     * @return a CSPSolver
     */
    public static CSPSolver getInstance() {
        return instance;
    }

    /**
     * Returns whether a solution was found to the specified CSP.
     *
     * @param csp The {@link CSP} to solve.
     * @return true if solved, false otherwise
     */
    public boolean solve(CSP csp) {
        // set up domains
        csp.getItems().forEach(item -> csp.getDomains().put(item, csp.getBags().stream()
                .collect(Collectors.toSet())));

        return backtracking(0, csp);
    }

    /**
     * Recursive method to solve the CSP.
     *
     * @param depth The recursion depth currently being processed.
     * @param csp   The {@link CSP} to solve.
     * @return true if solved, false otherwise
     */
    private boolean backtracking(int depth, CSP csp) {
        if (csp.isValid()) {
            return true;
        }

        // Get the next "variable" to evaluate
        Optional<Item> nextItem = getNextItem(csp);
        if (nextItem.isPresent()) {
            Item item = nextItem.get();

            // neighbors are items that have a constraint involving that item and the current item
            List<Item> neighbors = csp.getItems().stream()
                    .filter(item2 -> !item2.equals(item))
                    .filter(item2 -> csp.getConstraints().stream()
                            .anyMatch(constraint -> constraint.involves(item) && constraint.involves(item2)))
                    .collect(Collectors.toList());

            // sort the bags by comparing the number of possible values for neighbors that adding the current item to
            // that bag would allow, in reverse because we want the largest number of possible values first
            // this is the least-constraining-value heuristic
            List<Bag> orderedBags = csp.getDomains().get(item)
                    .stream()
                    .collect(Collectors.toList());
            Collections.sort(orderedBags, Comparator.<Bag, Long>comparing(bag -> possibleValues(item, neighbors, bag, csp)).reversed());

            // go through each possible value
            for (Bag bag : orderedBags) {
                // save the variable domains so we can undo the changes
                csp.saveDomains();

                // do forward checking
                forwardCheck(bag, item, csp);

                // set the item's bag to the current bag
                bag.add(item);

                // recursive backtracking
                boolean result = backtracking(depth + 1, csp);
                if (result) {
                    return true;
                }

                // undo setting the item's bag to the current bag
                bag.remove(item);

                // undo any domain changes
                csp.restoreDomains();
            }
            // all value choices exhausted
            return false;
        }
        else {
            // No other items
            return false;
        }
    }

    /**
     * Returns an optional containing the next bag to process, if one exists.
     *
     * @param csp The {@link CSP} to get the next {@link Bag} from.
     * @return an {@link Optional&lt;{@link Bag}&gt;}
     */
    private Optional<Item> getNextItem(CSP csp) {
        return csp.getItems().stream()
                .filter(item -> !item.hasAssignment()) // filter out assigned items
                .min(Comparator // minimum
                        .<Item, Long>comparing(item -> remainingValues(item, csp)) // first compare by remaining values
                        .<Long>thenComparing(item -> -degree(item, csp))); // if RV is the same, go by max degree (min -degree)
    }

    /**
     * Returns true if the given item can be added to the given bag without violating any constraints
     *
     * @param bag
     * @param item
     * @param csp
     * @return
     */
    private boolean canAdd(Bag bag, Item item, CSP csp) {
        if (item.hasAssignment()) return false;

        bag.add(item);
        boolean valid = csp.getConstraints().stream()
                .allMatch(constraint -> constraint.test(csp) != Constraint.Result.FAILED);
        bag.remove(item);

        return valid;
    }

    /**
     * Returns the number of remaining possible values for the given item.
     *
     * @param item
     * @param csp
     * @return
     */
    private long remainingValues(Item item, CSP csp) {
        return csp.getDomains().get(item).size();
    }

    /**
     * Returns the degree of the given item, or the number of binary constraints it is involved in with unassigned items.
     *
     * @param item
     * @param csp
     * @return
     */
    private long degree(Item item, CSP csp) {
        return csp.getItems().stream()
                .filter(item2 -> !item2.equals(item) && !item2.hasAssignment()) // filter out items with assignments
                .mapToLong(item2 -> csp.getConstraints().stream() // for each item
                        .filter(constraint -> constraint.involves(item) && constraint.involves(item2)) // count all constraints involving both items
                        .count())
                .sum(); // sum over all neighbors
    }

    /**
     * Returns the number of possible values the neighbors of the given item can take on if the item was added to the given bag.
     *
     * @param item
     * @param neighbors
     * @param bag
     * @param csp
     * @return
     */
    private long possibleValues(Item item, Collection<Item> neighbors, Bag bag, CSP csp) {
        bag.add(item);
        long count = neighbors.stream()
                .mapToLong(neighbor -> csp.getBags().stream() // for each neighbor
                        .filter(bag2 -> canAdd(bag2, neighbor, csp)) // count the number of bags that the neighbor could be added to
                        .count())
                .sum(); // sum over all neighbors
        bag.remove(item);
        return count;
    }

    /**
     * Performs forward checking by mock-adding the given item to the given bag and updating the domains of all items.
     *
     * @param bag
     * @param item
     * @param csp
     */
    private void forwardCheck(Bag bag, Item item, CSP csp) {
        bag.add(item);
        csp.getItems().stream()
                .filter(item2 -> !item2.hasAssignment()) // for each unassigned item
                .forEach(item2 -> csp.getDomains().get(item2) // get its domain
                        .removeIf(domainBag -> !canAdd(domainBag, item2, csp))); // remove any bags that this item can no longer be added to
        bag.remove(item);
    }
}
