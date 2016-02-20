package edu.wpi.cs.csp;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CSPSolver {

    private static CSPSolver instance = new CSPSolver();

    public static CSPSolver getInstance() {
        return instance;
    }

    private CSPSolver() {}

    public boolean solve(CSP csp) {
        return backtracking(0, csp);
    }

    private boolean backtracking(int depth, CSP csp) {
        if (csp.isValid()) {
            return true;
        }

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
        else {
            return false;
        }
    }

    private Optional<Bag> getNextBag(CSP csp) {
        return csp.getBags().stream().filter(bag -> !bag.isAtMaxItems() && !bag.isAtCapacity()).findAny();
    }
}
