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
     * @return a {@link Result}
     */
    Result test(CSP csp);

    /**
     * Tests whether this constraint uses the given item as a parameter.
     *
     * @param item
     * @return
     */
    boolean involves(Item item);
}
