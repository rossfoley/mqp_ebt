package mqp.ebt.tool;

import mqp.ebt.MarioXMLManager;
import org.jgap.gp.impl.GPProgram;
import org.jgap.gp.impl.ProgramChromosome;

import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.BufferedReader;

/**
 * Tool to convert Chromosomes to CSV files
 * @author Ross Foley and Karl Kuhn
 */
public class EBTCSVConverter {
    public final String runName = "radius2_300_newtask2";
    public final int minGen = 1;
    public final int maxGen = 1000;
    public final int interval = 1; //each one
    private BufferedWriter writer;

    /**
     * Converts all generations between minGen and maxGen into a CSV file
     * Only taking the specified counts
     * @throws IOException Hopefully not used.
     */
    public void convert() throws IOException {
        System.out.println("Converting run " + runName + " from generation " + minGen + " to " + maxGen);

        // Initialize buffered writer
        String bwFileName = "csv/" + runName + ".csv";
        File bwFile = new File(bwFileName);
        bwFile.getParentFile().mkdirs();
        writer = new BufferedWriter(new FileWriter(bwFile));

        // CSV Header
        writeLine("Generation,Champion Fitness,Champion Complexity,Champion Depth");

        for (int generation = minGen; generation <= maxGen; generation++) {
            if ((generation % interval) == 0 || generation == 1) {
                GPProgram gp = MarioXMLManager.loadEBT(runName, generation);
                ProgramChromosome chromosome = gp.getChromosome(0);

                int fitness = (int) gp.getFitnessValue();
                int complexity = chromosome.getSize(0);
                int depth = chromosome.getDepth(0);

                writeLine(String.format("%d,%d,%d,%d", generation, fitness, complexity, depth));
            }
        }
        writer.close();
    }

    /**
     * Write a single line followed by newline
     * @param line the line to write
     * @throws IOException
     */
    private void writeLine(String line) throws IOException {
        writer.write(line);
        writer.newLine();
        writer.flush();
    }

    /**
     * Main method that just runs an instance
     * @param args The command line arguments
     * @throws IOException Hopefully unused
     */
    public static void main(String[] args) throws IOException {
        EBTCSVConverter cv = new EBTCSVConverter();
        cv.convert();
        System.out.println("Finished converting");
    }
}
