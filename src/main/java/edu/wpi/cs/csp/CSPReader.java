package edu.wpi.cs.csp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the parser to handle reading the input file and setting up the CSP.
 *
 * @author Daniel Beckwith
 */
public class CSPReader {

    private static final String SECTION_PREFIX = "#####";
    private static CSPReader instance = new CSPReader();
    private List<String[]> bagLines;
    private boolean addedBags;

    /**
     * Creates a CSPReader instance.
     */
    private CSPReader() {
        bagLines = new ArrayList<>();
    }

    /**
     * Returns the singleton CSPReader instance.
     *
     * @return a CSPReader
     */
    public static CSPReader getInstance() {
        return instance;
    }

    /**
     * Reads an input stream and creates a {@link CSP} from it.
     *
     * @param in the {@link InputStream} to read
     * @return the parsed {@link CSP}
     * @throws IOException if there is an error in parsing the input
     */
    public CSP read(InputStream in) throws IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader(in));
        CSP csp = new CSP();

        String line;
        bagLines.clear();
        addedBags = false;

        r.readLine(); // read first section heading
        for (int section = 1; section <= 8; section++) {
            while ((line = r.readLine()) != null && // while not the end of the stream
                    !(line = line.trim()).startsWith(SECTION_PREFIX)) { // and not a section heading
                processLine(section, csp, line.split("\\s+"));
            }
        }

        return csp;
    }

    /**
     * Processes a single line in a given section of the input. Each line should correspond to one object in the CSP.
     *
     * @param section the section number, which determines what types of object this line will contain
     * @param csp     the CSP to update
     * @param parts   the parts of the line (space-separated)
     * @throws NumberFormatException if there was an error coercing a line part to a number
     */
    private void processLine(int section, CSP csp, String[] parts) throws NumberFormatException {
        if (section > 3 && !addedBags) {
            createBags(csp, 0, csp.getItems().size());
            addedBags = true;
        }
        switch (section) {
            case 1: // items
                csp.getItems().add(new Item(parts[0], Integer.parseInt(parts[1])));
                break;
            case 2: // bags
                bagLines.add(parts);
                break;
            case 3: // fitting limits
                createBags(csp, Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
                addedBags = true;
                break;
            case 4: // inclusion
            {
                Bag[] bags = new Bag[parts.length - 1];
                for (int i = 0; i < bags.length; i++) {
                    bags[i] = csp.getBag(parts[i + 1]);
                }
                csp.getConstraints().add(new InclusionConstraint(csp.getItem(parts[0]), bags));
                break;
            }
            case 5: // exclusion
            {
                Bag[] bags = new Bag[parts.length - 1];
                for (int i = 0; i < bags.length; i++) {
                    bags[i] = csp.getBag(parts[i + 1]);
                }
                csp.getConstraints().add(new ExclusionConstraint(csp.getItem(parts[0]), bags));
                break;
            }
            case 6: // equality
                csp.getConstraints().add(new EqualityConstraint(
                        csp.getItem(parts[0]),
                        csp.getItem(parts[1])
                ));
                break;
            case 7: // inequality
                csp.getConstraints().add(new InequalityConstraint(
                        csp.getItem(parts[0]),
                        csp.getItem(parts[1])
                ));
                break;
            case 8: // mutual inclusivity
                csp.getConstraints().add(new MutualInclusivityConstraint(
                        csp.getItem(parts[0]),
                        csp.getItem(parts[1]),
                        csp.getBag(parts[2]),
                        csp.getBag(parts[3])
                ));
                break;
        }
    }

    /**
     * Creates all of the bags given the CSP, minimum size and maximum size for each bag.
     *
     * @param csp     The {@link CSP} associated with the bags.
     * @param minSize The minimum size for each bag.
     * @param maxSize The maximum size for each bag.
     */
    private void createBags(CSP csp, int minSize, int maxSize) {
        bagLines.forEach(line -> {
            Bag bag = new Bag(line[0], maxSize, Integer.parseInt(line[1]));
            csp.getBags().add(bag);
            csp.getConstraints().add(new MinSizeConstraint(bag, minSize));
            csp.getConstraints().add(new MaxSizeConstraint(bag));
            csp.getConstraints().add(new MinCapacityPercentageConstraint(bag));
            csp.getConstraints().add(new MaxCapacityConstraint(bag));
        });
    }
}
