package edu.wpi.cs.csp;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.stream.Collectors;

/**
 * Main class.
 *
 * @author Aditya Nivarthi
 */
public class Main {

    /**
     * Main method.
     *
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        if (args.length < 0) {
            usage();
            return;
        }

        // Get filename
        String filename = args[0];

        CSP csp;
        try {
            csp = CSPReader.getInstance().read(new FileInputStream(filename));
        }
        catch (IOException e) {
            System.err.println("Error reading CSP info file: " + e);
            return;
        }

        // Get solution to CSP
        if (CSPSolver.getInstance().solve(csp)) {
            csp.getBags().forEach(bag -> {
                System.out.println(bag.getName() + " " + bag.stream().map(Item::getName).collect(Collectors.joining(" ")));
                System.out.println("number of items: " + bag.size());
                System.out.println("total weight: " + bag.getTotalWeight() + "/" + bag.getCapacity());
                System.out.println("wasted capacity: " + (bag.getCapacity() - bag.getTotalWeight()));
                System.out.println();
            });
        }
        else { // No solution found
            System.out.println("No solution found");
        }
    }

    /**
     * Prints program usage information to the console.
     */
    private static void usage() {
        System.out.println("Usage: java csp.jar csp_info_file");
    }
}
