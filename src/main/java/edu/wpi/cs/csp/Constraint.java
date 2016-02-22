package edu.wpi.cs.csp;

/**
 * This interface defines the common functionality between the constraints.
 *
 * @author Daniel Beckwith
 */
public interface Constraint {

    enum Result {
        PASSED, FAILED, IGNORED
    }

    /**
     * Tests the constraint for being satisfied.
     *
     * @param csp
     * @return true if satisfied, false otherwise
     */
    Result test(CSP csp);

    boolean involves(Item item);
}
