package edu.wpi.cs.csp;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Solution extends HashSet<Bag>{

    public Solution() {
        super();
    }

    public Solution(Collection<? extends Bag> c) {
        super(c);
    }

    public boolean isValid(Set<Constraint> constraints) {
        return stream().allMatch(Bag::isAtMinimumCapacity) && constraints.stream().allMatch(Constraint::test);
    }
}
