package edu.wpi.cs.csp;

import java.io.FileInputStream;
import java.io.IOException;

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

        System.out.println(csp);
    }

    private static void usage() {
        System.out.println("Usage: java csp.jar csp_info_file");
    }
}
