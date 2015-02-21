package mqp.ebt;

import com.thoughtworks.xstream.XStream;
import org.jgap.gp.impl.GPProgram;

import java.io.*;

/**
 * Utility functions related to saving and loading EBT XML
 * @author Ross Foley and Karl Kuhn
 */
public class MarioXMLManager {
    public static String getFileName(String runName, int generation) {
        return "db/" + runName + "/generation" + generation + ".xml";
    }

    public static GPProgram loadEBT(String fileName) throws FileNotFoundException {
        XStream xstream = new XStream();
        return (GPProgram) xstream.fromXML(new File(fileName));
    }

    public static GPProgram loadEBT(String runName, int generation) throws FileNotFoundException {
        return loadEBT(getFileName(runName, generation));
    }

    public static void writeEBT(GPProgram gp, String fileName) throws IOException {
        // Initialize the file and convert
        XStream xstream = new XStream();
        File file = new File(fileName);

        // Ensure that the parent folders exist
        file.getParentFile().mkdirs();

        // Write the file
        FileOutputStream os = new FileOutputStream(file);
        xstream.toXML(gp, os);
        os.close();
    }

    public static void writeEBT(GPProgram gp, String runName, int generation) throws IOException {
        writeEBT(gp, getFileName(runName, generation));
    }
}
