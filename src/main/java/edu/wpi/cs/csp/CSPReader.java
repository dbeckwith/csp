package edu.wpi.cs.csp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class CSPReader {

    private static CSPReader instance = new CSPReader();

    public static CSPReader getInstance() {
        return instance;
    }

    private static final String SECTION_PREFIX = "#####";

    private CSPReader() {}

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
     * @param csp the CSP to update
     * @param parts the parts of the line (space-separated)
     * @throws NumberFormatException if there was an error coercing a line part to a number
     */
    private void processLine(int section, CSP csp, String[] parts) throws NumberFormatException {
        switch (section) {
            case 1: // items
                csp.getItems().add(new Item(parts[0], Integer.parseInt(parts[1])));
                break;
            case 2: // bags
                csp.getBags().add(new Bag(parts[0], Integer.parseInt(parts[1])));
                break;
            case 3: // fitting limits
                csp.getConstraints().add(new BagFitConstraint(csp.getBags(), Integer.parseInt(parts[0]), Integer.parseInt(parts[1])));
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
}
