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
//        for (int i = 0; i < depth; i++) System.out.print('\t');
//        System.out.println(csp.getBags());
//        for (int i = 0; i < depth; i++) System.out.print('\t');
//        System.out.println(csp.getDomains().get(new Item("C", 0)));
        if (csp.isValid()) {
            return true;
        }

        // Get the next "variable" to evaluate
        Optional<Item> nextItem = getNextItem(csp);
        if (nextItem.isPresent()) {
            Item item = nextItem.get();
//            List<Bag> orderedBags = csp.getBags().stream()
//                    .filter(bag -> !bag.isAtMaxItems() && !bag.isOverMaxItems()
//                            && !bag.isAtCapacity() && !bag.isOverCapacity())
//                    .filter(bag -> csp.getConstraints().stream()
//                            .anyMatch(constraint -> {
//                                Set<Item> vars = constraint.getVariables(csp).collect(Collectors.toSet());
//                                return vars.contains(item) && vars.stream()
//                                        .filter(item2 -> !item2.equals(item))
//                                        .allMatch(Item::isAssigned);
//                            }))
//                    .collect(Collectors.toList());
            List<Bag> orderedBags = csp.getDomains().get(item)
                    .stream()
                    .collect(Collectors.toList());
//            for (int i = 0; i < depth; i++) System.out.print('\t');
//            System.out.println("domain for " + item.getName() + ": " + orderedBags.stream().map(Bag::getName).collect(Collectors.joining(", ")));
            List<Item> neighbors = csp.getItems().stream()
                    .filter(item2 -> !item2.equals(item))
                    .filter(item2 -> csp.getConstraints().stream()
                            .anyMatch(constraint -> constraint.involves(item) && constraint.involves(item2)))
                    .collect(Collectors.toList());
//            for (int i = 0; i < depth; i++) System.out.print('\t');
//            System.out.println("neighbors for " + item.getName() + ": " + neighbors.stream().map(Item::getName).collect(Collectors.joining(", ")));
            Collections.sort(orderedBags, Comparator.<Bag, Long>comparing(bag -> possibleValues(item, neighbors, bag, csp)).reversed());
            for (Bag bag : orderedBags) {
                csp.saveDomains();

                forwardCheck(bag, item, csp);

                bag.add(item);
                boolean result = backtracking(depth + 1, csp);
                if (result) {
                    return true;
                }
                bag.remove(item);

                csp.restoreDomains();
            }
//            for (int i = 0; i < depth; i++) System.out.print('\t');
//            System.out.println("all possibilities exhausted");
            return false;
        }
        else { // No other items
//            for (int i = 0; i < depth; i++) System.out.print('\t');
//            System.out.println("no more items");
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
                .filter(item -> !item.hasAssignment())
//                .findAny();
                .min(Comparator.<Item, Long>comparing(item -> remainingValues(item, csp))
                        .<Long>thenComparing(item -> degree(item, csp)));
    }

    private boolean canAdd(Bag bag, Item item, CSP csp) {
        if (item.hasAssignment()) return false;
        bag.add(item);
        boolean valid = csp.getConstraints().stream()
                .allMatch(constraint -> {
//                    System.out.println(constraint);
//                    System.out.println(constraint.test(csp));
                    return constraint.test(csp) != Constraint.Result.FAILED;
                });
        bag.remove(item);
        return valid;
    }

    private long remainingValues(Item item, CSP csp) {
        return csp.getDomains().get(item).size();
//        return csp.getBags().stream()
//                .filter(bag -> canAdd(bag, item, csp))
//                .count();
    }

    private long degree(Item item, CSP csp) {
        return csp.getItems().stream()
                .filter(item2 -> !item2.equals(item) && !item2.hasAssignment())
                .mapToLong(item2 -> csp.getConstraints().stream()
                        .filter(constraint -> constraint.involves(item) && constraint.involves(item2))
                        .count())
                .sum();
    }

    private long possibleValues(Item item, Collection<Item> neighbors, Bag bag, CSP csp) {
        bag.add(item);
        long count = neighbors.stream()
                .mapToLong(neighbor -> csp.getBags().stream()
                        .filter(bag2 -> canAdd(bag2, neighbor, csp))
                        .count())
                .sum();
        bag.remove(item);
        return count;
    }

    private void forwardCheck(Bag bag, Item item, CSP csp) {
        bag.add(item);
        csp.getItems().stream()
                .filter(item2 -> !item2.hasAssignment())
                .forEach(item2 -> {
//                            System.out.println("================================");
//                            System.out.println(item2);
//                            System.out.println(csp.getDomains().get(item2));
                    csp.getDomains().get(item2).removeIf(domainBag -> {
//                                System.out.println(domainBag);
//                                System.out.println(!canAdd(domainBag, item2, csp));
                        return !canAdd(domainBag, item2, csp);
                    });
//                            System.out.println(csp.getDomains().get(item2));
                });
        bag.remove(item);
    }
}
