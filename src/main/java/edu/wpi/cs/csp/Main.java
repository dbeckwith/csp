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
        String filename = args[0];

        CSP csp;
        try {
            csp = CSPReader.getInstance().read(new FileInputStream(filename));
        }
        catch (IOException e) {
            System.err.println("Error reading CSP info file: " + e);
            return;
        }

//        System.out.println("Items:");
//        csp.getItems().forEach(System.out::println);
//        System.out.println("Bags:");
//        csp.getBags().forEach(System.out::println);
//        System.out.println("Constraints:");
//        csp.getConstraints().stream().sorted(Comparator.comparing(constraint -> constraint.getClass().getSimpleName())).forEach(System.out::println);

        if (CSPSolver.getInstance().solve(csp)) {
            csp.getBags().forEach(bag -> {
                System.out.println(bag.getName() + " " + bag.stream().map(Item::getName).collect(Collectors.joining(" ")));
                System.out.println("number of items: " + bag.size());
                System.out.println("total weight: " + bag.getTotalWeight() + "/" + bag.getCapacity());
                System.out.println("wasted capacity: " + (bag.getCapacity() - bag.getTotalWeight()));
                System.out.println();
            });
        }
        else {
            System.out.println("No solution found");
        }
    }

    private static void usage() {
        System.out.println("Usage: java csp.jar csp_info_file");
    }
}
