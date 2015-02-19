package mqp.ebt;

import com.thoughtworks.xstream.XStream;
import org.jgap.gp.IGPProgram;

import java.io.*;

/**
 * Utility functions related to saving and loading EBT XML
 * @author Ross Foley and Karl Kuhn
 */
public class MarioXMLManager {
    public static String getFileName(String runName, int generation) {
        return "db/" + runName + "/generation" + generation + ".xml";
    }

    public static IGPProgram loadEBT(String fileName) throws FileNotFoundException {
        XStream xstream = new XStream();
        InputStream is = new FileInputStream(new File(fileName));
        return (IGPProgram) xstream.fromXML(is);
    }

    public static IGPProgram loadEBT(String runName, int generation) throws FileNotFoundException {
        return loadEBT(getFileName(runName, generation));
    }

    public static void writeEBT(IGPProgram gp, String fileName) throws FileNotFoundException {
        // Initialize the file and converter
        XStream xstream = new XStream();
        File file = new File(fileName);

        // Ensure that the parent folders exist
        file.getParentFile().mkdirs();

        // Write the file
        FileOutputStream os = new FileOutputStream(file);
        xstream.toXML(gp, os);
    }

    public static void writeEBT(IGPProgram gp, String runName, int generation) throws FileNotFoundException {
        writeEBT(gp, getFileName(runName, generation));
    }
}
