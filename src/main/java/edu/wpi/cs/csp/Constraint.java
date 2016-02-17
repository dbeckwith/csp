package edu.wpi.cs.csp;

/**
 * This interface defines the common functionality between the constraints.
 *
 * @author Daniel Beckwith
 */
public interface Constraint {

    /**
     * Tests the constraint for being satisfied.
     *
     * @return true if satisfied, false otherwise
     */
    boolean test();
}
