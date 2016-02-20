package edu.wpi.cs.csp;

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
        Optional<Bag> nextBag = getNextBag(csp);
        if (nextBag.isPresent()) {
            Bag bag = nextBag.get();
            List<Item> orderedItems = csp.getItems().stream().filter(item -> item.getBag() == null).collect(Collectors.toList());
            Collections.sort(orderedItems, Comparator.comparing(item -> {
                return item.hashCode();
            }));
            for (Item item : orderedItems) {
                bag.add(item);
                boolean result = backtracking(depth + 1, csp);
                if (result) {
                    return true;
                }
                bag.remove(item);
            }
            return false;
        }
        else { // No other bags
            return false;
        }
    }

    /**
     * Returns an optional containing the next bag to process, if one exists.
     *
     * @param csp The {@link CSP} to get the next {@link Bag} from.
     * @return an {@link Optional&lt;{@link Bag}&gt;}
     */
    private Optional<Bag> getNextBag(CSP csp) {
        return csp.getBags().stream().filter(bag -> !bag.isAtMaxItems() && !bag.isAtCapacity()).findAny();
    }
}
